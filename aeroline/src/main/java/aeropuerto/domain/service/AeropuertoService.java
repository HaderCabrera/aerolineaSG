package aeropuerto.domain.service;

import aeropuerto.domain.entity.Aeropuerto;

public interface AeropuertoService {
    Boolean  registrarAeropuerto(Aeropuerto aeropuerto);
    Aeropuerto consultarAeropuerto(Long id_aeropuerto);
}