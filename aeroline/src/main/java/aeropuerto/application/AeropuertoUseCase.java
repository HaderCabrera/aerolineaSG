package aeropuerto.application;

import aeropuerto.domain.entity.Aeropuerto;
import aeropuerto.domain.service.AeropuertoService;

public class AeropuertoUseCase {

    private final AeropuertoService aeropuertoService;

    public AeropuertoUseCase(AeropuertoService aeropuertoService) {
        this.aeropuertoService = aeropuertoService;
    }
    

    public Boolean registrarAeropuerto(Aeropuerto aeropuerto){
        Boolean confirmacion = aeropuertoService.registrarAeropuerto(aeropuerto);
        return confirmacion;
    }
}
