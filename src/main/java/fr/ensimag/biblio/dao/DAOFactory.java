/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package fr.ensimag.biblio.dao;

import fr.ensimag.biblio.controllers.SqlPlusContextListener;
import fr.ensimag.biblio.dao.impl.SqlPlusBookDAO;
import java.sql.Connection;
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
public class DAOFactory {
    private static final String DB_DATASRC_REF = "java:comp/env/jdbc/pool/MyAppDB";
    private DataSource dataSource;
    
    public DAOFactory(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public static DAOFactory getInstance(){
        DataSource dataSource = null;
        try {
            Context ctx = new InitialContext();
            dataSource = (DataSource)ctx.lookup(DB_DATASRC_REF);
        } catch (NamingException ex) {
            Logger.getLogger(SqlPlusContextListener.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new DAOFactory(dataSource);
    }
    
    public Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(DAOFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void closeConnection(Connection c) throws DAOException {
        if(c != null)
            try {
                c.close();
            } catch (SQLException ex) {
                throw new DAOException("Problème fermeture de connexion avec la BD ", ex);
            }
    }
    
    public BookDAO getBookDAO(){
        return new SqlPlusBookDAO(this);
    }
}
