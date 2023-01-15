package org.example.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {
    private Connection connection;

    public UserDAOImpl() {
        try {
            // Register the driver
            Class.forName("org.sqlite.JDBC");
            // Create a connection to the database
            connection = DriverManager.getConnection("jdbc:sqlite:C:/Users/Rou/IdeaProjects/IndividualProjectDemo/identifier.sqlite");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkIfUserExist(String username) {
        try {
            // Prepare a statement to select the user with the given username
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM user WHERE username = ?");
            statement.setString(1, username);
            ResultSet result = statement.executeQuery();
            // If the result set is not empty, the user exists
            return result.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean checkIfUserExist(String username, String password) {
        try {
            // Prepare a statement to select the user with the given username and password
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM user WHERE username = ? AND password = ?");
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();
            // If the result set is not empty, the user exists
            return result.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertUser(String username, String password) {
        try {
            // Prepare a statement to insert a new user
            PreparedStatement statement = connection.prepareStatement("INSERT INTO user(username, password) VALUES(?, ?)");
            statement.setString(1, username);
            statement.setString(2, password);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateUser(String username, String newPassword) {
        try {
            // Prepare a statement to update the password of a user
            PreparedStatement statement = connection.prepareStatement("UPDATE user SET password = ? WHERE username = ?");
            statement.setString(1, newPassword);
            statement.setString(2, username);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteUser(String username) {
        try {
            // Prepare a statement to delete a user
            PreparedStatement statement = connection.prepareStatement("DELETE FROM user WHERE username = ?");
            statement.setString(1, username);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
