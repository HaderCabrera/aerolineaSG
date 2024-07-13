package user.application;


import user.domain.entity.User;
import user.domain.service.UserService;

public class UserUseCase {
    private final UserService userService;

    /*Contructors */
    public UserUseCase(UserService userService) {
        this.userService = userService;
    }
    
    public User consultarUser(String user, String contraseña){
        return userService.consultarUser(user, contraseña);
    }
    // IR A MODIFICAR CONTROLADOR
    public void accederUser(User user){
        userService.accederUser(user);
    }
}
