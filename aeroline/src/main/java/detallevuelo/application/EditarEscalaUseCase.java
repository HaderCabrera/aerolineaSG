package detallevuelo.application;

import detallevuelo.domain.entity.DetalleVuelo;
import detallevuelo.domain.service.DetalleVueloService;

public class EditarEscalaUseCase {
    private DetalleVueloService detalleVueloService;

    

    public EditarEscalaUseCase(DetalleVueloService detalleVueloService) {
        this.detalleVueloService = detalleVueloService;
    }

    public DetalleVuelo editarEscalaVuelo(int id_escala){
        return detalleVueloService.editarEscalaVuelo(id_escala);
    }
}
