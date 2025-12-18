public class Main {
    public void main(String[] args){
        System.out.println("=== Veterinary Clinic Management System ===");
        System.out.println();

        Pet pet1=new Pet(1,"Rocky",2,"Dog","John Smith");
        Pet pet2=new Pet(2,"Leo",4,"Cat","Michael Johnson");
        Pet pet3=new Pet();

        Owner owner1=new Owner(1,"John Smith","+7-777-777-77-77",1);
        Owner owner2=new Owner(2,"Michael Johnson","+7-700-000-00-00",1);
        Owner owner3=new Owner();

        Veterinarian vet1=new Veterinarian(1,"Daniel Anderson","Dog",7);
        Veterinarian vet2=new Veterinarian(2,"David Wilson","Cat",2);
        Veterinarian vet3=new Veterinarian();

        System.out.println("Pets:");
        System.out.println(pet1);
        System.out.println(pet2);
        System.out.println(pet3);

        System.out.println("\nOwners:");
        System.out.println(owner1);
        System.out.println(owner2);
        System.out.println(owner3);

        System.out.println("\nVeterinarians:");
        System.out.println(vet1);
        System.out.println(vet2);
        System.out.println(vet3);


        System.out.println("\n--- TESTING GETTERS ---");
        System.out.println("Pet1 name: "+ pet1.getName());
        System.out.println("Pet1 ownerName: "+ pet1.getOwnerName());
        System.out.println("Owner1 name: "+ owner1.getName());
        System.out.println("Veterinarian1 name: "+ vet1.getName());

        System.out.println("\n--- TESTING SETTERS ---");
        System.out.println("Updating pet3...");
        pet3.setPetId(3);
        pet3.setName("Coco");
        pet3.setAge(4);
        pet3.setSpecies("Cat");
        pet3.setOwnerName("John Smith");
        System.out.println("Updated: " + pet3);


        System.out.println("\n--- TESTING PET METHODS ---");
        System.out.println(pet1.getName()+" is young: "+pet1.isYoung());
        System.out.println(pet1.getName()+" life stage: "+pet1.getLifeStage());

        System.out.println("\n--- TESTING OWNER METHODS ---");
        System.out.println(owner1.getName()+ " is frequent client: "+owner1.isFrequentClient());
        System.out.println(owner1.getName()+" add pet: "+owner1.addPet());

        System.out.println("\n--- TESTING VETERINARIAN METHODS ---");
        System.out.println(vet1.getName()+" can treat: "+vet1.canTreat(pet1));
        System.out.println(vet1.getName()+" is experienced: "+vet1.isExperienced());

    }
}
