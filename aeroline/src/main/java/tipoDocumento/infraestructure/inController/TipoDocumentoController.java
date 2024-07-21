package tipoDocumento.infraestructure.inController;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import tipoDocumento.application.TipoDocumentoUseCase;
import tipoDocumento.domain.entity.TipoDocumento;

public class TipoDocumentoController {
    private final TipoDocumentoUseCase tipoDocumentoUseCase;

    public TipoDocumentoController(TipoDocumentoUseCase tipoDocumentoUseCase) {
        this.tipoDocumentoUseCase = tipoDocumentoUseCase;
    }
    
    public void crearTipoDocumento(){
        TipoDocumento tipoDocumento = solicitarDatosRegistro();
        if (tipoDocumento != null) {
            if (tipoDocumento.getNombreDoc() != "cancelado") {
                tipoDocumentoUseCase.crearTipoDocumento(tipoDocumento);
            }
        } else JOptionPane.showMessageDialog(null, "Error al Ingresar Datos!", "Denied", JOptionPane.ERROR_MESSAGE); 
    }

    public List<TipoDocumento> listarTipoDocumento(){
        return tipoDocumentoUseCase.listarTipoDocumento();
    }

    public void updateTipoDocumento(){
        Long idTipoDocumento = solicitarIdTipoDocumento();
        if (idTipoDocumento != null) {
            if (idTipoDocumento != 151841511L) {
                TipoDocumento tipoDocumento = tipoDocumentoUseCase.consultarTipoDocumentoById(idTipoDocumento);
                if (tipoDocumento != null) {
                    tipoDocumento = obtenerTipoDocumentoModificado(tipoDocumento);
                    if (tipoDocumento != null) {
                        if (tipoDocumento.getNombreDoc() != "cancelado") {
                            Boolean actualizacion = tipoDocumentoUseCase.updateTipoDocumento(tipoDocumento);
                            if (actualizacion != null) {
                                JOptionPane.showMessageDialog(null, "Actualizacion exitosa!","Denied",JOptionPane.INFORMATION_MESSAGE);
                            } else JOptionPane.showMessageDialog(null, "Error al Actualizar Tipo de Documento!","Denied",JOptionPane.ERROR_MESSAGE); 
                        }
                    } else JOptionPane.showMessageDialog(null, "Datos Ingresados Incorrectos!","Denied",JOptionPane.ERROR_MESSAGE);
                } else JOptionPane.showMessageDialog(null, "Tipo de Documento No Encontrado!","Denied",JOptionPane.ERROR_MESSAGE);

            }
        } else JOptionPane.showMessageDialog(null, "Datos De Ingreso Incorrectos!","Denied",JOptionPane.ERROR_MESSAGE);
    }

    public void eliminarTipoDocumentoById(){
        Long idTipoDocumento = solicitarIdTipoDocumento();
        if (idTipoDocumento != null) {
            if (idTipoDocumento != 151841511L) {
                TipoDocumento tipoDocumento = tipoDocumentoUseCase.consultarTipoDocumentoById(idTipoDocumento);
                if (tipoDocumento != null) {
                    Boolean eliminacion = tipoDocumentoUseCase.eliminarTipoDocumentoById(idTipoDocumento);
                    if (eliminacion) {
                        JOptionPane.showMessageDialog(null, "Revision Eliminada con exito!","Denied",JOptionPane.INFORMATION_MESSAGE);  
                    } else JOptionPane.showMessageDialog(null, "Error al Eliminar Revision","Denied",JOptionPane.ERROR_MESSAGE);
                } else JOptionPane.showMessageDialog(null, "Tipo de Documento No Encontrado","Denied",JOptionPane.ERROR_MESSAGE);
            }
        } else JOptionPane.showMessageDialog(null, "Datos de ingreso Incorrectos!","Denied",JOptionPane.ERROR_MESSAGE);
    }

    public void consultarTipoDocumentoById(){
        Long idTipoDocumento = solicitarIdTipoDocumento();
        if (idTipoDocumento != null) {
            if (idTipoDocumento != 151841511L) {
                TipoDocumento tipoDocumento = tipoDocumentoUseCase.consultarTipoDocumentoById(idTipoDocumento);
                if (tipoDocumento != null) {
                    mostrarTipoDocumento(tipoDocumento);   
                } else JOptionPane.showMessageDialog(null, "Cliente No Encontrado!", "Denied", JOptionPane.ERROR_MESSAGE);  
            }
        } else JOptionPane.showMessageDialog(null, "Datos de Ingreso Invalidos!", "Denied", JOptionPane.ERROR_MESSAGE);
    }

    public TipoDocumento solicitarDatosRegistro(){
        TipoDocumento tipoDocumento = new TipoDocumento();

        JPanel panel = new JPanel(new GridLayout(1, 1, 5, 1));

        JLabel txtTipoDocumento = new JLabel("Nombre tipo documento:");
        JTextField lblTipoDocumento = new JTextField();
        lblTipoDocumento.setFont(new Font("Monospaced", Font.BOLD, 12));

        panel.setPreferredSize(new Dimension(250, 30));

        //Agreganmos elementos al panel
        panel.add(txtTipoDocumento);
        panel.add(lblTipoDocumento);

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
            String nombreDoc = lblTipoDocumento.getText();

            try {
                if (nombreDoc.length() > 0) {
                    tipoDocumento.setNombreDoc(nombreDoc);
                } else {
                    return null;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(panel, "Error al obtener datos de registro", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        } else {
            tipoDocumento.setNombreDoc("cancelado");
        }
        return tipoDocumento;
    }

    public Long solicitarIdTipoDocumento(){
        //Crear los componentes
        JPanel panel = new JPanel(new GridLayout(1, 2, 5, 1));

        JLabel lblIdTipoDocumento = new JLabel("Id Tipo de Documento:");
        JTextField txtIdTipoDocumento = new JTextField();
        txtIdTipoDocumento.setFont(new Font("Monospaced", Font.BOLD, 12));

        panel.setPreferredSize(new Dimension(350, 30));

        // Añadir los componentes al panel
        panel.add(lblIdTipoDocumento);
        panel.add(txtIdTipoDocumento);

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
                String idRevision = txtIdTipoDocumento.getText();
                return Long.parseLong(idRevision); 
            } catch (Exception e) {
                return null;
            }
        } else {
            return 151841511L;
        }
    }

    public TipoDocumento obtenerTipoDocumentoModificado(TipoDocumento tipoDocumento){

        JPanel panel = new JPanel(new GridLayout(1, 1, 5, 1));

        JLabel txtTipoDocumento = new JLabel("Nombre tipo documento:");
        JTextField lblTipoDocumento = new JTextField();
        lblTipoDocumento.setText(tipoDocumento.getNombreDoc());
        lblTipoDocumento.setFont(new Font("Monospaced", Font.BOLD, 12));

        panel.setPreferredSize(new Dimension(450, 30));

        //Agreganmos elementos al panel
        panel.add(txtTipoDocumento);
        panel.add(lblTipoDocumento);

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
            String nombreDoc = lblTipoDocumento.getText();

            try {
                if (nombreDoc.length() > 0) {
                    tipoDocumento.setNombreDoc(nombreDoc);
                } else {
                    return null;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(panel, "Error al obtener datos de registro", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        } else {
            tipoDocumento.setNombreDoc("cancelado");
        }
        return tipoDocumento;
    }

    public void mostrarTipoDocumento(TipoDocumento tipoDocumento){
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 5));

        JLabel lblIdTipoDocumento = new JLabel("Id Tipo Documento:");
        JLabel lblIdTipoDocumentoValor = new JLabel(String.valueOf(tipoDocumento.getId_tipo_documento()));

        JLabel lblTipoDocumento = new JLabel("Documento:");
        JLabel lblTipoDocumentoValor = new JLabel(String.valueOf(tipoDocumento.getNombreDoc()));

        //JPanel.setPreferredSize(new Dimension(350, 120));
        panel.add(lblIdTipoDocumento);
        panel.add(lblIdTipoDocumentoValor);
        panel.add(lblTipoDocumento);
        panel.add(lblTipoDocumentoValor);

        // Mostrar el panel en un JOptionPane
        JOptionPane.showConfirmDialog(
            null, 
            panel, 
            "Airline, Hight All  The Time!", 
            JOptionPane.CLOSED_OPTION, 
            JOptionPane.PLAIN_MESSAGE
        );
    }
}
