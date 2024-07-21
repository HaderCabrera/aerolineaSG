package revision.application;

import java.util.List;

import revision.domain.entity.Revision;
import revision.domain.service.RevisionService;

public class RevisionUseCase {
    private final RevisionService revisionService;

    public RevisionUseCase(RevisionService revisionService) {
        this.revisionService = revisionService;
    }
    
    public Long registrarRevision(Revision revision){
        return revisionService.registrarRevision(revision);
    }

    public Boolean eliminarRevision(Long IdRevision){
        return revisionService.eliminarRevision(IdRevision);
    }

    public List<Revision> listarRevisionesByPlaca(String placa_avion) {
        List<Revision> lstRevisiones = revisionService.listarRevisionesByPlaca(placa_avion);
        return lstRevisiones;
    }

    public Boolean updateRevisionById(Revision revision){
        return revisionService.updateRevisionById(revision);
    }

    public Revision consultarRevisionById(Long id_revision){
        return revisionService.consultarRevisionById(id_revision);
    }
}
