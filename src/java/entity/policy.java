/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;

/**
 *
 * @author jesperlim
 */
public class policy {
    int policy_id;
    String policyType;
    String driverName;
    String driverAge;
    String carPlateNumber;
    int agentContactNumber;
    int clientContactNumber;
    Date policyDate;
    int medicalQuota;
    int repairQuota;
    Date expireDate;

    public Date getPolicyDate() {
        return policyDate;
    }

    public void setPolicyDate(Date policyDate) {
        this.policyDate = policyDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public policy(int policy_id, String policyType, String driverName, String driverAge, String carPlateNumber, int agentContactNumber, int clientContactNumber, Date policyDate, int medicalQuota, int repairQuota, Date expireDate) {
        this.policy_id = policy_id;
        this.policyType = policyType;
        this.driverName = driverName;
        this.driverAge = driverAge;
        this.carPlateNumber = carPlateNumber;
        this.agentContactNumber = agentContactNumber;
        this.clientContactNumber = clientContactNumber;
        this.policyDate = policyDate;
        this.medicalQuota = medicalQuota;
        this.repairQuota = repairQuota;
    }

    public int getPolicy_id() {
        return policy_id;
    }

    public void setPolicy_id(int policy_id) {
        this.policy_id = policy_id;
    }

    public String getPolicyType() {
        return policyType;
    }

    public void setPolicyType(String policyType) {
        this.policyType = policyType;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverAge() {
        return driverAge;
    }

    public void setDriverAge(String driverAge) {
        this.driverAge = driverAge;
    }

    public String getCarPlateNumber() {
        return carPlateNumber;
    }

    public void setCarPlateNumber(String carPlateNumber) {
        this.carPlateNumber = carPlateNumber;
    }

    public int getAgentContactNumber() {
        return agentContactNumber;
    }

    public void setAgentContactNumber(int agentContactNumber) {
        this.agentContactNumber = agentContactNumber;
    }

    public int getClientContactNumber() {
        return clientContactNumber;
    }

    public void setClientContactNumber(int clientContactNumber) {
        this.clientContactNumber = clientContactNumber;
    }

    public int getMedicalQuota() {
        return medicalQuota;
    }

    public void setMedicalQuota(int medicalQuota) {
        this.medicalQuota = medicalQuota;
    }

    public int getRepairQuota() {
        return repairQuota;
    }

    public void setRepairQuota(int repairQuota) {
        this.repairQuota = repairQuota;
    }
    
    
}
