package cliente.infraestructure.inController;

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

import cliente.application.ClienteUseCase;
import cliente.domain.entity.Cliente;
import revisionEstado.application.RevisionEstadoUseCase;
import revisionEstado.domain.entity.RevisionEstado;
import revisionEstado.domain.service.RevisionEstadoService;
import revisionEstado.infraestructure.inController.RevisionEstadoController;
import revisionEstado.infraestructure.outRepository.RevisionEstadoRepository;
import tipoDocumento.application.TipoDocumentoUseCase;
import tipoDocumento.domain.entity.TipoDocumento;
import tipoDocumento.domain.service.TipoDocumentoService;
import tipoDocumento.infraestructure.inController.TipoDocumentoController;
import tipoDocumento.infraestructure.outRepository.TipoDocumentoRepository;

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

    public void updateCliente() {
        Long idCliente = obtenerDatosConsulta();
        Cliente cliente = clienteUseCase.consultarCliente(idCliente);
        Cliente clienteUpdate = obtenerClienteModificado(cliente);
    }

    public Long obtenerDatosConsulta(){

        JPanel panel = new JPanel(new GridLayout(1, 1, 5, 1));

        JLabel txtIdCliente = new JLabel("Id cliente:");
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
        JLabel txtIdCliente = new JLabel(String.valueOf(cliente.getId_cliente()));

        JLabel lblDocumento = new JLabel("Documento:");
        JLabel txtDocumento = new JLabel(String.valueOf(cliente.getDocumento()));
       
        JLabel lblNombre1 = new JLabel("Nombre 1:");
        JLabel txtNombre1 = new JLabel(cliente.getNombre1());

        JLabel lblNombre2 = new JLabel("Nombre 2:");
        JLabel txtNombre2 = new JLabel(cliente.getNombre2());

        JLabel lblApellidos = new JLabel("Apellidos:");
        JLabel txtApellidos = new JLabel(cliente.getApellidos());

        JLabel lblFechaNacimiento = new JLabel("Fecha De Nacimiento:");
        JLabel txtFechaNacimiento = new JLabel(cliente.getFecha_nacimiento());

        JLabel lblEmail = new JLabel("Email:");
        JLabel txtEmail = new JLabel(cliente.getEmail());

        JLabel lblIdTipoDocumento = new JLabel("Documento:");
        JLabel txtIdTipoDocumento = new JLabel(String.valueOf(cliente.getDocumento()));

        panel.add(lblIdCliente);
        panel.add(txtIdCliente);
        panel.add(lblDocumento);  
        panel.add(txtDocumento);
        panel.add(lblNombre1); 
        panel.add(txtNombre1);  
        panel.add(lblNombre2);   
        panel.add(txtNombre2);
        panel.add(lblApellidos); 
        panel.add(txtApellidos);
        panel.add(lblFechaNacimiento); 
        panel.add(txtFechaNacimiento);
        panel.add(lblEmail);
        panel.add(txtEmail);  
        panel.add(lblIdTipoDocumento); 
        panel.add(txtIdTipoDocumento);

        // Mostrar el panel en un JOptionPane
        JOptionPane.showConfirmDialog(
            null, 
            panel, 
            "Airline, Hight All  The Time!", 
            JOptionPane.CLOSED_OPTION, 
            JOptionPane.PLAIN_MESSAGE
        );

    }

    public Cliente obtenerClienteModificado(Cliente cliente){
        JPanel panel = new JPanel(new GridLayout(8, 2, 10, 5));

        // Crear etiquetas y agregarlas al panel
        JLabel lblDocumento = new JLabel("Documento:");
        JTextField txtDocumento = new JTextField();
        txtDocumento.setText(String.valueOf(cliente.getDocumento()));
    
        JLabel lblNombre1 = new JLabel("Nombre 1:");
        JTextField txtNombre1 = new JTextField();
        txtNombre1.setText(cliente.getNombre1());

        JLabel lblNombre2 = new JLabel("Nombre 2:");
        JTextField txtNombre2 = new JTextField();
        txtNombre2.setText(cliente.getNombre2());

        JLabel lblApellidos = new JLabel("Apellidos:");
        JTextField txtApellidos = new JTextField();
        txtApellidos.setText(cliente.getApellidos());

        JLabel lblFecha = new JLabel("Fecha Nacimiento:");
        JTextField txtFecha = new JTextField();
        txtFecha.setText(cliente.getFecha_nacimiento());

        JLabel lblEmail = new JLabel("Fecha Nacimiento:");
        JTextField txtEmail = new JTextField();
        txtEmail.setText(cliente.getEmail());

        JLabel lblTipo = new JLabel("Tipo Documento:");




        //GESTION PARA TIPO DE DOCUMENTPO
        List<String> lstTipos = new ArrayList<>();

        TipoDocumentoService tipoDocumentoService = new TipoDocumentoRepository();
        TipoDocumentoUseCase tipoDocumentoUseCase = new TipoDocumentoUseCase(tipoDocumentoService);
        TipoDocumentoController tipoDocumentoController = new TipoDocumentoController(tipoDocumentoUseCase);
        
        List<TipoDocumento> lstRevisionEstados = tipoDocumentoController.listarTipoDocumento();
        String[] opcionesTgs;

        //USANDO CONSUMER
        Consumer<TipoDocumento> getTipo = tipoDocumento -> lstTipos.add(tipoDocumento.getNombreDoc());
        lstRevisionEstados.forEach(getTipo);

        opcionesTgs = lstTipos.toArray(new String[0]);
        JComboBox<String> opTgsComboBox = new JComboBox<>(opcionesTgs);

        ////////////////////////////////////////////////////////

        panel.add(lblDocumento);
        panel.add(txtDocumento);
        panel.add(lblNombre1);  
        panel.add(txtNombre1);
        panel.add(lblNombre2); 
        panel.add(txtNombre2);  
        panel.add(lblApellidos); 
        panel.add(txtApellidos);
        panel.add(lblFecha); 
        panel.add(txtFecha);
        panel.add(lblEmail);
        panel.add(txtEmail);  
        panel.add(lblTipo); 
        panel.add(opTgsComboBox);

        // Mostrar el panel en un JOptionPane
        JOptionPane.showConfirmDialog(
            null, 
            panel, 
            "Airline, Hight All  The Time!", 
            JOptionPane.CLOSED_OPTION, 
            JOptionPane.PLAIN_MESSAGE
        );        
        return null;
    }
}
