package menu;

import Entity.*;
import java.util.ArrayList;
import java.util.Scanner;

public class VeterinaryClinicMenu implements Menu {

    private ArrayList<Treatment> allTreatment = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);


    public VeterinaryClinicMenu(){
        this.allTreatment= new ArrayList<>();
        this.scanner=new Scanner(System.in);

        //Test data
        allTreatment.add(new Surgery(1000,30,false,"General",2));
        allTreatment.add(new Vaccination(2000,40,false,"Rabies", 2));
    }

    private void addSurgery(){
        try {
            System.out.println("\n---ADD SURGERY---");
            System.out.println("Enter surgery cost: ");
            double cost = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Enter duration: ");
            int duration = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Enter surgery status: ");
            boolean completed = scanner.nextBoolean();

            System.out.println("Enter anesthesia type: ");
            String anesthesiaType = scanner.nextLine();

            System.out.println("Enter risk level: ");
            int riskLevel = scanner.nextInt();
            scanner.nextLine();

            Treatment treatment = new Surgery(cost, duration, completed, anesthesiaType, riskLevel);
            allTreatment.add(treatment);

            System.out.println("\n Surgery added successfully!");
        }catch (IllegalArgumentException e){
            System.out.println("❌" + e.getMessage());
        }
    }

    private void addVaccination(){
        try {
            System.out.println("\n---ADD VACCINATION---");

            System.out.println("Enter vaccination cost: ");
            double cost = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter duration: ");
            int duration = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Enter vaccination status: ");
            boolean completed = scanner.nextBoolean();

            System.out.println("Enter vaccination name: ");
            String vaccineName = scanner.nextLine();

            System.out.println("Enter number of dose: ");
            int doseNumber = scanner.nextInt();
            scanner.nextLine();

            Treatment treatment = new Vaccination(cost, duration, completed, vaccineName, doseNumber);
            allTreatment.add(treatment);

            System.out.println("\n Vaccination added successfully!");
        }catch (IllegalArgumentException e){
            System.out.println("❌" + e.getMessage());
        }
    }

    private void viewSurgery(){
        try {
            System.out.println("================================");
            System.out.println("          Surgery only");
            System.out.println("================================");
            int surgeryCount = 0;
            for (Treatment s : allTreatment) {
                if (s instanceof Surgery) {
                    Surgery surgery = (Surgery) s;
                    surgeryCount++;
                    System.out.println(surgeryCount + "." + surgery.getTreatmentName());
                    System.out.println("   Surgery cost: " + surgery.getCost());
                    System.out.println("   Surgery duration: " + surgery.getDuration());
                    System.out.println("   Anesthesia type: " + surgery.getAnesthesiaType());
                    System.out.println("   Risk level: " + surgery.getRiskLevel());
                    surgery.howRisky();
                    System.out.println();
                }
            }
            if (surgeryCount == 0) {
                System.out.println("No surgery found.");
            }
        }catch (IllegalArgumentException e){
            System.out.println("❌" + e.getMessage());
        }
    }

    private void viewVaccination(){
        try {
            System.out.println("================================");
            System.out.println("          Vaccination only");
            System.out.println("================================");
            int vaccinationCount = 0;
            for (Treatment s : allTreatment) {
                if (s instanceof Vaccination) {
                    Vaccination vaccination = (Vaccination) s;
                    vaccinationCount++;
                    System.out.println(vaccinationCount + "." + vaccination.getTreatmentName());
                    System.out.println("   Surgery cost: " + vaccination.getCost());
                    System.out.println("   Surgery duration: " + vaccination.getDuration());
                    System.out.println("   Vaccine name: " + vaccination.getVaccineName());
                    System.out.println("   Number of dose: " + vaccination.getDoseNumber());
                    if (vaccination.needsBooster()) {
                        System.out.println("Vaccine need a booster");
                    }
                    System.out.println();
                }
            }
            if (vaccinationCount == 0) {
                System.out.println("No vaccination found.");
            }
        }catch (IllegalArgumentException e){
            System.out.println("❌" + e.getMessage());
        }
    }

    private void viewAllTreatment(){
        try {
            System.out.println("\n==============================");
            System.out.println("     ALL TREATMENT(POLYMORPHIC LIST)");
            System.out.println("==============================");
            if (allTreatment.isEmpty()) {
                System.out.println("No treatment found.");
                return;
            }
            System.out.println("Total treatment: " + allTreatment.size());
            System.out.println();
            for (int i = 0; i < allTreatment.size(); i++) {
                Treatment s = allTreatment.get(i);
                System.out.println((i + 1) + "." + s);
                if (s instanceof Surgery) {
                    Surgery surgery = (Surgery) s;
                    surgery.howRisky();
                } else if (s instanceof Vaccination) {
                    Vaccination vaccination = (Vaccination) s;
                    if (vaccination.needsBooster()) {
                        System.out.println("Vaccine need a booster");
                    }
                }
                System.out.println();
            }
        }catch (IllegalArgumentException e){
            System.out.println("❌" + e.getMessage());
        }
    }

    private void demonstratePolymorphism(){
        System.out.println("\n========================================");
        System.out.println("   POLYMORPHISM DEMONSTRATION");
        System.out.println("========================================");
        System.out.println();
        for(Treatment s: allTreatment){
            s.work();
        }
    }


    @Override
    public void displayMenu(){
        System.out.println("===== VET CLINIC SYSTEM =====");
        System.out.println("1. Add Surgery");
        System.out.println("2. Add Vaccination");
        System.out.println("3. View Surgery");
        System.out.println("4. View Vaccination");
        System.out.println("5. Demonstrate Polymorphism");
        System.out.println("6. View All Treatment");
        System.out.println("0. Exit");
        System.out.println("==============================");
    }

    @Override
    public void run(){
        boolean running = true;
        while(running){
            displayMenu();
            System.out.println("Enter choice: ");
            try{
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice){
                    case 1: addSurgery();break;
                    case 2: addVaccination();break;
                    case 3: viewSurgery();break;
                    case 4: viewVaccination();break;
                    case 5: demonstratePolymorphism();break;
                    case 6: viewAllTreatment();break;
                    case 0: running= false;break;
                    default:
                        System.out.println("Invalid!");
                }
            }catch (Exception e){
                System.out.println("Error: " + e.getMessage());
                scanner.nextLine();
            }
        }
        scanner.close();
    }
}
