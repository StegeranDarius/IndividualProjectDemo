package org.example;

import com.formdev.flatlaf.FlatDarculaLaf;
import org.example.GUI.LoginScreen;
import org.example.GUI.MainWindow;
import org.example.GUI.PreLogin;
import org.example.core.LoginResult;
import org.example.core.TaskDAO;
import org.example.core.TaskDAOImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {


    public static void main(String[] args){
        FlatDarculaLaf.setup();
        TaskDAO taskDAO = new TaskDAOImpl();
        PreLogin loginScreen = new PreLogin(taskDAO);
        loginScreen.setLocationRelativeTo(null);
        loginScreen.setVisible(true);
}}
