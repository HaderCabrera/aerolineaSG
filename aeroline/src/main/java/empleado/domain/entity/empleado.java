package empleado.domain.entity;

public class empleado {
    private String id_empleado;
    private String nombre1;
    private String nombre2;
    private String apellidos;
    private int id_tripulacionRoles;
    private int id_aerolineas;
    public empleado() {
    }
    public empleado(String id_empleado, String nombre1, String nombre2, String apellidos, int id_tripulacionRoles,
            int id_aerolineas) {
        this.id_empleado = id_empleado;
        this.nombre1 = nombre1;
        this.nombre2 = nombre2;
        this.apellidos = apellidos;
        this.id_tripulacionRoles = id_tripulacionRoles;
        this.id_aerolineas = id_aerolineas;
    }
    public String getId_empleado() {
        return id_empleado;
    }
    public void setId_empleado(String id_empleado) {
        this.id_empleado = id_empleado;
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
    public int getId_tripulacionRoles() {
        return id_tripulacionRoles;
    }
    public void setId_tripulacionRoles(int id_tripulacionRoles) {
        this.id_tripulacionRoles = id_tripulacionRoles;
    }
    public int getId_aerolineas() {
        return id_aerolineas;
    }
    public void setId_aerolineas(int id_aerolineas) {
        this.id_aerolineas = id_aerolineas;
    }

    
}
