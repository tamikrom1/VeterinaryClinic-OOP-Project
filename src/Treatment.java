public class Treatment {
    protected String treatmentName;
    protected double cost;
    protected int duration;
    protected boolean completed;

    public Treatment(String treatmentName, double cost, int duration, boolean completed){
        this.treatmentName=treatmentName;
        this.cost=cost;
        this.duration=duration;
        this.completed=completed;
    }

    public String getTreatmentName() {
        return treatmentName;
    }

    public double getCost() {
        return cost;
    }

    public int getDuration() {
        return duration;
    }

    public boolean getComplete(){
        return completed;
    }

    public void setTreatmentName(String treatmentName) {
        this.treatmentName = treatmentName;
    }

    public void setCost(double cost) {
        if(cost>=0) this.cost = cost;
    }

    public void setDuration(int duration) {
        if (duration>=0)this.duration = duration;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }



    public void performTreatment(){
        System.out.println("Performing treatment: " + treatmentName);
    }

    public double calculateCost() {
        return cost;
    }

    public void showDetails(){
        System.out.println("Treatment name: " + treatmentName + "\nDurations: "+ duration + " minutes");
    }

    @Override
    public String toString() {
        return "Treatment name: "+treatmentName+ ", Cost: "+ cost +", Durations: " + duration + " minutes" + ", Status: "+completed;
    }
}
