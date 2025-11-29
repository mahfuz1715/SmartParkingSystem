package main.ui;

import main.controller.LoginController;
import main.model.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, registerButton;
    private LoginController controller = new LoginController();

    public LoginFrame() {
        setTitle("Smart Parking System - Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1));

        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        JPanel buttonPanel = new JPanel();
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);
        add(buttonPanel);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleRegister();
            }
        });
    }

    private void handleLogin() {
        String user = usernameField.getText();
        String pass = new String(passwordField.getPassword());
        User u = controller.login(user, pass);
        if (u != null) {
            JOptionPane.showMessageDialog(this, "Login successful!");
            dispose();
            new ParkingDashboard(u).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials");
        }
    }

    private void handleRegister() {
        String user = usernameField.getText();
        String pass = new String(passwordField.getPassword());
        if (controller.register(user, pass, "user")) {
            JOptionPane.showMessageDialog(this, "Registered successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Registration failed!");
        }
    }
}
