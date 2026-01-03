public class Vaccination extends Treatment{
    private String vaccineName;
    private int doseNumber;

    public Vaccination(double cost, int duration, boolean completed, String vaccineName, int doseNumber){
        super("Vaccination", cost, duration, completed);
        this.vaccineName = vaccineName;
        this.doseNumber = doseNumber;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public int getDoseNumber() {
        return doseNumber;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public void setDoseNumber(int doseNumber) {
        if(doseNumber>=0)this.doseNumber = doseNumber;
    }



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
