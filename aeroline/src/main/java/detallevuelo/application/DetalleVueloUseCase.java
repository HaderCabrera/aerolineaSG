package detallevuelo.application;

import java.util.List;

import detallevuelo.domain.entity.DetalleVuelo;
import detallevuelo.domain.service.DetalleVueloService;
import empleado.domain.entity.empleado;

public class DetalleVueloUseCase {
    private DetalleVueloService detalleVueloService;

    public DetalleVueloUseCase(DetalleVueloService detalleVueloService) {
        this.detalleVueloService = detalleVueloService;
    }
     
    public DetalleVuelo consultarTrayecto(int id_trayecto){
        return detalleVueloService.consultarTracayecto(id_trayecto);
    }

    public boolean eliminarTrayecto(int id_trayecto){
         return detalleVueloService.eliminarTrayecto(id_trayecto);
    }
    public DetalleVuelo asignarTripulacionTrayecto(empleado empleado){
        return detalleVueloService.asignarTripulacionTrayecto(empleado);
    }

    public boolean editarTrayecto(DetalleVuelo trayecto){
        return detalleVueloService.editarTrayecto(trayecto);
    }

    public List<DetalleVuelo> listarTrayectos(){
        return detalleVueloService.listarTrayectos();
    }
}
