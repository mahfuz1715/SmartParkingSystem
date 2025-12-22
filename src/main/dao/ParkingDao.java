package main.dao;

import main.model.ParkingSlot;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ParkingDao {

    public List<ParkingSlot> getAllSlots() {
        List<ParkingSlot> list = new ArrayList<>();
        String sql = "SELECT * FROM slots";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                ParkingSlot slot = new ParkingSlot(
                        rs.getInt("id"),
                        rs.getString("slot_number"),
                        rs.getString("type"),
                        rs.getString("status")
                );
                slot.setVehicleId((Integer) rs.getObject("vehicle_id"));
                list.add(slot);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching slots: " + e.getMessage());
        }
        return list;
    }

    public boolean updateSlotStatus(int slotId, Integer vehicleId, String status) {
        String sql = "UPDATE slots SET status=?, vehicle_id=? WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            if (vehicleId != null) ps.setInt(2, vehicleId);
            else ps.setNull(2, Types.INTEGER);
            ps.setInt(3, slotId);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error updating slot: " + e.getMessage());
            return false;
        }
    }

    public ParkingSlot getAvailableSlotByType(String type) {
        String sql = "SELECT * FROM slots WHERE type=? AND status='available' LIMIT 1";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, type);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new ParkingSlot(
                        rs.getInt("id"),
                        rs.getString("slot_number"),
                        rs.getString("type"),
                        rs.getString("status")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error getting slot: " + e.getMessage());
        }
        return null;
    }
}
