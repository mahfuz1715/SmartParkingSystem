package main.ui;

import main.controller.ParkingController;
import main.model.ParkingSlot;
import main.model.User;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ParkingDashboard extends JFrame {
    private ParkingController controller = new ParkingController();
    private JTable table;
    private JButton refreshButton, parkButton, releaseButton;
    public User currentUser;

    public ParkingDashboard(User u) {
        this.currentUser = u;
        setTitle("Smart Parking Dashboard - " + u.getUsername());
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        refreshButton = new JButton("Refresh");
        parkButton = new JButton("Park Vehicle");
        releaseButton = new JButton("Release Slot");

        JPanel topPanel = new JPanel();
        topPanel.add(refreshButton);
        topPanel.add(parkButton);
        topPanel.add(releaseButton);
        add(topPanel, BorderLayout.NORTH);

        String[] columns = { "ID", "Slot No", "Type", "Status" };
        Object[][] data = {};
        table = new JTable(data, columns);
        add(new JScrollPane(table), BorderLayout.CENTER);

        refreshButton.addActionListener(e -> loadSlots());
        parkButton.addActionListener(e -> handlePark());
        releaseButton.addActionListener(e -> handleRelease());

        loadSlots();
    }

    private void loadSlots() {
        List<ParkingSlot> slots = controller.getSlots();
        Object[][] data = new Object[slots.size()][4];
        for (int i = 0; i < slots.size(); i++) {
            ParkingSlot s = slots.get(i);
            data[i][0] = s.getId();
            data[i][1] = s.getSlotNumber();
            data[i][2] = s.getType();
            data[i][3] = s.getStatus();
        }
        String[] columns = { "ID", "Slot No", "Type", "Status" };
        table.setModel(new javax.swing.table.DefaultTableModel(data, columns));
    }

    private void handlePark() {
        String type = JOptionPane.showInputDialog("Enter vehicle type (Compact/Large/Electric):");
        int vehicleId = (int) (Math.random() * 1000); // fake id for now
        if (controller.parkVehicle(vehicleId, type)) {
            JOptionPane.showMessageDialog(this, "Vehicle parked successfully!");
            loadSlots();
        } else {
            JOptionPane.showMessageDialog(this, "No slots available for this type.");
        }
    }

    private void handleRelease() {
        String slotIdStr = JOptionPane.showInputDialog("Enter slot ID to release:");
        try {
            int slotId = Integer.parseInt(slotIdStr);
            if (controller.releaseSlot(slotId)) {
                JOptionPane.showMessageDialog(this, "Slot released!");
                loadSlots();
            } else {
                JOptionPane.showMessageDialog(this, "Error releasing slot.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid slot ID.");
        }
    }

    public void setManagementActionsVisible(boolean visible) {
        parkButton.setVisible(visible);
        releaseButton.setVisible(visible);
    }
}
