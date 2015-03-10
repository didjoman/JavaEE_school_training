/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package fr.ensimag.biblio.dao;

import fr.ensimag.biblio.models.Book;
import java.util.List;

/**
 *
 * @author Alexandre Rupp
 */
public interface BookDAO {
    
    public void createBook(String author, String title) throws DAOException;
    
    public List<Book> readAllBook() throws DAOException;
    
    public Book readBook(int id) throws DAOException;
    
    public void updateBook(Book book) throws DAOException;
    
    public void deleteBook(int id) throws DAOException;
    
    
}
