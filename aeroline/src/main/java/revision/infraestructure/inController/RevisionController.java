package revision.infraestructure.inController;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JComboBox;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import avion.application.AvionUseCase;
import avion.domain.entity.Avion;
import avion.domain.service.AvionService;
import avion.infraestructure.inController.AvionController;
import avion.infraestructure.outRepository.AvionRepository;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.function.Consumer;

import revision.application.RevisionUseCase;
import revision.domain.entity.Revision;
import revisionEmpleado.domain.service.RevisionEmpleadoService;
import revisionEmpleado.application.RevisionEmpleadoUseCase;
import revisionEmpleado.infraestructure.inController.RevisionEmpleadoController;
import revisionEmpleado.infraestructure.outRepository.RevisionEmpleadoRepository;
import revisionEstado.application.RevisionEstadoUseCase;
import revisionEstado.domain.entity.RevisionEstado;
import revisionEstado.domain.service.RevisionEstadoService;
import revisionEstado.infraestructure.inController.RevisionEstadoController;
import revisionEstado.infraestructure.outRepository.RevisionEstadoRepository;

public class RevisionController {
    private final RevisionUseCase revisionUseCase;

    public RevisionController(RevisionUseCase revisionUseCase) {
        this.revisionUseCase = revisionUseCase;
    }
    

    public void registrarRevision(){
        Revision revision = solicitarDatosRegistro();
        if (revision != null) {
            if (revision.getDescrip() != "cancelar") {
                Long idRevision = revisionUseCase.registrarRevision(revision);
                //Logica para registrar la revision a un empleado
                if (idRevision != 0) {
                    RevisionEmpleadoService revisionEmpleadoService = new RevisionEmpleadoRepository();
                    RevisionEmpleadoUseCase revisionEmpleadoUseCase = new RevisionEmpleadoUseCase(revisionEmpleadoService);
                    RevisionEmpleadoController revisionEmpleadoController = new RevisionEmpleadoController(revisionEmpleadoUseCase);
                    //falta terminar
                    Long confirmacion = revisionEmpleadoController.registrarRevisionEmpleado(idRevision);
                    if (confirmacion != null) {
                        JOptionPane.showMessageDialog(null, "Registros De Revisiòn Exitoso", "Confirmaciòn", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        Boolean confirmarEliminacion = revisionUseCase.eliminarRevision(idRevision);
                        if (confirmarEliminacion) {
                            JOptionPane.showMessageDialog(null, "Registro de Revision Cancelado", "Denied", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No ha sido posible registrar Revision", "Denied", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else JOptionPane.showMessageDialog(null, "Error Al Ingresar Datos", "Denied", JOptionPane.ERROR_MESSAGE);

    }

    public void listarRevisionesByPlaca(){
        AvionService avionService = new AvionRepository();
        AvionUseCase avionUseCase = new AvionUseCase(avionService);
        AvionController avionController = new AvionController(avionUseCase);

        Avion avion = avionController.consultarAvionByPlaca();
        if (avion != null) {
            List<Revision> lstRevisiones = revisionUseCase.listarRevisionesByPlaca(avion.getPlaca_identificacion());
            if (lstRevisiones.size() > 0) {
                mostrarHistorialRevisiones(lstRevisiones);
            } else {
                JOptionPane.showMessageDialog(null, "Avión sin registros!", "Error De Consulta", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public void updateRevisionById(){
        Long idRevision = solicitarIdRevision();
        if (idRevision != null) {
            if (idRevision != 151841511L) {
                Revision revisionUpdate = revisionUseCase.consultarRevisionById(idRevision);
                if (revisionUpdate != null) {
                    if (revisionUpdate.getDescrip() != "cancelar") {
                        revisionUpdate = obtenerRevisionModificada(revisionUpdate);
                        if (revisionUpdate != null) {
                            if (revisionUpdate.getDescrip() != "cancelar") {
                                Boolean actualizacion = revisionUseCase.updateRevisionById(revisionUpdate);
                                if (actualizacion) {
                                    JOptionPane.showMessageDialog(null, "Actualizaciòn Exitosa!","Denied",JOptionPane.INFORMATION_MESSAGE);
                                } else JOptionPane.showMessageDialog(null, "Error al Actualizar Info!","Denied",JOptionPane.ERROR_MESSAGE); 
                            }
                        } else JOptionPane.showMessageDialog(null, "Error al Ingresar Datos","Denied",JOptionPane.ERROR_MESSAGE); 
                    }
                } else JOptionPane.showMessageDialog(null, "Revision No Encontrada","Denied",JOptionPane.WARNING_MESSAGE);
            }
        } else JOptionPane.showMessageDialog(null, "Error al Ingresar Datos","Denied",JOptionPane.ERROR_MESSAGE);
    }

    public void eliminarRevisionById(){
        Long idRevision = solicitarIdRevision();
        if (idRevision != null) {
            if (idRevision != 151841511L) {
                Revision revision = revisionUseCase.consultarRevisionById(idRevision);
                if (revision != null) {
                    Boolean eliminacion = revisionUseCase.eliminarRevision(idRevision);
                    if (eliminacion) {
                        JOptionPane.showMessageDialog(null, "Revision Eliminada con exito!","Denied",JOptionPane.INFORMATION_MESSAGE);  
                    } else JOptionPane.showMessageDialog(null, "Error al Eliminar Revision","Denied",JOptionPane.ERROR_MESSAGE); 
                } else JOptionPane.showMessageDialog(null, "Revision No Encontrada!","Denied",JOptionPane.ERROR_MESSAGE); 
            }
        } else JOptionPane.showMessageDialog(null, "Datos de ingreso Incorrectos!","Denied",JOptionPane.ERROR_MESSAGE);
    }

    public Revision solicitarDatosRegistro(){
        /*Varibales */
        Revision revision = new Revision();
        List<String> lstEstados = new ArrayList<>();

        //Crear los componentes
        JPanel panel = new JPanel(new GridLayout(4, 2, 5, 1));

        JLabel fechaLabel = new JLabel("Fecha De Revisión:");
        JTextField fechaField = new JTextField();
        fechaField.setFont(new Font("Monospaced", Font.BOLD, 12));

        JLabel idLabel = new JLabel("Id Avión:");
        JTextField idField = new JTextField();
        idField.setFont(new Font("Monospaced", Font.BOLD, 12));

        JLabel descripcionLabel = new JLabel("Descripción:");
        JTextField descripcionField = new JTextField();
        descripcionField.setFont(new Font("Monospaced", Font.BOLD, 13));

        JLabel lblEstado = new JLabel("Estado:");

        //LLAMADO A HEXAGONAL DE ENTIDAD REVISION_ESTADO
        RevisionEstadoService revisionEstadoService = new RevisionEstadoRepository();
        RevisionEstadoUseCase revisionEstadoUseCase = new RevisionEstadoUseCase(revisionEstadoService);
        RevisionEstadoController revisionEstadoController = new RevisionEstadoController(revisionEstadoUseCase);
        List<RevisionEstado> lstRevisionEstados = revisionEstadoController.listarEstados();
        String[] opcionesTgs;

        //USANDO CONSUMER
        Consumer<RevisionEstado> getEstado = revisionEstado -> lstEstados.add(revisionEstado.getEstado());
        lstRevisionEstados.forEach(getEstado);

        opcionesTgs = lstEstados.toArray(new String[0]);
        JComboBox<String> opTgsComboBox = new JComboBox<>(opcionesTgs);

        panel.setPreferredSize(new Dimension(450, 120));


        //VALIDACIONES DE ENTERO
        idField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
                    JOptionPane.showMessageDialog(panel, "Campo solo numeros", "Error", JOptionPane.ERROR_MESSAGE);
                    e.consume(); // Ignorar la tecla no numérica
                }
            }
        });
        //VALIDACIONES DE FECHA
        fechaField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE && c != '-') {
                    JOptionPane.showMessageDialog(panel, "Caracter  Ingreado Invalido!", "Error", JOptionPane.ERROR_MESSAGE);
                    e.consume(); // Ignorar la tecla no numérica
                } else if (fechaField.getText().length() >= 10) {
                    JOptionPane.showMessageDialog(panel, "No Se Puede Ingresar Mas Caracteres!", "Error", JOptionPane.ERROR_MESSAGE);
                    e.consume(); 
                }
            }
        });

        // Añadir los componentes al panel
        panel.add(fechaLabel);
        panel.add(fechaField);
        panel.add(idLabel);
        panel.add(idField);
        panel.add(fechaLabel);
        panel.add(fechaField);
        panel.add(descripcionLabel);
        panel.add(descripcionField);
        panel.add(lblEstado);
        panel.add(opTgsComboBox);

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
            try {
                String fecha_revision = fechaField.getText();
                String id_avion = idField.getText();
                String descrip = descripcionField.getText();
                String estado = opTgsComboBox.getSelectedItem().toString();
                if (fecha_revision.length() > 0 && id_avion.length() > 0 && descrip.length() > 0 ) {
                    revision.setFecha_revision(fecha_revision);
                    revision.setId_avion(Integer.parseInt(id_avion));
                    revision.setDescrip(descrip);
                    lstRevisionEstados.forEach(revisionEstado -> {
                        if (revisionEstado.getEstado().equals(estado)) {
                            revision.setId_estado(revisionEstado.getId_estado());
                        }
                    });   
                } else return null;
            } catch (Exception e) {
                return null;
            }
        } else revision.setDescrip("cancelar");
        return revision;
    }

    public Long solicitarIdRevision(){
        //Crear los componentes
        JPanel panel = new JPanel(new GridLayout(1, 2, 5, 1));

        JLabel lblIdRevision = new JLabel("Id Revisión:");
        JTextField txtIdRevision = new JTextField();
        txtIdRevision.setFont(new Font("Monospaced", Font.BOLD, 12));

        panel.setPreferredSize(new Dimension(250, 30));

        // Añadir los componentes al panel
        panel.add(lblIdRevision);
        panel.add(txtIdRevision);

        // Mostrar el panel en un JOptionPane
        int option = JOptionPane.showConfirmDialog(
            null, 
            panel, 
            "Airline, Hight All  The Time!", 
            JOptionPane.OK_CANCEL_OPTION, 
            JOptionPane.QUESTION_MESSAGE
        );
        if (option == JOptionPane.OK_OPTION) {
            try {
                String idRevision = txtIdRevision.getText();
                return Long.parseLong(idRevision); 
            } catch (Exception e) {
                return null;
            }
        } else {
            return 151841511L;
        }
    }

    public void  mostrarHistorialRevisiones(List<Revision> lstRevisiones){
        //DEFINIAR LAS FILAS DE LA TABLA
        Vector<Vector<Object>> revisiones = new Vector<>();

        //CONSUMIR LA LISTA DE REVISIONES Y AGREGARLA AL VECTOR
        Consumer<Revision> getDatos = revisionFila -> {
            Vector<Object> fila = new Vector<>();
            fila.add(revisionFila.getId_avion());
            fila.add(revisionFila.getFecha_revision());
            fila.add(revisionFila.getEstado());
            fila.add(revisionFila.getDescrip());
            revisiones.add(fila);
        };
        lstRevisiones.forEach(getDatos);

        //DEFINO ENCABEZADO
        Vector<Object> encabezado = new Vector<>();
        encabezado.add("Id Avión");
        encabezado.add("Fecha De Revisión");
        encabezado.add("Estado");
        encabezado.add("Descripción");
        
        DefaultTableModel modelo = new DefaultTableModel(revisiones, encabezado);
        JTable tabla = new JTable(modelo);
        tabla.setPreferredScrollableViewportSize(new Dimension(800, 100));
        tabla.setBackground(Color.LIGHT_GRAY);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JScrollPane scrollPane = new JScrollPane(tabla);
        panel.add(scrollPane);
        JOptionPane.showConfirmDialog(
            null, 
            panel, 
            "Airline, Hight All  The Time!", 
            JOptionPane.CLOSED_OPTION, 
            JOptionPane.PLAIN_MESSAGE
        );
    }

    public Revision obtenerRevisionModificada(Revision revision){

        List<String> lstEstados = new ArrayList<>();
        //Crear los componentes
        JPanel panel = new JPanel(new GridLayout(4, 2, 5, 1));

        JLabel fechaLabel = new JLabel("Fecha De Revisión:");
        JTextField fechaField = new JTextField();
        fechaField.setText(revision.getFecha_revision());
        fechaField.setFont(new Font("Monospaced", Font.BOLD, 12));

        JLabel idLabel = new JLabel("Id Avión:");
        JTextField idField = new JTextField();
        idField.setText(String.valueOf(revision.getId_avion()));
        idField.setFont(new Font("Monospaced", Font.BOLD, 12));

        JLabel descripcionLabel = new JLabel("Descripción:");
        JTextField descripcionField = new JTextField();
        descripcionField.setText(revision.getDescrip());
        descripcionField.setFont(new Font("Monospaced", Font.BOLD, 13));

        JLabel lblEstado = new JLabel("Estado:");

        //LLAMADO A HEXAGONAL DE ENTIDAD REVISION_ESTADO
        RevisionEstadoService revisionEstadoService = new RevisionEstadoRepository();
        RevisionEstadoUseCase revisionEstadoUseCase = new RevisionEstadoUseCase(revisionEstadoService);
        RevisionEstadoController revisionEstadoController = new RevisionEstadoController(revisionEstadoUseCase);
        List<RevisionEstado> lstRevisionEstados = revisionEstadoController.listarEstados();
        String[] opcionesTgs;

        //USANDO CONSUMER
        Consumer<RevisionEstado> getEstado = revisionEstado -> lstEstados.add(revisionEstado.getEstado());
        lstRevisionEstados.forEach(getEstado);

        opcionesTgs = lstEstados.toArray(new String[0]);
        JComboBox<String> opTgsComboBox = new JComboBox<>(opcionesTgs);

        panel.setPreferredSize(new Dimension(450, 120));


        //VALIDACIONES DE ENTERO
        idField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
                    JOptionPane.showMessageDialog(panel, "Campo solo numeros", "Error", JOptionPane.ERROR_MESSAGE);
                    e.consume(); // Ignorar la tecla no numérica
                }
            }
        });
        //VALIDACIONES DE FECHA
        fechaField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE && c != '-') {
                    JOptionPane.showMessageDialog(panel, "Caracter  Ingreado Invalido!", "Error", JOptionPane.ERROR_MESSAGE);
                    e.consume(); // Ignorar la tecla no numérica
                } else if (fechaField.getText().length() >= 10) {
                    JOptionPane.showMessageDialog(panel, "No Se Puede Ingresar Mas Caracteres!", "Error", JOptionPane.ERROR_MESSAGE);
                    e.consume(); 
                }
            }
        });

        // Añadir los componentes al panel
        panel.add(fechaLabel);
        panel.add(fechaField);
        panel.add(idLabel);
        panel.add(idField);
        panel.add(fechaLabel);
        panel.add(fechaField);
        panel.add(descripcionLabel);
        panel.add(descripcionField);
        panel.add(lblEstado);
        panel.add(opTgsComboBox);

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
            try {
                String fecha_revision = fechaField.getText();
                String id_avion = idField.getText();
                String descrip = descripcionField.getText();
                String estado = opTgsComboBox.getSelectedItem().toString();
                if (fecha_revision.length() > 0 && id_avion.length() > 0 && descrip.length() > 0 ) {
                    revision.setFecha_revision(fecha_revision);
                    revision.setId_avion(Integer.parseInt(id_avion));
                    revision.setDescrip(descrip);
                    lstRevisionEstados.forEach(revisionEstado -> {
                        if (revisionEstado.getEstado().equals(estado)) {
                            revision.setId_estado(revisionEstado.getId_estado());
                        }
                    });   
                } else return null;
            } catch (Exception e) {
                return null;
            }
        } else revision.setDescrip("cancelar");
        return revision;
    }
}

