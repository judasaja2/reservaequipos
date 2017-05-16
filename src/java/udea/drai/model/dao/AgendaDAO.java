/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package udea.drai.model.dao;

import java.util.List;
import udea.drai.model.dto.Agenda;

/**
 *
 * @author Andres Felipe Arrubla Z.
 */
public interface AgendaDAO {
    
    public List<Agenda> obtenerProgramacionMesobtenerRecursosTipo(String inicio, String fin, String recurso);
    
    
}
