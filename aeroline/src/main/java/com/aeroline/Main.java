package com.aeroline;

import user.application.UserUseCase;

import user.domain.service.UserService;
import user.infraestructure.inController.UserController;
import user.infraestructure.outRepository.UserRepository;


public class Main {
    public static void main(String[] args) {
        UserService userService = new UserRepository();
        UserUseCase userUseCase = new UserUseCase(userService);
        UserController userController = new UserController(userUseCase);
        userController.start();
    
    }
}
