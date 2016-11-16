package ejb;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE ;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

import entity.*;
import java.lang.Exception;

@Path("/mail_box")
public class MailBoxManagerRest {
    @PersistenceContext(unitName="pu1")
    private EntityManager em;

    private int nextBoxId() {
      Query q = em.createQuery("select max(id) from Box");
      return (int)q.getSingleResult()+1;
    }

    private int nextUserId() {
      Query q = em.createQuery("select max(id) from MailUser");
      return (int)q.getSingleResult()+1;
    }
    
    @POST
    @Path("/add")
    @Produces(MediaType.TEXT_PLAIN)
    public int addMailBox(@QueryParam("user_id") int userId, @QueryParam("box_name") String boxName){
        Box box = new Box(nextBoxId(), userId, boxName);
        em.persist(box);
        return box.getId();
    }

    @DELETE
    @Path("/box/{boxId}")
    public void removeBox(@PathParam("boxId") int boxId){
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

    @GET
    @Path("/{user_id}/unread_messages")
    @Produces(MediaType.TEXT_PLAIN)
    public List<Message> readAUserNewMessages(@PathParam("user_id") int userId) {
        Box b = em.merge(findBox(userId));
        ArrayList<Message> messages = new ArrayList<Message>();
        for (Message m : messages){
            if (!m.getIsRead()){
                messages.add(m);
            }
        }
        b.readUnreadMessages();
        return (List<Message>)messages;
    }

    @GET
    @Path("/{user_id}/messages")
    @Produces(MediaType.TEXT_PLAIN)
    public List<Message> readAUserAllMessages(@PathParam("user_id") int userId){
        Box b = em.merge(findBox(userId));
        ArrayList<Message> messages = b.getMessages();
        b.readUnreadMessages();
        return (List<Message>)messages;
    }

    @DELETE
    @Path("/{user_id}/messages/{msg_id}")
    public void deleteAUserMessage(@PathParam("user_id") int userId, @PathParam("msg_id") int msgId){
        Message m = em.merge(findMessage(msgId));
        em.remove(m);
    }

    @DELETE
    @Path("/{user_id}/unread_messages")
    public void deleteAUserReadMessages(@PathParam("user_id") int userId) {
        Box b = em.merge(findBox(userId));
        ArrayList<Message> messages = b.getMessages();
        for (Message m : messages){
            if (m.getIsRead()){
                messages.remove(m);
            }
        }
        b.setMessages(messages);
    }

    @POST
    @Path("/send_news")
    @Produces(MediaType.TEXT_PLAIN)
    public int sendNews( @QueryParam("user_nae") String userName, @QueryParam("msg") Message msg){
        Query q = em.createQuery("select c from NewsBox c");
        
        NewsBox nb = em.merge((NewsBox)q.getSingleResult());

        nb.addMessage(userName,msg);
        return 1;
    }

}
