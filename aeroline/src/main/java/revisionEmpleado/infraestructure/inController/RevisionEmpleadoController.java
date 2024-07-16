package revisionEmpleado.infraestructure.inController;

import revisionEmpleado.application.RevisionEmpleadoUseCase;
import revisionEmpleado.domain.entity.RevisionEmpleado;

public class RevisionEmpleadoController {
    private final RevisionEmpleadoUseCase revisionEmpleadoUseCase;

    public RevisionEmpleadoController(RevisionEmpleadoUseCase revisionEmpleadoUseCase) {
        this.revisionEmpleadoUseCase = revisionEmpleadoUseCase;
    }

    public void registrarRevisionEmpleado(RevisionEmpleado revisionEmpleado){
        revisionEmpleadoUseCase.registrarRevisionEmpleado(revisionEmpleado);
        System.out.println("PEDIR DATOS DE REVISION EMPLEADO");
    }

    public RevisionEmpleado solicitarDatosRegistro(){
        //LOGICA DE INGRESAR DATOS
        //LOGICA DE LLAMAR A registrarRevisionEmpleado
        return null;
    }
}
