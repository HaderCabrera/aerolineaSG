package avion.domain.service;

import avion.domain.entity.Avion;

public interface AvionService {
    boolean registrarAvion(Avion avion);
    Avion consultarAvionByPlaca(String placa);
    Boolean updateAvion(Avion avion);
    Boolean eliminarAvionByPlaca(String placa);
}
