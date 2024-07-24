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
    //INGRESANDO LOS DATOS A TARIFA DE LA CONSULTA
    @Override
    public List<Tarifa> listarTarifasByTrayecto(int identificador) {
        String sql = "";
        List<Tarifa> lstTarifasValidades = new ArrayList<>();
        Tarifa tarifa = null;
        switch (identificador) {
            case 1:
                sql = "SELECT id_tarifa, id_tipoClase, precio_tarifa, descripcion FROM tarifa WHERE id_tarifa = ? OR id_tarifa = ? OR id_tarifa = ?;";
                try (Connection connection = DatabaseConfig.getConnection();
                        PreparedStatement statement = connection.prepareStatement(sql)) {

                    statement.setLong(1, 1);
                    statement.setLong(2, 3);
                    statement.setLong(3, 5);
                    try (ResultSet resultSet = statement.executeQuery()) {
                        while(resultSet.next()) {
                            tarifa = new Tarifa();
                            tarifa.setId_tarifa(Long.valueOf(resultSet.getString("id_tarifa")));
                            tarifa.setId_tipoClase(Long.valueOf(resultSet.getString("id_tipoClase")));
                            tarifa.setPrecio_tarifa(Double.valueOf(resultSet.getString("precio_tarifa")));
                            tarifa.setDescripcion(resultSet.getString("descripcion"));
                            lstTarifasValidades.add(tarifa);
                        }
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                sql = "SELECT id_tarifa, id_tipoClase, precio_tarifa, descripcion FROM tarifa WHERE id_tarifa = ? OR id_tarifa = ? OR id_tarifa = ?";
                try (Connection connection = DatabaseConfig.getConnection();
                        PreparedStatement statement = connection.prepareStatement(sql)) {

                    statement.setLong(1, 2);
                    statement.setLong(2, 4);
                    statement.setLong(3, 6);
                    try (ResultSet resultSet = statement.executeQuery()) {
                        if (resultSet.next()) {
                            tarifa = new Tarifa();
                            tarifa.setId_tarifa(Long.valueOf(resultSet.getString("id_tarifa")));
                            tarifa.setId_tipoClase(Long.valueOf(resultSet.getString("id_tipoClase")));
                            tarifa.setPrecio_tarifa(Double.valueOf(resultSet.getString("precio_tarifa")));
                            tarifa.setDescripcion(resultSet.getString("descripcion"));
                            lstTarifasValidades.add(tarifa);
                        }
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                System.out.println("LLEGO ACA");
                sql = "SELECT id_tarifa, id_tipoClase, precio_tarifa, descripcion FROM tarifa;";
                try (Connection connection = DatabaseConfig.getConnection();
                        PreparedStatement statement = connection.prepareStatement(sql)) {

                    try (ResultSet resultSet = statement.executeQuery()) {
                        while (resultSet.next()) {
                            tarifa = new Tarifa();
                            tarifa.setId_tarifa(Long.valueOf(resultSet.getString("id_tarifa")));
                            tarifa.setId_tipoClase(Long.valueOf(resultSet.getString("id_tipoClase")));
                            tarifa.setPrecio_tarifa(Double.valueOf(resultSet.getString("precio_tarifa")));
                            tarifa.setDescripcion(resultSet.getString("descripcion"));
                            lstTarifasValidades.add(tarifa);
                        }
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
        }

        return lstTarifasValidades;
    }

}
