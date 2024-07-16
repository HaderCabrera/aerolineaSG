package revisionEmpleado.domain.entity;

public class RevisionEmpleado {
    private int id_revision;
    private int id_empleado;

    //Constructors
    public RevisionEmpleado() {
    }
    public int getId_revision() {
        return id_revision;
    }
    
    //Getters and Setters
    public void setId_revision(int id_revision) {
        this.id_revision = id_revision;
    }
    public int getId_empleado() {
        return id_empleado;
    }
    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }
    
}
