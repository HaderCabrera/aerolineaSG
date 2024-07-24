package tripulacion.infraestructure.inController;

import java.awt.Font;
import java.util.List;
import java.util.Vector;
import java.util.function.Consumer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import org.eclipse.jdt.core.dom.Dimension;

import empleado.domain.entity.Empleado;
import revision.domain.entity.Revision;
import tripulacion.application.TripulacionUseCase;
import tripulacion.domain.entity.Tripulacion;

public class TripulacionController {

    private final TripulacionUseCase tripulacionUseCase;

    public TripulacionController(TripulacionUseCase tripulacionUseCase) {
        this.tripulacionUseCase = tripulacionUseCase;
    }

    public Tripulacion obtenerTripulacionPorVuelo() {
        JFrame windowFirst = new JFrame("Obtener Tripulación");
        JLabel disL = new JLabel("Estado de Tripulación");
        JTextField disF = new JTextField("Ingresa Estado");
        JButton submitButton = new JButton("Obtener Tripulación");

        windowFirst.setLayout(null);
        disL.setBounds(50, 50, 150, 30);
        disF.setBounds(50, 100, 200, 30);
        submitButton.setBounds(50, 150, 200, 30);

        windowFirst.add(disL);
        windowFirst.add(disF);
        windowFirst.add(submitButton);

        windowFirst.setSize(400, 400);
        windowFirst.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windowFirst.setVisible(true);

      submitButton.addActionListener(e -> {
            String estado = disF.getText();
            List<Empleado> tripulantesDisponibles = tripulacionUseCase.ObtenerTripulantesDisponibles(estado);

            // if (tripulantesDisponibles == null || tripulantesDisponibles.isEmpty()) {
            //     JOptionPane.showMessageDialog(null, "No se encontraron tripulantes disponibles para el estado especificado.", "Información", JOptionPane.INFORMATION_MESSAGE);
            //     return;
            // }

            String[][] data = new String[tripulantesDisponibles.size()][3];
            int index = 0; // Índice para el array de datos
            for (Empleado tripulante : tripulantesDisponibles) {
                data[index][0] = tripulante.getId_empleado();
                data[index][1] = tripulante.getNombre1();
                data[index][2] = tripulante.getId_estadoEmpleado();
                index++;
            }
            
            // Convertir la lista de tripulantes a un array de datos para la JTable

            System.out.println("Esta imprimprimeindo");

            String[] column = {"CODEC_T", "Tripulante", "Estado_Empleado"};
            JFrame tableFrame = new JFrame("Tripulantes Disponibles");

            JTable jt = new JTable(data, column);
            JScrollPane sp = new JScrollPane(jt);

            tableFrame.add(sp);
            tableFrame.setSize(400, 300);
            tableFrame.setVisible(true);
        });
    
    
        return null;
    }

}
