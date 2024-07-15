package avion.infraestructure.inController;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import avion.application.AvionUseCase;
import avion.domain.entity.Avion;

public class AvionController {
    private AvionUseCase avionUseCase;

    public AvionController(AvionUseCase avionUseCase) {
        this.avionUseCase = avionUseCase;
    }

    /*Metodos*/

    public void registrarAvion(){
        Avion avion = solicitarDatosRegistro();
        avionUseCase.registrarAvion(avion);
    }

    public Avion solicitarDatosRegistro(){

        Avion avion = new Avion();

         //Crear los componentes
         JPanel panel = new JPanel(new GridLayout(5, 2, 5, 1));
         JLabel placaLabel = new JLabel("Placa de identificación:");
         JTextField placaField = new JTextField();
         JLabel capacidadLabel = new JLabel("Capacidad:");
         JTextField capacidadField = new JTextField();
         JLabel fechaLabel = new JLabel("Fabricado (AAAA-MM-DD):");
         JTextField fechaField = new JTextField();
         JLabel estadoLabel = new JLabel("Id estado:");
         JTextField estadoField = new JTextField();
         JLabel modeloLabel = new JLabel("Id modelo:");
         JTextField modeloField = new JTextField();
         panel.setPreferredSize(new Dimension(450, 120));

         // Añadir los componentes al panel
         panel.add(placaLabel);
         panel.add(placaField);
         panel.add(capacidadLabel);
         panel.add(capacidadField);
         panel.add(fechaLabel);
         panel.add(fechaField);
         panel.add(estadoLabel);
         panel.add(estadoField);
         panel.add(modeloLabel);
         panel.add(modeloField);
         // Mostrar el panel en un JOptionPane
         int option = JOptionPane.showConfirmDialog(
             null, 
             panel, 
             "Airline, Hight All  The Time!", 
             JOptionPane.OK_CANCEL_OPTION, 
             JOptionPane.QUESTION_MESSAGE
         );
 
        // Manejar la entrada del usuario
        if (option == JOptionPane.OK_OPTION) {

            String placa_identificacion = placaField.getText();
            String capacidad = capacidadField.getText();
            String dateString = fechaField.getText();
            String id_estado = estadoField.getText();
            String id_modelo = modeloField.getText();
            try {
                avion.setPlaca_identificacion(placa_identificacion);
                avion.setCapacidad(Integer.parseInt(capacidad));
                avion.setId_estado(Integer.parseInt(id_estado));
                avion.setId_modelo(Integer.parseInt(id_modelo));
                
                String dateFormatPattern = "yyyy-MM-dd";
                SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatPattern);
                try {
                    // Convertir el String a java.util.Date
                    Date utilDate = dateFormat.parse(dateString);
    
                    // Convertir java.util.Date a java.sql.Date
                    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
    
                    // Aquí puedes enviar `sqlDate` a la base de datos
                    avion.setFabricacion_fecha(sqlDate);
    
                } catch (Exception e) {
                    System.out.println("Formato de fecha incorrecto, lea mano!");
                }
            } catch (Exception e) {
                System.out.println("Formatos invalidos, Try Again!" + e);
            }
          
        } else {
            System.out.println("Registro de avión cancelado.");
        }
        
        return avion;
    }

}
