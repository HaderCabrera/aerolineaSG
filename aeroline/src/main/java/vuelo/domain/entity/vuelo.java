package vuelo.domain.entity;

public class vuelo {
    private int id_cuelo;
    private String id_nuevo_vuelo;
    private int aeropuerto_origen;
    private int aeropuerto_destino;
    private String hora_salida;
    private String hora_llegada;

    
    public vuelo() {
    }


    public vuelo(int id_cuelo, String id_nuevo_vuelo, int aeropuerto_origen, int aeropuerto_destino, String hora_salida,
            String hora_llegada) {
        this.id_cuelo = id_cuelo;
        this.id_nuevo_vuelo = id_nuevo_vuelo;
        this.aeropuerto_origen = aeropuerto_origen;
        this.aeropuerto_destino = aeropuerto_destino;
        this.hora_salida = hora_salida;
        this.hora_llegada = hora_llegada;
    }


    public int getId_cuelo() {
        return id_cuelo;
    }


    public void setId_cuelo(int id_cuelo) {
        this.id_cuelo = id_cuelo;
    }


    public String getId_nuevo_vuelo() {
        return id_nuevo_vuelo;
    }


    public void setId_nuevo_vuelo(String id_nuevo_vuelo) {
        this.id_nuevo_vuelo = id_nuevo_vuelo;
    }


    public int getAeropuerto_origen() {
        return aeropuerto_origen;
    }


    public void setAeropuerto_origen(int aeropuerto_origen) {
        this.aeropuerto_origen = aeropuerto_origen;
    }


    public int getAeropuerto_destino() {
        return aeropuerto_destino;
    }


    public void setAeropuerto_destino(int aeropuerto_destino) {
        this.aeropuerto_destino = aeropuerto_destino;
    }


    public String getHora_salida() {
        return hora_salida;
    }


    public void setHora_salida(String hora_salida) {
        this.hora_salida = hora_salida;
    }


    public String getHora_llegada() {
        return hora_llegada;
    }


    public void setHora_llegada(String hora_llegada) {
        this.hora_llegada = hora_llegada;
    }
    


}
