package revisionEstado.application;

import java.util.List;

import revisionEstado.domain.entity.RevisionEstado;
import revisionEstado.domain.service.RevisionEstadoService;

public class RevisionEstadoUseCase {
    private final RevisionEstadoService revisionEstadoService;

    public RevisionEstadoUseCase(RevisionEstadoService revisionEstadoService) {
        this.revisionEstadoService = revisionEstadoService;
    }
    
    public List<RevisionEstado> listarEstados(){
        return revisionEstadoService.listarEstados();
    }
}
