package revision.domain.service;

import java.util.List;

import revision.domain.entity.Revision;

public interface RevisionService {
    Long registrarRevision(Revision revision);
    Boolean eliminarRevision(Long IdRevision);
    List<Revision> listarRevisionesByPlaca(String placa_avion);
    Boolean updateRevisionById(Revision revision);
    Revision consultarRevisionById(Long id_revision);
}
