package reserva.domain.entity;

public class Reserva {
    private Long id_reserva;
    private String fecha_reserva;
    private Long id_cliente;
    private Long id_estadoReserva;
    private Long id_puesto;
    private Long id_tarifa;
    public Reserva() {
    }
    public Reserva(Long id_reserva, String fecha_reserva, Long id_cliente, Long id_estadoReserva, Long id_puesto,
            Long id_tarifa) {
        this.id_reserva = id_reserva;
        this.fecha_reserva = fecha_reserva;
        this.id_cliente = id_cliente;
        this.id_estadoReserva = id_estadoReserva;
        this.id_puesto = id_puesto;
        this.id_tarifa = id_tarifa;
    }
    public Long getId_reserva() {
        return id_reserva;
    }
    public void setId_reserva(Long id_reserva) {
        this.id_reserva = id_reserva;
    }
    public String getFecha_reserva() {
        return fecha_reserva;
    }
    public void setFecha_reserva(String fecha_reserva) {
        this.fecha_reserva = fecha_reserva;
    }
    public Long getId_cliente() {
        return id_cliente;
    }
    public void setId_cliente(Long id_cliente) {
        this.id_cliente = id_cliente;
    }
    public Long getId_estadoReserva() {
        return id_estadoReserva;
    }
    public void setId_estadoReserva(Long id_estadoReserva) {
        this.id_estadoReserva = id_estadoReserva;
    }
    public Long getId_puesto() {
        return id_puesto;
    }
    public void setId_puesto(Long id_puesto) {
        this.id_puesto = id_puesto;
    }
    public Long getId_tarifa() {
        return id_tarifa;
    }
    public void setId_tarifa(Long id_tarifa) {
        this.id_tarifa = id_tarifa;
    }

    
}
