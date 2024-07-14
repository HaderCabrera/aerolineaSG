package user.domain.service;


import java.util.List;

import user.domain.entity.User;

public interface UserService {
    User findUser(String username, String contraseña);
    List<String> getPermisos(int id_rol);
} 