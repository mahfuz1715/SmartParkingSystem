package main.service;

import java.util.List;

import main.dao.ParkingDao;
import main.model.ParkingSlot;

// Define an interface for the Service
interface IParkingService {
    boolean occupySlot(int slotId, int vehicleId);
}

// The Real Service
public class ParkingService implements IParkingService {
    public boolean occupySlot(int slotId, int vehicleId) {
        // Core Logic
        return new ParkingDao().updateSlotStatus(slotId, vehicleId, "OCCUPIED");
    }

    public List<ParkingSlot> viewSlots() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'viewSlots'");
    }

    public ParkingSlot getFreeSlot(String type) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFreeSlot'");
    }

    public boolean releaseSlot(int slotId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'releaseSlot'");
    }
}

// The Proxy Service (New)
class ParkingServiceProxy implements IParkingService {
    private ParkingService realService = new ParkingService();
    private String userRole;

    public ParkingServiceProxy(String role) { this.userRole = role; }

    @Override
    public boolean occupySlot(int slotId, int vehicleId) {
        // Access Control Logic
        if ("ADMIN".equals(userRole) || "USER".equals(userRole)) {
            return realService.occupySlot(slotId, vehicleId);
        }
        System.out.println("Access Denied: You don't have permission to park.");
        return false;
    }
}
