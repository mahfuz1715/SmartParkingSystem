package main.ui;

import main.model.User;
import javax.swing.*;
import java.awt.*;

// Refused Bequest: Inherits from ParkingDashboard but overrides
// user-centric methods to do nothing, indicating poor inheritance design.
public class AdminDashboard extends ParkingDashboard {

    public AdminDashboard(User u) {
        super(u);
        setTitle("Admin Dashboard - " + u.getUsername());
        
        // Remove or hide user-centric buttons not needed for admin
        // Note: For a proper GUI, we'd remove listeners/buttons, but for
        // the Refused Bequest smell, overriding the handler methods is key.
        JComponent topPanel = (JComponent) this.getContentPane().getComponent(0);
        for (Component comp : topPanel.getComponents()) {
            if (comp instanceof JButton) {
                if (((JButton) comp).getText().equals("Park Vehicle")) {
                    comp.setVisible(false);
                }
                if (((JButton) comp).getText().equals("Release Slot")) {
                    comp.setVisible(false);
                }
            }
        }
    }

    /* @Override
    protected void handlePark() {
        // Refused Bequest: Admin doesn't park vehicles, but inherits this method.
        JOptionPane.showMessageDialog(this, "Admin cannot park vehicles directly.");
    }

    @Override
    protected void handleRelease() {
        // Refused Bequest: Admin doesn't release slots, but inherits this method.
        JOptionPane.showMessageDialog(this, "Admin cannot release slots directly.");
    }

    private void viewUsers() {
        // Admin-specific method that is not in the parent class
        JOptionPane.showMessageDialog(this, "Showing user list...");
        // Logic to show user list...
    } */
}
