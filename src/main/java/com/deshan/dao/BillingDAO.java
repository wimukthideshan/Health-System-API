package com.deshan.dao;

import com.deshan.model.Billing;
import com.deshan.model.Patient;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

// Data Access Object (DAO) class for managing Billing entities.
public class BillingDAO {

    private static final Logger logger = Logger.getLogger(BillingDAO.class.getName());

    // List to store all billings
    private static List<Billing> billings = new ArrayList<>();

    // Retrieves all billings.
    public static List<Billing> getAllBillings() {
        try {
            return billings;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving all billings", e);
            throw new RuntimeException("Error occurred while retrieving billings", e);
        }
    }

    // Initializing billings
    static {
        try {
            Patient patient1 = new Patient("Tharaka", "1234567", 1234, 31, "male", "Good", "Critical");
            billings.add(new Billing(2, patient1, 2000.00, 1000.00, 1000.00));
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while initializing billings", e);
            throw new RuntimeException("Error occurred while initializing billings", e);
        }
    }

    // Retrieves a billing by ID.
    public static Billing getBillingById(int id) {
        try {
            return billings.stream()
                    .filter(b -> b.getId() == id)
                    .findFirst()
                    .orElse(null);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving billing with ID " + id, e);
            throw new RuntimeException("Error occurred while retrieving billing", e);
        }
    }

    // Adds a new billing.
    public static boolean addBilling(Billing billing) {
        try {
            return billings.add(billing);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while adding billing", e);
            throw new RuntimeException("Error occurred while adding billing", e);
        }
    }

    // Updates an existing billing.
    public static boolean updateBilling(Billing billing) {
        try {
            Billing existingBilling = getBillingById(billing.getId());
            if (existingBilling != null) {
                billings = billings.stream()
                        .map(b -> b.getId() == billing.getId() ? billing : b)
                        .collect(Collectors.toList());
                return true;
            }
            return false;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while updating billing with ID " + billing.getId(), e);
            throw new RuntimeException("Error occurred while updating billing", e);
        }
    }

    // Deletes a billing by ID.
    public static boolean deleteBilling(int id) {
        try {
            return billings.removeIf(b -> b.getId() == id);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while deleting billing with ID " + id, e);
            throw new RuntimeException("Error occurred while deleting billing", e);
        }
    }
}
