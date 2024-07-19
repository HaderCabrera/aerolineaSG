package aeropuerto.infraestructure.inController;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import aeropuerto.application.AeropuertoUseCase;
import aeropuerto.domain.entity.Aeropuerto;
import ciudad.application.CiudadUseCase;
import ciudad.domain.service.CiudadService;
import ciudad.infraestructure.inController.CiudadController;
import ciudad.infraestructure.outRepository.CiudadRepository;
import ciudad.domain.entity.Ciudad;

public class AeropuertoController {
    private final AeropuertoUseCase aeropuertoUseCase;

    public AeropuertoController(AeropuertoUseCase aeropuertoUseCase) {
        this.aeropuertoUseCase = aeropuertoUseCase;
    }

    public void registrarAeropuerto(){
        Aeropuerto aeropuerto = solicitarDatosRegistro();
        Boolean confirmacion = aeropuertoUseCase.registrarAeropuerto(aeropuerto);
        if (confirmacion) {
            JOptionPane.showMessageDialog(null, "Registros Exitoso!", "Confirmacion", JOptionPane.INFORMATION_MESSAGE);
        } else  JOptionPane.showMessageDialog(null, "Error al registrar", "Denied", JOptionPane.WARNING_MESSAGE);
    }

    public void consultarAeropuerto(){
        Long id_aeropuerto = solicitarIdAeropuerto();
        Aeropuerto aeropuertoFind = aeropuertoUseCase.consultarAeropuerto(id_aeropuerto);
        if (aeropuertoFind != null) {
            mostrarDatosAeropuerto(aeropuertoFind);
        } else {
            JOptionPane.showMessageDialog(null, "Aeropuerto no encontrado!", "Error De Consulta", JOptionPane.ERROR_MESSAGE);
        }

    }

    public Aeropuerto solicitarDatosRegistro(){
        /*Varibales */
        Aeropuerto aeropuerto = new Aeropuerto();
        List<Ciudad> lstCiudades = new ArrayList<>();

        //Crear los componentes
        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 1));

        JLabel lblNombre = new JLabel("Nombre Aeropuerto:");
        JTextField txtNombre = new JTextField();
        txtNombre.setFont(new Font("Monospaced", Font.BOLD, 12));

        JLabel idLabel = new JLabel("Ciudad:");

        //LLAMAR ENTIDAD CIUDAD PARA TOMAR CIUDADES
        CiudadService ciudadService = new CiudadRepository();
        CiudadUseCase ciudadUseCase = new CiudadUseCase(ciudadService);
        CiudadController ciudadController = new CiudadController(ciudadUseCase);
        List<String> lstNombreCiudades = new ArrayList<>();
        lstCiudades = ciudadController.listarCiudades();
        String[] opcionesTgs;
        
        //USANDO CONSUMER
        Consumer<Ciudad> getNombre = ciudad -> lstNombreCiudades.add(ciudad.getNombre());
        lstCiudades.forEach(getNombre);

        opcionesTgs = lstNombreCiudades.toArray(new String[0]);
        JComboBox<String> opTgsComboBox = new JComboBox<>(opcionesTgs);

        panel.setPreferredSize(new Dimension(250, 80));

        // Añadir los componentes al panel
        panel.add(lblNombre);
        panel.add(txtNombre);
        panel.add(idLabel);
        panel.add(opTgsComboBox);


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
            String nombre = txtNombre.getText();
            String ciudadN = opTgsComboBox.getSelectedItem().toString();

            try {
                //USO DE FUNCIONES LAMDA PARA RECORRER LISTA DE OBJETOS
                lstCiudades.forEach(ciudad -> {
                    if (ciudad.getNombre().equals(ciudadN)) {
                        aeropuerto.setId_ciudad(ciudad.getId_ciudad());
                        aeropuerto.setNombre(nombre);
                    }
                });
            } catch (Exception e) {
                System.out.println("Formatos invalidos, Try Again!" + e);
            }
        
        } else {
            System.out.println("Registro de avión cancelado.");
        }
        return aeropuerto;
    }

    public Long  solicitarIdAeropuerto(){
        JPanel panel = new JPanel(new GridLayout(1, 1, 5, 1));

        JLabel txtIdAeropuerto = new JLabel("Id Aeropuerto:");
        JTextField lblIdAeropuerto = new JTextField();
        lblIdAeropuerto.setFont(new Font("Monospaced", Font.BOLD, 12));

        panel.setPreferredSize(new Dimension(250, 30));
    
        //Agreganmos elementos al panel
        panel.add(txtIdAeropuerto);
        panel.add(lblIdAeropuerto);

        // Mostrar el panel en un JOptionPane
        int option = JOptionPane.showConfirmDialog(
            null, 
            panel, 
            "Airline, Hight All  The Time!", 
            JOptionPane.OK_CANCEL_OPTION, 
            JOptionPane.QUESTION_MESSAGE
        );

        //Tratar datos recolectados
        if (option == JOptionPane.OK_OPTION) {
            Long id = Long.parseLong(lblIdAeropuerto.getText());
            return id;        

        } else {
            JOptionPane.showMessageDialog(panel, "Consulta cancelada", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public void mostrarDatosAeropuerto(Aeropuerto aeropuerto){
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 5));

        JLabel lblNombre = new JLabel("Aeropuerto:");
        JLabel lblNombreValor = new JLabel(aeropuerto.getNombre());

        JLabel lblCiudad = new JLabel("Ciudad:");
        JLabel lblCiudadValor = new JLabel(aeropuerto.getCiudad());

        panel.add(lblNombre);
        panel.add(lblNombreValor);
        panel.add(lblCiudad);
        panel.add(lblCiudadValor);

        // Mostrar el panel en un JOptionPane
        JOptionPane.showConfirmDialog(
            null, 
            panel, 
            "Airline, Hight All  The Time!", 
            JOptionPane.CLOSED_OPTION, 
            JOptionPane.PLAIN_MESSAGE
        );
    }
}


