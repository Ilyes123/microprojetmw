# Project Description
**ProjectName** : Micro Projet Middleware

**Authors** : Dhia Eddine Chouchane & Elyes Youssef

**Academic Year** : 2016/2017

# Design Patterns

# Persistent Entities
We chose to persist the following entities: `MailUser`, `NewsGroupRight`, `MailBox`, `Box`, `NewsBox` and `Message`.

Follwing is a brief description of the persistent entities and their attributes.

## MailUser
### Attributes
* `int id`
* `String userName` 
* `NewsGroupRight newsGroupRight`: Read and write rights on the news group

### Public Methods
* `String getUserName()` Returns the user name
* `void setUserName(String userName)` Sets the user name to `userName`
* `NewsGroupRight getNewsGroupRight()` Returns the news group rights
* `void setNewsGroupRight(NewsGroupRight right)` Sets the read and write access to `right`

## NewsGroupRight
### Attributes
* `int id`
* `boolean readNewsGroup` : True if you have read access to the news box
* `boolean writeNewsGroup`: True if you have write access to the news box

### Public Methods
* `boolean getReadNewsGroup()` Returns the read access boolean
* `void setReadNewsGroup(boolean right)` Sets the read access to `right`
* `boolean getWriteNewsGroup()` Returns the write access boolean
* `void setWriteNewsGroup(boolean right)` Sets the write access to `right`

## MailBox
### Attributes
* `int id`
* `int userId`
* `String boxName`

### Public Methods
* `void deleteAMessage(int msgId)` Deletes the message by its ID
* `void deleteReadMessages()` Deletes all read messages in the box
* `void deleteAllMessages()` Deletes all messages in the box
* `void readNewsMessages()` Reads all new messages (marks them as read)

# WebService interfaces 
## Mail Box Manager interface 
```java
 List<Message> readAUserNewMessages(int userId);
```
Returns a list containing the user's (`userId`) new messages and mark them as read.

```java
 void createNewsBox();
```
Creates the news mail box.

```java
 List<Message> readAUserAllMessages(int userId);
```
Returns a list of the user defined by `userId` messages and mark them as read.

```java
 void deleteAUserMessage(int userId, int msgId);
```
Deletes the message defined by `msgId`.

```java
 void deleteAUserReadMessages(int userId);
```
Delete all read messages belonging the the user defined by `userId`.

```java
 void sendAMessageToABox(Message msg, String senderName, int boxId);
```
Sends a message `msg` from `senderName` to the box `boxId`.

```java
 int addMailBox(int userId, String boxName);
```
Creates a mail box named `boxName` that will belong to the user `userId`.

```java
 void removeMailBox(int mailBoxId);
```
Removes the mail box `mailBoxId`.

```java
 void sendNews(String senderName, Message msg);
```
Sends a news message `msg` from `senderName`.

## User Directory interface
```java
  int addUser(String userName, boolean readsNewsGroup, boolean writesNewsGroup);
```
Creates a user called `userName` having the rights to news box defined by `readsNewsGroup` and `writesNewsGroup`

It returns the new user's id

```java    
  void createNewsBox();
```
Creates the news box (actually calls the `createNewsBox()` method on the mailBoxManager.

```java    
  void removeUser(int userId);
```
Removes the user by its ID `userId`.

```java    
  void removeUser(String userName);
```
Removes the user by its name `userName`.

```java    
  List<MailUser> lookupAllUsers();
```
Returns a list of all users in the database.

```java    
  NewsGroupRight lookupAUserRights(String userName);
```
Returns the news group rights of user `userName`.

```java    
  void updateAUserRights(int userId, boolean readsNewsGroup, boolean writesNewsGroup);
```
Sets the user `userId` rights according to the two booleans provided as arguments.

```java    
  MailUser findMailUserByName(String userName);
```
Returns the user object (fetch it by its name `userName`)

```java    
  void clearDB();
```
Clears the DataBase.
 
# REST 

* The application's base URI is : `http://localhost:9876/mail`

We chose to implement the mail box manager in REST and deploy it to `/mail_box`

## The REST interface 
POST    `/add`  (query params : [`box_name`, `user_id`])  creates a new mail box 

DELETE  `/box/{box_id}`                                   removes a mail box

GET     `/{user_id}/unread_messages`                      returns all unread messages of user and marks them as read

GET     `/{user_id}/messages`                             returns all user's messages

DELETE  `/{user_id}/messages/{message_id}`                delete the user's message

DELETE  `/{user_id}/read_messages`                        delete's a users all read msgs

POST    `/send_news` (query params: [`user_name`, `msg`]) posts a new message to the news box

