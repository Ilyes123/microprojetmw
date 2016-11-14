package client;

import javax.naming.InitialContext;

import javax.ejb.*;

import java.util.Collection;
import java.util.List;

import ejb.Test;
import entity.*;

public class JavaReservationClient {
	public static void main(String args[]) {
		Test sb;

        try {
			InitialContext ic = new InitialContext();
			sb = (Test) ic.lookup("ejb.Test");

            int id = sb.makeReservation("Paris", "StarCraft", "client1", 5);

			// Remove entity
            sb.cancelReservation(id);

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}

