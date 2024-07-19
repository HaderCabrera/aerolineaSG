package revision.domain.entity;

public class Revision {
    private Long id_revision;
    private String fecha_revision;
    private int id_avion;
    private String descrip;
    private Long id_estado;
    private String estado;
    public Revision() {
    }
    public Revision(Long id_revision, String fecha_revision, int id_avion, String descrip, Long id_estado,
            String estado) {
        this.id_revision = id_revision;
        this.fecha_revision = fecha_revision;
        this.id_avion = id_avion;
        this.descrip = descrip;
        this.id_estado = id_estado;
        this.estado = estado;
    }
    public Long getId_revision() {
        return id_revision;
    }
    public void setId_revision(Long id_revision) {
        this.id_revision = id_revision;
    }
    public String getFecha_revision() {
        return fecha_revision;
    }
    public void setFecha_revision(String fecha_revision) {
        this.fecha_revision = fecha_revision;
    }
    public int getId_avion() {
        return id_avion;
    }
    public void setId_avion(int id_avion) {
        this.id_avion = id_avion;
    }
    public String getDescrip() {
        return descrip;
    }
    public void setDescrip(String descrip) {
        this.descrip = descrip;
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
