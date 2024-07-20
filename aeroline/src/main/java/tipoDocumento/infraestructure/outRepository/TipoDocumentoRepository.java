package tipoDocumento.infraestructure.outRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.aeroline.DatabaseConfig;

import tipoDocumento.domain.entity.TipoDocumento;
import tipoDocumento.domain.service.TipoDocumentoService;

public class TipoDocumentoRepository implements TipoDocumentoService{

    @Override
    public void crearTipoDocumento(TipoDocumento tipoDocumento) {
        String sql = "INSERT INTO tipoDocumento (nombreDoc) VALUES (?)";

        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql,
                        PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, tipoDocumento.getNombreDoc());

            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    JOptionPane.showMessageDialog(null, "Registro exitoso!", "Confirmacion", JOptionPane.INFORMATION_MESSAGE);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<TipoDocumento> listarTipoDocumento() {
        String sql = "SELECT id_tipo_documento, nombreDoc FROM tipoDocumento";
        List<TipoDocumento> lstTipoDocumentos = new ArrayList<>();
        TipoDocumento tipoDocumento = null;

        try (Connection connection = DatabaseConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    tipoDocumento = new TipoDocumento();

                    tipoDocumento.setId_tipo_documento(Long.parseLong(resultSet.getString("id_tipo_documento")));
                    tipoDocumento.setNombreDoc(resultSet.getString("nombreDoc"));

                    lstTipoDocumentos.add(tipoDocumento);

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lstTipoDocumentos;
    }


}
