package avion.infraestructure.inController;

import avion.application.AvionUseCase;

public class AvionController {
    private AvionUseCase avionUseCase;

    public AvionController(AvionUseCase avionUseCase) {
        this.avionUseCase = avionUseCase;
    }

    /*Metodos*/

    public void registrarAvion(){
        
    }


    public void ingresarDatosRegistro(){
        System.out.println("SOLICITAR DATOS");
        avionUseCase.registrarAvion(null);
    }



}
