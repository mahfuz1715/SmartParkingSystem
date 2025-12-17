package main.ui;

import main.controller.LoginController;
import main.model.User;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(String imagePath) {
        try {
            ImageIcon icon = new ImageIcon(imagePath);
            backgroundImage = icon.getImage();
        } catch (Exception e) {
            System.err.println("Error loading background image: " + imagePath);
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
        
        setupUI(backgroundPanel);
        setVisible(true); 
    }

    private void setupUI(BackgroundPanel backgroundPanel) {
        JPanel headerPanel = new JPanel();
        headerPanel.setOpaque(false); 
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
        
        gbc.gridx = 0; gbc.gridy = 0;
        loginPanel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1; 
        usernameField = new JTextField(20);
        applyFieldStyle(usernameField); 
        loginPanel.add(usernameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        loginPanel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        passwordField = new JPasswordField(20);
        applyFieldStyle(passwordField); 
        loginPanel.add(passwordField, gbc);

        setupButtons(loginPanel, gbc);
    }

    private void applyFieldStyle(JTextField field) {
        field.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180)), 
            new EmptyBorder(8, 10, 8, 10) 
        ));
    }

    private void setupButtons(JPanel loginPanel, GridBagConstraints gbc) {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setOpaque(false); 

        loginButton = createStyledButton("Login", PRIMARY_COLOR);
        registerButton = createStyledButton("Register", ACCENT_COLOR);

        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2; 
        gbc.insets = new Insets(40, 5, 10, 5); 
        loginPanel.add(buttonPanel, gbc);
        
        loginButton.addActionListener(e -> handleLogin());
        registerButton.addActionListener(e -> handleRegister());
    }

    private JButton createStyledButton(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setPreferredSize(new Dimension(120, 40));
        return btn;
    }

    private void handleLogin() {
        String user = usernameField.getText();
        String pass = new String(passwordField.getPassword());
        
        if (user.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Fields cannot be empty.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        User u = controller.login(user, pass);
        if (u != null) {
            JOptionPane.showMessageDialog(this, "Login successful!");
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
            JOptionPane.showMessageDialog(this, "Fields cannot be empty.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (controller.register(user, pass, "user")) {
            JOptionPane.showMessageDialog(this, "Registered successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Registration failed!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
