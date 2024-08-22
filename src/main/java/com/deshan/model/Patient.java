/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deshan.model;

/**
 *
 * @author asus
 */
public class Patient extends Person{
    
    private String healthStatus; // Health status of the patient
    
    // Default constructor
    public Patient(){
        super();
    }
    
    // Constructor with parameters
    // Constructs a Patient object with specified attributes.
    public Patient(String name, String contactInfo, int id, int age, String gender,String address, String healthStatus) {
        super(name, contactInfo, id, age, gender, address);
        this.healthStatus = healthStatus;
    }

    // Getter for healthStatus
    public String getHealthStatus() {
        return healthStatus;
    }

    // Setter for healthStatus
    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

}
