package vuelo.domain.service;

import vuelo.domain.entity.vuelo;

public interface VueloService{
    vuelo consultarInformacionVuelo(int id_vuelo);
    vuelo consultarEscalasVuelo(int id_vuelo);
    vuelo listarVuelos();
    vuelo actualizarVuelo(int id_cuelo);
    void eliminarVuelo(int id_vuelo);
}
