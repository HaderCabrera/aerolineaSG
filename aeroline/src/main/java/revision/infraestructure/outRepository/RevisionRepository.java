package revision.infraestructure.outRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.aeroline.DatabaseConfig;

import revision.domain.entity.Revision;
import revision.domain.service.RevisionService;

public class RevisionRepository implements RevisionService {

    @Override
    public boolean registrarRevision(Revision revision) {
                String sql = "INSERT INTO revision (placa_identificacion, capacidad, fabricacion_fecha, id_estado, id_modelo)\n" + //
                        "VALUES (?, ?, ?, ?, ?);\n" + //
                        "";

        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql,
                        PreparedStatement.RETURN_GENERATED_KEYS)) {

            // statement.setString(1, re.getPlaca_identificacion());
            // statement.setInt(2   , avion.getCapacidad());
            // statement.setDate(3, avion.getFabricacion_fecha());
            // statement.setInt(4, avion.getId_estado());
            // statement.setInt(5, avion.getId_modelo());
            statement.executeUpdate();

        } catch (SQLException e) { 
            e.printStackTrace();
            return false;
        }
        
        return true;
    }

}
