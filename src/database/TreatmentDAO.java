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
                "VALUES ('Vaccination', ?, ?, ?, ?, ?)";

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
                "VALUES ('Surgery', ?, ?, ?, ?, ?)";

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



    private Treatment extractTreatmentFromResultSet(ResultSet resultSet) throws SQLException {
        int treatmentId = resultSet.getInt("treatment_id");
        String treatmentName = resultSet.getString("treatment_name");
        double cost = resultSet.getDouble("cost");
        int duration = resultSet.getInt("duration");
        boolean completed = resultSet.getBoolean("is_completed");


        Treatment treatment = null;

        if ("Vaccination".equals(treatmentName)) {
            String vaccineName = resultSet.getString("vaccine_name");
            int doseNumber = resultSet.getInt("dose_number");
            treatment = new Vaccination(cost, duration, completed, vaccineName, doseNumber);

        } else if ("Surgery".equals(treatmentName)) {
            String anesthesiaType = resultSet.getString("anesthesia_type");
            int riskLevel = resultSet.getInt("risk_level");
            treatment = new Surgery(cost,duration,completed, anesthesiaType, riskLevel);
        }

        return treatment;
    }
}
