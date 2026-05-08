package Animal.Mammal;

public class dog extends mammal{
    
    //constructor
    public dog(String petId, String petName, int petAge, double petWeight){
        super(petId,petName,petAge,petWeight);
    }

    //Defines species for Dog
    public String getSpecies(){
        return "Dog";
    }
}
