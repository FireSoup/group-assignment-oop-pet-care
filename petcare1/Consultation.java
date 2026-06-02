package petcare1;
import java.util.ArrayList;
import java.util.List;

class Medication {
    private String name; private int stockLevel; private double pricePerPill;
    public Medication(String name, int stockLevel, double pricePerPill) {
        this.name = name; this.stockLevel = stockLevel; this.pricePerPill = pricePerPill;
    }
    public String getName() { return this.name; }
    public int getStockLevel() { return this.stockLevel; }
    public double getPricePerPill() { return this.pricePerPill; }
    public void reduceStock(int quantity) { this.stockLevel -= quantity; }
}

// NEW: Centralized Database for Medicines
class PharmacyDirectory {
    public static List<Medication> loadAvailableMedications() {
        List<Medication> meds = new ArrayList<>();
        meds.add(new Medication("Amoxicillin", 100, 2.50));
        meds.add(new Medication("Rimadyl (Pain Relief)", 50, 4.00));
        meds.add(new Medication("NexGard (Flea/Tick)", 200, 45.00));
        return meds;
    }
}

class Prescription implements Billable {
    private Medication medication; private String dosage, frequency, duration; 
    private int quantity; private Veterinarian veterinarian; 
    public Prescription(Medication medication, String dosage, String frequency, String duration, int quantity, Veterinarian veterinarian) {
        this.medication = medication; this.dosage = dosage; this.frequency = frequency; this.duration = duration; this.quantity = quantity; this.veterinarian = veterinarian;
    }
    public Medication getMedication() { return this.medication; }
    public String getDosage() { return this.dosage; }
    public String getFrequency() { return this.frequency; }
    public String getDuration() { return this.duration; }
    
    @Override public String getItemName() { return medication.getName() + " (x" + quantity + ")"; }
    @Override public double getPrice() { return medication.getPricePerPill() * quantity; }
    @Override public String getAssignedDoctor() { return veterinarian.getName(); }
}

public class Consultation implements Billable {
    private String date, symptoms, diagnosis;
    private double consultationFee; 
    private Veterinarian veterinarian; 
    private Animals animal;              
    private Prescription prescription; 

    public Consultation(String date, String symptoms, String diagnosis, double consultationFee, Veterinarian veterinarian, Animals animal) {
        this.date = date; this.symptoms = symptoms; this.diagnosis = diagnosis;
        this.consultationFee = consultationFee; this.veterinarian = veterinarian; this.animal = animal;
    }

    // CHANGED: Now returns a String instead of using System.out.println
    public String dispenseMedication(Prescription prescription, int quantityToDispense) {
        this.prescription = prescription;
        StringBuilder sb = new StringBuilder();
        
        sb.append("\n=========================================\n");
        sb.append("   MEDICINE PRODUCTION PROCESS (PHARMACY)    \n");
        sb.append("=========================================\n");
        sb.append("Consultation date: ").append(this.date).append("\n");
        sb.append("Patient: ").append(this.animal.petName).append("\n"); 
        sb.append("Attending Veterinarian: ").append(this.veterinarian.getName()).append("\n"); 
        sb.append("Symptoms: ").append(this.symptoms).append(" | Diagnosis: ").append(this.diagnosis).append("\n");
        sb.append("-----------------------------------------\n");
        
        if (prescription.getMedication().getStockLevel() >= quantityToDispense) {
            prescription.getMedication().reduceStock(quantityToDispense);
            double totalKos = prescription.getMedication().getPricePerPill() * quantityToDispense;
            sb.append("Dispensed: ").append(prescription.getMedication().getName()).append(" | Cost: RM ").append(totalKos).append("\n");
        } else {
            sb.append("STATUS: FAILED! Insufficient stock.\n");
        }
        sb.append("=========================================\n");
        return sb.toString();
    }

    public String getDiagnosis() { return diagnosis; }
    @Override public String getItemName() { return "Consultation: " + diagnosis; }
    @Override public double getPrice() { return this.consultationFee; }
    @Override public String getAssignedDoctor() { return this.veterinarian.getName(); }
}
