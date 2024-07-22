package detallevuelo.infraestructure.inController;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

// import java.awt.event.KeyAdapter;
// import java.awt.event.KeyEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import detallevuelo.application.DetalleVueloUseCase;
import detallevuelo.domain.entity.DetalleVuelo;

import java.awt.*;
import java.util.List;

public class DetallevueloController {
    private DetalleVueloUseCase detalleVueloUseCase;

    public DetallevueloController(DetalleVueloUseCase detalleVueloUseCase) {
        this.detalleVueloUseCase = detalleVueloUseCase;
    }

    public DetalleVuelo consultarTrayecto() {

        DetalleVuelo detallevuelo = new DetalleVuelo();

        // Crear los componentes
        JPanel panel = new JPanel(new GridLayout(4, 2, 5, 1));

        JLabel codigoVueloLabel = new JLabel("Codigo Vuelo:");
        JTextField codigoVueloField = new JTextField();
        codigoVueloField.setFont(new Font("Monospaced", Font.BOLD, 12));

        panel.setPreferredSize(new Dimension(450, 120));

        // Añadir componentes Al window

        panel.add(codigoVueloLabel);
        panel.add(codigoVueloField);

        // Capta la informacion al moento de dicir si
        // Imprimir informacion de Detalle
        int option = JOptionPane.showConfirmDialog(
                null,
                panel,
                "Airline, Hight All  The Time!",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        System.out.println(option);

        if (option == JOptionPane.OK_OPTION) {

            String codigo_vuelo = codigoVueloField.getText();
            try {
                int id_trayecto = Integer.parseInt(codigo_vuelo);
                DetalleVuelo trayecto = detalleVueloUseCase.consultarTrayecto(id_trayecto);
                JButton delateButton = new JButton("Delate");
                Dimension buttonSize = new Dimension(80, 30);
                JButton actualizar = new JButton("Refresh");

                delateButton.setBounds(50, 100, 80, 30);
                delateButton.setBackground(Color.yellow);
                delateButton.setPreferredSize(buttonSize);
                actualizar.setBounds(100, 100, 80, 30);
                actualizar.setPreferredSize(buttonSize);
                actualizar.setBackground(Color.green);
                delateButton.setPreferredSize(new Dimension(100, 30));
                JPanel detailsPanel = new JPanel(new GridLayout(2, 8, 10, 5));

                String[] headers = {
                        "ID_trayecto", "origen_trayecto", "destino_trayecto",
                        "descripcion", "distancia", "tiempo Estimado"
                };

                // Añadir las etiquetas de cabecera
                for (String header : headers) {
                    JLabel label = new JLabel(header);
                    label.setPreferredSize(new Dimension(500, 3));
                    label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    label.setFont(new Font("Monospaced", Font.BOLD, 12));
                    label.setHorizontalAlignment(SwingConstants.CENTER);
                    detailsPanel.add(label);
                }

                detailsPanel.add(actualizar);

                // Valores a mostrar
                JTextField[] valueFields = new JTextField[headers.length];
                String[] values = {
                        String.valueOf(trayecto.getId_trayecto()),
                        trayecto.getOrigen_trayecto(),
                        trayecto.getDestino_tracyecto(),
                        trayecto.getDesc_trayecto(),
                        trayecto.getDistancia(),
                        trayecto.getTimpoEstimado()
                };

                for (int i = 0; i < values.length; i++) {
                    JTextField field = new JTextField(values[i]);
                    field.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    field.setFont(new Font("Arial", Font.PLAIN, 12));
                    field.setHorizontalAlignment(SwingConstants.CENTER);
                    valueFields[i] = field;
                    detailsPanel.add(field);
                }

                detailsPanel.add(delateButton);

                detailsPanel.setPreferredSize(new Dimension(1500, 100));

                delateButton.addActionListener(new ActionListener() {

                    // HACER CONSULTA EN JAVA ELIMIAR DELETE mySQL
                    public void actionPerformed(ActionEvent e) {
                        int result = JOptionPane.showConfirmDialog(null,
                                "¿Está seguro de que desea eliminar este elemento?",
                                "Confirmación de Eliminación",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.WARNING_MESSAGE);

                        if (result == JOptionPane.YES_OPTION) {
                            detalleVueloUseCase.eliminarTrayecto(trayecto.getId_trayecto());
                            ;
                            JOptionPane.showMessageDialog(panel, "Elemento eliminado.");
                        } else if (result == JOptionPane.NO_OPTION) {
                            JOptionPane.showMessageDialog(null, "Eliminación cancelada.");

                        }
                    }

                });

                actualizar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int result = JOptionPane.showConfirmDialog(null,
                                "Esta Seguro de actualizar",
                                "Confirmación de actulizar",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.WARNING_MESSAGE);

                        if (result == JOptionPane.YES_OPTION) {

                            try {
                                    
                                String nuevoOrigen = valueFields[1].getText();
                                String nuevoDestino = valueFields[2].getText();
                                String nuevaDescripcion = valueFields[3].getText();
                                String nuevaDistancia = valueFields[4].getText();
                                String nuevoTiempoEstimado = valueFields[5].getText();

                                
                                DetalleVuelo updatedTrayecto = new DetalleVuelo(
                                        trayecto.getId_trayecto(), // ID permanece igual
                                        nuevoOrigen,
                                        nuevoDestino,
                                        nuevaDescripcion,
                                        nuevaDistancia,
                                        nuevoTiempoEstimado);

                                detalleVueloUseCase.editarTrayecto(updatedTrayecto);
                                JOptionPane.showMessageDialog(null, "Actualización completada.");

                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(null, "Error al actualizar el detalle del vuelo.",
                                        "Error", JOptionPane.ERROR_MESSAGE);
                            }

                        } else if (result == JOptionPane.NO_OPTION) {
                            JOptionPane.showMessageDialog(null, "Actualizacion cancelada.");
                        }
                    }
                });

                // Mostrar el panel de detalles en un JOptionPane
                JOptionPane.showMessageDialog(null, detailsPanel, "Detalle del Vuelo", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(panel, "Error al consultar el detalle del vuelo", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        }

        return detallevuelo;

    }

    public List<DetalleVuelo> listarDescripcionesTrayecto(){
        return detalleVueloUseCase.listarTrayectos();
    }
    
}
