package detallevuelo.domain.service;

import detallevuelo.domain.entity.DetalleVuelo;

public interface DetalleVueloService {
    void consultarDetalleVuelo();
    DetalleVuelo eliminarDetalleVuelo(String numero_vuelo);
    DetalleVuelo actualizarDetallevulo();
    void  consultarInfoTripulacion(String id_empleado);
    DetalleVuelo editarEscalaVuelo();
    
}
