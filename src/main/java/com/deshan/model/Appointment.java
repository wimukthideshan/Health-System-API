/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author asus
 */
package com.deshan.model;

public class Appointment {
    
    private int id; // Appointment ID   
    private String date; // Date of the appointment    
    private String time; // Time of the appointment   
    private Patient patient; // Patient associated with the appointment   
    private Doctor doctor; // Doctor associated with the appointment
    
    // Default constructor
    public Appointment(){} 

    // Constructor with the parameters
    // Constructs an Appointment object with specified attributes.
    public Appointment(int id, String date, String time, Patient patient, Doctor doctor) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.patient = patient;
        this.doctor = doctor;
    }

    // Getter for ID
    public int getId() {
        return id;
    }

    // Setter for ID
    public void setId(int id) {
        this.id = id;
    }

    // Getter for date
    public String getDate() {
        return date;
    }

    // Setter for date
    public void setDate(String date) {
        this.date = date;
    }

    // Getter for time
    public String getTime() {
        return time;
    }

    // Setter for time
    public void setTime(String time) {
        this.time = time;
    }

    // Getter for patient
    public Patient getPatient() {
        return patient;
    }

    // Setter for patient
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    // Getter for doctor
    public Doctor getDoctor() {
        return doctor;
    }

    // Setter for doctor
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
