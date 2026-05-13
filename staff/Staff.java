package staff;

// This is the base class for ALL staff types
// We made it abstract so nobody can create a "Staff" directly
// You can only create Veterinarian, VetTechnician, or Receptionist
// It also uses the Payable contract, so all staff must have pay methods
public abstract class Staff implements Payable {

    // These are the fields every staff member has in common
    private String staffID;    // unique ID like V001
    private String name;       // full name
    private String shiftTimes; // example: 8am-4pm
    private double salary;     // base monthly salary

    // Constructor - runs when we create a new staff object
    // Sets up all the basic info every staff needs
    public Staff(String staffID, String name, String shiftTimes, double salary) {
        this.staffID = staffID;
        this.name = name;
        this.shiftTimes = shiftTimes;
        this.salary = salary;
    }

    // Getters - used to READ the private fields from outside this class
    public String getStaffID() { return staffID; }
    public String getName() { return name; }
    public String getShiftTimes() { return shiftTimes; }
    public double getSalary() { return salary; }

    // Setters - used to UPDATE the private fields from outside this class
    // No setStaffID because ID should never change once assigned
    public void setName(String name) { this.name = name; }
    public void setShiftTimes(String shiftTimes) { this.shiftTimes = shiftTimes; }
    public void setSalary(double salary) { this.salary = salary; }

    // Abstract method - every subclass MUST write their own version of this
    // Returns the job title like "Veterinarian" or "Receptionist"
    public abstract String getRole();

    // Abstract method - every subclass MUST write their own version of this
    // Prints all the details of that specific staff type
    public abstract void displayDetails();
}