package org.huebner.frederic.complaintapp.backend.ejb.interfaces;

import org.huebner.frederic.complaintapp.backend.entities.Complaint;

import java.util.List;

public interface ComplaintDAO {

    public Complaint save(Complaint complaint);

    public Complaint update(Complaint complaint);

    public void delete(long id);

    public Complaint getComplaint(long id);

    public List<Complaint> getAllComplaints();
}
