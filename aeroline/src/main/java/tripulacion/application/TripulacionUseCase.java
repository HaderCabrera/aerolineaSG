

package tripulacion.application;


import java.util.List;

import empleado.domain.entity.Empleado;
import tripulacion.domain.entity.Tripulacion;
import tripulacion.domain.service.TripulacionService;


public class TripulacionUseCase {
    private  final TripulacionService tripulacionservice;

    public  TripulacionUseCase(TripulacionService tripulacionservice){
        this.tripulacionservice = tripulacionservice;
    }

    public Tripulacion asignarEmpleadoToTripulacion(Tripulacion tripulacion){
        return  tripulacionservice.asignarEmpleadoToTripulacion(tripulacion);
    }

    public List<Empleado> obtenerTripulacionPorVuelo(String codec_vuelo){ 
        return  tripulacionservice.obtenerTripulacionPorVuelo(codec_vuelo);
    }
    
    public List<Empleado> ObtenerTripulantesDisponibles(String dispo){
        return  tripulacionservice.ObtenerTripulantesDisponibles(dispo);
    }

}
