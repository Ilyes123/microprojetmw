package entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.ArrayList;

@Entity
public class Box implements IBox {
    protected int id;
    protected String boxName;
    protected ArrayList<Message> messages;
    
    public Box (int id, String boxName){
        this.messages = new
        this.id = id;
        this.boxName = boxName
    }
     
    @Id
    @Column(name="ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    @Column(name="BOX_NAME")
    public String getBoxName() {
        return this.boxName;
    }

    public void setBoxName(String u) {
        this.boxName = u;
    }

    @OneToMany
    @JoinColumn(name="MAIL_BOX_ID", nullable=true)
    public ArrayList<Message> getMessages() {
      return messages;
    }

    public void setMessages(ArrayList messages){
      this.messages = messages;
    }

    public void readAllMessages(){
      for(Message m : messages){
        m.setIsRead(true);
      }
    }

    public void readAMessage(int messageId){
      for(Message m : messages){
        if (m.getId() == messageId){
          m.setIsRead(true);
        }
      }
    }

    public void addMessage(Message message){
      messages.add(message);
    }
}
