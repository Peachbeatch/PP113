package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

public class Util {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration confi = new Configuration();

                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/peachy_schema" );
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "2215");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                confi.setProperties(settings);
                confi.addAnnotatedClass(User.class);
                StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(confi.getProperties()).build();
                sessionFactory = confi.buildSessionFactory(serviceRegistry);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return sessionFactory;
    }

    /*private static final String URL = "jdbc:mysql://localhost:3306/peachy_schema";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "2215";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Не удалось подключиться: " + e.getMessage());
        }
        return connection;
    }*/

}
