package com.aeroline;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    
private static final String URL = "jdbc:mysql://roundhouse.proxy.rlwy.net:41600/railway";
private static final String USER = "root";
private static final String PASSWORD = "FtjNcJYULtcbURPVLBrmAReMgZcdLjAK";

public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(URL, USER, PASSWORD);
}

  



}