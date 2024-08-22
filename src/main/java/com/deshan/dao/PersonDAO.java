package com.deshan.dao;

import com.deshan.model.Doctor;
import com.deshan.model.Patient;
import com.deshan.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

// Data Access Object (DAO) class for managing Person entities.
public class PersonDAO {
    private static final Logger logger = Logger.getLogger(PersonDAO.class.getName());

    // List to store all persons
    private static List<Person> persons = new ArrayList<>();

    // Retrieves all persons.
    public static List<Person> getAllPersons() {
        try {
            logger.log(Level.INFO, "Retrieving all persons.");
            return persons;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving all persons", e);
            throw new RuntimeException("Error occurred while retrieving persons", e);
        }
    }

    // Initializing persons
    static {
        try {
            persons.add(new Patient("Tharaka", "1234567", 1234, 31, "male", "colombo", "Critical"));
            persons.add(new Patient("Sayuri", "1234557", 123, 23, "female", "colombo", "Good"));
            persons.add(new Patient("Mayuri", "12344567", 12, 30, "female", "colombo", "Good"));
            persons.add(new Patient("Sasmitha", "1234gr567", 23, 31, "male", "colombo", "Need Inspections"));
            persons.add(new Doctor("deshan", "0786534234", 1, 31, "male", "colombo", "cardiac"));
            persons.add(new Doctor("John", "078589625", 2, 33, "male", "colombo", "neuro"));
            persons.add(new Doctor("Hiruni", "070248623", 3, 31, "female", "colombo", "ENT"));
            persons.add(new Doctor("Ashely", "071584392", 4, 21, "female", "colombo", "Cancer"));
            logger.log(Level.INFO, "Initialized persons.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while initializing persons", e);
            throw new RuntimeException("Error occurred while initializing persons", e);
        }
    }

    // Retrieves a person by ID.
    public static Person getPersonById(int id) {
        try {
            logger.log(Level.INFO, "Retrieving person with ID: " + id);
            return persons.stream()
                    .filter(p -> p.getId() == id)
                    .findFirst()
                    .orElse(null);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving person with ID " + id, e);
            throw new RuntimeException("Error occurred while retrieving person", e);
        }
    }

    // Adds a new person.
    public static boolean addPerson(Person person) {
        try {
            boolean isAdded = persons.add(person);
            if (isAdded) {
                logger.log(Level.INFO, "Added person with ID: " + person.getId());
            }
            return isAdded;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while adding person", e);
            throw new RuntimeException("Error occurred while adding person", e);
        }
    }

    // Updates an existing person.
    public static boolean updatePerson(Person person) {
        try {
            Person existingPerson = getPersonById(person.getId());
            if (existingPerson != null) {
                persons = persons.stream()
                        .map(p -> p.getId() == person.getId() ? person : p)
                        .collect(Collectors.toList());
                logger.log(Level.INFO, "Updated person with ID: " + person.getId());
                return true;
            }
            logger.log(Level.INFO, "Person with ID " + person.getId() + " not found for updating.");
            return false;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while updating person with ID " + person.getId(), e);
            throw new RuntimeException("Error occurred while updating person", e);
        }
    }

    // Deletes a person by ID.
    public static boolean deletePerson(int id) {
        try {
            boolean isDeleted = persons.removeIf(p -> p.getId() == id);
            if (isDeleted) {
                logger.log(Level.INFO, "Deleted person with ID: " + id);
            } else {
                logger.log(Level.INFO, "Person with ID " + id + " not found for deletion.");
            }
            return isDeleted;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while deleting person with ID " + id, e);
            throw new RuntimeException("Error occurred while deleting person", e);
        }
    }
}
