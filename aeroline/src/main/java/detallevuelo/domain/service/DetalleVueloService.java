package detallevuelo.domain.service;

import detallevuelo.domain.entity.DetalleVuelo;


public interface DetalleVueloService {
    DetalleVuelo consultarDetalleVuelo(String Numero_Vuelo);
    void eliminarDetalleVuelo(int id_detalle_vuelo);
    // Esto ya inluye otra tabla que es EMPLEADO-DETALLEVUELO
    DetalleVuelo consultarInfoTripulacion(int id_empleado);
    //DetalleVuelo asignarEmpleadoTripulacion(empleado Empleado);
    DetalleVuelo editarEscalaVuelo(int id_vuelo);
    
}
