package main.controller;

import main.model.ParkingSlot;
import main.model.Ticket;
import main.service.ParkingService;
import main.service.PaymentService;

import java.time.LocalDateTime;
import java.util.List;

public class ParkingController {
    private ParkingService parkingService = new ParkingService();
    private PaymentService paymentService = new PaymentService();

    public List<ParkingSlot> getSlots() {
        return parkingService.viewSlots();
    }

    public boolean parkVehicle(int vehicleId, String type) {
        ParkingSlot slot = parkingService.getFreeSlot(type);
        if (slot == null) {
            System.out.println("No available slots for type: " + type);
            return false;
        }
        boolean success = parkingService.occupySlot(slot.getId(), vehicleId);
        if (success) {
            Ticket ticket = new Ticket(vehicleId, slot.getId(), LocalDateTime.now());
            paymentService.openTicket(ticket);
        }
        return success;
    }

    public boolean releaseSlot(int slotId) {
        return parkingService.releaseSlot(slotId);
    }
}
