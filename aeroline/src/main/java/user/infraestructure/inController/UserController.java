package user.infraestructure.inController;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

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
import revision.application.RevisionUseCase;
import revision.domain.service.RevisionService;
import revision.infraestructure.inController.RevisionController;
import revision.infraestructure.outRepository.RevisionRepository;
import tipoDocumento.application.TipoDocumentoUseCase;
import tipoDocumento.domain.service.TipoDocumentoService;
import tipoDocumento.infraestructure.inController.TipoDocumentoController;
import tipoDocumento.infraestructure.outRepository.TipoDocumentoRepository;

import java.awt.*;

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
        boolean bandera0 = false;

        while (!bandera0) {
            String[] opcionesMenuPrincipal = {"Iniciar Sesión", "Acceder Como Cliente", "Salír"};
            
            // Mostrar el menú principal
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            Dimension buttonSize = new Dimension(200, 30);

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
                            List<String> datosAcceso  = vistaInicioSesion();
                            // Aquí puedes añadir la lógica para validar el usuario y la contraseña
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
                            break;

                        case "Acceder Como Cliente":
                            List<String> permisos = userUseCase.getPermisosCase(2);
                            generarVistaUser(permisos);
                            break;

                        case "Salír":
                            JOptionPane.showMessageDialog(null, "Saliendo de la aplicación.");
                            System.exit(0);
                            break;
                    }
                });
                panel.add(button);
                panel.add(Box.createRigidArea(new Dimension(0, 5)));
            }
            // Mostrar el panel en un JOptionPane
            JOptionPane.showOptionDialog(
                null,
                panel,
                "Aeroline, Hight All The Time!",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                new Object[]{}, // Pasar un array vacío de opciones para que solo se muestre el panel
                null
            );
        }
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
            datosAcceso.add(usuario);
            datosAcceso.add(contraseña);
        } else {
            System.out.println("Inicio de sesión cancelado.");
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
        // Definir las opciones del submenú de Gestión de Usuarios
        String[] opcionesPaqueteAdmin = {"Gestionar Avion", "Gestionar Vuelo", "Gestionar Trayecto", "Gestionar Aeropuerto", "Gestionar Documento", "Menú Principal"};

        // Crear un panel con BoxLayout para organizar las opciones verticalmente
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        Dimension buttonSize = new Dimension(200, 30); // Tamaño fijo para todos los botones

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
                        generarVistaUser(lstPermisoAvion);    
                        break;

                    case "Gestionar Vuelo":
                        generarVistaUser(lstPermisoVuelo);
                        break;

                    case "Gestionar Trayecto":
                        generarVistaUser(lstPermisosTrayecto);
                        break;

                    case "Gestionar Aeropuerto":
                        generarVistaUser(lstPermisoAeropuerto);
                        break;

                    case "Gestionar Documento":
                        generarVistaUser(lstPermisoDocumento);
                        break;

                    case "Menú Principal":
                        start();
                        break;
                }
            });
            panel.add(button);
            panel.add(Box.createRigidArea(new Dimension(0, 5))); // Espacio entre botones
        }

        // Mostrar el panel en un JOptionPane
        JOptionPane.showOptionDialog(
            null,
            panel,
            "Gestión de Usuarios",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,
            new Object[]{}, // Pasar un array vacío de opciones para que solo se muestre el panel
            null
        );        
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
        String[] opcionesPaqueteAdmin = {"Gestionar Vuelo", "Gestionar Cliente", "Consultar Asignacion De Tripulacion", "Consultar Tarifa De Vuelo","Consultar Tipo De Documento", "Menú Principal"};

        // Crear un panel con BoxLayout para organizar las opciones verticalmente
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

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
                    case "Gestionar Vuelo":
                        generarVistaUser(lstPermisoReserva);    
                        break;

                    case "Gestionar Cliente":
                        generarVistaUser(lstPermisoCliente);
                        break;

                    case "Consultar Asignacion De Tripulacion":
                        ejecutarPermiso("Consultar Tripulacion De Trayecto");
                        break;

                    case "Consultar Escala De Un Trayecto":
                        ejecutarPermiso("Consultar Escalas De Vuelo");
                        break;

                    case "Consultar Tipo De Documento":
                        ejecutarPermiso("Consultar Tipo Documento");
                        break;

                    case "Menú Principal":
                        start();
                        break;
                }
            });
            panel.add(button);
            panel.add(Box.createRigidArea(new Dimension(0, 5))); // Espacio entre botones
        }

        // Mostrar el panel en un JOptionPane
        JOptionPane.showOptionDialog(
            null,
            panel,
            "Gestión de Usuarios",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,
            new Object[]{}, // Pasar un array vacío de opciones para que solo se muestre el panel
            null
        );        
    }

    public void generarVistaUser(List<String> permisos) {
        // Definir las opciones del submenú de Gestión de Usuarios
        String[] opcionesUsuarios = permisos.toArray(new String[0]);
        String[] nuevasOpciones = new String[opcionesUsuarios.length + 1];
        System.arraycopy(opcionesUsuarios, 0, nuevasOpciones, 0, opcionesUsuarios.length);
        nuevasOpciones[nuevasOpciones.length - 1] = "Menú Principal";

        // Crear un panel con BoxLayout para organizar las opciones verticalmente
        JPanel panel = new JPanel();    
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        Dimension buttonSize = new Dimension(450, 30); // Tamaño fijo para todos los botones

        // Agregar las opciones al panel
        for (String opcion : nuevasOpciones) {
            JButton button = new JButton(opcion);
            button.setPreferredSize(buttonSize);
            button.setMaximumSize(buttonSize);
            button.setMinimumSize(buttonSize);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.addActionListener(e -> {
                ejecutarPermiso(e.getActionCommand());
            });
            panel.add(button);
            panel.add(Box.createRigidArea(new Dimension(0, 5))); // Espacio entre botones
        }

        // Mostrar el panel en un JOptionPane
        JOptionPane.showOptionDialog(
            null,
            panel,
            "Airline, Higth All The Time!",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,
            new Object[]{}, // Pasar un array vacío de opciones para que solo se muestre el panel
            null
        );
   }

    public void ejecutarPermiso(String permiso){
        AvionService avionService = new AvionRepository();
        AvionUseCase avionUseCase = new AvionUseCase(avionService);
        AvionController avionController = new AvionController(avionUseCase);

        switch (permiso) {
            case "Registrar Avion":
                avionController.registrarAvion();
                break;

            case "Consultar Informacion De Avion":
                avionController.consultarAvionByPlaca(); 
                break;

            case "Actualizar Informacion De Avion":
                System.out.println("SI LO TOMOA BIEN");
                break;

            case "Eliminar Avion":
                System.out.println("SI LO TOMOA BIEN");
                break;

            case "Asignar Tripulacion A Trayecto":
                System.out.println("SI LO TOMOA BIEN");
                break;

            case "Consultar Tripulacion De Trayecto":
                
                System.out.println("SI LO TOMOA BIEN");
                break;

            case "Consultar Informacion De Trayecto":
                DetalleVueloService detalleVueloService = new DetalleVueloRepository();
                DetalleVueloUseCase detalleVueloUseCase = new DetalleVueloUseCase(detalleVueloService);
                DetallevueloController detallevueloController = new DetallevueloController(detalleVueloUseCase);
                detallevueloController.consultarDetalleVuelo();
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
                System.out.println("SI LO TOMOA BIEN");
                break;

            case "Consultar Informacion De Aeronave":
                //ES LO MISMO QUE CONSULTAR INFORMACION DE AVION
                break;

            case "Actualizar Informacion De Aeropuerto":
                System.out.println("SI LO TOMOA BIEN");
                break;

            case "Eliminar Aeropuerto":
                System.out.println("SI LO TOMOA BIEN");
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
                TipoDocumentoService tipoDocumentoService = new TipoDocumentoRepository();
                TipoDocumentoUseCase tipoDocumentoUseCase = new TipoDocumentoUseCase(tipoDocumentoService);
                TipoDocumentoController tipoDocumentoController = new TipoDocumentoController(tipoDocumentoUseCase);
                tipoDocumentoController.crearTipoDocumento();
                break;
            case "Actualizar Tipo Documento":
                System.out.println("SI LO TOMOA BIEN");
                break;
            case "Eliminar Tipo Documento":
                System.out.println("SI LO TOMOA BIEN");
                break;
            case "Consultar Tipo Documento":
                System.out.println("SI LO TOMOA BIEN");
                break;
            case "Realizar Pago":
                System.out.println("SI LO TOMOA BIEN");
                break;
            case "Registrar Reserva Vuelo":
                System.out.println("SI LO TOMOA BIEN");
                break;
            case "Consultar Informacion Cliente":
                ClienteService clienteService = new ClienteRepository();
                ClienteUseCase clienteUseCase = new ClienteUseCase(clienteService);
                ClienteController clienteController = new ClienteController(clienteUseCase);
                clienteController.consultarCliente();
                break;
            case "Actualizar Informacion Cliente":
                System.out.println("SI LO TOMOA BIEN");
                break;
            case "Eliminar Cliente":
                System.out.println("SI LO TOMOA BIEN");
                break;
            case "Registrar Revision Mantenimiento":
                RevisionService revisionService = new RevisionRepository();
                RevisionUseCase revisionUseCase = new RevisionUseCase(revisionService);
                RevisionController revisionController = new RevisionController(revisionUseCase);
                revisionController.registrarRevision();
                break;
            case "Historico De Revisiones En Avion":
                System.out.println("SI LO TOMOA BIEN");
                break;
            case "Actualizar Informacion Revision":
                System.out.println("SI LO TOMOA BIEN");
                break;
            case "Eliminar Revision Mantenimiento":
                System.out.println("SI LO TOMOA BIEN");
                break;
            case "Modificar Estado Reserva":
                System.out.println("SI LO TOMOA BIEN");
                break;
            case "Eliminar Reserva Vuelo":
                System.out.println("SI LO TOMOA BIEN");
                break;
            case "Consultar Reserva Vuelo":
                System.out.println("SI LO TOMOA BIEN");
                break;
            case "Listar Reservas":
                System.out.println("SI LO TOMOA BIEN");
                break;
            case "Crear Reserva Vuelo":
                System.out.println("SI LO TOMOA BIEN");
                break;
            case "Mostrar Detalles Reserva":
                System.out.println("SI LO TOMOA BIEN");
                break;
            case "Consultar Informacion De Aeropuerto":
                System.out.println("SI LO TOMOA BIEN");
                break;
            case "Eliminar Trayecto":
                System.out.println("SI LO TOMOA BIEN");
                break;
            case "Actualizar Informacion De Escala":
                System.out.println("SI LO TOMOA BIEN");
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



