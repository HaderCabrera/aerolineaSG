package tripulacion.domain.service;

import java.util.List;

import empleado.domain.entity.empleado;
import tripulacion.domain.entity.tripulacion;

public interface tripulacionService {
    tripulacion asignarEmpleado(empleado empleado);
    List<empleado> listaempleados();
    tripulacion obtenerTripulacionPorVuelo(int idVuelo);
}