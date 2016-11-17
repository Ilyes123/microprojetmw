package ejb;

import javax.ejb.Remote;

import entity.*;
import java.util.List;

@Remote public interface IUserDirectory {
    // peupler la base de données
    public int addUser(String userName, boolean rng, boolean wng);
    
    public void createNewsBox();

    public void removeUser(int userId);
    public void removeUser(String userName);

    public List<MailUser> lookupAllUsers();

    public NewsGroupRight lookupAUserRights(String userName);
    public NewsGroupRight lookupAUserRights(int userId);

    public void updateAUserRights(int id, boolean rng,boolean wng);
    
    public MailUser findMailUserByName(String userName);

    public void clearDB();
}
