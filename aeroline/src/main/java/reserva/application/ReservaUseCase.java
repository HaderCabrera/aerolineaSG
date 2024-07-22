package reserva.application;

import reserva.domain.entity.Reserva;
import reserva.domain.service.ReservaService;

public class ReservaUseCase {
    private final ReservaService reservaService;

    public ReservaUseCase(ReservaService reservaService) {
        this.reservaService = reservaService;
    }
    
    public Boolean registrarReserva(Reserva reserva){
        return reservaService.registrarReserva(reserva);
    }
}
