package ejb;

import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.naming.InitialContext;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.util.Collection;
import java.util.List;

import entity.*;
import java.lang.Exception;

@Stateful(name="ejb/IUserDirectory")
public class UserDirectory implements IUserDirectory {
    static int lastUserId=0;
    private IMailBoxManager sb;
    @PersistenceContext(unitName="pu1")
    private EntityManager em;

    public void createNewsBox(){
        try {
            InitialContext ic = new InitialContext();
            sb = (IMailBoxManager) ic.lookup("ejb.IMailBoxManager");

            sb.createNewsBox();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    
    public int addUser(String userName, boolean readNewsGroup, boolean writeNewsGroup){
        lastUserId++;
        MailUser mailUser = new MailUser(lastUserId, userName, readNewsGroup, writeNewsGroup);
        NewsGroupRight newsGroupRight = mailUser.getNewsGroupRight(); 
        em.persist(newsGroupRight);
        em.persist(mailUser);
        try {
            InitialContext ic = new InitialContext();
            sb = (IMailBoxManager) ic.lookup("ejb.IMailBoxManager");

            sb.addMailBox(mailUser.getId(), userName + "Box");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return mailUser.getId();
    }

    public void removeUser(int userId){
        
        MailUser r = em.merge(findMailUser(userId));

        // Delete records.
        em.remove(r);
    }
    private MailUser findMailUser(int userId) {
        Query q = em.createQuery("select c from MailUser c where c.id = :userId");
        q.setParameter("userId", userId);
        return (MailUser)q.getSingleResult();
    }

    public List<MailUser> lookupAllUsers() {
        Query q = em.createQuery("select c from MailUser c");
        return (List<MailUser>)q.getResultList();
    }

    public NewsGroupRight lookupAUserRights(String userName){
        MailUser mailUser = findMailUserByName(userName);
        return mailUser.getNewsGroupRight();
    }
    
    public NewsGroupRight lookupAUserRights(int userId){
        MailUser mailUser = findMailUser(userId);
        return mailUser.getNewsGroupRight();
    }
     
    public void updateAUserRights(int id, boolean readNewsGroup, boolean writeNewsGroup){
        MailUser mailUser = findMailUser(id);
        NewsGroupRight newsGroupRight = mailUser.getNewsGroupRight();
        newsGroupRight.setReadNewsGroup(readNewsGroup);
        newsGroupRight.setWriteNewsGroup(writeNewsGroup);
        mailUser.setNewsGroupRight(newsGroupRight);
    }
   
    public MailUser findMailUserByName(String userName){
        Query q = em.createQuery("select c from MailUser c where c.userName = :userName");
        q.setParameter("userName", userName);
        return (MailUser)q.getSingleResult();
    }
    
    public void removeUser(String userName){
        MailUser mu = em.merge(findMailUserByName(userName));
        em.remove(mu);
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
