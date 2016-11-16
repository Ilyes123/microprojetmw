package entity;

import entity.*;
import javax.persistence.*;

@Entity(name="NewsBox")
public class NewsBox extends Box {
  public NewsBox(int id, int usrId, String boxName){
    super(id, usrId, boxName);
  }
  public NewsBox(){
  }
}

