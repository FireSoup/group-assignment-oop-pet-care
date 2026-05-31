package MedicalRecord;

import java.util.ArrayList;
import java.util.List;

public class MedicalRecord {
    private Animals patient; 
    private List<VitalsLog> vitalsHistory;
    private List<Allergy> knownAllergies;
    private List<Object> pastConsultations;

    public MedicalRecord(Animals patient) {
        this.patient = patient;
        this.vitalsHistory = new ArrayList<>();
        this.knownAllergies = new ArrayList<>();
        this.pastConsultations = new ArrayList<>();
    }

    //Data Modifiers
    
    public void addVitalsLog(VitalsLog log) {
        vitalsHistory.add(log);
    }

    public void addAllergy(Allergy allergy) {
        knownAllergies.add(allergy);
    }

    public void addConsultation(Object consultation) {
        pastConsultations.add(consultation);
    }

    
    public Animals getPatient() { 
        return patient; 
    }
    
    public List<VitalsLog> getVitalsHistory() { 
        return vitalsHistory; 
    }
    
    public List<Allergy> getKnownAllergies() { 
        return knownAllergies; 
    }
    
    public List<Object> getPastConsultations() {
        return pastConsultations;
    }
}