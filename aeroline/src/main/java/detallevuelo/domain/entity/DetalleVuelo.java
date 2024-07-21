package detallevuelo.domain.entity;


public class DetalleVuelo {
    private String empleado;
    private String rolEmpleado;
    private String Numero_Vuelo;
    private String aeropuertoOrigen;
    private String aeropuertoDestino;
    private String horaSalida;
    private String horaLlegada;
    public DetalleVuelo(){

    }
    public DetalleVuelo(String empleado, String rolEmpleado, String numero_Vuelo, String aeropuertoOrigen,
            String aeropuertoDestino, String horaSalida, String horaLlegada) {
        this.empleado = empleado;
        this.rolEmpleado = rolEmpleado;
        Numero_Vuelo = numero_Vuelo;
        this.aeropuertoOrigen = aeropuertoOrigen;
        this.aeropuertoDestino = aeropuertoDestino;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
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
    public String getNumero_Vuelo() {
        return Numero_Vuelo;
    }
    public void setNumero_Vuelo(String numero_Vuelo) {
        Numero_Vuelo = numero_Vuelo;
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


}
