package detallevuelo.application;

import detallevuelo.domain.entity.DetalleVuelo;
import detallevuelo.domain.service.DetalleVueloService;

public class ConsultarInformacioTripulacionUseCase {
    private DetalleVueloService detalleVueloService;

    public ConsultarInformacioTripulacionUseCase(DetalleVueloService detalleVueloService) {
        this.detalleVueloService = detalleVueloService;
    }
    
    public DetalleVuelo consultarInfoTripulacion(int id_empleado){
        return detalleVueloService.consultarInfoTripulacion(id_empleado);
    }
}
