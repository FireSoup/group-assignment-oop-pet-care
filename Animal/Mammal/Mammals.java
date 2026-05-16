package Animal.Mammal;
import Animal.Animals;

//Abstract subclass of animal to group all mammals together
public abstract class Mammals extends Animals{
    
    //Constructor passes value to animal class
    public Mammals(String petId, String petName, int petAge, double petWeight){
        super(petId, petName, petAge, petWeight);
    }
}
