package tripulacion.domain.entity;

public class tripulacion {
    private int id_vuelo;
    private int id_empleado;


    public tripulacion() {
    }


    public tripulacion(int id_vuelo, int id_empleado) {
        this.id_vuelo = id_vuelo;
        this.id_empleado = id_empleado;
    }


    public int getId_vuelo() {
        return id_vuelo;
    }


    public void setId_vuelo(int id_vuelo) {
        this.id_vuelo = id_vuelo;
    }


    public int getId_empleado() {
        return id_empleado;
    }


    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    } 

    
}
