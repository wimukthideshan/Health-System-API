package com.deshan.response;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import java.util.logging.Level;
import java.util.logging.Logger;

// Class for handling responses and exceptions
public class ResponseHandler {

    private static final Logger logger = Logger.getLogger(ResponseHandler.class.getName());

    // Method to handle responses
    public static Response handleResponse(Response.Status status, Object entity) {
        try {
            String json = "{\"status\": \"" + status + "\", \"entity\": \"" + entity + "\"}";
            logger.log(Level.INFO, "Sending response with status: " + status.getStatusCode() + ", Entity: " + json);
            return Response.status(status).entity(json).type(MediaType.APPLICATION_JSON).build(); // Building response
        } catch (Exception e) {
            return handleException(e, Response.Status.INTERNAL_SERVER_ERROR); // Handling exception
        }
    }

    // Method to handle exceptions
    public static Response handleException(Exception e, Response.Status status) {
        logger.log(Level.SEVERE, "Error occurred: " + e.getMessage(), e); // Logging error
        return Response.status(status).entity("Error occurred: " + e.getMessage()).build(); // Building error response
    }

    // Method to handle WebApplicationExceptions
    public static Response handleWebApplicationException(WebApplicationException e) {
        logger.log(Level.WARNING, "WebApplicationException occurred: " + e.getMessage(), e); // Logging warning
        return e.getResponse(); // Returning response from the exception
    }
}
