package entity;

import java.io.Serializable;
import javax.persistence.*;
import static javax.persistence.CascadeType.*;

import java.util.Collection;
import java.util.ArrayList;

@Entity
public class ReservationClient implements Serializable {

    private int id;
    private String name;

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
