package avion.infraestructure.inController;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


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

    public void consultarAvionByPlaca(){
        String placa = solicitarPlacaAvion();
        Avion avion = avionUseCase.consultarAvionByPlaca(placa);
        if (avion != null) {
            mostrarDatosAvion(avion);
        }  else {
            JOptionPane.showMessageDialog(null, "Avión no encontrado!", "Error De Consulta", JOptionPane.ERROR_MESSAGE);
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

    public String  solicitarPlacaAvion(){
        JPanel panel = new JPanel(new GridLayout(1, 1, 5, 1));

        JLabel txtPlacaAvion = new JLabel("Placa Avión:");
        JTextField lblPlacaAvion = new JTextField();
        lblPlacaAvion.setFont(new Font("Monospaced", Font.BOLD, 12));

        panel.setPreferredSize(new Dimension(250, 30));
    
        //Agreganmos elementos al panel
        panel.add(txtPlacaAvion);
        panel.add(lblPlacaAvion);

        // Mostrar el panel en un JOptionPane
        int option = JOptionPane.showConfirmDialog(
            null, 
            panel, 
            "Airline, Hight All  The Time!", 
            JOptionPane.OK_CANCEL_OPTION, 
            JOptionPane.QUESTION_MESSAGE
        );

        //Tratar datos recolectados
        if (option == JOptionPane.OK_OPTION) {
            String placa = lblPlacaAvion.getText();
            return placa;        

        } else {
            JOptionPane.showMessageDialog(panel, "Consulta cancelada", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public void mostrarDatosAvion(Avion avion){
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 5));

        JLabel txtPlaca = new JLabel("Placa:");
        JLabel txtPlacaValor = new JLabel(avion.getPlaca_identificacion());

        JLabel txtCapacidad = new JLabel("Documento:");
        JLabel txtCapacidadValor = new JLabel(String.valueOf(avion.getCapacidad()));

        JLabel txtFecha = new JLabel("Fecha Fabricación:");
        JLabel txtFechaValor = new JLabel(avion.getFabricacion_fecha());

        JLabel txtModelo = new JLabel("Modelo:");
        JLabel txtModeloValor = new JLabel(avion.getModelo());

        JLabel txtEstado = new JLabel("Estado:");
        JLabel txtEstadoValor = new JLabel(avion.getEstado());

        panel.add(txtPlaca);
        panel.add(txtPlacaValor);
        panel.add(txtCapacidad);
        panel.add(txtCapacidadValor);
        panel.add(txtFecha);
        panel.add(txtFechaValor);
        panel.add(txtModelo);
        panel.add(txtModeloValor);
        panel.add(txtEstado);
        panel.add(txtEstadoValor);

        // Mostrar el panel en un JOptionPane
        JOptionPane.showConfirmDialog(
            null, 
            panel, 
            "Airline, Hight All  The Time!", 
            JOptionPane.CLOSED_OPTION, 
            JOptionPane.PLAIN_MESSAGE
        );
    }
}
