package tripulacion.infraestructure.inController;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import empleado.domain.entity.Empleado;
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

        windowFirst.setSize(400, 250);
        windowFirst.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windowFirst.setVisible(true);


        submitButton.addActionListener(e -> {
            String estado = disF.getText();
            List<Empleado> tripulantesDisponibles = tripulacionUseCase.ObtenerTripulantesDisponibles(estado);

            // Convertir la lista de tripulantes a un array de datos para la JTable
            String[][] data = new String[tripulantesDisponibles.size()][3];
            for (int i = 0; i < tripulantesDisponibles.size(); i++) {
                Empleado tripulante = tripulantesDisponibles.get(i);
                data[i][0] = tripulante.getId_empleado();
                data[i][1] = tripulante.getNombre1();
                data[i][2] = String.valueOf(tripulante.getId_estadoEmpleado());
            }

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
