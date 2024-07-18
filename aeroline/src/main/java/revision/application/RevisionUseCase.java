package revision.application;

import revision.domain.entity.Revision;
import revision.domain.service.RevisionService;

public class RevisionUseCase {
    private final RevisionService revisionService;

    public RevisionUseCase(RevisionService revisionService) {
        this.revisionService = revisionService;
    }
    
    public Long registrarRevision(Revision revision){
        Long confirmacion = revisionService.registrarRevision(revision);
        return confirmacion;
    }

    public Boolean eliminarRevision(Long IdRevision){
        Boolean confirmacion = revisionService.eliminarRevision(IdRevision);
        return confirmacion;
    }
}
