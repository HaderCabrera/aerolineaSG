package detallevuelo.application;

import detallevuelo.domain.entity.DetalleVuelo;
import detallevuelo.domain.service.DetalleVueloService;

public class DetalleVueloUseCase {
    // Linquear o unir el servicio con el caso de uso
    private  DetalleVueloService detalleVueloService;

    public DetalleVueloUseCase(DetalleVueloService detalleVueloService) {
        this.detalleVueloService = detalleVueloService;
    }

    public DetalleVuelo consultarDetalleVuelo(String id_vuelo){
        return  detalleVueloService.consultarDetalleVuelo(id_vuelo);
    }
    

    
    
    
}
