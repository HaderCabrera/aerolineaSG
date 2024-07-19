package ciudad.application;

import java.util.List;

import ciudad.domain.entity.Ciudad;
import ciudad.domain.service.CiudadService;

public class CiudadUseCase {
    private final CiudadService ciudadService;

    public CiudadUseCase(CiudadService ciudadService) {
        this.ciudadService = ciudadService;
    }
    
    public List<Ciudad> listarCiudades(){
        return ciudadService.listarCiudades();
    }
}
