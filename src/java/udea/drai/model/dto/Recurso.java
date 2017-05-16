package udea.drai.model.dto;

import java.util.Date;

public class Recurso {

    private int idRecurso;
    private String nombre;
    private int tipo;
    private String ubicacion;
    private int responsable;
    private String estado;
    private String uso;
    private Date fechaCompra;
    private Date vencimientoGarantia;
    private String proveedor;
    private int incluidoEn;
    private String tipoRelacion;
    private int accionInicial;
    private int accionFinal;
    private String clasificacion;
    private String ip;
    private String mac;
    private int inventario;
    private String caracteristicas;

    public int getIdRecurso() {
        return this.idRecurso;
    }

    public void setIdRecurso(int idRecurso) {
        this.idRecurso = idRecurso;
    }

    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMac() {
        return this.mac;
    }

    public void setMac(String ma) {
        this.mac = ma;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTipo() {
        return this.tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getUbicacion() {
        return this.ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getResponsable() {
        return this.responsable;
    }

    public void setResponsable(int responsable) {
        this.responsable = responsable;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUso() {
        return this.uso;
    }

    public void setUso(String uso) {
        this.uso = uso;
    }

    public Date getFechaCompra() {
        return this.fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Date getVencimientoGarantia() {
        return this.vencimientoGarantia;
    }

    public void setVencimientoGarantia(Date vencimientoGarantia) {
        this.vencimientoGarantia = vencimientoGarantia;
    }

    public String getProveedor() {
        return this.proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public int getIncluidoEn() {
        return this.incluidoEn;
    }

    public void setIncluidoEn(int incluidoEn) {
        this.incluidoEn = incluidoEn;
    }

    public String getTipoRelacion() {
        return this.tipoRelacion;
    }

    public void setTipoRelacion(String tipoRelacion) {
        this.tipoRelacion = tipoRelacion;
    }

    public int getAccionInicial() {
        return this.accionInicial;
    }

    public void setAccionInicial(int accionInicial) {
        this.accionInicial = accionInicial;
    }

    public int getAccionFinal() {
        return this.accionFinal;
    }

    public void setAccionFinal(int accionFinal) {
        this.accionFinal = accionFinal;
    }

    public String getClasificacion() {
        return this.clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public int getInventario() {
        return this.inventario;
    }

    public void setInventario(int inventario) {
        this.inventario = inventario;
    }

    public String getCaracteristicas() {
        return this.caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }
}