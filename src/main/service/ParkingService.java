package main.service;

import main.dao.ParkingDao;
import main.model.ParkingSlot;
import java.util.List;

public class ParkingService {
    private ParkingDao dao = new ParkingDao();

    public List<ParkingSlot> viewSlots() {
        return dao.getAllSlots();
    }

    public boolean occupySlot(int slotId, int vehicleId) {
        return dao.updateSlotStatus(slotId, "occupied", vehicleId);
    }

    public boolean releaseSlot(int slotId) {
        return dao.updateSlotStatus(slotId, "available", null);
    }

    public ParkingSlot getFreeSlot(String type) {
        return dao.getAvailableSlotByType(type);
    }
}
