package menu;

import Entity.*;
import java.util.List;
import Exception.InvalidInputException;
import java.util.Scanner;
import database.*;

public class VeterinaryClinicMenu implements Menu {
    private Scanner scanner;
    private TreatmentDAO treatmentDAO;

    public VeterinaryClinicMenu() {
        this.scanner = new Scanner(System.in);
        this.treatmentDAO = new TreatmentDAO();

        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║  VETERINARY CLINIC SYSTEM v2.0    ║");
        System.out.println("║  Week 8: Fully Database-Driven     ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("✅ All data is stored in PostgreSQL");
        System.out.println("✅ No in-memory ArrayLists");
        System.out.println("✅ Complete CRUD operations");

    }

    private void addSurgery() {
        try {
            System.out.println("\n---ADD SURGERY---");

            System.out.println("Id:");
            int treatmentId = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Enter surgery cost: ");
            double cost = scanner.nextDouble();
            scanner.nextLine();

            System.out.println("Enter duration: ");
            int duration = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Enter surgery status: ");
            boolean completed = scanner.nextBoolean();
            scanner.nextLine();

            System.out.println("Enter anesthesia type: ");
            String anesthesiaType = scanner.nextLine();

            System.out.println("Enter risk level: ");
            int riskLevel = scanner.nextInt();
            scanner.nextLine();

            Surgery surgery = new Surgery(treatmentId, cost, duration, completed, anesthesiaType, riskLevel);
            treatmentDAO.insertSurgery(surgery);

            System.out.println("\n Surgery added successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("❌" + e.getMessage());
        }catch (java.util.InputMismatchException e) {
            System.out.println("❌ Error: Invalid input type!");
            scanner.nextLine();
        }
    }

    private void addVaccination() {
        try {
            System.out.println("Id:");
            int treatmentId = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Cost: ");
            int cost = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Duration: ");
            int duration = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Completed: ");
            boolean completed = scanner.nextBoolean();
            scanner.nextLine();

            System.out.println("Vaccine name: ");
            String vaccineName = scanner.nextLine();

            System.out.println("Dose number: ");
            int doseNumber = scanner.nextInt();
            scanner.nextLine();

            Vaccination vaccination = new Vaccination(treatmentId, cost, duration, completed, vaccineName, doseNumber);
            treatmentDAO.insertVaccination(vaccination);


        } catch (IllegalArgumentException e) {
            System.out.println();
        }catch (java.util.InputMismatchException e) {
            System.out.println("❌ Error: Invalid input type!");
            scanner.nextLine();
        }
    }

    private void viewSurgery() {
        List<Surgery> surgeries = treatmentDAO.getAllSurgery();

        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║         Surgery ONLY                   ║");
        System.out.println("╚════════════════════════════════════════╝");

        if (surgeries.isEmpty()) {
            System.out.println("📭 No surgeries in database.");
        } else {
            for (int i = 0; i < surgeries.size(); i++) {
                Surgery surgery = surgeries.get(i);
                System.out.println((i + 1) + ". " + surgery.toString());
                System.out.println("   Anesthesia type: " + surgery.getAnesthesiaType());
                System.out.println("   Risk level: " + surgery.getRiskLevel());
                System.out.println();
            }
            System.out.println("Total Surgeries: " + surgeries.size());
        }
    }

    private void viewVaccination() {
        List<Vaccination> vaccinations = treatmentDAO.getAllVaccination();

        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║         Vaccination ONLY               ║");
        System.out.println("╚════════════════════════════════════════╝");

        if(vaccinations.isEmpty()){
            System.out.println("📭 No vaccinations in database.");
        }else {
            for (int i = 0; i < vaccinations.size(); i++){
                Vaccination vaccination = vaccinations.get(i);
                System.out.println((i+1) + ". " + vaccination.toString());
                System.out.println("     Vaccine name: " + vaccination.getVaccineName());
                System.out.println("     Dose number: " + vaccination.getDoseNumber());
                System.out.println();
            }
            System.out.println("Total Vaccinations: " + vaccinations.size());
        }
    }

    private void viewAllTreatment() {
        treatmentDAO.displayAllTreatment();
    }

    private void demonstratePolymorphism() {
         treatmentDAO.demonstratePolymorphism();
    }



    private void updateTreatment() {
        System.out.println("\n┌─ UPDATE Treatment ─────────────────────────┐");
        System.out.print("│ Enter Treatment ID to update: ");

        try {
            int treatmentId = scanner.nextInt();
            scanner.nextLine();

            // First, get existing staff from database
            Treatment existingTreatment = treatmentDAO.getTreatmentById(treatmentId);

            if (existingTreatment == null) {
                System.out.println("❌ No treatment found with ID: " + treatmentId);
                return;
            }

            // Display current info
            System.out.println("│ Current Info:");
            System.out.println("│ " + existingTreatment.toString());
            System.out.println("└────────────────────────────────────────┘");

            // Get new values
            System.out.println("\n┌─ ENTER NEW VALUES ─────────────────────┐");
            System.out.println("│ (Press Enter to keep current value)   │");

            System.out.print("│ New Cost [" + existingTreatment.getCost() + "]: ");
            String costInput = scanner.nextLine();
            double newCost = costInput.trim().isEmpty() ?
                    existingTreatment.getCost() : Double.parseDouble(costInput);

            System.out.print("│ New Duration [" + existingTreatment.getDuration() + "]: ");
            String durInput = scanner.nextLine();
            int newDuration = durInput.trim().isEmpty() ?
                    existingTreatment.getDuration() : Integer.parseInt(durInput);

            System.out.print("│ New Complete status [" + existingTreatment.getComplete() + "]: ");
            String comInput = scanner.nextLine();
            boolean newComplete = comInput.trim().isEmpty() ?
                    existingTreatment.getComplete() : Boolean.parseBoolean(comInput);

            // Update based on type
            if (existingTreatment instanceof Vaccination) {
                Vaccination vaccination = (Vaccination) existingTreatment;
                System.out.print("│ New Vaccine name [" + vaccination.getVaccineName() + "]: ");
                String newVacName = scanner.nextLine();
                if (newVacName.trim().isEmpty()) {
                    newVacName = vaccination.getVaccineName();
                }

                System.out.print("│ New Dose number [" + vaccination.getDoseNumber() + "]: ");
                String doseInput = scanner.nextLine();
                int newDosNum = doseInput.trim().isEmpty() ?
                        vaccination.getDoseNumber() : Integer.parseInt(doseInput);

                Vaccination updatedVac = new Vaccination(treatmentId, newCost, newDuration, newComplete, newVacName, newDosNum);
                treatmentDAO.updateVaccination(updatedVac);

            } else if (existingTreatment instanceof Surgery) {
                Surgery surgery = (Surgery) existingTreatment;
                System.out.print("│ New Anesthesia type [" + surgery.getAnesthesiaType() + "]: ");
                String newAnes = scanner.nextLine();
                if (newAnes.trim().isEmpty()) {
                    newAnes = surgery.getAnesthesiaType();
                }

                System.out.print("│ New Risk level  [" + surgery.getRiskLevel() + "]: ");
                String riskInput = scanner.nextLine();
                int newRisk = riskInput.trim().isEmpty() ?
                        surgery.getRiskLevel() : Integer.parseInt(riskInput);

                Surgery updatedSur = new Surgery(treatmentId, newCost, newDuration, newComplete, newAnes, newRisk);
                treatmentDAO.updateSurgery(updatedSur);
            }

            System.out.println("└────────────────────────────────────────┘");

        } catch (NumberFormatException e) {
            System.out.println("❌ Error: Invalid number format!");
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Validation Error: " + e.getMessage());
        }
    }

    private void deleteTreatment() {
        System.out.println("\n┌─ DELETE Treatment ─────────────────────────┐");
        System.out.print("│ Enter Treatment ID to delete: ");

        try {
            int treatmentId = scanner.nextInt();
            scanner.nextLine();

            // First, show who will be deleted
            Treatment treatment = treatmentDAO.getTreatmentById(treatmentId);

            if (treatment == null) {
                System.out.println("❌ No treatment found with ID: " + treatmentId);
                return;
            }

            System.out.println("│ Treatment to delete:");
            System.out.println("│ " + treatment.toString());
            System.out.println("└────────────────────────────────────────┘");

            System.out.print("⚠️  Are you sure? (yes/no): ");
            String confirmation = scanner.nextLine();

            if (confirmation.equalsIgnoreCase("yes")) {
                treatmentDAO.deleteTreatment(treatmentId);
            } else {
                System.out.println("❌ Deletion cancelled.");
            }

        } catch (java.util.InputMismatchException e) {
            System.out.println("❌ Error: Invalid input!");
            scanner.nextLine();
        }
    }

    private void searchByName() {
        System.out.println("\n┌─ SEARCH BY NAME ───────────────────────┐");
        System.out.print("│ Enter treatment name to search: ");
        String treatmentName = scanner.nextLine();
        System.out.println("└────────────────────────────────────────┘");

        List<Treatment> results = treatmentDAO.searchByName(treatmentName);

        displaySearchResults(results, "Search: '" + treatmentName + "'");
    }

    private void searchByCostRange() {
        try {
            System.out.println("\n┌─ SEARCH BY COST RANGE ───────────────┐");
            System.out.print("│ Enter minimum cost: ");
            double minCost = scanner.nextDouble();

            System.out.print("│ Enter maximum cost: ");
            double maxCost = scanner.nextDouble();
            scanner.nextLine();
            System.out.println("└────────────────────────────────────────┘");

            List<Treatment> results = treatmentDAO.searchByCostRange(minCost, maxCost);

            displaySearchResults(results, "Cost: " + minCost + " - " + maxCost + " KZT");

        } catch (java.util.InputMismatchException e) {
            System.out.println("❌ Error: Invalid number!");
            scanner.nextLine();
        }
    }

    private void searchHighCostTreatment() {
        try {
            System.out.println("\n┌─ HIGH-Cost Treatment ──────────────────────┐");
            System.out.print("│ Enter minimum cost: ");
            double minCost = scanner.nextDouble();
            scanner.nextLine();
            System.out.println("└────────────────────────────────────────┘");

            List<Treatment> results = treatmentDAO.searchByMinCost(minCost);

            displaySearchResults(results, "Cost >= " + minCost + " KZT");

        } catch (java.util.InputMismatchException e) {
            System.out.println("❌ Error: Invalid number!");
            scanner.nextLine();
        }
    }


    private void displaySearchResults(List<Treatment> results, String criteria) {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║         SEARCH RESULTS                ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("Criteria: " + criteria);
        System.out.println("─────────────────────────────────────────");

        if (results.isEmpty()) {
            System.out.println("📭 No treatment found matching criteria.");
        } else {
            for (int i = 0; i < results.size(); i++) {
                Treatment s = results.get(i);
                System.out.print((i + 1) + ". ");
                System.out.print("[" + s.getTreatmentName() + "] ");
                System.out.println(s.toString());
            }
            System.out.println("─────────────────────────────────────────");
            System.out.println("Total Results: " + results.size());
        }
    }

    @Override
    public void displayMenu() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║         MAIN MENU - Week 8            ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("┌─ Treatment MANAGEMENT ─────────────────────┐");
        System.out.println("│ 1. Add Vaccination                         │");
        System.out.println("│ 2. Add Surgery                             │");
        System.out.println("│ 3. View All Treatment                      │");
        System.out.println("│ 4. View Vaccination Only                   │");
        System.out.println("│ 5. View Surgery Only                       │");
        System.out.println("│ 6. Update Treatment                        │");
        System.out.println("│ 7. Delete Treatment                        │");
        System.out.println("├─ SEARCH & FILTER ──────────────────────────┤");
        System.out.println("│ 8. Search by Name                          │");
        System.out.println("│ 9. Search by Cost Range                    │");
        System.out.println("│10. High-Cost Treatment (Cost >= X)         │");
        System.out.println("├─ DEMO & OTHER ─────────────────────────────┤");
        System.out.println("│11. Polymorphism Demo                       │");
        System.out.println("│ 0. Exit                                    │");
        System.out.println("└────────────────────────────────────────────┘");
    }

    @Override
    public void run() {
        boolean running = true;

        while (running) {
            displayMenu();
            System.out.print("\n👉 Enter your choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        addVaccination();
                        break;
                    case 2:
                        addSurgery();
                        break;
                    case 3:
                        viewAllTreatment();
                        break;
                    case 4:
                        viewVaccination();
                        break;
                    case 5:
                        viewSurgery();
                        break;
                    case 6:
                        updateTreatment();
                        break;
                    case 7:
                        deleteTreatment();
                        break;
                    case 8:
                        searchByName();
                        break;
                    case 9:
                        searchByCostRange();
                        break;
                    case 10:
                        searchHighCostTreatment();
                        break;
                    case 11:
                        demonstratePolymorphism();
                        break;
                    case 0:
                        running = false;
                        System.out.println("\n╔════════════════════════════════════════╗");
                        System.out.println("║  Thank you for using our system!      ║");
                        System.out.println("║  Goodbye! 👋                          ║");
                        System.out.println("╚════════════════════════════════════════╝");
                        break;
                    default:
                        System.out.println("❌ Invalid choice! Please select 0-11.");
                }

                if (choice != 0) {
                    pressEnterToContinue();
                }

            } catch (java.util.InputMismatchException e) {
                System.out.println("❌ Error: Please enter a valid number!");
                scanner.nextLine();
                pressEnterToContinue();
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
                scanner.nextLine();
                pressEnterToContinue();
            }
        }
        scanner.close();
    }

    private void pressEnterToContinue() {
        System.out.println("\n[Press Enter to continue...]");
        scanner.nextLine();
    }
}