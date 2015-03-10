/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.biblio.controllers;

import com.mongodb.MongoClient;
import java.net.UnknownHostException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *
 * @author Alexandre Rupp
 */
@WebListener
public class MongoDBContextListener implements ServletContextListener {
 
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        MongoClient mongo = (MongoClient) sce.getServletContext()
                            .getAttribute("MONGO_CLIENT");
        mongo.close();
        System.out.println("MongoClient closed successfully");
    }
 
    @Override
    public void contextInitialized(ServletContextEvent servContextEvent) {
        try {
            ServletContext ctx = servContextEvent.getServletContext();
            MongoClient mongo = new MongoClient(
                    ctx.getInitParameter("MONGODB_HOST"),
                    Integer.parseInt(ctx.getInitParameter("MONGODB_PORT")));
            System.out.println("MongoClient initialized successfully");
            ctx.setAttribute("MONGO_CLIENT", mongo);
        } catch (UnknownHostException e) {
            throw new RuntimeException("MongoClient init failed");
        }
    }

 
}