package user.infraestructure.inController;

import java.util.Scanner;

import user.application.UserUseCase;
import user.domain.entity.User;

public class UserController {
    private UserUseCase userUseCase;

    /*Constructor*/
    public UserController(UserUseCase userUseCase) {
        this.userUseCase = userUseCase;
    }

    public void start(){
        boolean salir = false;
        while (!salir) {
            System.out.println("\n");
            System.out.println("========================");
            System.out.println("   Hihgt all the time");
            System.out.println("________________________");
            System.out.println("1. Ingresar");
            System.out.println("2. Acceder Como Cliente");
            System.out.println("3. Salir");
            System.out.println("========================");
            System.out.print("Elige una opción: ");
            Scanner scanner = new Scanner(System.in);
            try {

                int opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
       
                        try {
                            System.out.print("-> Usuario: ");
                            String usuario = scanner.nextLine();
                            System.out.print("-> Contraseña: ");
                            String contraseña = scanner.nextLine();
                            User usuarioValidado = userUseCase.consultarUser(usuario, contraseña);
                            if (usuarioValidado != null) {
                                //Llamado al servicio de acceso
                                userUseCase.accederUser(usuarioValidado);
                            } else {
                                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                                System.out.println("Datos incorrectos, usuario no encontrado.");
                                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                            }
                        } catch (Exception e) {
                            System.out.println("Ha ocurrido un error inesperado: " + e);
                        }

                        System.out.println("Access successfully!");
                        break;

                    case 2:
                        // Instanciar a vista de CLIENTE
                        System.out.println("Access successfully!");
                        break;

                    case 3:
                        System.out.println("Saliendo...");
                        salir = true;
                        break;
                        
                    default:
                        System.out.println("Opción no válida, por favor elige una opción entre 1 y 4");
                }
            } catch (Exception e) {
                System.out.println("Ha ocurrido un error inesperado: " + e);
            }
        }
    }
}
