package udea.drai.model.dao.ds;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BDConexion {

    private static final String CONFIGURATION_FILE = "udea.drai.utils.properties.propiedadesBase";
    private static final String STATEMENTS_FILE = "udea.drai.utils.properties.statements";
    private static final ResourceBundle PROPERTIES = ResourceBundle.getBundle(CONFIGURATION_FILE);
    private static final ResourceBundle STATEMENTS = ResourceBundle.getBundle(STATEMENTS_FILE);
    private Connection connection = null;
    private static BDConexion ds = null;
    
//    private BDConexion(){
//    }
    
    public BDConexion(){
        String password;
        String driver;
        String url;
        String user;
        driver = getPropiedad("DriverBaseDeDatos");
        url = getPropiedad("URLConexion") + getPropiedad("NombreBaseDeDatos");
        user = getPropiedad("UsuarioBaseDeDatos");
        password = getPropiedad("ContrasenaBaseDeDatos");
        try {
            Class.forName(driver);
            this.connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BDConexion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BDConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public BDConexion(boolean dec) {
        String password;
        String driver;
        String url;
        String user;
        if (dec) {
            driver = getPropiedad("DriverBaseDeDatos");
            url = getPropiedad("URLConexion2") + getPropiedad("NombreBaseDeDatos2");
            user = getPropiedad("UsuarioBaseDeDatos2");
            password = getPropiedad("ContrasenaBaseDeDatos2");
        } else {
            driver = getPropiedad("DriverBaseDeDatos");
            url = getPropiedad("URLConexion") + getPropiedad("NombreBaseDeDatos");
            user = getPropiedad("UsuarioBaseDeDatos");
            password = getPropiedad("ContrasenaBaseDeDatos");
        }
        try {
            Class.forName(driver);
            this.connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BDConexion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BDConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String getPropiedad(String nombre) {
        String valor = PROPERTIES.getString(nombre);
        return valor;
    }

    public static String getStatement(String nombre) {
        String valor = STATEMENTS.getString(nombre);
        return valor;
    }

    public void closeConnection() {
        try {
            if (this.connection != null) {
                this.connection.close();
            }
        } catch (SQLException sqlException) {
            System.err.println(sqlException);
        }
    }

    public Connection getConnection() {
        return this.connection;
    }
    
//    public Connection getConnection2(){
//        Connection con = null;
//        String password;
//        String driver;
//        String url;
//        String user;
//
//        driver = getPropiedad("DriverBaseDeDatos");
//        url = getPropiedad("URLConexion") + getPropiedad("NombreBaseDeDatos");
//        user = getPropiedad("UsuarioBaseDeDatos");
//        password = getPropiedad("ContrasenaBaseDeDatos");
//        try {
//            Class.forName(driver);
//            this.connection = DriverManager.getConnection(url, user, password);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(BDConexion.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException e) {
//            Logger.getLogger(BDConexion.class.getName()).log(Level.SEVERE, null, e);
//        }           
//        return con;
//    }
    
//    public static BDConexion getInstance(){
//        if (ds == null) {
//            ds = new BDConexion();
//        }
//        return ds;
//    }    
}
