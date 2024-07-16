package revision.application;

import revision.domain.entity.Revision;
import revision.domain.service.RevisionService;

public class RevisionUseCase {
    private final RevisionService revisionService;

    public RevisionUseCase(RevisionService revisionService) {
        this.revisionService = revisionService;
    }
    
    Boolean registrarRevision(Revision revision){
        Boolean confirmacion = revisionService.registrarRevision(revision);
        return confirmacion;
    }
}
