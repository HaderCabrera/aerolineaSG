package user.infraestructure.outRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.aeroline.DatabaseConfig;

import user.domain.entity.User;
import user.domain.service.UserService;

public class UserRepository implements UserService{

    @Override
    public User consultarUser(String userName, String contraseña) {
        String sql = "SELECT id_usuario, nombre_usuario, pass, id_rolUsuario FROM usuario WHERE nombre_usuario = ? && pass = ? ";
        User user = null;

        try (Connection connection = DatabaseConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, userName);
            statement.setString(2, contraseña);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = new User();
                    user.setId_usuario(resultSet.getInt("id_usuario"));
                    user.setNombre_usuario(resultSet.getString("nombre_usuario"));
                    user.setPass(resultSet.getString("pass"));
                    user.setId_rolUsuario(resultSet.getInt("id_rolUsuario"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
        
    }

    @Override
    public void accederUser(User user) {
        // Logica de ACCEDER COMO USUARIO
    }

}
