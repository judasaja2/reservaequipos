package udea.drai.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;
import udea.drai.model.dao.ReservaDAO;
import udea.drai.model.dao.ds.BDConexion;
import udea.drai.model.dto.Reserva;

public class ReservaDAOJdbc implements ReservaDAO{

    private BDConexion conexion;

    public ReservaDAOJdbc() {
        this.conexion = new BDConexion(false);
    }

    public void cerrarConexion() {
        this.conexion.closeConnection();
    }

    public void insertarReserva(String responsable, String aleatorio, String fechaIni, String fechaFin, String horaIni, String horaFin, int recurso, String dias, String estado) {
        try {
            Connection connection = this.conexion.getConnection();
            PreparedStatement ps = connection.prepareStatement(BDConexion.getStatement("insertarReserva"));
            ps.setString(1, responsable);
            ps.setString(2, aleatorio);
            ps.setString(3, fechaIni);
            ps.setString(4, fechaFin);
            ps.setString(5, horaIni);
            ps.setString(6, horaFin);
            ps.setInt(7, recurso);
            ps.setString(8, dias);
            ps.setString(9, estado);

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public int obtenerConsecutivo(String responsable, String aleatorio) {
        int consecutivoReserva = 0;
        try {
            Connection connection = this.conexion.getConnection();
            PreparedStatement ps = connection.prepareStatement(BDConexion.getStatement("obtenerConsecutivo"));
            ps.setString(1, responsable);
            ps.setString(2, aleatorio);
            ResultSet consecutivo = ps.executeQuery();
            while (consecutivo.next()) {
                consecutivoReserva = consecutivo.getInt("NMRESERVA");
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return consecutivoReserva;
    }

    public Reserva getReserva(int idReserva) {
        Reserva r = null;
        try {
            Connection connection = this.conexion.getConnection();
            PreparedStatement ps = connection.prepareStatement(BDConexion.getStatement("obtenerDatosReserva"));
            ps.setInt(1, idReserva);
            ResultSet reserva = ps.executeQuery();

            while (reserva.next()) {
                Date fechaI = reserva.getDate("FEFECHAINI");
                r = new Reserva();
                r.setFechaIni(String.valueOf(fechaI));
                Date fechaF = reserva.getDate("FEFECHAFIN");
                r.setFechaFin(String.valueOf(fechaF));
                r.setHoraIni(reserva.getString("DSHORAINI"));
                r.setHoraFin(reserva.getString("DSHORAFIN"));
                r.setIdReserva(reserva.getInt("NMRESERVA"));
                r.setDias(reserva.getString("DSDIAS"));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return r;
    }

    public Vector<Reserva> getReservaUsuario(int documento) {
        Vector r = null;
        try {
            Connection connection = this.conexion.getConnection();
            PreparedStatement ps = connection.prepareStatement(BDConexion.getStatement("obtenerReservasDeUsusario"));
            ps.setInt(1, documento);
            ResultSet reserva = ps.executeQuery();
            r = new Vector();
            while (reserva.next()) {
                Reserva aux = new Reserva();
                aux.setIdReserva(reserva.getInt("nmreserva"));
                Date fechaI = reserva.getDate("fefechaini");
                aux.setFechaIni(String.valueOf(fechaI));
                aux.setHoraIni(reserva.getString("dshoraini"));
                aux.setHoraFin(reserva.getString("dshorafin"));
                aux.setDias(reserva.getString("dsdias"));
                r.addElement(aux);
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return r;
    }

    public void eliminarReserva(int codigoReserva) {
        try {
            Connection connection = this.conexion.getConnection();
            PreparedStatement ps = connection.prepareStatement(BDConexion.getStatement("eliminarReserva"));
            ps.setInt(1, codigoReserva);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public int eliminarActividadesReserva(String idReserva, String fechaInicial, String fechaFinal, int horaInicial, int horaFinal) {
        Connection c = this.conexion.getConnection();
        int rows = -1;
        try {
            PreparedStatement ps = c.prepareStatement(BDConexion.getStatement("eliminarActividadesReserva"));

            ps.setString(1, idReserva);
            ps.setString(2, fechaInicial);
            ps.setString(3, fechaFinal);
            ps.setInt(4, horaInicial);
            ps.setInt(5, horaFinal);

            rows = ps.executeUpdate();

            ps.close();

            ps = c.prepareStatement(BDConexion.getStatement("insertarActividadReservaCancelada"));

            ps.setString(1, idReserva);
            ps.setString(2, fechaInicial);
            ps.setString(3, fechaFinal);
            ps.setInt(4, horaInicial);
            ps.setInt(5, horaFinal);

            ps.executeUpdate();

            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rows;
    }
}
