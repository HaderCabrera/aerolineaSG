package ciudad.domain.entity;

public class Ciudad {
    private String id_ciudad;
    private String nombre;
    private String id_pais;
    public Ciudad() {
    }
    public Ciudad(String id_ciudad, String nombre, String id_pais) {
        this.id_ciudad = id_ciudad;
        this.nombre = nombre;
        this.id_pais = id_pais;
    }
    public String getId_ciudad() {
        return id_ciudad;
    }
    public void setId_ciudad(String id_ciudad) {
        this.id_ciudad = id_ciudad;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getId_pais() {
        return id_pais;
    }
    public void setId_pais(String id_pais) {
        this.id_pais = id_pais;
    }
    
}
