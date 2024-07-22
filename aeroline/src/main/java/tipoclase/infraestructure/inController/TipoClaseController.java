package tipoclase.infraestructure.inController;

import java.util.List;

import tipoclase.application.TipoClaseUseCase;
import tipoclase.domain.entity.TipoClase;

public class TipoClaseController {
    private final TipoClaseUseCase tipoClaseUseCase;

    public TipoClaseController(TipoClaseUseCase tipoClaseUseCase) {
        this.tipoClaseUseCase = tipoClaseUseCase;
    }

    public List<TipoClase> listarTiposClase(){
        return tipoClaseUseCase.listarTiposClase();
    }
}
