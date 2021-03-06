/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entity.incident;
import entity.policy;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    public incident retrieve(int id) { // ******* should we sort by incident_id as descending also? to get the last inciednt object ***********
        //Prepare SQL statement
        String stmt = "select * from incident where incident_id = ?;";
        incident incident = null;
        try {
            //Get connection from DatabaseConnectionManager
            conn = DatabaseConnectionManager.getConnection();

            //Prepare SQL statement
            pstmt = conn.prepareStatement(stmt);

            //Set parameters into prepared statement
            pstmt.setInt(1, id);

            //Execute query (retrieve)
            rs = pstmt.executeQuery();

            //If there is a result
            if (rs.next()) {
                System.out.println("get into database!!!");
                //System.out.println(rs.getString(2));
                //Set record results into variable
                int incidentId = rs.getInt("incident_id");
                String date = rs.getString("sas_date");
                String location = rs.getString("sas_formatted_address");
                String registrationNumber = rs.getString("sas_registration_plate");
                String owner = rs.getString("sas_owner");
                String contactNumber = rs.getString("sas_contact_number");
                String crashType = rs.getString("sas_type");
                String weather = rs.getString("sas_weather");
                boolean isReported = rs.getBoolean("is_reported");
                String otherRegistrationNumber = rs.getString("other_registration_plate");
                String otherDriver = rs.getString("other_driver");
                String otherCompany = rs.getString("other_company");
                boolean reportedToPolice = rs.getBoolean("sas_reported_to_police");
                incident = new incident(incidentId, date, location, registrationNumber, owner, contactNumber, crashType, weather, isReported, otherRegistrationNumber, otherDriver, otherCompany, reportedToPolice);
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

    public ArrayList<incident> retrieveAll(String registration_plate) {
        //Prepare SQL statement
        String stmt = "SELECT * FROM incident inner join policy on incident.policy_id = policy.policy_id where incident.sas_registration_plate = ? order by incident.sas_date desc, incident.incident_id desc;";
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
                int incidentId = rs.getInt("incident_id");
                String date = rs.getString("sas_date");
                String location = rs.getString("sas_formatted_address");
                String registrationNumber = rs.getString("sas_registration_plate");
                String owner = rs.getString("sas_owner");
                String contactNumber = rs.getString("sas_contact_number");
                String crashType = rs.getString("sas_type");
                String weather = rs.getString("sas_weather");
                boolean isReported = rs.getBoolean("is_reported");
                String otherRegistrationNumber = rs.getString("other_registration_plate");
                String otherDriver = rs.getString("other_driver");
                String otherCompany = rs.getString("other_company");
                boolean reportedToPolice = rs.getBoolean(("sas_reported_to_police"));
                incident = new incident(incidentId, date, location, registrationNumber, owner, contactNumber, crashType, weather, isReported, otherRegistrationNumber, otherDriver, otherCompany, reportedToPolice);
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

    public int retrieveByIncidentId(int incidentId) {
        //Prepare SQL statement
        String stmt = "SELECT * FROM incident inner join policy on incident.policy_id = policy.policy_id where incident.incident_id = ?;";
        ArrayList<incident> incidentList = new ArrayList<incident>();
        incident incident = null;
        int policyId = 0;
        try {
            //Get connection from DatabaseConnectionManager
            conn = DatabaseConnectionManager.getConnection();

            //Prepare SQL statement
            pstmt = conn.prepareStatement(stmt);

            //Set parameters into prepared statement
            pstmt.setInt(1, incidentId);

            //Execute query (retrieve)
            rs = pstmt.executeQuery();

            //If there is a result
            while (rs.next()) {
                if (rs.getBoolean("incident.is_reported")) {
                    policyId = rs.getInt("policy.policy_id");
                }
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
        return policyId;
    }

    public void updateIncident(incident incident) {
        //Prepare SQL statement
        String stmt = "UPDATE incident SET sas_owner = ?, sas_formatted_address = ?, sas_type = ?, sas_weather = ?, sas_reported_to_police = ?, other_company = ?, is_reported = ?, other_driver = ?, other_registration_plate =? WHERE incident_id = ?";
        // ("UPDATE items SET name = ?, category = ?, price = ?, quantity = ? WHERE id = ?");
        try {
            //Get connection from DatabaseConnectionManager
            conn = DatabaseConnectionManager.getConnection();

            //Prepare SQL statement
            pstmt = conn.prepareStatement(stmt);

            //Set parameters into prepared statement
            pstmt.setString(1, incident.getOwner());
            pstmt.setString(2, incident.getFormattedAddress());
            pstmt.setString(3, incident.getCrashType());
            pstmt.setString(4, incident.getWeather());
            pstmt.setBoolean(5, incident.getIsReported());
            pstmt.setString(6, incident.getOtherCompany());
            pstmt.setBoolean(7, true);
            pstmt.setString(8, incident.getOtherDriver());
            pstmt.setString(9, incident.getOtherRegistrationNumber());
            pstmt.setInt(10, incident.getIncidentId());
           // pstmt.setString(7, incident.getContactNumber());

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

    public void addIncident(incident incident) throws ParseException {
        //Prepare SQL statement
        policyDAO dao = new policyDAO();
        policy p = dao.retrieveByCarPlate(incident.getRegistrationNumber());
        String stmt = "insert into incident (policy_id, sas_location_lat, sas_location_lng, sas_registration_plate, sas_owner, sas_contact_number, sas_type, sas_formatted_address, sas_date, is_reported) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            //Get connection from DatabaseConnectionManager
            conn = DatabaseConnectionManager.getConnection();

            //Prepare SQL statement
            pstmt = conn.prepareStatement(stmt);

            //Set parameters into prepared statement
            System.out.println(p.getPolicy_id());
            pstmt.setInt(1, p.getPolicy_id());
            System.out.println(incident.getLat());
            pstmt.setString(2, incident.getLat());
            System.out.println(incident.getLng());
            pstmt.setString(3, incident.getLng());
            System.out.println(incident.getRegistrationNumber());
            pstmt.setString(4, incident.getRegistrationNumber());
            System.out.println(p.getDriverName());
            pstmt.setString(5, p.getDriverName());
            System.out.println(p.getClientContactNumber());
            pstmt.setInt(6, p.getClientContactNumber());
            System.out.println(incident.getCrashType());
            pstmt.setString(7, incident.getCrashType());
            System.out.println(incident.getFormattedAddress());
            pstmt.setString(8, incident.getFormattedAddress());
            Date date = new Date();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String d = df.format(date);
            Date dateFormat = new SimpleDateFormat("yyy-MM-dd").parse(d);
            System.out.println(dateFormat.getTime());
            java.sql.Date sqlDate = new java.sql.Date(dateFormat.getTime());
            System.out.println(sqlDate);

            pstmt.setDate(9, sqlDate);
            pstmt.setBoolean(10, false);

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
