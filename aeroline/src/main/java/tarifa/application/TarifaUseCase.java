package tarifa.application;

import java.util.List;

import tarifa.domain.entity.Tarifa;
import tarifa.domain.service.TarifaService;

public class TarifaUseCase {
    private final TarifaService tarifaService;

    public TarifaUseCase(TarifaService tarifaService) {
        this.tarifaService = tarifaService;
    }

    public List<Tarifa> listarTarifasByTrayecto(int identificador){
        return tarifaService.listarTarifasByTrayecto(identificador);
    }
    
}
