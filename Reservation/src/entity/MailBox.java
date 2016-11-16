package entity;

import entity.*;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class NewsBox extends Box implements IMailBox {
  public void deleteAMessage(int msgId) {
    messages.remove(msg);
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

