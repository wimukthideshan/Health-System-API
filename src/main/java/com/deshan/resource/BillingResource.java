
package com.deshan.resource;

import com.deshan.dao.BillingDAO;
import com.deshan.model.Billing;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

// Resource class for handling billings
@Path("/billings") // Maps the resource to the /billings endpoint
@Produces(MediaType.APPLICATION_JSON) // Specifies that this resource produces JSON responses
@Consumes(MediaType.APPLICATION_JSON) // Specifies that this resource consumes JSON requests
public class BillingResource {

    private static final Logger logger = Logger.getLogger(BillingResource.class.getName());

    // Endpoint to retrieve all billings
    @GET
    public Response getAllBillings() {
        try {
            logger.log(Level.INFO, "Retrieving all billings."); // Logging retrieval of all billings
            List<Billing> billings = BillingDAO.getAllBillings(); // Retrieving all billings from DAO
            return Response.ok(billings).build(); // Returning OK response with billings
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving all billings", e); // Logging error
            throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error occurred while retrieving billings: " + e.getMessage()).build()); // Throwing WebApplicationException with error message
        }
    }

    // Endpoint to retrieve a billing by ID
    @GET
    @Path("/{id}")
    public Response getBillingById(@PathParam("id") int id) {
        try {
            logger.log(Level.INFO, "Retrieving billing with ID: " + id); // Logging retrieval of billing by ID
            Billing billing = BillingDAO.getBillingById(id); // Retrieving billing by ID from DAO
            if (billing != null) {
                return Response.ok(billing).build(); // Returning OK response with billing
            } else {
                logger.log(Level.WARNING, "Billing not found with ID: " + id); // Logging warning for billing not found
                throw new NotFoundException("Billing not found with ID: " + id); // Throwing NotFoundException
            }
        } catch (NotFoundException e) {
            logger.log(Level.WARNING, "Billing not found with ID: " + id, e); // Logging exception
            throw e;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving billing with ID " + id, e); // Logging error
            throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error occurred while retrieving billing: " + e.getMessage()).build()); // Throwing WebApplicationException with error message
        }
    }

    // Endpoint to add a new billing
    @POST
    public Response addBilling(Billing billing) {
        try {
            logger.log(Level.INFO, "Adding new billing"); // Logging addition of new billing
            BillingDAO.addBilling(billing); // Adding billing to DAO
            return Response.ok().build(); // Returning OK response
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while adding billing", e); // Logging error
            throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error occurred while adding billing: " + e.getMessage()).build()); // Throwing WebApplicationException with error message
        }
    }

    // Endpoint to update an existing billing
    @PUT
    @Path("/{id}")
    public Response updateBilling(@PathParam("id") int id, Billing billing) {
        try {
            logger.log(Level.INFO, "Updating billing with ID: " + id); // Logging updating of billing by ID
            billing.setId(id); // Setting billing ID
            BillingDAO.updateBilling(billing); // Updating billing in DAO
            return Response.ok().build(); // Returning OK response
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while updating billing with ID " + id, e); // Logging error
            throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error occurred while updating billing: " + e.getMessage()).build()); // Throwing WebApplicationException with error message
        }
    }

    // Endpoint to delete a billing by ID
    @DELETE
    @Path("/{id}")
    public Response deleteBilling(@PathParam("id") int id) {
        try {
            logger.log(Level.INFO, "Deleting billing with ID: " + id); // Logging deletion of billing by ID
            boolean deleted = BillingDAO.deleteBilling(id); // Deleting billing by ID from DAO
            if (deleted) {
                return Response.ok().build(); // Returning OK response
            } else {
                logger.log(Level.WARNING, "Billing not found with ID: " + id); // Logging warning for billing not found
                throw new NotFoundException("Billing not found with ID: " + id); // Throwing NotFoundException
            }
        } catch (NotFoundException e) {
            logger.log(Level.WARNING, "Billing not found with ID: " + id, e); // Logging exception
            throw e;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while deleting billing with ID " + id, e); // Logging error
            throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error occurred while deleting billing: " + e.getMessage()).build()); // Throwing WebApplicationException with error message
        }
    }
}
