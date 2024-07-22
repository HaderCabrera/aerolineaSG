package puesto.application;

import java.util.List;

import puesto.domain.entity.Puesto;
import puesto.domain.service.PuestoService;

public class PuestoUseCase {
    private final PuestoService puestoService;

    public PuestoUseCase(PuestoService puestoService) {
        this.puestoService = puestoService;
    }
    
    List<Puesto> lstPuestosByIdAvion(Long id_avion){
        return puestoService.lstPuestosByIdAvion(id_avion);
    }
}
