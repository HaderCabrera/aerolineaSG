package revisionEstado.infraestructure.inController;

import java.util.List;

import revisionEstado.application.RevisionEstadoUseCase;
import revisionEstado.domain.entity.RevisionEstado;

public class RevisionEstadoController {
    private final RevisionEstadoUseCase revisionEstadoUseCase;

    public RevisionEstadoController(RevisionEstadoUseCase revisionEstadoUseCase) {
        this.revisionEstadoUseCase = revisionEstadoUseCase;
    }
    
    public List<RevisionEstado> listarEstados(){
        List<RevisionEstado> lstRevisionEstados = revisionEstadoUseCase.listarEstados();
        return lstRevisionEstados;
    }
}
