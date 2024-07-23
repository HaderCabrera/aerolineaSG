package detallevuelo.domain.service;

import java.util.List;

import detallevuelo.domain.entity.DetalleVuelo;
import empleado.domain.entity.Empleado;



public interface DetalleVueloService {
    DetalleVuelo consultarTracayecto(int id_trayecto);
    boolean eliminarTrayecto(int id_trayecto);
    DetalleVuelo asignarTripulacionTrayecto(Empleado empleado);
    boolean editarTrayecto(DetalleVuelo id_trayecto);
    List<DetalleVuelo> listarTrayectos();
    DetalleVuelo obtenerTrayectoByDescripcion(String descripcion);
}
