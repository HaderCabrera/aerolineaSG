package estadoavion.infraestructure.inController;

import java.util.List;

import estadoavion.application.EstadoAvionUseCase;
import estadoavion.domain.entity.EstadoAvion;

public class EstadoAvionController {
    private final EstadoAvionUseCase estadoAvionUseCase;

    public EstadoAvionController(EstadoAvionUseCase estadoAvionUseCase) {
        this.estadoAvionUseCase = estadoAvionUseCase;
    }
    
    public List<EstadoAvion> listarEstadoAviones(){
        List<EstadoAvion> lstEstadosAvion = estadoAvionUseCase.listarEstadoAviones();
        return lstEstadosAvion;
    }
}
