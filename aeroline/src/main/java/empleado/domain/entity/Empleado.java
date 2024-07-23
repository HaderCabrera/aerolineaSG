package empleado.domain.entity;

public class Empleado {
    private String id_empleado;
    private String nombre1;
    private String nombre2;
    private String apellidos;
    private int id_tripulacionRoles;
    private int id_estadoEmpleado;
    

    public Empleado() {
    }

    
    public Empleado(String id_empleado, String nombre1, int id_tripulacionRoles) {
        this.id_empleado = id_empleado;
        this.nombre1 = nombre1;
        this.id_tripulacionRoles = id_tripulacionRoles;
    }


    public Empleado(String id_empleado, String nombre1, String nombre2, String apellidos, int id_tripulacionRoles,
            int id_estadoEmpleado) {
        this.id_empleado = id_empleado;
        this.nombre1 = nombre1;
        this.nombre2 = nombre2;
        this.apellidos = apellidos;
        this.id_tripulacionRoles = id_tripulacionRoles;
        this.id_estadoEmpleado = id_estadoEmpleado;
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


    public int getId_estadoEmpleado() {
        return id_estadoEmpleado;
    }


    public void setId_estadoEmpleado(int id_estadoEmpleado) {
        this.id_estadoEmpleado = id_estadoEmpleado;
    }
    
    
    
}
