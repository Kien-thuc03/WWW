package edu.iuh.fit.session_01.beans;

import edu.iuh.fit.session_01.connection.ConnectDB;
import jakarta.servlet.http.HttpServlet;
import org.mariadb.jdbc.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
    private final Connection connection;

    public Login() {
        ConnectDB.instanceConnection("root", "root");
        connection = ConnectDB.getInstance();
    }
    public boolean login(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String resultUsername = resultSet.getString(2);
                String resultPass = resultSet.getString(3);

                if (resultUsername == null || resultPass == null) {
                    return false;
                }
                if (resultUsername.isEmpty() || resultPass.isEmpty()) {
                    return false;
                }
                if (resultUsername.equals(username) && resultPass.equals(password)) {
                    return true;
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
