package detallevuelo.domain.entity;


public class DetalleVuelo {
    private String empleado;
    private String rolEmpleado;
    private String aeropuertoOrigen;
    private String aeropuertoDestino;
    private String horaSalida;
    private String horaLlegada;
    private int id_detalle_vuelo;
    private int id_vuelo;

    public DetalleVuelo(){

    }

    public DetalleVuelo(String empleado, String rolEmpleado, String aeropuertoOrigen, String aeropuertoDestino,
            String horaSalida, String horaLlegada, int id_detalle_vuelo, int id_vuelo) {
        this.empleado = empleado;
        this.rolEmpleado = rolEmpleado;
        this.aeropuertoOrigen = aeropuertoOrigen;
        this.aeropuertoDestino = aeropuertoDestino;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
        this.id_detalle_vuelo = id_detalle_vuelo;
        this.id_vuelo = id_vuelo;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public String getRolEmpleado() {
        return rolEmpleado;
    }

    public void setRolEmpleado(String rolEmpleado) {
        this.rolEmpleado = rolEmpleado;
    }

    public String getAeropuertoOrigen() {
        return aeropuertoOrigen;
    }

    public void setAeropuertoOrigen(String aeropuertoOrigen) {
        this.aeropuertoOrigen = aeropuertoOrigen;
    }

    public String getAeropuertoDestino() {
        return aeropuertoDestino;
    }

    public void setAeropuertoDestino(String aeropuertoDestino) {
        this.aeropuertoDestino = aeropuertoDestino;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(String horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public int getId_detalle_vuelo() {
        return id_detalle_vuelo;
    }

    public void setId_detalle_vuelo(int id_detalle_vuelo) {
        this.id_detalle_vuelo = id_detalle_vuelo;
    }

    public int getId_vuelo() {
        return id_vuelo;
    }

    public void setId_vuelo(int id_vuelo) {
        this.id_vuelo = id_vuelo;
    }

    

}
