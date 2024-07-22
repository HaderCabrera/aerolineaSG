package tipoclase.domain.entity;

public class TipoClase {
    private Long id_tipoClase;
    private String nombre_Clase;
    public TipoClase() {
    }
    public TipoClase(Long id_tipoClase, String nombre_Clase) {
        this.id_tipoClase = id_tipoClase;
        this.nombre_Clase = nombre_Clase;
    }
    public Long getId_tipoClase() {
        return id_tipoClase;
    }
    public void setId_tipoClase(Long id_tipoClase) {
        this.id_tipoClase = id_tipoClase;
    }
    public String getNombre_Clase() {
        return nombre_Clase;
    }
    public void setNombre_Clase(String nombre_Clase) {
        this.nombre_Clase = nombre_Clase;
    }
    
}
