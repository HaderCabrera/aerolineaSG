package vuelo.infraestructure.inController;

import java.util.List;

import vuelo.application.VueloUseCase;

public class VueloController {
    private final VueloUseCase vueloUseCase;

    public VueloController(VueloUseCase vueloUseCase) {
        this.vueloUseCase = vueloUseCase;
    }
    
    public List<Long> getIdAvionByIdTrayecto(Long id_trayecto){
        return vueloUseCase.getIdAvionByIdTrayecto(id_trayecto);
    }
}
