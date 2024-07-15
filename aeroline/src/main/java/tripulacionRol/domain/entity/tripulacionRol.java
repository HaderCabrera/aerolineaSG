package tripulacionRol.domain.entity;

public class tripulacionRol {
    private int id_tripulacionRoles;
    private String nombre;
    
    public tripulacionRol() {
    }
    public tripulacionRol(int id_tripulacionRoles, String nombre) {
        this.id_tripulacionRoles = id_tripulacionRoles;
        this.nombre = nombre;
    }
    public int getId_tripulacionRoles() {
        return id_tripulacionRoles;
    }
    public void setId_tripulacionRoles(int id_tripulacionRoles) {
        this.id_tripulacionRoles = id_tripulacionRoles;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
