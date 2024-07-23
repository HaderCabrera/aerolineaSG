package escala.infraestructure.outRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aeroline.DatabaseConfig;

import escala.domain.entity.Escala;
import escala.domain.service.EscalaService;

public class EscalaRepository implements EscalaService{

    @Override
    public List<Escala> consultarEscalarByDescripcion(Long id_trayecto) {
        String sql = "SELECT id_vuelo, origen, destino FROM escala WHERE id_trayecto = ?";
        List<Escala> lstEscalasByTrayecto = new ArrayList<>();;
        Escala escala = null;

        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id_trayecto);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    escala = new Escala();
                    escala.setId_vuelo(resultSet.getLong("id_vuelo"));
                    escala.setId_trayecto(id_trayecto);
                    escala.setInicio(resultSet.getString("origen"));
                    System.out.println("ESTO ESTOY INGRESANDO A ESCALA DESTINO: " + resultSet.getString("destino"));
                    escala.setDestino(resultSet.getString("destino"));
                    lstEscalasByTrayecto.add(escala);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lstEscalasByTrayecto;
    }


}
