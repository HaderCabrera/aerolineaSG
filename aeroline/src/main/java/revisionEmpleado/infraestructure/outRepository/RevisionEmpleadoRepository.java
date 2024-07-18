package revisionEmpleado.infraestructure.outRepository;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.aeroline.DatabaseConfig;

import revisionEmpleado.domain.entity.RevisionEmpleado;
import revisionEmpleado.domain.service.RevisionEmpleadoService;

public class RevisionEmpleadoRepository implements RevisionEmpleadoService{

    @Override
    public Long registrarRevisionEmpleado(RevisionEmpleado revisionEmpleado) {
                String sql =
                            "INSERT INTO revision_empleado (id_revision, id_empleado)\n" + //
                            "VALUES\n" + //
                            "(?, ?);\n" + //
                            "";

        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql,
                        PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setLong(1, revisionEmpleado.getId_revision());
            statement.setString(2, revisionEmpleado.getId_empleado());

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


}
