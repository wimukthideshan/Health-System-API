/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.deshan.resource;

import com.deshan.dao.DoctorDAO;
import com.deshan.model.Doctor;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

// Resource class for handling doctors
@Path("/doctors") // Maps the resource to the /doctors endpoint
@Produces(MediaType.APPLICATION_JSON) // Specifies that this resource produces JSON responses
@Consumes(MediaType.APPLICATION_JSON) // Specifies that this resource consumes JSON requests
public class DoctorResource {

    private static final Logger logger = Logger.getLogger(DoctorResource.class.getName());

    // Endpoint to retrieve all doctors
    @GET
    public Response getAllDoctors() {
        try {
            logger.log(Level.INFO, "Retrieving all doctors."); // Logging retrieval of all doctors
            List<Doctor> doctors = DoctorDAO.getAllDoctors(); // Retrieving all doctors from DAO
            return Response.ok(doctors).build(); // Returning OK response with doctors
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving all doctors", e); // Logging error
            throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error occurred while retrieving doctors: " + e.getMessage()).build()); // Throwing WebApplicationException with error message
        }
    }

    // Endpoint to retrieve a doctor by ID
    @GET
    @Path("/{id}")
    public Response getDoctorById(@PathParam("id") int id) {
        try {
            logger.log(Level.INFO, "Retrieving doctor with ID: " + id); // Logging retrieval of doctor by ID
            Doctor doctor = DoctorDAO.getDoctorById(id); // Retrieving doctor by ID from DAO
            if (doctor != null) {
                return Response.ok(doctor).build(); // Returning OK response with doctor
            } else {
                logger.log(Level.WARNING, "Doctor not found with ID: " + id); // Logging warning for doctor not found
                throw new NotFoundException("Doctor not found with ID: " + id); // Throwing NotFoundException
            }
        } catch (NotFoundException e) {
            logger.log(Level.WARNING, "Doctor not found with ID: " + id, e); // Logging exception
            throw e;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving doctor with ID " + id, e); // Logging error
            throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error occurred while retrieving doctor: " + e.getMessage()).build()); // Throwing WebApplicationException with error message
        }
    }

    // Endpoint to add a new doctor
    @POST
    public Response addDoctor(Doctor doctor) {
        try {
            logger.log(Level.INFO, "Adding new doctor"); // Logging addition of new doctor
            DoctorDAO.addDoctor(doctor); // Adding doctor to DAO
            return Response.ok().build(); // Returning OK response
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while adding doctor", e); // Logging error
            throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error occurred while adding doctor: " + e.getMessage()).build()); // Throwing WebApplicationException with error message
        }
    }

    // Endpoint to update an existing doctor
    @PUT
    @Path("/{id}")
    public Response updateDoctor(@PathParam("id") int id, Doctor doctor) {
        try {
            logger.log(Level.INFO, "Updating doctor with ID: " + id); // Logging updating of doctor by ID
            doctor.setId(id); // Setting doctor ID
            DoctorDAO.updateDoctor(doctor); // Updating doctor in DAO
            return Response.ok().build(); // Returning OK response
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while updating doctor with ID " + id, e); // Logging error
            throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error occurred while updating doctor: " + e.getMessage()).build()); // Throwing WebApplicationException with error message
        }
    }

    // Endpoint to delete a doctor by ID
    @DELETE
    @Path("/{id}")
    public Response deleteDoctor(@PathParam("id") int id) {
        try {
            logger.log(Level.INFO, "Deleting doctor with ID: " + id); // Logging deletion of doctor by ID
            boolean deleted = DoctorDAO.deleteDoctor(id); // Deleting doctor by ID from DAO
            if (deleted) {
                return Response.ok().build(); // Returning OK response
            } else {
                logger.log(Level.WARNING, "Doctor not found with ID: " + id); // Logging warning for doctor not found
                throw new NotFoundException("Doctor not found with ID: " + id); // Throwing NotFoundException
            }
        } catch (NotFoundException e) {
            logger.log(Level.WARNING, "Doctor not found with ID: " + id, e); // Logging exception
            throw e;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while deleting doctor with ID " + id, e); // Logging error
            throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error occurred while deleting doctor: " + e.getMessage()).build()); // Throwing WebApplicationException with error message
        }
    }
}
