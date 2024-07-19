package ciudad.infraestructure.outRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aeroline.DatabaseConfig;

import ciudad.domain.entity.Ciudad;
import ciudad.domain.service.CiudadService;


public class CiudadRepository implements CiudadService{

    @Override
    public List<Ciudad> listarCiudades() {
        String sql = "SELECT id_ciudad, nombre, id_pais FROM ciudad";
        List<Ciudad> lstCiudades = new ArrayList<>();
        Ciudad ciudad = null;

        try (Connection connection = DatabaseConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    ciudad = new Ciudad();

                    ciudad.setId_ciudad(resultSet.getString("id_ciudad"));
                    ciudad.setNombre(resultSet.getString("nombre"));
                    ciudad.setId_pais(resultSet.getString("id_pais"));

                    lstCiudades.add(ciudad);

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lstCiudades;
    }

}
