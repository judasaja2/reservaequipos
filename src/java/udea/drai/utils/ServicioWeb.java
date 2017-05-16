package udea.drai.utils;

import co.edu.udea.exception.OrgSistemasSecurityException;
import co.edu.udea.wsClient.OrgSistemasWebServiceClient;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import udea.drai.model.dto.Estudiante;
import udea.drai.model.dto.Semestre;
import udea.drai.model.dto.Usuario;

/**
 *
 * @author Andres Felipe Arrubla
 */
public class ServicioWeb {
    
    private static final String TOKEN = "5facbdd992ecd3e667df2b544e22a80a8274fd59";
    private static final String INFO_PERSONA = "consultapersonamares";
    private static final String INFO_ACADEMICA = "consultainformacionacademicamares";
    
    
    private static OrgSistemasWebServiceClient wsClient = null;
    
    /**
     * Metodo que retorna la información personal de un estudiante.
     * @param cedula del estudiante a consultar
     * @return 
     */
    public static Usuario obtenerDatosPersonalesEstudiante(String cedula){
       Usuario estudiante = null;
        try {
            estudiante = new Usuario();
            wsClient = new OrgSistemasWebServiceClient();
            wsClient.addParam("cedula", cedula);
            //Se obtienen los datos personales del estudiante
            for(Estudiante est : wsClient.obtenerBean(INFO_PERSONA, TOKEN, Estudiante.class)){
                estudiante.asignaDocumento(cedula);
                estudiante.asignaNombre(est.getNombre());
                estudiante.asignaTipoUsuario("ESTUDIANTE");
//                estudiante.asignaPrograma("504");
                estudiante.asignaCorreo((String) est.getEmail());   
            }
            //Se obtiene el programa del estudiante
            List<Semestre> semestre = wsClient.obtenerBean(INFO_ACADEMICA, TOKEN, Semestre.class);
            for(int i = 0; i < semestre.size(); i ++){
                if(("MATRIC").equals(semestre.get(i).getTipoHomologacion())){
//                estudiante.asignaPrograma(""+semestre.get(0).getPrograma());
                    estudiante.asignaPrograma(String.valueOf(semestre.get(i).getPrograma()));     
                    i = semestre.size() + 1;
                }
            }
//            int j = 0;
//            while(!("MATRIC").equals(semestre.get(j).getTipoHomologacion())){
//                j++;
//            }
//            estudiante.asignaPrograma(String.valueOf(semestre.get(j).getPrograma()));  
            
        } catch (OrgSistemasSecurityException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return estudiante;
    }
    
    /**
     * Metodo que retornda datos quemados de un estudiante para pruebas
     * @param cedula
     * @return 
     */
    public static Usuario obtenerInfoPrueba(String cedula){
        Usuario estudiante = new Usuario();
        estudiante.asignaDocumento(cedula);
        estudiante.asignaNombre("Ruben Dario");
        estudiante.asignaTipoUsuario("ESTUDIANTE");
        estudiante.asignaPrograma("504");
        estudiante.asignaCorreo("ax.fx.ax@gmail.com");
        return estudiante;
    }
    
}
