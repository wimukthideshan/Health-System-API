/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deshan.model;

/**
 *
 * @author asus
 */
public class Prescription {
    
    private int id; // Prescription ID
    private Patient patient; // Patient associated with the prescription
    private String medication; // Medication prescribed
    private String dosage; // Dosage of the medication
    private String instructions; // Instructions for taking the medication
    private String duration; // Duration for which the medication should be taken
    
    // Default constructor
    public Prescription(){}

    // Constructor with parameters
    // Constructs a Prescription object with specified attributes.
    public Prescription(int id, Patient patient, String medication, String dosage, String instructions, String duration) {
        this.id = id;
        this.patient = patient;
        this.medication = medication;
        this.dosage = dosage;
        this.instructions = instructions;
        this.duration = duration;
    }

    // Getter for id
    public int getId() {
        return id;
    }

    // Setter for id
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

    // Getter for medication
    public String getMedication() {
        return medication;
    }

    // Setter for medication
    public void setMedication(String medication) {
        this.medication = medication;
    }

    // Getter for dosage
    public String getDosage() {
        return dosage;
    }

    // Setter for dosage
    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    // Getter for instructions
    public String getInstructions() {
        return instructions;
    }

    // Setter for instructions
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    // Getter for duration
    public String getDuration() {
        return duration;
    }

    // Setter for duration
    public void setDuration(String duration) {
        this.duration = duration;
    }
}
