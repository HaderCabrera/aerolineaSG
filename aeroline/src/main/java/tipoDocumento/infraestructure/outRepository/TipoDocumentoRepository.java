package tipoDocumento.infraestructure.outRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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


}
