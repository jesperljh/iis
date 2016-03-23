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
public class incident {
    int incidentId;
    String date;
    String location;
    String registrationNumber;
    String owner;
    String contactNumber;
    String crashType;
    String weather;
    boolean isReported;
    String otherRegistrationNumber;
    String otherDriver;
    String otherCompany;
    String lat;
    String lng;
    String formattedAddress;

    public incident(){
        
    }
    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public int getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(int incidentId) {
        this.incidentId = incidentId;
    }
    

    public incident(int incidentId, String date, String location, String registrationNumber, String owner, String contactNumber, String crashType, String weather, boolean isReported, String otherRegistrationNumber, String otherDriver, String otherCompany) {
        this.incidentId = incidentId;
        this.date = date;
        this.location = location;
        this.registrationNumber = registrationNumber;
        this.owner = owner;
        this.contactNumber = contactNumber;
        this.crashType = crashType;
        this.weather = weather;
        this.isReported = isReported;
        this.otherRegistrationNumber = otherRegistrationNumber;
        this.otherDriver = otherDriver;
        this.otherCompany = otherCompany;
    }

    
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getCrashType() {
        return crashType;
    }

    public void setCrashType(String crashType) {
        this.crashType = crashType;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public boolean getIsReported() {
        return isReported;
    }

    public void setIsReported(boolean isReported) {
        this.isReported = isReported;
    }

    public String getOtherRegistrationNumber() {
        return otherRegistrationNumber;
    }

    public void setOtherRegistrationNumber(String otherRegistrationNumber) {
        this.otherRegistrationNumber = otherRegistrationNumber;
    }

    public String getOtherDriver() {
        return otherDriver;
    }

    public void setOtherDriver(String otherDriver) {
        this.otherDriver = otherDriver;
    }

    public String getOtherCompany() {
        return otherCompany;
    }

    public void setOtherCompany(String otherCompany) {
        this.otherCompany = otherCompany;
    }
    
    
}
