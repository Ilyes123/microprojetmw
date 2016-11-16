package entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.ArrayList;

@Entity
public class Message {
    private int id;
    private String senderName, receiverName, subject, body, sendingDate;
    private boolean alreadyRead;
    
    public Message(int id, String sndrName, String rcvName, String subject, String body, String sendingDate, boolean alreadyRead){
        this.id = id;
        this.senderName = sndrName;
        this.receiverName = rcvName;
        this.subject = subject;
        this.body = body;
        this.sendingDate = sendingDate;
        this.alreadyRead = alreadyRead;
    }
    
    public Message(){
    }
     
    @Id
    @Column(name="ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column
    public String getSenderName() {
        return this.senderName;
    }

    public void setSenderName(String u) {
        this.senderName = u;
    }
  
    @Column
    public String getReceiverName() {
        return this.receiverName;
    }

    public void setReceiverName(String u) {
        this.receiverName = u;
    }

    @Column
    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String u) {
        this.subject = u;
    }

    @Column
    public String getBody() {
        return this.body;
    }

    public void setBody(String u) {
        this.body = u;
    }

    @Column
    public boolean getIsRead() {
      return this.alreadyRead;
    }

    public void setIsRead(boolean isRead) {
      this.alreadyRead = isRead;
    }

    @Column
    public String getSendingDate() {
        return this.sendingDate;
    }

    public void setSendingDate(String u) {
        this.sendingDate = u;
    }
}
