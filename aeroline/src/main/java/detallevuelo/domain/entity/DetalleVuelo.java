package detallevuelo.domain.entity;


public class DetalleVuelo {
    private int id_trayecto;
    private String origen_trayecto;
    private String destino_tracyecto;
    private String desc_trayecto;
    private String distancia;
    private String timpoEstimado;
    public DetalleVuelo(){

    }
    public DetalleVuelo(int id_trayecto, String origen_trayecto, String destino_tracyecto, String desc_trayecto,
            String distancia, String timpoEstimado) {
        this.id_trayecto = id_trayecto;
        this.origen_trayecto = origen_trayecto;
        this.destino_tracyecto = destino_tracyecto;
        this.desc_trayecto = desc_trayecto;
        this.distancia = distancia;
        this.timpoEstimado = timpoEstimado;
    }
    public int getId_trayecto() {
        return id_trayecto;
    }
    public void setId_trayecto(int id_trayecto) {
        this.id_trayecto = id_trayecto;
    }
    public String getOrigen_trayecto() {
        return origen_trayecto;
    }
    public void setOrigen_trayecto(String origen_trayecto) {
        this.origen_trayecto = origen_trayecto;
    }
    public String getDestino_tracyecto() {
        return destino_tracyecto;
    }
    public void setDestino_tracyecto(String destino_tracyecto) {
        this.destino_tracyecto = destino_tracyecto;
    }
    public String getDesc_trayecto() {
        return desc_trayecto;
    }
    public void setDesc_trayecto(String desc_trayecto) {
        this.desc_trayecto = desc_trayecto;
    }
    public String getDistancia() {
        return distancia;
    }
    public void setDistancia(String distancia) {
        this.distancia = distancia;
    }
    public String getTimpoEstimado() {
        return timpoEstimado;
    }
    public void setTimpoEstimado(String timpoEstimado) {
        this.timpoEstimado = timpoEstimado;
    }
    
    
    

}
