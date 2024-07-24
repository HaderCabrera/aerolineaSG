package escala.domain.service;

import java.util.List;

import escala.domain.entity.Escala;

public interface EscalaService {
    List<Escala> consultarEscalarByDescripcion(Long id_trayecto);
    Escala obtenerEscalaById(Long idEscala);
    Boolean actualizarEscala(Escala escala);
}
