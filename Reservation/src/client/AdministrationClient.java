package client;

import javax.naming.InitialContext;

import javax.ejb.*;

import java.util.Collection;
import java.util.List;

import ejb.Test;
import entity.*;

public class AdministrationClient {
	public static void main(String args[]) {
		Test sb;

        try {
			InitialContext ic = new InitialContext();
			sb = (Test) ic.lookup("ejb.IUserDirectory");

            int id1 = sb.addUser("User1", true, true);
            
            int id2 = sb.addUser("User2", true, false);

            int id3 = sb.addUser("User3", false, false);
			
            int id4 = sb.addUser("User4", false, false);
            
            // Remove user 4 
            sb.removeUser(id4);

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}

