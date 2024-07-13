package permisosusuarios.domain.entity;

public class PermisosUsuarios {
    private int id_permisosUsuarios;
    private String nombre_permiso;
    private String descripcion_permiso;

    /*Constructors */
    public PermisosUsuarios() {
    }

    public PermisosUsuarios(int id_permisosUsuarios, String nombre_permiso, String descripcion_permiso) {
        this.id_permisosUsuarios = id_permisosUsuarios;
        this.nombre_permiso = nombre_permiso;
        this.descripcion_permiso = descripcion_permiso;
    }
    
    /*Getters and Setters */
    public int getId_permisosUsuarios() {
        return id_permisosUsuarios;
    }

    public void setId_permisosUsuarios(int id_permisosUsuarios) {
        this.id_permisosUsuarios = id_permisosUsuarios;
    }

    public String getNombre_permiso() {
        return nombre_permiso;
    }

    public void setNombre_permiso(String nombre_permiso) {
        this.nombre_permiso = nombre_permiso;
    }

    public String getDescripcion_permiso() {
        return descripcion_permiso;
    }

    public void setDescripcion_permiso(String descripcion_permiso) {
        this.descripcion_permiso = descripcion_permiso;
    }
    
}
