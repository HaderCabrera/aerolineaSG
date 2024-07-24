package vuelo.infraestructure.inController;

import java.awt.BorderLayout;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import empleado.domain.entity.Empleado;
import tripulacion.application.TripulacionUseCase;
import tripulacion.domain.service.TripulacionService;
import tripulacion.infraestructure.inController.TripulacionController;
import tripulacion.infraestructure.outRepository.TripulacionRepositiry;
import vuelo.application.VueloUseCase;
import vuelo.domain.entity.Vuelo;

public class VueloController {
    private final VueloUseCase vueloUseCase;

    public VueloController(VueloUseCase vueloUseCase) {
        this.vueloUseCase = vueloUseCase;
    }

    public List<Long> getIdAvionByIdTrayecto(Long id_trayecto) {
        return vueloUseCase.getIdAvionByIdTrayecto(id_trayecto);
    }

    public Vuelo listarVuelos() {
        List<Vuelo> ListaVuelos = vueloUseCase.obtenerIdVuelos();

        // LLAMADONDO EL HEXAGOANAL DE TRIPULACION
        TripulacionService tripulacionService = new TripulacionRepositiry();
        TripulacionUseCase tripulacionUseCase = new TripulacionUseCase(tripulacionService);
        TripulacionController tripulacionController = new TripulacionController(tripulacionUseCase);

        String[][] data = new String[ListaVuelos.size()][6];
        for (int i = 0; i < ListaVuelos.size(); i++) {
            Vuelo vuelo = ListaVuelos.get(i);
            data[i][0] = Long.toString(vuelo.getId_vuelo());
            data[i][1] = vuelo.getNumero_vuelo();
            data[i][2] = Long.toString(vuelo.getAeropuerto_destino());
            data[i][3] = Long.toString(vuelo.getAeropuerto_origen());
            data[i][4] = vuelo.getHora_llegada();
            data[i][5] = vuelo.getHora_salida();
        }

        System.out.println("Esta imprimprimeindo");

        String[] column = { "id_vuelo", "numero_vuelo", "aeropuerto_destino", "aeropuerto_origen", "hora_llegada",
                "hora_salida" };
        JFrame tableFrame = new JFrame("Lista de Vuelos");
        tableFrame.setLayout(new BorderLayout());
        JTable jt = new JTable(data, column);
        JScrollPane sp = new JScrollPane(jt);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2, 10, 10));
        JLabel vueloLabel = new JLabel("Información del Vuelo:");
        JTextField idVueloField = new JTextField();
        JTextField idEmpleado_asignar = new JTextField();
        JTextField idEmpleado = new JTextField();
        JButton asignarButton = new JButton("Asignar Empleados");
        JButton Agregar = new JButton("ADD");
        panel.add(vueloLabel);

        
        panel.add(idVueloField);
        panel.add(idEmpleado);
        panel.add(idEmpleado_asignar);
        panel.add(asignarButton);
        panel.add(Agregar);

        tableFrame.add(panel, BorderLayout.NORTH);
        tableFrame.add(sp, BorderLayout.CENTER);

        tableFrame.add(sp);
        tableFrame.setSize(800, 400);
        tableFrame.setVisible(true);
        tableFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        jt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                int row = jt.rowAtPoint(e.getPoint());
                int column = jt.columnAtPoint(e.getPoint());

                if (column == 1) { // Verifica si el clic fue en la columna de "Acción"
                    Object valueColumn1 = jt.getValueAt(row, 0); // Primera columna
                    Object valueColumn2 = jt.getValueAt(row, 1); // Segunda columna

                    System.out.println("Botón clickeado para empleado ID: " + valueColumn1.toString());
                    idVueloField.setText(valueColumn2.toString());
                    String valor_vuelo = valueColumn1.toString();
                    System.out.println(valor_vuelo+  "    ===" + valueColumn2);
                    // tripulacionUseCase.asignarEmpleadoToTripulacion(t)
                    // valueColumn2.toString());
                    System.out.println("se ejecuto esta parte");

                }
            }

        });
        asignarButton.addActionListener(listasVuelos -> {

            JFrame windowFirst = new JFrame("Obtener Tripulación");
            JLabel disL = new JLabel("Estado de Tripulación");
            JTextField disF = new JTextField("Ingresa Estado");
            JButton submitButton = new JButton("Obtener Tripulación");
            JButton imprimirListaEmpleados = new JButton("Obtenerlista");
            JButton imprimirListaVuelos = new JButton("Obtenerlista");

            windowFirst.setLayout(null);
            disL.setBounds(50, 50, 150, 30);
            disF.setBounds(50, 100, 200, 30);
            submitButton.setBounds(50, 150, 200, 30);
            imprimirListaEmpleados.setBounds(50, 250, 200, 30);
            imprimirListaVuelos.setBounds(50, 350, 200, 30);

            windowFirst.add(disL);
            windowFirst.add(disF);
            windowFirst.add(submitButton);
            windowFirst.add(imprimirListaEmpleados);
            windowFirst.add(imprimirListaVuelos);

            windowFirst.setSize(500, 200);
            windowFirst.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            windowFirst.setVisible(true);

            submitButton.addActionListener(e -> {


                String estado = disF.getText();
                List<Empleado> tripulantesDisponibles = tripulacionUseCase.ObtenerTripulantesDisponibles(estado);
                List<String> Lstidemplados_para_tripulacion = new ArrayList<>();

                if (tripulantesDisponibles == null || tripulantesDisponibles.isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "No se encontraron tripulantes disponibles para el estado especificado.", "Información",
                            JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                String[][] data2 = new String[tripulantesDisponibles.size()][4];
                for (int i = 0; i < tripulantesDisponibles.size(); i++) {
                    Empleado tripulante = tripulantesDisponibles.get(i);
                    data2[i][0] = tripulante.getId_empleado();
                    data2[i][1] = tripulante.getNombre1();
                    data2[i][2] = tripulante.getId_estadoEmpleado();
                    data2[i][3] = "agragar vuelo";
                }

                // Convertir la lista de tripulantes a un array de datos para la JTable

                System.out.println("Esta imprimprimeindo");

                String[] column2 = { "CODEC_T", "Tripulante", "Estado_Empleado", "ADD" };
                JFrame tableFrame2 = new JFrame("Tripulantes Disponibles");
                JTable jt2 = new JTable(data2, column2);
                JScrollPane sp2 = new JScrollPane(jt2);
                jt2.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        int row = jt2.rowAtPoint(e.getPoint());
                        int column = jt2.columnAtPoint(e.getPoint());

                        if (column == 3) { // Verifica si el clic fue en la columna de "Acción"
                            Object valueColumn3 = jt2.getValueAt(row, 0);
                            idEmpleado_asignar.setText(valueColumn3.toString());
                            String idEmpleado = (String) jt2.getValueAt(row, 0);
                            System.out.println("Botón clickeado para empleado ID: " + idEmpleado);
                            Lstidemplados_para_tripulacion.add(idEmpleado);

                        }
                    }

                });

                imprimirListaEmpleados.addActionListener(lista -> {
                    for (String i : Lstidemplados_para_tripulacion) {
                        System.out.println("Lista empleado: " + i);
                    }

                });

                tableFrame2.add(sp2);
                tableFrame2.setSize(600, 300);
                tableFrame2.setVisible(true);
            });

        });

        Agregar.addActionListener(e ->{
            
        });

        return null;

    }

}
