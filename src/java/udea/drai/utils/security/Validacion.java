package udea.drai.utils.security;
 
import co.edu.udea.UtilidadesAutenticacion_wsdl.UtilidadesAutenticacionPortTypeProxy;
import java.rmi.RemoteException;

    public class Validacion{
        
        private static UtilidadesAutenticacionPortTypeProxy util = new UtilidadesAutenticacionPortTypeProxy();
        private static String respuesta;
        
        public static String authUser(String username, String password, Boolean vsportal){           
            respuesta = "";
            try {
                respuesta = util.autenticaLogin(username, password);
                System.out.println(respuesta);
            } catch (RemoteException e) {
                    e.printStackTrace();
            }
            return respuesta;
        }
        
        public static String validarPrueba(String username, String password, Boolean vsportal){
            return "1152439736";
        }
    }
