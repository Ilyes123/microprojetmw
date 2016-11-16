package entity;

import entity.*;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class MailBox extends Box implements IMailBox {
  public MailBox(int id, int usrId, String boxName){
    super(id, usrId, boxName);
  }

  public void deleteAMessage(int msgId) {
    for(Message msg : messages) {
      if(msg.getId() == msgId){
        messages.remove(msg);
        break;
      }
    }
  }

  public void deleteReadMessages() {
    for(Message msg : messages) {
      if (msg.getIsRead()) {
        messages.remove(msg);
      }
    }
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

