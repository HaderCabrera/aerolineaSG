package escala.domain.entity;

public class Escala {
    private Long id_vuelo;
    private Long id_trayecto;
    public Escala() {
    }
    public Escala(Long id_vuelo, Long id_trayecto) {
        this.id_vuelo = id_vuelo;
        this.id_trayecto = id_trayecto;
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
    
}