package cliente.domain.entity;

public class Cliente {
    private Long id_cliente;
    private Long documento;
    private String nombre1;
    private String nombre2;
    private String apellidos;
    private String fecha_nacimiento;
    private String email;
    private Long id_tipo_documento;
    
    public Cliente() {
    }
    public Cliente(Long id_cliente, Long documento, String nombre1, String nombre2, String apellidos,
            String fecha_nacimiento, String email, Long id_tipo_documento) {
        this.id_cliente = id_cliente;
        this.documento = documento;
        this.nombre1 = nombre1;
        this.nombre2 = nombre2;
        this.apellidos = apellidos;
        this.fecha_nacimiento = fecha_nacimiento;
        this.email = email;
        this.id_tipo_documento = id_tipo_documento;
    }
    public Long getId_cliente() {
        return id_cliente;
    }
    public void setId_cliente(Long id_cliente) {
        this.id_cliente = id_cliente;
    }
    public Long getDocumento() {
        return documento;
    }
    public void setDocumento(Long documento) {
        this.documento = documento;
    }
    public String getNombre1() {
        return nombre1;
    }
    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }
    public String getNombre2() {
        return nombre2;
    }
    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }
    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }
    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Long getId_tipo_documento() {
        return id_tipo_documento;
    }
    public void setId_tipo_documento(Long id_tipo_documento) {
        this.id_tipo_documento = id_tipo_documento;
    }

    
    
}
