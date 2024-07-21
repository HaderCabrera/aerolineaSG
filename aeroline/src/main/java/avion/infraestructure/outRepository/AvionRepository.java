package avion.infraestructure.outRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.aeroline.DatabaseConfig;

import avion.domain.entity.Avion;
import avion.domain.service.AvionService;


public class AvionRepository implements AvionService{

    @Override
    public boolean registrarAvion(Avion avion) {
        String sql = "INSERT INTO avion (placa_identificacion, capacidad, fabricacion_fecha, id_estado, id_modelo)\n" + //
                        "VALUES (?, ?, ?, ?, ?);\n" + //
                        "";

        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql,
                        PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, avion.getPlaca_identificacion());
            statement.setInt(2   , avion.getCapacidad());
            statement.setString(3, avion.getFabricacion_fecha());
            statement.setInt(4, avion.getId_estado());
            statement.setInt(5, avion.getId_modelo());
            statement.executeUpdate();

        } catch (SQLException e) { 
            e.printStackTrace();
            return false;
        }
        
        return true;
    }

    @Override
    public Avion consultarAvionByPlaca(String placa) {
        String sql = " CALL ObtenerDatosAvion(?);";
        Avion avion = null;

        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, placa);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {

                    avion = new Avion();
                    avion.setId_avion(resultSet.getInt("id_avion"));
                    avion.setPlaca_identificacion(resultSet.getString("placa_identificacion"));
                    avion.setCapacidad(resultSet.getInt("capacidad"));
                    avion.setFabricacion_fecha(resultSet.getString("fabricacion_fecha"));
                    avion.setEstado(resultSet.getString("estado"));
                    avion.setModelo(resultSet.getString("modelo"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return avion;
    }

    @Override
    public Boolean updateAvion(Avion avion) {
        String sql = "UPDATE avion SET placa_identificacion = ?, capacidad = ?, fabricacion_fecha = ?, id_estado = ?, id_modelo = ? WHERE id_avion = ?";
        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, avion.getPlaca_identificacion());
            statement.setLong(2, avion.getCapacidad());
            statement.setString(3, avion.getFabricacion_fecha());
            statement.setLong(4, avion.getId_estado());
            statement.setLong(5, avion.getId_modelo());
            statement.setLong(6, avion.getId_avion());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true; 
    }

    @Override
    public Boolean eliminarAvionByPlaca(String placa) {
        String sql = "DELETE FROM avion WHERE placa_identificacion = ?;";
        try (Connection connection = DatabaseConfig.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, placa);
            statement.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
    }

}
