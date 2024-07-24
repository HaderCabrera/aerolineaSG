package escala.infraestructure.inController;


import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


import escala.application.EscalaUseCase;
import escala.domain.entity.Escala;


public class EscalaController {
    private final EscalaUseCase escalaUseCase;

    public EscalaController(EscalaUseCase escalaUseCase) {
        this.escalaUseCase = escalaUseCase;
    }
    
    public List<Escala> validarTipoTarifasForTrayecto(Long id_trayecto){
        List<Escala> lstEscalasByDescripcion = new ArrayList<>();
        lstEscalasByDescripcion = escalaUseCase.consultarEscalarByDescripcion(id_trayecto);
        return lstEscalasByDescripcion;
    }

    public void actualizarEscala(){
        Long idEscala = obtenerIdEscala();

        Escala escala = escalaUseCase.obtenerEscalaById(idEscala);


        escala = obtenerDatosEscalaNueva(escala);
        if (escala != null) {
            Boolean actualizacion = escalaUseCase.actualizarEscala(escala);
            System.out.println("PASA PARA ACA");
            if (actualizacion) {
                JOptionPane.showMessageDialog(null, "Actualizaciòn exitosa!","Confimacion", JOptionPane.INFORMATION_MESSAGE);
            } else JOptionPane.showMessageDialog(null, "Actualizaciòn rechazada!","Denied", JOptionPane.WARNING_MESSAGE);
        }  
    }

    public Escala obtenerDatosEscalaNueva(Escala escala){
        Escala escalaN = null;
        JPanel panel = new JPanel(new GridLayout(8, 2, 10, 5));
        panel.setPreferredSize(new Dimension(400,300));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 80, 20, 80));

        // Crear etiquetas y agregarlas al panel
        JLabel lblVuelo = new JLabel("Id vuelo:");
        JTextField txtVuelo = new JTextField();
        txtVuelo.setText(String.valueOf(escala.getId_vuelo()));

        JLabel lblTrayecto = new JLabel("Id trayecto:");
        JTextField txtTrayecto = new JTextField();
        txtTrayecto.setText(String.valueOf(escala.getId_trayecto()));

        JLabel lblOrigen = new JLabel("Origen:");
        JTextField txtOrigen = new JTextField();
        txtOrigen.setText(escala.getInicio());

        JLabel lblDestino = new JLabel("Destino:");
        JTextField txtDestino = new JTextField();
        txtDestino.setText(escala.getDestino());

        //VALIDACIONES DE ENTERO
        txtTrayecto.addKeyListener(new KeyAdapter() {
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
        txtVuelo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
                    JOptionPane.showMessageDialog(panel, "Campo solo numeros", "Error", JOptionPane.ERROR_MESSAGE);
                    e.consume(); // Ignorar la tecla no numérica
                }
            }
        });
        Font font2 = new Font("Monospaced", Font.BOLD, 14); 
        Font font = new Font("Monospaced", Font.BOLD, 20); 
        lblVuelo.setFont(font);
        lblTrayecto.setFont(font);
        lblOrigen.setFont(font);
        lblDestino.setFont(font);
        txtVuelo.setFont(font2);
        txtTrayecto.setFont(font2);
        txtOrigen.setFont(font2);
        txtDestino.setFont(font2);

        panel.add(lblVuelo);
        panel.add(txtVuelo);
        panel.add(lblTrayecto);  
        panel.add(txtTrayecto);
        panel.add(lblOrigen); 
        panel.add(txtOrigen);  
        panel.add(lblDestino); 
        panel.add(txtDestino);


        // Mostrar el panel en un JOptionPane
        int option = JOptionPane.showConfirmDialog(
            null, 
            panel, 
            "Airline, Hight All  The Time!", 
            JOptionPane.CLOSED_OPTION, 
            JOptionPane.PLAIN_MESSAGE
        );       
        
        // Manejar la entrada del usuario
        if (option == JOptionPane.OK_OPTION) {
            escalaN = new Escala();
            Long id_vuelo = Long.valueOf(txtVuelo.getText());
            Long id_trayecto = Long.valueOf(txtTrayecto.getText());
            String origen = txtOrigen.getText();
            String destino = txtDestino.getText();

            try {
                escalaN.setId_vuelo(id_vuelo);
                escalaN.setId_trayecto(id_trayecto);
                escalaN.setInicio(origen);
                escalaN.setDestino(destino);
                escalaN.setId_escala(escala.getId_escala());
                
            } catch (Exception e) {
                System.out.println("Formatos invalidos, Try Again!" + e);
            }
        
        } else return null;
        return escalaN;
    }

    public Long obtenerIdEscala(){
        JPanel panel = new JPanel(new GridLayout(1, 1, 5, 1));

        JLabel txtIdCliente = new JLabel("Id escala:");
        JTextField lblIdCliente = new JTextField();
        lblIdCliente.setFont(new Font("Monospaced", Font.BOLD, 12));

        panel.setPreferredSize(new Dimension(250, 30));

        //VALIDACIONES DE ENTERO
        lblIdCliente.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
                    JOptionPane.showMessageDialog(panel, "Caracter no valido!", "Error", JOptionPane.ERROR_MESSAGE);
                    e.consume(); // Ignorar la tecla no numérica
                } 
            }
        });    
        //Agreganmos elementos al panel
        panel.add(txtIdCliente);
        panel.add(lblIdCliente);

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
            try {
                String idCliente = lblIdCliente.getText();
                return Long.parseLong(idCliente); 
            } catch (Exception e) {
                return null;
            }
        } else {
            return 151841511L;
        }
    }
}
