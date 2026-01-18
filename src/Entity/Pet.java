package Entity;

public class Pet {
    private int petId;
    private String name;
    private int age;
    private String species;
    private String ownerName;

    public Pet(int petId, String name, int age, String species, String ownerName){
        this.petId = petId;
        setName(name);
        setAge(age);
        this.species=species;
        this.ownerName=ownerName;
    }
    public Pet(){
        this.petId=0;
        this.name="Unknown";
        this.age=0;
        this.species="Unknown";
        this.ownerName="Unknown";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name!=null && !name.trim().isEmpty()){
            this.name = name;
        }
        else{
            System.out.println("Warning: Name cannot be empty!");
        }
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if(age >= 0){
            this.age = age;
        }
        else {
            System.out.println("Warning: age can't be negative! Setting to 0.");
            this.age=0;
        }
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        if(species!=null && !species.trim().isEmpty()){
            this.species = species;
        }
        else{
            System.out.println("Warning: Species cannot be empty!");
        }
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        if(ownerName!=null && !ownerName.trim().isEmpty()){
            this.ownerName = ownerName;
        }
        else{
            System.out.println("Warning: Owner name cannot be empty!");
        }
    }

    public boolean isYoung(){
        return age <= 3;
    }
    
    public String getLifeStage(){
        if(age<=3){
            return "Young";
        } else if (age<=7){
            return "Adult";
        }else{
            return "Senior";
        }
    }

    @Override
    public String toString() {
        return "Pet{" +
                "petId=" + petId +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", species='" + species + '\'' +
                ", ownerName='" + ownerName + '\'' +
                '}';
    }
}
