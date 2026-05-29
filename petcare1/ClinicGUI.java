package petcare1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ClinicGUI extends JFrame {

    // THE MASTER DATABASE (Stores all registered clients)
    private List<PetOwner> globalClientList = new ArrayList<>();
    
    // Counters for auto-generating IDs
    private int clientCounter = 100;
    private int petCounter = 100;  

    // Tab 1 Components (Client)
    private JTextField clientNameField, clientPhoneField, clientAddressField, clientEmailField;
    private JComboBox<String> paymentCombo;
    
    // Tab 2 Components (Pet)
    private JTextField petNameField, petAgeField, petWeightField;
    private JComboBox<String> speciesCombo, doctorCombo, clientSelectionCombo;
    
    // Tab 3 Component (Output)
    private JTextArea databaseOutputArea;

    public ClinicGUI() {
        setTitle("Vet Clinic Management System");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Use a Tabbed Pane to separate tasks!
        JTabbedPane tabbedPane = new JTabbedPane();

        // ==========================================
        // TAB 1: CLIENT REGISTRATION
        // ==========================================
        JPanel clientPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        clientPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        clientPanel.add(new JLabel("Full Name:"));
        clientNameField = new JTextField();
        clientPanel.add(clientNameField);

        clientPanel.add(new JLabel("Phone Number:"));
        clientPhoneField = new JTextField();
        clientPanel.add(clientPhoneField);

        clientPanel.add(new JLabel("Email Address:"));
        clientEmailField = new JTextField();
        clientPanel.add(clientEmailField);

        clientPanel.add(new JLabel("Home Address:"));
        clientAddressField = new JTextField();
        clientPanel.add(clientAddressField);

        // Requested Feature: Payment Method Dropdown
        clientPanel.add(new JLabel("Preferred Payment Method:"));
        paymentCombo = new JComboBox<>(new String[]{"Cash", "Credit Card", "Debit Card", "E-Wallet", "Pet Insurance"});
        clientPanel.add(paymentCombo);

        JButton registerClientBtn = new JButton("Register New Client");
        clientPanel.add(new JLabel("")); // spacer
        clientPanel.add(registerClientBtn);


        // ==========================================
        // TAB 2: PET REGISTRATION
        // ==========================================
        JPanel petPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        petPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Links the Pet to the specific Owner!
        petPanel.add(new JLabel("Select Owner:"));
        clientSelectionCombo = new JComboBox<>();
        petPanel.add(clientSelectionCombo);

        petPanel.add(new JLabel("Pet Name:"));
        petNameField = new JTextField();
        petPanel.add(petNameField);

        petPanel.add(new JLabel("Pet Age (Years):"));
        petAgeField = new JTextField();
        petPanel.add(petAgeField);

        petPanel.add(new JLabel("Pet Weight (kg):"));
        petWeightField = new JTextField();
        petPanel.add(petWeightField);

        petPanel.add(new JLabel("Species:"));
        speciesCombo = new JComboBox<>(new String[]{"Dog", "Cat", "Other Mammal", "Reptile", "Exotic Pet"});
        petPanel.add(speciesCombo);

        petPanel.add(new JLabel("Assign to Doctor:"));
        List<Veterinarian> availableDoctors = StaffDirectory.loadAvailableDoctors();
        String[] docNames = new String[availableDoctors.size()];
        for (int i = 0; i < availableDoctors.size(); i++) {
            docNames[i] = availableDoctors.get(i).getName() + " (" + availableDoctors.get(i).getSpecialty() + ")";
        }
        doctorCombo = new JComboBox<>(docNames);
        petPanel.add(doctorCombo);

        JButton registerPetBtn = new JButton("Register Pet");
        petPanel.add(new JLabel("")); 
        petPanel.add(registerPetBtn);


        // ==========================================
        // TAB 3: SYSTEM DATABASE (Output)
        // ==========================================
        databaseOutputArea = new JTextArea();
        databaseOutputArea.setEditable(false);
        databaseOutputArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(databaseOutputArea);


        // Add tabs to the main window
        tabbedPane.addTab("1. Register Client", clientPanel);
        tabbedPane.addTab("2. Register Pet", petPanel);
        tabbedPane.addTab("3. View Database", scrollPane);
        add(tabbedPane);


        // ==========================================
        // ACTION LISTENERS (The Logic)
        // ==========================================

        // When "Register Client" is clicked
        registerClientBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = clientNameField.getText();
                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(ClinicGUI.this, "Name cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String id = "C-" + (++clientCounter);
                String phone = clientPhoneField.getText();
                String email = clientEmailField.getText();
                String address = clientAddressField.getText();
                String payment = (String) paymentCombo.getSelectedItem();

                // Create the owner and add to Master Array
                PetOwner newOwner = new PetOwner(id, name, phone, address, email, payment);
                globalClientList.add(newOwner);

                // Add them to the Dropdown menu in Tab 2!
                clientSelectionCombo.addItem(newOwner.getName() + " [" + newOwner.getId() + "]");

                JOptionPane.showMessageDialog(ClinicGUI.this, "Client Registered Successfully!\nClient ID: " + id);
                refreshDatabaseView();
                
                // Clear fields
                clientNameField.setText("");
                clientPhoneField.setText("");
                clientEmailField.setText("");
                clientAddressField.setText("");
            }
        });

        // When "Register Pet" is clicked
        registerPetBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (globalClientList.isEmpty()) {
                    JOptionPane.showMessageDialog(ClinicGUI.this, "You must register a Client first!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    String name = petNameField.getText();
                    int age = Integer.parseInt(petAgeField.getText());
                    double weight = Double.parseDouble(petWeightField.getText());
                    String species = (String) speciesCombo.getSelectedItem();
                    String petId = "P-" + (++petCounter);

                    // Find which client was selected in the dropdown
                    int selectedClientIndex = clientSelectionCombo.getSelectedIndex();
                    PetOwner selectedOwner = globalClientList.get(selectedClientIndex);

                    // Create the Animal
                    Animals newPet;
                    switch (species) {
                        case "Dog": newPet = new Dog(petId, name, age, weight); break;
                        case "Cat": newPet = new Cat(petId, name, age, weight); break;
                        case "Reptile": newPet = new Reptile(petId, name, age, weight); break;
                        case "Exotic Pet": newPet = new ExoticPet(petId, name, age, weight); break;
                        default: newPet = new OtherMammal(petId, name, age, weight); break;
                    }

                    // TIE THE PET TO THE OWNER!
                    selectedOwner.addPet(newPet);

                    JOptionPane.showMessageDialog(ClinicGUI.this, name + " registered successfully to " + selectedOwner.getName() + "!");
                    refreshDatabaseView();

                    // Clear fields
                    petNameField.setText("");
                    petAgeField.setText("");
                    petWeightField.setText("");

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(ClinicGUI.this, "Please enter valid numbers for Age and Weight!", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // Method to loop through the Master Array and print everyone out
    private void refreshDatabaseView() {
        StringBuilder sb = new StringBuilder();
        sb.append("========== VET CLINIC MASTER DATABASE ==========\n\n");
        
        if (globalClientList.isEmpty()) {
            sb.append("No clients registered yet.\n");
        } else {
            for (PetOwner owner : globalClientList) {
                sb.append(owner.getOwnerDetails()).append("\n");
            }
        }
        databaseOutputArea.setText(sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ClinicGUI().setVisible(true));
    }
}