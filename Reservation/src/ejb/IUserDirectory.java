package ejb;

import javax.ejb.Remote;

import entity.*;
import java.util.List;

@Remote public interface IUserDirectory {
    // peupler la base de données
    public int addUser(String userName, boolean rng, boolean wng);

    public void removeUser(int userId);

    public List<MailUser> lookupAllUsers();

    public NewsGroupRight lookupAUserRights(int id);
    
    public void updateAUserRights(int id, boolean rng,boolean wng);

    public void clearDB();
}
