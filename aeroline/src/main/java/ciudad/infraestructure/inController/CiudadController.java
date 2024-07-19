package ciudad.infraestructure.inController;

import java.util.List;

import ciudad.application.CiudadUseCase;
import ciudad.domain.entity.Ciudad;

public class CiudadController {
    private final CiudadUseCase ciudadUseCase;

    public CiudadController(CiudadUseCase ciudadUseCase) {
        this.ciudadUseCase = ciudadUseCase;
    }
    
    public List<Ciudad> listarCiudades(){
        return ciudadUseCase.listarCiudades();
    }
}
