package main.model;

public class Vehicle {
    private int id;
    private String number;
    private String type;
    private int userId;

    public Vehicle() {}
    public Vehicle(int id, String number, String type, int userId) {
        this.id = id;
        this.number = number;
        this.type = type;
        this.userId = userId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    @Override
    public String toString() {
        return number + " (" + type + ")";
    }
}
