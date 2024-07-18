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

}
