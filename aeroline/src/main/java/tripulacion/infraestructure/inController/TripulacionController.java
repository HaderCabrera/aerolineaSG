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

            // Crear el modelo de la tabla
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("CODEC_T");
            model.addColumn("Tripulante");
            model.addColumn("Estado_Empleado");
            model.addColumn("Acción");

            // Rellenar la tabla con datos
            for (Empleado tripulante : tripulantesDisponibles) {
                Object[] row = new Object[4];
                row[0] = tripulante.getId_empleado();
                row[1] = tripulante.getNombre1();
                row[2] = tripulante.getId_estadoEmpleado();
                row[3] = "Asignar";
                model.addRow(row);
            }

            // Crear la JTable con el modelo
            JTable table = new JTable(model);
            table.setRowHeight(30); // Ajustar altura de las filas

            // Agregar un botón en cada fila
            

            // Crear y mostrar la ventana con la tabla
            JFrame tableFrame = new JFrame("Tripulantes Disponibles");
            tableFrame.setSize(600, 300);
            tableFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            tableFrame.add(new JScrollPane(table));
            tableFrame.setVisible(true);
        });
    
    
        return null;
    }

}
