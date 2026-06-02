package petcare1;

// Developers 5 and 6 MUST implement this!
public interface Billable {
    String getItemName();        // e.g., "Rabies Vaccine" or "Spay Surgery"
    double getPrice();           // e.g., 45.00
    String getAssignedDoctor();  // e.g., "Dr. Alice"
}
