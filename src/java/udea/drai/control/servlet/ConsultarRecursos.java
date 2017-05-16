package udea.drai.control.servlet;

import udea.drai.model.dao.impl.VpnDAOJdbc;
import udea.drai.model.dao.impl.RecursoDAOJdbc;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import udea.drai.model.dto.EMail;
import udea.drai.control.ModuloAgenda;
import udea.drai.model.dao.RecursoDAO;
import udea.drai.model.dto.DefaultReserva;
import udea.drai.model.dto.Recurso;
import udea.drai.model.dto.Usuario;

public class ConsultarRecursos extends HttpServlet {

//    private static final String PUNTUAL = "0";
//    private static final String LIBRE = "libre";
    private static final String PROPERTIESPATH = "udea.drai.utils.properties.ContenidoCorreoAprobacion";
    private static final ResourceBundle PROPIEDADES = ResourceBundle.getBundle(PROPERTIESPATH);
//    private static final String APROBAR = "1";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
//        PrintWriter out = response.getWriter();
        String sw = null;
        String hora = null;
        String fecha = null;
        
//        List listaSW1 = new ArrayList();
//        List listaOcupado1 = new ArrayList();        
//        List listaLibre1 = new ArrayList();                

        Vector listaSW = new Vector(10, 5);
        Vector listaOcupado = new Vector(10, 5);
        Vector listaLibre = new Vector(10, 5);
        HttpSession sesion = request.getSession(true);
        RecursoDAOJdbc recursoDAO = new RecursoDAOJdbc();

        sw = request.getParameter("software");
        hora = request.getParameter("hora");
        fecha = request.getParameter("date");
        //Obtiene los computadores que tienen ese software
        listaSW = recursoDAO.getNuevoSW(sw);

        if (listaSW.isEmpty()) {
            //NO hay computadores con este software instalado
            sesion.setAttribute("mensaje", "ERROR001");
            response.sendRedirect("MensajeError.jsp");
            return;
        } else {
            //Obtiene el primer equipo donde esta instalado el software
            sw = (String) listaSW.get(0);
        }
        String nombre = (String) sesion.getAttribute("nombre");
        String correoEstudiante = (String) sesion.getAttribute("correo");
        String cedula = (String) sesion.getAttribute("cedula1");
        Usuario user = (Usuario) sesion.getAttribute("user1");

        sesion.setAttribute("hora", hora);
        sesion.setAttribute("fecha", fecha);
        DefaultReserva reserva = new DefaultReserva();

        reserva.setResponsable(cedula);
        reserva.setFechaIni(fecha);
        reserva.setFechaFin(fecha);

        //Obtiene los recursos libres
        listaSW = getFreeSW(sw, recursoDAO, listaSW);
        Recurso recurso = recursoDAO.getRecurso(Integer.parseInt(sw));

        VpnDAOJdbc vpn = new VpnDAOJdbc();
        boolean decide = true;
        listaOcupado = vpn.retornaSoftware(fecha, hora.substring(0, 5));
        if (listaOcupado.size() > 0) {
            for (int i = 0; i < listaSW.size(); i++) {
                decide = true;
                for (int j = 0; j < listaOcupado.size(); j++) {
                    if (listaOcupado.get(j).equals(listaSW.get(i))) {
                        decide = false;
                    }
                }
                if (decide) {
                    listaLibre.add(listaSW.get(i));
                }
            }
        } else {
            listaLibre = listaSW;
        }
        if (listaLibre.size() > 0) {
            sw = (String) listaLibre.get(0);
            recurso = recursoDAO.getRecurso(Integer.parseInt(sw));
            decide = true;
            //ERROR
            recurso.setTipo(Integer.parseInt(sw));
        } else {
            decide = false;
        }

        recursoDAO.cerrarConexion();

        if (decide) {
            sesion.setAttribute("nodatos", null);
            //Comprueba la reserva
            String respuesta = aprobarReserva(reserva, recurso, hora, cedula, fecha, user);
            if (!respuesta.equals("APROBADO")) {
                sesion.setAttribute("mensaje", respuesta);
                response.sendRedirect("MensajeError.jsp");
                return;
            }

            EMail correo = generarCorreo(recurso, hora, fecha, correoEstudiante, nombre, sesion, user);

            if (correo != null) {
                request.setAttribute("email", correo);
                //Envia el correo
                RequestDispatcher dispa = getServletContext().getNamedDispatcher("SendEMail");
                dispa.include(request, response);
            } else {
                //Ya existe una reserva en la fecha y hora indicada
                sesion.setAttribute("noMail", "no");
            }
        } else {
            sesion.setAttribute("nodatos", "nodatos");
            return;
        }
        response.sendRedirect("asignacionDeTurno.jsp");
//        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/asignacionDeTurno.jsp");
//        dispatcher.forward(request, response);        
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    public String getServletInfo() {
        return "Short description";
    }

    private String aprobarReserva(DefaultReserva reserva, Recurso r, String hora, String cedula, String fecha, Usuario user) {
        reserva.setRecurso(r);
        reserva.setUbicacion("U de A");
        reserva.setHoraIni(hora.substring(0, 2));
        reserva.setHoraFin(String.valueOf(Integer.parseInt(hora.substring(0, 2)) + 2));
        reserva.setMotivo("Reserva horario extremo");
        reserva.setEstado("A");

        ModuloAgenda moduloAgenda = new ModuloAgenda();
        
        if (moduloAgenda.consultarUsuarioAgendado(cedula, fecha)) {
            return "ERROR006";
        }
        return moduloAgenda.preReserva(reserva, user);       
    }

    /**
     * Metodo que retorna los recursos libres para un software
     *
     * @param sw
     * @param rdao
     * @param listaSW
     * @return
     */
    private Vector getFreeSW(String sw, RecursoDAOJdbc rdao, Vector listaSW) {
        Recurso recurso = rdao.getRecurso(Integer.parseInt(sw));
        Vector lista = new Vector(10, 5);
        for (int i = 0; i < listaSW.size(); i++) {
            try {
                sw = (String) listaSW.get(i);
                recurso = rdao.getRecurso(Integer.parseInt(sw));
                if ("libre".equals(recurso.getClasificacion())) {
                    lista.add(sw);
                }
            } catch (Exception e) {
                System.out.println(e.toString() + " error en lista free");
            }
            recurso = rdao.getRecurso(Integer.parseInt(sw));
        }
        return lista;
    }

    private EMail generarCorreo(Recurso recurso, String hora, String fecha, String correoEstudiante, String nombre, HttpSession sesion, Usuario user) {

        String cuerpoEmail = null;
        cuerpoEmail = PROPIEDADES.getString("bodyReservaPuntual");
        
        cuerpoEmail = cuerpoEmail.replaceFirst("@aprobacion", "APROBADA");
        sesion.setAttribute("estado", "APROBADA");
        cuerpoEmail = cuerpoEmail.replaceFirst("@codigo", user.retornaCodigo());
//        String dattt = user.retornaCodigo();
        cuerpoEmail = cuerpoEmail.replaceFirst("@tipo", "ESTUDIANTE");
        cuerpoEmail = cuerpoEmail.replaceFirst("@nombre", nombre);
        cuerpoEmail = cuerpoEmail.replaceFirst("@equipo", recurso.getNombre());
        cuerpoEmail = cuerpoEmail.replaceFirst("@fecha", fecha);
        cuerpoEmail = cuerpoEmail.replaceFirst("@horaInicial", hora.substring(0, 5));
        cuerpoEmail = cuerpoEmail.replaceFirst("@horaFinal", hora.substring(6, hora.length()));
        cuerpoEmail = cuerpoEmail.replaceFirst("@tipoRecurso", Integer.toString(recurso.getIdRecurso()));
        EMail correo = new EMail();
        correo.setTo(correoEstudiante);
        correo.setFrom(PROPIEDADES.getString("from"));
        correo.setBody(cuerpoEmail);
        correo.setSubject(PROPIEDADES.getString("subject"));
        return correo;
    }
}