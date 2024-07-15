package avion.infraestructure.outRepository;

import avion.domain.entity.Avion;
import avion.domain.service.AvionService;

public class AvionRepository implements AvionService{

    @Override
    public boolean registrarAvion(Avion avion) {
        //LOGICA DE AGREGAR AVION A LA BASE DE DATOS
        return true;
    }

}
