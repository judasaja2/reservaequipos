  package udea.drai.model.dto;
  
  import java.io.Serializable;
  
  public class Reserva
    implements Serializable
  {
    private String fechaIni;
    private String fechaFin;
    private String horaIni;
    private String horaFin;
    private String motivo;
    private String ubicacion;
    private String responsable;
    private int idReserva;
    private int dias;
  
    public String getFechaIni()
    {
      return this.fechaIni;
    }
  
    public void setFechaIni(String fechaIni) {
      this.fechaIni = fechaIni;
    }
  
    public String getFechaFin() {
      return this.fechaFin;
    }
  
    public void setFechaFin(String fechaFin) {
      this.fechaFin = fechaFin;
    }
  
    public String getHoraIni() {
      return this.horaIni;
    }
  
    public void setHoraIni(String horaIni) {
      this.horaIni = horaIni;
    }
  
    public String getHoraFin() {
      return this.horaFin;
    }
  
    public void setHoraFin(String horaFin) {
      this.horaFin = horaFin;
    }
  
    public String getMotivo() {
      return this.motivo;
    }
  
    public void setMotivo(String motivo) {
      this.motivo = motivo;
    }
  
    public String getResponsable() {
      return this.responsable;
    }
  
    public void setResponsable(String responsable) {
      this.responsable = responsable;
    }
  
    public int getIdReserva() {
      return this.idReserva;
    }
  
    public void setIdReserva(int idReserva) {
      this.idReserva = idReserva;
    }
  
    public String getUbicacion() {
      return this.ubicacion;
    }
  
    public void setUbicacion(String ubicacion) {
      this.ubicacion = ubicacion;
    }
    public String getDias() {
      return this.ubicacion;
    }
  
    public void setDias(String dia) {
      this.ubicacion = dia;
    }
  }
