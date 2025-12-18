public class Pet {
    private int petId;
    private String name;
    private int age;
    private String species;
    private String ownerName;

    public Pet(int petId, String name, int age, String species, String ownerName){
        this.petId = petId;
        this.name=name;
        this.age=age;
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
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
        this.species = species;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
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
