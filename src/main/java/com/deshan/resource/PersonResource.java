/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 *
*/

package com.deshan.resource;

import com.deshan.dao.PersonDAO;
import com.deshan.model.Person;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/persons")
public class PersonResource {

    private static final Logger logger = Logger.getLogger(PersonResource.class.getName());

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getAllPersons() {
        try {
            logger.log(Level.INFO, "Retrieving all persons.");
            List<Person> persons = PersonDAO.getAllPersons();
            logger.log(Level.INFO, "Retrieved all persons successfully.");
            return persons;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving all persons", e);
            throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error occurred while retrieving persons: " + e.getMessage()).build());
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonById(@PathParam("id") int id) {
        try {
            logger.log(Level.INFO, "Retrieving person with ID: " + id);
            Person person = PersonDAO.getPersonById(id);
            if (person != null) {
                logger.log(Level.INFO, "Retrieved person with ID: " + id);
                return Response.ok(person).build();
            } else {
                logger.log(Level.WARNING, "Person not found with ID: " + id);
                throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                        .entity("Person not found with ID: " + id).build());
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving person with ID " + id, e);
            throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error occurred while retrieving person: " + e.getMessage()).build());
        }
    }
    
    
    @POST
@Consumes(MediaType.APPLICATION_JSON)
public Response addPerson(Person person) {
    try {
        if (person == null || PersonDAO.getPersonById(person.getId()) != null) {
            logger.log(Level.WARNING, "Invalid person object or person with same ID already exists.");
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid person object or person with same ID already exists.").build());
        }
        boolean isAdded = PersonDAO.addPerson(person);
        if (isAdded) {
            logger.log(Level.INFO, "Added person with ID: " + person.getId());
            return Response.ok().build();
        } else {
            logger.log(Level.SEVERE, "Failed to add person with ID: " + person.getId());
            throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to add person with ID: " + person.getId()).build());
        }
    } catch (WebApplicationException e) {
        // Log the error with WARNING level
        logger.log(Level.WARNING, "Error occurred while adding person: " + e.getMessage(), e);
        // Return the exception's response to be displayed in Postman
        return e.getResponse();
    } catch (Exception e) {
        // Log the error with SEVERE level
        logger.log(Level.SEVERE, "Error occurred while adding person", e);
        // Throw a new WebApplicationException with INTERNAL_SERVER_ERROR status
        throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Error occurred while adding person: " + e.getMessage()).build());
    }
}

@PUT
@Path("/{id}")
@Consumes(MediaType.APPLICATION_JSON)
public Response updatePerson(@PathParam("id") int id, Person person) {
    try {
        person.setId(id);
        boolean isUpdated = PersonDAO.updatePerson(person);
        if (isUpdated) {
            logger.log(Level.INFO, "Updated person with ID: " + id);
            return Response.ok().build();
        } else {
            logger.log(Level.WARNING, "Person not found with ID: " + id);
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("Person not found with ID: " + id).build());
        }
    } catch (WebApplicationException e) {
        // Log the error with WARNING level
        logger.log(Level.WARNING, "Error occurred while updating person: " + e.getMessage(), e);
        // Return the exception's response to be displayed in Postman
        return e.getResponse();
    } catch (Exception e) {
        // Log the error with SEVERE level
        logger.log(Level.SEVERE, "Error occurred while updating person with ID " + id, e);
        // Throw a new WebApplicationException with INTERNAL_SERVER_ERROR status
        throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Error occurred while updating person: " + e.getMessage()).build());
    }
}


    @DELETE
    @Path("/{id}")
    public Response deletePerson(@PathParam("id") int id) {
        try {
            boolean isDeleted = PersonDAO.deletePerson(id);
            if (isDeleted) {
                logger.log(Level.INFO, "Deleted person with ID: " + id);
                return Response.ok().build();
            } else {
                logger.log(Level.WARNING, "Person not found with ID: " + id);
                throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                        .entity("Person not found with ID: " + id).build());
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while deleting person with ID " + id, e);
            throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error occurred while deleting person: " + e.getMessage()).build());
        }
    }
}


