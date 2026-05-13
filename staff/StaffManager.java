package staff;

import java.util.ArrayList;
import java.util.Scanner;

// This is the main controller for the Staff module
// It handles all CRUD operations: Add, View, Edit, Delete
// It also handles Search and Payroll
// This is the file that shows the menu to the user
public class StaffManager {

    // staffList stores all staff objects added during the session
    private ArrayList<Staff> staffList = new ArrayList<>();

    // scanner reads input from the keyboard
    private Scanner scanner = new Scanner(System.in);

    // Helper method - checks if a staff ID already exists before adding
    // Returns true if duplicate found, false if ID is new
    private boolean isDuplicateID(String id) {
        for (Staff s : staffList) {
            if (s.getStaffID().equals(id)) return true;
        }
        return false;
    }

    // ADD - asks user for info and creates a new staff object
    // User picks the type first (Vet, Tech, Receptionist)
    // Then fills in shared info, then type-specific info
    public void addStaff() {
        System.out.println("\n=== ADD STAFF ===");
        System.out.println("1. Veterinarian");
        System.out.println("2. Vet Technician");
        System.out.println("3. Receptionist");
        System.out.print("Choose type: ");

        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            // catches the error if user types letters instead of a number
            System.out.println("Invalid input. Please enter a number.");
            return;
        }

        if (choice < 1 || choice > 3) {
            System.out.println("Invalid staff type.");
            return;
        }

        System.out.print("Staff ID: ");
        String staffID = scanner.nextLine().trim();
        if (staffID.isEmpty()) { System.out.println("Staff ID cannot be empty."); return; }
        if (isDuplicateID(staffID)) { System.out.println("Staff ID already exists."); return; }

        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) { System.out.println("Name cannot be empty."); return; }

        System.out.print("Shift Times: ");
        String shiftTimes = scanner.nextLine().trim();
        if (shiftTimes.isEmpty()) { System.out.println("Shift Times cannot be empty."); return; }

        double salary;
        try {
            System.out.print("Salary: ");
            salary = Double.parseDouble(scanner.nextLine().trim());
            if (salary < 0) { System.out.println("Salary cannot be negative."); return; }
        } catch (NumberFormatException e) {
            // catches the error if user types letters instead of a number
            System.out.println("Invalid salary.");
            return;
        }

        // Create the correct staff type based on user's choice
        if (choice == 1) {
            System.out.print("License Number: ");
            String license = scanner.nextLine().trim();
            System.out.print("Specialty: ");
            String specialty = scanner.nextLine().trim();
            staffList.add(new Veterinarian(staffID, name, shiftTimes, salary, license, specialty));
        } else if (choice == 2) {
            System.out.print("Room ID: ");
            String roomId = scanner.nextLine().trim();
            System.out.print("Assigned Duty: ");
            String duty = scanner.nextLine().trim();
            staffList.add(new VetTechnician(staffID, name, shiftTimes, salary, roomId, duty));
        } else {
            System.out.print("Desk Number: ");
            String desk = scanner.nextLine().trim();
            staffList.add(new Receptionist(staffID, name, shiftTimes, salary, desk));
        }
        System.out.println("Staff added successfully!");
    }

    // VIEW - loops through staffList and calls displayDetails() on each staff
    // displayDetails() behaves differently per type - this is polymorphism
    public void viewStaff() {
        System.out.println("\n=== STAFF LIST ===");
        if (staffList.isEmpty()) { System.out.println("No staff registered."); return; }
        for (Staff s : staffList) {
            s.displayDetails(); // each staff type prints its own unique details
        }
    }

    // EDIT - finds a staff by ID, then updates their info
    // Validates all new values before saving anything
    // Also updates type-specific fields (license, room, desk)
    public void editStaff() {
        System.out.println("\n=== EDIT STAFF ===");
        System.out.print("Enter Staff ID to edit: ");
        String id = scanner.nextLine().trim();

        for (Staff s : staffList) {
            if (s.getStaffID().equals(id)) {

                // Read and validate all shared fields first before saving
                System.out.print("New Name: ");
                String newName = scanner.nextLine().trim();
                if (newName.isEmpty()) { System.out.println("Name cannot be empty."); return; }

                System.out.print("New Shift Times: ");
                String newShift = scanner.nextLine().trim();
                if (newShift.isEmpty()) { System.out.println("Shift Times cannot be empty."); return; }

                double newSalary;
                try {
                    System.out.print("New Salary: ");
                    newSalary = Double.parseDouble(scanner.nextLine().trim());
                    if (newSalary < 0) { System.out.println("Salary cannot be negative."); return; }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid salary.");
                    return;
                }

                // All values valid - now save them
                s.setName(newName);
                s.setShiftTimes(newShift);
                s.setSalary(newSalary);

                // Update type-specific fields based on what type of staff they are
                // instanceof checks what type the object actually is at runtime
                if (s instanceof Veterinarian) {
                    Veterinarian v = (Veterinarian) s; // downcast to access vet-only methods
                    System.out.print("New License Number: ");
                    v.setLicenseNumber(scanner.nextLine().trim());
                    System.out.print("New Specialty: ");
                    v.setSpecialty(scanner.nextLine().trim());
                } else if (s instanceof VetTechnician) {
                    VetTechnician t = (VetTechnician) s;
                    System.out.print("New Room ID: ");
                    t.setRoomId(scanner.nextLine().trim());
                    System.out.print("New Assigned Duty: ");
                    t.setAssignedDuty(scanner.nextLine().trim());
                } else if (s instanceof Receptionist) {
                    Receptionist r = (Receptionist) s;
                    System.out.print("New Desk Number: ");
                    r.setDeskNumber(scanner.nextLine().trim());
                }

                System.out.println("Staff updated successfully!");
                return;
            }
        }
        System.out.println("Staff ID not found.");
    }

    // DELETE - finds a staff by ID and removes them from the list
    // Uses index-based loop so removing during iteration doesn't cause errors
    public void deleteStaff() {
        System.out.println("\n=== DELETE STAFF ===");
        System.out.print("Enter Staff ID to delete: ");
        String id = scanner.nextLine().trim();

        for (int i = 0; i < staffList.size(); i++) {
            if (staffList.get(i).getStaffID().equals(id)) {
                staffList.remove(i);
                System.out.println("Staff deleted successfully!");
                return;
            }
        }
        System.out.println("Staff ID not found.");
    }

    // SEARCH - finds staff whose name contains the search word
    // Case-insensitive so "ali" will match "Ali" or "ALI"
    public void searchStaff() {
        System.out.println("\n=== SEARCH STAFF ===");
        System.out.print("Enter name to search: ");
        String query = scanner.nextLine().trim().toLowerCase();
        if (query.isEmpty()) { System.out.println("Search query cannot be empty."); return; }

        boolean found = false;
        for (Staff s : staffList) {
            if (s.getName().toLowerCase().contains(query)) {
                s.displayDetails();
                found = true;
            }
        }
        if (!found) System.out.println("No staff found.");
    }

    // PAYROLL - calculates and displays total monthly pay for all staff
    // Uses calculateMonthlyPay() and calculateBonus() from the Payable interface
    // Each staff type calculates bonus differently - this is the interface in action
    public void viewTotalPayroll() {
        System.out.println("\n=== TOTAL PAYROLL ===");
        if (staffList.isEmpty()) { System.out.println("No staff registered."); return; }

        double total = 0;
        for (Staff s : staffList) {
            double pay = s.calculateMonthlyPay();
            double bonus = s.calculateBonus();
            double sub = pay + bonus;
            System.out.println(s.getName() + " (" + s.getRole() + ")" +
                    " | Pay: RM" + pay + " | Bonus: RM" + bonus + " | Total: RM" + sub);
            total += sub;
        }
        System.out.println("-----------------------------");
        System.out.println("Grand Total Payroll: RM" + total);
    }

    // Integration method for Dexter (Person 5 - Consultation module)
    // and Damian (Person 6 - Vaccination module)
    // They call this to get a specific vet by ID from their own code
    public Veterinarian getVeterinarianByID(String id) {
        for (Staff s : staffList) {
            if (s.getStaffID().equals(id) && s instanceof Veterinarian) {
                return (Veterinarian) s;
            }
        }
        return null; // returns null if no vet found with that ID
    }

    // Integration method - returns the full list of all registered vets
    // Useful if Dexter or Damian need to show a dropdown of available vets
    public ArrayList<Veterinarian> getAllVeterinarians() {
        ArrayList<Veterinarian> vets = new ArrayList<>();
        for (Staff s : staffList) {
            if (s instanceof Veterinarian) vets.add((Veterinarian) s);
        }
        return vets;
    }

    // MENU - this is what runs when the Staff module is opened
    // Loops until user chooses 7 to exit
    public void menu() {
        int choice = 0;
        while (choice != 7) {
            System.out.println("\n=== STAFF MANAGEMENT ===");
            System.out.println("1. Add Staff");
            System.out.println("2. View All Staff");
            System.out.println("3. Edit Staff");
            System.out.println("4. Delete Staff");
            System.out.println("5. Search Staff by Name");
            System.out.println("6. View Total Payroll");
            System.out.println("7. Exit");
            System.out.print("Choose: ");

            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice.");
                continue; // go back to the top of the loop
            }

            if (choice == 1) addStaff();
            else if (choice == 2) viewStaff();
            else if (choice == 3) editStaff();
            else if (choice == 4) deleteStaff();
            else if (choice == 5) searchStaff();
            else if (choice == 6) viewTotalPayroll();
            else if (choice != 7) System.out.println("Invalid choice.");
        }
    }
}