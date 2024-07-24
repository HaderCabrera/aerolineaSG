package vuelo.domain.service;

import java.util.List;

import vuelo.domain.entity.Vuelo;

public interface VueloService {
    List<Long> getIdAvionByIdTrayecto(Long id_trayecto);
    List<Vuelo> obtenerIdVuelos();
    
}
