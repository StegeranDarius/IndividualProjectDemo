package org.example.GUI;

import org.example.core.TaskDAO;
import org.example.core.UserDAO;
import org.example.core.UserDAOImpl;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LoginScreen extends JFrame {

    private final TaskDAO taskDAO;
    private boolean loginSuccessful = false;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;

    UserDAO userDAO = new UserDAOImpl();
    JButton loginButton = new JButton("Login");
    private TaskDAO TaskDAOImpl;

    public LoginScreen(TaskDAO taskDAO) {
        super("Login Screen");
        this.taskDAO = taskDAO;
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridBagLayout());
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                UserDAOImpl userDAO = new UserDAOImpl();
                boolean loginSuccessful = userDAO.checkIfUserExist(username,password);
                if(password.isEmpty() || username.isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Username or password empty", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (userDAO.checkIfUserExist(username, password)) {
                    JOptionPane.showMessageDialog(null, "Login successful");
                    dispose();
                    MainWindow window = new MainWindow(taskDAO, username);
                    window.setLocationRelativeTo(null);
                    window.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        JButton signUpButton = new JButton("Sign Up");
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SignUpScreen window = new SignUpScreen(taskDAO);
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