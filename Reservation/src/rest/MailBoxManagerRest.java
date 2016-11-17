package rest;

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

import com.google.gson.*;

import javax.naming.InitialContext;
import javax.ejb.*;

import ejb.IMailBoxManager;

@Path("/mail_box")
public class MailBoxManagerRest {
    // Connect to the EJB webs
    private IMailBoxManager service(){
      IMailBoxManager mb = null;
      try {
        InitialContext ic = new InitialContext();
        mb = (IMailBoxManager) ic.lookup("ejb.IMailBoxManager");
      } catch (Exception e){
        e.printStackTrace();
      }
      return mb;
    }
    
    @POST
    @Path("/add")
    @Produces(MediaType.TEXT_PLAIN)
    public String addMailBox(@QueryParam("user_id") int userId, @QueryParam("box_name") String boxName){
      return (new Gson()).toJson(service().addMailBox(userId, boxName));
    }

    @DELETE
    @Path("/box/{boxId}")
    public void removeMailBox(@PathParam("boxId") int boxId){
      service().removeMailBox(boxId);
    }

    @GET
    @Path("/{user_id}/unread_messages")
    @Produces(MediaType.TEXT_PLAIN)
    public String readAUserNewMessages(@PathParam("user_id") int userId) {
        return (new Gson()).toJson(service().readAUserNewMessages(userId));
    }

    @GET
    @Path("/{user_id}/messages")
    @Produces(MediaType.TEXT_PLAIN)
    public String readAUserAllMessages(@PathParam("user_id") int userId){
        return (new Gson()).toJson(service().readAUserAllMessages(userId));
    }

    @DELETE
    @Path("/{user_id}/messages/{msg_id}")
    public void deleteAUserMessage(@PathParam("user_id") int userId, @PathParam("msg_id") int msgId){
      service().deleteAUserMessage(userId, msgId);
    }

    @DELETE
    @Path("/{user_id}/unread_messages")
    public void deleteAUserReadMessages(@PathParam("user_id") int userId) {
      service().deleteAUserReadMessages(userId);
    }

    @POST
    @Path("/send_news")
    public void sendNews( @QueryParam("user_name") String userName, @QueryParam("msg") Message msg){
      service().sendNews(userName, msg);
    }

}
