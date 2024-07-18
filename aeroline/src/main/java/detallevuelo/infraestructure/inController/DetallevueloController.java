package detallevuelo.infraestructure.inController;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;



// import java.awt.event.KeyAdapter;
// import java.awt.event.KeyEvent;



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
        
        panel.setPreferredSize(new Dimension(450, 120));
        /*      
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

        */

        // Añadir componentes Al window
        
        panel.add(codigoVueloLabel);
        panel.add(codigoVueloField);


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
              
                DetalleVuelo detalvuelo = detalleVueloUseCase.consultarDetalleVuelo(codigo_vuelo);

                // Crear el panel de detalles
                JPanel detailsPanel = new JPanel(new GridLayout(6, 2, 5, 5));
                detailsPanel.add(new JLabel("Empleado:"));
                detailsPanel.add(new JLabel(detalvuelo.getEmpleado()));
                detailsPanel.add(new JLabel("Numero_Vuelo:"));
                detailsPanel.add(new JLabel(detalvuelo.getNumero_Vuelo()));
                detailsPanel.add(new JLabel("Rol Empleado:"));
                detailsPanel.add(new JLabel(detalvuelo.getRolEmpleado()));
                detailsPanel.add(new JLabel("Aeropuerto Origen:"));
                detailsPanel.add(new JLabel(detalvuelo.getAeropuertoOrigen()));
                detailsPanel.add(new JLabel("Aeropuerto Destino:"));
                detailsPanel.add(new JLabel(detalvuelo.getAeropuertoDestino()));
                detailsPanel.add(new JLabel("Hora Salida:"));
                detailsPanel.add(new JLabel(detalvuelo.getHoraSalida()));
                detailsPanel.add(new JLabel("Hora Llegada:"));
                detailsPanel.add(new JLabel(detalvuelo.getHoraLlegada()));

                // Mostrar el panel de detalles en un JOptionPane
                JOptionPane.showMessageDialog(null, detailsPanel, "Detalle del Vuelo", JOptionPane.INFORMATION_MESSAGE);
            }catch(Exception e){
                JOptionPane.showMessageDialog(panel, "Error al consultar el detalle del vuelo", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        



        
        


        return detallevuelo;
        
    }

    
}
