package detallevuelo.application;

import detallevuelo.domain.entity.DetalleVuelo;
import detallevuelo.domain.service.DetalleVueloService;

public class DetalleVueloUseCase {
    // Linquear o unir el servicio con el caso de uso
    private  final DetalleVueloService detalleVueloService;

    public DetalleVueloUseCase(DetalleVueloService detalleVueloService) {
        this.detalleVueloService = detalleVueloService;
    }

    public DetalleVuelo consultarDetalleVuelo(int id_vuelo){
        return  detalleVueloService.consultarDetalleVuelo(id_vuelo);
    }
    
    public void eliminarDetalleVuelo(int id_vuelo){
        detalleVueloService.eliminarDetalleVuelo(id_vuelo);
    }

    DetalleVuelo consultarInfoTripulacion(int id_empleado){
        return detalleVueloService.consultarInfoTripulacion(id_empleado);
    }
    
    DetalleVuelo editarEscalaVuelo(int id_escala){
        return detalleVueloService.editarEscalaVuelo(id_escala);
    }
    
}
