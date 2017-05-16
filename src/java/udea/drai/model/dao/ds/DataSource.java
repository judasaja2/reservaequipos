/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package udea.drai.model.dao.ds;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import udea.drai.utils.LeerPropiedades;

/**
 *
 * @author Andres Felipe Arrubla
 */

public class DataSource {
	
    private static DataSource ds = null;

    private DataSource() {
    }
	
    public static synchronized DataSource getInstance() {
        if (ds == null) {
            ds = new DataSource();
        }
        return ds;
    }

    public Connection getConnection() {
        Connection con = null;
        LeerPropiedades prop = new LeerPropiedades();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(prop.getValor(LeerPropiedades.CONEXION_URL),
                    prop.getValor(LeerPropiedades.CONEXION_USER),
                    prop.getValor(LeerPropiedades.CONEXION_PASS));
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        } 

        return con;
    }

}
