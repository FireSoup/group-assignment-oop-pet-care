package ANIMAL.MAMMAL;
import ANIMAL.ANIMALS;

//Abstract subclass of animal to group all mammals together
public abstract class MAMMALS extends ANIMALS{
    
    //Constructor passes value to animal class
    public MAMMALS(String petId, String petName, int petAge, double petWeight){
        super(petId, petName, petAge, petWeight);
    }
}
