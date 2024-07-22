package estadoReserva.domain.entity;

public class EstadoReserva {
    private Long id_estadoReserva;
    private String nombre_estado;
    public EstadoReserva() {
    }
    public EstadoReserva(Long id_estadoReserva, String nombre_estado) {
        this.id_estadoReserva = id_estadoReserva;
        this.nombre_estado = nombre_estado;
    }
    public Long getId_estadoReserva() {
        return id_estadoReserva;
    }
    public void setId_estadoReserva(Long id_estadoReserva) {
        this.id_estadoReserva = id_estadoReserva;
    }
    public String getNombre_estado() {
        return nombre_estado;
    }
    public void setNombre_estado(String nombre_estado) {
        this.nombre_estado = nombre_estado;
    }
    
}
