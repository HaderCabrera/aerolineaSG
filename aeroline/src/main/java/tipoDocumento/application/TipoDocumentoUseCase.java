package tipoDocumento.application;

import tipoDocumento.domain.entity.TipoDocumento;
import tipoDocumento.domain.service.TipoDocumentoService;

public class TipoDocumentoUseCase {
    private final TipoDocumentoService tipoDocumentoService;
    
    public TipoDocumentoUseCase(TipoDocumentoService tipoDocumentoService) {
        this.tipoDocumentoService = tipoDocumentoService;
    }

    public void crearTipoDocumento(TipoDocumento tipoDocumento){
        tipoDocumentoService.crearTipoDocumento(tipoDocumento);
    }
}
