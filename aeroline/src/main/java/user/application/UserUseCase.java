package user.application;

import java.util.Scanner;

import user.domain.entity.User;
import user.domain.service.UserService;

public class UserUseCase {
    private final UserService userService;

    /*Contructors */
    public UserUseCase(UserService userService) {
        this.userService = userService;
    }
    
    public void execute(User user){
        boolean salir = false;
        while (!salir) {
            System.out.println("1. Ingresar");
            System.out.println("2. Acceder Como Cliente");
            System.out.println("3. Salir");
            System.out.print("Elige una opción:");
            Scanner scanner = new Scanner(System.in);
            try {

                int opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        boolean salir2 = false;
                        while (!salir2) {
                            System.out.println("-> Usuario: ");
                            String usuario = scanner.nextLine();
                            System.out.println("-> Contraseña: ");
                            String contraseña = scanner.nextLine();

                            // PASO SIGUIENTE VERIFICAR USUARIO Y CONTRASEÑA CON SERVICIO DE BUSCAR USUARIO
                            try {
                
                                int opcion2 = Integer.parseInt(scanner.nextLine());
                
                                switch (opcion2) {
                                    case 1:
                                        
                                        userService.accederUser(user);
                                        System.out.println("Access successfully!");
                                        break;
                
                                    case 2:
                                        System.out.println("Access successfully!");
                                        break;
                
                                    case 3:
                                        System.out.println("Saliendo...");
                                        salir2 = true;
                                        break;
                                        
                                    default:
                                        System.out.println("Opción no válida, por favor elige una opción entre 1 y 4");
                                }
                            } catch (Exception e) {
                                System.out.println("Ha ocurrido un error inesperado: " + e);
                            }
                        }












                        userService.accederUser(user);
                        System.out.println("Access successfully!");
                        break;

                    case 2:
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
