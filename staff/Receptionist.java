package staff;

// Receptionist is a type of Staff
// They work at the front desk and handle walk-ins and inquiries
public class Receptionist extends Staff {

    private String deskNumber; // which front desk they are stationed at, example: DESK-1

    // Constructor - sets up Staff fields first, then receptionist specific field
    public Receptionist(String staffID, String name, String shiftTimes, double salary,
                        String deskNumber) {
        super(staffID, name, shiftTimes, salary); // calls Staff constructor
        this.deskNumber = deskNumber;
    }

    // Getter for desk number
    public String getDeskNumber() { return deskNumber; }

    // Setter for desk number
    public void setDeskNumber(String deskNumber) { this.deskNumber = deskNumber; }

    // Returns the job title for this staff type
    @Override
    public String getRole() { return "Receptionist"; }

    // Prints all info specific to a Receptionist
    @Override
    public void displayDetails() {
        System.out.println("[Receptionist] " + getName() +
                " | ID: " + getStaffID() +
                " | Desk: " + deskNumber +
                " | Shift: " + getShiftTimes() +
                " | Salary: RM" + getSalary());
    }

    // Receptionist monthly pay is just their base salary
    @Override
    public double calculateMonthlyPay() { return getSalary(); }

    // Receptionist gets a 5% bonus on top of salary
    @Override
    public double calculateBonus() { return getSalary() * 0.05; }
}