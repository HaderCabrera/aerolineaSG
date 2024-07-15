package detallevuelo.application;

import detallevuelo.domain.entity.DetalleVuelo;
import detallevuelo.domain.service.DetalleVueloService;

public class ConsultarDetalleVueloUseCase {
    private DetalleVueloService detalleVueloService;

    public ConsultarDetalleVueloUseCase(DetalleVueloService detalleVueloService) {
        this.detalleVueloService = detalleVueloService;
    }

     public DetalleVuelo consultarDetalleVuelo(int id_vuelo){
        return  detalleVueloService.consultarDetalleVuelo(id_vuelo);
    }
    

    

}
