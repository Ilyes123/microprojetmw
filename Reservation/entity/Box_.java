package entity;

import entity.Message;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150916-rNA", date="2016-11-17T20:19:17")
@StaticMetamodel(Box.class)
public class Box_ { 

    public static volatile ListAttribute<Box, Message> messages;
    public static volatile SingularAttribute<Box, String> boxName;
    public static volatile SingularAttribute<Box, Integer> id;
    public static volatile SingularAttribute<Box, Integer> userId;

}