package entity;

import javax.persistence.*;

@Entity
public class Reservation {
    private int id, seats;
    private Show show;
    private ReservationClient client;

    
    @Id
    @Column(name="RESERVATION_ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
 
    @Id
    @Column(name="SEATS")
    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }
        
    @ManyToOne()
    @JoinColumn(name="SHOW_ID")
    public Show getShow() {
        return show;
    }
    
    public void setShow(Show show) {
        this.show = show;
    }
 
    @ManyToOne()
    @JoinColumn(name="CLIENT_ID")
    public ReservationClient getClient() {
        return client;
    }

    public void setClient(ReservationClient client) {
        this.client = client;
    }
}
