package reserva.infraestructure.inController;


import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import reserva.application.ReservaUseCase;
import reserva.domain.entity.Reserva;

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
        System.out.println("SI ENTRO");
        // Configuración del JFrame principal
        JFrame ventanaReserva = new JFrame("Gestionar Reserva");
        ventanaReserva.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaReserva.setSize(400, 300);
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

        //FORMULARIO 2
        JPanel panel2 = new JPanel(new GridLayout(2, 2, 10, 5));
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
        panel2.add(lblTrayecto);
        panel2.add(descripcionComboBox);

        // Panel principal que contendrá los formularios
        JPanel panelPrincipal = new JPanel(new CardLayout()); // Usamos CardLayout para gestionar los formularios
        panelPrincipal.add(panel1);
        panelPrincipal.add(panel2);

        //Botón para cambiar al siguiente formulario
        JButton btnSiguiente = new JButton("Siguiente");
        btnSiguiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cambiar al siguiente formulario usando CardLayout
                CardLayout cardLayout = (CardLayout) panelPrincipal.getLayout();
                cardLayout.next(panelPrincipal);
            }
        });   

        // Configurar el JFrame principal
        ventanaReserva.getContentPane().setLayout(new BorderLayout());
        ventanaReserva.getContentPane().add(panelPrincipal, BorderLayout.CENTER);
        ventanaReserva.getContentPane().add(btnSiguiente, BorderLayout.SOUTH);
        // Mostrar el JFrame

        ventanaReserva.setVisible(true);


        return null;
    }
}
