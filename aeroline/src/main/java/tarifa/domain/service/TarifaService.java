package tarifa.domain.service;

import java.util.List;

import tarifa.domain.entity.Tarifa;

public interface TarifaService {
    List<Tarifa> listarTarifasByTrayecto (List<Integer> idPermisos);
}
