package avion.application;

import avion.domain.entity.Avion;
import avion.domain.service.avionService;


public class RegistrarAvionUseCase {
    private avionService avionService;

    public RegistrarAvionUseCase(avion.domain.service.avionService avionService) {
        this.avionService = avionService;
    }
        public void execute(Avion avion) {
        avionService.registrarAvion(avion);
    }
}
