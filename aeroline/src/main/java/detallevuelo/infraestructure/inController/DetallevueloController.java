package detallevuelo.infraestructure.inController;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

// import java.awt.event.KeyAdapter;
// import java.awt.event.KeyEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
      
        //VALIDACIONES DE ENTERO
        // codigoVueloField.addKeyListener(new KeyAdapter() {
        //     @Override
        //     public void keyTyped(KeyEvent e) {
        //         char c = e.getKeyChar();
        //         if (c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
        //             JOptionPane.showMessageDialog(panel, "sin espacios", "Error", JOptionPane.ERROR_MESSAGE);
        //             e.consume(); // Ignorar la tecla no numérica
        //         }
        //     }
        // });

        

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
                JButton delateButton = new JButton("Delate");
                JButton actualizar = new JButton("Refresh");
                delateButton.setPreferredSize(new Dimension(100, 30));
                // Function <String, JLabel> createLabel = (String dato) -> {
                // JLabel label =  new JLabel(dato);
                // return label; };
                // Crear el panel de detalles
                JPanel detailsPanel = new JPanel(new GridLayout(2, 8, 10, 5));

                String[] headers = {
                    "Empleado", "Numero_Vuelo", "Rol Empleado",
                    "Aeropuerto Origen", "Aeropuerto Destino", "Hora Salida",
                    "Hora Llegada"
                };
                
                // Añadir las etiquetas de cabecera
                for (String header : headers) {
                    JLabel label = new JLabel(header);
                    label.setPreferredSize(new Dimension(500, 3)); 
                    label.setBorder(BorderFactory.createLineBorder(Color.BLACK)); 
                    label.setFont(new Font("Monospaced", Font.BOLD, 12));
                    label.setHorizontalAlignment(SwingConstants.CENTER);
                    detailsPanel.add(label);
                }
                detailsPanel.add(actualizar);
        
                // Valores a mostrar
                String[] values = {
                    detalvuelo.getEmpleado(),
                    detalvuelo.getNumero_Vuelo(),
                    detalvuelo.getRolEmpleado(),
                    detalvuelo.getAeropuertoOrigen(),
                    detalvuelo.getAeropuertoDestino(),
                    detalvuelo.getHoraSalida(),
                    detalvuelo.getHoraLlegada()
                };

                for (String value : values) {
                    JLabel label = new JLabel(value);
                    label.setPreferredSize(new Dimension(500, 3)); 
                    label.setBorder(BorderFactory.createLineBorder(Color.BLACK)); 
                    label.setFont(new Font("Arial", Font.PLAIN, 12));
                    label.setHorizontalAlignment(SwingConstants.CENTER); 
                    label.setHorizontalAlignment(SwingConstants.LEFT);
                    detailsPanel.add(label);
                     
                }

                detailsPanel.add(delateButton);
                
                detailsPanel.setPreferredSize(new Dimension(1500, 100));

                
                delateButton.addActionListener(new ActionListener() {
                    
                    // HACER CONSULTA EN JAVA ELIMIAR DELETE mySQL
                    public void actionPerformed(ActionEvent e){
                        int result = JOptionPane.showConfirmDialog(null,
                        "¿Está seguro de que desea eliminar este elemento?", 
                        "Confirmación de Eliminación", 
                        JOptionPane.YES_NO_OPTION, 
                        JOptionPane.WARNING_MESSAGE);

                        if (result== JOptionPane.YES_OPTION) {
                            detalleVueloUseCase.eliminarDetalleVuelo(Integer.parseInt(detalvuelo.getNumero_Vuelo()));
                            JOptionPane.showMessageDialog(panel, "Elemento eliminado.");
                        }else if(result == JOptionPane.NO_OPTION){
                            JOptionPane.showMessageDialog(null, "Eliminación cancelada.");
                        }
                    
                        
                        //detalleVueloUseCase.editarEscalaVuelo();
                    }
                });


                
                

                // Mostrar el panel de detalles en un JOptionPane
                JOptionPane.showMessageDialog(null, detailsPanel, "Detalle del Vuelo", JOptionPane.INFORMATION_MESSAGE);
            }catch(Exception e){
                JOptionPane.showMessageDialog(panel, "Error al consultar el detalle del vuelo", "Error", JOptionPane.ERROR_MESSAGE);
            }


            
        }

        



        
        


        return detallevuelo;
        
    }

    
}
