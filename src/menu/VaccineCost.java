package menu;
import Entity.*;

public class VaccineCost implements TreatmentsCost{
    private double cost;
    private String vaccineName;
    private int doseNumber;

    @Override
    public void canculateCost(){
        System.out.println("Cost: " + cost*doseNumber);
    }

    @Override
    public String getCost(){
        return "Vaccine name: " + vaccineName + "\n Cost: " + cost;
    }
}
