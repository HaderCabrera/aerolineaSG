package escala.infraestructure.inController;


import java.util.ArrayList;
import java.util.List;

import escala.application.EscalaUseCase;
import escala.domain.entity.Escala;

public class EscalaController {
    private final EscalaUseCase escalaUseCase;

    public EscalaController(EscalaUseCase escalaUseCase) {
        this.escalaUseCase = escalaUseCase;
    }
    
    public List<Escala> validarTipoTarifasForTrayecto(Long id_trayecto){
        List<Escala> lstEscalasByDescripcion = new ArrayList<>();
        lstEscalasByDescripcion = escalaUseCase.consultarEscalarByDescripcion(id_trayecto);
        return lstEscalasByDescripcion;
    }

}
