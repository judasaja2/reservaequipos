package udea.drai.control.servlet;

import java.io.IOException;
import java.util.Arrays;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import udea.drai.utils.ServicioWeb;
import udea.drai.utils.security.Validacion;
import udea.drai.model.dto.Usuario;
import udea.drai.utils.Constantes;

public class Login extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession sesion = request.getSession(true);

        //Se obtiene los parametros ingresados
        String userName = request.getParameter("usuario");
        String pass = request.getParameter("clave");

        //Valida si el usuario y contraseña son vacios
        if( ("").equals(userName) || ("").equals(pass) ){
            response.sendRedirect("logeo.jsp");
            return;
        }
            
        //Se valida el usuario y contraseña ingresados son correctos
        String cedula = Validacion.authUser(userName, pass, Boolean.TRUE);
        String espera = "si";
        String correo = "";
        String nombre = "";
        Usuario user = null;
        
        user = new Usuario();
        user.asignaCorreo("lelelele@udea.edu.co");
        user.asignaDocumento("1234567890");
        user.asignaNombre("Juan David Sánchez Jaramillo");
        user.asignaPrograma("536");
        user.asignaTipoUsuario("ESTUDIANTE");
        correo = user.retornaCorreo();           
        nombre = user.retornaNombre();
        //Se valida lo retornado por el servicio de autenticacion
        /*if ("ERROR".equals(cedula.substring(0, 5))) {
            sesion.setAttribute("mensaje", "ERROR002");
            response.sendRedirect("MensajeError.jsp");
            return;
        } else {
            //se obtiene los datos personales del estudiante
//            user = ServicioWeb.obtenerInfoPrueba(cedula);
            user = ServicioWeb.obtenerDatosPersonalesEstudiante(cedula);
            correo = user.retornaCorreo();           
            nombre = user.retornaNombre();
        }*/// ---> Comentado por Juan David Sánchez Jaramillo
        System.out.println(user.retornaPrograma());
        //se validad los datos de la persona retornados por el servicio Web
        if ((nombre == null) || (("").equals(nombre)) || (correo == null) || (("").equals(correo))) {
            sesion.setAttribute("mensaje", "ERROR004");
            response.sendRedirect("MensajeError.jsp");
            return;
        //se valida que el estudiante pertenezca a la facultad de Ingeniería
//        } else if (! Arrays.asList( Constantes.PROGRAMASINGENIERIA ).contains(user.retornaPrograma())) {
//            sesion.setAttribute("mensaje", "ERROR005");
//            response.sendRedirect("MensajeError.jsp");
//            return;
        } else {
            sesion.setAttribute("cedula1", cedula);
            sesion.setAttribute("user1", user);
            sesion.setAttribute("correo", correo);
            sesion.setAttribute("nombre", nombre);
            sesion.setAttribute("espera", espera);
        }

//        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/asignacionRecurso.jsp");
//        dispatcher.forward(request, response);
        response.sendRedirect("asignacionRecurso.jsp");

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

}