package aeropuerto.infraestructure.outRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import com.aeroline.DatabaseConfig;

import aeropuerto.domain.entity.Aeropuerto;
import aeropuerto.domain.service.AeropuertoService;

public class AeropuertoRepository implements AeropuertoService {

    @Override
    public Boolean registrarAeropuerto(Aeropuerto aeropuerto) {

       String  sql = "INSERT INTO aeropuerto (nombre, id_ciudad) VALUES (?, ?);";
            try (Connection connection = DatabaseConfig.getConnection(); 
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

                statement.setString(1, aeropuerto.getNombre());
                statement.setString(2, aeropuerto.getId_ciudad());
                statement.executeUpdate();

        } catch (SQLException e) { 
            e.printStackTrace();
            return false;
        }
        
        return true;
    }

}
