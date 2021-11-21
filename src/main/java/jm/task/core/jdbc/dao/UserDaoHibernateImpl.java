package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        Session session = null;
        final String CREATE_TABLE = "CREATE TABLE users " +
                "(id BIGINT not NULL PRIMARY KEY AUTO_INCREMENT, " +
                " name VARCHAR(255) not NULL, " +
                " lastName VARCHAR(255) not NULL, " +
                " age TINYINT UNSIGNED not NULL)" ;
        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Query tableQuary = session.createSQLQuery(CREATE_TABLE);
            tableQuary.executeUpdate();
            transaction.commit();
        }  catch (Exception ex) {
            assert transaction != null;
            transaction.rollback();
                ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        Session session = null;
        final String DROP_TABLE = "DROP TABLE IF EXISTS USERS" ;
        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Query tableQuary = session.createSQLQuery(DROP_TABLE);
            tableQuary.executeUpdate();
            transaction.commit();
        }  catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        Session session = null;
        User user = new User(name, lastName, age);
        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        Session session = null;
        final String REMOVE_USER = "DELETE FROM USERS WHERE ID = " + id;
        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Query tableQuary = session.createSQLQuery(REMOVE_USER);
            tableQuary.executeUpdate();
            transaction.commit();
        }  catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        Transaction transaction = null;
        Session session = null;
        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            list = session.createQuery("FROM User").getResultList();
            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        Session session = null;
        final String CLEAN_TABLE = "TRUNCATE TABLE users";
        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Query tableQuary = session.createSQLQuery(CLEAN_TABLE);
            tableQuary.executeUpdate();
            transaction.commit();
        }  catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }
}
