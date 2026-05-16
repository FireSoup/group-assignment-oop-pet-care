//The Child Class
package vaccination;

public class Vaccine extends InventoryItem {

    private String vaccineId;
    private String manufacturer;
    private String batchNumber;
    private int validityMonths;

    //Constructor
    public Vaccine(String itemId, String name, int stockQuantity, String vaccineId, String manufacturer, String batchNumber, int validityMonths) {
        super(itemId, name, stockQuantity);
        this.vaccineId = vaccineId;
        this.manufacturer = manufacturer;
        this.batchNumber = batchNumber;
        this.validityMonths = validityMonths;
    }

    //Getter
    public String getVaccineId() {
        return vaccineId;
    }
    public String getManufacturer() {
        return manufacturer;
    }
    public String getBatchNumber() {
        return batchNumber;
    }
    public int getValidityMonths() {
        return validityMonths;
    }

    //Setter
    public void setVaccineId(String vaccineId) {
        this.vaccineId = vaccineId;
    }
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }
    public void setValidityMonths(int validityMonths) {
        this.validityMonths = validityMonths;
    }


    @Override
    public void displayDetails() {
        System.out.println("[Vaccine] " + getName() + " | Batch: " + batchNumber);
    }
}