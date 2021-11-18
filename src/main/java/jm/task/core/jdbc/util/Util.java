package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/peachy_schema";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "2215";

    public static Connection getConnection() {
    Connection connection = null;
    try {
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    } catch (SQLException e) {
        System.out.println("Не удалось подключиться: " + e.getMessage());
    }
    return connection;
    }
}
