package tarifa.infraestructure.inController;

import java.util.ArrayList;
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
        List<Integer> lstIdTarifas = null;
        switch (identificador) {
            case 1:
                lstIdTarifas = new ArrayList<>();
                lstIdTarifas.add(1);
                lstIdTarifas.add(3);
                lstIdTarifas.add(5);
                lstTarifas = tarifaUseCase.listarTarifasByTrayecto(lstIdTarifas);
                break;
            case 2:
                lstIdTarifas = new ArrayList<>();
                lstIdTarifas.add(2);
                lstIdTarifas.add(4);
                lstIdTarifas.add(6);
                lstTarifas = tarifaUseCase.listarTarifasByTrayecto(lstIdTarifas);
                break;
            case 3:
                lstIdTarifas = new ArrayList<>();
                lstIdTarifas.add(1);
                lstIdTarifas.add(3);
                lstIdTarifas.add(5);
                lstIdTarifas.add(2);
                lstIdTarifas.add(4);
                lstIdTarifas.add(6);
                lstTarifas = tarifaUseCase.listarTarifasByTrayecto(lstIdTarifas);
                break;
            default:
                break;
        }

        return lstTarifas;
    }
}
