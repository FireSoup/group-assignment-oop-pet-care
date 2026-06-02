package petcare1;

public abstract class Animals {
    protected String petId;
    protected String petName;
    protected int petAge;
    protected double petWeight;

    public Animals(String petId, String petName, int petAge, double petWeight){
        this.petId = petId;
        this.petName = petName;
        this.petAge = petAge;
        this.petWeight = petWeight;
    }

    public abstract String getSpecies();

    public String getAnimalInfo(){
        return "Pet ID: " + petId + " | Name: " + petName + 
               " | Age: " + petAge + " | Weight: " + petWeight + "kg | Type: " + getSpecies();
    }
}

// --- SUBCLASSES ---

abstract class Mammals extends Animals {
    public Mammals(String petId, String petName, int petAge, double petWeight){
        super(petId, petName, petAge, petWeight);
    }
}

class Dog extends Mammals {
    public Dog(String petId, String petName, int petAge, double petWeight){
        super(petId,petName,petAge,petWeight);
    }
    @Override
    public String getSpecies(){ return "Dog"; }
}

class Cat extends Mammals {
    public Cat(String petId, String petName, int petAge, double petWeight){
        super(petId,petName,petAge,petWeight);
    }
    @Override
    public String getSpecies(){ return "Cat"; }
}

class OtherMammal extends Mammals {
    public OtherMammal(String petId, String petName, int petAge, double petWeight){
        super(petId, petName, petAge, petWeight);
    }
    @Override
    public String getSpecies(){ return "Other Mammal"; }
}

class Reptile extends Animals {
    public Reptile(String petId, String petName, int petAge, double petWeight){
        super(petId, petName, petAge, petWeight);
    }
    @Override
    public String getSpecies(){ return "Reptile"; }
}

class ExoticPet extends Animals {
    public ExoticPet(String petId, String petName, int petAge, double petWeight){
        super(petId, petName, petAge, petWeight);
    }
    @Override
    public String getSpecies(){ return "Exotic Pet"; }
}
