package revision.domain.entity;

public class Revision {
    private String fecha_revision;
    private int id_avion;
    private String descrip;

    /*Contructors */
    public Revision() {
    }
    public Revision(String fecha_revision, int id_avion, String descrip) {
        this.fecha_revision = fecha_revision;
        this.id_avion = id_avion;
        this.descrip = descrip;
    }

    /*Getters and Setters */
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
    
}
