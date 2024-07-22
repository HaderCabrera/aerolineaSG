package escala.application;

import escala.domain.service.EscalaService;

public class EscalaUseCase {
    private final EscalaService escalaService;

    public EscalaUseCase(EscalaService escalaService) {
        this.escalaService = escalaService;
    }

}
