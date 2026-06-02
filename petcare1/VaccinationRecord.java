package petcare1;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

abstract class InventoryItem {
    private String itemId, name; private int stockQuantity; private double price; 
    public InventoryItem(String itemId, String name, int stockQuantity, double price) {
        this.itemId = itemId; this.name = name; this.stockQuantity = stockQuantity; this.price = price;
    }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }
}

class Vaccine extends InventoryItem {
    private String vaccineId, manufacturer, batchNumber; private int validityMonths;
    public Vaccine(String itemId, String name, int stockQuantity, double price, String vaccineId, String manufacturer, String batchNumber, int validityMonths) {
        super(itemId, name, stockQuantity, price);
        this.vaccineId = vaccineId; this.manufacturer = manufacturer; this.batchNumber = batchNumber; this.validityMonths = validityMonths;
    }
    public int getValidityMonths() { return validityMonths; }
}

// NEW: Centralized Database for Vaccines
class VaccineDirectory {
    public static List<Vaccine> loadAvailableVaccines() {
        List<Vaccine> vaccines = new ArrayList<>();
        vaccines.add(new Vaccine("I-1", "Parvovirus Vaccine", 100, 60.00, "V-1", "Pfizer", "B-099", 12));
        vaccines.add(new Vaccine("I-2", "Rabies Booster", 150, 45.00, "V-2", "Moderna", "B-101", 12));
        vaccines.add(new Vaccine("I-3", "Feline Leukemia", 80, 50.00, "V-3", "Zoetis", "B-205", 24));
        return vaccines;
    }
}

public class VaccinationRecord implements Billable {
    protected Animals patient; 
    protected Vaccine administredVaccine;
    protected Veterinarian attedingVet;
    private LocalDate administrationDate, nextDueDate;

    public VaccinationRecord(Animals patient, Vaccine administredVaccine, Veterinarian attedingVet, LocalDate administrationDate) {
        this.patient = patient; this.administredVaccine = administredVaccine;
        this.attedingVet = attedingVet; this.administrationDate = administrationDate;
        this.nextDueDate = administrationDate.plusMonths(administredVaccine.getValidityMonths());
        administredVaccine.setStockQuantity(administredVaccine.getStockQuantity() - 1);
    }

    // CHANGED: Now returns a String instead of using System.out.println
    public String getRecordDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n===============================\n");
        sb.append("    OFFICIAL VACCINATION RECORD  \n");
        sb.append("=================================\n");
        sb.append("Patient Name: ").append(patient.petName).append("\n");
        sb.append("Doctor      : ").append(attedingVet.getName()).append("\n");
        sb.append("Vaccine     : ").append(administredVaccine.getName()).append("\n");
        sb.append("NEXT DUE    : ").append(nextDueDate).append("\n");
        sb.append("================================\n");
        return sb.toString();
    }

    @Override public String getItemName() { return administredVaccine.getName() + " Vaccine"; }
    @Override public double getPrice() { return administredVaccine.getPrice(); }
    @Override public String getAssignedDoctor() { return attedingVet.getName(); }
}
