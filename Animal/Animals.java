package Animal;
//Abstract parent class
//All animal will inherit from this class

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
}
