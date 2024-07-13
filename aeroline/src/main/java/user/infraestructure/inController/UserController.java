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

        boolean bandera0 = false;

        while (!bandera0) {

            String[] opcionesMenuPrincipal = {"Iniciar sesión", "Acceder como cliente", "Salír"};

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
                        case "Iniciar sesión":
                            mostrarSubMenuAcceso();
                            break;
                        case "Acceder como cliente":
                            //mostrarSubMenuProductos();
                            break;
                        case "Salír":
                            JOptionPane.showMessageDialog(null, "Saliendo de la aplicación.");
                            System.exit(0);
                            break;
                    }
                });
                panel.add(button);
                panel.add(Box.createRigidArea(new Dimension(0, 10)));
            }
            // Mostrar el panel en un JOptionPane
            JOptionPane.showOptionDialog(
                null,
                panel,
                "Aeroline",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                new Object[]{}, // Pasar un array vacío de opciones para que solo se muestre el panel
                null
            );
        }

    }


    public void mostrarSubMenuAcceso(){
        // Crear los componentes
        JPanel panel = new JPanel(new GridLayout(2, 2, 15, 15));
        JLabel userLabel = new JLabel("Usuario:");
        JTextField userField = new JTextField();
        JLabel passLabel = new JLabel("Contraseña:");
        JPasswordField passField = new JPasswordField();

        // Añadir los componentes al panel
        panel.add(userLabel);
        panel.add(userField);
        panel.add(passLabel);
        panel.add(passField);

        // Mostrar el panel en un JOptionPane
        int option = JOptionPane.showConfirmDialog(
            null, 
            panel, 
            "Iniciar Sesión", 
            JOptionPane.OK_CANCEL_OPTION, 
            JOptionPane.PLAIN_MESSAGE
        );

        // Manejar la entrada del usuario
        if (option == JOptionPane.OK_OPTION) {
            String usuario = userField.getText();
            String contraseña = new String(passField.getPassword());

            // Aquí puedes añadir la lógica para validar el usuario y la contraseña
            User usuarioValidado = userUseCase.consultarUser(usuario, contraseña);
            if (usuarioValidado != null) {
                List<String> permisos = userUseCase.obtenerPermisosUserCase(usuarioValidado.getId_rolUsuario());
                if (usuarioValidado.getId_rolUsuario() == 1) {
                    mostrarSubMenuPaquetesPermisosAdmin(permisos);
                }  else if (usuarioValidado.getId_rolUsuario() == 3){
                    //mostrarSubMenuPaquetesPermisosVentas(permisos);
                } else {
                    mostrarSubMenuPermisos(permisos);
                }
            }
        } else {
            System.out.println("Inicio de sesión cancelado.");
        }
    }


    public void mostrarSubMenuPaquetesPermisosAdmin(List<String> permisos){
        List<String> permisoAvion = new ArrayList<>();
        List<String> permisoVuelo = new ArrayList<>();
        List<String> permisoAeropuerto = new ArrayList<>();
        List<String> permisoDocumento = new ArrayList<>();
        List<String> permisoTripulacion = new ArrayList<>();
        List<String> permisoTarifa = new ArrayList<>();

        //Separando permisos por paquetes
        for (String permiso: permisos) {
            if (permiso.contains("avion")) {
                permisoAvion.add(permiso);
            } else if (permiso.contains("vuelo") || permiso.contains("trayecto") || permiso.contains("escala")) {
                permisoVuelo.add(permiso);
            } else if (permiso.contains("documento")) {
                permisoDocumento.add(permiso);
            } else if (permiso.contains("aeropuerto")) {
                permisoAeropuerto.add(permiso);
            }else if (permiso.contains("tripulacion")) {
                permisoTripulacion.add(permiso);
            } else if (permiso.contains("tarifa")) {
                permisoTarifa.add(permiso);
            }
        }

        // Definir las opciones del submenú de Gestión de Usuarios
        String[] opcionesPaqueteAdmin = {"Gestionar Avion", "Gestionar Vuelo", "Gestionar Aeropuerto", "Gestionar Tripulacion", "Gestionar tarifas", "Gestionar Documento", "Volver"};

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
                        viewAdminAvion(permisoAvion);
                        break;

                    case "Gestionar Vuelo":
                        JOptionPane.showMessageDialog(null, "Eliminando un usuario...");
                        break;

                    case "Gestionar Aeropuerto":
                        JOptionPane.showMessageDialog(null, "Actualizando un usuario...");
                        break;

                    case "Gestionar Tripulacion":
                        JOptionPane.showMessageDialog(null, "Añadiendo un nuevo usuario...");
                        break;

                    case "Gestionar tarifas":
                        JOptionPane.showMessageDialog(null, "Eliminando un usuario...");
                        break;

                    case "Gestionar Documento":
                        JOptionPane.showMessageDialog(null, "Actualizando un usuario...");
                        break;

                    case "Volver":
                        start();
                        break;
                }
            });
            panel.add(button);
            panel.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio entre botones
        }

        // Mostrar el panel en un JOptionPane
        JOptionPane.showOptionDialog(
            null,
            panel,
            "Gestión de Usuarios",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            new Object[]{}, // Pasar un array vacío de opciones para que solo se muestre el panel
            null
        );        
    }

    public void viewAdminAvion(List<String> permisos){

    }

    public void mostrarSubMenuPaquetesPermisosVentas(){

    }

    public void mostrarSubMenuPermisos(List<String> permisos){
        // Definir las opciones del submenú de Gestión de Usuarios
        String[] opcionesUsuarios = permisos.toArray(new String[0]);

        // Crear un panel con BoxLayout para organizar las opciones verticalmente
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        Dimension buttonSize = new Dimension(300, 30); // Tamaño fijo para todos los botones

        // Agregar las opciones al panel
        for (String opcion : opcionesUsuarios) {
            JButton button = new JButton(opcion);
            button.setPreferredSize(buttonSize);
            button.setMaximumSize(buttonSize);
            button.setMinimumSize(buttonSize);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.addActionListener(e -> {
                
            });
            panel.add(button);
            panel.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio entre botones
        }

        // Mostrar el panel en un JOptionPane
        JOptionPane.showOptionDialog(
            null,
            panel,
            "Gestión de Usuarios",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            new Object[]{}, // Pasar un array vacío de opciones para que solo se muestre el panel
            null
        );
    }
}
