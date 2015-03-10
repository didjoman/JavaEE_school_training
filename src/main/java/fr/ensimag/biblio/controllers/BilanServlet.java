/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package fr.ensimag.biblio.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Alexandre Rupp
 */
@WebServlet(name = "BilanServlet", urlPatterns = {"/bilan"})
public class BilanServlet extends HttpServlet {
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // We check that the user has been logged. If no, he can't access this page.
        HttpSession session = request.getSession();
        String login = (String)session.getAttribute("login");
        if(login == null){
            request.setAttribute("error", "Erreur : Vous devez êtres identifiés pour accéder à la page Bilan");
            RequestDispatcher view = request.getRequestDispatcher("./WEB-INF/pages/login.jsp");
            view.forward(request, response);
        }
        
        PrintWriter out = response.getWriter();
        Cookie[] cookies = request.getCookies();
        
        int i=0;
        boolean found = false;
        String name = null;
        
        while (i<cookies.length && !found)
        {
            found = cookies[i].getName().equals("login");
            if (found)
                name=cookies[i].getValue();
            else
                i++;
        }
        
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\" />");
            out.println("</head>");
            out.println("<body>");
            out.println("Liste des emprunts de " + name);
            out.println("<ul>");
            out.println("<li><span>Les Travailleurs de la Mer</span>, Victor Hugo</li>");
            out.println("<li><span>CSS 2 - Pratique du design web</span>, Raphaël Goetter</li>");
            out.println("<li><span>The C++ Programming Language</span>, Bjarne Stroustrup</li>");
            out.println("</ul>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
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
        processRequest(request, response);
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
        processRequest(request, response);
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
