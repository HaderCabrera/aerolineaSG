package avion.infraestructure.inController;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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

    /* Metodos */

    public void registrarAvion() {
        Avion avion = solicitarDatosRegistro();
        boolean confirmacion = avionUseCase.registrarAvion(avion);
        
        if (confirmacion) {
            String mensaje = "Registro Exitoso!";
            JOptionPane.showMessageDialog(null, mensaje, "Confirm", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String mensaje = "Registro Fallido!";   
            JOptionPane.showMessageDialog(null, mensaje, "Denied", JOptionPane.WARNING_MESSAGE);
        }

    }

    public Avion solicitarDatosRegistro() {

        Avion avion = new Avion();

        // Crear los componentes
        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 1));

        JLabel placaLabel = new JLabel("Placa de identificación:");
        JTextField placaField = new JTextField();
        placaField.setFont(new Font("Monospaced", Font.BOLD, 12));
        
        JLabel capacidadLabel = new JLabel("Capacidad:");
        JTextField capacidadField = new JTextField();
        capacidadField.setFont(new Font("Monospaced", Font.BOLD, 12));
    
        JLabel fechaLabel = new JLabel("Fabricado (AAAA-MM-DD):");
        JTextField fechaField = new JTextField();
        fechaField.setFont(new Font("Monospaced", Font.BOLD, 12));

        JLabel estadoLabel = new JLabel("Id estado:");
        JTextField estadoField = new JTextField();
        estadoField.setFont(new Font("Monospaced", Font.BOLD, 12));

        JLabel modeloLabel = new JLabel("Id modelo:");
        JTextField modeloField = new JTextField();
        modeloField.setFont(new Font("Monospaced", Font.BOLD, 12));
        panel.setPreferredSize(new Dimension(450, 120));

        //VALIDACIONES DE FECHA
        fechaField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE && c != '-') {
                    JOptionPane.showMessageDialog(panel, "Caracter  Ingreado Invalido!", "Error", JOptionPane.ERROR_MESSAGE);
                    e.consume(); // Ignorar la tecla no numérica
                } else if (fechaField.getText().length() >= 10) {
                    JOptionPane.showMessageDialog(panel, "No Se Puede Ingresar Mas Caracteres!", "Error", JOptionPane.ERROR_MESSAGE);
                    e.consume(); 
                }
            }
        });

        //VALIDACIONES DE ENTERO
        modeloField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
                    JOptionPane.showMessageDialog(panel, "Caracter no valido!", "Error", JOptionPane.ERROR_MESSAGE);
                    e.consume(); // Ignorar la tecla no numérica
                } 
            }
        });    
        //VALIDACIONES DE ENTERO
        estadoField.addKeyListener(new KeyAdapter() {
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
        capacidadField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
                    JOptionPane.showMessageDialog(panel, "Campo solo numeros", "Error", JOptionPane.ERROR_MESSAGE);
                    e.consume(); // Ignorar la tecla no numérica
                } 
            }
        });  

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
                JOptionPane.QUESTION_MESSAGE);

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
                avion.setFabricacion_fecha(dateString);
            } catch (Exception e) {
                System.out.println("Formatos invalidos, Try Again!" + e);
            }

        } else {
            System.out.println("Registro de avión cancelado.");
        }

        return avion;
    }

}
