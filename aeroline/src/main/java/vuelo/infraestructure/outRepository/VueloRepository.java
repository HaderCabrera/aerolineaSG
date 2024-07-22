package vuelo.infraestructure.outRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.aeroline.DatabaseConfig;

import vuelo.domain.service.VueloService;

public class VueloRepository implements VueloService{

    @Override
    public List<Long> getIdAvionByIdTrayecto(Long id_trayecto) {
        String sql = "CALL obtenerIdAvionByIdTrayecto(?);";
        List<Long> lstIdsAvion = new ArrayList<>();

        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id_trayecto);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    lstIdsAvion.add(resultSet.getLong("id_avion"));
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al generar consulta.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return lstIdsAvion;
    }

}
 