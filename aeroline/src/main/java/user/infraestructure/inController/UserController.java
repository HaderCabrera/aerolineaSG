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
import detallevuelo.application.DetalleVueloUseCase;
import detallevuelo.domain.entity.DetalleVuelo;
import detallevuelo.domain.service.DetalleVueloService;
import detallevuelo.infraestructure.inController.DetallevueloController;
import detallevuelo.infraestructure.outRepository.DetalleVueloRepository;

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
                                JOptionPane.showMessageDialog(null, "USUARIO NO ENCONTRADO", "Not Connected", JOptionPane.WARNING_MESSAGE);
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

        //Separando permisos por paquetes
        for (String permiso: permisos) {
            if (permiso.contains("avion".toUpperCase())) {
                lstPermisoAvion.add(permiso);
            } else if (permiso.contains("vuelo".toUpperCase()) || permiso.contains("trayecto".toUpperCase()) || permiso.contains("escala".toUpperCase())) {
                lstPermisoVuelo.add(permiso);
            } else if (permiso.contains("documento".toUpperCase())) {
                lstPermisoDocumento.add(permiso);
            } else if (permiso.contains("aeropuerto".toUpperCase())) {
                lstPermisoAeropuerto.add(permiso);
            }
        }

        // Definir las opciones del submenú de Gestión de Usuarios
        String[] opcionesPaqueteAdmin = {"Gestionar Avion", "Gestionar Vuelo", "Gestionar Aeropuerto", "Gestionar Documento", "Menú Principal"};

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
            if (permiso.contains("cliente".toUpperCase())) {
                lstPermisoCliente.add(permiso);
            } else  if(permiso.contains("vuelo".toUpperCase()) || permiso.contains("reserva".toUpperCase())) {
                lstPermisoReserva.add(permiso);
            } 
        }
        // Definir las opciones del submenú de Gestión de Usuarios
        String[] opcionesPaqueteAdmin = {"Gestionar Reserva", "Gestionar Cliente", "Consultar Asignacion De Tripulacion", "Consultar Escala De Un Trayecto", "Consultar Tarifa De Vuelo","Consultar Tipo De Documento", "Menú Principal"};

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
                    case "Gestionar Reserva":
                        generarVistaUser(lstPermisoReserva);    
                        break;

                    case "Gestionar Cliente":
                        generarVistaUser(lstPermisoCliente);
                        break;

                    case "Consultar Asignacion De Tripulacion":
                        ejecutarPermiso("CONSULTAR TRIPULACION A TRAYECTO");
                        break;

                    case "Consultar Escala De Un Trayecto":
                        ejecutarPermiso("Consultar Escalas De Un Vuelo".toUpperCase());
                        break;

                    case "Consultar Tarifa De Vuelo":
                        ejecutarPermiso("Consultar Tarifa De Vuelo".toUpperCase());
                        break;

                    case "Consultar Tipo De Documento":
                        ejecutarPermiso("Consultar Tipo De Documento".toUpperCase());
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
        switch (permiso) {
            case "REGISTRAR AVION":
                AvionService avionService = new AvionRepository();
                AvionUseCase avionUseCase = new AvionUseCase(avionService);
                AvionController avionController = new AvionController(avionUseCase);
                avionController.registrarAvion();
                break;

            case "CONSULTAR INFORMACION DE AVION":
                System.out.println("SI LO TOMOA BIEN");
                break;

            case "ACTUALIZAR INFORMACION DE AVION":
                System.out.println("SI LO TOMOA BIEN");
                break;

            case "ELIMINAR AVION":
                System.out.println("SI LO TOMOA BIEN");
                break;

            case "ASIGNAR TRIPULACION A TRAYECTO":
                System.out.println("SI LO TOMOA BIEN");
                break;

            case "CONSULTAR TRIPULACION A TRAYECTO":
                DetalleVueloService detalleVueloService = new DetalleVueloRepository();
                DetalleVueloUseCase detalleVueloUseCase = new DetalleVueloUseCase(detalleVueloService);
                DetallevueloController detallevueloController = new DetallevueloController(detalleVueloUseCase);
                detallevueloController.consultarDetalleVuelo();
                System.out.println("SI LO TOMOA BIEN");
                break;

            case "CONSULTAR INFORMACION DE TRAYECTO":
                System.out.println("SI LO TOMOA BIEN");
                break;

            case "ASIGNAR AERONAVE A TRAYECTO":
                System.out.println("SI LO TOMOA BIEN");
                break;

            case "ACTUALIZAR INFORMACION DE TRAYECTO":
                System.out.println("SI LO TOMOA BIEN");
                break;

            case "ELIMINAR ESCALA":
                System.out.println("SI LO TOMOA BIEN");
                break;

            case "REGISTRAR AEROPUERTO":
                System.out.println("SI LO TOMOA BIEN");
                break;

            case "CONSULTAR INFORMACION DE AEROPUERTO":
                System.out.println("SI LO TOMOA BIEN");
                break;

            case "ACTUALIZAR INFORMACION DE AEROPUERTO":
                System.out.println("SI LO TOMOA BIEN");
                break;

            case "ELIMINAR AEROPUERTO":
                System.out.println("SI LO TOMOA BIEN");
                break;

            case "CONSULTAR INFORMACION VUELO":
                System.out.println("SI LO TOMOA BIEN");
                break;
                
            case "CONSULTAR ESCALAS DE UN VUELO":
                System.out.println("SI LO TOMOA BIEN");
                break;
            case "REGISTRAR TARIFA DE VUELO":
                System.out.println("SI LO TOMOA BIEN");
                break;
            case "ACTUALIZAR INFORMACION DE TARIFA DE VUELO":
                System.out.println("SI LO TOMOA BIEN");
                break;
            case "ELIMINAR TARIFA DE VUELO":
                System.out.println("SI LO TOMOA BIEN");
                break;
            case "CONSULTAR TARIFA DE VUELO":
                System.out.println("SI LO TOMOA BIEN");
                break;
            case "BUSCAR VUELO":
                System.out.println("SI LO TOMOA BIEN");
                break;
            case "SELECCIONAR VUELO":
                System.out.println("SI LO TOMOA BIEN");
                break;
            case "REGISTRAR TIPO DE DOCUMENTO":
                System.out.println("SI LO TOMOA BIEN");
                break;
            case "ACTUALIZAR TIPO DE DOCUMENTO":
                System.out.println("SI LO TOMOA BIEN");
                break;
            case "ELIMINAR TIPO DE DOCUMENTO":
                System.out.println("SI LO TOMOA BIEN");
                break;
            case "CONSULTAR TIPO DE DOCUMENTO":
                System.out.println("SI LO TOMOA BIEN");
                break;
            case "REALIZAR PAGO":
                System.out.println("SI LO TOMOA BIEN");
                break;
            case "REGISTRAR RESERVA DE VUELO":
                System.out.println("SI LO TOMOA BIEN");
                break;
            case "CONSULTAR INFORMACION DEL CLIENTE":
                System.out.println("SI LO TOMOA BIEN");
                break;
            case "ACTUALIZAR INFORMACION DEL CLIENTE":
                System.out.println("SI LO TOMOA BIEN");
                break;
            case "ELIMINAR CLIENTE":
                System.out.println("SI LO TOMOA BIEN");
                break;
            case "REGISTRAR REVISION DE MANTENIMIENTO":
                System.out.println("SI LO TOMOA BIEN");
                break;
            case "CONSULTAR HISTORIAL DE REVISIONES DE AVIONES":
                System.out.println("SI LO TOMOA BIEN");
                break;
            case "ACTUALIZAR INFORMACION DE REVISION":
                System.out.println("SI LO TOMOA BIEN");
                break;
            case "ELIMINAR REVISION DE MANTENIMIENTO":
                System.out.println("SI LO TOMOA BIEN");
                break;
            case "MODIFICAR ESTADO RESERVA":
                System.out.println("SI LO TOMOA BIEN");
                break;
            case "ELIMINAR RESERVA DE VUELO":
                System.out.println("SI LO TOMOA BIEN");
                break;
            case "CONSULTAR RESERVA DE VUELO":
                System.out.println("SI LO TOMOA BIEN");
                break;
            case "LISTAR RESERVA":
                System.out.println("SI LO TOMOA BIEN");
                break;
            case "CREAR RESERVA DE VUELO":
                System.out.println("SI LO TOMOA BIEN");
                break;
            case "MOSTRAR DETALLES DE LA RESERVA":
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



