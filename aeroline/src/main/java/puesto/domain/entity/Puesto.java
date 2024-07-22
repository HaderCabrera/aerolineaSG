package puesto.domain.entity;

public class Puesto {
    private Long id_puesto;
    private Long id_avion;
    private Long id_estadoPuesto;
    private Long numero_puesto;
    public Puesto() {
    }
    public Puesto(Long id_puesto, Long id_avion, Long id_estadoPuesto, Long numero_puesto) {
        this.id_puesto = id_puesto;
        this.id_avion = id_avion;
        this.id_estadoPuesto = id_estadoPuesto;
        this.numero_puesto = numero_puesto;
    }
    public Long getId_puesto() {
        return id_puesto;
    }
    public void setId_puesto(Long id_puesto) {
        this.id_puesto = id_puesto;
    }
    public Long getId_avion() {
        return id_avion;
    }
    public void setId_avion(Long id_avion) {
        this.id_avion = id_avion;
    }
    public Long getId_estadoPuesto() {
        return id_estadoPuesto;
    }
    public void setId_estadoPuesto(Long id_estadoPuesto) {
        this.id_estadoPuesto = id_estadoPuesto;
    }
    public Long getNumero_puesto() {
        return numero_puesto;
    }
    public void setNumero_puesto(Long numero_puesto) {
        this.numero_puesto = numero_puesto;
    }
    
}
