package cliente.infraestructure.outRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.aeroline.DatabaseConfig;

import cliente.domain.entity.Cliente;
import cliente.domain.service.ClienteService;

public class ClienteRepository implements ClienteService{

    @Override
    public Cliente consultarCliente(Long id_cliente) {
        String sql = "SELECT id_cliente, documento, nombre1, nombre2, apellidos, fecha_nacimiento, email, id_tipo_documento FROM cliente WHERE id_cliente = ?";
        Cliente cliente = null;

        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id_cliente);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    cliente = new Cliente();
                    cliente.setId_cliente(resultSet.getLong("id_cliente"));
                    cliente.setDocumento(resultSet.getLong("documento"));
                    cliente.setNombre1(resultSet.getString("nombre1"));
                    cliente.setNombre2(resultSet.getString("nombre2"));
                    cliente.setApellidos(resultSet.getString("apellidos"));
                    cliente.setFecha_nacimiento(resultSet.getString("fecha_nacimiento"));
                    cliente.setEmail(resultSet.getString("email"));
                    cliente.setId_tipo_documento(resultSet.getLong("id_tipo_documento"));
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al generar consulta.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return cliente;
    }

    @Override
    public Boolean updateCliente(Cliente cliente) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateCliente'");
    }

}
