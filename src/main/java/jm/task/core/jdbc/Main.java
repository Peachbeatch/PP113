package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args) {
        UserDaoJDBCImpl user = new UserDaoJDBCImpl();
        user.createUsersTable();
        user.saveUser("John", "Snow", (byte) 17);
        user.saveUser("Daenerys", "Targaryen", (byte) 16);
        user.saveUser("Cersei", "Lannister", (byte) 34);
        user.saveUser("Jaime", "Lannister", (byte) 34);
        user.getAllUsers();
        user.cleanUsersTable();
        user.dropUsersTable();
    }
}
