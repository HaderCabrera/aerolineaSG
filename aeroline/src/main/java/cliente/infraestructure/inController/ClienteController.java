package cliente.infraestructure.inController;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cliente.application.ClienteUseCase;
import cliente.domain.entity.Cliente;

public class ClienteController {
    private final ClienteUseCase clienteUseCase;

    public ClienteController(ClienteUseCase clienteUseCase) {
        this.clienteUseCase = clienteUseCase;
    }
    
    public void consultarCliente() {
        Long idCliente = obtenerDatosConsulta();
        Cliente cliente = clienteUseCase.consultarCliente(idCliente);
        mostrarDatosCliente(cliente);
    }

    public Long obtenerDatosConsulta(){

        JPanel panel = new JPanel(new GridLayout(1, 1, 5, 1));

        JLabel txtIdCliente = new JLabel("Id cliente:");
        JTextField lblIdCliente = new JTextField();
        lblIdCliente.setFont(new Font("Monospaced", Font.BOLD, 12));

        panel.setPreferredSize(new Dimension(250, 60));

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
            String idCliente = lblIdCliente.getText();

            return Long.parseLong(idCliente);            

        } else {
            JOptionPane.showMessageDialog(panel, "Consulta cancelada", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return 0L;
    }

    public void mostrarDatosCliente(Cliente cliente){

        JPanel panel = new JPanel(new GridLayout(8, 2, 10, 5));

        // Crear etiquetas y agregarlas al panel
        JLabel lblIdCliente = new JLabel("Id Cliente:");
        panel.add(lblIdCliente);
        JLabel txtIdCliente = new JLabel(String.valueOf(cliente.getId_cliente()));
        panel.add(txtIdCliente);

        JLabel lblDocumento = new JLabel("Documento:");
        panel.add(lblDocumento);    
        JLabel txtDocumento = new JLabel(String.valueOf(cliente.getDocumento()));
        panel.add(txtDocumento);

        JLabel lblNombre1 = new JLabel("Nombre 1:");
        panel.add(lblNombre1);    
        JLabel txtNombre1 = new JLabel(cliente.getNombre1());
        panel.add(txtNombre1);

        JLabel lblNombre2 = new JLabel("Nombre 2:");
        panel.add(lblNombre2);    
        JLabel txtNombre2 = new JLabel(cliente.getNombre2());
        panel.add(txtNombre2);

        JLabel lblApellidos = new JLabel("Apellidos:");
        panel.add(lblApellidos);    
        JLabel txtApellidos = new JLabel(cliente.getApellidos());
        panel.add(txtApellidos);

        JLabel lblApellidos = new JLabel("Apellidos:");
        panel.add(lblApellidos);    
        JLabel txtApellidos = new JLabel(cliente.getApellidos());
        panel.add(txtApellidos);

    }
}
