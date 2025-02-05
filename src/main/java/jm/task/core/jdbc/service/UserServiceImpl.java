package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    public void createUsersTable() {
String sql = "CREATE TABLE IF NOT EXISTS users " +
        "("+ "id SERIAL PRIMARY KEY," + "username VARCHAR(255) NOT NULL," +
        "password VARCHAR(255) NOT NULL," + "email VARCHAR(255) NOT NULL," + "PRIMARY KEY (id))";
    }
    public void dropUsersTable() {
String sql = "DROP TABLE IF EXISTS users";
    }
    public void saveUser(String name, String lastName, byte age) {
String sql = "INSERT INTO users VALUES(?,?,?)";
    }
    public void removeUserById(long id) {
String sql = "DELETE FROM users WHERE id = ?";
    }
    public List<User> getAllUsers() {
        return null;
    }

    public void cleanUsersTable() {
String sql = "DROP TABLE IF EXISTS users";
    }
}
