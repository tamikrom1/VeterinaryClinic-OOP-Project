import java.util.Objects;

public class Owner {
    private int ownerId;
    private String name;
    private String phone;
    private int numberOfPets;

    public Owner(int ownerId, String name, String phone, int numberOfPets){
        this.ownerId=ownerId;
        this.name=name;
        this.phone=phone;
        this.numberOfPets=numberOfPets;
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
        this.name = name;
    }

    public int getNumberOfPets() {
        return numberOfPets;
    }

    public void setNumberOfPets(int numberOfPets) {
        this.numberOfPets = numberOfPets;
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
