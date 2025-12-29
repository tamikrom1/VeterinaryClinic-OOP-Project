import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    private static ArrayList<Pet> pets = new ArrayList<>();
    private static ArrayList<Owner> owners = new ArrayList<>();
    private static ArrayList<Veterinarian> veterinarians = new ArrayList<>();

    private static Scanner scanner = new Scanner(System.in);


    public void main(String[] args){
        System.out.println("=== Veterinary Clinic Management System ===");
        System.out.println();

        pets.add(new Pet(1,"Rocky",2,"Dog","John Smith"));
        pets.add(new Pet(2,"Leo",4,"Cat","Michael Johnson"));
        pets.add(new Pet());

        owners.add(new Owner(1,"John Smith","+7-777-777-77-77",1));
        owners.add(new Owner(2,"Michael Johnson","+7-700-000-00-00",1));
        owners.add(new Owner());

        veterinarians.add(new Veterinarian(1,"Daniel Anderson","Dog",7));
        veterinarians.add(new Veterinarian(2,"David Wilson","Cat",2));
        veterinarians.add(new Veterinarian());

        //Menu loop

        boolean running = true;
        while(running){
            displayMenu();
            int choice=scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1:
                    addPet();
                    break;
                case 2:
                    viewallPets();
                    break;
                case 3:
                    addOwner();
                    break;
                case 4:
                    viewallOwners();
                    break;
                case 5:
                    addVeterinarian();
                    break;
                case 6:
                    viewallVeterinarian();
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

    private static void displayMenu(){
        System.out.println("\n====== VET CLINIC SYSTEM ======");
        System.out.println("\n1. Add Pet");
        System.out.println("\n2. View All Pets");
        System.out.println("\n3. Add Owner");
        System.out.println("\n4. View All Owners");
        System.out.println("\n5. Add Veterinarian");
        System.out.println("\n6. View All Veterinarian");
        System.out.println("\n0. Exit");
        System.out.println("========================================");
        System.out.println("\nEnter choice: ");
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
