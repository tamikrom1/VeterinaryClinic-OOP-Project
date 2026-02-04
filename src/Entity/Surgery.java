package Entity;

public class Surgery extends Treatment{
    private String anesthesiaType;
    private int riskLevel;

    public Surgery(int treatmentId, double cost, int duration, boolean completed, String anesthesiaType, int riskLevel){
        super(treatmentId,"Surgery", cost, duration, completed);
        setAnesthesiaType(anesthesiaType);
        setRiskLevel(riskLevel);
    }

    public int getRiskLevel() {
        return riskLevel;
    }

    public String getAnesthesiaType() {
        return anesthesiaType;
    }

    public void setRiskLevel(int riskLevel) {
        if(riskLevel<0){
            throw new IllegalArgumentException("Risk level cannot be negative!");
        }
        this.riskLevel=riskLevel;
    }

    public void setAnesthesiaType(String anesthesiaType) {
        if(anesthesiaType==null || anesthesiaType.trim().isEmpty()){
            throw new IllegalArgumentException("Anesthesia type cannot be empty");
        }
        this.anesthesiaType=anesthesiaType;
    }


    //Abstract implementation
    @Override
    public void work(){
        System.out.println(treatmentName + " is performing with " + anesthesiaType + " anesthesia "+"\nRisk level: "+ riskLevel);
    }

    @Override
    public String getTreatment(){
        return "Surgery";
    }



    //Surgery methods

    @Override
    public void performTreatment() {
        System.out.println("Performing surgery with " + anesthesiaType + " anesthesia");
        completed = true;
    }

    @Override
    public double calculateCost() {
        return cost + riskLevel * 100;
    }

    public void showAnesthesiaType() {
        System.out.println("Anesthesia: " + anesthesiaType);
    }
    public void howRisky(){
        if(riskLevel>=3){
            System.out.println("Dangerous surgery");
        }else {
            System.out.println("Safe surgery");
        }
    }

    @Override
    public String toString() {
        return super.toString() + ", Anesthesia type: "+ anesthesiaType + ", Risk level: "+riskLevel;
    }
}
