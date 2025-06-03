package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/schema1?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "melsik2002";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при подключении к базе данных", e);
        }
    }

    private static final SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration()
                    .setProperty("hibernate.connection.url", JDBC_URL)
                    .setProperty("hibernate.connection.username", USER)
                    .setProperty("hibernate.connection.password", PASSWORD)
                    .setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver")
                    .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect")
                    .setProperty("hibernate.hbm2ddl.auto", "update")
                    .setProperty("hibernate.show_sql", "true")
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("Initial SessionFactory creation failed: " + ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}