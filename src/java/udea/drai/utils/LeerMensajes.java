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
public class LeerMensajes {
    
    private final static String MENSAJES = "udea.drai.utils.properties.MensajeError";
    public final static String ERROR001 = "ERROR001";
    ResourceBundle prop = null;

    public LeerMensajes() {
        try {
            prop = ResourceBundle.getBundle(MENSAJES);
        } catch (Exception e) {
        }
    }          

    public String getValor(String clave) {
        return prop.getString(clave);
    }      
}