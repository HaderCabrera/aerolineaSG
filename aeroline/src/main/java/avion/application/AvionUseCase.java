package avion.application;


import avion.domain.entity.Avion;
import avion.domain.service.AvionService;


public class AvionUseCase {
    private final AvionService avionService;

    public AvionUseCase(avion.domain.service.AvionService avionService) {
        this.avionService = avionService;
    }

    /*Llamado a los servicios */
    public boolean registrarAvion(Avion avion) {
        Boolean seAgrego;
        seAgrego = avionService.registrarAvion(avion);
        return seAgrego;
    }
    public Avion consultarAvionByPlaca(String placa){
        Avion avion = avionService.consultarAvionByPlaca(placa);
        return avion;
    }
    public Boolean updateAvion(Avion avion){
        Boolean confirmacion = avionService.updateAvion(avion);
        return confirmacion;
    }

    public Boolean eliminarAvionByPlaca(String placa){
        Boolean confirmacion = avionService.eliminarAvionByPlaca(placa);
        return confirmacion;
    }
}
