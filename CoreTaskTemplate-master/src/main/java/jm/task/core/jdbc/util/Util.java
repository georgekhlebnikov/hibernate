//import org.hibernate.SessionFactory;
//import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
//import org.hibernate.cfg.Configuration;

package jm.task.core.jdbc.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;
import java.sql.*;

public class Util {
    // For all:
    private static final String DB_JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/" +
            "jmdao?useUnicode=true&serverTimezone=UTC&useSSL=true&verifyServerCertificate=false";
    private static final String DB_LOGIN = "root";
    private static final String DB_PASSWORD = "1234";
    // Specific:
    private static final String DB_DIALECT = "org.hibernate.dialect.MySQLDialect";
    private static final String DB_SHOW_SQL = "true";
    //private static final String CONTEXT_CLASS = "thread";
    private static final String HBM2DDL_AUTO = "create-drop"; //create

    //JDBC configuration:
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

    //Hibernate configuration:
    private static SessionFactory sessionFactory;

    static {
        Configuration configuration = new Configuration();

        Properties settings = new Properties();
        settings.put(Environment.DRIVER, DB_JDBC_DRIVER);
        settings.put(Environment.URL, DB_URL);
        settings.put(Environment.USER, DB_LOGIN);
        settings.put(Environment.PASS, DB_PASSWORD);
        settings.put(Environment.DIALECT, DB_DIALECT);
        settings.put(Environment.SHOW_SQL, DB_SHOW_SQL);
        //settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, CONTEXT_CLASS);
        settings.put(Environment.HBM2DDL_AUTO, HBM2DDL_AUTO);

        configuration
                .setProperties(settings)
                .addAnnotatedClass(jm.task.core.jdbc.model.User.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();

        sessionFactory = configuration
                .buildSessionFactory(serviceRegistry);

        //Configuration configuration = new Configuration().configure();
        //StandardServiceRegistryBuilder registryBuilder =
        //        new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        //sessionFactory = configuration.buildSessionFactory(registryBuilder.build());
    }
    public static SessionFactory getSessionfactory() {
        return sessionFactory;
    }
}
