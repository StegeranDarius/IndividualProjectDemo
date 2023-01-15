package org.example.core;


import java.util.List;

public interface TaskDAO {
    void addTask(String username, String task);
    List<String> getTasks(String username);
    void removeTask(String username, String task);
    void editTask(String username, String oldTask, String newTask);
}

