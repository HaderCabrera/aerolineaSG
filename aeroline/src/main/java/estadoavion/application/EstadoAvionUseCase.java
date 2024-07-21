package estadoavion.application;

import java.util.List;

import estadoavion.domain.entity.EstadoAvion;
import estadoavion.domain.service.EstadoAvionService;

public class EstadoAvionUseCase {
    private final EstadoAvionService estadoAvionService;

    public EstadoAvionUseCase(EstadoAvionService estadoAvionService) {
        this.estadoAvionService = estadoAvionService;
    }
    
    public List<EstadoAvion> listarEstadoAviones(){
        List<EstadoAvion> lstEstadosAvion = estadoAvionService.listarEstadosAvion();
        return lstEstadosAvion;
    }
}
