package udea.drai.control;

import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import udea.drai.model.dao.impl.AgendaDAOJdbc;
import udea.drai.model.dao.impl.RecursoDAOJdbc;
import udea.drai.model.dao.impl.ReservaDAOJdbc;
import udea.drai.model.dao.impl.VpnDAOJdbc;
import udea.drai.model.dto.*;

public class ModuloAgenda {

    int consecutivoReserva;
    String consecutivoProgramacion;

    public String reservar(Calendar[] fechas, String horaInicial, String horaFinal, int recursoReservado, String usuario, String dias, String fechaInicial, String fechaFinal, String tipoReserva, String ubicacion, String comentario, String estadoReserva, Usuario user, Recurso rec) {
        ManejoFechas manejoFechas = new ManejoFechas();
        AgendaDAOJdbc ad = new AgendaDAOJdbc();
        RecursoDAOJdbc recursoDao1 = new RecursoDAOJdbc();
        //Obtiene en donde esta incluido el software
        int incluido = recursoDao1.obtenerDondeEstaIncluido(Integer.toString(recursoReservado));
        System.out.println(Integer.toString(recursoReservado));
        recursoDao1.cerrarConexion();

        if (incluido != 0) {
            //Obtiene la disponibilidad de un recurso, o la ocupacion del recurso
            Vector resultadoDisponibilidadSuper = determinarDisponibilidad(fechas, horaInicial, horaFinal, incluido, usuario);
            Vector fechasNoDisponiblesSuper = (Vector) resultadoDisponibilidadSuper.elementAt(0);
            Vector fechasDisponiblesSuper = (Vector) resultadoDisponibilidadSuper.elementAt(1);
            if (fechasNoDisponiblesSuper.size() != 0) {
                if (fechasDisponiblesSuper.size() != 0) {
                    StringBuffer respuesta = new StringBuffer();
                    respuesta.append("\tRESERVAS NO DISPONIBLES\n\n");
                    for (int i = 0; i < fechasNoDisponiblesSuper.size(); i++) {
                        Reserva r = (Reserva) fechasNoDisponiblesSuper.elementAt(i);
                        respuesta.append("Hora Inicial: " + r.getHoraIni() + "  " + "Hora Final: " + r.getHoraFin() + "  ");
                        respuesta.append("Motivo: " + r.getMotivo() + "\n" + "Responsable: " + r.getResponsable() + "\n\n");
                    }
                    ad.cerrarConexion();
                    return respuesta.toString();
                }
                ad.cerrarConexion();
//                return "Sala reservada en todas las fechas\ny horas solicitados";
                return "ERROR003";
            }
        }

        Vector resultadoDisponibilidad = determinarDisponibilidad(fechas, horaInicial, horaFinal, recursoReservado, usuario);

        Vector fechasNoDisponibles = (Vector) resultadoDisponibilidad.elementAt(0);
        Vector fechasDisponibles = (Vector) resultadoDisponibilidad.elementAt(1);
        if (fechasNoDisponibles.size() == 0) {
            RecursoDAOJdbc recursoDao = new RecursoDAOJdbc();
            Vector incluidos = recursoDao.obtenerRecursosIncluidos(Integer.toString(recursoReservado));
            recursoDao.cerrarConexion();
            if (incluidos.size() != 0) {
                for (int i = 0; i < incluidos.size(); i++) {
                    Recurso auxiliar = (Recurso) incluidos.elementAt(i);
                    int nRecursoIncluido = auxiliar.getIdRecurso();

                    Vector resultadoIncluido = determinarDisponibilidad(fechas, horaInicial, horaFinal, nRecursoIncluido, usuario);
                    Vector ocupadasIncluido = (Vector) resultadoIncluido.elementAt(0);
                    Vector libresIncluido = (Vector) resultadoIncluido.elementAt(1);
                    if (ocupadasIncluido.size() != 0) {
                        ad.cerrarConexion();
                        StringBuffer respuesta = new StringBuffer();
                        respuesta.append("\tExisten reservas para PCS incluidos en la Sala\n\n");
                        for (int j = 0; j < ocupadasIncluido.size(); j++) {
                            Reserva res = (Reserva) ocupadasIncluido.elementAt(j);
                            respuesta.append("Hora Inicial: " + res.getHoraIni() + "  " + "Hora Final: " + res.getHoraFin() + "  ");
                            respuesta.append("Motivo: " + res.getMotivo() + "\n" + "Responsable: " + res.getResponsable() + "\n\n");
                        }
                        return respuesta.toString();
                    }
                }
            }

            ad.cerrarConexion();

            Random aleatorio = new Random();
            int num = Math.abs(aleatorio.nextInt(99000));
            String numString = Integer.toString(num);
            String ced = usuario + numString;
            ReservaDAOJdbc reserva = new ReservaDAOJdbc();

            reserva.insertarReserva(usuario, ced, fechaInicial, fechaFinal, horaInicial, horaFinal, recursoReservado, dias, estadoReserva);

            VpnDAOJdbc vpn = new VpnDAOJdbc();
            String fecha_Ini = fechaInicial + " " + horaInicial + ":00:00";
            String fecha_Fin = fechaFinal + " " + Integer.toString(Integer.parseInt(horaFinal) - 1) + ":55:00";
            char c0 = (char) ('a' + (char) aleatorio.nextInt(26));
            char c1 = (char) ('a' + (char) aleatorio.nextInt(26));
            char c2 = (char) ('a' + (char) aleatorio.nextInt(26));
            char c3 = (char) ('a' + (char) aleatorio.nextInt(26));
            String code0 = numString + c3 + c2 + c0 + c1 + "";
            user.asignacodigo(code0);
            vpn.insertaUsuario(usuario, user.retornaNombre(), code0, user.retornaPrograma(), fecha_Ini, fecha_Fin, recursoReservado, user.retornaCorreo(), rec.getIp(), rec.getMac());

            this.consecutivoReserva = reserva.obtenerConsecutivo(usuario, ced);
            this.consecutivoProgramacion = Integer.toString(this.consecutivoReserva);
            AgendaDAOJdbc ado = new AgendaDAOJdbc();

            for (int i = 0; i < fechasDisponibles.size(); i++) {
                Agenda aux = (Agenda) fechasDisponibles.elementAt(i);
                ado.insertarProgramacion(this.consecutivoProgramacion, manejoFechas.convertirFecha(aux.getFecha()), aux.getHora(), usuario, tipoReserva, ubicacion, comentario, recursoReservado, usuario, aux.getAccion(), estadoReserva);
            }

            ado.cerrarConexion();
            reserva.cerrarConexion();
            if ("A".equals(estadoReserva)) {
                return "APROBADO";
//                return "Reserva Registrada Exitosamente\n";
            }
            return "APROBADO";
//            return "Su reserva ha quedado en estado de aprobaci&oacute;n, la respuesta a su solicitud le ser&aacute; enviada via Email\n";
        }

        ad.cerrarConexion();

        if (fechasDisponibles.size() != 0) {
            StringBuffer respuesta = new StringBuffer();
            respuesta.append("\tRESERVAS NO DISPONIBLES\n\n");
            for (int i = 0; i < fechasNoDisponibles.size(); i++) {
                Reserva r = (Reserva) fechasNoDisponibles.elementAt(i);
                respuesta.append("Hora Inicial: " + r.getHoraIni() + "  " + "Hora Final: " + r.getHoraFin() + "  ");
                respuesta.append("Motivo: " + r.getMotivo() + "\n" + "Responsable: " + r.getResponsable() + "\n\n");
            }
            return respuesta.toString();
        }

        return "Todas las fechas y horarios solicitados \nSe encuentran ocupados";
    }

    public Vector<Reserva> obtenerReservas() {
        Vector reservas = new Vector();
        return reservas;
    }

    /**
     * 
     * @param fechas
     * @param horaInicial
     * @param horaFinal
     * @param recursoReservado
     * @param responsable
     * @return 
     */
    public Vector<Vector> determinarDisponibilidad(Calendar[] fechas, String horaInicial, String horaFinal, int recursoReservado, String responsable) {
        Vector reservasNoDisponibles = new Vector(100, 50);
        Vector reservasRealizadas = new Vector(100, 50);
        Vector resultado = new Vector(2);
        AgendaDAOJdbc agendaDao = new AgendaDAOJdbc();
        ReservaDAOJdbc reservaDao = new ReservaDAOJdbc();
        ManejoFechas manejoFechas = new ManejoFechas();
        for (int i = 0; i < fechas.length; i++) {
            Calendar fecha = fechas[i];
            String fechaAux = manejoFechas.convertirFecha(fecha);

            int horaInicio = Integer.parseInt(horaInicial);
            int horaFin = Integer.parseInt(horaFinal) - 1;
            int horaReserva = horaInicio;
            while (horaReserva <= horaFin) {
                String hora = Integer.toString(horaReserva);
                String accion = null;
                Agenda programada = agendaDao.getReserva(fechaAux, hora, recursoReservado, responsable);
                if (programada == null) {
                    if (horaReserva == horaInicio) {
                        accion = "I";
                    }
                    if (horaReserva == horaFin) {
                        accion = "F";
                    }
                    if (((horaReserva != horaInicio ? 1 : 0) & (horaReserva != horaFin ? 1 : 0)) != 0) {
                        accion = "N";
                    }

                    if (horaInicial.equalsIgnoreCase(Integer.toString(Integer.parseInt(horaFinal) - 1))) {
                        accion = "IF";
                    }
                    Agenda registrada = new Agenda();
                    registrada.setAccion(accion);
                    reservasRealizadas.addElement(registrada);
                    registrada.setFecha(manejoFechas.establecerFecha(fechaAux));
                    registrada.setHora(hora);
                } else if (!estaAgregada(Integer.parseInt(programada.getIdReserva()), reservasNoDisponibles)) {
                    Reserva reserva = reservaDao.getReserva(Integer.parseInt(programada.getIdReserva()));
                    reserva.setMotivo(programada.getMotivo());
                    reserva.setResponsable(programada.getNombrePersona());
                    reserva.setUbicacion(programada.getUbicacion());
                    reservasNoDisponibles.addElement(reserva);
                }

                horaReserva += 1;
            }
        }

        resultado.add(0, reservasNoDisponibles);
        resultado.add(1, reservasRealizadas);

        agendaDao.cerrarConexion();
        reservaDao.cerrarConexion();
        return resultado;
    }

    public boolean estaAgregada(int dsIdReserva, Vector<Reserva> reservas) {
        for (int i = 0; i < reservas.size(); i++) {
            if (((Reserva) reservas.elementAt(i)).getIdReserva() == dsIdReserva) {
                return true;
            }
        }
        return false;
    }

    public String preReserva(DefaultReserva reserva, Usuario user) {
        String tipo = null;
        ManejoFechas mf = new ManejoFechas();
        String dias;
        Calendar[] fechas;
        if (reserva.isPuntual()) {
            Calendar fecha = mf.establecerFecha(reserva.getFechaIni());

            dias = mf.getDiaPuntual(fecha);

            fechas = new Calendar[1];
            fechas[0] = fecha;
            tipo = "Puntual";
        } else {
            dias = reserva.getDias();

            if (dias.length() == 0) {
                return "Seleccione dias para hacer una reserva periodica";
            }
            String[] diasReserva = new String[dias.length()];

            for (int i = 0; i < dias.length(); i++) {
                Character c = new Character(dias.charAt(i));
                diasReserva[i] = new String(c.toString());
            }

            fechas = mf.calcularFechasParaReservar(diasReserva, mf.establecerFecha(reserva.getFechaIni()), mf.establecerFecha(reserva.getFechaFin()));

            if (fechas.length == 0) {
                return "Los dias no corresponden al rango de fechas";
            }
            tipo = "Periodica";
        }

        return reservar(fechas, reserva.getHoraIni(), reserva.getHoraFin(), reserva.getRecurso().getIdRecurso(), reserva.getResponsable(), dias, reserva.getFechaIni(), reserva.getFechaFin(), tipo, reserva.getUbicacion(), reserva.getMotivo(), reserva.getEstado(), user, reserva.getRecurso());
    }
    
    /**
    * Metodo que comprueba si un usuario tiene reservas para una fecha especifica
    * @param cedula
    * @param fecha
    * @return 
    */
    public boolean consultarUsuarioAgendado(String cedula, String fecha) {
        AgendaDAOJdbc consulta = new AgendaDAOJdbc();
        if (consulta.reservo(cedula, fecha)) {
            consulta.cerrarConexion();
            return true;
        }
        consulta.cerrarConexion();
        return false;
    }
}