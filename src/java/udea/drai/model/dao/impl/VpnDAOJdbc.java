package udea.drai.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import udea.drai.model.dao.VPNDAO;
import udea.drai.model.dao.ds.BDConexion;
import udea.drai.model.dao.ds.DataSource;

public class VpnDAOJdbc implements VPNDAO{

    private BDConexion conexion;
    private Connection connection;

    public VpnDAOJdbc() {
        this.conexion = new BDConexion(true);
    }

    public void cerrarConexion() {
        this.conexion.closeConnection();
    }

    public Vector retornaSoftware(String fecha, String hora) {
        Vector sw = new Vector(10, 5);
        String aux = fecha + " " + hora + ":00";
        aux = validarFecha(aux);
        try {
            Connection connection = this.conexion.getConnection();
            PreparedStatement ps = connection.prepareStatement(BDConexion.getStatement("retornacodigo"));
            ps.setString(1, aux);
            ResultSet dato = ps.executeQuery();
            while (dato.next()) {
                sw.add(dato.getString("code"));
            }
            dato.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return sw;
    }
    
    public Vector retornaSoftware2(String fecha, String hora){
        Vector sw = new Vector(10, 5);
        String aux = fecha + " " + hora + ":00";
        aux = validarFecha(aux);   
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;        
        try{
            connection = DataSource.getInstance().getConnection();
            ps = connection.prepareStatement(BDConexion.getStatement("retornacodigo"));
            ps.setString(1, aux);
            rs = ps.executeQuery();
            while(rs.next()){
                sw.add(rs.getString("code"));                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
        return sw;
    }

    public void insertaUsuario(String usuario, String nombre, String contraseña, String programa, String fechaIni, String fechaFin, int recursoReservado, String correo, String ip, String mac) {
        try {
            String ipDisponible = getIpDisponible();
            borrarIpDisponible(ipDisponible);
            Connection connection = this.conexion.getConnection();
            PreparedStatement ps = connection.prepareStatement(BDConexion.getStatement("insertarUsuario"));
            ps.setString(1, usuario);
            ps.setString(2, nombre);
            ps.setString(3, contraseña);
            ps.setString(4, programa);
            ps.setString(7, fechaIni);
            ps.setString(8, fechaFin);
            ps.setString(6, Integer.toString(recursoReservado));
            ps.setString(9, correo);
            ps.setString(10, ip);
            ps.setString(5, mac);
            ps.setString(11, ipDisponible);

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private String validarFecha(String aux) {
        String date = aux;
        for (int i = 0; i < aux.length(); i++) {
            if (aux.substring(i, i + 1) == "/") {
                date = date.substring(0, i) + "-" + date.substring(i + 1, date.length());
            }
        }
        return date;
    }

    public String getIpDisponible() {
        String ip = "";
        try {
            Connection connection = this.conexion.getConnection();
            PreparedStatement ps = connection.prepareStatement(BDConexion.getStatement("getipdispoible"));
            ResultSet dato = ps.executeQuery();
            while (dato.next()) {
                ip = dato.getString("ip");
            }
            dato.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ip;
    }

    public void borrarIpDisponible(String ip) {
        try {
            Connection c = this.conexion.getConnection();
            PreparedStatement ps = c.prepareStatement(BDConexion.getStatement("borraripseleccionada"));
            ps.setString(1, ip);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.err.println(e.getMessage() + "error al borrar1");
        }
    }
}
