package user.domain.service;

import user.domain.entity.User;

public interface UserService {
    User consultarUser(String user, String contraseña);
    void accederUser(User user);
} 