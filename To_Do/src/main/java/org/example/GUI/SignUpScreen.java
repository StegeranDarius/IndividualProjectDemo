package org.example.GUI;

import org.example.core.TaskDAO;
import org.example.core.UserDAO;
import org.example.core.UserDAOImpl;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class SignUpScreen extends JFrame {

    private final TaskDAO taskDAO;
    private boolean loginSuccessful = false;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;

    UserDAO userDAO = new UserDAOImpl();
    JButton loginButton = new JButton("Login");
    private TaskDAO TaskDAOImpl;

    public SignUpScreen(TaskDAO taskDAO) {
        super("Login Screen");
        this.taskDAO = taskDAO;
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridBagLayout());
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        confirmPasswordField = new JPasswordField(20);

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());
                if(password.isEmpty() || username.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Username or password empty", "Error", JOptionPane.ERROR_MESSAGE);
                }else if (password.equals(confirmPassword)) {
                    if (!userDAO.checkIfUserExist(username)) {
                        if (userDAO.insertUser(username, password)) {
                            JOptionPane.showMessageDialog(null, "User created successfully");
                            dispose();
                            LoginScreen login = new LoginScreen(taskDAO);
                            login.setLocationRelativeTo(null);
                            login.setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(null, "Error creating user", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Username already exists", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        JButton loginButton = new JButton("Log in");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginScreen window = new LoginScreen(taskDAO);
                window.setLocationRelativeTo(null);
                window.setVisible(true);
            }
        });

        // Add the labels and fields to the login panel using GridBagConstraints
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        loginPanel.add(usernameLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        loginPanel.add(usernameField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        loginPanel.add(passwordLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        loginPanel.add(passwordField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        loginPanel.add(confirmPasswordLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        loginPanel.add(confirmPasswordField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        loginPanel.add(loginButton, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        loginPanel.add(signUpButton, constraints);


        add(loginPanel);

        setSize(960, 540);
        setVisible(true);
    }

    public JButton getLoginResult() {
        return loginButton;
    }
}