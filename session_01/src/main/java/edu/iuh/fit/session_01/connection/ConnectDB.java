package edu.iuh.fit.session_01.connection;

import org.mariadb.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    private static Connection instance;

    public static void instanceConnection(String user, String password) {
        if (instance == null) {
            try {
                Class.forName("org.mariadb.jdbc.Driver");
                String url = "jdbc:mariadb://localhost:3306/login_lab1?user=" + user + "&password=" + password;
                instance = (Connection) DriverManager.getConnection(url);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static Connection getInstance() {
        return instance;
    }
}
