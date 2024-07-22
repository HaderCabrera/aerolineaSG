package estadoReserva.application;

import java.util.List;

import estadoReserva.domain.entity.EstadoReserva;
import estadoReserva.domain.service.EstadoReservaService;

public class EstadoReservaUseCase {
    private final EstadoReservaService estadoReservaService;

    public EstadoReservaUseCase(EstadoReservaService estadoReservaService) {
        this.estadoReservaService = estadoReservaService;
    }

    public List<EstadoReserva> listarEstadosReserva(){
        return estadoReservaService.listarEstadosReserva();
    }
}
