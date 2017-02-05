package org.huebner.frederic.complaintapp.backend.rest;

import org.huebner.frederic.complaintapp.backend.ejb.interfaces.ComplaintDAO;
import org.huebner.frederic.complaintapp.backend.entities.Complaint;
import org.huebner.frederic.complaintapp.backend.entities.ProcessingStatus;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("test")
public class TestDataGenerator {

    @EJB
    ComplaintDAO complaintDAO;

    /**
     * Creates test data
     * @return that data has been created
     */
    @GET
    @Path("createData")
    @Produces(MediaType.TEXT_PLAIN)
    public String createData() {
        for (int i = 0; i < 5; i++) {
            Complaint complaint = new Complaint();
            complaint.setName("Max Mustermann");
            complaint.setLocation("Musterstadt");
            complaint.setComplaintText("Lorem ipsum dolor sit amet, consetetur sadipscing elitr.");
            complaint.setProcessingStatus(ProcessingStatus.IN_PROCESS);
            complaintDAO.save(complaint);
        }
        return "Example data has been created";
    }
}
