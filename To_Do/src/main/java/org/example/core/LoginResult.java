package org.example.core;

import javax.swing.*;

public class LoginResult {
    private JButton button;
    private boolean successful;

    public LoginResult(JButton button, boolean successful) {
        this.button = button;
        this.successful = successful;
    }

    public JButton getButton() {
        return button;
    }

    public boolean isSuccessful() {
        return successful;
    }
}
