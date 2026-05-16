package ANIMAL.MAMMAL;

public class DOG extends MAMMALS{
    
    //constructor
    public DOG(String petId, String petName, int petAge, double petWeight){
        super(petId,petName,petAge,petWeight);
    }

    //Defines species for Dog
    public String getSpecies(){
        return "Dog";
    }
}
