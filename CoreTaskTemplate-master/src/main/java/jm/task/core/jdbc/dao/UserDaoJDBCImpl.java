package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    public final Connection connection = getConnection();

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() throws SQLException {
        //Connection connection = getConnection();
        Statement statement;
        statement = connection.createStatement();

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
        } finally {
            if (statement != null) {
                statement.close();
            }
            //if (connection != null) {
            //    connection.close();
            //}
        }
    }

    public void dropUsersTable() throws SQLException {
        //Connection connection = getConnection();
        Statement statement;
        statement = connection.createStatement();

        String sql = "DROP TABLE IF EXISTS users";
        try {
            statement.executeUpdate(sql);
            System.out.println("Таблица 'users' \u2014 удалена");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        //Connection connection = getConnection();
        String sql = "INSERT INTO users(name, lastname, age) "
                + "VALUES(?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User '" + name + " " + lastName + "' \u2014 добавлен");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //if (connection != null) {
            //    connection.close();
            //}
        }
    }

    public void removeUserById(long id) throws SQLException {
        //Connection connection = getConnection();
        Statement statement;
        statement = connection.createStatement();

        String sql = "DELETE FROM users " +
                "WHERE id = " + id;
        try {
            statement.executeUpdate(sql);
            System.out.println("User с ID=" + id + " \u2014 удален");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            //if (connection != null) {
            //    connection.close();
            //}
        }
    }

    public List<User> getAllUsers() throws SQLException {
        //Connection connection = getConnection();
        Statement statement;
        statement = connection.createStatement();

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
        } finally {
            if (statement != null) {
                statement.close();
            }
            //if (connection != null) {
            //    connection.close();
            //}
        }
        return null;
    }

    public void cleanUsersTable() throws SQLException {
        //Connection connection = getConnection();
        Statement statement;
        statement = connection.createStatement();

        String sql = "TRUNCATE TABLE users";
        try {
            statement.executeUpdate(sql);
            System.out.println("Таблица 'users' \u2014 очищена");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            //if (connection != null) {
            //    connection.close();
            //}
        }
    }
}
