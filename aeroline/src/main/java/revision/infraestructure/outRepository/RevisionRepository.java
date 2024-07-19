package revision.infraestructure.outRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.aeroline.DatabaseConfig;

import revision.domain.entity.Revision;
import revision.domain.service.RevisionService;

public class RevisionRepository implements RevisionService {

    @Override
    public Long registrarRevision(Revision revision) {
        String sql = "INSERT INTO revision (fecha_revision, id_avion, descrip, id_estado_revision) VALUES \n" + //
                     "(?, ?, ?, ?);\n" + //
                     "";

        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql,
                        PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, revision.getFecha_revision());
            statement.setInt(2, revision.getId_avion());
            statement.setString(3, revision.getDescrip());
            statement.setLong(4, revision.getId_estado());
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

    @Override
    public List<Revision> listarRevisionesByPlaca(String placa_avion) {

        Revision revision = null;
        List<Revision> lstRevisiones = new ArrayList<>();
        String sql = "CALL ObtenerHistorialRevisiones(?);";

        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, placa_avion);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    revision = new Revision();
                    revision.setId_avion(Integer.parseInt(resultSet.getString("id_avion"))); 
                    revision.setFecha_revision(resultSet.getString("fecha_revision"));
                    revision.setDescrip(resultSet.getString("descrip"));
                    revision.setEstado(resultSet.getString("estado"));
                    revision.setEstado(resultSet.getString("estado"));
                    lstRevisiones.add(revision);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lstRevisiones;
    }
}
