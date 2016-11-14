package entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.ArrayList;

@Entity
public class MailUser {
    private int id;
    private String userName;
    private NewsGroupRight newsGroupRight = new NewsGroupRight();
    
    public MailUser(int id, String userName, boolean rng, boolean wmg){
        this.id = id;
        this.userName = userName;
        this.NewsGroupRight.setId(id);
        this.NewsGroupRight.setReadNewsGroup(rng);
        this.NewsGroupRight.setWriteiNewsGroup(wng); 
    }
        
    @Id
    @Column(name="ID")
    public int getId() {
        return userName;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    @Column(name="USER_NAME")
    public int getUserName() {
        return userName;
    }

    public void setUserName(string userName) {
        this.userName = userName;
    }
        

    @JoinColumn(name="NEWS_GROUP_RIGHT")
    public NewsGroupRight getNewsGroupRight() {
        return newsGroupRight;
    }
    
    public void setNewsGroupRight(NewsGroupRight newsGroupRight) {
        this.newsGroupRight = newsGroupRight;
    }

}
