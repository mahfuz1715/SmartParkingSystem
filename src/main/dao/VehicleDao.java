package main.dao;
import main.model.Vehicle;
import java.sql.*;
import java.util.Optional;

public class VehicleDao {
    public boolean createVehicle(Vehicle vehicle) {
        
        String sql = "INSERT INTO vehicles (number, type, user_id) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, vehicle.getNumber());
            ps.setString(2, vehicle.getType());
            ps.setInt(3, vehicle.getUserId());
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    vehicle.setId(rs.getInt(1));
                }
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.out.println("Error inserting vehicle: " + e.getMessage());
            return false;
        }
    }

    public Optional<Vehicle> getVehicleById(int id) {
        String sql = "SELECT * FROM vehicles WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(new Vehicle(
                    rs.getInt("id"),
                    rs.getString("number"),
                    rs.getString("type"),
                    rs.getInt("user_id")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching vehicle: " + e.getMessage());
        }
        return Optional.empty(); // Returns an empty Optional if not found in DB
    }
}
