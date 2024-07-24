package reserva.domain.service;

import reserva.domain.entity.Reserva;

public interface ReservaService {
    Boolean registrarReserva(Reserva reserva);
    Reserva consultarReservaByCliente(Long idCliente);
    Reserva consultarReservaByTrayecto(Long idTrayecto);
}
