package main.ui;

import main.controller.LoginController;
import main.model.User;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File; 

class BackgroundPanel extends JPanel {
    private Image backgroundImage;
    private float imageOpacity = 0.5f; 

    public BackgroundPanel(String imagePath) {
        try {
            
            ImageIcon icon = new ImageIcon(imagePath);
            backgroundImage = icon.getImage();
        } catch (Exception e) {
            System.err.println("Error loading background image: " + imagePath);
            e.printStackTrace();
            
            backgroundImage = null;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            
            g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            
            g2d.setColor(new Color(0, 0, 0, 100)); 
            g2d.fillRect(0, 0, getWidth(), getHeight());
            
            g2d.dispose();
        } else {
            
            g.setColor(new Color(50, 50, 70));
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, registerButton;
    private LoginController controller = new LoginController(); 

    private static final Color PRIMARY_COLOR = new Color(50, 150, 250); 
    private static final Color ACCENT_COLOR = new Color(255, 100, 100);  
    
    private static final Color CARD_BACKGROUND = new Color(255, 255, 255, 230); 

    private static final String BACKGROUND_IMAGE_PATH = "parking_background.jpg"; 

    public LoginFrame() {
        setTitle("Smart Parking System - Login");
        
        setSize(800, 600); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        BackgroundPanel backgroundPanel = new BackgroundPanel(BACKGROUND_IMAGE_PATH);
        backgroundPanel.setLayout(new BorderLayout());
        setContentPane(backgroundPanel);
        
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(50, 150, 250, 200)); 
        headerPanel.setBorder(new EmptyBorder(25, 0, 25, 0));
        JLabel titleLabel = new JLabel("SMART PARKING SYSTEM");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 30)); 
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);
        backgroundPanel.add(headerPanel, BorderLayout.NORTH); 

        JPanel loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setBackground(CARD_BACKGROUND); 
        
        loginPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200, 150), 1), 
            new EmptyBorder(40, 40, 40, 40) 
        ));
        
        JPanel centerContainer = new JPanel(new GridBagLayout());
        centerContainer.setOpaque(false); 
        centerContainer.add(loginPanel); 

        backgroundPanel.add(centerContainer, BorderLayout.CENTER); 
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 5, 10, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0;
        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        loginPanel.add(userLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 1.0;
        usernameField = new JTextField(20);
        usernameField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        usernameField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180)), 
            new EmptyBorder(8, 10, 8, 10) 
        ));
        loginPanel.add(usernameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        loginPanel.add(passLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 1; gbc.weightx = 1.0;
        passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180)), 
            new EmptyBorder(8, 10, 8, 10) 
        ));
        loginPanel.add(passwordField, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setOpaque(false); 

        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        loginButton.setBackground(PRIMARY_COLOR);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setPreferredSize(new Dimension(120, 40)); 
        
        registerButton = new JButton("Register");
        registerButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        registerButton.setBackground(ACCENT_COLOR);
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        registerButton.setPreferredSize(new Dimension(120, 40)); 

        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);

        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2; 
        gbc.insets = new Insets(40, 5, 10, 5); 
        loginPanel.add(buttonPanel, gbc);
        
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
        
        setVisible(true); 
    }

    private void handleLogin() {
        String user = usernameField.getText();
        String pass = new String(passwordField.getPassword());
        
        if (user.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username and Password cannot be empty.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        User u = controller.login(user, pass);
        if (u != null) {
            JOptionPane.showMessageDialog(this, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();

            new ParkingDashboard(u).setVisible(true); 
        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleRegister() {
        String user = usernameField.getText();
        String pass = new String(passwordField.getPassword());

        if (user.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username and Password cannot be empty.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (controller.register(user, pass, "user")) {
            JOptionPane.showMessageDialog(this, "Registered successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Registration failed! User may already exist.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
