package user.infraestructure.outRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aeroline.DatabaseConfig;

import user.domain.entity.User;
import user.domain.service.UserService;

public class UserRepository implements UserService{

    @Override
    public User findUser(String name_user, String contraseña_user) {
        String sql = "SELECT id_usuario, nombre_usuario, pass, id_rolUsuario FROM usuario WHERE nombre_usuario = ? && pass = ? ";
        User user = null;

        try (Connection connection = DatabaseConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, name_user);
            statement.setString(2, contraseña_user);

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
    public List<String> getPermisos(int id_rol) {
        List<String> permisos = new ArrayList<>();

        String sql = "SELECT PU.nombre_permiso" + " " +
                        "FROM permisosUsuarios AS PU" + " " +
                        "INNER JOIN rol_permiso AS RP ON PU.id_permisosUsuarios = RP.id_permisosUsuarios" + " " +
                        "WHERE RP.id_rolUsuario = ?;";

        try (Connection connection = DatabaseConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, id_rol);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    permisos.add(resultSet.getString("nombre_permiso"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return permisos;
    }

}
