package reserva.infraestructure.inController;

import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
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
import vuelo.application.VueloUseCase;
import vuelo.domain.service.VueloService;
import vuelo.infraestructure.inController.VueloController;
import vuelo.infraestructure.outRepository.VueloRepository;
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
        // // Configuración del JFrame principal
        // JFrame ventanaReserva = new JFrame();
        // ventanaReserva.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // setSize(400, 300);
        // setLocationRelativeTo(null);
        return null;
    }
}
