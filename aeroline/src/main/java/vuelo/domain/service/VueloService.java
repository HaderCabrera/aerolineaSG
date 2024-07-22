package vuelo.domain.service;

import java.util.List;

public interface VueloService {
    List<Long> getIdAvionByIdTrayecto(Long id_trayecto);
}
