/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package fr.ensimag.biblio.controllers;

import fr.ensimag.biblio.dao.BookDAO;
import fr.ensimag.biblio.dao.DAOException;
import fr.ensimag.biblio.dao.DAOFactory;
import fr.ensimag.biblio.dao.impl.SqlPlusBookDAO;
import fr.ensimag.biblio.models.Book;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alexandre Rupp
 */
@WebServlet(name = "BookServlet", urlPatterns = {"/books"})
public class BookServlet extends HttpServlet {
    
    
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Redirection if HTTP verbs not used :
        String action = request.getParameter("action");
        
        DAOFactory daoFactory = (DAOFactory)request.getServletContext()
                .getAttribute("SQLPLUS_CLIENT");
        BookDAO bookDao = daoFactory.getBookDAO();
        
        if (action != null && !action.equals("get")) {
            if (action.equals("post")) {
                doPost(request, response);
                
                try {
                    List<Book> books = bookDao.readAllBook();
                    request.setAttribute("books", books);
                    request.getRequestDispatcher("./WEB-INF/pages/book_get_all.jsp")
                            .forward(request, response);
                    return;
                } catch (DAOException ex) {
                    Logger.getLogger(BookServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (action.equals("delete")) {
                doDelete(request, response);
                
                try {
                    List<Book> books = bookDao.readAllBook();
                    request.setAttribute("books", books);
                    request.getRequestDispatcher("./WEB-INF/pages/book_get_all.jsp")
                            .forward(request, response);
                    return;
                } catch (DAOException ex) {
                    Logger.getLogger(BookServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                return;
            } else if (action.equals("put")) {
                doPut(request, response);
                
                try {
                    List<Book> books = bookDao.readAllBook();
                    request.setAttribute("books", books);
                    request.getRequestDispatcher("./WEB-INF/pages/book_get_all.jsp")
                            .forward(request, response);
                    return;
                } catch (DAOException ex) {
                    Logger.getLogger(BookServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                return;
            } else {
                // renvoi vers une page d'erreur controleurErreur.jsp
            }
        }
        
        // GET :
        String view = request.getParameter("view");
        
        if(view != null){
            if(view.equals("update")){
                try {
                    // TODO : test the user input valididty :
                    Book book = bookDao.readBook(Integer.parseInt((String)request.getParameter("id")));
                    request.setAttribute("book", book);
                    request.getRequestDispatcher("./WEB-INF/pages/book_update.jsp")
                            .forward(request, response);
                    return;
                } catch (DAOException ex) {
                    Logger.getLogger(BookServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if(view.equals("delete")){
                try {
                    Book book = bookDao.readBook(Integer.parseInt((String)request.getParameter("id")));
                    request.setAttribute("book", book);
                    request.getRequestDispatcher("./WEB-INF/pages/book_delete.jsp")
                            .forward(request, response);
                    return;
                } catch (DAOException ex) {
                    Logger.getLogger(BookServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if(view.equals("create")){
                request.getRequestDispatcher("./WEB-INF/pages/book_add.jsp")
                        .forward(request, response);
                return;
            } else {
                // error
            }
        }
        
        // By default GET all:
        
        try {
            List<Book> books = bookDao.readAllBook();
            request.setAttribute("books", books);
            request.getRequestDispatcher("./WEB-INF/pages/book_get_all.jsp")
                    .forward(request, response);
        } catch (DAOException ex) {
            Logger.getLogger(BookServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        try {
            DAOFactory daoFactory = (DAOFactory)request.getServletContext()
                    .getAttribute("SQLPLUS_CLIENT");
            BookDAO bookDao = daoFactory.getBookDAO();
            
            String author = request.getParameter("author");
            String title = request.getParameter("title");
            bookDao.createBook(author, title);
            doGet(request, response);
        } catch (DAOException ex) {
            Logger.getLogger(BookServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(BookServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BookServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            DAOFactory daoFactory = (DAOFactory)req.getServletContext()
                    .getAttribute("SQLPLUS_CLIENT");
            BookDAO bookDao = daoFactory.getBookDAO();
            
            Integer id = Integer.parseInt(req.getParameter("id")); 
            // TODO : erreur non catchée pour le parsing du int
            bookDao.deleteBook(id);
        } catch (DAOException ex) {
            Logger.getLogger(BookServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            DAOFactory daoFactory = (DAOFactory)req.getServletContext()
                    .getAttribute("SQLPLUS_CLIENT");
            BookDAO bookDao = daoFactory.getBookDAO();
            
            Integer id = Integer.parseInt(req.getParameter("id"));
            String author = req.getParameter("author");
            String title = req.getParameter("title");
            
            bookDao.updateBook(new Book(id, author, title));
        } catch (DAOException ex) {
            Logger.getLogger(BookServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
