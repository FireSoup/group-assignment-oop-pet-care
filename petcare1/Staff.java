package petcare1;
import java.util.ArrayList;
import java.util.List;

enum Specialty { GENERAL, AVIAN, EXOTIC, SURGERY, DENTAL, VACCINATION}

public abstract class Staff {
    protected String staffID;
    protected String name;
    protected String shiftTimes; 

    public Staff(String staffID, String name, String shiftTimes) {
        this.staffID = staffID; this.name = name; this.shiftTimes = shiftTimes; 
    }
    public String getStaffID()    { return staffID; }
    public String getName()       { return name; }
    public String getShiftTimes() { return shiftTimes; }
    public abstract String getRole();
}

class Veterinarian extends Staff {
    private String licenseNumber;
    private Specialty specialty;

    public Veterinarian(String staffID, String name, String shiftTimes, String licenseNumber, Specialty specialty) {
        super(staffID, name, shiftTimes);
        this.licenseNumber = licenseNumber; 
        this.specialty = specialty;
    }
    public String getLicenseNumber() { return licenseNumber; }
    public Specialty getSpecialty()  { return specialty; }

    // =========================================================
    // NEW FIX: This method allows the GUI to filter the doctors!
    // =========================================================
    public boolean hasSpecialty(Specialty reqSpec) {
        return this.specialty == reqSpec;
    }

    @Override public String getRole() { return "Veterinarian"; }
}

class Assistant extends Staff {
    private String assignedWard;  
    public Assistant(String staffID, String name, String shiftTimes, String assignedWard) {
        super(staffID, name, shiftTimes);
        this.assignedWard = assignedWard; 
    }
    public String getAssignedWard() { return assignedWard; }
    @Override public String getRole() { return "Vet Assistant"; }
}

class StaffDirectory {
    public static List<Veterinarian> loadAvailableDoctors() {
        List<Veterinarian> doctors = new ArrayList<>();
        doctors.add(new Veterinarian("V-01", "Dr. Alice", "Morning", "LIC-111", Specialty.GENERAL));
        doctors.add(new Veterinarian("V-02", "Dr. Bob", "Evening", "LIC-222", Specialty.SURGERY));
        doctors.add(new Veterinarian("V-03", "Dr. Charlie", "Night", "LIC-333", Specialty.EXOTIC)); 
        doctors.add(new Veterinarian("V-04", "Dr. Diana", "Morning", "LIC-444", Specialty.GENERAL));
        doctors.add(new Veterinarian("V-05", "Dr. Austine", "Night", "LIC-555", Specialty.VACCINATION));
        return doctors;
    }
}
