package entity;

import entity.*;

public interface IMailBox {
  void deleteAMessage(int messageId);
  void deleteReadMessages();
  void deleteAllMessages();
  void readNewMessages();
}
