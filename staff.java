package staff;

import java.util.Scanner;

// RUNNER (just to test / show output - safe to delete) =====
public class Main {
    public static void main(String[] args) {
        Staff vet   = new Veterinarian("V001", "Alice", "8am-4pm", 5000, "VET-2021-001", Specialty.SURGERY);
        Staff asst  = new Assistant("A001", "Bob", "9am-5pm", 3000, "ROOM-2", "Vaccination", "Cat - Persian, 3yrs");
        Staff pharm = new Pharmacist("P001", "Carol", "10am-6pm", 4000, "PHARM-2022-045");

        vet.displayDetails();
        asst.displayDetails();
        pharm.displayDetails();

        System.out.printf("Bonuses -> Vet: RM%.2f | Assistant: RM%.2f | Pharmacist: RM%.2f%n",
                vet.calculateBonus(), asst.calculateBonus(), pharm.calculateBonus());
    }
}

// A contract - any class that "implements Payable" MUST have these two methods.
interface Payable {
    double calculateMonthlyPay(); // base monthly pay
    double calculateBonus();      // extra money on top
}

// A fixed list of allowed values so nobody can mistype a specialty.
enum Specialty {
    GENERAL, AVIAN, EXOTIC, SURGERY, DENTAL;

    // Turns a menu number (1,2,3...) into the matching Specialty. Invalid -> GENERAL.
    public static Specialty fromChoice(int choice) {
        Specialty[] all = values();
        if (choice < 1 || choice > all.length) return GENERAL;
        return all[choice - 1];
    }
}

//Staff (abstract base)
//Blueprint for ALL staff types. "abstract" = cannot be created directly.
abstract class Staff implements Payable, Comparable<Staff> {
    private String staffID, name, shiftTimes; // shared info
    private double salary;

    public Staff(String staffID, String name, String shiftTimes, double salary) {
        this.staffID = staffID; this.name = name; this.shiftTimes = shiftTimes; this.salary = salary;
    }

    // Getters - read the private fields
    public String getStaffID()    { return staffID; }
    public String getName()       { return name; }
    public String getShiftTimes() { return shiftTimes; }
    public double getSalary()     { return salary; }

    // Setters - update the private fields (no setStaffID - ID never changes)
    public void setName(String name)             { this.name = name; }
    public void setShiftTimes(String shiftTimes) { this.shiftTimes = shiftTimes; }
    public void setSalary(double salary)         { this.salary = salary; }

    // Abstract methods - every subclass MUST write its own version
    public abstract String getRole();
    public abstract void displayDetails();
    public abstract void editSubclassFields(Scanner scanner);

    @Override public double calculateMonthlyPay() { return salary; }        // default pay = salary
    @Override public int compareTo(Staff other) { return this.staffID.compareTo(other.staffID); } // sort by ID
    @Override public String toString() {
        return String.format("[%s] %s (ID: %s) | Shift: %s | Salary: RM%.2f", getRole(), name, staffID, shiftTimes, salary);
    }
}

// Veterinarian
// A type of Staff. Adds license + specialty. Earns a 10% bonus.
class Veterinarian extends Staff {
    private String licenseNumber;
    private Specialty specialty;

    public Veterinarian(String staffID, String name, String shiftTimes, double salary, String licenseNumber, Specialty specialty) {
        super(staffID, name, shiftTimes, salary);
        this.licenseNumber = licenseNumber; this.specialty = specialty;
    }

    public String getLicenseNumber() { return licenseNumber; }
    public Specialty getSpecialty()  { return specialty; }
    public void setLicenseNumber(String licenseNumber) { this.licenseNumber = licenseNumber; }
    public void setSpecialty(Specialty specialty)      { this.specialty = specialty; }

    @Override public String getRole() { return "Veterinarian"; }

    @Override public void displayDetails() {
        System.out.printf("[Veterinarian] %s | ID: %s | License: %s | Specialty: %s | Shift: %s | Salary: RM%.2f%n",
                getName(), getStaffID(), licenseNumber, specialty, getShiftTimes(), getSalary());
    }

    @Override public void editSubclassFields(Scanner scanner) {
        System.out.print("New License Number (Enter to keep): ");
        String nl = scanner.nextLine().trim();
        if (!nl.isEmpty()) this.licenseNumber = nl;
        System.out.println("Specialty options:");
        Specialty[] all = Specialty.values();
        for (int i = 0; i < all.length; i++) System.out.println("  " + (i + 1) + ". " + all[i]);
        System.out.print("Choose new specialty (Enter to keep): ");
        String cs = scanner.nextLine().trim();
        if (!cs.isEmpty()) {
            try { this.specialty = Specialty.fromChoice(Integer.parseInt(cs)); }
            catch (NumberFormatException e) { System.out.println("Invalid input - specialty unchanged."); }
        }
    }

    @Override public double calculateBonus() { return getSalary() * 0.10; } // 10% bonus
}

// Assistant (replaces VetTechnician)
// A type of Staff. The doctor assigns a room, a treatment, and an animal.
class Assistant extends Staff {
    private String assignedRoom;  // which room, e.g. ROOM-2
    private String treatment;     // what treatment, e.g. Vaccination
    private String animalDetails; // animal info, e.g. "Cat - Persian, 3yrs"

    public Assistant(String staffID, String name, String shiftTimes, double salary, String assignedRoom, String treatment, String animalDetails) {
        super(staffID, name, shiftTimes, salary);
        this.assignedRoom = assignedRoom; this.treatment = treatment; this.animalDetails = animalDetails;
    }

    public String getAssignedRoom()  { return assignedRoom; }
    public String getTreatment()     { return treatment; }
    public String getAnimalDetails() { return animalDetails; }
    public void setAssignedRoom(String assignedRoom)   { this.assignedRoom = assignedRoom; }
    public void setTreatment(String treatment)         { this.treatment = treatment; }
    public void setAnimalDetails(String animalDetails) { this.animalDetails = animalDetails; }

    @Override public String getRole() { return "Assistant"; }

    @Override public void displayDetails() {
        System.out.printf("[Assistant] %s | ID: %s | Room: %s | Treatment: %s | Animal: %s | Shift: %s | Salary: RM%.2f%n",
                getName(), getStaffID(), assignedRoom, treatment, animalDetails, getShiftTimes(), getSalary());
    }

    @Override public void editSubclassFields(Scanner scanner) {
        System.out.print("New Assigned Room (Enter to keep): ");
        String r = scanner.nextLine().trim();
        if (!r.isEmpty()) this.assignedRoom = r;
        System.out.print("New Treatment (Enter to keep): ");
        String t = scanner.nextLine().trim();
        if (!t.isEmpty()) this.treatment = t;
        System.out.print("New Animal Details (Enter to keep): ");
        String a = scanner.nextLine().trim();
        if (!a.isEmpty()) this.animalDetails = a;
    }

    @Override public double calculateBonus() { return 150.00; } // flat RM150
}

// Pharmacist
// a type of Staff. Tracks prescriptions dispensed. Earns a 7% bonus.
class Pharmacist extends Staff {
    private String pharmacyLicense;
    private int prescriptionsDispensed; // starts at 0

    public Pharmacist(String staffID, String name, String shiftTimes, double salary, String pharmacyLicense) {
        super(staffID, name, shiftTimes, salary);
        this.pharmacyLicense = pharmacyLicense; this.prescriptionsDispensed = 0;
    }

    public String getPharmacyLicense()     { return pharmacyLicense; }
    public int getPrescriptionsDispensed() { return prescriptionsDispensed; }
    public void setPharmacyLicense(String pharmacyLicense) { this.pharmacyLicense = pharmacyLicense; }

    // A method (not a setter) so nobody can set the count to a random number.
    public void dispensePrescription() { prescriptionsDispensed++; }

    @Override public String getRole() { return "Pharmacist"; }

    @Override public void displayDetails() {
        System.out.printf("[Pharmacist] %s | ID: %s | License: %s | Dispensed: %d | Shift: %s | Salary: RM%.2f%n",
                getName(), getStaffID(), pharmacyLicense, prescriptionsDispensed, getShiftTimes(), getSalary());
    }

    @Override public void editSubclassFields(Scanner scanner) {
        System.out.print("New Pharmacy License (Enter to keep): ");
        String nl = scanner.nextLine().trim();
        if (!nl.isEmpty()) this.pharmacyLicense = nl;
    }

    @Override public double calculateBonus() { return getSalary() * 0.07; } // 7% bonus
}
