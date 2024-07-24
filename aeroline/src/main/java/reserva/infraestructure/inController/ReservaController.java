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

import com.ibm.icu.impl.BOCU;

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

    public void registrarReserva(){
        Reserva reserva = registrarDatosReserva();
        Boolean confirmacion = reservaUseCase.registrarReserva(reserva);
        if (confirmacion) {
            JOptionPane.showMessageDialog(null, "Registros Exitoso","Confirmacion",JOptionPane.PLAIN_MESSAGE);
        } else JOptionPane.showMessageDialog(null, "Error al registrar.","Error de conexiòn",JOptionPane.WARNING_MESSAGE);
    }

    public Reserva registrarDatosReserva(){
        Reserva reserva = new Reserva();
        List<DetalleVuelo> lstTrayectos = new ArrayList<>();
        //INSTANCIA PARA TRAYECTO
        DetalleVueloService detalleVueloService = new DetalleVueloRepository();
        DetalleVueloUseCase detalleVueloUseCase = new DetalleVueloUseCase(detalleVueloService);
        DetallevueloController detalleVueloController = new DetallevueloController(detalleVueloUseCase);

        //INSTANCIAR HEXAGONAL ESCALA
        EscalaService escalaService = new EscalaRepository();
        EscalaUseCase escalaUseCase = new EscalaUseCase(escalaService);
        EscalaController escalaController = new EscalaController(escalaUseCase);

        //INSTANCIAR TARIFA
        TarifaService tarifaService = new TarifaRepository();
        TarifaUseCase tarifaUseCase  = new TarifaUseCase(tarifaService);
        TarifaController tarifaController = new TarifaController(tarifaUseCase);

        lstTrayectos = detalleVueloController.listarDescripcionesTrayecto();
    
        Cliente clienteValidado = reservaStepOne();
        if (clienteValidado != null) {
            int validarTarifas = 0;
            String revisionSeleccionada = reservaStepTwo(lstTrayectos);
            //TOMAR EL ID DE LA DESCRIPCION SELECCIONADA Y CONSULTAR SI TIENE ESCALAS O VUELOS

            DetalleVuelo trayecto = detalleVueloController.obtenerTrayectoByDescripcion(revisionSeleccionada);

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

            Long idTarifaSelect = reservaSetpThree(lstTarifasValidades);

            // if (condition) { 
            // }
            reserva.setFecha_reserva("2024-07-24");
            reserva.setId_cliente(clienteValidado.getId_cliente());
            reserva.setId_estadoReserva(1L);
            reserva.setId_puesto(1L);
            reserva.setId_tarifa(idTarifaSelect);

        } else JOptionPane.showMessageDialog(null, "Error al ingresar usuario","Error de formulario",JOptionPane.WARNING_MESSAGE);


        return reserva;
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
        String trayectoSelect = "";
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
            trayectoSelect = descripcionComboBox.getSelectedItem().toString();
        } else {
            return null;
        }
        return trayectoSelect;
    }

    public Long reservaSetpThree(List<Tarifa>lstTarifasValidades){
        Long idTipoTarifa = 0L;
        //FORMULARIO 3
        JPanel panel1 = new JPanel(new GridLayout(2, 1, 20, 20));    
        panel1.setPreferredSize(new Dimension(600, 500));
        panel1.setBorder(BorderFactory.createEmptyBorder(180, 10, 180, 10));

        JLabel lblTarifa = new JLabel("Tipo de tarifa disponible:");

        List<String> descripciones = new ArrayList<>();
        String[] descripcionTgs;
        Consumer<Tarifa> getDescripcionTarifa = descTarifa -> descripciones.add(descTarifa.getDescripcion());
        lstTarifasValidades.forEach(getDescripcionTarifa);
        descripcionTgs = descripciones.toArray(new String[0]);
        JComboBox<String> tarifaComboBox = new JComboBox<>(descripcionTgs);

        // Establecer estilo de fuente
        Font font1 = new Font("Monospaced", Font.ITALIC, 14); 
        Font font = new Font("Monospaced", Font.BOLD, 20); // Arial, negrita, tamaño 16
        lblTarifa.setFont(font);
        tarifaComboBox.setFont(font1);

        panel1.add(lblTarifa);
        panel1.add(tarifaComboBox);

        
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
            for (Tarifa tarifa : lstTarifasValidades) {
                    if (tarifa.getDescripcion().equals(tarifaComboBox.getSelectedItem().toString())) {
                        idTipoTarifa = tarifa.getId_tarifa();
                    }
            }
        } else {
            return null;
        }
        return idTipoTarifa;
    }


}
