/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package fr.ensimag.biblio.dao;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import fr.ensimag.biblio.converter.UserConverter;
import fr.ensimag.biblio.dao.impl.MongoDBUserDAO;
import fr.ensimag.biblio.models.User;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author Alexandre Rupp
 */
public interface UserDAO {
    
    // CRUD powwaaa !
    public User createUser(User user);
    
    public void updateUser(User user);
    
    public List<User> readAllUser();
    
    public void deleteUser(User user);
    
    public User readUser(User user);
}
