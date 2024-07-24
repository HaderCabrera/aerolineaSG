package tripulacion.infraestructure.inController;


import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import empleado.domain.entity.Empleado;
import tripulacion.application.TripulacionUseCase;
import tripulacion.domain.entity.Tripulacion;
import vuelo.application.VueloUseCase;

import vuelo.domain.service.VueloService;
import vuelo.infraestructure.inController.VueloController;
import vuelo.infraestructure.outRepository.VueloRepository;

public class TripulacionController {

    private final TripulacionUseCase tripulacionUseCase;

    public TripulacionController(TripulacionUseCase tripulacionUseCase) {
        this.tripulacionUseCase = tripulacionUseCase;
    }

    public Tripulacion obtenerTripulacionPorVuelo() {
        // LLAMADO DE HEXAGONAL VUELO
        // RevisionEstadoService revisionEstadoService = new RevisionEstadoRepository();
        // RevisionEstadoUseCase revisionEstadoUseCase = new RevisionEstadoUseCase(revisionEstadoService);
        // RevisionEstadoController revisionEstadoController = new RevisionEstadoController(revisionEstadoUseCase);

        VueloService vueloService = new VueloRepository();
        VueloUseCase vueloUseCase = new VueloUseCase(vueloService);
        VueloController vueloController = new VueloController(vueloUseCase);
        
        
    
        



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

        windowFirst.setSize(400, 400);
        windowFirst.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windowFirst.setVisible(true);
        
        imprimirListaVuelos.addActionListener(listasVuelos ->{
            vueloController.listarVuelos();
            });

        submitButton.addActionListener(e -> {
            String estado = disF.getText();
            List<Empleado> tripulantesDisponibles = tripulacionUseCase.ObtenerTripulantesDisponibles(estado);
            List<String> Lstidemplados_para_tripulacion = new ArrayList<>();

            if (tripulantesDisponibles == null || tripulantesDisponibles.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No se encontraron tripulantes disponibles para el estado especificado.", "Información", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
            String[][] data = new String[tripulantesDisponibles.size()][4];
            for (int i = 0; i < tripulantesDisponibles.size(); i++) {
                Empleado tripulante = tripulantesDisponibles.get(i);
                data[i][0] = tripulante.getId_empleado();
                data[i][1] = tripulante.getNombre1();
                data[i][2] = tripulante.getId_estadoEmpleado();
                data[i][3] = "agragar vuelo";
            }

            // Convertir la lista de tripulantes a un array de datos para la JTable

            System.out.println("Esta imprimprimeindo");

            String[] column = {"CODEC_T", "Tripulante", "Estado_Empleado", "ADD"};
            JFrame tableFrame = new JFrame("Tripulantes Disponibles");
            JTable jt = new JTable(data, column);
            JScrollPane sp = new JScrollPane(jt);
            jt.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    
                    int row = jt.rowAtPoint(e.getPoint());
                    int column = jt.columnAtPoint(e.getPoint());
    
                    if (column == 3) { // Verifica si el clic fue en la columna de "Acción"
                        
                        
                        String idEmpleado = (String) jt.getValueAt(row, 0);
                        System.out.println("Botón clickeado para empleado ID: " + idEmpleado);
                        Lstidemplados_para_tripulacion.add(idEmpleado);
                        
                    }
                }


            });

            imprimirListaEmpleados.addActionListener (lista ->{
                for (String i : Lstidemplados_para_tripulacion) {
                    System.out.println("Lista empleado: " + i);
            }

            });

            

            tableFrame.add(sp);
            tableFrame.setSize(600, 300);
            tableFrame.setVisible(true);
        });
    
    
        return null;
    }
    public Tripulacion asignarEmpleadoToTripulacion(Tripulacion tripulacion){

        

        return null;
    }

}
