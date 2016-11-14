package entity;

import javax.persistence.*;

@Entity
public class Show {
    private int id, emptySeats, totalSeats;
    private String showName, showroomName;
    
    @Id
    @Column(name="SHOW_ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="EMPTY_SEATS")
    public int getEmptySeats() {
        return emptySeats;
    }

    public void setEmptySeats(int emptySeats) {
        this.emptySeats = emptySeats;
    }

    @Column(name="TOTAL_SEATS")
    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    @Column(name="SHOW_NAME")
    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    @Column(name="SHOW_ROOM_NAME")
    public String getShowroomName() {
        return showroomName;
    }

    public void setShowroomName(String showroomName) {
        this.showroomName = showroomName;
    }
}
