/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package fr.ensimag.biblio.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Alexandre Rupp
 */
public class OracleDB {
    private static final String DB_DATASRC_REF = "java:comp/env/jdbc/MyAppDB";
    private InitialContext  ctx;
    private DataSource datasource;
    private Connection conn;
    
    public OracleDB() {
        try {
            ctx = new InitialContext();
            datasource = (DataSource) ctx.lookup(DB_DATASRC_REF);
            // Definition of the Datasource in META-INF/context.xml
        } catch (NamingException ex) {
            Logger.getLogger(OracleDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    public Connection connect(){
        try {
            System.out.print("Connecting to the database... ");
            conn = datasource.getConnection();
            System.out.println("connected");
        } catch (SQLException e) {
            System.err.println("failed");
            e.printStackTrace(System.err);
        }
        return conn;
    }
    
    public void disconnect(){
        if(conn != null)
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(OracleDB.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
