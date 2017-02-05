package org.huebner.frederic.complaintapp.backend.ejb.interfaces;

import org.huebner.frederic.complaintapp.backend.entities.Complaint;

import java.util.List;

public interface ComplaintDAO {

    /**
     * Saves a complaint entity to the database
     * @param complaint the complaint to persist
     * @return the persisted complaint
     */
    public Complaint save(Complaint complaint);

    /**
     *
     * @param id
     */
    public void delete(long id);

    /**
     *
     * @param id
     * @return
     */
    public Complaint getComplaint(long id);

    /**
     *
     * @return
     */
    public List<Complaint> getAllComplaints();
}
