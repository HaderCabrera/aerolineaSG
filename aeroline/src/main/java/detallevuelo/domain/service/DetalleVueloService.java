package detallevuelo.domain.service;

import detallevuelo.domain.entity.DetalleVuelo;
import empleado.domain.entity.empleado;


public interface DetalleVueloService {
    DetalleVuelo consultarTrayecto(int Numero_Vuelo);
    void eliminarTrayecto(int id_treayecto);
    DetalleVuelo asignarTripulacionTrayecto(empleado empleado);
    DetalleVuelo actualizarTrayecto(int id_trayecto);
}
