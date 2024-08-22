/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deshan.model;

/**
 *
 * @author asus
 */
public class Doctor extends Person{
    
    public Doctor(){
        super();
    }
    
    private String specialization;

    public Doctor(String name, String contactInformation, int id, int age, String gender,String address, String specialization) {
        super(name, contactInformation, id, age, gender, address);
        this.specialization = specialization;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}