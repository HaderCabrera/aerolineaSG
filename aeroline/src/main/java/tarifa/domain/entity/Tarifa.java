package tarifa.domain.entity;

public class Tarifa {
    private Long id_tarifa;
    private Long id_tipoClase;
    private Double precio_tarifa;
    private String descripcion;
    public Tarifa() {
    }
    public Tarifa(Long id_tarifa, Long id_tipoClase, Double precio_tarifa, String descripcion) {
        this.id_tarifa = id_tarifa;
        this.id_tipoClase = id_tipoClase;
        this.precio_tarifa = precio_tarifa;
        this.descripcion = descripcion;
    }
    public Long getId_tarifa() {
        return id_tarifa;
    }
    public void setId_tarifa(Long id_tarifa) {
        this.id_tarifa = id_tarifa;
    }
    public Long getId_tipoClase() {
        return id_tipoClase;
    }
    public void setId_tipoClase(Long id_tipoClase) {
        this.id_tipoClase = id_tipoClase;
    }
    public Double getPrecio_tarifa() {
        return precio_tarifa;
    }
    public void setPrecio_tarifa(Double precio_tarifa) {
        this.precio_tarifa = precio_tarifa;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
