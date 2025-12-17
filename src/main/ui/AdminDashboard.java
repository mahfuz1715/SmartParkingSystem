package main.ui;

import main.model.User;

public class AdminDashboard extends ParkingDashboard {

    public AdminDashboard(User u) {
        super(u);
        setTitle("Admin Dashboard - " + u.getUsername());
        
        this.setManagementActionsVisible(false);
    }
}
