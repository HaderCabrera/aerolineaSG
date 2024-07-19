package avion.domain.entity;

public class Avion {
    private int id_avion;
    private String placa_identificacion;
    private int capacidad;
    private String fabricacion_fecha;
    private int id_estado;
    private int id_modelo;
    private String modelo;
    private String estado;
    public Avion() {
    }
    public Avion(int id_avion, String placa_identificacion, int capacidad, String fabricacion_fecha, int id_estado,
            int id_modelo, String modelo, String estado) {
        this.id_avion = id_avion;
        this.placa_identificacion = placa_identificacion;
        this.capacidad = capacidad;
        this.fabricacion_fecha = fabricacion_fecha;
        this.id_estado = id_estado;
        this.id_modelo = id_modelo;
        this.modelo = modelo;
        this.estado = estado;
    }
    public int getId_avion() {
        return id_avion;
    }
    public void setId_avion(int id_avion) {
        this.id_avion = id_avion;
    }
    public String getPlaca_identificacion() {
        return placa_identificacion;
    }
    public void setPlaca_identificacion(String placa_identificacion) {
        this.placa_identificacion = placa_identificacion;
    }
    public int getCapacidad() {
        return capacidad;
    }
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
    public String getFabricacion_fecha() {
        return fabricacion_fecha;
    }
    public void setFabricacion_fecha(String fabricacion_fecha) {
        this.fabricacion_fecha = fabricacion_fecha;
    }
    public int getId_estado() {
        return id_estado;
    }
    public void setId_estado(int id_estado) {
        this.id_estado = id_estado;
    }
    public int getId_modelo() {
        return id_modelo;
    }
    public void setId_modelo(int id_modelo) {
        this.id_modelo = id_modelo;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
