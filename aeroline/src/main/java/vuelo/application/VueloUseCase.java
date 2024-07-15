package vuelo.application;


import vuelo.domain.entity.vuelo;
import vuelo.domain.service.VueloService;

public class VueloUseCase {
    public  VueloService vueloService;

    public VueloUseCase(VueloService vueloService) {
        this.vueloService = vueloService;
    }

    public vuelo consultarInformacionVuelo(int id_vuelo){
        return vueloService.consultarInformacionVuelo(id_vuelo);
    }

    public vuelo consultarEscalasVuelo(int id_vuelo){
        return vueloService.consultarEscalasVuelo(id_vuelo);
    }
    public vuelo listarVuelos(){
        return vueloService.listarVuelos();
    }
    
    public vuelo actualizarVuelo(int id_cuelo){
        return vueloService.actualizarVuelo(id_cuelo);
    }

    public void eliminarVuelo(int id_vuelo){
        vueloService.eliminarVuelo(id_vuelo);
    }


}
