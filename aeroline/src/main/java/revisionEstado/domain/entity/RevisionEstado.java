package revisionEstado.domain.entity;

public class RevisionEstado {
    private Long id_estado;
    private String estado;
    public RevisionEstado() {
    }
    public RevisionEstado(Long id_estado, String estado) {
        this.id_estado = id_estado;
        this.estado = estado;
    }
    public Long getId_estado() {
        return id_estado;
    }
    public void setId_estado(Long id_estado) {
        this.id_estado = id_estado;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
