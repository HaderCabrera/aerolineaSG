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

    public Aeropuerto consultarAeropuerto(Long id_aeropuerto){
        Aeropuerto aeropuerto = aeropuertoService.consultarAeropuerto(id_aeropuerto);
        return aeropuerto;
    }

    public Boolean updateAeropuerto(Aeropuerto aeropuerto){
        return aeropuertoService.updateAeropuerto(aeropuerto);
    }

    public Boolean eliminarAeropuertoById(Long id_aeropuerto){
        return aeropuertoService.eliminarAeropuertoById(id_aeropuerto);
    }
}
