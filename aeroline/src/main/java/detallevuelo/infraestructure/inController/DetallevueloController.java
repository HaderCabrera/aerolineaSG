package detallevuelo.infraestructure.inController;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;



import detallevuelo.application.DetalleVueloUseCase;
import detallevuelo.domain.entity.DetalleVuelo;
import java.awt.*;

public class DetallevueloController {
    private DetalleVueloUseCase detalleVueloUseCase;

    public DetallevueloController(DetalleVueloUseCase detalleVueloUseCase) {
        this.detalleVueloUseCase = detalleVueloUseCase;
    }

    
    public DetalleVuelo consultarDetalleVuelo(){

    

        DetalleVuelo detallevuelo = new DetalleVuelo();

        // Crear los componentes
        JPanel panel = new JPanel(new GridLayout(4, 2, 5, 1));

        JLabel codigoVueloLabel = new JLabel("Codigo Vuelo:");
        JTextField codigoVueloField = new JTextField();
        codigoVueloField.setFont(new Font("Monospaced", Font.BOLD, 12));
        codigoVueloField.setEditable(false);
        
        panel.setPreferredSize(new Dimension(450, 120));

        //VALIDACIONES DE ENTERO
        codigoVueloField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
                    JOptionPane.showMessageDialog(panel, "Campo solo numeros", "Error", JOptionPane.ERROR_MESSAGE);
                    e.consume(); // Ignorar la tecla no numérica
                }
            }
        });

        //VALIDACIONES DE ENTERO
        codigoVueloField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
                    JOptionPane.showMessageDialog(panel, "Campo solo numeros", "Error", JOptionPane.ERROR_MESSAGE);
                    e.consume(); // Ignorar la tecla no numérica
                }
            }
        });



        // Añadir componentes Al window

        panel.add(codigoVueloLabel);


        // Capta la informacion al moento de dicir si
        // Imprimir informacion de Detalle
        int option = JOptionPane.showConfirmDialog(
                null,
                panel,
                "Airline, Hight All  The Time!",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE);

            
            System.out.println(option);

        if (option == JOptionPane.OK_OPTION) {
            
            String codigo_vuelo = codigoVueloField.getText();
            try{
                   detallevuelo.setId_detalle_vuelo(Integer.parseInt(codigo_vuelo));
            }catch(Exception e){

            }
        }


        
        


        return detallevuelo;
        
    }

    
}
