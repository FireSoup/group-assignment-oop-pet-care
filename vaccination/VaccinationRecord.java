//The Action Class
package vaccination;

import java.time.LocalDate;

//Composition - It use for variable that has complex objects
// That why Pet or Vaccine were being act as a sub-datatype wannabe.
public class VaccinationRecord {
    protected Pet patient; // This Datatype is a sub-replacement testing for waiting Developer 1
    protected Vaccine administredVaccine;
    protected Veterinarian attedingVet;// This Datatype is a sub-replacement testing for waiting Developer 4

    private LocalDate administrationDate;
    private LocalDate nextDueDate;

    //Constructor
    public VaccinationRecord(Pet patient, Vaccine administredVaccine, Veterinarian attedingVet, LocalDate administrationDate) {
        this.patient = patient;
        this.administredVaccine = administredVaccine;
        this.attedingVet = attedingVet;
        this.administrationDate = administrationDate;

        //Autonomous Logic //Dot operator being use for the validityMonths
        this.nextDueDate = calculateNextDueDate (administrationDate, administredVaccine.getValidityMonths());

        //This system for reduce the vaccine stock
        this.administredVaccine.reduceStock(1);
    }

    private LocalDate calculateNextDueDate(LocalDate dataGiven, int monthsValid) {
        return dataGiven.plusMonths(monthsValid);
    }

    //Getter
    public Pet getPatient() {
        return patient;
    }
    public Vaccine getVaccine() {
        return administredVaccine;
    }
    public Veterinarian getAttedingVet() {
        return attedingVet;
    }
    public LocalDate getAdministrationDate() {
        return administrationDate;
    }
    public LocalDate getNextDueDate() {
        return nextDueDate;
    }

    // This Part will be acted as subtest code for waiting Developer 7
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
