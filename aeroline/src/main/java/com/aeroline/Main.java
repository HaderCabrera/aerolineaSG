package com.aeroline;

import user.application.UserUseCase;

import user.domain.service.UserService;
import user.infraestructure.inController.UserController;
import user.infraestructure.outRepository.UserRepository;
// import vuelo.application.VueloUseCase;
// import vuelo.domain.service.VueloService;
// import vuelo.infraestructure.inController.Vuelocontroller;
// import vuelo.infraestructure.outRepository.VueloRepository;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserRepository();
        UserUseCase userUseCase = new UserUseCase(userService);
        UserController userController = new UserController(userUseCase);
        userController.start();
        // VueloService vueloService = new VueloRepository();
        // VueloUseCase vueloUseCase = new VueloUseCase(vueloService);
        // Vuelocontroller vueloController = new Vuelocontroller(vueloUseCase);
        // vueloController.start();
      
    }
}


