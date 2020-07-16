package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    private static final String DB_JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/jmdao";
    private static final String DB_LOGIN = "root";
    private static final String DB_PASSWORD = "1234";

    public Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_LOGIN, DB_PASSWORD);
            System.out.println("Driver loading success");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
