package com.deshan.model;

/**
 * Represents billing information associated with a patient.
 * 
 * 
 * 
 * 
 * 
 * @author asus
 */
public class Billing {
    
    private int id; // Billing ID
    private Patient patient; // Patient associated with the billing
    private double totalAmount; // Total amount of the billing
    private double paidAmount; // Amount paid for the billing
    private double outstandingBalance; // Outstanding balance for the billing
    
    // Default constructor
    public Billing(){}

    // Constructor with parameters
     // Constructs a Billing object with specified attributes.
    public Billing(int id, Patient patient, double totalAmount, double paidAmount, double outstandingBalance) {
        this.id = id;
        this.patient = patient;
        this.totalAmount = totalAmount;
        this.paidAmount = paidAmount;
        this.outstandingBalance = outstandingBalance;
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

    // Getter for totalAmount
    public double getTotalAmount() {
        return totalAmount;
    }

    // Setter for totalAmount
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    // Getter for paidAmount
    public double getPaidAmount() {
        return paidAmount;
    }

    // Setter for paidAmount
    public void setPaidAmount(double paidAmount) {
        this.paidAmount = paidAmount;
    }

    // Getter for outstandingBalance
    public double getOutstandingBalance() {
        return outstandingBalance;
    }

    // Setter for outstandingBalance
    public void setOutstandingBalance(double outstandingBalance) {
        this.outstandingBalance = outstandingBalance;
    }
}
