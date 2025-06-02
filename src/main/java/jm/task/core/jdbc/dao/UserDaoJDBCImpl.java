package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl extends Util implements UserDao {
    public UserDaoJDBCImpl() {
    }


    //    public void createUsersTable() {
//        String sql = "CREATE TABLE IF NOT EXISTS USERS ("
//                + "id INTEGER PRIMARY KEY AUTOINCREMENT,";
//        sql += "username VARCHAR(255) NOT NULL,";
//        sql += "password VARCHAR(255) NOT NULL,";
//        sql += "firstname VARCHAR(255) NOT NULL,";
//        sql += "lastname VARCHAR(255) NOT NULL,";
//        sql += "email VARCHAR(255) NOT NULL,";
//        sql += "PRIMARY KEY (id))";
//    }
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                "ID INT AUTO_INCREMENT PRIMARY KEY, " +
                "NAME VARCHAR(255), " +
                "LASTNAME VARCHAR(255), " +
                "AGE INT" +
                ")";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
            System.out.println("Таблица users создана или уже существует");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка при создании таблицы", e);
        }
    }


    public void dropUsersTable() {
        Connection connection = null;
        Statement statement = null;
        String sql = "DROP TABLE IF EXISTS users";
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            connection.commit();
            System.out.println("Таблица 'users' успешно удалена.");
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            throw new RuntimeException("Ошибка при удалении таблицы 'users'", e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException closeEx) {
                closeEx.printStackTrace();
        }
    }
 }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Не удалось сохранить пользователя", e);
        }
    }




    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE ID = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, id);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Пользователь с ID " + id + " удалён.");
            } else {
                System.out.println("Пользователь с ID " + id + " не найден.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка при удалении пользователя с ID " + id, e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                long id = rs.getLong("ID");
                String name = rs.getString("NAME");
                String lastName = rs.getString("LASTNAME");
                byte age = rs.getByte("AGE");

                users.add(new User(id, name, lastName, age));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return users;
    }


    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE users";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.executeUpdate();
            System.out.println("Таблица 'users' очищена (TRUNCATE).");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка при очистке таблицы users", e);
        }
    }
}