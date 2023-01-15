package org.example.GUI;

import org.example.core.TaskDAO;
import org.example.core.UserDAO;
import org.example.core.UserDAOImpl;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class PreLogin extends JFrame {

    private final TaskDAO taskDAO;
    private boolean loginSuccessful = false;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;

    UserDAO userDAO = new UserDAOImpl();
    JButton loginButton = new JButton("Login");
    private TaskDAO TaskDAOImpl;

    public PreLogin(TaskDAO taskDAO) {
        super("Login Screen");
        this.taskDAO = taskDAO;
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridBagLayout());
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginScreen login = new LoginScreen(taskDAO);
                login.setLocationRelativeTo(null);
                login.setVisible(true);
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


        GridBagConstraints constraints = new GridBagConstraints();

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