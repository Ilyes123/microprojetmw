package entity;

import entity.*;

import javax.persistence.*;
import java.util.ArrayList;

@Entity(name="MailBox")
public class MailBox extends Box implements IMailBox {
  public MailBox(int id, int usrId, String boxName){
    super(id, usrId, boxName);
  }
    
  public MailBox(){
    super();
  }
  public void deleteAMessage(int msgId) {
    ArrayList<Message> messages = new ArrayList<Message>(this.getMessages());
    Message messageToRemove = new Message();
    for (Message m : messages){
        if (msgId == m.getId()){
            messageToRemove = m;
        }
    }
    messages.remove(messageToRemove);
    this.setMessages(messages);
   
  }

  public void deleteReadMessages() {
    ArrayList<Message> messages = new ArrayList<Message>(this.getMessages());
    ArrayList<Message> messagesToRemove = new ArrayList<Message>();
    for (Message m : messages){
       if (m.getIsRead()){
          messagesToRemove.add(m);
       }
    }
    messages.removeAll(messagesToRemove);
    this.setMessages(messages);
  }

  public void deleteAllMessages() {
    messages.clear();
  }

  public void readNewMessages() {
    for(Message msg : messages) {
      if(!msg.getIsRead()){
        msg.setIsRead(true);
      }
    }
  }
}

