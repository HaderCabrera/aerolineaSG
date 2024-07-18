package detallevuelo.application;

import detallevuelo.domain.entity.DetalleVuelo;
import detallevuelo.domain.service.DetalleVueloService;

public class DetalleVueloUseCase {
    private DetalleVueloService detalleVueloService;

    public DetalleVueloUseCase(DetalleVueloService detalleVueloService) {
        this.detalleVueloService = detalleVueloService;
    }

     public DetalleVuelo consultarDetalleVuelo(String Numero_Vuelo){
        return  detalleVueloService.consultarDetalleVuelo(Numero_Vuelo);
    }

    public DetalleVuelo consultarInfoTripulacion(int id_empleado){
        return detalleVueloService.consultarInfoTripulacion(id_empleado);
    }
    public DetalleVuelo editarEscalaVuelo(int id_escala){
        return detalleVueloService.editarEscalaVuelo(id_escala);
    }

    public void eliminarDetalleVuelo(int id_vuelo){
        detalleVueloService.eliminarDetalleVuelo(id_vuelo);
    }

    

    

}
