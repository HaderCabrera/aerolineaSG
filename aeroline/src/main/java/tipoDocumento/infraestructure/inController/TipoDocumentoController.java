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
            tipoDocumentoUseCase.crearTipoDocumento(tipoDocumento);
        } else {
            JOptionPane.showMessageDialog(null, "Dato ingresado invalido!", "Denied", JOptionPane.WARNING_MESSAGE);
        } 
    }

    public List<TipoDocumento> listarTipoDocumento(){
        return tipoDocumentoUseCase.listarTipoDocumento();
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
            return null;
        }
        return tipoDocumento;
    }


}
