package puesto.domain.service;

import java.util.List;

import puesto.domain.entity.Puesto;

public interface PuestoService {
    List<Puesto> lstPuestosByIdAvion(Long id_avion);
}
