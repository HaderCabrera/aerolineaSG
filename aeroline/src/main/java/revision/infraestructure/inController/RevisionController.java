package revision.infraestructure.inController;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import avion.domain.entity.Avion;
import revision.application.RevisionUseCase;
import revision.domain.entity.Revision;

public class RevisionController {
    private final RevisionUseCase revisionUseCase;

    public RevisionController(RevisionUseCase revisionUseCase) {
        this.revisionUseCase = revisionUseCase;
    }
    
    public void registrarRevision(){

    }

    public Revision solicitarDatosRegistro(){
        /*Varibales */
        Revision revision = new Revision();

        //Crear los componentes
        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 1));
        JLabel fechaLabel = new JLabel("Fecha De Revisión:");
        JTextField fechaField = new JTextField();
        JLabel idLabel = new JLabel("Id Avión:");
        JTextField idField = new JTextField();
        JLabel descripcionLabel = new JLabel("Descripción:");
        JTextField descripcionField = new JTextField();
        panel.setPreferredSize(new Dimension(450, 120));

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
