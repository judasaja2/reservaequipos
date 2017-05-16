package udea.drai.model.dto;

public class Usuario {

    private String nombre;
    private String tipo_usuario;
    private String programa;
    private String correo;
    private String documento;
    private String codigo;

    public void asignaNombre(String nombre) {
        this.nombre = nombre;
    }

    public void asignaTipoUsuario(String tipo) {
        this.tipo_usuario = tipo;
    }

    public void asignaPrograma(String programa) {
        this.programa = programa;
    }

    public void asignaCorreo(String correo) {
        this.correo = correo;
    }

    public void asignaDocumento(String documento) {
        this.documento = documento;
    }

    public void asignacodigo(String cod) {
        this.codigo = cod;
    }

    public String retornaCodigo() {
        return this.codigo;
    }

    public String retornaNombre() {
        return this.nombre;
    }

    public String retornaTipoUsuario() {
        return this.tipo_usuario;
    }

    public String retornaPrograma() {
        return this.programa;
    }

    public String retornaCorreo() {
        return this.correo;
    }

    public String retornaDocumento() {
        return this.documento;
    }
}
 
