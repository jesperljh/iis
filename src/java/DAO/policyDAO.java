/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

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
}
