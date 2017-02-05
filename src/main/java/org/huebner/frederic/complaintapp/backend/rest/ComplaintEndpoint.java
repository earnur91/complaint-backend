package org.huebner.frederic.complaintapp.backend.rest;

import org.huebner.frederic.complaintapp.backend.ejb.interfaces.ComplaintDAO;
import org.huebner.frederic.complaintapp.backend.entities.Complaint;
import org.huebner.frederic.complaintapp.backend.exceptions.UpdateConflictException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkState;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.UriBuilder.fromUri;

@Path("/complaint")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ComplaintEndpoint {

    @Context
    private UriInfo uriInfo;

    @EJB
    private ComplaintDAO complaintDAO;

    @GET
    public List<Complaint> getAllComplaints() {
        return complaintDAO.getAllComplaints();
    }

    @GET
    @Path("{id}")
    public Response getComplaint(@PathParam("id") Long id) {
        Complaint complaint = complaintDAO.getComplaint(id);
        if (complaint == null) {
            return Response.status(NOT_FOUND).build();
        }
        return Response.ok(complaint).build();
    }

    @POST
    public Response createComplaint(Complaint complaint) {
        Complaint savedComplaint = complaintDAO.save(complaint);

        return Response.created(fromUri(uriInfo.getRequestUri()).path("{id}").build(complaint.getId())).entity(savedComplaint).build();
    }

    @PUT
    @Path("{id}")
    public Response updateComplaint(Complaint complaint, @PathParam("id") Long id) {
        if (complaint.getVersion() == null) {
            throw new WebApplicationException(BAD_REQUEST);
        }

        if (complaintDAO.getComplaint(id) == null) {
            return Response.status(NOT_FOUND).build();
        }

        if (complaint.getId() == null) {
            complaint.setId(id);
        } else {
            checkState(Objects.equals(id, complaint.getId()), "IDs don't match");
        }

        try {
            Complaint savedComplaint = complaintDAO.save(complaint);
            return Response.ok(savedComplaint).build();

        } catch (UpdateConflictException e) {
            return Response.status(Response.Status.CONFLICT).entity(complaintDAO.getComplaint(complaint.getId())).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteComplaint(@PathParam("id") Long id) {
        if (complaintDAO.getComplaint(id) == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        complaintDAO.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

}
