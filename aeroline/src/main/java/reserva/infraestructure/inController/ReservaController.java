package reserva.infraestructure.inController;


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cliente.application.ClienteUseCase;
import cliente.domain.entity.Cliente;
import cliente.domain.service.ClienteService;
import cliente.infraestructure.inController.ClienteController;
import cliente.infraestructure.outRepository.ClienteRepository;
import reserva.application.ReservaUseCase;
import reserva.domain.entity.Reserva;
import tarifa.application.TarifaUseCase;
import tarifa.domain.entity.Tarifa;
import tarifa.domain.service.TarifaService;
import tarifa.infraestructure.inController.TarifaController;
import tarifa.infraestructure.outRepository.TarifaRepository;
import detallevuelo.domain.entity.DetalleVuelo;
import detallevuelo.domain.service.DetalleVueloService;
import detallevuelo.application.DetalleVueloUseCase;
import detallevuelo.infraestructure.inController.DetallevueloController;
import detallevuelo.infraestructure.outRepository.DetalleVueloRepository;
import escala.application.EscalaUseCase;
import escala.domain.entity.Escala;
import escala.domain.service.EscalaService;
import escala.infraestructure.inController.EscalaController;
import escala.infraestructure.outRepository.EscalaRepository;

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
        // Configuración del JFrame principal
        JFrame ventanaReserva = new JFrame("Gestionar Reserva");
        ventanaReserva.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaReserva.setSize(500, 150);
        ventanaReserva.setLocationRelativeTo(null);

        //FORMULARIO 1
        JPanel panel1 = new JPanel(new GridLayout(2, 2, 10, 5));
        JLabel lblIdCliente = new JLabel("Id cliente:");
        JTextField txtIdCliente = new JTextField();
        // Crear etiquetas y agregarlas al panel
        JLabel lblFecha = new JLabel("Fecha Registro:");
        JTextField txtFecha = new JTextField();
        txtFecha.setText("0000-00-00");
        panel1.add(lblIdCliente);
        panel1.add(txtIdCliente);
        panel1.add(lblFecha);
        panel1.add(txtFecha);
        panel1.setPreferredSize(new Dimension(200, 100));

        //FORMULARIO 2
        JPanel panel2 = new JPanel(new GridLayout(1, 2, 10, 5));
        JLabel lblTrayecto = new JLabel("Trayecto:");

        List<String> lstDescripcionesTrayecto = new ArrayList<>();
        lstDescripcionesTrayecto.add("Selecciona un trayecto");
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

        panel2.add(lblTrayecto);
        panel2.add(descripcionComboBox);
        panel2.setPreferredSize(new Dimension(600, 100));

        //FORMULARIO 3

        // Panel principal que contendrá los formularios
        JPanel panelPrincipal = new JPanel(new CardLayout()); // Usamos CardLayout para gestionar los formularios
        panelPrincipal.add(panel1);
        panelPrincipal.add(panel2);
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Margen de 20 píxeles en todos los lados

        //INSTANCIA R HEXAGONAL DE CLIENTE
        ClienteService clienteService = new ClienteRepository();
        ClienteUseCase clienteUseCase = new ClienteUseCase(clienteService);
        ClienteController clienteController = new ClienteController(clienteUseCase);

        //INSTANCIAR HEXAGONAL ESCALA
        EscalaService escalaService = new EscalaRepository();
        EscalaUseCase escalaUseCase = new EscalaUseCase(escalaService);
        EscalaController escalaController = new EscalaController(escalaUseCase);

        //INSTANCIAR TARIFA
        TarifaService tarifaService = new TarifaRepository();
        TarifaUseCase tarifaUseCase  = new TarifaUseCase(tarifaService);
        TarifaController tarifaController = new TarifaController(tarifaUseCase);

        //Botón para cambiar al siguiente formulario
        JButton btnSiguiente = new JButton("Siguiente");
        btnSiguiente.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // Cambiar al siguiente formulario usando CardLayout
                Cliente cliente = new Cliente();
                cliente = clienteUseCase.consultarCliente(Long.parseLong(txtIdCliente.getText()));
                int validarTarifas = 0;
                if (cliente != null && descripcionComboBox.getSelectedItem().toString() != "Selecciona un trayecto") {
                    //SACAR ID DEL TRAYECTO SELECCIONADO
                   DetalleVuelo trayecto = detalleVueloController.obtenerTrayectoByDescripcion(descripcionComboBox.getSelectedItem().toString());
                   List<Escala> lstEscalasByDescripcion = escalaController.validarTipoTarifasForTrayecto(Long.valueOf(trayecto.getId_trayecto()));

                   if (lstEscalasByDescripcion.size() > 2) {
                        //LLAMAR A LISTAR TARIFAS ESCALAS;
                        for (Escala escala : lstEscalasByDescripcion) {
                            if (escala.getDestino().equals(trayecto.getDestino_tracyecto()) && escala.getInicio().equals(trayecto.getOrigen_trayecto())) {
                                validarTarifas = 3;
                            } 
                        }
                    } else if (lstEscalasByDescripcion.size() == 1) {
                        validarTarifas = 2;
                    } else validarTarifas = 1;

                    //INSTANCIAR TARIFA
                    List<Tarifa> lstTarifasValidades = tarifaController.listarTarifasByTrayecto(validarTarifas);
                    System.out.println("CANTIDAD DE TARIFAS PARA ESE VUELO: " + lstTarifasValidades.size());

                } else System.out.println("CLIENTE NOE XISTE");
 
                CardLayout cardLayout = (CardLayout) panelPrincipal.getLayout();
                cardLayout.next(panelPrincipal);
            }
        });   

        // Configurar el JFrame principal
        ventanaReserva.getContentPane().setLayout(new BorderLayout());
        ventanaReserva.getContentPane().add(panelPrincipal, BorderLayout.CENTER);
        ventanaReserva.getContentPane().add(btnSiguiente, BorderLayout.SOUTH);
        // Mostrar el JFrame
        // Revalidar y redibujar el JFrame para asegurarse de que los cambios sean visibles
        ventanaReserva.revalidate();
        ventanaReserva.repaint();
        ventanaReserva.setVisible(true);

        return null;
    }
}
