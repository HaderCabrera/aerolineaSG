package detallevuelo.domain.entity;

public class DetalleVuelo {
    
    private int id_vuelo;
    private int id_escala;
    private int id_puesto;


    public DetalleVuelo(){

    }

    public DetalleVuelo(int id_vuelo, int id_escala, int id_puesto) {
        this.id_vuelo = id_vuelo;
        this.id_escala = id_escala;
        this.id_puesto = id_puesto;
    }


    public int getId_vuelo() {
        return id_vuelo;
    }


    public void setId_vuelo(int id_vuelo) {
        this.id_vuelo = id_vuelo;
    }


    public int getId_escala() {
        return id_escala;
    }


    public void setId_escala(int id_escala) {
        this.id_escala = id_escala;
    }


    public int getId_puesto() {
        return id_puesto;
    }


    public void setId_puesto(int id_puesto) {
        this.id_puesto = id_puesto;
    }

    

}
