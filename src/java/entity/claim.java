/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author jesperlim
 */
public class claim {
    int accidentID;
    String claimType;
    String claimant;
    String description;
    int amount;
    
    public claim(){
        
    }
    
    public claim(int accidentID, String claimType, String claimant, String description, int amount) {
        this.accidentID = accidentID;
        this.claimType = claimType;
        this.claimant = claimant;
        this.description = description;
        this.amount = amount;
    }

    public int getAccidentID() {
        return accidentID;
    }

    public void setAccidentID(int accidentID) {
        this.accidentID = accidentID;
    }

    public String getClaimType() {
        return claimType;
    }

    public void setClaimType(String claimType) {
        this.claimType = claimType;
    }

    public String getClaimant() {
        return claimant;
    }

    public void setClaimant(String claimant) {
        this.claimant = claimant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    
}
