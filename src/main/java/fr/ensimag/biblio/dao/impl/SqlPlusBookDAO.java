/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package fr.ensimag.biblio.dao.impl;

import fr.ensimag.biblio.controllers.AddUserServlet;
import fr.ensimag.biblio.controllers.CheckUserServlet;
import fr.ensimag.biblio.dao.BookDAO;
import fr.ensimag.biblio.dao.DAOException;
import fr.ensimag.biblio.dao.DAOFactory;
import fr.ensimag.biblio.models.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alexandre Rupp
 */
public class SqlPlusBookDAO implements BookDAO{
    private DAOFactory daoFactory;
    
    public SqlPlusBookDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
    public void createBook(String author, String title) throws DAOException {
        Connection connec = daoFactory.getConnection();
        
        PreparedStatement createBookPrep = null;
        String createBookQuery = "INSERT INTO BIBLIOGRAPHIE(id, auteur, titre) VALUES(id_seq.nextval,?,?)";
        try {
            createBookPrep = connec.prepareStatement(createBookQuery);
            createBookPrep.setString(1, author);
            createBookPrep.setString(2, title);
            createBookPrep.executeUpdate();
        } catch (SQLException ex) {
            throw new DAOException("Erreur BD " + ex.getMessage(), ex);
        } finally {
            try {
                if(createBookPrep != null)
                    createBookPrep.close();
                daoFactory.closeConnection(connec);
            } catch (SQLException ex) {
                throw new DAOException("Erreur BD " + ex.getMessage(), ex);
            }
        }
    }
    
    public List<Book> readAllBook() throws DAOException {
        Connection connec = daoFactory.getConnection();
        
        List<Book> books = new ArrayList<Book>();
        PreparedStatement selectBooksPrep = null;
        String selectBooksQuery = "SELECT * FROM BIBLIOGRAPHIE";
        ResultSet rs = null;
        try {
            selectBooksPrep = connec.prepareStatement(selectBooksQuery);
            rs = selectBooksPrep.executeQuery();
            
            while(rs.next()){
                Book book = new Book(rs.getInt("id"),
                        rs.getString("auteur"),
                        rs.getString("titre"));
                books.add(book);
            }
        } catch (SQLException ex) {
            throw new DAOException("Erreur BD " + ex.getMessage(), ex);
        } finally {
            try {
                if(rs != null)
                    rs.close();
                if(selectBooksPrep != null)
                    selectBooksPrep.close();
                daoFactory.closeConnection(connec);
            } catch (SQLException ex) {
                throw new DAOException("Erreur BD " + ex.getMessage(), ex);
            }
        }
        return books;
    }
    
    public Book readBook(int id) throws DAOException {
        Connection connec = daoFactory.getConnection();
        
        Book bookResult = null;
        PreparedStatement selectBookPrep = null;
        String selectBookQuery = "SELECT * FROM BIBLIOGRAPHIE WHERE id = ?";
        ResultSet rs = null;
        try {
            selectBookPrep = connec.prepareStatement(selectBookQuery);
            selectBookPrep.setInt(1, id);
            rs = selectBookPrep.executeQuery();
            
            if(rs.next()){
                bookResult = new Book(rs.getInt("id"),
                        rs.getString("auteur"),
                        rs.getString("titre"));
            }
        } catch (SQLException ex) {
            throw new DAOException("Erreur BD " + ex.getMessage(), ex);
        } finally {
            try {
                if(rs != null)
                    rs.close();
                if(selectBookPrep != null)
                    selectBookPrep.close();
                daoFactory.closeConnection(connec);
            } catch (SQLException ex) {
                throw new DAOException("Erreur BD " + ex.getMessage(), ex);
            }
        }
        return bookResult;
    }
    
    public void updateBook(Book book) throws DAOException {
        Connection connec = daoFactory.getConnection();
        
        PreparedStatement createBookPrep = null;
        String createBookQuery = "UPDATE BIBLIOGRAPHIE "
                + "SET auteur=?, titre=? "
                + "WHERE id=?";
        try {
            // TODO : vérifier les entrées utilisateurs 
            // TODO : Créer un utilisateur qui n'a pas tous les droits.
            createBookPrep = connec.prepareStatement(createBookQuery);
            createBookPrep.setString(1, book.getAuthor());
            createBookPrep.setString(2, book.getTitle());
            createBookPrep.setInt(3, book.getId());
            createBookPrep.executeUpdate();
        } catch (SQLException ex) {
            throw new DAOException("Erreur BD " + ex.getMessage(), ex);
        } finally {
            try {
                if(createBookPrep != null)
                    createBookPrep.close();
                daoFactory.closeConnection(connec);
            } catch (SQLException ex) {
                throw new DAOException("Erreur BD " + ex.getMessage(), ex);
            }
        }
    }
    
    public void deleteBook(int id) throws DAOException {
        Connection connec = daoFactory.getConnection();
        
        PreparedStatement createBookPrep = null;
        String createBookQuery = "DELETE FROM BIBLIOGRAPHIE "
                + "WHERE id=?";
        try {
            // TODO : vérifier les entrées utilisateurs 
            // TODO : Créer un utilisateur qui n'a pas tous les droits (GRANT).
            createBookPrep = connec.prepareStatement(createBookQuery);
            createBookPrep.setInt(1, id);
            createBookPrep.executeUpdate();
        } catch (SQLException ex) {
            throw new DAOException("Erreur BD " + ex.getMessage(), ex);
        } finally {
            try {
                if(createBookPrep != null)
                    createBookPrep.close();
                daoFactory.closeConnection(connec);
            } catch (SQLException ex) {
                throw new DAOException("Erreur BD " + ex.getMessage(), ex);
            }
        }
    }
    
    
    
    
    
}
