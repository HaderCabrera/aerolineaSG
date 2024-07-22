package tripulacion.domain.service;

import tripulacion.domain.entity.tripulacion;

public interface tripulacionService {
    tripulacion asignarTripulacion(int id_vuelo, int id_empleado);
}
