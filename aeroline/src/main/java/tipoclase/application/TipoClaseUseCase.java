package tipoclase.application;

import java.util.List;

import tipoclase.domain.entity.TipoClase;
import tipoclase.domain.service.TipoClaseService;

public class TipoClaseUseCase {
    private final TipoClaseService tipoClaseService;

    public TipoClaseUseCase(TipoClaseService tipoClaseService) {
        this.tipoClaseService = tipoClaseService;
    }

    public List<TipoClase> listarTiposClase(){
        return tipoClaseService.listarTiposClase();
    }
}
