package vuelo.infraestructure.inController;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.google.gwt.dev.jjs.ast.JLabel;

import vuelo.application.VueloUseCase;
import vuelo.domain.entity.Vuelo;

public class VueloController {
    private final VueloUseCase vueloUseCase;

    public VueloController(VueloUseCase vueloUseCase) {
        this.vueloUseCase = vueloUseCase;
    }
    
    public List<Long> getIdAvionByIdTrayecto(Long id_trayecto){
        return vueloUseCase.getIdAvionByIdTrayecto(id_trayecto);
    }

    public Vuelo listarVuelos(){
        List<Vuelo> ListaVuelos = vueloUseCase.obtenerIdVuelos();


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

            String[] column = {"id_vuelo", "numero_vuelo", "aeropuerto_destino", "aeropuerto_origen", "hora_llegada", "hora_salida"};
            JFrame tableFrame = new JFrame("Lista de Vuelos");
            tableFrame.setLayout(new BorderLayout());
            JTable jt = new JTable(data, column);
            JScrollPane sp = new JScrollPane(jt);


            // Panel para los campos y botones
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(2, 2, 10, 10)); // Filas, Columnas, Espacio Horizontal, Espacio Vertical
            JLabel vueloLabel = new JLabel(null, "Información del Vuelo:");
            JTextField idVueloField = new JTextField();
            JButton asignarButton = new JButton("Asignar Empleados");

            panel.add(vueloLabel);
            panel.


 


 

            tableFrame.add(sp);
            tableFrame.setSize(800, 400);
            tableFrame.setVisible(true);
            tableFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            

        return null ;

    }
}
