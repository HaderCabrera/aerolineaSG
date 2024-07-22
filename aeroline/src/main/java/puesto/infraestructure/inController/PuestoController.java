package puesto.infraestructure.inController;

import java.util.List;

import puesto.application.PuestoUseCase;
import puesto.domain.entity.Puesto;

public class PuestoController {
    private final PuestoUseCase puestoUseCase;

    public PuestoController(PuestoUseCase puestoUseCase) {
        this.puestoUseCase = puestoUseCase;
    }
    
    public List<Puesto> listarPuestoByIdAvion(Long id_avion){
        return null;
    }
}
