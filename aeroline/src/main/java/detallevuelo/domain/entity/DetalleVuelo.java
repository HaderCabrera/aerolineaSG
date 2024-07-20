package detallevuelo.domain.entity;


public class DetalleVuelo {
    private int id_trayecto;
    private String destino_trayecto;
    private String origen_trayecto;
    private String desc_trayecto;
    private String distancia;
    private String numero_vuelo;
    public DetalleVuelo(){

    }
    public DetalleVuelo(int id_trayecto, String destino_trayecto, String origen_trayecto, String desc_trayecto,
            String distancia, String numero_vuelo) {
        this.id_trayecto = id_trayecto;
        this.destino_trayecto = destino_trayecto;
        this.origen_trayecto = origen_trayecto;
        this.desc_trayecto = desc_trayecto;
        this.distancia = distancia;
        this.numero_vuelo = numero_vuelo;
    }
    public int getId_trayecto() {
        return id_trayecto;
    }
    public void setId_trayecto(int id_trayecto) {
        this.id_trayecto = id_trayecto;
    }
    public String getDestino_trayecto() {
        return destino_trayecto;
    }
    public void setDestino_trayecto(String destino_trayecto) {
        this.destino_trayecto = destino_trayecto;
    }
    public String getOrigen_trayecto() {
        return origen_trayecto;
    }
    public void setOrigen_trayecto(String origen_trayecto) {
        this.origen_trayecto = origen_trayecto;
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
    public String getNumero_vuelo() {
        return numero_vuelo;
    }
    public void setNumero_vuelo(String numero_vuelo) {
        this.numero_vuelo = numero_vuelo;
    }
    
}
