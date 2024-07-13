package rolusuario.domain.entity;

public class RolUsuario {
    private int id_rolUsuario;
    private String nombre_rol;
    
    /*COntructors */
    public RolUsuario() {
    }

    public RolUsuario(int id_rolUsuario, String nombre_rol) {
        this.id_rolUsuario = id_rolUsuario;
        this.nombre_rol = nombre_rol;
    }
    
    /*Getters and Setters */
    public int getId_rolUsuario() {
        return id_rolUsuario;
    }

    public void setId_rolUsuario(int id_rolUsuario) {
        this.id_rolUsuario = id_rolUsuario;
    }

    public String getNombre_rol() {
        return nombre_rol;
    }

    public void setNombre_rol(String nombre_rol) {
        this.nombre_rol = nombre_rol;
    }
    
}
