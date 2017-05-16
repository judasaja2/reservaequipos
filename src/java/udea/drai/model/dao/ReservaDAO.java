package udea.drai.model.dao;

import java.util.List;
import udea.drai.model.dto.Reserva;

/**
 *
 * @author Andres Felipe Arrubla Z.
 */
public interface ReservaDAO {

    public void insertarReserva(String responsable, String aleatorio,
            String fechaIni, String fechaFin, String horaIni,
            String horaFin, int recurso, String dias, String estado);

    public int obtenerConsecutivo(String responsable, String aleatorio);

    public Reserva getReserva(int idReserva);

    public List<Reserva> getReservaUsuario(int documento);

    public void eliminarReserva(int codigoReserva);

    public int eliminarActividadesReserva(String idReserva, String fechaInicial,
            String fechaFinal, int horaInicial, int horaFinal);
}
