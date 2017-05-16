package udea.drai.model.dto;

import java.util.Enumeration;
import java.util.Vector;

public class DefaultReserva extends Reserva {

    private Vector<Agenda> actividad;
    private String estado;
    private Recurso recurso;
    public static final String PENDIENTE = "P";
    public static final String APROBADA = "A";

    public DefaultReserva() {
        this.actividad = new Vector();
    }

    public void addActivity(Agenda agenda) {
        this.actividad.add(agenda);
    }

    public Enumeration<Agenda> getActivities() {
        return this.actividad.elements();
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean isPuntual() {
        return getFechaIni().equals(getFechaFin());
    }

    public Recurso getRecurso() {
        return this.recurso;
    }

    public void setRecurso(Recurso recurso) {
        this.recurso = recurso;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        String salto = "\n";

        sb.append("Recurso: ");
        sb.append(getRecurso().getIdRecurso());
        sb.append(salto);
        sb.append("IdReserva: ");
        sb.append(getIdReserva());
        sb.append(salto);
        sb.append("Fecha Inicio: ");
        sb.append(getFechaIni());
        sb.append(salto);
        sb.append("Fecha Final: ");
        sb.append(getFechaFin());
        sb.append(salto);
        sb.append("Hora inicial: ");
        sb.append(getHoraIni());
        sb.append(salto);
        sb.append("Hora Final: ");
        sb.append(getHoraFin());
        sb.append(salto);

        boolean b = isPuntual();

        sb.append("es puntual?: ");
        sb.append(b);
        sb.append(salto);

        if (!b) {
            sb.append("Dias: ");
            sb.append(getDias());
            sb.append(salto);
        }

        sb.append("Motivo: ");
        sb.append(getMotivo());
        sb.append(salto);
        sb.append("Responsable: ");
        sb.append(getResponsable());
        sb.append(salto);
        sb.append("Estado: ");
        sb.append(getEstado());
        sb.append(salto);

        return new String(sb);
    }
}