package escala.infraestructure.inController;


import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


import escala.application.EscalaUseCase;
import escala.domain.entity.Escala;
import tipoDocumento.application.TipoDocumentoUseCase;
import tipoDocumento.domain.entity.TipoDocumento;
import tipoDocumento.domain.service.TipoDocumentoService;
import tipoDocumento.infraestructure.inController.TipoDocumentoController;
import tipoDocumento.infraestructure.outRepository.TipoDocumentoRepository;

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

        Boolean actualizacion = escalaUseCase.actualizarEscala(escala);
        if (actualizacion) {
            JOptionPane.showMessageDialog(null, "Actualizaciòn exitosa!","Confimacion", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public Escala obtenerDatosEscalaNueva(){
        Escala escala = null;
        JPanel panel = new JPanel(new GridLayout(8, 2, 10, 5));
        
        // Crear etiquetas y agregarlas al panel
        JLabel lblVuelo = new JLabel("Id vuelo:");
        JTextField txtVuelo = new JTextField();
    
        JLabel lblTrayecto = new JLabel("Id trayecto:");
        JTextField txtTrayecto = new JTextField();

        JLabel lblOrigen = new JLabel("Origen:");
        JTextField txtOrigen = new JTextField();

        JLabel lblDestino = new JLabel("Destino:");
        JTextField txtDestino = new JTextField();

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
            escala = new Escala();
            Long id_vuelo = Long.valueOf(txtVuelo.getText());
            Long id_trayecto = Long.valueOf(txtTrayecto.getText());
            String origen = txtOrigen.getText();
            String destino = txtDestino.getText();

            try {
                escala.setId_vuelo(id_vuelo);
                escala.setId_trayecto(id_trayecto);
                escala.setInicio(origen);
                escala.setDestino(destino);
                
            } catch (Exception e) {
                System.out.println("Formatos invalidos, Try Again!" + e);
            }
        
        }
        return escala;
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
