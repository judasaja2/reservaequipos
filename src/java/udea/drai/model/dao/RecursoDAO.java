package udea.drai.model.dao;

import java.util.List;
import udea.drai.model.dto.Recurso;
import udea.drai.model.dto.SoftwareType;

/**
 *
 * @author 
 */
public interface RecursoDAO {
    
    public Recurso getRecurso(int identificador);
    
    public int obtenerDondeEstaIncluido(String recurso);
    
    public List<Recurso> obtenerRecursosIncluidos(String recurso);
    
    public List<SoftwareType> obtenerTiposDeSoftware();
    
    public List getNuevoSW(String string);
    
    public void borrarDatrosConCedula(String string);
    
    public void borrarDatrosConCedula2(String string);
    
    public void IngresarDatosTables();
    
    public void IngresarDatosTables2();
    
    
    
}
