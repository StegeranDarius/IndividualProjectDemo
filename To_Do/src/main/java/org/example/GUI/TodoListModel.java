package org.example.GUI;

import org.example.core.TaskDAO;

import javax.swing.AbstractListModel;
import java.util.List;

public class TodoListModel extends AbstractListModel<String> {
    private TaskDAO taskDAO;
    private String username;

    public TodoListModel(TaskDAO taskDAO, String username) {
        this.taskDAO = taskDAO;
        this.username = username;
    }

    public void moveUp(int i) {
        // Code to move a task up in the database goes here
    }

    public void moveDown(int i) {
        // Code to move a task down in the database goes here
    }

    public void removeAt(int i) {
        String task = getElementAt(i);
        taskDAO.removeTask(username, task);
        fireContentsChanged(this, i, i);
    }

    public void add(String task) {
        taskDAO.addTask(username, task);
        fireContentsChanged(this, getSize() - 1, getSize() - 1);
    }

    public void editAt(int i, String newValue) {
        String task = getElementAt(i);
        taskDAO.editTask(username, task, newValue);
        fireContentsChanged(this, i, i);
    }

    @Override
    public int getSize() {
        return taskDAO.getTasks(username).size();
    }

    @Override
    public String getElementAt(int index) {
        return taskDAO.getTasks(username).get(index);
    }
}