package tipoDocumento.domain.service;

import java.util.List;

import tipoDocumento.domain.entity.TipoDocumento;

public interface TipoDocumentoService {
    void crearTipoDocumento(TipoDocumento tipoDocumento);
    List<TipoDocumento> listarTipoDocumento();
    TipoDocumento consultarTipoDocumentoById(Long idTipoDocumento);
    Boolean updateTipoDocumento(TipoDocumento tipoDocumento); 
    Boolean eliminarTipoDocumentoById(Long idTipoDocumento);
}
