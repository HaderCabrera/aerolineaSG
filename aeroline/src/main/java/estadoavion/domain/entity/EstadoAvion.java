package estadoavion.domain.entity;

public class EstadoAvion {
    private Long id_estado;
    private String nombreEstado;

    public EstadoAvion() {
    }
    public EstadoAvion(Long id_estado, String nombreEstado) {
        this.id_estado = id_estado;
        this.nombreEstado = nombreEstado;
    }
    
    public Long getId_estado() {
        return id_estado;
    }
    public void setId_estado(Long id_estado) {
        this.id_estado = id_estado;
    }
    public String getNombreEstado() {
        return nombreEstado;
    }
    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }
     
}
