package entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.ArrayList;

@Entity
public class MailUser {
    private int id;
    private String userName;
    private NewsGroupRight newsGroupRight;
    
    public MailUser(){
    }
    
    public MailUser(int id, String userName, boolean rng, boolean wng){
        this.id = id;
        this.userName = userName;
        this.newsGroupRight = new NewsGroupRight(rng, wng);
    }
     
    @Id
    @Column(name="MAIL_USER_ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

     
    @Column(name="USER_NAME")
    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String u) {
        this.userName = u;
    }
        
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="NEWS_GROUP_RIGHT")
    public NewsGroupRight getNewsGroupRight() {
        return newsGroupRight;
    }
    
    public void setNewsGroupRight(NewsGroupRight newsGroupRight) {
        this.newsGroupRight = newsGroupRight;
    }

}
