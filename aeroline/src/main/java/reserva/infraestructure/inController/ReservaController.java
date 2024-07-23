package reserva.infraestructure.inController;


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

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
        List<DetalleVuelo> lstTrayectos = new ArrayList<>();
        DetalleVueloService detalleVueloService = new DetalleVueloRepository();
        DetalleVueloUseCase detalleVueloUseCase = new DetalleVueloUseCase(detalleVueloService);
        DetallevueloController detalleVueloController = new DetallevueloController(detalleVueloUseCase);
        lstTrayectos = detalleVueloController.listarDescripcionesTrayecto();
    
        Cliente clienteValidado = reservaStepOne();
        if (clienteValidado != null) {
            String revisionSeleccionada = reservaStepTwo(lstTrayectos);
        } else JOptionPane.showMessageDialog(null, "Error al ingresar usuario","Error de formulario",JOptionPane.WARNING_MESSAGE);
        

        return null;
    }

    public Cliente reservaStepOne(){
        //INSTANCIA R HEXAGONAL DE CLIENTE
        Cliente cliente = new Cliente();

        ClienteService clienteService = new ClienteRepository();
        ClienteUseCase clienteUseCase = new ClienteUseCase(clienteService);
        //ClienteController clienteController = new ClienteController(clienteUseCase);

        //FORMULARIO 1
        JPanel panel1 = new JPanel(new GridLayout(1, 2, 1, 1));    
        panel1.setPreferredSize(new Dimension(600, 500));
        panel1.setBorder(BorderFactory.createEmptyBorder(230, 100, 230, 100));

        JLabel lblIdCliente = new JLabel("Id cliente:");
        JTextField txtIdCliente = new JTextField();

        // Establecer estilo de fuente
        Font font = new Font("Monospaced", Font.BOLD, 20); // Arial, negrita, tamaño 16
        txtIdCliente.setFont(font);
        lblIdCliente.setFont(font);

        panel1.add(lblIdCliente);
        panel1.add(txtIdCliente);

        
        // Mostrar el panel en un JOptionPane
        int option = JOptionPane.showConfirmDialog(
            null, 
            panel1, 
            "Formulario registros de reserva!", 
            JOptionPane.CLOSED_OPTION, 
            JOptionPane.QUESTION_MESSAGE
        );

        // Manejar la entrada del usuario
        if (option == JOptionPane.OK_OPTION) {
            if (txtIdCliente.getText().length() > 0) {
                cliente = clienteUseCase.consultarCliente(Long.valueOf(txtIdCliente.getText())); 
            } else JOptionPane.showMessageDialog(null, "Ingrese un id de cliente.","Validacion de cliente",JOptionPane.ERROR_MESSAGE);

        } else {
            return null;
        }
        return cliente;
    }

    public String reservaStepTwo(List<DetalleVuelo> lstDetallesVuelo){

        //FORMULARIO 2
        JPanel panel1 = new JPanel(new GridLayout(2, 1, 20, 20));    
        panel1.setPreferredSize(new Dimension(600, 500));
        panel1.setBorder(BorderFactory.createEmptyBorder(180, 10, 180, 10));

        JLabel lblTrayecto = new JLabel("Trayecto:");

        List<String> lstDescripcionesTrayecto = new ArrayList<>();
        String[] descripcionesTgs;
        Consumer<DetalleVuelo> getDescripcion = trayecto -> lstDescripcionesTrayecto.add(trayecto.getDesc_trayecto());
        lstDetallesVuelo.forEach(getDescripcion);
        descripcionesTgs = lstDescripcionesTrayecto.toArray(new String[0]);
        JComboBox<String> descripcionComboBox = new JComboBox<>(descripcionesTgs);

        // Establecer estilo de fuente
        Font font1 = new Font("Monospaced", Font.ITALIC, 14); 
        Font font = new Font("Monospaced", Font.BOLD, 20); // Arial, negrita, tamaño 16
        lblTrayecto.setFont(font);
        descripcionComboBox.setFont(font1);

        panel1.add(lblTrayecto);
        panel1.add(descripcionComboBox);

        
        // Mostrar el panel en un JOptionPane
        int option = JOptionPane.showConfirmDialog(
            null, 
            panel1, 
            "Formulario registros de reserva!", 
            JOptionPane.CLOSED_OPTION, 
            JOptionPane.QUESTION_MESSAGE
        );

        // Manejar la entrada del usuario
        if (option == JOptionPane.OK_OPTION) {


        } else {
            return null;
        }
        return null;
    }
}
