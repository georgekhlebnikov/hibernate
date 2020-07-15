/**
 * JDBC Driver (pom.xml):
 * mysql-connector-java-5.1.49-bin.jar
 *
 * MySQL -> Class:
 * com.mysql.jdbc.Driver
 *
 * Database -> Advanced:
 * serverTimezone = UTC
 * verifyServerCertificate = FALSE
 * requireSSL = FALSE
 * sslMode = DISABLED
 * useSSL = FALSE
 *
 * Методы для открытия и закрытия соединения:
 * Util.runDB();
 * Util.closeDB();
 */
package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        Util.runDB();

        UserService userServiceImpl = new UserServiceImpl();
        userServiceImpl.createUsersTable();
        userServiceImpl.saveUser("Zara", "Ali", (byte) 18);
        userServiceImpl.saveUser("Ivan", "Ivanov", (byte) 20);
        userServiceImpl.saveUser("Raaj", "Kumar", (byte) 22);
        userServiceImpl.saveUser("Sergey", "Smirnov", (byte) 25);
        //userServiceImpl.removeUserById(3);
        List<User> users = userServiceImpl.getAllUsers();
        for (User user : users) {
            System.out.println(user.toString());
        }

        userServiceImpl.cleanUsersTable();
        userServiceImpl.dropUsersTable();

        Util.closeDB();
    }
}
