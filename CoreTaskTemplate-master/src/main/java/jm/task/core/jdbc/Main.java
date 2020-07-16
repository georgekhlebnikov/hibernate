/**
 * _JDBC Driver (pom.xml):
 * mysql-connector-java-5.1.49-bin.jar
 *
 * _MySQL -> Class:
 * com.mysql.jdbc.Driver
 *
 * _Database -> Advanced:
 * serverTimezone = UTC
 * verifyServerCertificate = FALSE
 * requireSSL = FALSE
 * sslMode = DISABLED
 * useSSL = FALSE
 */
package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<User> users;
        UserService userServiceImpl = new UserServiceImpl();
        try {
            userServiceImpl.createUsersTable();
            userServiceImpl.saveUser("Zara", "Ali", (byte) 18);
            userServiceImpl.saveUser("Ivan", "Ivanov", (byte) 20);
            userServiceImpl.saveUser("Raaj", "Kumar", (byte) 22);
            userServiceImpl.saveUser("Sergey", "Smirnov", (byte) 25);
            userServiceImpl.removeUserById(3);
            users = userServiceImpl.getAllUsers();
            for (User user : users) {
                System.out.println(user.toString());
            }
            userServiceImpl.cleanUsersTable();
            userServiceImpl.dropUsersTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
