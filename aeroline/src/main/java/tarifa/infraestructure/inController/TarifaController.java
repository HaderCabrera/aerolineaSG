package tarifa.infraestructure.inController;

import tarifa.application.TarifaUseCase;

public class TarifaController {
    private final TarifaUseCase tarifaUseCase;

    public TarifaController(TarifaUseCase tarifaUseCase) {
        this.tarifaUseCase = tarifaUseCase;
    }
    
}
