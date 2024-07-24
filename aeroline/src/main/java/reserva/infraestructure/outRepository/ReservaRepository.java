package reserva.infraestructure.outRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.aeroline.DatabaseConfig;

import reserva.domain.entity.Reserva;
import reserva.domain.service.ReservaService;


public class ReservaRepository implements ReservaService{

    @Override
    public Boolean registrarReserva(Reserva reserva) {
        String sql = "INSERT INTO reserva (fecha_reserva, id_cliente, id_estadoReserva, id_puesto, id_tarifa)\n" + //
                        "VALUES (?, ?, ?, ?, ?);";

        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql,
                        PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, reserva.getFecha_reserva());
            statement.setLong(2, reserva.getId_cliente());
            statement.setLong(3, reserva.getId_estadoReserva());
            statement.setLong(4, reserva.getId_puesto());
            statement.setLong(5, reserva.getId_tarifa());

            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) { 
                    return true;
                } 
            }

        } catch (SQLException e) { 
            e.printStackTrace();
            String mensaje = "Registro Fallido!"; 
            JOptionPane.showMessageDialog(null, mensaje, "Denied", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return false;
    }

    @Override
    public Reserva consultarReservaByCliente(Long idCliente) {
        String sql = "CALL ObtenerDatosReservaByCliente(?);";
        Reserva reserva = null;

        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, idCliente);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    reserva = new Reserva();
                    //reserva.setId_reserva(Long.valueOf(resultSet.getInt("id_reserva")));
                    reserva.setFecha_reserva(resultSet.getString("fecha_reserva"));
                    reserva.setEstadoReserva(resultSet.getString("estado"));
                    reserva.setPuesto(resultSet.getString("puesto"));
                    reserva.setTarifa(resultSet.getString("tarifa"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reserva;
    }

    @Override
    public Reserva consultarReservaByTrayecto(Long idTrayecto) {
        String sql = "CALL ObtenerDatosReservaByTrayecto(?);";
        Reserva reserva = null;

        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, idTrayecto);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    reserva = new Reserva();
                    //reserva.setId_reserva(Long.valueOf(resultSet.getInt("id_reserva")));
                    reserva.setFecha_reserva(resultSet.getString("fecha_reserva"));
                    reserva.setEstadoReserva(resultSet.getString("estado"));
                    reserva.setPuesto(resultSet.getString("puesto"));
                    reserva.setTarifa(resultSet.getString("tarifa"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reserva;
    }

}
