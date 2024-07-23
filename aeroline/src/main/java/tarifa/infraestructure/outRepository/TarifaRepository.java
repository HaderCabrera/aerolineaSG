package tarifa.infraestructure.outRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aeroline.DatabaseConfig;

import tarifa.domain.entity.Tarifa;
import tarifa.domain.service.TarifaService;

public class TarifaRepository implements TarifaService{

    @Override
    public List<Tarifa> listarTarifasByTrayecto(List<Integer> idTarifas) {
        List<Tarifa> lstTarifasValidades = new ArrayList<>();
        for (Integer idTarifa : idTarifas) {
            String sql = "SELECT id_tarifa, id_tipoClase, precio_tarifa, descripcion FROM tarifa WHERE id_tarifa = ?";
            Tarifa tarifa = null;
            try (Connection connection = DatabaseConfig.getConnection();
                    PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setLong(1, idTarifa);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        tarifa = new Tarifa();
                        tarifa.setId_tarifa(Long.valueOf(resultSet.getString("id_tarifa")));
                        tarifa.setId_tipoClase(Long.valueOf(resultSet.getString("id_tipoClase")));
                        tarifa.setPrecio_tarifa(resultSet.getDouble("precio_tarifa"));
                        tarifa.setDescripcion(resultSet.getString("descripcion"));
                        lstTarifasValidades.add(tarifa);
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return lstTarifasValidades;
    }

}
