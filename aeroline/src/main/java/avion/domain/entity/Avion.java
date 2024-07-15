package avion.domain.entity;

import java.sql.Date;

public class Avion {
    private int id_avion;
    private String placa_identificacion;
    private Date fabricacion_fecha;
    private int id_estado;
    private int id_modelo;
    public Avion() {
    }
    public Avion(int id_avion, String placa_identificacion, Date fabricacion_fecha, int id_estado, int id_modelo) {
        this.id_avion = id_avion;
        this.placa_identificacion = placa_identificacion;
        this.fabricacion_fecha = fabricacion_fecha;
        this.id_estado = id_estado;
        this.id_modelo = id_modelo;
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
    public Date getFabricacion_fecha() {
        return fabricacion_fecha;
    }
    public void setFabricacion_fecha(Date fabricacion_fecha) {
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

    
}
