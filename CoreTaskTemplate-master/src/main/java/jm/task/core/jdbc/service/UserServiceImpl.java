package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.*;

public class UserServiceImpl implements UserService {

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users " +
                "(id INTEGER auto_increment, " +
                " name VARCHAR(64), " +
                " lastname VARCHAR(64), " +
                " age INTEGER(3), " +
                " PRIMARY KEY (id))";
        try {
            statement.executeUpdate(sql);
            System.out.println("Created table in database..");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE users";
        try {
            statement.executeUpdate(sql);
            System.out.println("Таблица \'users\' \u2014 удалена");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users(name, lastname, age) "
                + "VALUES(?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User \'" + name + " " + lastName + "\' \u2014 добавлен");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM users " +
                "WHERE id = " + id;
        try {
            statement.executeUpdate(sql);
            System.out.println("User с ID=" + id + " \u2014 удален");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        String sql = "SELECT id, name, lastname, age FROM users";
        try {
            List<User> users = new ArrayList<>();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                User user = new User();
                user.setId((long) rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("lastname"));
                user.setAge((byte) rs.getInt("age"));
                users.add(user);
            }
            rs.close();
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE users";
        try {
            statement.executeUpdate(sql);
            System.out.println("Таблица \'users\' \u2014 очищена");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
