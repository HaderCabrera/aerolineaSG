package modeloavion.infraestructure.outRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aeroline.DatabaseConfig;

import modeloavion.domain.entity.ModeloAvion;
import modeloavion.domain.service.ModeloAvionService;

public class ModeloAvionRepository implements ModeloAvionService{

    @Override
    public List<ModeloAvion> listarModelosAvion() {
        String sql = "SELECT id_modelo, nombre,id_manufactura FROM modelo";
        List<ModeloAvion> lstModelosAvion = new ArrayList<>();
        ModeloAvion modeloAvion = null;

        try (Connection connection = DatabaseConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    modeloAvion = new ModeloAvion();
                    modeloAvion.setId_modelo(Long.parseLong(resultSet.getString("id_modelo")));
                    modeloAvion.setNombreModelo(resultSet.getString("nombre"));
                    modeloAvion.setIdFabricante(resultSet.getLong("id_manufactura"));
                    lstModelosAvion.add(modeloAvion);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lstModelosAvion;
    }
}
