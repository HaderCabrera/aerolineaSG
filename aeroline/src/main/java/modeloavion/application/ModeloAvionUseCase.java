package modeloavion.application;



import java.util.List;

import modeloavion.domain.entity.ModeloAvion;
import modeloavion.domain.service.ModeloAvionService;

public class ModeloAvionUseCase {
    private final ModeloAvionService modeloAvionService;

    public ModeloAvionUseCase(ModeloAvionService modeloAvionService) {
        this.modeloAvionService = modeloAvionService;
    }
    
    public List<ModeloAvion> listarModelosAvion(){
        List<ModeloAvion> lstModelosAvion = modeloAvionService.listarModelosAvion();
        return lstModelosAvion;
    }
}
