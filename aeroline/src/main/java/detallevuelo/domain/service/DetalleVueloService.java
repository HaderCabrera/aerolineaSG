package detallevuelo.domain.service;

import detallevuelo.domain.entity.DetalleVuelo;
import empleado.domain.entity.empleado;



public interface DetalleVueloService {
    DetalleVuelo consultarTracayecto(int id_trayecto);
    boolean eliminarTrayecto(int id_trayecto);
    DetalleVuelo asignarTripulacionTrayecto(empleado empleado);
    boolean editarTrayecto(DetalleVuelo id_trayecto);
    
}
