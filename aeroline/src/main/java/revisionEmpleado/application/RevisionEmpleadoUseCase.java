package revisionEmpleado.application;

import revisionEmpleado.domain.service.RevisionEmpleadoService;
import revisionEmpleado.domain.entity.RevisionEmpleado;

public class RevisionEmpleadoUseCase {
    private final RevisionEmpleadoService revisionEmpleadoService;

    public RevisionEmpleadoUseCase(RevisionEmpleadoService revisionEmpleadoService) {
        this.revisionEmpleadoService = revisionEmpleadoService;
    }
    
    public void registrarRevisionEmpleado(RevisionEmpleado revisionEmpleado){
        revisionEmpleadoService.registrarRevisionEmpleado(revisionEmpleado);
    }
}
