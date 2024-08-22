package com.deshan.resource;

import com.deshan.dao.PrescriptionDAO;
import com.deshan.model.Prescription;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

// Resource class for handling prescriptions
@Path("/prescriptions") // Maps the resource to the /prescriptions endpoint
@Produces(MediaType.APPLICATION_JSON) // Specifies that this resource produces JSON responses
@Consumes(MediaType.APPLICATION_JSON) // Specifies that this resource consumes JSON requests
public class PrescriptionResource {

    private static final Logger LOGGER = Logger.getLogger(PrescriptionResource.class.getName());

    private final PrescriptionDAO prescriptionDAO;

    // Constructor initializing PrescriptionDAO
    public PrescriptionResource() {
        this.prescriptionDAO = new PrescriptionDAO();
    }

    // Endpoint to retrieve all prescriptions
    @GET
    public List<Prescription> getAllPrescriptions() {
        try {
            List<Prescription> prescriptions = prescriptionDAO.getAllPrescriptions();
            LOGGER.log(Level.INFO, "Retrieved all prescriptions successfully"); // Logging success
            return prescriptions;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to retrieve all prescriptions", e); // Logging error
            throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to retrieve all prescriptions: " + e.getMessage()).build()); // Throwing WebApplicationException with error message
        }
    }

    // Endpoint to retrieve a prescription by ID
    @GET
    @Path("/{id}")
    public Response getPrescriptionById(@PathParam("id") int id) {
        try {
            Prescription prescription = prescriptionDAO.getPrescriptionById(id);
            if (prescription != null) {
                LOGGER.log(Level.INFO, "Retrieved prescription with ID: " + id); // Logging success
                return Response.ok(prescription).build();
            } else {
                LOGGER.log(Level.WARNING, "Prescription not found with ID: " + id); // Logging warning
                throw new NotFoundException("Prescription not found with ID: " + id); // Throwing NotFoundException
            }
        } catch (NotFoundException e) {
            LOGGER.log(Level.WARNING, "Prescription not found with ID: " + id, e); // Logging exception
            throw e;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to retrieve prescription with ID: " + id, e); // Logging error
            throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to retrieve prescription with ID: " + id + ": " + e.getMessage()).build()); // Throwing WebApplicationException with error message
        }
    }

    // Endpoint to add a new prescription
    @POST
    public Response addPrescription(Prescription prescription) {
        try {
            prescriptionDAO.addPrescription(prescription);
            LOGGER.log(Level.INFO, "Added new prescription"); // Logging success
            return Response.ok().build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to add prescription", e); // Logging error
            throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to add prescription: " + e.getMessage()).build()); // Throwing WebApplicationException with error message
        }
    }

    // Endpoint to update a prescription
    @PUT
    @Path("/{id}")
    public Response updatePrescription(@PathParam("id") int id, Prescription updatedPrescription) {
        try {
            Prescription existingPrescription = prescriptionDAO.getPrescriptionById(id);
            if (existingPrescription != null) {
                updatedPrescription.setId(id);
                prescriptionDAO.updatePrescription(updatedPrescription);
                LOGGER.log(Level.INFO, "Updated prescription with ID: " + id); // Logging success
                return Response.ok().build();
            } else {
                LOGGER.log(Level.WARNING, "Prescription not found with ID: " + id); // Logging warning
                throw new NotFoundException("Prescription not found with ID: " + id); // Throwing NotFoundException
            }
        } catch (NotFoundException e) {
            LOGGER.log(Level.WARNING, "Prescription not found with ID: " + id, e); // Logging exception
            throw e;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to update prescription with ID: " + id, e); // Logging error
            throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to update prescription with ID: " + id + ": " + e.getMessage()).build()); // Throwing WebApplicationException with error message
        }
    }

    // Endpoint to delete a prescription
    @DELETE
    @Path("/{id}")
    public Response deletePrescription(@PathParam("id") int id) {
        try {
            Prescription existingPrescription = prescriptionDAO.getPrescriptionById(id);
            if (existingPrescription != null) {
                prescriptionDAO.deletePrescription(id);
                LOGGER.log(Level.INFO, "Deleted prescription with ID: " + id); // Logging success
                return Response.ok().build();
            } else {
                LOGGER.log(Level.WARNING, "Prescription not found with ID: " + id); // Logging warning
                throw new NotFoundException("Prescription not found with ID: " + id); // Throwing NotFoundException
            }
        } catch (NotFoundException e) {
            LOGGER.log(Level.WARNING, "Prescription not found with ID: " + id, e); // Logging exception
            throw e;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to delete prescription with ID: " + id, e); // Logging error
            throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to delete prescription with ID: " + id + ": " + e.getMessage()).build()); // Throwing WebApplicationException with error message
        }
    }
}
