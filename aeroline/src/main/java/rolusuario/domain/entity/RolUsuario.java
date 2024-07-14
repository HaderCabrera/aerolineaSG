package rolusuario.domain.entity;

import java.util.ArrayList;
import java.util.List;

public class RolUsuario {
    private int id_rolUsuario;
    private String nombre_rol;
    private List<String> lsPermisos;
    
    /*COntructors */
    public RolUsuario() {
        lsPermisos = new ArrayList<String>();
    }  

    public RolUsuario(int id_rolUsuario, String nombre_rol, List<String> lsPermisos) {
        this.id_rolUsuario = id_rolUsuario;
        this.nombre_rol = nombre_rol;
        this.lsPermisos = lsPermisos;
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

    public List<String> getLsPermisos() {
        return lsPermisos;
    }

    public void setLsPermisos(List<String> lsPermisos) {
        this.lsPermisos = lsPermisos;
    }

}
