package estadoReserva.infraestructure.inController;

import java.util.List;

import estadoReserva.application.EstadoReservaUseCase;
import estadoReserva.domain.entity.EstadoReserva;

public class EstadoReservaController {
    private final EstadoReservaUseCase estadoReservaUseCase;

    public EstadoReservaController(EstadoReservaUseCase estadoReservaUseCase) {
        this.estadoReservaUseCase = estadoReservaUseCase;
    }
    
    List<EstadoReserva> listarEstadosReserva(){
        return estadoReservaUseCase.listarEstadosReserva();
    }
}
