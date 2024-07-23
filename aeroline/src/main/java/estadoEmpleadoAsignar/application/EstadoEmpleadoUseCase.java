

package estadoEmpleadoAsignar.application;

import java.util.List;

import estadoEmpleadoAsignar.domain.entity.EstadoEmpleado;
import estadoEmpleadoAsignar.domain.service.EstadoEmpeladoService;


public class EstadoEmpleadoUseCase {
    private final EstadoEmpeladoService estadoEmpleadoService;

    public EstadoEmpleadoUseCase(EstadoEmpeladoService estadoEmpleadoService){
        this.estadoEmpleadoService = estadoEmpleadoService;
    }

    public List<EstadoEmpleado> listarEstadosEmpleados(){
        return estadoEmpleadoService.listarEstadosEmpleados();
    }
    
}
