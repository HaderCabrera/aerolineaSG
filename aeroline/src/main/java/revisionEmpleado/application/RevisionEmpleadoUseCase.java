package revisionEmpleado.application;

import revisionEmpleado.domain.service.RevisionEmpleadoService;
import revisionEmpleado.domain.entity.RevisionEmpleado;

public class RevisionEmpleadoUseCase {
    private final RevisionEmpleadoService revisionEmpleadoService;

    public RevisionEmpleadoUseCase(RevisionEmpleadoService revisionEmpleadoService) {
        this.revisionEmpleadoService = revisionEmpleadoService;
    }
    
    public Long registrarRevisionEmpleado(RevisionEmpleado revisionEmpleado){
        Long confirmacion = revisionEmpleadoService.registrarRevisionEmpleado(revisionEmpleado);
        return confirmacion;
    }
}
