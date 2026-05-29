package petcare1;
import java.util.ArrayList;
import java.util.List;

public class PetOwner {
    private String id;
    private String name;
    private String phoneNo;
    private String address;
    private String email;
    private String paymentMethod;
    private List<Animals> petList; 

    public PetOwner (String id, String name, String phoneNo, String address, String email, String paymentMethod) {
        this.id = id;
        this.name = name;
        this.phoneNo = phoneNo;
        this.address = address;
        this.email = email;
        this.paymentMethod = paymentMethod; 
        this.petList = new ArrayList<>();
    }

    public void addPet(Animals pet) {
        petList.add(pet);
    }

    // --- NEW GETTERS ADDED FOR THE GUI ---
    public String getId() { return id; }
    public String getName() { return name; }
    public List<Animals> getPetList() { return petList; }

    public String getOwnerDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- Pet Owner Details ---\n");
        sb.append("Client Name: ").append(name).append(" (ID: ").append(id).append(")\n");
        sb.append("Contact: ").append(phoneNo).append(" | Email: ").append(email).append("\n");
        sb.append("Address: ").append(address).append("\n");
        sb.append("Payment Method: ").append(paymentMethod).append("\n");
        sb.append("Pets Registered: \n");
        
        if (petList.isEmpty()) {
            sb.append("  [No pets registered yet]\n");
        } else {
            for (Animals pet : petList) {
                sb.append("  -> ").append(pet.getAnimalInfo()).append("\n");
            }
        }
        sb.append("----------------------------\n");
        return sb.toString();
    }
}