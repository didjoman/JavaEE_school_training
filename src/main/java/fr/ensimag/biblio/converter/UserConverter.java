/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.biblio.converter;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import fr.ensimag.biblio.models.User;
import org.bson.types.ObjectId;

/**
 *
 * @author Alexandre Rupp
 */
public class UserConverter {

    // convert User Object to MongoDB DBObject
    // take special note of converting login String to ObjectId
    public static DBObject toDBObject(User user) {
        BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
                .append("lastName", user.getLastName())
                .append("firstName", user.getFirstName())
                .append("login", user.getLogin())
                .append("password", user.getPassword())
                .append("isMale", user.getIsMale())
                .append("age", user.getAge())
                .append("town", user.getTown());
        if (user.getId() != null)
            builder = builder.append("_id", new ObjectId(user.getId()));
        return builder.get();
    }
    
    // convert DBObject Object to User
    // take special note of converting ObjectId to String
    public static User toUser(DBObject doc) {
        User user = new User();
        user.setLastName((String) doc.get("lastName"));
        user.setFirstName((String) doc.get("firstName"));
        user.setLogin((String) doc.get("login"));
        user.setPassword((String) doc.get("password"));
        user.setIsMale((Boolean) doc.get("isMale"));
        user.setAge((String) doc.get("age"));
        user.setTown((String) doc.get("town"));
        // The id : 
        ObjectId id = (ObjectId) doc.get("_id");
        user.setId(id.toString());
        return user;
 
    }
    
}
