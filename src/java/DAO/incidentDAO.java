/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entity.incident;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import utility.DatabaseConnectionManager;

/**
 *
 * @author jesperlim
 */
public class incidentDAO {
    /**
     * Connection object is required to access the database.
     */
    private Connection conn;
    /**
     * PreparedStatement is a parameterized SQL statement which is used to query
     * the database.
     */
    private PreparedStatement pstmt;
    /**
     * ResultSet is a table of data representing a database result set.
     */
    private ResultSet rs;
    
    public incident retrieve(){
        //Prepare SQL statement
        String stmt = "select * from incident inner join policy on incident.policy_id = policy.policy_id where policy.car_plate_number = ?;";
        incident incident = null;
        try {
            //Get connection from DatabaseConnectionManager
            conn = DatabaseConnectionManager.getConnection();

            //Prepare SQL statement
            pstmt = conn.prepareStatement(stmt);

            //Set parameters into prepared statement
            pstmt.setString(1, "SBC1314Z");

            //Execute query (retrieve)
            rs = pstmt.executeQuery();

            //If there is a result
            if (rs.next()) {
                System.out.println("get into database!!!");
                //System.out.println(rs.getString(2));
                //Set record results into variable
                String date = rs.getString("sas_date"); 
                String location = rs.getString("sas_formatted_address");
                String registrationNumber = rs.getString("sas_registration_plate");
                String owner = rs.getString("sas_owner");
                String contactNumber = rs.getString("sas_contact_number");
                String crashType = rs.getString("sas_type");
                String weather = rs.getString("sas_weather");
                boolean isReported = rs.getBoolean("sas_isReported");
                String otherRegistrationNumber = rs.getString("other_registration_plate");
                String otherDriver = rs.getString("other_driver");
                String otherCompany = rs.getString("other_company");
                incident = new incident(date, location, registrationNumber, owner, contactNumber, crashType, weather, isReported, otherRegistrationNumber, otherDriver, otherCompany);
                //Initiate Incident object based on results from the database
            }

            //If result set is not null, close it
            if (rs != null) {
                rs.close();
            }
            //If prepared statement is not null, close it.
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            //Prints out SQLException - good for debugging if sql statement is buggy or constraints that may be causing issues                        
            System.out.println("Failed to retrieve incident:" + e);
        } finally {
            //Close the connection 
            DatabaseConnectionManager.closeConnection(conn);
        }
        return incident;
    }
    public ArrayList<incident> retrieveAll(String registration_plate){
        //Prepare SQL statement
        String stmt = "SELECT * FROM incident inner join policy on incident.policy_id = policy.policy_id where incident.sas_registration_plate = ? order by incident.sas_date desc;";
        ArrayList<incident> incidentList = new ArrayList<incident>();
        incident incident = null;
        try {
            //Get connection from DatabaseConnectionManager
            conn = DatabaseConnectionManager.getConnection();

            //Prepare SQL statement
            pstmt = conn.prepareStatement(stmt);

            //Set parameters into prepared statement
            pstmt.setString(1, registration_plate);

            //Execute query (retrieve)
            rs = pstmt.executeQuery();

            //If there is a result
            while (rs.next()) {
                System.out.println("get into database!!!");
                //System.out.println(rs.getString(2));
                //Set record results into variable
                String date = rs.getString("sas_date"); 
                String location = rs.getString("sas_formatted_address");
                String registrationNumber = rs.getString("sas_registration_plate");
                String owner = rs.getString("sas_owner");
                String contactNumber = rs.getString("sas_contact_number");
                String crashType = rs.getString("sas_type");
                String weather = rs.getString("sas_weather");
                boolean isReported = rs.getBoolean("sas_isReported");
                String otherRegistrationNumber = rs.getString("other_registration_plate");
                String otherDriver = rs.getString("other_driver");
                String otherCompany = rs.getString("other_company");
                incident = new incident(date, location, registrationNumber, owner, contactNumber, crashType, weather, isReported, otherRegistrationNumber, otherDriver, otherCompany);
                incidentList.add(incident);
                //Initiate Incident object based on results from the database
            }

            //If result set is not null, close it
            if (rs != null) {
                rs.close();
            }
            //If prepared statement is not null, close it.
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            //Prints out SQLException - good for debugging if sql statement is buggy or constraints that may be causing issues                        
            System.out.println("Failed to retrieve incident:" + e);
        } finally {
            //Close the connection 
            DatabaseConnectionManager.closeConnection(conn);
        }
        return incidentList;
    }
    
    public void updateIncident(incident incident){
        //Prepare SQL statement
        String stmt = "update incident set sas_location_lat = ?, sas_location_lng = ?, sas_type = ?, sas_formatted_address = ? where sas_registration_plate = ?";
        try {
            //Get connection from DatabaseConnectionManager
            conn = DatabaseConnectionManager.getConnection();

            //Prepare SQL statement
            pstmt = conn.prepareStatement(stmt);

            //Set parameters into prepared statement
            pstmt.setString(1, incident.getLat());
            pstmt.setString(2, incident.getLng());
            pstmt.setString(3, incident.getCrashType());
            pstmt.setString(4, incident.getFormattedAddress());
            pstmt.setString(5, incident.getRegistrationNumber());
            
            //Execute query (retrieve)
            int result = pstmt.executeUpdate();

            //If there is a result
            if (result != 0) {
                System.out.println("updated!!!");
            }

            //If result set is not null, close it
            if (rs != null) {
                rs.close();
            }
            //If prepared statement is not null, close it.
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            //Prints out SQLException - good for debugging if sql statement is buggy or constraints that may be causing issues                        
            System.out.println("Failed to retrieve incident:" + e);
        } finally {
            //Close the connection 
            DatabaseConnectionManager.closeConnection(conn);
        }
    }
    public void addIncident(incident incident){
        //Prepare SQL statement
        String stmt = "insert into incident ('', '', '', '') values (?, ?, ?, ?)";
        try {
            //Get connection from DatabaseConnectionManager
            conn = DatabaseConnectionManager.getConnection();

            //Prepare SQL statement
            pstmt = conn.prepareStatement(stmt);

            //Set parameters into prepared statement
            pstmt.setString(1, incident.getLat());
            pstmt.setString(2, incident.getLng());
            pstmt.setString(3, incident.getCrashType());
            pstmt.setString(4, incident.getFormattedAddress());
            pstmt.setString(5, incident.getRegistrationNumber());
            
            //Execute query (retrieve)
            int result = pstmt.executeUpdate();

            //If there is a result
            if (result != 0) {
                System.out.println("updated!!!");
            }

            //If result set is not null, close it
            if (rs != null) {
                rs.close();
            }
            //If prepared statement is not null, close it.
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            //Prints out SQLException - good for debugging if sql statement is buggy or constraints that may be causing issues                        
            System.out.println("Failed to retrieve incident:" + e);
        } finally {
            //Close the connection 
            DatabaseConnectionManager.closeConnection(conn);
        }
    }
}
