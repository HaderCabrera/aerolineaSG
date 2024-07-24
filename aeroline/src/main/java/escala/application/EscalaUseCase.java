package escala.application;

import java.util.List;

import escala.domain.entity.Escala;
import escala.domain.service.EscalaService;

public class EscalaUseCase {
    private final EscalaService escalaService;

    public EscalaUseCase(EscalaService escalaService) {
        this.escalaService = escalaService;
    }

    public List<Escala> consultarEscalarByDescripcion (Long id_trayecto){
        return escalaService.consultarEscalarByDescripcion(id_trayecto);
    }

    public Boolean actualizarEscala(Escala escala){
        return escalaService.actualizarEscala(escala);
    }

    public Escala obtenerEscalaById(Long idEscala){
        return escalaService.obtenerEscalaById(idEscala);
    }
}
