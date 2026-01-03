public class Surgery extends Treatment{
    private String anesthesiaType;
    private int riskLevel;

    public Surgery(double cost, int duration, boolean completed, String anesthesiaType, int riskLevel){
        super("Surgery", cost, duration, completed);
        this.anesthesiaType=anesthesiaType;
        this.riskLevel=riskLevel;
    }

    public int getRiskLevel() {
        return riskLevel;
    }

    public String getAnesthesiaType() {
        return anesthesiaType;
    }

    public void setRiskLevel(int riskLevel) {
        if(riskLevel>=0)this.riskLevel = riskLevel;
    }

    public void setAnesthesiaType(String anesthesiaType) {
        this.anesthesiaType = anesthesiaType;
    }



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
