package detallevuelo.application;

import detallevuelo.domain.entity.DetalleVuelo;
import detallevuelo.domain.service.DetalleVueloService;
import empleado.domain.entity.empleado;

public class DetalleVueloUseCase {
    private DetalleVueloService detalleVueloService;

    public DetalleVueloUseCase(DetalleVueloService detalleVueloService) {
        this.detalleVueloService = detalleVueloService;
    }

    public DetalleVuelo consultarTrayecto(int Numero_Vuelo){
        return detalleVueloService.consultarTrayecto(Numero_Vuelo);
    }
    
    public void eliminarTrayecto(int id_trayecto){
        detalleVueloService.eliminarTrayecto(id_trayecto);
    }

    public DetalleVuelo asignarTripulacionTrayecto(empleado empleado){
       return detalleVueloService.asignarTripulacionTrayecto(empleado);
    }
    public DetalleVuelo actualizarTrayecto(int id_trayecto){
        return detalleVueloService.actualizarTrayecto(id_trayecto);
    }
}
