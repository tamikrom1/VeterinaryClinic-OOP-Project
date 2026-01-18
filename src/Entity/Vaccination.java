package Entity;

public class Vaccination extends Treatment{
    private String vaccineName;
    private int doseNumber;

    public Vaccination(double cost, int duration, boolean completed, String vaccineName, int doseNumber){
        super("Vaccination", cost, duration, completed);
        setVaccineName(vaccineName);
        setDoseNumber(doseNumber);
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public int getDoseNumber() {
        return doseNumber;
    }

    public void setVaccineName(String vaccineName) {
        if(vaccineName==null || vaccineName.trim().isEmpty()){
            throw new IllegalArgumentException("Vaccine name cannot be empty!");
        }
        this.vaccineName=vaccineName;
    }

    public void setDoseNumber(int doseNumber) {
        if(doseNumber<0){
            throw new IllegalArgumentException("Number of dose cannot be negative!");
        }
        this.doseNumber=doseNumber;
    }


    //Abstract implementation
    @Override
    public void work(){
        System.out.println(treatmentName + " is performing with " + vaccineName +" vaccine "+ "\nNumber of dose : "+ doseNumber);
    }

    @Override
    public String getTreatment(){
        return "Vaccine";
    }




    //Vaccine methods
    @Override
    public void performTreatment() {
        System.out.println("Administering vaccine: " + vaccineName);
        completed = true;
    }

    @Override
    public double calculateCost() {
        return cost * doseNumber;
    }


    public void requiredVaccine(){
        System.out.println("Vaccine name: "+vaccineName+ "\nNumber of dose: "+ doseNumber);
    }

    public boolean needsBooster() {
        return doseNumber < 3;
    }


    @Override
    public String toString() {
        return super.toString() + ", Vaccine name: " + vaccineName + ", Number of dose: " + doseNumber;
    }
}
