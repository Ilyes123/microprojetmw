package entity;

import javax.persistence.*;

@Entity
public class NewsGroupRight {
    private int id;
    private boolean readNewsGroup, writeNewsGroup;
   
    public NewsGroupRight(int id,boolean rng, boolean wng){
        this.id = id;
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

    @Column(name="READ")
    public boolean getReadNewsGroup() {
        return readNewsGroup;
    }

    public void setReadNewsGroup(boolean rng) {
        this.readNewsGroup = rng;
    }

    @Column(name="WRITE")
    public boolean getWriteNewsGroup() {
        return writeNewsGroup;
    }

    public void setWriteNewsGroup(boolean wng) {
        this.writeNewsGroup = wng;
    }

}
