package petcare1; 


public class ConsultAndPharmacy {
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

    // Mengurangi stok apabila ubat dikeluarkan
    public void reduceStock(int quantity) {
        this.stockLevel -= quantity;
    }
}

class Prescription {
    private Medication medication; // Hubungan Komposisi (Composition)
    private String dosage;         // Contoh: "1/2 pil"
    private String frequency;      // Contoh: "2 kali sehari"
    private String duration;       // Contoh: "5 hari"

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
        System.out.println("   PROSES PENGELUARAN UBAT (PHARMACY)    ");
        System.out.println("=========================================");
        System.out.println("Tarikh Berkonsultasi: " + this.date);
        System.out.println("Pesakit (Haiwan): " + this.animal.petName + " [" + this.animal.getSpecies() + "]"); 
        System.out.println("Doktor Bertugas: " + this.veterinarian.getName() + " (Pakar: " + this.veterinarian.getSpecialty() + ")"); 
        System.out.println("Gejala / Symptoms: " + this.symptoms);
        System.out.println("Diagnosis: " + this.diagnosis);
        System.out.println("-----------------------------------------");

        if (prescription.getMedication().getStockLevel() >= quantityToDispense) {
            prescription.getMedication().reduceStock(quantityToDispense);
            
            double totalKosUbat = prescription.getMedication().getPricePerPill() * quantityToDispense;
            
            System.out.println("Ubat Diberi: " + prescription.getMedication().getName());
            System.out.println("Aturan Dos: " + prescription.getDosage() + " | " + prescription.getFrequency() + " | " + prescription.getDuration());
            System.out.println("Kuantiti: " + quantityToDispense + " pil/biji");
            System.out.println("Harga per Pil: RM " + prescription.getMedication().getPricePerPill());
            System.out.println("Total Harga Ubat: RM " + totalKosUbat);
            System.out.println("Baki Stok Semasa: " + prescription.getMedication().getStockLevel() + " unit");
            System.out.println("STATUS: SELESAI & BERJAYA DISPENSE");
        } else {
            System.out.println("Ubat Diminta: " + prescription.getMedication().getName());
            System.out.println("STATUS: GAGAL! Stok tidak mencukupi (Baki: " + prescription.getMedication().getStockLevel() + ")");
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
