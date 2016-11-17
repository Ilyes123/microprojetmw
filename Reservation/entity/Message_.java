package entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150916-rNA", date="2016-11-17T20:19:17")
@StaticMetamodel(Message.class)
public class Message_ { 

    public static volatile SingularAttribute<Message, String> senderName;
    public static volatile SingularAttribute<Message, String> subject;
    public static volatile SingularAttribute<Message, String> receiverName;
    public static volatile SingularAttribute<Message, Boolean> isRead;
    public static volatile SingularAttribute<Message, String> sendingDate;
    public static volatile SingularAttribute<Message, Integer> id;
    public static volatile SingularAttribute<Message, String> body;

}