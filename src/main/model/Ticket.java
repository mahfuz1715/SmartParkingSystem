package main.model;

import java.time.Duration;
import java.time.LocalDateTime;

public class Ticket {
    private int id;
    private int vehicleId;
    private int slotId;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private double totalCharge;

    public Ticket() {}
    public Ticket(int vehicleId, int slotId, LocalDateTime entryTime) {
        this.vehicleId = vehicleId;
        this.slotId = slotId;
        this.entryTime = entryTime;
    }

    public double calculateCharge(double ratePerHour) {
        if (entryTime == null || exitTime == null) return 0.0;
        long hours = Duration.between(entryTime, exitTime).toHours();
        if (hours == 0) hours = 1;
        this.totalCharge = hours * ratePerHour;
        return this.totalCharge;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getVehicleId() { return vehicleId; }
    public void setVehicleId(int vehicleId) { this.vehicleId = vehicleId; }
    public int getSlotId() { return slotId; }
    public void setSlotId(int slotId) { this.slotId = slotId; }
    public LocalDateTime getEntryTime() { return entryTime; }
    public void setEntryTime(LocalDateTime entryTime) { this.entryTime = entryTime; }
    public LocalDateTime getExitTime() { return exitTime; }
    public void setExitTime(LocalDateTime exitTime) { this.exitTime = exitTime; }
    public double getTotalCharge() { return totalCharge; }
    public void setTotalCharge(double totalCharge) { this.totalCharge = totalCharge; }
}
