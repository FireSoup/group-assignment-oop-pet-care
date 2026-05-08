package Animal.Mammal;
import Animal.animal;

//Abstract subclass of animal to group all mammals together
public abstract class mammal extends animal{
    
    //Constructor passes value to animal class
    public mammal(String petId, String petName, int petAge, double petWeight){
        super(petId, petName, petAge, petWeight);
    }
}
