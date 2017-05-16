  package udea.drai.model.dto;
  
  import java.io.Serializable;
  import java.util.Calendar;
  
  public class Agenda
    implements Serializable
  {
    private int codigo;
    private String idReserva;
    private Calendar fecha;
    private String hora;
    private String responsable;
    private String tipo;
    private String ubicacion;
    private String motivo;
    private int recurso;
    private String solicitante;
    private String accion;
    private String nombrePersona;
    private String nombreRecurso;
    private String estado;
  
    public int getCodigo()
    {
      return this.codigo;
    }
  
    public void setCodigo(int codigo)
    {
      this.codigo = codigo;
    }
  
    public String getIdReserva() {
      return this.idReserva;
    }
  
    public void setIdReserva(String idReserva) {
      this.idReserva = idReserva;
    }
  
    public Calendar getFecha() {
      return this.fecha;
    }
  
    public void setFecha(Calendar fecha) {
      this.fecha = fecha;
    }
  
    public String getHora() {
      return this.hora;
    }
  
    public void setHora(String hora) {
      this.hora = hora;
    }
  
    public String getResponsable() {
      return this.responsable;
    }
  
    public void setResponsable(String responsable) {
      this.responsable = responsable;
    }
  
    public String getTipo() {
      return this.tipo;
    }
  
    public void setTipo(String tipo) {
      this.tipo = tipo;
    }
  
    public String getUbicacion() {
      return this.ubicacion;
    }
  
    public void setUbicacion(String ubicacion) {
      this.ubicacion = ubicacion;
    }
  
    public String getMotivo() {
      return this.motivo;
    }
  
    public void setMotivo(String motivo) {
      this.motivo = motivo;
    }
  
    public int getRecurso() {
      return this.recurso;
    }
  
    public void setRecurso(int recurso) {
      this.recurso = recurso;
    }
  
    public String getSolicitante() {
      return this.solicitante;
    }
  
    public void setSolicitante(String solicitante) {
      this.solicitante = solicitante;
    }
  
    public String getAccion() {
      return this.accion;
    }
  
    public void setAccion(String accion) {
      this.accion = accion;
    }
  
    public String getNombrePersona() {
      return this.nombrePersona;
    }
  
    public void setNombrePersona(String nombrePersona) {
      this.nombrePersona = nombrePersona;
    }
  
    public String getNombreRecurso() {
      return this.nombreRecurso;
    }
  
    public void setNombreRecurso(String nombreRecurso) {
      this.nombreRecurso = nombreRecurso;
    }
  
    public String getEstado()
    {
      return this.estado;
    }
  
    public void setEstado(String estado)
    {
      this.estado = estado;
    }
  }
