import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    private static ArrayList<Pet> pets = new ArrayList<>();
    private static ArrayList<Owner> owners = new ArrayList<>();
    private static ArrayList<Veterinarian> veterinarians = new ArrayList<>();
    private static ArrayList<Treatment> allTreatment = new ArrayList<>();

    private static Scanner scanner = new Scanner(System.in);


    public void main(String[] args){


        System.out.println("=== Veterinary Clinic Management System ===");
        System.out.println();
        allTreatment.add(new Surgery(1000,30,false,"General",2));
        allTreatment.add(new Vaccination(2000,40,false,"Rabies", 2));




        /*
        pets.add(new Pet(1,"Rocky",2,"Dog","John Smith"));
        pets.add(new Pet(2,"Leo",4,"Cat","Michael Johnson"));
        pets.add(new Pet());

        owners.add(new Owner(1,"John Smith","+7-777-777-77-77",1));
        owners.add(new Owner(2,"Michael Johnson","+7-700-000-00-00",1));
        owners.add(new Owner());

        veterinarians.add(new Veterinarian(1,"Daniel Anderson","Dog",7));
        veterinarians.add(new Veterinarian(2,"David Wilson","Cat",2));
        veterinarians.add(new Veterinarian());
        */
        //Menu loop

        boolean running = true;
        while(running){
            displayMenu();
            int choice=scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1:
                    addSurgery();
                    break;
                case 2:
                    viewSurgery();
                    break;
                case 3:
                    addVaccination();
                    break;
                case 4:
                    viewVaccination();
                    break;
                case 5:
                    demonstratePolymorphism();
                    break;
                case 6:
                    viewAllTreatment();
                    break;
                case 0:
                    System.out.println("\n Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("\n Invalid choice!");
            }
            if (running){
                System.out.println("\n Press Enter to continue...");
                scanner.nextLine();
            }
        }
        scanner.close();


    }

    private static void addSurgery(){
        System.out.println("\n---ADD SURGERY---");

        System.out.println("Enter surgery cost: ");
        double cost=scanner.nextInt();
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

        Treatment treatment = new Surgery(cost,duration,completed,anesthesiaType,riskLevel);
        allTreatment.add(treatment);

        System.out.println("\n Surgery added successfully!");
    }

    private static void addVaccination(){
        System.out.println("\n---ADD VACCINATION---");

        System.out.println("Enter vaccination cost: ");
        double cost=scanner.nextInt();
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

        Treatment treatment = new Vaccination(cost,duration,completed,vaccineName,doseNumber);
        allTreatment.add(treatment);

        System.out.println("\n Vaccination added successfully!");
    }

    private static void viewSurgery(){
        System.out.println("================================");
        System.out.println("          Surgery only");
        System.out.println("================================");

        int surgeryCount = 0;
        for(Treatment s: allTreatment){
            if(s instanceof Surgery){
                Surgery surgery = (Surgery) s;
                surgeryCount++;

                System.out.println(surgeryCount+"."+ surgery.getTreatmentName());
                System.out.println("   Surgery cost: "+ surgery.getCost());
                System.out.println("   Surgery duration: "+ surgery.getDuration());
                System.out.println("   Anesthesia type: "+ surgery.getAnesthesiaType());
                System.out.println("   Risk level: "+ surgery.getRiskLevel());

                surgery.howRisky();

                System.out.println();
            }
        }
        if(surgeryCount==0){
            System.out.println("No surgery found.");
        }
    }

    private static void viewVaccination(){
        System.out.println("================================");
        System.out.println("          Vaccination only");
        System.out.println("================================");

        int vaccinationCount = 0;
        for(Treatment s: allTreatment){
            if(s instanceof Vaccination){
                Vaccination vaccination = (Vaccination) s;
                vaccinationCount++;

                System.out.println(vaccinationCount+"."+ vaccination.getTreatmentName());
                System.out.println("   Surgery cost: "+ vaccination.getCost());
                System.out.println("   Surgery duration: "+ vaccination.getDuration());
                System.out.println("   Vaccine name: "+ vaccination.getVaccineName());
                System.out.println("   Number of dose: "+ vaccination.getDoseNumber());

                if (vaccination.needsBooster()){
                    System.out.println("Vaccine need a booster");
                }

                System.out.println();
            }
        }
        if(vaccinationCount==0){
            System.out.println("No vaccination found.");
        }
    }

    private static void viewAllTreatment(){
        System.out.println("\n==============================");
        System.out.println("     ALL TREATMENT(POLYMORPHIC LIST)");
        System.out.println("==============================");

        if(allTreatment.isEmpty()){
            System.out.println("No treatment found.");
            return;
        }

        System.out.println("Total treatment: " + allTreatment.size());
        System.out.println();

        for (int i=0;i<allTreatment.size();i++){
            Treatment s= allTreatment.get(i);

            System.out.println((i+1) + "." + s);
            if(s instanceof Surgery){
                Surgery surgery = (Surgery) s;
                surgery.howRisky();
            } else if (s instanceof Vaccination) {
                Vaccination vaccination =(Vaccination) s;
                if(vaccination.needsBooster()){
                    System.out.println("Vaccine need a booster");
                }
            }

            System.out.println();

        }
    }

    private static void demonstratePolymorphism(){
        System.out.println("\n========================================");
        System.out.println("   POLYMORPHISM DEMONSTRATION");
        System.out.println("========================================");
        System.out.println();

        for(Treatment s: allTreatment){
            s.performTreatment();
        }

        System.out.println();
        System.out.println("   This is POLYMORPHISM in action!");
    }



    private static void displayMenu(){
        /*
        System.out.println("====== VET CLINIC SYSTEM ======");
        System.out.println("1. Add Pet");
        System.out.println("2. View All Pets");
        System.out.println("3. Add Owner");
        System.out.println("4. View All Owners");
        System.out.println("5. Add Veterinarian");
        System.out.println("6. View All Veterinarian");
        System.out.println("0. Exit");
        System.out.println("========================================");
        System.out.println("Enter choice: ");
        */

        System.out.println("===== VET CLINIC SYSTEM =====");
        System.out.println("1. Add Surgery");
        System.out.println("2. View Surgery");
        System.out.println("3. Add Vaccination");
        System.out.println("4. View Vaccination");
        System.out.println("5. Demonstrate Polymorphism");
        System.out.println("6. View All Treatment");
        System.out.println("0. Exit");
        System.out.println("==============================");
        System.out.println("Enter choice: ");
    }

    private static void addPet(){
        System.out.println("\n--- ADD PET ---");

        System.out.print("Enter pet ID: ");
        int petId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter pet name: ");
        String name = scanner.nextLine();

        System.out.print("Enter pet age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter pet species: ");
        String species = scanner.nextLine();

        System.out.print("Enter pet Owner name: ");
        String ownerName = scanner.nextLine();

        Pet pet = new Pet(petId,name,age,species,ownerName);
        pets.add(pet);

        System.out.println("\nPet added successfully!");

    }

    private static void viewallPets(){
        System.out.println("========================");
        System.out.println("    ALL PETS");
        System.out.println("========================");

        if (pets.isEmpty()){
            System.out.println("No pets found");
            return;
        }

        System.out.println("Total pets: " + pets.size());
        System.out.println();

        for (int i=0;i< pets.size();i++){
            Pet pet = pets.get(i);
            System.out.println((i+1) + ". Pet ID: " + pet.getPetId() + "\nName: " + pet.getName());
            System.out.println("Age: " + pet.getAge());
            System.out.println("Species: " + pet.getSpecies());
            System.out.println("Owner Name: " + pet.getOwnerName());

            System.out.println("Life stage: " + pet.getLifeStage());
            System.out.println();
        }
    }

    private static void addOwner(){
        System.out.println("\n--- ADD OWNER ---");

        System.out.print("Enter owner ID: ");
        int ownerId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter owner name: ");
        String name = scanner.nextLine();

        System.out.print("Enter owner phone: ");
        String phone = scanner.nextLine();

        System.out.print("Enter owner number of Pets: ");
        int numberOfPets = scanner.nextInt();
        scanner.nextLine();

        Owner owner = new Owner(ownerId,name,phone,numberOfPets);
        owners.add(owner);

        System.out.println("\nOwner added successfully!");

    }

    private static void viewallOwners(){
        System.out.println("========================");
        System.out.println("    ALL OWNERS");
        System.out.println("========================");

        if (owners.isEmpty()){
            System.out.println("No owners found");
            return;
        }

        System.out.println("Total owners: " + owners.size());
        System.out.println();

        for (int i = 0;i< owners.size();i++){
            Owner owner = owners.get(i);
            System.out.println((i+1) + ". Owner ID: " + owner.getOwnerId() + "\nName: " + owner.getName());
            System.out.println("Phone: " + owner.getPhone());
            System.out.println("Number of Pets: " + owner.getNumberOfPets());

            if (owner.isFrequentClient()){
                System.out.println("Frequent client");
            }
            System.out.println();
        }
    }

    private static void addVeterinarian(){
        System.out.println("\n--- ADD VETERINARIAN ---");

        System.out.print("Enter veterinarian ID: ");
        int vetId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter veterinarian name: ");
        String name = scanner.nextLine();

        System.out.print("Enter veterinarian specialization: ");
        String specialization = scanner.nextLine();

        System.out.print("Enter veterinarian experience: ");
        int experience = scanner.nextInt();
        scanner.nextLine();

        Veterinarian vet = new Veterinarian(vetId,name,specialization,experience);
        veterinarians.add(vet);

        System.out.println("\nVeterinarian added successfully!");

    }

    private static void viewallVeterinarian(){
        System.out.println("========================");
        System.out.println("    ALL VETERINARIANS");
        System.out.println("========================");

        if (veterinarians.isEmpty()){
            System.out.println("No veterinarians found");
            return;
        }

        System.out.println("Total veterinarians: " + veterinarians.size());
        System.out.println();

        for (int i = 0;i< veterinarians.size();i++){
            Veterinarian veterinarian = veterinarians.get(i);
            System.out.println((i+1) + ". Veterinarian ID:" + veterinarian.getVetId() + "\nName: " + veterinarian.getName());
            System.out.println("Specialization: " + veterinarian.getSpecialization());
            System.out.println("Experience: " + veterinarian.getExperience());

            if (veterinarian.isExperienced()){
                System.out.println("Experienced veterinarian");
            }
            System.out.println();
        }
    }

}
