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

    private Ticket(Builder builder) {
        this.vehicleId = builder.vehicleId;
        this.slotId = builder.slotId;
        this.entryTime = builder.entryTime;
        this.exitTime = builder.exitTime;
    }

    public Ticket(int vehicleId2, int id2, LocalDateTime now) {
    }

    public static class Builder {
        private int vehicleId;
        private int slotId;
        private LocalDateTime entryTime;
        private LocalDateTime exitTime;

        public Builder setVehicleId(int id) { this.vehicleId = id; return this; }
        public Builder setSlotId(int id) { this.slotId = id; return this; }
        public Builder setEntryTime(LocalDateTime time) { this.entryTime = time; return this; }
        
        public Ticket build() {
            return new Ticket(this);
        }
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
