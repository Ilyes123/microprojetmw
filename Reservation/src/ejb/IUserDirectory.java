package ejb;

import javax.ejb.Remote;

import entity.*;
import java.util.Set;

@Remote public interface IUserDirectory {
    // peupler la base de données
    public void addUser(String userName, boolean rng, boolean wng);

    public void removeUser();

    public Set<MailUser> lookupAllUsers();

    public NewsGroupRight lookupAUserRights(String userName);
    
    public void updateAUserRights(String userName, boolean rng,boolean wng)

    public void clearDB();
}
