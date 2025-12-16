package main.ui;

import main.model.User;
import javax.swing.*;
import java.awt.*;

public class AdminDashboard extends ParkingDashboard {

    public AdminDashboard(User u) {
        super(u);
        setTitle("Admin Dashboard - " + u.getUsername());
        
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

    @Override
    protected void handlePark() {
        JOptionPane.showMessageDialog(this, "Admin cannot park vehicles directly.");
    }

    @Override
    protected void handleRelease() {
        JOptionPane.showMessageDialog(this, "Admin cannot release slots directly.");
    }

    private void viewUsers() {
        JOptionPane.showMessageDialog(this, "Showing user list...");
    }
}
