package detallevuelo.domain.service;

import detallevuelo.domain.entity.DetalleVuelo;

public interface DetalleVueloService {
    DetalleVuelo consultarDetalleVuelo(String id_vuelo);
    void eliminarDetalleVuelo(String numero_vuelo);
    DetalleVuelo actualizarDetallevulo();
    DetalleVuelo consultarInfoTripulacion(String id_empleado);
    DetalleVuelo editarEscalaVuelo();
    
}
