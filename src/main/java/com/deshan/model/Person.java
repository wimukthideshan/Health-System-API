/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deshan.model;

/**
 *
 * @author asus
 */
public class Person {
    
    private String name; // Name of the person
    private String contactInformation; // Contact information of the person
    private int id; // ID of the person
    private int age; // Age of the person
    private String gender; // Gender of the person
    private String address; // Address of the person
    
    // Default constructor
    public Person(){
    }

    // Constructor with parameters
    // Constructs a Person object with specified attributes.
    public Person(String name, String contactInfo, int id, int age, String gender, String address) {
        this.name = name;
        this.contactInformation = contactInfo;
        this.id = id;
        this.age = age;
        this.gender = gender;
        this.address = address;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for contactInformation
    public String getContactInformation() {
        return contactInformation;
    }

    // Setter for contactInformation
    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    // Getter for id
    public int getId() {
        return id;
    }

    // Setter for id
    public void setId(int id) {
        this.id = id;
    }

    // Getter for age
    public int getAge() {
        return age;
    }

    // Setter for age
    public void setAge(int age) {
        this.age = age;
    }

    // Getter for gender
    public String getGender() {
        return gender;
    }

    // Setter for gender
    public void setGender(String gender) {
        this.gender = gender;
    }

    // Getter for address
    public String getAddress() {
        return address;
    }
    
    // Setter for address
    public void setAddress(String address) {
        this.address = address;
    }
    
    // Setter for person_ID (This method is not supported and throws an UnsupportedOperationException)
    public void setPerson_ID(int personID) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
