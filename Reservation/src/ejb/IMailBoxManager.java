package ejb;

import javax.ejb.Remote;

import entity.*;
import java.util.List;

@Remote public interface IMailBoxManager {
    // peupler la base de données
    public List<Message> readAUserNewMessages(int userId);

    public List<Message> readAUserAllMessages(int userId);

    public void deleteAUserMessage(int userId, int msgId);

    public void deleteAUserReadMessages(int userId);
    
    public void sendAMessageToABox(Message msg, int userId, int boxId);

    public int addMailBox(int userId, String boxName);
    
    public void removeMailBox(int mailBoxId);

    public void sendNews(String senderName, Message msg);
}
