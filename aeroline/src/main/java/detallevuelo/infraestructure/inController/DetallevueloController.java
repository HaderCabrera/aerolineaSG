package detallevuelo.infraestructure.inController;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;



import detallevuelo.application.DetalleVueloUseCase;
import detallevuelo.domain.entity.DetalleVuelo;
import java.awt.*;

public class DetallevueloController {
    private DetalleVueloUseCase detalleVueloUseCase;

    public DetallevueloController(DetalleVueloUseCase detalleVueloUseCase) {
        this.detalleVueloUseCase = detalleVueloUseCase;
    }

    
    public DetalleVuelo consultarDetalleVuelo(){

        DetalleVuelo detalle = new DetalleVuelo();

        // Crear los componentes
        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 1));
        JLabel codigoVueloLabel = new JLabel("Placa de identificación:");
        JTextField codigoVueloField = new JTextField();
        panel.setPreferredSize(new Dimension(450, 120));
        codigoVueloField.setEditable(false);
        panel.add(codigoVueloLabel);
        // Imprimir informacion de Detalle
        int option = JOptionPane.showConfirmDialog(
                null,
                panel,
                "Airline, Hight All  The Time!",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE);

            
            System.out.println(option);
        
        


        return detalle;
        
    }
}
