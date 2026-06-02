package petcare1; 


public class packagedeveloper5 {
    public void infoModul() {
        System.out.println("====== MODUL 5: ACTIVE CONSULTATION & PHARMACY ======");
    }
}

class Medication {
    private String name;
    private int stockLevel;
    private double pricePerPill;

    public Medication(String name, int stockLevel, double pricePerPill) {
        this.name = name;
        this.stockLevel = stockLevel;
        this.pricePerPill = pricePerPill;
    }

    public String getName() { return this.name; }
    public int getStockLevel() { return this.stockLevel; }
    public double getPricePerPill() { return this.pricePerPill; }

    // reduce stok when medication is dispensed
    public void reduceStock(int quantity) {
        this.stockLevel -= quantity;
    }
}

class Prescription {
    private Medication medication; // medicine recommended
    private String dosage;         // example: "1/2 pill"
    private String frequency;      // example: "twiice per day"
    private String duration;       // example: "five days"

    public Prescription(Medication medication, String dosage, String frequency, String duration) {
        this.medication = medication;
        this.dosage = dosage;
        this.frequency = frequency;
        this.duration = duration;
    }

    public Medication getMedication() { return this.medication; }
    public String getDosage() { return this.dosage; }
    public String getFrequency() { return this.frequency; }
    public String getDuration() { return this.duration; }
}

class Consultation {
    private String date;
    private String symptoms;
    private String diagnosis;
    
    private Veterinarian veterinarian; 
    private Animals animal;             
    private Prescription prescription; 

    public Consultation(String date, String symptoms, String diagnosis, Veterinarian veterinarian, Animals animal) {
        this.date = date;
        this.symptoms = symptoms;
        this.diagnosis = diagnosis;
        this.veterinarian = veterinarian;
        this.animal = animal;
    }

    public void dispenseMedication(Prescription prescription, int quantityToDispense) {
        this.prescription = prescription;
        
        System.out.println("\n=========================================");
        System.out.println("   MEDICINE PRODUCTION PROCESS (PHARMACY)    ");
        System.out.println("=========================================");
        System.out.println("Consultation date: " + this.date);
        System.out.println("Patient (Animal): " + this.animal.petName + " [" + this.animal.getSpecies() + "]"); 
        System.out.println("Attending Veterinarian: " + this.veterinarian.getName() + " (Specialty: " + this.veterinarian.getSpecialty() + ")"); 
        System.out.println("Symptoms: " + this.symptoms);
        System.out.println("Diagnosis: " + this.diagnosis);
        System.out.println("-----------------------------------------");

        if (prescription.getMedication().getStockLevel() >= quantityToDispense) {
            prescription.getMedication().reduceStock(quantityToDispense);
            
            double totalKosUbat = prescription.getMedication().getPricePerPill() * quantityToDispense;
            
            System.out.println("Medication dispensed: " + prescription.getMedication().getName());
            System.out.println("Dosage instructions: " + prescription.getDosage() + " | " + prescription.getFrequency() + " | " + prescription.getDuration());
            System.out.println("Quantity: " + quantityToDispense + " pills/tablets");
            System.out.println("Price per Pill: RM " + prescription.getMedication().getPricePerPill());
            System.out.println("Total Medication Cost: RM " + totalKosUbat);
            System.out.println("Current Stock Balance: " + prescription.getMedication().getStockLevel() + " units");
            System.out.println("STATUS: COMPLETED & SUCCESSFULLY DISPENSED");
        } else {
            System.out.println("Requested Medication: " + prescription.getMedication().getName());
            System.out.println("STATUS: FAILED! Insufficient stock (Available: " + prescription.getMedication().getStockLevel() + ")");
        }
        System.out.println("=========================================\n");
    }
    
    public double getPrescriptionCost(int quantity) {
        if (prescription != null) {
            return prescription.getMedication().getPricePerPill() * quantity;
        }
        return 0.0;
    }
}
