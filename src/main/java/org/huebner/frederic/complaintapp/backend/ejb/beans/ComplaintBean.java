package org.huebner.frederic.complaintapp.backend.ejb.beans;

import org.huebner.frederic.complaintapp.backend.ejb.interfaces.ComplaintDAO;
import org.huebner.frederic.complaintapp.backend.entities.Complaint;
import org.huebner.frederic.complaintapp.backend.exceptions.UpdateConflictException;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@Remote(ComplaintDAO.class)
public class ComplaintBean implements ComplaintDAO {

    @PersistenceContext
    private EntityManager em;

    public Complaint save(Complaint complaint) throws UpdateConflictException {
        try {
            if (complaint.getId() != null) {
                complaint = em.merge(complaint);
            }
            em.persist(complaint);
        } catch (OptimisticLockException e) {
            em.clear();
            throw new UpdateConflictException(e);
        }
        return complaint;
    }

    public Complaint update(Complaint complaint) {
        return null;
    }

    public void delete(long id) {
        Complaint toBeDeleted = em.find(Complaint.class, id);
        em.remove(toBeDeleted);
    }

    public Complaint getComplaint(long id) {
        return em.find(Complaint.class, id);
    }

    public List<Complaint> getAllComplaints() {
        return em.createQuery("SELECT c FROM Complaint c", Complaint.class).getResultList();
    }
}
