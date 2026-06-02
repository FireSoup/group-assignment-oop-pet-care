package petcare1;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ClinicGUI extends JFrame {

    // --- SYSTEM DATABASES ---
    private List<PetOwner> globalClientList = new ArrayList<>();
    private List<Animals> globalPetList = new ArrayList<>();
    private List<PetOwner> petToOwnerMap = new ArrayList<>(); 
    private List<MedicalRecord> globalMedicalRecords = new ArrayList<>();
    
    private List<Veterinarian> allDoctors = StaffDirectory.loadAvailableDoctors();
    private List<Vaccine> allVaccines = VaccineDirectory.loadAvailableVaccines();
    private List<Medication> allMedications = PharmacyDirectory.loadAvailableMedications();
    
    private int clientCounter = 100, petCounter = 100, invoiceCounter = 1000;

    // --- GUI COMPONENTS ---
    private JTextField clientNameField, clientPhoneField, clientAddressField, clientEmailField;
    private JComboBox<String> paymentCombo, speciesCombo, clientSelectionCombo;
    private JTextField petNameField, petAgeField, petWeightField;
    private JTextArea databaseOutputArea;
    
    private JComboBox<String> billingPetCombo, editPetCombo, visitTypeCombo, docCombo, treatmentItemCombo;
    private JTextField symptomsField;
    private JTextArea receiptArea, historyArea;

    public ClinicGUI() {
        setTitle("Vet Clinic Management System (Stable Build)");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTabbedPane tabbedPane = new JTabbedPane();

        // ==========================================
        // TAB 1: CLIENT REGISTRATION
        // ==========================================
        JPanel clientPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        clientPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        clientPanel.add(new JLabel("Full Name:")); clientNameField = new JTextField(); clientPanel.add(clientNameField);
        clientPanel.add(new JLabel("Phone Number:")); clientPhoneField = new JTextField(); clientPanel.add(clientPhoneField);
        clientPanel.add(new JLabel("Email Address:")); clientEmailField = new JTextField(); clientPanel.add(clientEmailField);
        clientPanel.add(new JLabel("Home Address:")); clientAddressField = new JTextField(); clientPanel.add(clientAddressField);
        clientPanel.add(new JLabel("Preferred Payment Method:"));
        paymentCombo = new JComboBox<>(new String[]{"Cash", "Credit Card", "Debit Card", "E-Wallet", "Pet Insurance"});
        clientPanel.add(paymentCombo);
        JButton registerClientBtn = new JButton("Register New Client");
        clientPanel.add(new JLabel("")); clientPanel.add(registerClientBtn);

        // ==========================================
        // TAB 2: PET REGISTRATION & EDITING
        // ==========================================
        JPanel petPanel = new JPanel(new BorderLayout(10, 10));
        petPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JPanel petForm = new JPanel(new GridLayout(6, 2, 10, 10));
        petForm.add(new JLabel("Select Owner:")); clientSelectionCombo = new JComboBox<>(); petForm.add(clientSelectionCombo);
        petForm.add(new JLabel("Pet Name:")); petNameField = new JTextField(); petForm.add(petNameField);
        petForm.add(new JLabel("Pet Age (Years):")); petAgeField = new JTextField(); petForm.add(petAgeField);
        petForm.add(new JLabel("Pet Weight (kg):")); petWeightField = new JTextField(); petForm.add(petWeightField);
        petForm.add(new JLabel("Species:")); speciesCombo = new JComboBox<>(new String[]{"Dog", "Cat", "Other Mammal", "Reptile", "Exotic Pet"}); petForm.add(speciesCombo);
        
        JButton registerPetBtn = new JButton("Register New Pet");
        petForm.add(new JLabel("")); petForm.add(registerPetBtn);
        
        JPanel editPanel = new JPanel(new BorderLayout(10, 10));
        editPanel.setBorder(BorderFactory.createTitledBorder("Edit Existing Pet"));
        editPetCombo = new JComboBox<>(); 
        JButton editPetBtn = new JButton("Edit Selected Pet (Age/Weight)");
        editPanel.add(new JLabel("Select Pet:"), BorderLayout.WEST);
        editPanel.add(editPetCombo, BorderLayout.CENTER);
        editPanel.add(editPetBtn, BorderLayout.SOUTH);
        
        petPanel.add(petForm, BorderLayout.NORTH);
        petPanel.add(editPanel, BorderLayout.SOUTH);

        // ==========================================
        // TAB 3: SYSTEM DATABASE
        // ==========================================
        databaseOutputArea = new JTextArea(); databaseOutputArea.setEditable(false); databaseOutputArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(databaseOutputArea);

        // ==========================================
        // TAB 4: TREATMENT & BILLING
        // ==========================================
        JPanel billingPanel = new JPanel(new BorderLayout(10, 10));
        billingPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        inputPanel.add(new JLabel("Select Patient:")); billingPetCombo = new JComboBox<>(); inputPanel.add(billingPetCombo);
        
        inputPanel.add(new JLabel("Visit Type:")); visitTypeCombo = new JComboBox<>(new String[]{"General Sickness", "Surgery", "Vaccination"}); inputPanel.add(visitTypeCombo);
        inputPanel.add(new JLabel("Assign Attending Doctor:")); docCombo = new JComboBox<>(); inputPanel.add(docCombo);
        inputPanel.add(new JLabel("Select Medicine/Vaccine:")); treatmentItemCombo = new JComboBox<>(); inputPanel.add(treatmentItemCombo);
        
        inputPanel.add(new JLabel("Symptoms/Diagnosis:")); symptomsField = new JTextField(); inputPanel.add(symptomsField);
        
        JPanel btnPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        JButton addAllergyBtn = new JButton("Add Known Allergy");
        JButton treatBtn = new JButton("Process Treatment & Bill");
        btnPanel.add(addAllergyBtn); btnPanel.add(treatBtn);
        inputPanel.add(new JLabel("Actions:")); inputPanel.add(btnPanel);
        
        receiptArea = new JTextArea(); receiptArea.setFont(new Font("Monospaced", Font.PLAIN, 12)); receiptArea.setEditable(false);
        historyArea = new JTextArea(); historyArea.setFont(new Font("Monospaced", Font.PLAIN, 12)); historyArea.setEditable(false);
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(receiptArea), new JScrollPane(historyArea));
        splitPane.setDividerLocation(420);

        billingPanel.add(inputPanel, BorderLayout.NORTH);
        billingPanel.add(splitPane, BorderLayout.CENTER);

        tabbedPane.addTab("1. Register Client", clientPanel);
        tabbedPane.addTab("2. Manage Pets", petPanel);
        tabbedPane.addTab("3. View Database", scrollPane);
        tabbedPane.addTab("4. Treatment & Checkout", billingPanel);
        add(tabbedPane);

        // ==========================================
        // DYNAMIC DROPDOWN LOGIC 
        // ==========================================
        visitTypeCombo.addActionListener(e -> {
            String type = (String) visitTypeCombo.getSelectedItem();
            if (type == null) return;

            docCombo.removeAllItems();
            Specialty reqSpec = type.equals("Vaccination") ? Specialty.VACCINATION : (type.equals("Surgery") ? Specialty.SURGERY : Specialty.GENERAL);
            for (Veterinarian v : allDoctors) {
                if (v.hasSpecialty(reqSpec)) docCombo.addItem(v.getName());
            }

            treatmentItemCombo.removeAllItems();
            if (type.equals("Vaccination")) {
                for (Vaccine v : allVaccines) treatmentItemCombo.addItem(v.getName() + " (RM " + v.getPrice() + ")");
            } else {
                for (Medication m : allMedications) treatmentItemCombo.addItem(m.getName() + " (RM " + m.getPricePerPill() + "/pill)");
            }
        });

        visitTypeCombo.setSelectedIndex(0);

        // ==========================================
        // ACTION LISTENERS 
        // ==========================================
        registerClientBtn.addActionListener(e -> {
            String name = clientNameField.getText();
            if (name.isEmpty()) return;
            String id = "C-" + (++clientCounter);
            PetOwner newOwner = new PetOwner(id, name, clientPhoneField.getText(), clientAddressField.getText(), clientEmailField.getText(), (String) paymentCombo.getSelectedItem());
            globalClientList.add(newOwner);
            clientSelectionCombo.addItem(newOwner.getName() + " [" + newOwner.getId() + "]");
            JOptionPane.showMessageDialog(this, "Client Registered Successfully!");
            refreshDatabaseView();
        });

        registerPetBtn.addActionListener(e -> {
            if (globalClientList.isEmpty()) return;
            try {
                String name = petNameField.getText(); int age = Integer.parseInt(petAgeField.getText()); double weight = Double.parseDouble(petWeightField.getText());
                String species = (String) speciesCombo.getSelectedItem();
                PetOwner selectedOwner = globalClientList.get(clientSelectionCombo.getSelectedIndex());

                Animals newPet;
                switch (species) {
                    case "Dog": newPet = new Dog("P-"+(++petCounter), name, age, weight); break;
                    case "Cat": newPet = new Cat("P-"+(++petCounter), name, age, weight); break;
                    case "Reptile": newPet = new Reptile("P-"+(++petCounter), name, age, weight); break;
                    case "Exotic Pet": newPet = new ExoticPet("P-"+(++petCounter), name, age, weight); break;
                    default: newPet = new OtherMammal("P-"+(++petCounter), name, age, weight); break;
                }

                selectedOwner.addPet(newPet);
                globalPetList.add(newPet);
                petToOwnerMap.add(selectedOwner);
                globalMedicalRecords.add(new MedicalRecord(newPet)); 
                
                String petDropdownEntry = newPet.getAnimalInfo() + " (Owner: " + selectedOwner.getName() + ")";
                
                billingPetCombo.addItem(petDropdownEntry);
                editPetCombo.addItem(petDropdownEntry);
                
                JOptionPane.showMessageDialog(this, name + " registered!");
                refreshDatabaseView();
            } catch (Exception ex) { JOptionPane.showMessageDialog(this, "Check numbers for age/weight!"); }
        });

        // ==========================================
        // THE FIX: UPDATE DROPDOWNS DYNAMICALLY
        // ==========================================
        editPetBtn.addActionListener(e -> {
            if (editPetCombo.getItemCount() == 0) return;
            int idx = editPetCombo.getSelectedIndex(); 
            Animals pet = globalPetList.get(idx);
            try {
                String newAgeStr = JOptionPane.showInputDialog(this, "Update Age for " + pet.petName + ":", pet.petAge);
                String newWeightStr = JOptionPane.showInputDialog(this, "Update Weight (kg) for " + pet.petName + ":", pet.petWeight);
                if (newAgeStr != null && newWeightStr != null) {
                    // Update the object in memory
                    pet.petAge = Integer.parseInt(newAgeStr);
                    pet.petWeight = Double.parseDouble(newWeightStr);
                    
                    // Generate the brand new text string
                    String updatedEntry = pet.getAnimalInfo() + " (Owner: " + petToOwnerMap.get(idx).getName() + ")";
                    
                    // Force the Tab 2 dropdown to update and re-select the edited pet
                    editPetCombo.removeItemAt(idx);
                    editPetCombo.insertItemAt(updatedEntry, idx);
                    editPetCombo.setSelectedIndex(idx);
                    
                    // Force the Tab 4 (Billing) dropdown to update without changing the currently viewed pet
                    int currentBillingIdx = billingPetCombo.getSelectedIndex();
                    billingPetCombo.removeItemAt(idx);
                    billingPetCombo.insertItemAt(updatedEntry, idx);
                    billingPetCombo.setSelectedIndex(currentBillingIdx == -1 ? 0 : currentBillingIdx);
                    
                    JOptionPane.showMessageDialog(this, "Pet Updated Successfully!");
                    refreshDatabaseView();
                }
            } catch (Exception ex) { JOptionPane.showMessageDialog(this, "Invalid numbers!"); }
        });

        addAllergyBtn.addActionListener(e -> {
            if (billingPetCombo.getItemCount() == 0) {
                JOptionPane.showMessageDialog(this, "Please select a patient first!"); return;
            }
            int idx = billingPetCombo.getSelectedIndex();
            MedicalRecord record = globalMedicalRecords.get(idx);
            
            String allergen = JOptionPane.showInputDialog(this, "Enter Allergen (e.g., Penicillin, Peanuts):");
            if(allergen != null && !allergen.trim().isEmpty()) {
                String[] severities = {"Mild", "Moderate", "Severe"};
                String severity = (String) JOptionPane.showInputDialog(this, "Select Severity:", "Severity", JOptionPane.QUESTION_MESSAGE, null, severities, severities[0]);
                String notes = JOptionPane.showInputDialog(this, "Enter Vet Notes:");
                record.addAllergy(new Allergy(allergen, severity, notes));
                JOptionPane.showMessageDialog(this, "Allergy added to medical record!");
                refreshDatabaseView();
            }
        });

        treatBtn.addActionListener(e -> {
            if (billingPetCombo.getItemCount() == 0 || docCombo.getSelectedItem() == null || treatmentItemCombo.getSelectedItem() == null) return;
            
            int idx = billingPetCombo.getSelectedIndex();
            Animals pet = globalPetList.get(idx);
            PetOwner owner = petToOwnerMap.get(idx);
            MedicalRecord record = globalMedicalRecords.get(idx);
            
            Invoice invoice = new Invoice("INV-" + (++invoiceCounter), owner, pet, "REF-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase());
            
            String docName = (String) docCombo.getSelectedItem();
            Veterinarian selectedDoc = allDoctors.stream().filter(d -> d.getName().equals(docName)).findFirst().get();
            
            String visitType = (String) visitTypeCombo.getSelectedItem();
            int itemIdx = treatmentItemCombo.getSelectedIndex();
            
            historyArea.setText(""); 
            
            if (visitType.equals("Vaccination")) {
                Vaccine selectedVaccine = allVaccines.get(itemIdx); 
                
                if (selectedVaccine.getStockQuantity() > 0) {
                    VaccinationRecord vRec = new VaccinationRecord(pet, selectedVaccine, selectedDoc, LocalDate.now());
                    historyArea.append(vRec.getRecordDetails()); 
                    record.addVaccination(vRec);
                    invoice.addItem(vRec); 
                } else {
                    historyArea.append("\n===============================\n");
                    historyArea.append("STATUS: FAILED! Insufficient stock for " + selectedVaccine.getName() + ".\n");
                    historyArea.append("===============================\n");
                }
                
            } else {
                double fee = visitType.equals("Surgery") ? 350.00 : 80.00;
                Consultation cRec = new Consultation(LocalDate.now().toString(), symptomsField.getText(), visitType, fee, selectedDoc, pet);
                
                Medication selectedMed = allMedications.get(itemIdx); 
                int requiredPills = 14; 
                
                Prescription rx = new Prescription(selectedMed, "1 pill", "Twice Daily", "7 days", requiredPills, selectedDoc);
                
                boolean hasEnoughStock = selectedMed.getStockLevel() >= requiredPills;
                
                historyArea.append(cRec.dispenseMedication(rx, requiredPills)); 
                
                record.addConsultation(cRec);
                invoice.addItem(cRec); 
                
                if (hasEnoughStock) {
                    invoice.addItem(rx); 
                }
            }

            receiptArea.setText(invoice.generateReceipt());
            historyArea.append("\n" + record.getMedicalHistorySummary());
            refreshDatabaseView();
        });
    }

    private void refreshDatabaseView() {
        StringBuilder sb = new StringBuilder();
        sb.append("========== VET CLINIC MASTER DATABASE ==========\n\n");
        if (globalClientList.isEmpty()) sb.append("No clients registered yet.\n");
        else {
            for (int i=0; i<globalClientList.size(); i++) {
                sb.append(globalClientList.get(i).getOwnerDetails()).append("\n");
                for(int j=0; j<globalPetList.size(); j++) {
                    if(petToOwnerMap.get(j).equals(globalClientList.get(i))) {
                        sb.append(globalMedicalRecords.get(j).getMedicalHistorySummary()).append("\n");
                    }
                }
            }
        }
        databaseOutputArea.setText(sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ClinicGUI().setVisible(true));
    }
}
