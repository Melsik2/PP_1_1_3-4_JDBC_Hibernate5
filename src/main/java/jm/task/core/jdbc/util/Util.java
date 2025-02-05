package jm.task.core.jdbc.util;

import com.sun.jdi.connect.spi.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
private static final String URL = "jdbc:mysql://localhost:3306/schema1";
private static final String USER = "root";
private static final String PASSWORD = "melsik2002";
public static Connection getConnection() {
  java.sql.Connection connection = null;
    try {
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
        System.out.println("Подключение установлено!");
    } catch (SQLException e) {
        System.out.println("Подключение не установлено!");
        throw new RuntimeException(e);
    }
    return (Connection) connection;
}
}
