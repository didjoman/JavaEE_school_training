/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package fr.ensimag.biblio.controllers;

import fr.ensimag.biblio.dao.BookDAO;
import fr.ensimag.biblio.dao.DAOException;
import fr.ensimag.biblio.dao.DAOFactory;
import fr.ensimag.biblio.dao.UserDAO;
import fr.ensimag.biblio.dao.impl.SqlPlusBookDAO;
import fr.ensimag.biblio.models.Book;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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

@WebServlet(name = "Library", urlPatterns = {"/library"})
public class Library extends HttpServlet {
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *super.doDelete(req, resp);
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        DAOFactory daoFactory = (DAOFactory)request.getServletContext()
                .getAttribute("SQLPLUS_CLIENT");
        BookDAO bookDao = daoFactory.getBookDAO();
        
        try {
            List<Book> books = bookDao.readAllBook();
            request.setAttribute("books", books);
        } catch (DAOException ex) {
            Logger.getLogger(BookServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        RequestDispatcher view = request.getRequestDispatcher("./WEB-INF/pages/library.jsp");
        view.forward(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
}
