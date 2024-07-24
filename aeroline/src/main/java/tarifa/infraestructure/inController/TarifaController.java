package tarifa.infraestructure.inController;

import java.util.List;

import tarifa.application.TarifaUseCase;
import tarifa.domain.entity.Tarifa;

public class TarifaController {
    private final TarifaUseCase tarifaUseCase;

    public TarifaController(TarifaUseCase tarifaUseCase) {
        this.tarifaUseCase = tarifaUseCase;
    }

    public List<Tarifa> listarTarifasByTrayecto(int identificador){
        List<Tarifa> lstTarifas = null;

        lstTarifas = tarifaUseCase.listarTarifasByTrayecto(identificador);
        return lstTarifas;  
    }
}
