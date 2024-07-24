package tripulacion.domain.entity;

import java.util.ArrayList;
import java.util.List;

import empleado.domain.entity.Empleado;
import tripulacionRol.domain.entity.tripulacionRol;

public class Tripulacion {
    private  long id_tripulacion;
    private  String  id_empelado;
    private long id_vuelo;
    private String numero_vuelo;
    private tripulacionRol rol;
    private  String nombre_empleado;
    List<Empleado> lstEmpleado = new ArrayList<>();

    public Tripulacion() {
    }

    
    public Tripulacion(Long id_tripulacion, String id_empelado, long id_vuelo, String numero_vuelo, tripulacionRol rol,
            String nombre_empleado, List<Empleado> lstEmpleado) {
        this.id_tripulacion = id_tripulacion;
        this.id_empelado = id_empelado;
        this.id_vuelo = id_vuelo;
        this.numero_vuelo = numero_vuelo;
        this.rol = rol;
        this.nombre_empleado = nombre_empleado;
        this.lstEmpleado = lstEmpleado;
    }


    public String getId_empelado() {
        return id_empelado;
    }

    public void setId_empelado(String id_empelado) {
        this.id_empelado = id_empelado;
    }

    public long getId_vuelo() {
        return id_vuelo;
    }

    public void setId_vuelo(long id_vuelo) {
        this.id_vuelo = id_vuelo;
    }

    public List<Empleado> getLstEmpleado() {
        return lstEmpleado;
    }

    public void setLstEmpleado(List<Empleado> lstEmpleado) {
        this.lstEmpleado = lstEmpleado;
    }

    public long getId_tripulacion() {
        return id_tripulacion;
    }


    public void setId_tripulacion(long id_tripulacion) {
        this.id_tripulacion = id_tripulacion;
    }

    public String getNumero_vuelo() {
        return numero_vuelo;
    }

    public void setNumero_vuelo(String numero_vuelo) {
        this.numero_vuelo = numero_vuelo;
    }

    public tripulacionRol getRol() {
        return rol;
    }

    public void setRol(tripulacionRol rol) {
        this.rol = rol;
    }


    public String getNombre_empleado() {
        return nombre_empleado;
    }


    public void setNombre_empleado(String nombre_empleado) {
        this.nombre_empleado = nombre_empleado;
    }

    
}
