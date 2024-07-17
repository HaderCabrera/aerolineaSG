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

    public void registrarRevisionEmpleado(RevisionEmpleado revisionEmpleado){
        RevisionEmpleado revisionEmpleadoOk = solicitarDatosRegistro();
        revisionEmpleadoUseCase.registrarRevisionEmpleado(revisionEmpleadoOk);
    }

    public RevisionEmpleado solicitarDatosRegistro(){
        RevisionEmpleado revisionEmpleado = new RevisionEmpleado();

        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 1));

        JLabel txtIdRevision = new JLabel("Id Revisión:");
        JTextField lblIdRevision = new JTextField();
        lblIdRevision.setFont(new Font("Monospaced", Font.BOLD, 12));

        JLabel txtIdEmpleado = new JLabel("Id Revisión:");
        JTextField lblIdEmpleado = new JTextField();
        lblIdEmpleado.setFont(new Font("Monospaced", Font.BOLD, 12));

        panel.setPreferredSize(new Dimension(250, 60));

        //VALIDACIONES DE ENTERO
        lblIdRevision.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
                    JOptionPane.showMessageDialog(panel, "Campo solo numeros", "Error", JOptionPane.ERROR_MESSAGE);
                    e.consume(); // Ignorar la tecla no numérica
                }
            }
        });

        //VALIDACIONES DE ENTERO
        lblIdEmpleado.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
                    JOptionPane.showMessageDialog(panel, "Campo solo numeros", "Error", JOptionPane.ERROR_MESSAGE);
                    e.consume(); // Ignorar la tecla no numérica
                }
            }
        });

        //Agreganmos elementos al panel
        panel.add(txtIdRevision);
        panel.add(lblIdRevision);
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
            String id_revision = lblIdRevision.getText();
            String id_empleado = lblIdEmpleado.getText();

            try {
                revisionEmpleado.setId_revision(Integer.parseInt(id_revision));
                revisionEmpleado.setId_empleado(Integer.parseInt(id_empleado));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(panel, "Error en SQL", "Error", JOptionPane.ERROR_MESSAGE);
            }
            

        } 
        return revisionEmpleado;
    }
}
