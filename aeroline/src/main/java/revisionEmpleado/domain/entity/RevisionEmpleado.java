package revisionEmpleado.domain.entity;

public class RevisionEmpleado {
    private Long id_revision;
    private String id_empleado;
    
    public RevisionEmpleado() {
    }
    public RevisionEmpleado(Long id_revision, String id_empleado) {
        this.id_revision = id_revision;
        this.id_empleado = id_empleado;
    }
    public Long getId_revision() {
        return id_revision;
    }
    public void setId_revision(Long id_revision) {
        this.id_revision = id_revision;
    }
    public String getId_empleado() {
        return id_empleado;
    }
    public void setId_empleado(String id_empleado) {
        this.id_empleado = id_empleado;
    }

    
}
