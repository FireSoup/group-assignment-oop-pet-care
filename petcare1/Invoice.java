package petcare1;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Invoice {
    private String invoiceId, referenceNumber;
    private LocalDate invoiceDate; 
    private PetOwner client;
    private Animals patient;
    private List<Billable> billableItems = new ArrayList<>();
    private final double TAX_RATE = 0.06; 

    public Invoice(String invoiceId, PetOwner client, Animals patient, String referenceNumber) {
        this.invoiceId = invoiceId; this.client = client; this.patient = patient;
        this.referenceNumber = referenceNumber; this.invoiceDate = LocalDate.now(); 
    }

    public void addItem(Billable item) { billableItems.add(item); }

    public String generateReceipt() {
        double subtotal = 0.0;
        for (Billable item : billableItems) subtotal += item.getPrice();
        double tax = subtotal * TAX_RATE;
        double total = subtotal + tax;

        StringBuilder receipt = new StringBuilder();
        receipt.append("======================================================\n");
        receipt.append("               PAWS & CLAWS VET CLINIC                \n");
        receipt.append("======================================================\n");
        receipt.append("Invoice ID : ").append(invoiceId).append(" | Date: ").append(invoiceDate).append("\n");
        receipt.append("Payment Ref: ").append(referenceNumber).append(" (").append(client.getPaymentMethod()).append(")\n");
        receipt.append("Client     : ").append(client.getName()).append("\n");
        receipt.append("Patient    : ").append(patient.getAnimalInfo()).append("\n");
        receipt.append("------------------------------------------------------\n");
        receipt.append(String.format("%-25s %-15s %s\n", "ITEM/TREATMENT", "ATTENDING VET", "PRICE (RM)"));
        receipt.append("------------------------------------------------------\n");

        for (Billable item : billableItems) {
            receipt.append(String.format("%-25s %-15s %.2f\n", item.getItemName(), item.getAssignedDoctor(), item.getPrice()));
        }

        receipt.append("------------------------------------------------------\n");
        receipt.append(String.format("%-41s %.2f\n", "SUBTOTAL:", subtotal));
        receipt.append(String.format("%-41s %.2f\n", "SST (6%):", tax));
        receipt.append("======================================================\n");
        receipt.append(String.format("%-41s %.2f\n", "TOTAL DUE:", total));
        receipt.append("======================================================\n");
        return receipt.toString();
    }
}
