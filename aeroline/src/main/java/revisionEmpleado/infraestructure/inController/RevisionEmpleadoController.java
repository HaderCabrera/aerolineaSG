package revisionEmpleado.infraestructure.inController;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import revisionEmpleado.application.RevisionEmpleadoUseCase;
import revisionEmpleado.domain.entity.RevisionEmpleado;

public class RevisionEmpleadoController {
    private final RevisionEmpleadoUseCase revisionEmpleadoUseCase;

    public RevisionEmpleadoController(RevisionEmpleadoUseCase revisionEmpleadoUseCase) {
        this.revisionEmpleadoUseCase = revisionEmpleadoUseCase;
    }

    public Long registrarRevisionEmpleado(Long idRevision){
        RevisionEmpleado revisionEmpleadoOk = solicitarDatosRegistro(idRevision);
        Long confirmacion2 = revisionEmpleadoUseCase.registrarRevisionEmpleado(revisionEmpleadoOk);
        return confirmacion2;
    }

    public RevisionEmpleado solicitarDatosRegistro(Long IdRevision){
        RevisionEmpleado revisionEmpleado = new RevisionEmpleado();

        JPanel panel = new JPanel(new GridLayout(1, 1, 5, 1));

        JLabel txtIdEmpleado = new JLabel("Id Revisión:");
        JTextField lblIdEmpleado = new JTextField();
        lblIdEmpleado.setFont(new Font("Monospaced", Font.BOLD, 12));

        panel.setPreferredSize(new Dimension(250, 60));

        //Agreganmos elementos al panel
        panel.add(txtIdEmpleado);
        panel.add(lblIdEmpleado);

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
            String id_empleado = lblIdEmpleado.getText();

            try {
                revisionEmpleado.setId_empleado(id_empleado);
                revisionEmpleado.setId_revision(IdRevision);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(panel, "Error en SQL", "Error", JOptionPane.ERROR_MESSAGE);
            }
            

        } 
        return revisionEmpleado;
    }
}
