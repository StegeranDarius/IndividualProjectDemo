package org.example.core;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDAOImpl implements TaskDAO {

    private Connection connection;

    public TaskDAOImpl() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:C:/Users/Rou/IdeaProjects/IndividualProjectDemo/identifier.sqlite");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addTask(String username, String task) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO task_table (username, task) VALUES (?, ?)");
            statement.setString(1, username);
            statement.setString(2, task);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> getTasks(String username) {
        List<String> tasks = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT task FROM task_table WHERE username = ?");
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                tasks.add(rs.getString("task"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    @Override
    public void removeTask(String username, String task) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM task_table WHERE username = ? AND task = ?");
            statement.setString(1, username);
            statement.setString(2, task);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editTask(String username, String oldTask, String newTask) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE task_table SET task = ? WHERE username = ? AND task = ?");
            statement.setString(1, newTask);
            statement.setString(2, username);
            statement.setString(3, oldTask);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
