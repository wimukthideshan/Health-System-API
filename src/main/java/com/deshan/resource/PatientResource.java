/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.deshan.resource;

import com.deshan.dao.PatientDAO;
import com.deshan.model.Patient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.WebApplicationException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

// Resource class for handling patients
@Path("/patients") // Maps the resource to the /patients endpoint
@Produces(MediaType.APPLICATION_JSON) // Specifies that this resource produces JSON responses
@Consumes(MediaType.APPLICATION_JSON) // Specifies that this resource consumes JSON requests
public class PatientResource {

    private static final Logger logger = Logger.getLogger(PatientResource.class.getName());

    // Endpoint to retrieve all patients
    @GET
    public Response getAllPatients() {
        try {
            logger.log(Level.INFO, "Retrieving all patients."); // Logging retrieval of all patients
            List<Patient> patients = PatientDAO.getAllPatients(); // Retrieving all patients from DAO
            return Response.ok(patients).build(); // Returning OK response with patients
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving all patients", e); // Logging error
            throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error occurred while retrieving patients: " + e.getMessage()).build()); // Throwing WebApplicationException with error message
        }
    }

    // Endpoint to retrieve a patient by ID
    @GET
    @Path("/{id}")
    public Response getPatientById(@PathParam("id") int id) {
        try {
            logger.log(Level.INFO, "Retrieving patient with ID: " + id); // Logging retrieval of patient by ID
            Patient patient = PatientDAO.getPatientById(id); // Retrieving patient by ID from DAO
            if (patient != null) {
                return Response.ok(patient).build(); // Returning OK response with patient
            } else {
                logger.log(Level.WARNING, "Patient not found with ID: " + id); // Logging warning for patient not found
                throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                        .entity("Patient not found with ID: " + id).build()); // Throwing WebApplicationException with error message
            }
        } catch (NotFoundException e) {
            logger.log(Level.WARNING, "Patient not found with ID: " + id, e); // Logging exception
            throw e;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving patient with ID " + id, e); // Logging error
            throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error occurred while retrieving patient: " + e.getMessage()).build()); // Throwing WebApplicationException with error message
        }
    }

    // Endpoint to add a new patient
    @POST
    public Response addPatient(Patient patient) {
        try {
            logger.log(Level.INFO, "Adding new patient"); // Logging addition of new patient
            PatientDAO.addPatient(patient); // Adding patient to DAO
            return Response.ok().build(); // Returning OK response
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while adding patient", e); // Logging error
            throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error occurred while adding patient: " + e.getMessage()).build()); // Throwing WebApplicationException with error message
        }
    }

    // Endpoint to update an existing patient
    @PUT
    @Path("/{id}")
    public Response updatePatient(@PathParam("id") int id, Patient patient) {
        try {
            logger.log(Level.INFO, "Updating patient with ID: " + id); // Logging updating of patient by ID
            patient.setId(id); // Setting patient ID
            PatientDAO.updatePatient(patient); // Updating patient in DAO
            return Response.ok().build(); // Returning OK response
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while updating patient with ID " + id, e); // Logging error
            throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error occurred while updating patient: " + e.getMessage()).build()); // Throwing WebApplicationException with error message
        }
    }

    // Endpoint to delete a patient by ID
    @DELETE
    @Path("/{id}")
    public Response deletePatient(@PathParam("id") int id) {
        try {
            logger.log(Level.INFO, "Deleting patient with ID: " + id); // Logging deletion of patient by ID
            boolean deleted = PatientDAO.deletePatient(id); // Deleting patient by ID from DAO
            if (deleted) {
                return Response.ok().build(); // Returning OK response
            } else {
                logger.log(Level.WARNING, "Patient not found with ID: " + id); // Logging warning for patient not found
                throw new NotFoundException("Patient not found with ID: " + id); // Throwing NotFoundException
            }
        } catch (NotFoundException e) {
            logger.log(Level.WARNING, "Patient not found with ID: " + id, e); // Logging exception
            throw e;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while deleting patient with ID " + id, e); // Logging error
            throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error occurred while deleting patient: " + e.getMessage()).build()); // Throwing WebApplicationException with error message
        }
    }
}
