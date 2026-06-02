package vaccination;

import java.time.LocalDate;

//Action Class
public class VaccinationRecord {
    protected Pet patient;
    protected Vaccine administredVaccine;
    protected Veterinarian attedingVet;

    private LocalDate administrationDate;
    private LocalDate nextDueDate;

    public VaccinationRecord(Pet patient, Vaccine administredVaccine, Veterinarian attedingVet, LocalDate administrationDate) {
        this.patient = patient;
        this.administredVaccine = administredVaccine;
        this.attedingVet = attedingVet;
        this.administrationDate = administrationDate;

        this.nextDueDate = calculateNextDueDate(administrationDate, administredVaccine.getValidityMonths());

        // Simple stock reduction using basic getter and setter
        int currentStock = this.administredVaccine.getStockQuantity();
        this.administredVaccine.setStockQuantity(currentStock - 1);
    }

    private LocalDate calculateNextDueDate(LocalDate dataGiven, int monthsValid) {
        return dataGiven.plusMonths(monthsValid);
    }

    public Pet getPatient() { return patient; }
    public Vaccine getVaccine() { return administredVaccine; }
    public Veterinarian getAttedingVet() { return attedingVet; }
    public LocalDate getAdministrationDate() { return administrationDate; }
    public LocalDate getNextDueDate() { return nextDueDate; }

    public void printRecordDetails() {
        System.out.println("\n===============================");
        System.out.println("    OFFICIAL VACCINATION RECORD  ");
        System.out.println("=================================");
        System.out.println("Patient Name: " +  patient.getName());
        System.out.println("Doctor      : " + attedingVet.getName());
        System.out.println("__________________________________");
        System.out.println("Vaccine     : " + administredVaccine.getName());
        System.out.println("Batch No.   : " + administredVaccine.getBatchNumber());
        System.out.println("Date Given  : " + administrationDate);
        System.out.println("NEXT DUE    : " + nextDueDate);
        System.out.println("================================\n");
    }
}

//Child Class
class Vaccine extends InventoryItem {
    private String vaccineId;
    private String manufacturer;
    private String batchNumber;
    private int validityMonths;

    public Vaccine(String itemId, String name, int stockQuantity, String vaccineId, String manufacturer, String batchNumber, int validityMonths) {
        super(itemId, name, stockQuantity);
        this.vaccineId = vaccineId;
        this.manufacturer = manufacturer;
        this.batchNumber = batchNumber;
        this.validityMonths = validityMonths;
    }

    public String getVaccineId() { return vaccineId; }
    public String getManufacturer() { return manufacturer; }
    public String getBatchNumber() { return batchNumber; }
    public int getValidityMonths() { return validityMonths; }

    public void setVaccineId(String vaccineId) { this.vaccineId = vaccineId; }
    public void setManufacturer(String manufacturer) { this.manufacturer = manufacturer; }
    public void setBatchNumber(String batchNumber) { this.batchNumber = batchNumber; }
    public void setValidityMonths(int validityMonths) { this.validityMonths = validityMonths; }

    @Override
    public void displayDetails() {
        System.out.println("[Vaccine] " + getName() + " | Batch: " + batchNumber);
    }
}

//Parent Class
abstract class InventoryItem {
    private String itemId;
    private String name;
    private int stockQuantity;

    public InventoryItem(String itemId, String name, int stockQuantity) {
        this.itemId = itemId;
        this.name = name;
        this.stockQuantity = stockQuantity;
    }

    public String getItemId() { return itemId; }
    public String getName() { return name; }
    public int getStockQuantity() { return stockQuantity; }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
    public abstract void displayDetails();
}

//My Dummy files
class Pet {
    private String name;
    public Pet(String name) { this.name = name; }
    public String getName() { return name; }
}
class Veterinarian {
    private String name;
    public Veterinarian(String name) { this.name = name; }
    public String getName() { return name; }
}
