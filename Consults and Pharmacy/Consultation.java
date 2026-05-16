package petcare:
import  java.uti.ArrayList;
public class Consultation 
{
    String animalName;//NAMA HAIWAN YANG DITANGANI(KUCING, ANJING, DLL)
    String vetName;//NAMA DOKTER HEWAN YANG MENANGANI
    String symptoms;//APA SAKIT YANG DIALAMI HAIWAN
    String diagnosis;// DOKTOR KENAL PASTI PENYAKIT HAIWAN
    ArrayList <Prescription> prescriptions = new ArrayList<>();

    public Consultation(String animalName, String vetName) 
    {
        this.animalName = animalName;
        this.vetName = vetName;
    }
    
}
