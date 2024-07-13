package user.infraestructure.outRepository;

import user.domain.entity.User;
import user.domain.service.UserService;

public class UserRepository implements UserService{

    @Override
    public User consultarUser(String user, String contraseña) {
        //Logica de CONSULTAR USUARIO
    }

    @Override
    public void accederUser(User user) {
        // Logica de ACCEDER COMO USUARIO
    }

}
