package main.model;

public class ParkingSlot {
    private int id;
    private String slotNumber;
    private String type;
    private String status; 
    private Integer vehicleId;

    public ParkingSlot() {}
    public ParkingSlot(int id, String slotNumber, String type, String status) {
        this.id = id;
        this.slotNumber = slotNumber;
        this.type = type;
        this.status = status;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getSlotNumber() { return slotNumber; }
    public void setSlotNumber(String slotNumber) { this.slotNumber = slotNumber; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Integer getVehicleId() { return vehicleId; }
    public void setVehicleId(Integer vehicleId) { this.vehicleId = vehicleId; }

    @Override
    public String toString() {
        return slotNumber + " - " + type + " (" + status + ")";
    }
}
