/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entity.claim;
import entity.incident;
import entity.policy;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import utility.DatabaseConnectionManager;

/**
 *
 * @author jesperlim
 */
public class claimDAO {
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
    public void addClaim(claim c){
        //Prepare SQL statement
        //policyDAO dao = new policyDAO();
        //policy p = dao.retrieveByCarPlate(incident.getRegistrationNumber());
        boolean successful = false;
        String stmt = "insert into claim ('incident_id', 'claim_type', 'claimant', 'description', 'amount') values (?, ?, ?, ?, ?)";
        try {
            //Get connection from DatabaseConnectionManager
            conn = DatabaseConnectionManager.getConnection();

            //Prepare SQL statement
            pstmt = conn.prepareStatement(stmt);

            //Set parameters into prepared statement
            pstmt.setInt(1, c.getAccidentID());
            pstmt.setString(2, c.getClaimType());
            pstmt.setString(3, c.getClaimant());
            pstmt.setString(4, c.getDescription());
            pstmt.setInt(5, c.getAmount());
            //Execute query (retrieve)
            int result = pstmt.executeUpdate();

            //If there is a result
            if (result != 0) {
                successful = true;
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
        
        if(successful){
            policyDAO dao = new policyDAO();
            dao.updatePolicy(c);
        }
    }
    
    
    public ArrayList<claim> retrieveAll(int incident_id){
        //Prepare SQL statement
        String stmt = "SELECT * FROM claim where incident_id = " + incident_id + " order by incident.sas_date desc;";
        ArrayList<claim> claimList = new ArrayList<claim>();
        claim c = null;
        try {
            //Get connection from DatabaseConnectionManager
            conn = DatabaseConnectionManager.getConnection();

            //Prepare SQL statement
            pstmt = conn.prepareStatement(stmt);

            //Execute query (retrieve)
            rs = pstmt.executeQuery();

            //If there is a result
            while (rs.next()) {
                System.out.println("get into database!!!");
                //System.out.println(rs.getString(2));
                //Set record results into variable
                int accidentID = rs.getInt("incident_id"); 
                int claimId = rs.getInt("claim_id");
                int amount = rs.getInt("amount");
                String claimType = rs.getString("claim_type");
                String claimant = rs.getString("claimant");
                String description = rs.getString("description");
                c = new claim(accidentID, claimType, claimant, description, amount);
                claimList.add(c);
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
        return claimList;
    }
}
