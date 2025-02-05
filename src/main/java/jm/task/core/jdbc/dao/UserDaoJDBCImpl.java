package jm.task.core.jdbc.dao;

import com.sun.jdi.connect.spi.Connection;
import jm.task.core.jdbc.model.User;

import java.beans.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
String url = "jdbc:postgresql://localhost:5432/postgres";
String user = "postgres";
String password = "postgres";
Connection connection = null;
Statement statement = null;
ResultSet resultSet = null;
    }
    public void createUsersTable() {
String sql = "CREATE TABLE IF NOT EXISTS USERS ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT,";
sql += "username VARCHAR(255) NOT NULL,";
sql += "password VARCHAR(255) NOT NULL,";
sql += "firstname VARCHAR(255) NOT NULL,";
sql += "lastname VARCHAR(255) NOT NULL,";
sql += "email VARCHAR(255) NOT NULL,";
sql += "PRIMARY KEY (id))";
    }

    public void dropUsersTable() {
String sql = "DROP TABLE IF EXISTS USERS";
    }

    public void saveUser(String name, String lastName, byte age) {
String sql = "INSERT INTO USERS (name, lastName, age) VALUES (?, ?, ?)";
    }

    public void removeUserById(long id) {
String sql = "DELETE FROM USERS WHERE id = ?";
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM USERS";
        return null;
    }

    public void cleanUsersTable() {
String sql = "DROP TABLE IF EXISTS USERS";
    }
}

