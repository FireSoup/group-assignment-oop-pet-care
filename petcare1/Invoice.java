package petcare1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// 1. ADDED: Status tracking for the invoice lifecycle
enum InvoiceStatus {
    UNPAID, PAID, CANCELLED
}

public class Invoice {
    private String invoiceId, referenceNumber;
    private LocalDate invoiceDate; 
    private PetOwner client;
    private Animals patient;
    private List<Billable> billableItems = new ArrayList<>();
    private final double TAX_RATE = 0.06; 

    // 2. ADDED: New Business Logic Variables
    private InvoiceStatus status;
    private double discountPercentage = 0.0;
    private double amountPaid = 0.0;

    public Invoice(String invoiceId, PetOwner client, Animals patient, String referenceNumber) {
        this.invoiceId = invoiceId; 
        this.client = client; 
        this.patient = patient;
        this.referenceNumber = referenceNumber; 
        this.invoiceDate = LocalDate.now(); 
        this.status = InvoiceStatus.UNPAID; // Invoices start as unpaid
    }

    public void addItem(Billable item) { billableItems.add(item); }

    // --- NEW BUSINESS LOGIC METHODS ---

    // Allows the clinic to apply a promotional discount
    public void applyDiscount(double percentage) {
        if (percentage > 0 && percentage <= 100) {
            this.discountPercentage = percentage;
        }
    }

    // Modularized math functions make the code professional and testable
    public double calculateSubtotal() {
        double subtotal = 0.0;
        for (Billable item : billableItems) subtotal += item.getPrice();
        return subtotal;
    }

    public double calculateDiscountAmount() {
        return calculateSubtotal() * (discountPercentage / 100.0);
    }

    public double calculateTax() {
        // Tax is calculated AFTER the discount is applied
        return (calculateSubtotal() - calculateDiscountAmount()) * TAX_RATE;
    }

    public double calculateTotal() {
        return (calculateSubtotal() - calculateDiscountAmount()) + calculateTax();
    }

    // Validates if the customer gave enough money
    public boolean processPayment(double tenderedAmount) {
        if (tenderedAmount >= calculateTotal()) {
            this.amountPaid = tenderedAmount;
            this.status = InvoiceStatus.PAID;
            return true; // Payment successful
        }
        return false; // Insufficient funds
    }

    // --- RECEIPT GENERATION ---

    public String generateReceipt() {
        double subtotal = calculateSubtotal();
        double discountAmt = calculateDiscountAmount();
        double tax = calculateTax();
        double total = calculateTotal();

        StringBuilder receipt = new StringBuilder();
        receipt.append("======================================================\n");
        receipt.append("               PAWS & CLAWS VET CLINIC                \n");
        receipt.append("======================================================\n");
        receipt.append("Invoice ID : ").append(invoiceId).append(" | Date: ").append(invoiceDate).append("\n");
        
        // Dynamically displays the status stamp!
        receipt.append("STATUS     : [ ").append(status.name()).append(" ]\n"); 
        
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
        
        // Only show discount line if a discount was actually applied
        if (discountPercentage > 0) {
            receipt.append(String.format("%-41s -%.2f\n", "DISCOUNT (" + discountPercentage + "%):", discountAmt));
        }
        
        receipt.append(String.format("%-41s %.2f\n", "SST (6%):", tax));
        receipt.append("======================================================\n");
        receipt.append(String.format("%-41s %.2f\n", "TOTAL DUE:", total));
        
        // Show payment math if paid
        if (status == InvoiceStatus.PAID) {
            receipt.append(String.format("%-41s %.2f\n", "AMOUNT TENDERED:", amountPaid));
            receipt.append(String.format("%-41s %.2f\n", "CHANGE DUE:", (amountPaid - total)));
        }
        
        receipt.append("======================================================\n");
        return receipt.toString();
    }
}