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
   
   
    public void createNewsBox(){
        NewsBox nb = new NewsBox(0, 0, "NewsBox");
       
 
        em.persist(nb);
    }
    
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
        Query q = em.createQuery("select c from MailBox c where c.id = :boxId");
        q.setParameter("boxId", boxId);
        return (MailBox)q.getSingleResult();
    }

    private NewsBox findNewsBox() {
        Query q = em.createQuery("select c from NewsBox c where c.id = :boxId");
        q.setParameter("boxId", 0);
        return (NewsBox)q.getSingleResult();
    }


    private MailBox findMailBoxByUserId(int userId) {
        Query q = em.createQuery("select c from MailBox c where c.userId = :userId");
        q.setParameter("userId", userId);
        return (MailBox)q.getSingleResult();
    }


    private Message findMessage(int msgId) {
        Query q = em.createQuery("select c from Message c where c.id = :msgId");
        q.setParameter("msgId", msgId);
        return (Message)q.getSingleResult();
    }



    public List<Message> readAUserNewMessages(int userId) {
        Box b = em.merge(findMailBoxByUserId(userId));
        List<Message> messages = new ArrayList<Message>(b.getMessages());
        for (Message m : messages){
            if (m.getIsRead()){
                messages.remove(m);
            }
        }
        b.readUnreadMessages();
        try {
            InitialContext ic = new InitialContext();
            sb = (IUserDirectory) ic.lookup("ejb.IUserDirectory");
            
            NewsGroupRight ngr = sb.lookupAUserRights(userId);
            if(ngr.getReadNewsGroup()){ 
                Query q = em.createQuery("select c from NewsBox c where c.id=0");
        
                NewsBox nb = em.merge((NewsBox)q.getSingleResult());
                
                messages.addAll(nb.getMessages());
            }
            else {
                System.out.println("\n[+] user cant write in newsbox\n");
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return (List<Message>)messages;
    }

    public List<Message> readAUserAllMessages(int userId){
        Box b = em.merge(findMailBoxByUserId(userId));
        List<Message> messages = new ArrayList<Message>(b.getMessages());
        b.readUnreadMessages();
        try {
            InitialContext ic = new InitialContext();
            sb = (IUserDirectory) ic.lookup("ejb.IUserDirectory");
            
            NewsGroupRight ngr = sb.lookupAUserRights(userId);
            if(ngr.getReadNewsGroup()){ 
                Query q = em.createQuery("select c from NewsBox c where c.id=0");
        
                NewsBox nb = em.merge((NewsBox)q.getSingleResult());
                
                messages.addAll(nb.getMessages());
            }
            else {
                System.out.println("\n[+] user cant write in newsbox\n");
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return (List<Message>)messages;
    }
   
    public void deleteAUserMessage(int userId, int msgId){
        Message mtr = em.merge(findMessage(msgId));
        em.remove(mtr);
        MailBox mb = em.merge(findMailBoxByUserId(userId));
        mb.deleteAMessage(msgId); 
        em.flush();
    }

    public void deleteAUserReadMessages(int userId) {
        MailBox b = em.merge(findMailBoxByUserId(userId));
        b.deleteReadMessages(); 
        em.flush();
    }

    public void sendNews(String userName, Message msg){
        try {
            InitialContext ic = new InitialContext();
            sb = (IUserDirectory) ic.lookup("ejb.IUserDirectory");
            
            NewsGroupRight ngr = sb.lookupAUserRights(userName);
            if(ngr.getWriteNewsGroup()){ 
        
                NewsBox nb = em.merge(findNewsBox());
                //em.persist(msg);
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
        //em.persist(msg);

        b.addMessage(senderName, msg);

    }

}
