package main.dao;

import main.model.Ticket;
import java.sql.*;
import java.time.LocalDateTime;

interface DatabaseConnection {
    Connection getConnection() throws SQLException;
}

class DBConnection implements DatabaseConnection {
    public static Connection getConnection() throws SQLException {
        
        return DriverManager.getConnection(null);
    }
}

public class PaymentDao {

    private DatabaseConnection dbConnection;

    public PaymentDao(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public PaymentDao() {
    }

    public boolean insertTicket(Ticket t) {
        String sql = "INSERT INTO tickets (vehicle_id, slot_id, entry_time) VALUES (?, ?, ?)";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, t.getVehicleId());
            ps.setInt(2, t.getSlotId());
            ps.setTimestamp(3, Timestamp.valueOf(t.getEntryTime()));
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error creating ticket: " + e.getMessage());
            return false;
        }
    }

    public boolean closeTicket(int ticketId, LocalDateTime exit, double charge) {
        String sql = "UPDATE tickets SET exit_time=?, total_charge=? WHERE id=?";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setTimestamp(1, Timestamp.valueOf(exit));
            ps.setDouble(2, charge);
            ps.setInt(3, ticketId);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error closing ticket: " + e.getMessage());
            return false;
        }
    }
}
