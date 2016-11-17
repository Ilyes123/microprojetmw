package rest;

import java.io.IOException;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;

public class Publisher {
  static final String BASE_URI = "http://localhost:9876/mail/";

  public static void main(String[] args) {
    try {
      HttpServer server = HttpServerFactory.create(BASE_URI);
      server.start();
      System.out.println("Press Enter to stop server");
      System.in.read();
      System.out.println("Server stopped.");
      server.stop(0);
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
