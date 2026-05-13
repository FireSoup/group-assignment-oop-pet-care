package staff;

// VetTechnician is a type of Staff
// They assist vets and are assigned to a specific room and duty
public class VetTechnician extends Staff {

    private String roomId;       // which room they are assigned to, example: ROOM-3
    private String assignedDuty; // what their job is, example: Surgical Assistant

    // Constructor - sets up Staff fields first, then vet tech specific fields
    public VetTechnician(String staffID, String name, String shiftTimes, double salary,
                         String roomId, String assignedDuty) {
        super(staffID, name, shiftTimes, salary); // calls Staff constructor
        this.roomId = roomId;
        this.assignedDuty = assignedDuty;
    }

    // Getters for vet tech specific fields
    public String getRoomId() { return roomId; }
    public String getAssignedDuty() { return assignedDuty; }

    // Setters for vet tech specific fields
    public void setRoomId(String roomId) { this.roomId = roomId; }
    public void setAssignedDuty(String assignedDuty) { this.assignedDuty = assignedDuty; }

    // Returns the job title for this staff type
    @Override
    public String getRole() { return "Vet Technician"; }

    // Prints all info specific to a Vet Technician
    @Override
    public void displayDetails() {
        System.out.println("[Vet Technician] " + getName() +
                " | ID: " + getStaffID() +
                " | Room: " + roomId +
                " | Duty: " + assignedDuty +
                " | Shift: " + getShiftTimes() +
                " | Salary: RM" + getSalary());
    }

    // Vet Technician monthly pay is just their base salary
    @Override
    public double calculateMonthlyPay() { return getSalary(); }

    // Vet Technician gets a flat RM200 room allowance as bonus
    @Override
    public double calculateBonus() { return 200.00; }
}