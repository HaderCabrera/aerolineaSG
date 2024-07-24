package user.infraestructure.inController;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import java.awt.*;

import aeropuerto.application.AeropuertoUseCase;
import aeropuerto.domain.service.AeropuertoService;
import aeropuerto.infraestructure.inController.AeropuertoController;
import aeropuerto.infraestructure.outRepository.AeropuertoRepository;
import avion.application.AvionUseCase;
import avion.domain.service.AvionService;
import avion.infraestructure.inController.AvionController;
import avion.infraestructure.outRepository.AvionRepository;
import cliente.application.ClienteUseCase;
import cliente.domain.service.ClienteService;
import cliente.infraestructure.inController.ClienteController;
import cliente.infraestructure.outRepository.ClienteRepository;
import detallevuelo.application.DetalleVueloUseCase;
import detallevuelo.domain.service.DetalleVueloService;
import detallevuelo.infraestructure.inController.DetallevueloController;
import detallevuelo.infraestructure.outRepository.DetalleVueloRepository;
import escala.application.EscalaUseCase;
import escala.domain.service.EscalaService;
import escala.infraestructure.inController.EscalaController;
import escala.infraestructure.outRepository.EscalaRepository;
import reserva.application.ReservaUseCase;
import reserva.domain.service.ReservaService;
import reserva.infraestructure.inController.ReservaController;
import reserva.infraestructure.outRepository.ReservaRepository;
import revision.application.RevisionUseCase;
import revision.domain.service.RevisionService;
import revision.infraestructure.inController.RevisionController;
import revision.infraestructure.outRepository.RevisionRepository;
import tipoDocumento.application.TipoDocumentoUseCase;
import tipoDocumento.domain.service.TipoDocumentoService;
import tipoDocumento.infraestructure.inController.TipoDocumentoController;
import tipoDocumento.infraestructure.outRepository.TipoDocumentoRepository;
import tripulacion.application.TripulacionUseCase;

import tripulacion.domain.service.TripulacionService;
import tripulacion.infraestructure.inController.TripulacionController;
import tripulacion.infraestructure.outRepository.TripulacionRepositiry;


import user.application.UserUseCase;
import user.domain.entity.User;

public class UserController {
    private UserUseCase userUseCase;

    /*Constructor*/
    public UserController(UserUseCase userUseCase) {
        this.userUseCase = userUseCase;
    }

    public void start(){

        //variables
            String[] opcionesMenuPrincipal = {"Iniciar Sesión", "Acceder Como Cliente", "Salír"};

            JFrame ventanaPrincipal = new JFrame("Gestionar Reserva");

            ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventanaPrincipal.setSize(750, 750);
            ventanaPrincipal.setLocationRelativeTo(null);

            // Mostrar el menú principal
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            Dimension buttonSize = new Dimension(200, 30);
            panel.setBackground(Color.decode("#3e3d41"));
            panel.setBorder(BorderFactory.createEmptyBorder(180, 100, 230, 100));

            // Agregar las opciones al panel
            for (String opcion : opcionesMenuPrincipal) {
                JButton button = new JButton(opcion);
                button.setPreferredSize(buttonSize);
                button.setMaximumSize(buttonSize);
                button.setMinimumSize(buttonSize);
                button.setAlignmentX(Component.CENTER_ALIGNMENT);

                button.addActionListener(e -> {
                    switch (opcion) {
                        case "Iniciar Sesión":
                        //ventanaPrincipal.setVisible(false);
                            List<String> datosAcceso  = vistaInicioSesion();
                            if (datosAcceso != null) {
                                if (!datosAcceso.get(0).equals("incorrecto")) {
                                    User usuarioValidado = userUseCase.findUserCase(datosAcceso.get(0), datosAcceso.get(1));
                                    if (usuarioValidado != null) {
                                        List<String> permisos = userUseCase.getPermisosCase(usuarioValidado.getId_rolUsuario());
                                        
                                        if (usuarioValidado.getId_rolUsuario() == 1) {
                                            mostrarSubMenuPaquetesPermisosAdmin(permisos);
                                        }  else if (usuarioValidado.getId_rolUsuario() == 3){
                                            mostrarSubMenuPaquetesPermisosVendedor(permisos);
                                        } else {
                                            generarVistaUser(permisos);
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Usuario No Registrado", "Not Connected", JOptionPane.WARNING_MESSAGE);
                                    }    
                                } else JOptionPane.showMessageDialog(null, "Datos Ingesados Incorrectos!", "Not Connected", JOptionPane.WARNING_MESSAGE);
                            }
                            break;

                        case "Acceder Como Cliente":
                            List<String> permisos = userUseCase.getPermisosCase(2);
                            ventanaPrincipal.setVisible(false);
                            generarVistaUser(permisos);
                            break;

                        case "Salír":
                            ventanaPrincipal.setVisible(false);
                            JOptionPane.showMessageDialog(null, "Saliendo de la aplicación.");
                            break;
                    }
                });
                panel.add(button);
                panel.add(Box.createRigidArea(new Dimension(0, 5)));
            }
            ventanaPrincipal.add(panel);
            ventanaPrincipal.setVisible(true);
        }

    public List<String> vistaInicioSesion(){
         //Crear los componentes
        JPanel panel = new JPanel(new GridLayout(2, 2, 1, 1));

        JLabel userLabel = new JLabel("Usuario:");
        JTextField userField = new JTextField();
        userField.setFont(new Font("Monospaced", Font.BOLD, 13));   

        JLabel passLabel = new JLabel("Contraseña:");
        JPasswordField passField = new JPasswordField();

        panel.setPreferredSize(new Dimension(20, 60));

        List<String> datosAcceso = new ArrayList<>();

        // Añadir los componentes al panel
        panel.add(userLabel);
        panel.add(userField);
        panel.add(passLabel);
        panel.add(passField);

        // Mostrar el panel en un JOptionPane
        int option = JOptionPane.showConfirmDialog(
            null, 
            panel, 
            "Airline, Hight All  The Time!", 
            JOptionPane.OK_CANCEL_OPTION, 
            JOptionPane.QUESTION_MESSAGE
        );

        // Manejar la entrada del usuario
        if (option == JOptionPane.OK_OPTION) {

            String usuario = userField.getText();
            String contraseña = new String(passField.getPassword());
            if (usuario.length() > 0 && contraseña.length() > 0) {
                datosAcceso.add(usuario);
                datosAcceso.add(contraseña);    
            } else datosAcceso.add("incorrecto");

        } else {
            return null;
        }
  
        return datosAcceso;
    }

    public void mostrarSubMenuPaquetesPermisosAdmin(List<String> permisos){
        List<String> lstPermisoAvion = new ArrayList<>();
        List<String> lstPermisoVuelo = new ArrayList<>();
        List<String> lstPermisoAeropuerto = new ArrayList<>();
        List<String> lstPermisoDocumento = new ArrayList<>();
        List<String> lstPermisosTrayecto = new ArrayList<>();

        //Separando permisos por paquetes
        for (String permiso: permisos) {
            if (permiso.contains("Avion")) {
                lstPermisoAvion.add(permiso);
            } else if (permiso.contains("Vuelo") || permiso.contains("Escala")) {
                lstPermisoVuelo.add(permiso);
            } 
            else if ( permiso.contains("Trayecto")){
                lstPermisosTrayecto.add(permiso);
            } else if (permiso.contains("Documento")) {
                lstPermisoDocumento.add(permiso);
            } else if (permiso.contains("Aeropuerto")) {
                lstPermisoAeropuerto.add(permiso);
            }
        }

        //frame
        JFrame ventanaAdmin = new JFrame("Gestion como administrador");
        ventanaAdmin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaAdmin.setSize(750, 750);
        ventanaAdmin.setLocationRelativeTo(null);


        // Definir las opciones del submenú de Gestión de Usuarios
        String[] opcionesPaqueteAdmin = {"Gestionar Avion", "Gestionar Vuelo", "Gestionar Trayecto", "Gestionar Aeropuerto", "Gestionar Documento", "Atras"};

        // Crear un panel con BoxLayout para organizar las opciones verticalmente
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.decode("#3e3d41"));
        panel.setBorder(BorderFactory.createEmptyBorder(100, 100, 230, 100));
        Dimension buttonSize = new Dimension(200, 30);
         // Tamaño fijo para todos los botones

        // Agregar las opciones al panel
        for (String opcion : opcionesPaqueteAdmin) {
            JButton button = new JButton(opcion);
            button.setPreferredSize(buttonSize);
            button.setMaximumSize(buttonSize);
            button.setMinimumSize(buttonSize);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.addActionListener(e -> {
                switch (opcion) {
                    case "Gestionar Avion":
                        // ventanaAdmin.setVisible(false);  
                        generarVistaUser(lstPermisoAvion);
                        break;

                    case "Gestionar Vuelo":
                        // ventanaAdmin.setVisible(false);  
                        generarVistaUser(lstPermisoVuelo);
                        break;

                    case "Gestionar Trayecto":
                        // ventanaAdmin.setVisible(false);      
                        generarVistaUser(lstPermisosTrayecto);
                        break;

                    case "Gestionar Aeropuerto":
                        // ventanaAdmin.setVisible(false);  
                        generarVistaUser(lstPermisoAeropuerto);
                        break;

                    case "Gestionar Documento":
                        //ventanaAdmin.setVisible(false);  
                        generarVistaUser(lstPermisoDocumento);
                        break;

                    case "Atras":
                        ventanaAdmin.setVisible(false); 
                        //start();
                        break;
                }
            });
            panel.add(button);
            panel.add(Box.createRigidArea(new Dimension(0, 5))); // Espacio entre botones
        }

        ventanaAdmin.add(panel);
        ventanaAdmin.setVisible(true);      
    }

    public void mostrarSubMenuPaquetesPermisosVendedor(List<String> permisos){
        List<String> lstPermisoReserva = new ArrayList<>();
        List<String> lstPermisoCliente = new ArrayList<>();

        //Separando permisos por paquetes
        for (String permiso: permisos) {
            if (permiso.contains("Cliente")) {
                lstPermisoCliente.add(permiso);
            } else  if(permiso.contains("Vuelo") || permiso.contains("Reserva")) {
                lstPermisoReserva.add(permiso);
            } 
        }
        // Definir las opciones del submenú de Gestión de Usuarios
        String[] opcionesPaqueteAdmin = {"Gestionar Vuelo / Reserva / Escala", "Gestionar Cliente", "Consultar Asignacion De Tripulacion", "Consultar Tipo De Documento", "Atras"};

        JFrame ventanaPrincipal = new JFrame("Gestiones vendedor");
        ventanaPrincipal.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //ventanaPrincipal.setSize(420, 270);
        ventanaPrincipal.setSize(750, 750);

        ventanaPrincipal.setLocationRelativeTo(null);

        // Crear un panel con BoxLayout para organizar las opciones verticalmente
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.decode("#3e3d41"));
        panel.setBorder(BorderFactory.createEmptyBorder(100, 100, 230, 100));

        Dimension buttonSize = new Dimension(350, 30); // Tamaño fijo para todos los botones
        // Agregar las opciones al panel
        for (String opcion : opcionesPaqueteAdmin) {
            JButton button = new JButton(opcion);
            button.setPreferredSize(buttonSize);
            button.setMaximumSize(buttonSize);
            button.setMinimumSize(buttonSize);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.addActionListener(e -> {
                switch (opcion) {
                    case "Gestionar Vuelo / Reserva / Escala":
                        //ventanaPrincipal.setVisible(false); 
                        generarVistaUser(lstPermisoReserva);    
                        break;

                    case "Gestionar Cliente":
                        //ventanaPrincipal.setVisible(false); 
                        generarVistaUser(lstPermisoCliente);
                        break;

                    case "Consultar Asignacion De Tripulacion":
                        ejecutarPermiso("Consultar Tripulacion De Trayecto");
                        break;

                    case "Consultar Tipo De Documento":
                        ejecutarPermiso("Consultar Tipo Documento");
                        break;

                    case "Atras":
                        ventanaPrincipal.setVisible(false); 
                        //start();
                        break;
                }
            });
            panel.add(button);
            panel.add(Box.createRigidArea(new Dimension(0, 5))); // Espacio entre botones
        }

        ventanaPrincipal.add(panel);
        ventanaPrincipal.setVisible(true);        
    }

    public void generarVistaUser(List<String> permisos) {
        // Definir las opciones del submenú de Gestión de Usuarios
        String[] opcionesUsuarios = permisos.toArray(new String[0]);
        String[] nuevasOpciones = new String[opcionesUsuarios.length + 1];
        System.arraycopy(opcionesUsuarios, 0, nuevasOpciones, 0, opcionesUsuarios.length);
        nuevasOpciones[nuevasOpciones.length - 1] = "Atras";

        JFrame ventanaPrincipal = new JFrame("Seleccion de servicio");
        ventanaPrincipal.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaPrincipal.setSize(500, 500);

        ventanaPrincipal.setLocationRelativeTo(null);


        // Crear un panel con BoxLayout para organizar las opciones verticalmente
        JPanel panel = new JPanel();    
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.decode("#3e3d41"));
        panel.setBorder(BorderFactory.createEmptyBorder(100, 100, 230, 100));
        Dimension buttonSize = new Dimension(450, 30); // Tamaño fijo para todos los botones

        for (String opcion : nuevasOpciones) {
            JButton button = new JButton(opcion);
            button.setPreferredSize(buttonSize);
            button.setMaximumSize(buttonSize);
            button.setMinimumSize(buttonSize);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.addActionListener(e -> {
                if (e.getActionCommand().toString().equals("Atras")) {
                    ventanaPrincipal.setVisible(false);
                }
                ejecutarPermiso(e.getActionCommand());
            });
            panel.add(button);
            panel.add(Box.createRigidArea(new Dimension(0, 5))); // Espacio entre botones
        }

        ventanaPrincipal.add(panel);
        ventanaPrincipal.setVisible(true);
   }

    public void ejecutarPermiso(String permiso){
        //LLAMADO A HEXAGONAL AVION
        AvionService avionService = new AvionRepository();
        AvionUseCase avionUseCase = new AvionUseCase(avionService);
        AvionController avionController = new AvionController(avionUseCase);

        //LLAMADO A HEXAGONAL AEROPUERTO
        AeropuertoService aeropuertoService = new AeropuertoRepository();
        AeropuertoUseCase aeropuertoUseCase = new AeropuertoUseCase(aeropuertoService);
        AeropuertoController aeropuertoController = new AeropuertoController(aeropuertoUseCase);

        //LLAMADO A HEXAGONAL REVISION
        RevisionService revisionService = new RevisionRepository();
        RevisionUseCase revisionUseCase = new RevisionUseCase(revisionService);
        RevisionController revisionController = new RevisionController(revisionUseCase);

        //LLAMADO EXAGONAL CLIENTE
        ClienteService clienteService = new ClienteRepository();
        ClienteUseCase clienteUseCase = new ClienteUseCase(clienteService);
        ClienteController clienteController = new ClienteController(clienteUseCase);
        
        //LLAMADO HEXAGONAL TIPO DOCUMENTO
        TipoDocumentoService tipoDocumentoService = new TipoDocumentoRepository();
        TipoDocumentoUseCase tipoDocumentoUseCase = new TipoDocumentoUseCase(tipoDocumentoService);
        TipoDocumentoController tipoDocumentoController = new TipoDocumentoController(tipoDocumentoUseCase);
        
        //LLAMADO HEXAGONAL RESERVA
        ReservaService reservaService = new ReservaRepository();
        ReservaUseCase reservaUseCase = new ReservaUseCase(reservaService);
        ReservaController reservaController = new ReservaController(reservaUseCase);

        //LLAMANDO HEXAGONAL TRIPULACION ESTADOS
        TripulacionService tripulacionService = new TripulacionRepositiry();
        TripulacionUseCase tripulacionUseCase = new  TripulacionUseCase(tripulacionService);
        TripulacionController  tripulacionController = new TripulacionController(tripulacionUseCase);


        //LLAMANDO HEXAGONAL TRAYECTO
        DetalleVueloService detalleVueloService = new DetalleVueloRepository();
        DetalleVueloUseCase detalleVueloUseCase = new DetalleVueloUseCase(detalleVueloService);
        DetallevueloController detallevueloController = new DetallevueloController(detalleVueloUseCase);

        //LLAMADO HEXAGONAL ESCALA
        EscalaService escalaService = new EscalaRepository();
        EscalaUseCase escalaUseCase = new EscalaUseCase(escalaService);
        EscalaController escalaController = new EscalaController(escalaUseCase);
        switch (permiso) {
            case "Registrar Avion":
                avionController.registrarAvion();
                break;

            case "Consultar Informacion De Avion":
                avionController.consultarAvionByPlaca(); 
                break;

            case "Actualizar Informacion De Avion":
                avionController.updateAvion();
                break;

            case "Eliminar Avion":
                avionController.eliminarAvionByPlaca();
                break;

            case "Asignar Tripulacion A Trayecto":
                System.out.println("SI LO TOMOA BIEN");
                break;

            case "Consultar Tripulacion De Trayecto":

               
                tripulacionController.obtenerTripulacionPorVuelo();
                
                System.out.println("SI LO TOMOA BIEN");
                break;

            case "Consultar Informacion De Trayecto":
                
                detallevueloController.consultarTrayecto();
                System.out.println("SI LO TOMOA BIEN");
                break;

            case "Asignar Aeronave A Trayecto":
                System.out.println("SI LO TOMOA BIEN");
                break;

            case "Actualizar Informacion De Trayecto":
                System.out.println("SI LO TOMOA BIEN");
                break;

            case "Eliminar Escala":
                System.out.println("SI LO TOMOA BIEN");
                break;

            case "Registrar Aeropuerto":
                aeropuertoController.registrarAeropuerto();
                break;

            case "Consultar Informacion De Aeronave":
                //ES LO MISMO QUE CONSULTAR INFORMACION DE AVION
                break;

            case "Actualizar Informacion De Aeropuerto":
                aeropuertoController.updateAeropuerto();
                break;

            case "Eliminar Aeropuerto":
                aeropuertoController.eliminarAeropuertoById();
                break;

            case "Consultar Informacion De Vuelo":
                System.out.println("SI LO TOMOA BIEN");
                break;
                
            case "Consultar Escalas De Vuelo":
                System.out.println("SI LO TOMOA BIEN");
                break;
            case "Registrar Tarifa De Vuelo":
                System.out.println("SI LO TOMOA BIEN");
                break;
            case "Actualizar Informacion Tarifa De Vuelo":
                System.out.println("SI LO TOMOA BIEN");
                break;
            case "Eliminar Tarifa De Vuelo":
                System.out.println("SI LO TOMOA BIEN");
                break;
            case "Consultar Tarifa De Vuelo":
                System.out.println("SI LO TOMOA BIEN");
                break;
            case "Buscar Vuelo":
                System.out.println("SI LO TOMOA BIEN");
                break;
            case "Seleccionar Vuelo":
                System.out.println("SI LO TOMOA BIEN");
                break;
            case "Registrar Tipo Documento":
                tipoDocumentoController.crearTipoDocumento();
                break;
            case "Actualizar Tipo Documento":
                tipoDocumentoController.updateTipoDocumento();
                break;
            case "Eliminar Tipo Documento":
                tipoDocumentoController.eliminarTipoDocumentoById();
                break;
            case "Consultar Tipo Documento":
                tipoDocumentoController.consultarTipoDocumentoById();
                break;
            case "Realizar Pago":
                System.out.println("SI LO TOMOA BIEN");
                break;
            case "Consultar Informacion Cliente":
                clienteController.consultarCliente();
                break;
            case "Actualizar Informacion Cliente":
                clienteController.updateCliente();
                break;
            case "Eliminar Cliente":
                System.out.println("SI LO TOMOA BIEN");
                break;
            case "Registrar Revision Mantenimiento":
                revisionController.registrarRevision();
                break;
            case "Historico De Revisiones En Avion":
                revisionController.listarRevisionesByPlaca();
                break;
            case "Actualizar Informacion Revision":
                revisionController.updateRevisionById();
                break;
            case "Eliminar Revision Mantenimiento":
                revisionController.eliminarRevisionById();
                break;
            case "Modificar Estado Reserva":
                System.out.println("SI LO TOMOA BIEN");
                break;
            case "Eliminar Reserva Vuelo":
                System.out.println("SI LO TOMOA BIEN");
                break;
            case "Consultar Reserva Vuelo":
                reservaController.consultarReservaByClienteTrayecto();
                break;
            case "Listar Reservas":
                System.out.println("SI LO TOMOA BIEN");
                break;
            case "Crear Reserva":
                reservaController.registrarReserva();
                break;
            case "Mostrar Detalles Reserva":
                System.out.println("SI LO TOMOA BIEN");
                break;
            case "Consultar Informacion De Aeropuerto":
                aeropuertoController.consultarAeropuerto();
                break;
            case "Eliminar Trayecto":
                System.out.println("SI LO TOMOA BIEN");
                break;
            case "Actualizar Informacion De Escala":
                escalaController.actualizarEscala();
                break;
            case "Añadir Pasajero":
                System.out.println("SI LO TOMOA BIEN");
                break;
            case "Seleccionar Asiento":
                System.out.println("SI LO TOMOA BIEN");
                break;
            case "Registrar Cliente":
                System.out.println("SI LO TOMOA BIEN");
                break;
            case "Menú Principal":
                start();                
                break;
            default:
                break;
        }
    }
}



