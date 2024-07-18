package revision.infraestructure.inController;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import revision.application.RevisionUseCase;
import revision.domain.entity.Revision;
import revisionEmpleado.domain.service.RevisionEmpleadoService;
import revisionEmpleado.application.RevisionEmpleadoUseCase;
import revisionEmpleado.infraestructure.inController.RevisionEmpleadoController;
import revisionEmpleado.infraestructure.outRepository.RevisionEmpleadoRepository;

public class RevisionController {
    private final RevisionUseCase revisionUseCase;

    public RevisionController(RevisionUseCase revisionUseCase) {
        this.revisionUseCase = revisionUseCase;
    }
    
    public void registrarRevision(){
        Revision revision = solicitarDatosRegistro();
        Long idRevision = revisionUseCase.registrarRevision(revision);
        
        //Logica para registrar la revision a un empleado
        if (idRevision != 0) {
            //PENDIENTE LOGICA DE REGISTRAR EN ENTIDAD revision_empleado
            RevisionEmpleadoService revisionEmpleadoService = new RevisionEmpleadoRepository();
            RevisionEmpleadoUseCase revisionEmpleadoUseCase = new RevisionEmpleadoUseCase(revisionEmpleadoService);
            RevisionEmpleadoController revisionEmpleadoController = new RevisionEmpleadoController(revisionEmpleadoUseCase);
            //falta terminar
            Long confirmacion = revisionEmpleadoController.registrarRevisionEmpleado(idRevision);
            System.out.println(confirmacion);
            if (confirmacion != 0) {
                //JPnael CONFIRMACION
                String mensaje = "Registros De Revisiòn Exitoso";
                JOptionPane.showMessageDialog(null, mensaje, "Denied", JOptionPane.PLAIN_MESSAGE);

            } else {
               revisionUseCase.eliminarRevision(idRevision);
            }
        } 
    }

    public Revision solicitarDatosRegistro(){
        /*Varibales */
        Revision revision = new Revision();

        //Crear los componentes
        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 1));

        JLabel fechaLabel = new JLabel("Fecha De Revisión:");
        JTextField fechaField = new JTextField();
        fechaField.setFont(new Font("Monospaced", Font.BOLD, 12));

        JLabel idLabel = new JLabel("Id Avión:");
        JTextField idField = new JTextField();
        idField.setFont(new Font("Monospaced", Font.BOLD, 12));

        JLabel descripcionLabel = new JLabel("Descripción:");
        JTextField descripcionField = new JTextField();
        descripcionField.setFont(new Font("Monospaced", Font.BOLD, 13));

        panel.setPreferredSize(new Dimension(450, 120));

        //VALIDACIONES DE ENTERO
        idField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
                    JOptionPane.showMessageDialog(panel, "Campo solo numeros", "Error", JOptionPane.ERROR_MESSAGE);
                    e.consume(); // Ignorar la tecla no numérica
                }
            }
        });
        //VALIDACIONES DE FECHA
        fechaField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE && c != '-') {
                    JOptionPane.showMessageDialog(panel, "Caracter  Ingreado Invalido!", "Error", JOptionPane.ERROR_MESSAGE);
                    e.consume(); // Ignorar la tecla no numérica
                } else if (fechaField.getText().length() >= 10) {
                    JOptionPane.showMessageDialog(panel, "No Se Puede Ingresar Mas Caracteres!", "Error", JOptionPane.ERROR_MESSAGE);
                    e.consume(); 
                }
            }
        });

        // Añadir los componentes al panel
        panel.add(fechaLabel);
        panel.add(fechaField);
        panel.add(idLabel);
        panel.add(idField);
        panel.add(fechaLabel);
        panel.add(fechaField);
        panel.add(descripcionLabel);
        panel.add(descripcionField);

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

            String fecha_revision = fechaField.getText();
            String id_revision = idField.getText();
            String descrip = descripcionField.getText();

            try {
                revision.setFecha_revision(fecha_revision);
                revision.setId_avion(Integer.parseInt(id_revision));
                revision.setDescrip(descrip);
                

            } catch (Exception e) {
                System.out.println("Formatos invalidos, Try Again!" + e);
            }
        
        } else {
            System.out.println("Registro de avión cancelado.");
        }
        return revision;
    }
}

