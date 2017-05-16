package udea.drai.model.dao.impl;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;
import udea.drai.model.dao.ds.BDConexion;
import udea.drai.control.ManejoFechas;
import udea.drai.model.dao.AgendaDAO;
import udea.drai.model.dto.Agenda;

public class AgendaDAOJdbc implements AgendaDAO{

    private BDConexion conexion;

    public AgendaDAOJdbc() {
        this.conexion = new BDConexion(false);
    }

    public void cerrarConexion() {
        this.conexion.closeConnection();
    }

    public Vector<Agenda> obtenerProgramacionMesobtenerRecursosTipo(String inicio, String fin, String recurso) {
        Vector programaciones = new Vector(100, 50);
        try {
            Connection connection = this.conexion.getConnection();
            ManejoFechas manejoFechas = new ManejoFechas();
            PreparedStatement ps = connection.prepareStatement(BDConexion.getStatement("obtenerProgramacionMes1"));
            ps.setString(1, inicio);
            ps.setString(2, fin);
            ps.setString(3, recurso);
            ResultSet agenda = ps.executeQuery();
            while (agenda.next()) {
                Agenda auxiliar = new Agenda();
                auxiliar.setEstado(agenda.getString("CDESTADO"));
                auxiliar.setHora(Integer.toString(agenda.getInt("DSHORA")));
                auxiliar.setNombrePersona(agenda.getString("DSNOMBRE"));
                auxiliar.setMotivo(agenda.getString("DSMOTIVO"));
                auxiliar.setUbicacion(agenda.getString("DSUBICACION"));
                auxiliar.setTipo(agenda.getString("DSTIPORESERVA"));
                auxiliar.setResponsable(agenda.getString("NMRESPONSABLE"));
                Date fecha5 = agenda.getDate("FEFECHA");
                String fechaAuc = String.valueOf(fecha5);
                auxiliar.setFecha(manejoFechas.establecerFecha(fechaAuc));
                programaciones.addElement(auxiliar);
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return programaciones;
    }

    public Vector<Agenda> obtenerProgramacionUsuario(String fechaInicio, String fechaFin, int recurso, String usuario) {
        Vector programaciones = new Vector(100, 50);
        try {
            Connection connection = this.conexion.getConnection();
            PreparedStatement ps = connection.prepareStatement(BDConexion.getStatement("obtenerProgramacionUsuario"));
            ps.setString(1, fechaInicio);
            ps.setString(2, fechaFin);
            ps.setInt(3, recurso);
            ps.setString(4, usuario);
            ResultSet agenda = ps.executeQuery();
            while (agenda.next()) {
                Agenda auxiliar = new Agenda();
                auxiliar.setHora(Integer.toString(agenda.getInt("DSHORA")));
                auxiliar.setNombrePersona(agenda.getString("DSNOMBRE"));
                auxiliar.setMotivo(agenda.getString("DSMOTIVO"));
                auxiliar.setCodigo(agenda.getInt("NMCODIGO"));
                auxiliar.setIdReserva(agenda.getString("DSIDRESERVA"));
                programaciones.addElement(auxiliar);
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return programaciones;
    }

    public Agenda getReserva(String fecha, String hora, int recurso, String responsable) {
        Agenda reserva = null;
        try {
            Connection connection = this.conexion.getConnection();
            PreparedStatement ps = connection.prepareStatement(BDConexion.getStatement("obtenerReserva"));
            ps.setInt(1, recurso);
            ps.setString(2, fecha);
            ps.setString(3, hora);

            ResultSet datosReserva = ps.executeQuery();
            ManejoFechas manejoFechas = new ManejoFechas();
            if (datosReserva.next()) {
                reserva = new Agenda();
                reserva.setHora(Integer.toString(datosReserva.getInt("DSHORA")));
                reserva.setNombrePersona(datosReserva.getString("DSNOMBRE"));
                reserva.setMotivo(datosReserva.getString("DSMOTIVO"));
                reserva.setCodigo(datosReserva.getInt("NMCODIGO"));
                reserva.setIdReserva(datosReserva.getString("DSIDRESERVA"));
                reserva.setUbicacion(datosReserva.getString("DSUBICACION"));
                Date d = datosReserva.getDate("FEFECHA");
                String fechaDate = d.toString();
                reserva.setFecha(manejoFechas.establecerFecha(fechaDate));
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return reserva;
    }

    public Agenda getReserva2(String fecha, String hora, int recurso, String responsable) {
        Agenda reserva = null;
        try {
            Connection connection = this.conexion.getConnection();
            PreparedStatement ps = connection.prepareStatement(BDConexion.getStatement("obtenerReserva2"));
            ps.setInt(1, recurso);
            ps.setString(2, fecha);
            ps.setString(3, hora);
            ps.setString(4, responsable);
            ResultSet datosReserva = ps.executeQuery();
            ManejoFechas manejoFechas = new ManejoFechas();
            if (datosReserva.next()) {
                reserva = new Agenda();
                reserva.setHora(Integer.toString(datosReserva.getInt("DSHORA")));
                reserva.setNombrePersona(datosReserva.getString("DSNOMBRE"));
                reserva.setMotivo(datosReserva.getString("DSMOTIVO"));
                reserva.setCodigo(datosReserva.getInt("NMCODIGO"));
                reserva.setIdReserva(datosReserva.getString("DSIDRESERVA"));
                reserva.setUbicacion(datosReserva.getString("DSUBICACION"));
                Date d = datosReserva.getDate("FEFECHA");
                String fechaDate = d.toString();
                reserva.setFecha(manejoFechas.establecerFecha(fechaDate));
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return reserva;
    }

    public Agenda obtenerReserva(String fecha, String hora, int recurso, String responsable) {
        Agenda reserva = null;
        try {
            Connection connection = this.conexion.getConnection();
            PreparedStatement ps = connection.prepareStatement(BDConexion.getStatement("obtenerReservaUsuario"));
            ps.setString(1, fecha);
            ps.setString(2, hora);
            ps.setInt(3, recurso);
            ps.setString(4, responsable);
            ResultSet agenda = ps.executeQuery();
            ManejoFechas manejoFechas = new ManejoFechas();
            if (agenda.next()) {
                reserva = new Agenda();
                reserva.setHora(Integer.toString(agenda.getInt("DSHORA")));
                reserva.setNombrePersona(agenda.getString("DSNOMBRE"));
                reserva.setMotivo(agenda.getString("DSMOTIVO"));
                reserva.setUbicacion(agenda.getString("DSUBICACION"));
                reserva.setCodigo(agenda.getInt("NMCODIGO"));
                reserva.setIdReserva(agenda.getString("DSIDRESERVA"));
                Date d = agenda.getDate("FEFECHA");
                String fechaDate = d.toString();
                reserva.setFecha(manejoFechas.establecerFecha(fechaDate));
            }
            ps.close();
        } catch (SQLException ex) {
            reserva = null;
            ex.printStackTrace();
        }
        return reserva;
    }

    public void insertarProgramacion(String id, String fecha, String hora, String responsable, String tipoReserva, String ubicacion, String motivo, int recurso, String solicitante, String accion, String estado) {
        try {
            Connection connection = this.conexion.getConnection();
            PreparedStatement ps = connection.prepareStatement(BDConexion.getStatement("insertarProgramacion"));
            ps.setString(1, id);
            ps.setString(2, fecha);
            ps.setString(3, hora);
            ps.setString(4, responsable);
            ps.setString(5, tipoReserva);
            ps.setString(6, ubicacion);
            ps.setString(7, motivo);
            ps.setInt(8, recurso);
            ps.setString(9, solicitante);
            ps.setString(10, accion);
            ps.setString(11, estado);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void eliminarProgramacionPendiente(String fecha, String hora, int recurso, String responsable) {
        try {
            Connection connection = this.conexion.getConnection();
            PreparedStatement ps = connection.prepareStatement(BDConexion.getStatement("eliminarProgramacion1"));
            ps.setString(1, fecha);
            ps.setString(2, hora);
            ps.setInt(3, recurso);
            ps.setString(4, responsable);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void actualizarEstadoReserva(String estado, String fecha, String hora, int recurso, String responsable) {
        try {
            Connection connection = this.conexion.getConnection();
            PreparedStatement ps = connection.prepareStatement(BDConexion.getStatement("actualizarEstadoReserva"));
            ps.setString(1, estado);
            ps.setString(2, fecha);
            ps.setString(3, hora);
            ps.setInt(4, recurso);
            ps.setString(5, responsable);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public boolean reservo(String cedula, String fecha) {
        int dato = 0;
        try {
            Connection connection = this.conexion.getConnection();
            PreparedStatement ps = connection.prepareStatement(BDConexion.getStatement("numeroRecursos"));
            ps.setString(1, cedula);
            ps.setString(2, fecha);
            ResultSet agenda = ps.executeQuery();
            if (agenda.next()) {
                dato = agenda.getInt("recursos");
            }
            ps.close();
        } catch (Exception e) {
            System.out.println(e.toString() + "error al conectar reserva");
        }
        if (dato > 0) {
            return true;
        }
        return false;
    }
}