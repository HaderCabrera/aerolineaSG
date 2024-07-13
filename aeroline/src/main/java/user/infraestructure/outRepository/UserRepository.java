package user.infraestructure.outRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aeroline.DatabaseConfig;

import permisosusuarios.domain.entity.PermisosUsuarios;
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
        System.out.println(user.getId_rolUsuario());
    }

    @Override
    public List<String> obtenerPermisosUser(int idRole) {

        List<String> permisos = new ArrayList<>();

        String sql = "SELECT PU.nombre_permiso\n" + //
                     "FROM permisosUsuarios AS PU\n" + //
                     "INNER JOIN rol_permiso AS RP ON PU.id_permisosUsuarios = RP.id_permisosUsuarios\n" + //
                     "WHERE RP.id_rolUsuario = ?;";
        try (Connection connection = DatabaseConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, idRole);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    permisos.add(resultSet.getString("nombre_permiso"));
                } 
                System.out.println("SALI DEL VIAJE");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return permisos;        
    }

}
