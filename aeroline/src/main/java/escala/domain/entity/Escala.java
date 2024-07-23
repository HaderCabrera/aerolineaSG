package escala.domain.entity;

public class Escala {
    private Long id_vuelo;
    private Long id_trayecto;
    private String destino;
    private String inicio;
    public Escala() {
    }
    public Escala(Long id_vuelo, Long id_trayecto, String destino, String inicio) {
        this.id_vuelo = id_vuelo;
        this.id_trayecto = id_trayecto;
        this.destino = destino;
        this.inicio = inicio;
    }
    public Long getId_vuelo() {
        return id_vuelo;
    }
    public void setId_vuelo(Long id_vuelo) {
        this.id_vuelo = id_vuelo;
    }
    public Long getId_trayecto() {
        return id_trayecto;
    }
    public void setId_trayecto(Long id_trayecto) {
        this.id_trayecto = id_trayecto;
    }
    public String getDestino() {
        return destino;
    }
    public void setDestino(String destino) {
        this.destino = destino;
    }
    public String getInicio() {
        return inicio;
    }
    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    
}