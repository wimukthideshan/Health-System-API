
package com.deshan.resource;

import com.deshan.dao.AppointmentDAO;
import com.deshan.model.Appointment;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

// Resource class for handling appointments
@Path("/appointments") // Maps the resource to the /appointments endpoint
@Produces(MediaType.APPLICATION_JSON) // Specifies that this resource produces JSON responses
@Consumes(MediaType.APPLICATION_JSON) // Specifies that this resource consumes JSON requests
public class AppointmentResource {

    private static final Logger logger = Logger.getLogger(AppointmentResource.class.getName());

    // Endpoint to retrieve all appointments
    @GET
    public Response getAllAppointments() {
        try {
            logger.log(Level.INFO, "Retrieving all appointments."); // Logging retrieval of all appointments
            List<Appointment> appointments = AppointmentDAO.getAllAppointments(); // Retrieving all appointments from DAO
            return Response.ok(appointments).build(); // Returning OK response with appointments
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving all appointments", e); // Logging error
            throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error occurred while retrieving appointments: " + e.getMessage()).build()); // Throwing WebApplicationException with error message
        }
    }

    // Endpoint to retrieve an appointment by ID
    @GET
    @Path("/{id}")
    public Response getAppointmentById(@PathParam("id") int id) {
        try {
            logger.log(Level.INFO, "Retrieving appointment with ID: " + id); // Logging retrieval of appointment by ID
            Appointment appointment = AppointmentDAO.getAppointmentById(id); // Retrieving appointment by ID from DAO
            if (appointment != null) {
                return Response.ok(appointment).build(); // Returning OK response with appointment
            } else {
                logger.log(Level.WARNING, "Appointment not found with ID: " + id); // Logging warning for appointment not found
                throw new NotFoundException("Appointment not found with ID: " + id); // Throwing NotFoundException
            }
        } catch (NotFoundException e) {
            logger.log(Level.WARNING, "Appointment not found with ID: " + id, e); // Logging exception
            throw e;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving appointment with ID " + id, e); // Logging error
            throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error occurred while retrieving appointment: " + e.getMessage()).build()); // Throwing WebApplicationException with error message
        }
    }

    // Endpoint to add a new appointment
    @POST
    public Response addAppointment(Appointment appointment) {
        try {
            logger.log(Level.INFO, "Adding new appointment"); // Logging addition of new appointment
            AppointmentDAO.addAppointment(appointment); // Adding appointment to DAO
            return Response.ok().build(); // Returning OK response
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while adding appointment", e); // Logging error
            throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error occurred while adding appointment: " + e.getMessage()).build()); // Throwing WebApplicationException with error message
        }
    }

    // Endpoint to update an existing appointment
    @PUT
    @Path("/{id}")
    public Response updateAppointment(@PathParam("id") int id, Appointment appointment) {
        try {
            logger.log(Level.INFO, "Updating appointment with ID: " + id); // Logging updating of appointment by ID
            appointment.setId(id); // Setting appointment ID
            AppointmentDAO.updateAppointment(appointment); // Updating appointment in DAO
            return Response.ok().build(); // Returning OK response
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while updating appointment with ID " + id, e); // Logging error
            throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error occurred while updating appointment: " + e.getMessage()).build()); // Throwing WebApplicationException with error message
        }
    }

    // Endpoint to delete an appointment by ID
    @DELETE
    @Path("/{id}")
    public Response deleteAppointment(@PathParam("id") int id) {
        try {
            logger.log(Level.INFO, "Deleting appointment with ID: " + id); // Logging deletion of appointment by ID
            boolean deleted = AppointmentDAO.deleteAppointment(id); // Deleting appointment by ID from DAO
            if (deleted) {
                return Response.ok().build(); // Returning OK response
            } else {
                logger.log(Level.WARNING, "Appointment not found with ID: " + id); // Logging warning for appointment not found
                throw new NotFoundException("Appointment not found with ID: " + id); // Throwing NotFoundException
            }
        } catch (NotFoundException e) {
            logger.log(Level.WARNING, "Appointment not found with ID: " + id, e); // Logging exception
            throw e;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while deleting appointment with ID " + id, e); // Logging error
            throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error occurred while deleting appointment: " + e.getMessage()).build()); // Throwing WebApplicationException with error message
        }
    }
}

