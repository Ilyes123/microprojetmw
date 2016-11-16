package ejb;

import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.naming.InitialContext;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

import entity.*;
import java.lang.Exception;

@Stateful(name="ejb/IMailBoxManager")
public class MailBoxManager implements IMailBoxManager {
    static int lastUserId=0;
    static int lastBoxId=0;
    @PersistenceContext(unitName="pu1")
    private EntityManager em;
    IUserDirectory sb;
    
    public int addMailBox(int userId, String boxName){
        lastBoxId++;
        MailBox box = new MailBox(lastBoxId, userId, boxName);
        em.persist(box);
        return box.getId();
    }

    public void removeMailBox(int boxId){
        
        Box r = em.merge(findMailBox(boxId));

        // Delete records.
        em.remove(r);
    }
    private MailBox findMailBox(int boxId) {
        Query q = em.createQuery("select * from MailBox m where m.ID = :boxId");
        q.setParameter("boxId", boxId);
        return (MailBox)q.getSingleResult();
    }

    private MailBox findMailBoxByUserId(int userId) {
        Query q = em.createQuery("select * from MailBox c where c.USER_ID = :userId");
        q.setParameter("userId", userId);
        return (MailBox)q.getSingleResult();
    }


    private Message findMessage(int msgId) {
        Query q = em.createQuery("select * from Message c where c.ID = :msgId");
        q.setParameter("msgId", msgId);
        return (Message)q.getSingleResult();
    }



    public List<Message> readAUserNewMessages(int userId) {
        Box b = em.merge(findMailBox(userId));
        ArrayList<Message> messages = b.getMessages();
        for (Message m : messages){
            if (m.getIsRead()){
                messages.remove(m);
            }
        }
        b.readUnreadMessages();
        return (List<Message>)messages;
    }

    public List<Message> readAUserAllMessages(int userId){
        Box b = em.merge(findMailBoxByUserId(userId));
        ArrayList<Message> messages = b.getMessages();
        b.readUnreadMessages();
        return (List<Message>)messages;
    }
   
    public void deleteAUserMessage(int userId, int msgId){
        Message m = em.merge(findMessage(msgId));
        em.remove(m);
    }

    public void deleteAUserReadMessages(int userId) {
        Box b = em.merge(findMailBoxByUserId(userId));
        ArrayList<Message> messages = b.getMessages();
        for (Message m : messages){
            if (m.getIsRead()){
                messages.remove(m);
            }
        }
        b.setMessages(messages);
    }

    public void sendNews(String userName, Message msg){
        try {
            InitialContext ic = new InitialContext();
            sb = (IUserDirectory) ic.lookup("ejb.IUserDirectory");
            
            NewsGroupRight ngr = sb.lookupAUserRights(userName);
            if(ngr.getWriteNewsGroup()){ 
                Query q = em.createQuery("select * from NewsBox c");
        
                NewsBox nb = em.merge((NewsBox)q.getSingleResult());

                nb.addMessage(userName,msg);
            }
            else {
                System.out.println("\n[+] user cant write in newsbox\n");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void sendAMessageToABox(Message msg, String senderName, int boxId){
        MailBox b = findMailBox(boxId);
        em.merge(b);
        em.persist(msg);

        b.addMessage(senderName, msg);

    }

}
