package Animal.Mammal;

public class Dog extends Mammals{
    
    //constructor
    public Dog(String petId, String petName, int petAge, double petWeight){
        super(petId,petName,petAge,petWeight);
    }

    //Defines species for Dog
    public String getSpecies(){
        return "Dog";
    }
}
