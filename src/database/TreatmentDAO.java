package database;

import Entity.*;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarInputStream;

public class TreatmentDAO {

    public boolean insertVaccination(Vaccination vaccination) {
        String sql = "INSERT INTO treatment (treatment_name, cost, duration, completed, vaccine_name, dose_number) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, vaccination.getTreatmentName());
            statement.setDouble(2, vaccination.getCost());
            statement.setInt(3, vaccination.getDuration());
            statement.setBoolean(4, vaccination.getComplete());
            statement.setString(5, vaccination.getVaccineName());
            statement.setInt(6, vaccination.getDoseNumber());

            int rowsInserted = statement.executeUpdate();
            statement.close();

            if (rowsInserted > 0) {
                System.out.println("✅ Vaccination inserted: " + vaccination.getTreatmentName());
                return true;
            }

        } catch (SQLException e) {
            System.out.println("❌ Insert Vaccination failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return false;
    }

    public boolean insertSurgery(Surgery surgery){
        String sql = "INSERT INTO treatment (treatment_name, cost, duration, completed, anesthesia_type, risk_level) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, surgery.getTreatmentName());
            statement.setDouble(2, surgery.getCost());
            statement.setInt(3, surgery.getDuration());
            statement.setBoolean(4, surgery.getComplete());
            statement.setString(5, surgery.getAnesthesiaType());
            statement.setInt(6, surgery.getRiskLevel());

            int rowsInserted = statement.executeUpdate();
            statement.close();

            if (rowsInserted > 0) {
                System.out.println("✅ Surgery inserted: " + surgery.getTreatmentName());
                return true;
            }

        } catch (SQLException e) {
            System.out.println("❌ Insert Surgery failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return false;
    }

    public Treatment getTreatmentById(int treatmentId) {
        String sql = "SELECT * FROM treatment WHERE treatment_id = ?";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return null;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, treatmentId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Treatment treatment = extractTreatmentFromResultSet(resultSet);

                resultSet.close();
                statement.close();

                if (treatment != null) {
                    System.out.println("✅ Found treatment with ID: " + treatmentId);
                }

                return treatment;
            }

            System.out.println("⚠️ No treatment found with ID: " + treatmentId);

            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            System.out.println("❌ Select by ID failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return null;
    }

    public List<Vaccination> getAllVaccination() {
        List<Vaccination> vaccinations = new ArrayList<>();
        String sql = "SELECT * FROM treatment WHERE treatment_name = 'Vaccination' ORDER BY treatment_id";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return vaccinations;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Treatment treatment = extractTreatmentFromResultSet(resultSet);
                if (treatment instanceof Vaccination) {
                    vaccinations.add((Vaccination) treatment);
                }
            }

            resultSet.close();
            statement.close();

            System.out.println("✅ Retrieved " + vaccinations.size() + " vaccinations");

        } catch (SQLException e) {
            System.out.println("❌ Select vaccinations failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return vaccinations;
    }

    public List<Surgery> getAllSurgery(){
        List<Surgery> surgeries = new ArrayList<>();
        String sql = "SELECT * FROM treatment WHERE treatment_name = 'Surgery' ORDER BY treatment_id";

        Connection connection = DatabaseConnection.getConnection();
        if(connection == null) return surgeries;

        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Treatment treatment = extractTreatmentFromResultSet(resultSet);
                if(treatment instanceof Surgery){
                    surgeries.add((Surgery) treatment);
                }
            }

            resultSet.close();
            statement.close();

            System.out.println("✅ Retrieved " + surgeries.size() + " surgeries");
        }catch (SQLException e) {
            System.out.println("❌ Select vaccinations failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return surgeries;
    }

    public List<Treatment> getAllTreatment() {
        List<Treatment> treatmentList = new ArrayList<>();
        String sql = "SELECT * FROM treatment ORDER BY treatment_id";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return treatmentList;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Treatment treatment = extractTreatmentFromResultSet(resultSet);
                if (treatment != null) {
                    treatmentList.add(treatment);
                }
            }

            resultSet.close();
            statement.close();

            System.out.println("✅ Retrieved " + treatmentList.size() + " treatment from database");

        } catch (SQLException e) {
            System.out.println("❌ Select all treatment failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return treatmentList;
    }

    public boolean updateVaccination(Vaccination vaccination) {
        String sql = "UPDATE treatment SET cost = ?, duration = ?, completed = ?, vaccine_name = ?, dose_number=? " +
                "WHERE treatment_id = ? AND treatment_name = 'Vaccination'";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, vaccination.getCost());
            statement.setInt(2, vaccination.getDuration());
            statement.setBoolean(3, vaccination.getComplete());
            statement.setString(4, vaccination.getVaccineName());
            statement.setInt(5, vaccination.getDoseNumber());
            statement.setInt(6, vaccination.getTreatmentId());

            int rowsUpdated = statement.executeUpdate();
            statement.close();

            if (rowsUpdated > 0) {
                System.out.println("✅ Vaccination updated: " + vaccination.getVaccineName());
                return true;
            } else {
                System.out.println("⚠️ No vaccination found with ID: " + vaccination.getTreatment());
            }

        } catch (SQLException e) {
            System.out.println("❌ Update Vaccination failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return false;
    }

    public boolean updateSurgery(Surgery surgery){
        String sql = "UPDATE treatment SET cost = ?, duration = ?, completed = ?, anesthesia_type = ?, risk_level = ?"
                + "WHERE treatment_id = ? AND treatment_name = 'Surgery'";

        Connection connection = DatabaseConnection.getConnection();
        if(connection == null) return false;

        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, surgery.getCost());
            statement.setInt(2, surgery.getDuration());
            statement.setBoolean(3, surgery.getComplete());
            statement.setString(4, surgery.getAnesthesiaType());
            statement.setInt(5, surgery.getRiskLevel());
            statement.setInt(6, surgery.getTreatmentId());


            int rowsUpdated = statement.executeUpdate();
            statement.close();

            if(rowsUpdated>0){
                System.out.println("✅ Surgery updated: " + surgery.getAnesthesiaType());
                return true;
            }else{
                System.out.println("⚠️ No surgery found with ID: " + surgery.getTreatment());
            }
        }catch (SQLException e){
            System.out.println("❌ Update Vaccination failed!");
            e.printStackTrace();
        }finally {
            DatabaseConnection.closeConnection(connection);
        }
        return false;
    }

    public boolean deleteTreatment(int treatmentId) {
        String sql = "DELETE FROM treatment WHERE treatment_id = ?";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, treatmentId);

            int rowsDeleted = statement.executeUpdate();
            statement.close();

            if (rowsDeleted > 0) {
                System.out.println("✅ Treatment deleted (ID: " + treatmentId + ")");
                return true;
            } else {
                System.out.println("⚠️ No treatment found with ID: " + treatmentId);
            }

        } catch (SQLException e) {
            System.out.println("❌ Delete failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return false;
    }

    public List<Treatment> searchByName(String treatment_name) {
        List<Treatment> treatmentList = new ArrayList<>();

        // ILIKE for case-insensitive search, % for partial match
        String sql = "SELECT * FROM treatment WHERE treatment_name ILIKE ? ORDER BY treatment_name";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return treatmentList;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + treatment_name + "%");  // % = wildcard

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Treatment treatment = extractTreatmentFromResultSet(resultSet);
                if (treatment != null) {
                    treatmentList.add(treatment);
                }
            }

            resultSet.close();
            statement.close();

            System.out.println("✅ Found " + treatmentList.size() + " treatment matching '" + treatment_name + "'");

        } catch (SQLException e) {
            System.out.println("❌ Search by treatment name failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return treatmentList;
    }

    public List<Treatment> searchByCostRange(double minCost, double maxCost) {
        List<Treatment> treatmentList = new ArrayList<>();

        String sql = "SELECT * FROM treatment WHERE cost BETWEEN ? AND ? ORDER BY cost DESC";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return treatmentList;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, minCost);
            statement.setDouble(2, maxCost);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Treatment treatment = extractTreatmentFromResultSet(resultSet);
                if (treatment != null) {
                    treatmentList.add(treatment);
                }
            }

            resultSet.close();
            statement.close();

            System.out.println("✅ Found " + treatmentList.size() + " treatment in cost range " +
                    minCost + " - " + maxCost);

        } catch (SQLException e) {
            System.out.println("❌ Search by cost failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return treatmentList;
    }

    public List<Treatment> searchByMinCost(double minCost) {
        List<Treatment> treatmentList = new ArrayList<>();

        String sql = "SELECT * FROM treatment WHERE cost >= ? ORDER BY cost DESC";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return treatmentList;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, minCost);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Treatment treatment = extractTreatmentFromResultSet(resultSet);
                if (treatment != null) {
                    treatmentList.add(treatment);
                }
            }

            resultSet.close();
            statement.close();

            System.out.println("✅ Found " + treatmentList.size() + " treatment cost >= " + minCost);

        } catch (SQLException e) {
            System.out.println("❌ Search by min cost failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return treatmentList;
    }



    public void displayAllTreatment() {
        List<Treatment> treatmentList = getAllTreatment();

        System.out.println("\n========================================");
        System.out.println("   ALL TREATMENT FROM DATABASE");
        System.out.println("========================================");

        if (treatmentList.isEmpty()) {
            System.out.println("No treatment members in database.");
        } else {
            for (int i = 0; i < treatmentList.size(); i++) {
                Treatment s = treatmentList.get(i);
                System.out.print((i + 1) + ". ");
                System.out.print("[" + s.getTreatmentName() + "] ");
                System.out.println(s.toString());
            }
        }

        System.out.println("========================================\n");
    }

    public void demonstratePolymorphism() {
        List<Treatment> treatmentList = getAllTreatment();

        System.out.println("\n========================================");
        System.out.println("  POLYMORPHISM: Treatment from Database");
        System.out.println("========================================");

        if (treatmentList.isEmpty()) {
            System.out.println("No treatment to demonstrate.");
        } else {
            for (Treatment s : treatmentList) {
                s.work();
            }
        }

        System.out.println("========================================\n");
    }

    private Treatment extractTreatmentFromResultSet(ResultSet resultSet) throws SQLException {
        int treatmentId = resultSet.getInt("treatment_id");
        String treatmentName = resultSet.getString("treatment_name");
        double cost = resultSet.getDouble("cost");
        int duration = resultSet.getInt("duration");
        boolean completed = resultSet.getBoolean("completed");


        Treatment treatment = null;

        if ("Vaccination".equals(treatmentName)) {
            String vaccineName = resultSet.getString("vaccine_name");
            int doseNumber = resultSet.getInt("dose_number");
            treatment = new Vaccination(treatmentId, cost, duration, completed, vaccineName, doseNumber);

        } else if ("Surgery".equals(treatmentName)) {
            String anesthesiaType = resultSet.getString("anesthesia_type");
            int riskLevel = resultSet.getInt("risk_level");
            treatment = new Surgery(treatmentId, cost,duration,completed, anesthesiaType, riskLevel);
        }

        return treatment;
    }
}
