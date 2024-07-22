package vuelo.application;

import java.util.List;

import vuelo.domain.service.VueloService;

public class VueloUseCase {
    private final VueloService vueloService;

    public VueloUseCase(VueloService vueloService) {
        this.vueloService = vueloService;
    }

    public List<Long> getIdAvionByIdTrayecto(Long id_trayecto){
        return vueloService.getIdAvionByIdTrayecto(id_trayecto);
    }
}
