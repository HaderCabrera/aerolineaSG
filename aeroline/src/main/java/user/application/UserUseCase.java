package user.application;

import java.util.List;

import user.domain.entity.User;
import user.domain.service.UserService;

public class UserUseCase {
    private final UserService userService;

    /*Contructors */
    public UserUseCase(UserService userService) {
        this.userService = userService;
    }
    
    // IR A MODIFICAR CONTROLADOR
    public User findUserCase(String user_name, String contraseña_user){
        return userService.findUser(user_name, contraseña_user);
    }
    public List<String> getPermisosCase(int id_rolUsuario){
        return userService.getPermisos(id_rolUsuario);
    }
}
