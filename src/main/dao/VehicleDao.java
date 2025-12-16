package main.dao;

import main.model.Vehicle;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional; 

public class VehicleDao {
    private static List<Vehicle> vehicleStore = new ArrayList<>();
    private static int nextId = 1; 

    static {
        vehicleStore.add(new Vehicle(nextId++, "ABC1234", "Compact", 1));
        vehicleStore.add(new Vehicle(nextId++, "XYZ9876", "Large", 2));
    }


    public boolean createVehicle(Vehicle vehicle) {
        if (vehicle.getId() == 0) {
            vehicle.setId(nextId++);
        }
        vehicleStore.add(vehicle);
        System.out.println("Vehicle created in-memory: " + vehicle);
        return true;
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

        return vehicleStore.stream().filter(v -> v.getId() == id).findFirst();
    }
}
