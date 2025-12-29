public class Veterinarian {
    private int vetId;
    private String name;
    private String specialization;
    private int experience;

    public Veterinarian(int vetId, String name,String specialization, int experience){
        this.vetId=vetId;
        this.name=name;
        this.specialization=specialization;
        setExperience(experience);
    }
    public Veterinarian(){
        this.vetId=0;
        this.name="Unknown";
        this.specialization="Unknown";
        this.experience=0;
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

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        if(experience >= 0){
            this.experience = experience;
        }
        else {
            System.out.println("Warning: experience can't be negative! Setting to 0.");
            this.experience=0;
        }
    }

    public int getVetId() {
        return vetId;
    }

    public void setVetId(int vetId) {
        this.vetId = vetId;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public boolean canTreat(Pet pet){
        return specialization.equals(pet.getSpecies());
    }

    public boolean isExperienced(){
        return experience>=3;
    }

    @Override
    public String toString() {
        return "Veterinarian{" +
                "vetId=" + vetId +
                ", name='" + name + '\'' +
                ", specialization='" + specialization + '\'' +
                ", experience=" + experience +
                '}';
    }
}
