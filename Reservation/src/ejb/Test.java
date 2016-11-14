package ejb;

import javax.ejb.Remote;

import entity.*;

@Remote public interface Test {
    // peupler la base de données
    public void fill();

    public int makeReservation(String showroomName, String showName, String clientName, int seats) throws Exception;

    public void cancelReservation(int resId);

    public ReservationClient findClient(String name) ;
    
    public Show findShow(String showroomName, String showName);
    
    public Reservation findReservation(int resId);

    public void clearDB();
}
