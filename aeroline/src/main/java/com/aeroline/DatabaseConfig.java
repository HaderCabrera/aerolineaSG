package com.aeroline;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    // COMANDO PARA QUITAR EL COMENTARIO : ctrl + k + u


    // RAILWAY 
    /*  



    private static final String URL = "jdbc:mysql://roundhouse.proxy.rlwy.net:41600/railway";
    private static final String USER = "root";
    private static final String PASSWORD = "FtjNcJYULtcbURPVLBrmAReMgZcdLjAK";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    */
    

    // MYSQL 
/*
  private static final String URL = "jdbc:mysql://127.0.0.1:3306/railway";
    private static final String USER = "campus2023";
    private static final String PASSWORD = "campus2023";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
 
 */
private static final String URL = "jdbc:mysql://roundhouse.proxy.rlwy.net:41600/railway";
private static final String USER = "root";
private static final String PASSWORD = "FtjNcJYULtcbURPVLBrmAReMgZcdLjAK";

public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(URL, USER, PASSWORD);
}


}