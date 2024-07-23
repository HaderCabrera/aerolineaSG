package tripulacion.domain.service;


import java.util.List;

import empleado.domain.entity.Empleado;
import tripulacion.domain.entity.Tripulacion;


public interface TripulacionService {

    Tripulacion asignarEmpleadoToTripulacion(Tripulacion tripulacion);
    List<Empleado> obtenerTripulacionPorVuelo(String codec_vuelo);
    List<Empleado> ObtenerTripulantesDisponibles(String dispo);
}