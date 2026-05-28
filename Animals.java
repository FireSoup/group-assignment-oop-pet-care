public abstract class Animals {
    protected String petId;     //animal info variable
    protected String petName;
    protected int petAge;
    protected double petWeight;

    //Constructor
    public Animals(String petId, String petName, int petAge, double petWeight){
        this.petId = petId;
        this.petName = petName;
        this.petAge = petAge;
        this.petWeight = petWeight;
    }

    //Abstract method
    public abstract String getSpecies();

    //display animal information
    public void displayInfo(){
        System.out.println("Pet ID: "+ petId);
        System.out.println("Pet Name: "+petName);
        System.out.println("Pet Age: "+petAge);
        System.out.println("Pet weight: "+petWeight+"kg");
        System.out.println("Pet Type: "+getSpecies());
    }

    //subclass
    abstract class Mammals extends Animals{
        public Mammals(String petId, String petName, int petAge, double petWeight){
            super(petId, petName, petAge, petWeight);
        }
    }

    //dog class
    class Dog extends Mammals{
            public Dog(String petId, String petName, int petAge, double petWeight){
        super(petId,petName,petAge,petWeight);
    }

    //Defines species for Dog and Cat
    public String getSpecies(){
        return "Dog";
    }
    }
        class Cat extends Mammals{
            public Cat(String petId, String petName, int petAge, double petWeight){
        super(petId,petName,petAge,petWeight);
    }

    //Defines species for Cat
    public String getSpecies(){
        return "Cat";
    }
    }
}
