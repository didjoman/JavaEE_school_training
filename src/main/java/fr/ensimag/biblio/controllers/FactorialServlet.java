package fr.ensimag.biblio.controllers;

/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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
@WebServlet(name = "FactorialServlet", urlPatterns = {"/factorial"})
public class FactorialServlet extends HttpServlet {
    
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
        RequestDispatcher view = request.getRequestDispatcher("./WEB-INF/pages/factorial.jsp");
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
            request.setAttribute("facto",
                    fact(Integer.parseInt(request.getParameter("number"))));
        
        RequestDispatcher view = request.getRequestDispatcher("./WEB-INF/pages/factorial.jsp");
        view.forward(request, response);
    }
    
    public static int fact(int n) throws ServletException {
        if(n < 0)
            throw new ServletException("L’entier saisi doit etre positif");
        
        int fact = 1; // this  will be the result
        for (int i = 1; i <= n; i++)
            fact *= i;
        return fact;
    }
}
