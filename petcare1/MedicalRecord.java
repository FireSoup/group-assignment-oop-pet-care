package petcare1;
import java.util.ArrayList;
import java.util.List;

class Allergy {
    private String allergen, severity, notes;
    public Allergy(String allergen, String severity, String notes) {
        this.allergen = allergen; this.severity = severity; this.notes = notes;
    }
    public String getAllergen() { return allergen; }
    @Override public String toString() { return allergen + " (Severity: " + severity + ") - " + notes; }
}

public class MedicalRecord {
    private Animals patient; 
    private List<Allergy> knownAllergies;
    private List<Consultation> pastConsultations; // Swapped Object to Consultation
    private List<VaccinationRecord> pastVaccinations; // Added Vaccinations

    public MedicalRecord(Animals patient) {
        this.patient = patient;
        this.knownAllergies = new ArrayList<>();
        this.pastConsultations = new ArrayList<>();
        this.pastVaccinations = new ArrayList<>();
    }

    public void addAllergy(Allergy allergy) { knownAllergies.add(allergy); }
    public void addConsultation(Consultation consultation) { pastConsultations.add(consultation); }
    public void addVaccination(VaccinationRecord vaccination) { pastVaccinations.add(vaccination); }

    public String getMedicalHistorySummary() {
        StringBuilder summary = new StringBuilder();
        summary.append("=========================================\n");
        summary.append("          MASTER MEDICAL RECORD          \n");
        summary.append("=========================================\n");
        summary.append("Patient Name : ").append(patient.getAnimalInfo()).append("\n");
        
        summary.append(">> KNOWN ALLERGIES:\n");
        if (knownAllergies.isEmpty()) summary.append("   None recorded.\n");
        else for (Allergy a : knownAllergies) summary.append("   - ").append(a.toString()).append("\n");
        
        summary.append("\n>> PAST TREATMENTS:\n");
        if (pastConsultations.isEmpty() && pastVaccinations.isEmpty()) summary.append("   No past visits.\n");
        
        for (Consultation c : pastConsultations) summary.append("   - Consultation: ").append(c.getDiagnosis()).append(" (by ").append(c.getAssignedDoctor()).append(")\n");
        for (VaccinationRecord v : pastVaccinations) summary.append("   - ").append(v.getItemName()).append(" (by ").append(v.getAssignedDoctor()).append(")\n");
        
        summary.append("=========================================\n");
        return summary.toString();
    }
}
