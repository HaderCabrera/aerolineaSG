package reserva.infraestructure.outRepository;

import reserva.domain.entity.Reserva;
import reserva.domain.service.ReservaService;

public class ReservaRepository implements ReservaService{

    @Override
    public Boolean registrarReserva(Reserva reserva) {
        return false;
    }

}
