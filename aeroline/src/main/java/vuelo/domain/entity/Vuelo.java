package vuelo.domain.entity;

public class Vuelo {
    private Long id_vuelo;
    private String numero_vuelo;
    private Long aeropuerto_origen;
    private Long aeropuerto_destino;
    private String hora_salida;
    private String hora_llegada;
    public Vuelo() {
    }
    public Vuelo(Long id_vuelo, String numero_vuelo, Long aeropuerto_origen, Long aeropuerto_destino,
            String hora_salida, String hora_llegada) {
        this.id_vuelo = id_vuelo;
        this.numero_vuelo = numero_vuelo;
        this.aeropuerto_origen = aeropuerto_origen;
        this.aeropuerto_destino = aeropuerto_destino;
        this.hora_salida = hora_salida;
        this.hora_llegada = hora_llegada;
    }
    public Long getId_vuelo() {
        return id_vuelo;
    }
    public void setId_vuelo(Long id_vuelo) {
        this.id_vuelo = id_vuelo;
    }
    public String getNumero_vuelo() {
        return numero_vuelo;
    }
    public void setNumero_vuelo(String numero_vuelo) {
        this.numero_vuelo = numero_vuelo;
    }
    public Long getAeropuerto_origen() {
        return aeropuerto_origen;
    }
    public void setAeropuerto_origen(Long aeropuerto_origen) {
        this.aeropuerto_origen = aeropuerto_origen;
    }
    public Long getAeropuerto_destino() {
        return aeropuerto_destino;
    }
    public void setAeropuerto_destino(Long aeropuerto_destino) {
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
