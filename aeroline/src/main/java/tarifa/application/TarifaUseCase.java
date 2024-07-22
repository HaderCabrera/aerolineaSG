package tarifa.application;

import tarifa.domain.service.TarifaService;

public class TarifaUseCase {
    private final TarifaService tarifaService;

    public TarifaUseCase(TarifaService tarifaService) {
        this.tarifaService = tarifaService;
    }
    
}
