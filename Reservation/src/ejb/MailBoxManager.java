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

@Stateful(name="ejb/IMailBoxManager")
public class MailBoxManager implements IMailBoxManager {
    static int lastUserId=0;
    static int lastBoxId=0;
    @PersistenceContext(unitName="pu1")
    private EntityManager em;
    
    public int addMailBox(int userId, String boxName){
        lastBoxId++;
        Box box = new Box(lastBoxId, boxName);
        em.persist(box);
        return box.getId();
    }

    public void removeBox(int boxId){
        
        Box r = em.merge(findBox(boxId));

        // Delete records.
        em.remove(r);
    }
    private Box findBox(int boxId) {
        Query q = em.createQuery("select c from Box c where c.ID = :boxId");
        q.setParameter("boxId", boxId);
        return (Box)q.getSingleResult();
    }

    public List<Message> readAUserNewMessages(int userId) {
        Box b = em.merge(findBox(userId));
        ArrayList<Message> messages = box.getMessages();
        for (Message m : messages){
            if (m.getIsRead()){
                messages.remove(m);
            }
        }
        b.readUnreadMessages();
        return (List<Message>)messages;
    }

    public NewsGroupRight lookupAUserRights(int id){
        MailUser mailUser = findMailUser(id);
        return mailUser.getNewsGroupRight();
    }
   
    public void updateAUserRights(int id, boolean readNewsGroup, boolean writeNewsGroup){
        MailUser mailUser = findMailUser(id);
        NewsGroupRight newsGroupRight = mailUser.getNewsGroupRight();
        newsGroupRight.setReadNewsGroup(readNewsGroup);
        newsGroupRight.setWriteNewsGroup(writeNewsGroup);
        mailUser.setNewsGroupRight(newsGroupRight);
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
