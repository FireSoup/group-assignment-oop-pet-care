package Animal.Mammal;
import Animal.Sound;

public class Dog extends Mammals implements Sound{
    
    //constructor
    public Dog(String petId, String petName, int petAge, double petWeight){
        super(petId,petName,petAge,petWeight);
    }

    //Defines species for Dog
    public String getSpecies(){
        return "Dog";
    }

    public void makeSound(){
        System.out.println("Woof woof!");
    }
}
