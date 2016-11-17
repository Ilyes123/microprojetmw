package client;

import javax.naming.InitialContext;

import javax.ejb.*;

import java.util.Collection;
import java.util.List;

import ejb.IMailBoxManager;
import entity.*;

public class MailBoxClient {
	public static void main(String args[]) {
		IMailBoxManager sb;
        int userId;
        String userName;

        try {
			InitialContext ic = new InitialContext();
			sb = (IMailBoxManager) ic.lookup("ejb.IMailBoxManager");
            
            userId = 1;
            userName = "User1"; 
            Message msg = new Message(1, userName, "User2", "Subject1", "The message is", "5/5/2015", false);
            System.out.println("\nUser " + userName + " sends: " + msg.getSubject() +" to " + msg.getReceiverName() +"\n");
            sb.sendAMessageToABox(msg, userName, 2);
            Message msg2 = new Message(2, userName, "All", "Subject2", "This is awesome", "5/5/2015", false);
            System.out.println("\nUser " + userName + " sends: " + msg.getSubject() +" to "  +"All\n");
            sb.sendNews(userName, msg2);

            Message msg3 = new Message(5, userName, "All", "Subject5", "explain yourself", "5/5/2015", false);
            System.out.println("\nUser " + userName + " sends: " + msg.getSubject() +" to "  +"All\n");
            sb.sendNews(userName, msg3);


            userId = 3;
            userName = "User3"; 
            msg = new Message(3, userName, "User2", "Subject3", "Not really", "6/5/2015", false);
            System.out.println("\nUser " + userName + " sends: " + msg.getSubject() +" to " + msg.getReceiverName() +"\n");

            sb.sendAMessageToABox(msg, userName, 2);
            msg = new Message(4, userName, "All", "Subject4", "Wanna play a game?", "5/5/2015", false);
            System.out.println("\nUser " + userName + " sends: " + msg.getSubject() +" to " + msg.getReceiverName() +"\n");
            sb.sendNews(userName, msg);


          
            userId = 2;
            userName = "User2";
System.out.println("\nUser 2 reads unread messages\n");

            List<Message> msgs = sb.readAUserNewMessages(userId);
            for (Message m : msgs){
                System.out.println("\nFrom: " + m.getSenderName() + "\n Subject: " + m.getSubject() + "\n Body: "+ m.getBody() + "\n");
            }

            
             
            System.out.println("\n delete read msgs for user 2\n"); 
            sb.deleteAUserReadMessages(2);
            
             
            System.out.println("\nUser 2 reads all messages\n");
        
            List<Message> msgs2 = sb.readAUserAllMessages(userId);
            for (Message m : msgs2){
                System.out.println("\nFrom: " + m.getSenderName() + "\n Subject: " + m.getSubject() + "\n Body: "+ m.getBody() + "\n");
            }

        
            userId = 3;
            userName = "User3"; 
            msg = new Message(6, userName, "User2", "Subject6", "No I don't", "6/5/2015", false);
            System.out.println("\nUser " + userName + " sends: " + msg.getSubject() +" to " + msg.getReceiverName() +"\n");

            sb.sendAMessageToABox(msg, userName, 2);

            userId = 2;
            userName = "User2";

            System.out.println("\n delete msg 6\n"); 

            sb.deleteAUserMessage(2,6); 
            System.out.println("\nUser " + userName + " reads unread msgs\n");
            
            List<Message> msgs3 = sb.readAUserNewMessages(userId);
            for (Message m : msgs3){
                System.out.println("\nFrom: " + m.getSenderName() + "\n Subject: " + m.getSubject() + "\n Body: "+ m.getBody() + "\n");
            }
            
            
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}

