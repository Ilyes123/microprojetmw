package ejb;

import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.util.Collection;
import java.util.List;

import entity.*;
import java.lang.Exception;

@Stateful(name="ejb/IUserDirectory")
public class UserDirectory implements IUserDirectory {
    static int lastUserId=0;

    @PersistenceContext(unitName="pu1")
    private EntityManager em;

    public void fill() {
    }
    
    public int addUser(String userName, boolean rng, boolean wng){
        lastUserId++;
        MailUser mu = new MailUser(lastUserId, userName, rng, wng);
        NewsGroupRight ngr = mu.getNewsGroupRight(); 
        em.persist(mu);
        em.persist(ngr);
        return mu.getId();
    }

    public void removeUser(int userId){
        
        MailUser r = em.merge(findMailUser(userId));

        // Delete records.
        em.remove(r);
    }
    private MailUser findMailUser(int userId) {
        Query q = em.createQuery("select c from MailUser c where c.ID = :userId");
        q.setParameter("userId", userId);
        return (MailUser)q.getSingleResult();
    }

    public List<MailUser> lookupAllUsers() {
        Query q = em.createQuery("select c from MailUser c");
        return (List<MailUser>)q.getResultList();
    }

    public NewsGroupRight lookupAUserRights(int id){
        MailUser mu = findMailUser(id);
        return mu.getNewsGroupRight();
    }
   
    public void updateAUserRights(int id, boolean rng, boolean wng){
        MailUser mu = findMailUser(id);
        NewsGroupRight ngr = mu.getNewsGroupRight();
        ngr.setReadNewsGroup(rng);
        ngr.setWriteNewsGroup(wng);
        mu.setNewsGroupRight(ngr);
    }
    
    public void clearDB() {
        MailUser r = em.merge(findMailUser(1));
        // Delete records.
        em.remove(r);
        r = em.merge(findMailUser(2));
        // Delete records.
        em.remove(r);
        r = em.merge(findMailUser(3));
        // Delete records.
        em.remove(r);

    }
}
