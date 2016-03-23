/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entity.claim;
import entity.policy;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import utility.DatabaseConnectionManager;

/**
 *
 * @author jesperlim
 */
public class policyDAO {
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
    
    public ArrayList<String> retrieve(String carPlate){
        //Prepare SQL statement
        String stmt = "select * from policy where car_plate_number = ?;";
        ArrayList<String> policy = new ArrayList<String>();
        try {
            //Get connection from DatabaseConnectionManager
            conn = DatabaseConnectionManager.getConnection();

            //Prepare SQL statement
            pstmt = conn.prepareStatement(stmt);

            //Set parameters into prepared statement
            pstmt.setString(1, carPlate);

            //Execute query (retrieve)
            rs = pstmt.executeQuery();
            
            
            //If there is a result
            if (rs.next()) {
                System.out.println("get into database!!!");
                //System.out.println(rs.getString(2));
                //Set record results into variable
                policy.add(rs.getString("driver_name"));
                policy.add(rs.getString("driver_age"));
                policy.add("" + rs.getInt("client_contact_number"));
                policy.add("" + rs.getInt("agent_contact_number"));
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
        return policy;
    }
    public policy retrieveByCarPlate(String carPlate){
        //Prepare SQL statement
        String stmt = "select * from policy where car_plate_number = ?;";
        
        policy p = null;
        try {
            //Get connection from DatabaseConnectionManager
            conn = DatabaseConnectionManager.getConnection();

            //Prepare SQL statement
            pstmt = conn.prepareStatement(stmt);

            //Set parameters into prepared statement
            pstmt.setString(1, carPlate);

            //Execute query (retrieve)
            rs = pstmt.executeQuery();
            
            
            //If there is a result
            if (rs.next()) {
                System.out.println("get into database!!!");
                //System.out.println(rs.getString(2));
                //Set record results into variable
                int policyId = rs.getInt("policy_id");
                String policyType = rs.getString("policy_type");
                String name = rs.getString("driver_name");
                String age = rs.getString("driver_age");
                String carPlateNumber = rs.getString("car_plate_number");
                int clientNumber = rs.getInt("client_contact_number");
                int agentNumber = rs.getInt("agent_contact_number");
                Date policyDate = rs.getDate("policy_date");
                int medicalQuota = rs.getInt("medical_quota");
                int repairQuota = rs.getInt("repair_quota");
                Date expireDate = rs.getDate("expire_date");
                
                p = new policy(policyId, policyType, name, age, carPlateNumber, agentNumber, clientNumber, policyDate, medicalQuota, repairQuota, expireDate);
                
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
        return p;
    }
    
    public policy retrieveByPolicyId(int id){
        //Prepare SQL statement
        String stmt = "select * from policy where policy_id = ?;";
        
        policy p = null;
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
                int policyId = rs.getInt("policy_id");
                String policyType = rs.getString("policy_type");
                String name = rs.getString("driver_name");
                String age = rs.getString("driver_age");
                String carPlateNumber = rs.getString("car_plate_number");
                int clientNumber = rs.getInt("client_contact_number");
                int agentNumber = rs.getInt("agent_contact_number");
                Date policyDate = rs.getDate("policy_date");
                int medicalQuota = rs.getInt("medical_quota");
                int repairQuota = rs.getInt("repair_quota");
                Date expireDate = rs.getDate("expire_date");
                
                p = new policy(policyId, policyType, name, age, carPlateNumber, agentNumber, clientNumber, policyDate, medicalQuota, repairQuota, expireDate);
                
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
        return p;
    }
    
    
    public void updatePolicy(claim c){
        //Prepare SQL statement
        incidentDAO dao = new incidentDAO();
        int policyId = dao.retrieveByIncidentId(c.getAccidentID());
        policy p = retrieveByPolicyId(policyId);
        int newAmount = 0;
        String stmt = "";
        if(c.getClaimType().equalsIgnoreCase("Medical")){
            newAmount = p.getMedicalQuota() - c.getAmount();
            stmt = "update policy set medical_quota = " + newAmount + " where policy_id = " + policyId + ";";
        }else{
            newAmount = p.getRepairQuota() - c.getAmount();
            stmt = "update policy set repair_quota = " + newAmount + " where policy_id = " + policyId + ";";
        }
        
        try {
            //Get connection from DatabaseConnectionManager
            conn = DatabaseConnectionManager.getConnection();

            //Prepare SQL statement
            pstmt = conn.prepareStatement(stmt);
            
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
