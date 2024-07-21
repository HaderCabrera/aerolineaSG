package modeloavion.infraestructure.inController;

import java.util.List;

import modeloavion.application.ModeloAvionUseCase;
import modeloavion.domain.entity.ModeloAvion;

public class ModeloAvionController {
    private final ModeloAvionUseCase modeloAvionUseCase;

    public ModeloAvionController(ModeloAvionUseCase modeloAvionUseCase) {
        this.modeloAvionUseCase = modeloAvionUseCase;
    }
    
    public List<ModeloAvion> listarModelosAvion(){
        List<ModeloAvion> lstModelosAvion = modeloAvionUseCase.listarModelosAvion();
        return lstModelosAvion;
    }
}
