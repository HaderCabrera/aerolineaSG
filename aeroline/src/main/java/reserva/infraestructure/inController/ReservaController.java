package reserva.infraestructure.inController;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import puesto.application.PuestoUseCase;
import puesto.domain.entity.Puesto;
import puesto.domain.service.PuestoService;
import puesto.infraestructure.inController.PuestoController;
import puesto.infraestructure.outRepository.PuestoRepository;
import reserva.application.ReservaUseCase;
import reserva.domain.entity.Reserva;
import detallevuelo.application.DetalleVueloUseCase;
import detallevuelo.domain.entity.DetalleVuelo;
import detallevuelo.domain.service.DetalleVueloService;
import detallevuelo.infraestructure.inController.DetallevueloController;
import detallevuelo.infraestructure.outRepository.DetalleVueloRepository;

public class ReservaController {
    private final ReservaUseCase reservaUseCase;

    public ReservaController(ReservaUseCase reservaUseCase) {
        this.reservaUseCase = reservaUseCase;
    }

    public Boolean registrarReserva(){
        Reserva reserva = registrarDatosReserva();
        return null;
    }

    public Reserva registrarDatosReserva(){
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 5));
        
        // Crear etiquetas y agregarlas al panel
        JLabel lblFecha = new JLabel("Fecha Registro:");
        JTextField txtFecha = new JTextField();
        txtFecha.setText("0000-00-00");
        
        JLabel lblTrayecto = new JLabel("Trayecto:");
        List<String> lstDescripcionesTrayecto = new ArrayList<>();
        List<DetalleVuelo> lstTrayectos = new ArrayList<>();
        String[] descripcionesTgs;
        DetalleVueloService detalleVueloService = new DetalleVueloRepository();
        DetalleVueloUseCase detalleVueloUseCase = new DetalleVueloUseCase(detalleVueloService);
        DetallevueloController detalleVueloController = new DetallevueloController(detalleVueloUseCase);
        lstTrayectos = detalleVueloController.listarDescripcionesTrayecto();

        Consumer<DetalleVuelo> getDescripcion = trayecto -> lstDescripcionesTrayecto.add(trayecto.getDesc_trayecto());
        lstTrayectos.forEach(getDescripcion);

        descripcionesTgs = lstDescripcionesTrayecto.toArray(new String[0]);
        JComboBox<String> descripcionComboBox = new JComboBox<>(descripcionesTgs);

        JLabel lblIdCliente = new JLabel("Id cliente:");
        JTextField txtIdCliente = new JTextField();

        JLabel lblNombre2 = new JLabel("Puesto:");
        List<Long> lstPuestos = new ArrayList<>();
        PuestoService puestoService = new PuestoRepository();
        PuestoUseCase puestoUseCase = new PuestoUseCase(puestoService);
        PuestoController puestoController = new PuestoController(puestoUseCase);
        List<Puesto> lstPuestoByIdAvion = puestoController.listarPuestoByIdAvion(null);
        
        JLabel lblTipo = new JLabel("Tipo Documento:");
        //GESTION PARA TIPO DE DOCUMENTPO
        List<String> lstTipos = new ArrayList<>();
/* 
        TipoDocumentoService tipoDocumentoService = new TipoDocumentoRepository();
        TipoDocumentoUseCase tipoDocumentoUseCase = new TipoDocumentoUseCase(tipoDocumentoService);
        TipoDocumentoController tipoDocumentoController = new TipoDocumentoController(tipoDocumentoUseCase);
        
        List<TipoDocumento> lstTipoDocumentos = tipoDocumentoController.listarTipoDocumento();
        String[] opcionesTgs;

        //USANDO CONSUMER
        Consumer<TipoDocumento> getTipo = tipoDocumento -> lstTipos.add(tipoDocumento.getNombreDoc());
        lstTipoDocumentos.forEach(getTipo);

        opcionesTgs = lstTipos.toArray(new String[0]);
        JComboBox<String> opTgsComboBox = new JComboBox<>(opcionesTgs);
        /* 
        //VALIDACIONES DE ENTERO
        txtDocumento.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
                    JOptionPane.showMessageDialog(panel, "Campo solo numeros", "Error", JOptionPane.ERROR_MESSAGE);
                    e.consume(); // Ignorar la tecla no numérica
                }
            }
        });

        //VALIDACIONES DE FECHA
        txtFecha.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE && c != '-') {
                    JOptionPane.showMessageDialog(panel, "Caracter  Ingreado Invalido!", "Error", JOptionPane.ERROR_MESSAGE);
                    e.consume(); // Ignorar la tecla no numérica
                } else if (txtFecha.getText().length() >= 10) {
                    JOptionPane.showMessageDialog(panel, "No Se Puede Ingresar Mas Caracteres!", "Error", JOptionPane.ERROR_MESSAGE);
                    e.consume(); 
                }
            }
        });

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
        int option = JOptionPane.showConfirmDialog(
            null, 
            panel, 
            "Airline, Hight All  The Time!", 
            JOptionPane.CLOSED_OPTION, 
            JOptionPane.PLAIN_MESSAGE
        );       
        
        // Manejar la entrada del usuario
        if (option == JOptionPane.OK_OPTION) {

            String documento = txtDocumento.getText();
            String nombre1 = txtNombre1.getText();
            String nombre2 = txtNombre2.getText();
            String apellidos = txtApellidos.getText();
            String fecha_nacimiento = txtFecha.getText();
            String tipoDocumentos = opTgsComboBox.getSelectedItem().toString();
            String email = txtEmail.getText();

            try {
                cliente.setDocumento(Long.parseLong(documento));
                cliente.setNombre1(nombre1);
                cliente.setNombre2(nombre2);
                cliente.setApellidos(apellidos);
                cliente.setFecha_nacimiento(fecha_nacimiento);
                cliente.setEmail(email);
                lstTipoDocumentos.forEach(tipoDocumentoN -> {
                    if (tipoDocumentoN.getNombreDoc().equals(tipoDocumentos)) {
                        cliente.setId_tipo_documento(tipoDocumentoN.getId_tipo_documento());
                    }
                });
                

            } catch (Exception e) {
                System.out.println("Formatos invalidos, Try Again!" + e);
            }
        
        }else cliente.setEmail("cancelar");
         */
        return null;
    }
}
