package staff;

// Veterinarian is a type of Staff
// "extends Staff" means it inherits everything from Staff
// (staffID, name, shiftTimes, salary, getters, setters)
// Then it adds its own extra fields: licenseNumber and specialty
public class Veterinarian extends Staff {

    private String licenseNumber; // their official vet license, example: VET-2021-001
    private String specialty;     // what they specialize in, example: General, Avian, Exotic

    // Constructor - sets up both the Staff fields (via super) and the vet-specific fields
    public Veterinarian(String staffID, String name, String shiftTimes, double salary,
                        String licenseNumber, String specialty) {
        super(staffID, name, shiftTimes, salary); // calls Staff constructor to set up shared fields
        this.licenseNumber = licenseNumber;
        this.specialty = specialty;
    }

    // Getters for vet-specific fields
    public String getLicenseNumber() { return licenseNumber; }
    public String getSpecialty() { return specialty; }

    // Setters for vet-specific fields
    public void setLicenseNumber(String licenseNumber) { this.licenseNumber = licenseNumber; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }

    // Returns the job title for this staff type
    @Override
    public String getRole() { return "Veterinarian"; }

    // Prints all info specific to a Veterinarian
    @Override
    public void displayDetails() {
        System.out.println("[Veterinarian] " + getName() +
                " | ID: " + getStaffID() +
                " | License: " + licenseNumber +
                " | Specialty: " + specialty +
                " | Shift: " + getShiftTimes() +
                " | Salary: RM" + getSalary());
    }

    // Veterinarian monthly pay is just their base salary
    @Override
    public double calculateMonthlyPay() { return getSalary(); }

    // Veterinarian gets a 10% bonus on top of salary
    @Override
    public double calculateBonus() { return getSalary() * 0.10; }
}