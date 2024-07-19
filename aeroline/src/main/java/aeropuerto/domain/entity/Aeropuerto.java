package aeropuerto.domain.entity;

public class Aeropuerto {
    private Long id_aeropuerto;
    private String nombre;
    private String id_ciudad;
    private String Ciudad;
    public Aeropuerto() {
    }
    public Aeropuerto(Long id_aeropuerto, String nombre, String id_ciudad, String ciudad) {
        this.id_aeropuerto = id_aeropuerto;
        this.nombre = nombre;
        this.id_ciudad = id_ciudad;
        Ciudad = ciudad;
    }
    public Long getId_aeropuerto() {
        return id_aeropuerto;
    }
    public void setId_aeropuerto(Long id_aeropuerto) {
        this.id_aeropuerto = id_aeropuerto;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getId_ciudad() {
        return id_ciudad;
    }
    public void setId_ciudad(String id_ciudad) {
        this.id_ciudad = id_ciudad;
    }
    public String getCiudad() {
        return Ciudad;
    }
    public void setCiudad(String ciudad) {
        Ciudad = ciudad;
    }

    
}
