/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package udea.drai.utils;

import java.util.ResourceBundle;

/**
 *
 * @author Andres Felipe Arrubla
 */
public class LeerPropiedades {
    
    private final static String PROPERTIES = "udea.drai.utils.properties.propiedadesBase";
    private final static String MENSAJES = "udea.drai.utils.MensajeError";
    public final static String CONEXION_URL = "conexion.url";
    public final static String CONEXION_USER = "UsuarioBaseDeDatos";
    public final static String CONEXION_PASS = "ContrasenaBaseDeDatos";
    public final static String ERROR001 = "ERROR001";
    ResourceBundle prop = null;

    public LeerPropiedades() {
        try {
            prop = ResourceBundle.getBundle(PROPERTIES);
        } catch (Exception e) {
        }
    }          

    public String getValor(String clave) {
        return prop.getString(clave);
    }      
}
