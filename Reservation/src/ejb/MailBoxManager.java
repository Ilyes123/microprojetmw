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
        Box box = new Box(lastBoxId, userId, boxName);
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

    private Message findMessage(int msgId) {
        Query q = em.createQuery("select c from Message c where c.ID = :msgId");
        q.setParameter("msgId", msgId);
        return (Message)q.getSingleResult();
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

    public List<Messages> readAUserAllMessages(int userId){
        Box b = em.merge(findBox(userId));
        ArrayList<Message> messages = box.getMessages();
        b.readUnreadMessages();
        return (List<Message>)messages;
    }
   
    public void deleteAUserMessage(int userId, int msgId){
        Message m = em.merge(findMessage(msgId));
        em.remvoe(m);
    }

    public void deleteAUserReadMessages(int userId) {
        Box b = em.merge(findBox(userId));
        ArrayList<Message> messages = b.getMessages();
        for (Message m : messages){
            if (m.getIsRead()){
                messages.remove(m);
            }
        }
        b.setMessages(messages);
    }

    public int sendNews(String userName, Message msg){
        Query q = em.createQuery("select c from NewsBox c");
        
        NewsBox nb = em.merge((NewsBox)q.getSingleResult());

        nb.addMessage(String userName,msg);

    }

}
