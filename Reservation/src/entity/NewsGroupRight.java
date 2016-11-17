package entity;

import javax.persistence.*;
import java.io.Serializable;
@Entity
public class NewsGroupRight implements Serializable{
    private int id;
    static private int lastId=0;
    private boolean readNewsGroup, writeNewsGroup;
    
    public NewsGroupRight(){
    }
       
    public NewsGroupRight(boolean rng, boolean wng){
        lastId++; 
        this.id = lastId;
        this.readNewsGroup = rng;
        this.writeNewsGroup = wng;
    }
    
    @Id
    @Column(name="ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="READ_Right")
    public boolean getReadNewsGroup() {
        return readNewsGroup;
    }

    public void setReadNewsGroup(boolean rng) {
        this.readNewsGroup = rng;
    }

    @Column(name="WRITE_Right")
    public boolean getWriteNewsGroup() {
        return writeNewsGroup;
    }

    public void setWriteNewsGroup(boolean wng) {
        this.writeNewsGroup = wng;
    }

}
