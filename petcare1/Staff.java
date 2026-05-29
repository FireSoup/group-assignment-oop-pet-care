package petcare1;

import java.util.ArrayList;
import java.util.List;

// Enum for standardizing doctor specialties
enum Specialty {
    GENERAL, AVIAN, EXOTIC, SURGERY, DENTAL;
}

public abstract class Staff {
    protected String staffID;
    protected String name;
    protected String shiftTimes; 

    public Staff(String staffID, String name, String shiftTimes) {
        this.staffID = staffID; 
        this.name = name; 
        this.shiftTimes = shiftTimes; 
    }

    public String getStaffID()    { return staffID; }
    public String getName()       { return name; }
    public String getShiftTimes() { return shiftTimes; }

    public abstract String getRole();
}

// --- SUBCLASSES ---

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

    @Override 
    public String getRole() { return "Veterinarian"; }
}

class Assistant extends Staff {
    private String assignedWard;  

    public Assistant(String staffID, String name, String shiftTimes, String assignedWard) {
        super(staffID, name, shiftTimes);
        this.assignedWard = assignedWard; 
    }
    
    public String getAssignedWard() { return assignedWard; }

    @Override 
    public String getRole() { return "Vet Assistant"; }
}

// --- MOCK DATABASE ---
// This safely stores the doctors so they don't have to be hardcoded into the GUI
class StaffDirectory {
    public static List<Veterinarian> loadAvailableDoctors() {
        List<Veterinarian> doctors = new ArrayList<>();
        doctors.add(new Veterinarian("V-01", "Dr. Alice", "Morning", "LIC-111", Specialty.GENERAL));
        doctors.add(new Veterinarian("V-02", "Dr. Bob", "Evening", "LIC-222", Specialty.SURGERY));
        doctors.add(new Veterinarian("V-03", "Dr. Charlie", "Night", "LIC-333", Specialty.EXOTIC));
        return doctors;
    }
}