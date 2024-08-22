/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deshan.model;

/**
 *
 * @author asus
 * 
 */
public class MedicalRecord {
    
    private int id; // Medical record ID
    private Patient patient; // Patient associated with the medical record
    private String diagnoses; // Diagnoses related to the medical record
    private String treatments; // Treatments prescribed for the medical record
    private String otherInformation; // Other relevant information about the medical record
    
    // Default constructor
    public MedicalRecord(){}

    // Constructor with parameters
    // Constructs a MedicalRecord object with specified attributes.
    public MedicalRecord(int id, Patient patient, String diagnoses, String treatments, String otherInformation) {
        this.id = id;
        this.patient = patient;
        this.diagnoses = diagnoses;
        this.treatments = treatments;
        this.otherInformation = otherInformation;
    }

    // Getter for ID
    public int getId() {
        return id;
    }

    // Setter for ID
    public void setId(int id) {
        this.id = id;
    }

    // Getter for patient
    public Patient getPatient() {
        return patient;
    }

    // Setter for patient
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    // Getter for diagnoses
    public String getDiagnoses() {
        return diagnoses;
    }

    // Setter for diagnoses
    public void setDiagnoses(String diagnoses) {
        this.diagnoses = diagnoses;
    }

    // Getter for treatments
    public String getTreatments() {
        return treatments;
    }

    // Setter for treatments
    public void setTreatments(String treatments) {
        this.treatments = treatments;
    }

    // Getter for otherInformation
    public String getOtherInformation() {
        return otherInformation;
    }

    // Setter for otherInformation
    public void setOtherInformation(String otherInformation) {
        this.otherInformation = otherInformation;
    }
}

