package fr.ensimag.biblio.controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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

public class AccueilServlet extends HttpServlet {
    // Warning, the name will be the same for ALL users !
    private String order = "Choisir un nom : ";
    private String welcomeMsg = "Bienvenue !";
    
    private String mStartDate;
    private int mVisitCounter;
    
    @Override
    public void init() {
        mStartDate = (new Date()).toString();
        mVisitCounter = 0;
    } 


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
        // Count the number of GET done since the beginning
        String msg;
        synchronized(this) {
            mVisitCounter++;
            msg = mVisitCounter + " visits since " + mStartDate;
        } 
        
        request.setAttribute("welcomeMsg", welcomeMsg);
        request.setAttribute("order", order);
        request.setAttribute("msg", msg);
        request.setAttribute("isIndex", true);
	RequestDispatcher view = request.getRequestDispatcher("./WEB-INF/pages/index.jsp");
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
        welcomeMsg = "Bienvenue "+ request.getParameter("name")+" !";
        order = "Changer de nom :";
        
        request.setAttribute("welcomeMsg", welcomeMsg);
        request.setAttribute("order", order);
        RequestDispatcher view = request.getRequestDispatcher("./WEB-INF/pages/index.jsp");
	view.forward(request, response);
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
