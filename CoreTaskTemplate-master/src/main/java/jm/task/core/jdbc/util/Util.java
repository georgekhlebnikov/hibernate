package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/jmdao";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "1234";

    public static Connection connection = null;
    public static Statement statement = null;

    public static void runDB() {
        registerDriver();
        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeDB() {
        try {
            boolean closed = statement.isClosed();
            System.out.println(closed);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                if (statement != null) {
                    connection.close();
                    System.out.println("Connection closed success");
                }
                statement.close();
                //boolean closed = stmt.isClosed();
                //System.out.println(closed);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void registerDriver() {
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Driver loading success");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
