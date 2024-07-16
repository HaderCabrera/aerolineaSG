package com.aeroline;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    // COMANDO PARA QUITAR EL COMENTARIO : ctrl + k + u


    // RAILWAY 
    
    // private static final String URL = "jdbc:mysql://monorail.proxy.rlwy.net:25239/railway";
    // private static final String USER = "root";
    // private static final String PASSWORD = "aCnubcljtlOQUMjOYFHnHCeAKeWJMehP";

    // public static Connection getConnection() throws SQLException {
    //     return DriverManager.getConnection(URL, USER, PASSWORD);
    // }

    // MYSQL 

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/railway";
    private static final String USER = "root";
    private static final String PASSWORD = "juandavidgomez15@";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }


}