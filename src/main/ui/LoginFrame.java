package main.ui;

import main.controller.LoginController;
import main.model.User;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File; // Needed for loading image

// --- Custom JPanel to handle the background image ---
class BackgroundPanel extends JPanel {
    private Image backgroundImage;
    private float imageOpacity = 0.5f; // Dim the background slightly

    public BackgroundPanel(String imagePath) {
        try {
            // Load the image using an ImageIcon for compatibility
            ImageIcon icon = new ImageIcon(imagePath);
            backgroundImage = icon.getImage();
        } catch (Exception e) {
            System.err.println("Error loading background image: " + imagePath);
            e.printStackTrace();
            // Fallback to a plain color if image fails to load
            backgroundImage = null;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            // Draw the image scaled to fit the panel size
            g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            
            // Optional: Add a subtle overlay to dim the image for better text readability
            g2d.setColor(new Color(0, 0, 0, 100)); // Black with 100/255 opacity
            g2d.fillRect(0, 0, getWidth(), getHeight());
            
            g2d.dispose();
        } else {
            // Fallback background color if image is not found
            g.setColor(new Color(50, 50, 70));
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, registerButton;
    private LoginController controller = new LoginController(); // Existing controller instance

    // --- COLOR PALETTE ---
    private static final Color PRIMARY_COLOR = new Color(50, 150, 250); // Bright Blue
    private static final Color ACCENT_COLOR = new Color(255, 100, 100);  // Soft Red for Register
    // Card background is now slightly transparent white
    private static final Color CARD_BACKGROUND = new Color(255, 255, 255, 230); // White with 90% opacity

    // --- IMAGE PATH ---
    private static final String BACKGROUND_IMAGE_PATH = "parking_background.jpg"; 

    public LoginFrame() {
        setTitle("Smart Parking System - Login");
        // Adjusted size for a prominent look with the background image
        setSize(800, 600); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // --- 1. Set the Custom Background Panel as the Content Pane ---
        BackgroundPanel backgroundPanel = new BackgroundPanel(BACKGROUND_IMAGE_PATH);
        backgroundPanel.setLayout(new BorderLayout());
        setContentPane(backgroundPanel);
        
        // --- 2. Header/Title Panel (Top) ---
        // Make header panel semi-transparent or use a striking color
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(50, 150, 250, 200)); // Primary color with transparency
        headerPanel.setBorder(new EmptyBorder(25, 0, 25, 0));
        JLabel titleLabel = new JLabel("SMART PARKING SYSTEM");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 30)); // Larger font
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);
        backgroundPanel.add(headerPanel, BorderLayout.NORTH); // Added to the background panel

        // --- 3. Main Login Panel (Center) ---
        JPanel loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setBackground(CARD_BACKGROUND); // Use semi-transparent white
        
        // Clean, rounded border simulation (using EmptyBorder and a LineBorder on the outside)
        loginPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200, 150), 1), // Light, semi-transparent border
            new EmptyBorder(40, 40, 40, 40) 
        ));
        
        // Use a container to hold the loginPanel and center it perfectly
        JPanel centerContainer = new JPanel(new GridBagLayout());
        centerContainer.setOpaque(false); // Make the container transparent
        centerContainer.add(loginPanel); // Add login box to the center container

        backgroundPanel.add(centerContainer, BorderLayout.CENTER); // Added to the background panel
        
        // --- GridBagLayout Constraints Setup ---
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 5, 10, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // --- Username Field ---
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0;
        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        loginPanel.add(userLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 1.0;
        usernameField = new JTextField(20);
        usernameField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        usernameField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180)), 
            new EmptyBorder(8, 10, 8, 10) // More padding
        ));
        loginPanel.add(usernameField, gbc);

        // --- Password Field ---
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        loginPanel.add(passLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 1; gbc.weightx = 1.0;
        passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180)), 
            new EmptyBorder(8, 10, 8, 10) // More padding
        ));
        loginPanel.add(passwordField, gbc);

        // --- Button Panel ---
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setOpaque(false); 

        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        loginButton.setBackground(PRIMARY_COLOR);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setPreferredSize(new Dimension(120, 40)); // Larger buttons
        
        registerButton = new JButton("Register");
        registerButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        registerButton.setBackground(ACCENT_COLOR);
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        registerButton.setPreferredSize(new Dimension(120, 40)); // Larger buttons

        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);

        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2; // Span across two columns
        gbc.insets = new Insets(40, 5, 10, 5); 
        loginPanel.add(buttonPanel, gbc);

        // --- Action Listeners (Functionality Remains SAME) ---
        
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
    
    // --- Helper Methods (Functionality Remains SAME) ---

    private void handleLogin() {
        String user = usernameField.getText();
        String pass = new String(passwordField.getPassword());
        
        if (user.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username and Password cannot be empty.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // This is your original login call
        User u = controller.login(user, pass);
        if (u != null) {
            JOptionPane.showMessageDialog(this, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            // This assumes your ParkingDashboard class is defined elsewhere
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
        
        // This is your original register call
        if (controller.register(user, pass, "user")) {
            JOptionPane.showMessageDialog(this, "Registered successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Registration failed! User may already exist.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
