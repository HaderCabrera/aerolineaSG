package revisionEstado.infraestructure.outRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aeroline.DatabaseConfig;

import revisionEstado.domain.entity.RevisionEstado;
import revisionEstado.domain.service.RevisionEstadoService;

public class RevisionEstadoRepository implements RevisionEstadoService {

    @Override
    public List<RevisionEstado> listarEstados() {
        String sql = "SELECT id_estado, estado FROM estado_revision";
        List<RevisionEstado> lstEstadosRevision = new ArrayList<>();
        RevisionEstado revisionEstado = null;

        try (Connection connection = DatabaseConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    revisionEstado = new RevisionEstado();

                    revisionEstado.setId_estado(Long.parseLong(resultSet.getString("id_estado")));
                    revisionEstado.setEstado(resultSet.getString("estado"));

                    lstEstadosRevision.add(revisionEstado);

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lstEstadosRevision;
    }

}
