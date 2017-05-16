package udea.drai.model.dto;

/**
 * 
 * @author Andres Felipe Arrubla Z. ax.fx.ax@gmail.com 
 */
public class Semestre {

    private Long semestre;
    private Long programa;
    private String inicioSemestre;
    private String finSemestre;
    private Double promedioSemestre;
    private Double promedioPrograma;
    private Double promedioUniversidad;
    private String tercioProgramaNivel;
    private Integer creditosAcumulados;
    private Integer creditosfaltantes;
    private Integer numeroAprobadas;
    private Integer numeroPerdidas;
    private Integer numeroCanceladas;
    private String tipoHomologacion;
    private String nombrePrograma;

    public String getNombrePrograma() {
        return nombrePrograma;
    }

    public void setNombrePrograma(String nombrePrograma) {
        this.nombrePrograma = nombrePrograma;
    }

    public Integer getCreditosAcumulados() {
        return creditosAcumulados;
    }

    public void setCreditosAcumulados(Integer creditosAcumulados) {
        this.creditosAcumulados = creditosAcumulados;
    }

   
    public String getFinSemestre() {
        return finSemestre;
    }

    public void setFinSemestre(String finSemestre) {
        this.finSemestre = finSemestre;
    }

    public String getInicioSemestre() {
        return inicioSemestre;
    }

    public void setInicioSemestre(String inicioSemestre) {
        this.inicioSemestre = inicioSemestre;
    }

    public Integer getCreditosfaltantes() {
        return creditosfaltantes;
    }

    public void setCreditosfaltantes(Integer creditosfaltantes) {
        this.creditosfaltantes = creditosfaltantes;
    }

    public Integer getNumeroAprobadas() {
        return numeroAprobadas;
    }

    public void setNumeroAprobadas(Integer numeroAprobadas) {
        this.numeroAprobadas = numeroAprobadas;
    }

    public Integer getNumeroCanceladas() {
        return numeroCanceladas;
    }

    public void setNumeroCanceladas(Integer numeroCanceladas) {
        this.numeroCanceladas = numeroCanceladas;
    }

    public Integer getNumeroPerdidas() {
        return numeroPerdidas;
    }

    public void setNumeroPerdidas(Integer numeroPerdidas) {
        this.numeroPerdidas = numeroPerdidas;
    }

    public Long getPrograma() {
        return programa;
    }

    public void setPrograma(Long programa) {
        this.programa = programa;
    }

    public Double getPromedioPrograma() {
        return promedioPrograma;
    }

    public void setPromedioPrograma(Double promedioPrograma) {
        this.promedioPrograma = promedioPrograma;
    }

    public Double getPromedioSemestre() {
        return promedioSemestre;
    }

    public void setPromedioSemestre(Double promedioSemestre) {
        this.promedioSemestre = promedioSemestre;
    }

    public Double getPromedioUniversidad() {
        return promedioUniversidad;
    }

    public void setPromedioUniversidad(Double promedioUniversidad) {
        this.promedioUniversidad = promedioUniversidad;
    }

    public Long getSemestre() {
        return semestre;
    }

    public void setSemestre(Long semestre) {
        this.semestre = semestre;
    }

    public String getTercioProgramaNivel() {
        return tercioProgramaNivel;
    }

    public void setTercioProgramaNivel(String tercioProgramaNivel) {
        this.tercioProgramaNivel = tercioProgramaNivel;
    }

    public String getTipoHomologacion() {
        return tipoHomologacion;
    }

    public void setTipoHomologacion(String tipoHomologacion) {
        this.tipoHomologacion = tipoHomologacion;
    }
           
}
