package petcare:
public class Prescription 
{
    Medication medication;//UBAT APAA YANG DIBERIKAN
    int dosagePerDay;//PENGAMBILAN UBAT PER HARI
    String frequency;//KELUARAN UBAT (2 KALI SEHARI, 3 KALI SEHARI, DLL)
    int durationDays;//BERAPA LAMA HAIWAN PERLU AMBIL NI UBAT

    public Prescription(Medication medication, int dosagePerDay, String frequency, int durationDays) 
    {
        this.medication = medication;//UBAT APAA YANG DIBERIKAN
        this.dosagePerDay = dosagePerDay;//PENGAMBILAN UBAT PER HARI
        this.frequency = frequency;//KELUARAN UBAT (2 KALI SEHARI, 3 KALI SEHARI, DLL)
        this.durationDays = durationDays;//BERAPA LAMA HAIWAN PERLU AMBIL NI UBAT
    }
}
