package ejb;

import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.util.Collection;
import java.util.List;

import entity.*;
import java.lang.Exception;

@Stateful(name="ejb/Test")
public class TestBean implements Test {
    static int lastId=0;

    @PersistenceContext(unitName="pu1")
    private EntityManager em;

    public void fill() {
        ReservationClient c = new ReservationClient();
        c.setName("client1") ;
        c.setId(1);
        em.persist(c);

        Show s = new Show();
        s.setId(1);
        s.setShowName("StarCraft");
        s.setShowroomName("Paris");
        s.setTotalSeats(200);
        s.setEmptySeats(200);
        em.persist(s);
    }
    
    public int makeReservation(String showroomName, String showName, String clientName, int seats) throws Exception{
        Show s = findShow(showroomName, showName);
        if(s.getEmptySeats() < seats) {
            throw new Exception("no seats available");
        }
        
        Reservation r = new Reservation();
        r.setId(lastId++);
        r.setShow(s);
        r.setClient(findClient(clientName));
        r.setSeats(seats);
        s.setEmptySeats(s.getEmptySeats() - seats);
        em.persist(s);
        em.persist(r);
        return r.getId();
    }

    public void cancelReservation(int resId){
        Reservation r = em.merge(findReservation(resId));

        // Delete records.
        em.remove(r);
    }
    public ReservationClient findClient(String name) {
        Query q = em.createQuery("select c from ReservationClient c where c.name = :name");
        q.setParameter("name", name);
        return (ReservationClient)q.getSingleResult();
    }

    public Show findShow(String showroomName, String showName) {
        Query q = em.createQuery("select c from Show c where c.show_room_Name = :showroomName and c.show_Name = :showName");
        q.setParameter("showroomName", showroomName);
        q.setParameter("showName", showName);
        return (Show)q.getSingleResult();
    }

    public Reservation findReservation(int resId) {
        Query q = em.createQuery("select c from Reservation c where c.reservation_id = :resId");
        q.setParameter("resId", resId);
        return (Reservation)q.getSingleResult();
    }
    
    public void clearDB() {
       ReservationClient c = findClient("client1");
       Show s = findShow("Paris", "StarCraft");
       c = em.merge(c);
       em.remove(c);
       s = em.merge(s);
       em.remove(c);
    }
}
