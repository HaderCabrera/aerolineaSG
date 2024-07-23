
package estadoEmpleadoAsignar.domain.entity;


public class EstadoEmpleado {
    private int id_estado;
    private String nombreEstado;

    public EstadoEmpleado(){

    }

    public EstadoEmpleado(int id_estado, String nombreEstado){
        this.id_estado = id_estado;
        this.nombreEstado = nombreEstado;

    }

    public  int getId_estado(){
        return id_estado;
    }

    public  void setId_estado(int id_estado){
        this.id_estado = id_estado;
    }

    public String getNombreEstado(){
        return  nombreEstado;
    }

    public void setNombreEstado(String nombreEstado){
        this.nombreEstado = nombreEstado;
    }
}
