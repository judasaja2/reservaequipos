package udea.drai.model.dao;

import java.util.List;

/**
 *
 * @author Andres Felipe Zapata
 */
public interface VPNDAO {
    
    public List retornaSoftware(String fecha, String hora);

    public List retornaSoftware2(String fecha, String hora);
    
    public void insertaUsuario(String usuario, String nombre, String contraseña,
            String programa, String fechaIni, String fechaFin, int recursoReservado, 
            String correo, String ip, String mac);
    
    public String getIpDisponible();
    
    public void borrarIpDisponible(String ip);
}
