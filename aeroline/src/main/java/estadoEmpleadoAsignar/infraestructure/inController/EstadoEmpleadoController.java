
package estadoEmpleadoAsignar.infraestructure.inController;

import java.util.List;

import estadoEmpleadoAsignar.application.EstadoEmpleadoUseCase;
import estadoEmpleadoAsignar.domain.entity.EstadoEmpleado;


public class EstadoEmpleadoController {
    private  final EstadoEmpleadoUseCase estadoEmpleadoUseCase;

    public EstadoEmpleadoController(EstadoEmpleadoUseCase estadoEmpleadoUseCase){
        this.estadoEmpleadoUseCase = estadoEmpleadoUseCase;
    }

    public List<EstadoEmpleado> listarEstadosEmpleados(){
        List<EstadoEmpleado> lstRevisionEstados = estadoEmpleadoUseCase.listarEstadosEmpleados();
        return lstRevisionEstados;
    }
}
