/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.deshan.resource;

import com.deshan.dao.MedicalRecordDAO;
import com.deshan.model.MedicalRecord;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

// Resource class for handling medical records
@Path("/medicalRecords") // Maps the resource to the /medicalRecords endpoint
@Produces(MediaType.APPLICATION_JSON) // Specifies that this resource produces JSON responses
@Consumes(MediaType.APPLICATION_JSON) // Specifies that this resource consumes JSON requests
public class MedicalRecordResource {

    private static final Logger logger = Logger.getLogger(MedicalRecordResource.class.getName());

    // Endpoint to retrieve all medical records
    @GET
    public Response getAllMedicalRecords() {
        try {
            logger.log(Level.INFO, "Retrieving all medical records."); // Logging retrieval of all medical records
            List<MedicalRecord> medicalRecords = MedicalRecordDAO.getAllMedicalRecords(); // Retrieving all medical records from DAO
            return Response.ok(medicalRecords).build(); // Returning OK response with medical records
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving all medical records", e); // Logging error
            throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error occurred while retrieving medical records: " + e.getMessage()).build()); // Throwing WebApplicationException with error message
        }
    }

    // Endpoint to retrieve a medical record by ID
    @GET
    @Path("/{id}")
    public Response getMedicalRecordById(@PathParam("id") int id) {
        try {
            logger.log(Level.INFO, "Retrieving medical record with ID: " + id); // Logging retrieval of medical record by ID
            MedicalRecord medicalRecord = MedicalRecordDAO.getMedicalRecordById(id); // Retrieving medical record by ID from DAO
            if (medicalRecord != null) {
                return Response.ok(medicalRecord).build(); // Returning OK response with medical record
            } else {
                logger.log(Level.WARNING, "Medical record not found with ID: " + id); // Logging warning for medical record not found
                throw new NotFoundException("Medical record not found with ID: " + id); // Throwing NotFoundException
            }
        } catch (NotFoundException e) {
            logger.log(Level.WARNING, "Medical record not found with ID: " + id, e); // Logging exception
            throw e;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving medical record with ID " + id, e); // Logging error
            throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error occurred while retrieving medical record: " + e.getMessage()).build()); // Throwing WebApplicationException with error message
        }
    }

    // Endpoint to add a new medical record
    @POST
    public Response addMedicalRecord(MedicalRecord medicalRecord) {
        try {
            logger.log(Level.INFO, "Adding new medical record"); // Logging addition of new medical record
            MedicalRecordDAO.addMedicalRecord(medicalRecord); // Adding medical record to DAO
            return Response.ok().build(); // Returning OK response
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while adding medical record", e); // Logging error
            throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error occurred while adding medical record: " + e.getMessage()).build()); // Throwing WebApplicationException with error message
        }
    }

    // Endpoint to update an existing medical record
    @PUT
    @Path("/{id}")
    public Response updateMedicalRecord(@PathParam("id") int id, MedicalRecord medicalRecord) {
        try {
            logger.log(Level.INFO, "Updating medical record with ID: " + id); // Logging updating of medical record by ID
            medicalRecord.setId(id); // Setting medical record ID
            MedicalRecordDAO.updateMedicalRecord(medicalRecord); // Updating medical record in DAO
            return Response.ok().build(); // Returning OK response
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while updating medical record with ID " + id, e); // Logging error
            throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error occurred while updating medical record: " + e.getMessage()).build()); // Throwing WebApplicationException with error message
        }
    }

    // Endpoint to delete a medical record by ID
    @DELETE
    @Path("/{id}")
    public Response deleteMedicalRecord(@PathParam("id") int id) {
        try {
            logger.log(Level.INFO, "Deleting medical record with ID: " + id); // Logging deletion of medical record by ID
            boolean deleted = MedicalRecordDAO.deleteMedicalRecord(id); // Deleting medical record by ID from DAO
            if (deleted) {
                return Response.ok().build(); // Returning OK response
            } else {
                logger.log(Level.WARNING, "Medical record not found with ID: " + id); // Logging warning for medical record not found
                throw new NotFoundException("Medical record not found with ID: " + id); // Throwing NotFoundException
            }
        } catch (NotFoundException e) {
            logger.log(Level.WARNING, "Medical record not found with ID: " + id, e); // Logging exception
            throw e;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while deleting medical record with ID " + id, e); // Logging error
            throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error occurred while deleting medical record: " + e.getMessage()).build()); // Throwing WebApplicationException with error message
        }
    }
}
