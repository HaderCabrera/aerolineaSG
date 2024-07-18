package revision.infraestructure.outRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.aeroline.DatabaseConfig;

import revision.domain.entity.Revision;
import revision.domain.service.RevisionService;

public class RevisionRepository implements RevisionService {

    @Override
    public Long registrarRevision(Revision revision) {
        String sql = "INSERT INTO revision (fecha_revision, id_avion, descrip) VALUES \n" + //
                     "(?, ?, ?);\n" + //
                     "";

        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql,
                        PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, revision.getFecha_revision());
            statement.setInt(2, revision.getId_avion());
            statement.setString(3, revision.getDescrip());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    Long idRevision = generatedKeys.getLong(1);  
                    return idRevision;
                } 
            }

        } catch (SQLException e) { 
            e.printStackTrace();
            String mensaje = "Registro Fallido!"; 
            JOptionPane.showMessageDialog(null, mensaje, "Denied", JOptionPane.WARNING_MESSAGE);
            return 0L;
        }
        
        return 0L;
    }

	@Override
	public Boolean eliminarRevision(Long IdRevision) {
        String sql = "DELETE FROM revision WHERE IdRevision = ?;";
        try (Connection connection = DatabaseConfig.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, IdRevision);
            statement.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
	}
}
