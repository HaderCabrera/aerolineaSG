package detallevuelo.application;

import detallevuelo.domain.service.DetalleVueloService;

public class EliminarDetalleVuelouseCase {
    private DetalleVueloService detalleVueloService;

    public EliminarDetalleVuelouseCase(DetalleVueloService detalleVueloService) {
        this.detalleVueloService = detalleVueloService;
    }
    
    public void eliminarDetalleVuelo(int id_vuelo){
        detalleVueloService.eliminarDetalleVuelo(id_vuelo);
    }
}
