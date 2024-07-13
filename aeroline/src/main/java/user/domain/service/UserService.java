package user.domain.service;


import java.util.List;

import user.domain.entity.User;

public interface UserService {
    User consultarUser(String user, String contraseña);
    void accederUser(User user);
    List<String> obtenerPermisosUser(int idRole);
} 