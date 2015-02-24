/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package fr.ensimag.biblio.dao.impl;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import fr.ensimag.biblio.converter.UserConverter;
import fr.ensimag.biblio.dao.OracleDB;
import fr.ensimag.biblio.models.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bson.types.ObjectId;

/**
 *
 * @author Alexandre Rupp
 */
public class MongoDBUserDAO {
    private DBCollection col;
    
    public MongoDBUserDAO(MongoClient mongo) {
        this.col = mongo.getDB("MyWebAppDB").getCollection("Users");
    }
    
    public User createUser(User user){
        DBObject doc = UserConverter.toDBObject(user);
        this.col.insert(doc);
        // We get the id given by MongoDB
        ObjectId id = (ObjectId) doc.get("_id");
        user.setId(id.toString());
        return user;
    }
    
    public void updateUser(User user) {
        DBObject query = BasicDBObjectBuilder.start()
                .append("_id", new ObjectId(user.getId())).get();
        this.col.update(query, UserConverter.toDBObject(user));
    }
    
    public User readUser(User user) {
        DBObject query = BasicDBObjectBuilder.start()
                .append("_id", new ObjectId(user.getId())).get();
        DBObject data = this.col.findOne(query);
        return UserConverter.toUser(data);
    }
    
    public User readUser(String login, String password) {
        BasicDBObject query = new BasicDBObject();
        query.put("login", login);
        query.put("password", password);
        DBObject data = this.col.findOne(query);
        
        return (data == null) ? null : UserConverter.toUser(data);
    }
    
    public List<User> readAllUser() {
        List<User> data = new ArrayList<User>();
        DBCursor cursor = col.find();
        while (cursor.hasNext()) {
            DBObject doc = cursor.next();
            User user = UserConverter.toUser(doc);
            data.add(user);
        }
        return data;
    }
    
    public void deleteUser(User user) {
        DBObject query = BasicDBObjectBuilder.start()
                .append("_id", new ObjectId(user.getId())).get();
        this.col.remove(query);
    }
    
    public Map<String, Double>  getUsersBirthTownStatistics(){
        
        // *** Count total number of users :
        DBObject totalGroupFields = new BasicDBObject( "_id", "$town");
        
        // we use the $sum operator to increment the "count"
        // for each unique town
        totalGroupFields.put("count", new BasicDBObject( "$sum", 1));
        DBObject totalGroup = new BasicDBObject("$group", totalGroupFields );
        
        AggregationOutput totalOutput = col.aggregate(totalGroup);
        int total = Integer.parseInt(String.valueOf(totalOutput.results().iterator().next().get("count")));
        
        
        Map <String, Double> stats = new HashMap<String, Double>();
        
        // *** Count number of users for each town :
        DBObject groupFields = new BasicDBObject( "_id", "$town");
        
        // we use the $sum operator to increment the "count"
        // for each unique town
        groupFields.put("count", new BasicDBObject( "$sum", 1));
        DBObject group = new BasicDBObject("$group", groupFields );
        
        
        // You can add a sort to order by count descending
        DBObject sortFields = new BasicDBObject("count", -1);
        DBObject sort = new BasicDBObject("$sort", sortFields );
        
        
        AggregationOutput output = col.aggregate(group, sort);
        for (DBObject result : output.results()){
            String town = (String)result.get("_id");
            Double count = Double.parseDouble(String.valueOf(result.get("count"))) / total;
            stats.put(town, count);
            //System.out.println(stats.get(town));
        }
        return stats;
    }
    
}
