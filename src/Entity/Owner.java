package Entity;

public class Owner {
    private int ownerId;
    private String name;
    private String phone;
    private int numberOfPets;

    public Owner(int ownerId, String name, String phone, int numberOfPets){
        this.ownerId=ownerId;
        this.name=name;
        this.phone=phone;
        setNumberOfPets(numberOfPets);
    }
    public Owner(){
        this.ownerId=0;
        this.name="Unknown";
        this.phone="Unknown";
        this.numberOfPets=0;
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

    public int getNumberOfPets() {
        return numberOfPets;
    }

    public void setNumberOfPets(int numberOfPets) {
        if(numberOfPets >= 0){
            this.numberOfPets = numberOfPets;
        }
        else {
            System.out.println("Warning: number of pets can't be negative! Setting to 0.");
            this.numberOfPets=0;
        }
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public boolean addPet(){
        return numberOfPets>=1;
    }

    public boolean isFrequentClient(){
        return numberOfPets >= 2;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "ownerId=" + ownerId +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", numberOfPets=" + numberOfPets +
                '}';
    }
}
