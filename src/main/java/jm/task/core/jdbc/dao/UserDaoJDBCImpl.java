package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        Connection connection = Util.getConnection();
        Statement stmt;
        try {
            connection.setAutoCommit(false);
            stmt = connection.createStatement();
            stmt.executeUpdate("CREATE TABLE USERS " +
                    "(id BIGINT not NULL PRIMARY KEY AUTO_INCREMENT, " +
                    " name VARCHAR(255) not NULL, " +
                    " lastName VARCHAR(255) not NULL, " +
                    " age TINYINT UNSIGNED not NULL)");
            connection.commit();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void dropUsersTable() {
        Connection connection = Util.getConnection();
        Statement stmt;
        try {
            connection.setAutoCommit(false);
            stmt = connection.createStatement();
            stmt.executeUpdate("DROP TABLE IF EXISTS USERS");
            connection.commit();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        Connection connection = Util.getConnection();
        PreparedStatement prpstm;
        try {
            connection.setAutoCommit(false);
            prpstm = connection.prepareStatement("INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)");
            prpstm.setString(1, name);
            prpstm.setString(2, lastName);
            prpstm.setByte(3, age);
            prpstm.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    public void removeUserById(long id) {
        Connection connection = Util.getConnection();
        Statement stmt;
        try {
            connection.setAutoCommit(false);
            stmt = connection.createStatement();
            stmt.executeUpdate("DELETE FROM USERS WHERE ID = " + id);
            connection.commit();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        Connection connection = Util.getConnection();
        Statement stmt;
        try {
            connection.setAutoCommit(false);
            stmt = connection.createStatement();
            ResultSet rsst = stmt.executeQuery("SELECT id, name, lastName, age FROM USERS");
            while (rsst.next()) {
                list.add(new User(rsst.getString(2),
                        rsst.getString(3), rsst.getByte(4)));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    //ResultSet

    public void cleanUsersTable() {
        Connection connection = Util.getConnection();
        Statement stmt;
        try {
            connection.setAutoCommit(false);
            stmt = connection.createStatement();
            stmt.executeUpdate("TRUNCATE TABLE USERS");
            connection.commit();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
