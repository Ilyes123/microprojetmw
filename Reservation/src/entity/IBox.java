package entity;

import entity.*;

public interface IBox {
  void readAllMessages();
  void readAMessage(int messageId);
  void addMessage(String userName, Message message);
}
