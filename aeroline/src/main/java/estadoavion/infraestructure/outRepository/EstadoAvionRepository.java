package estadoavion.infraestructure.outRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aeroline.DatabaseConfig;

import estadoavion.domain.entity.EstadoAvion;
import estadoavion.domain.service.EstadoAvionService;


public class EstadoAvionRepository implements EstadoAvionService{

    @Override
    public List<EstadoAvion> listarEstadosAvion() {
        String sql = "SELECT id_estado, nombre FROM estadoAvion";
        List<EstadoAvion> lstEstadosAvion = new ArrayList<>();
        EstadoAvion estadoAvion = null;

        try (Connection connection = DatabaseConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    estadoAvion = new EstadoAvion();

                    estadoAvion.setId_estado(Long.parseLong(resultSet.getString("id_estado")));
                    estadoAvion.setNombreEstado(resultSet.getString("nombre"));
                    lstEstadosAvion.add(estadoAvion);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lstEstadosAvion;
    }
}
