/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package fr.ensimag.biblio.controllers;

import com.mongodb.MongoClient;
import fr.ensimag.biblio.dao.OracleDB;
import fr.ensimag.biblio.dao.impl.MongoDBUserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Alexandre Rupp
 */
public class CheckUserServlet extends HttpServlet {
    
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
        Cookie[] cookies = request.getCookies();
        
        String login = null;
        String password = null;
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("login"))
                login=cookie.getValue();
            else if(cookie.getName().equals("password"))
                password=cookie.getValue();
            
            if(login != null && password != null)
                break;
        }
        
        request.setAttribute("login", login);
        request.setAttribute("password", password);
        
        // We print the login page.
        RequestDispatcher view = request.getRequestDispatcher("./WEB-INF/pages/login.jsp");
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
        // We get the login and password submited by the user :
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        
        // Save login & pwd in a cookie :
        response.addCookie(new Cookie("login", login));
        response.addCookie(new Cookie("password", password));
        
        // We check wether the user exists or not in the DB :
        String error = null;
        boolean logged = true;
        // At first we check if the fields have been correctly fulfilled :
        if(login == null || login.equals("") ||
                password == null || password.equals("")){
            error = "Erreur : Login ou mot de passe non saisi.";
            logged = false;
        }else{
            // Then we check if the data are corrects :
            MongoClient mongo = (MongoClient) request.getServletContext()
                    .getAttribute("MONGO_CLIENT");
            MongoDBUserDAO userDAO = new MongoDBUserDAO(mongo);
            if(userDAO.readUser(login, password) == null){
                error = "Erreur : Login ou mot de passe incorrecte.";
                logged = false;
            }
        }
        
        // ************************* /!\ USELESS THING *************************
        // We store it in the 2nd database (Oracle) :
        Connection connec = new OracleDB().connect();
        PreparedStatement checkUser = null;
        String checkUserQuery = "SELECT login FROM Users WHERE login = ? AND password = ?";
        try {
            checkUser = connec.prepareStatement(checkUserQuery);
            checkUser.setString(1, login);
            checkUser.setString(2, password);
            ResultSet res = checkUser.executeQuery();
            if(!res.next()){
                error = "Erreur : Login ou mot de passe incorrecte !";
                logged = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddUserServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(checkUser != null)
                    checkUser.close();
                connec.close();
            } catch (SQLException ex) {
                Logger.getLogger(CheckUserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        // If the user has been logged correctly, we set the session :
        if(error == null){
            HttpSession session = request.getSession(true);
            session.setAttribute("login", login);
        }
        // We print the login page.
        request.setAttribute("error", error);
        request.setAttribute("logged", logged);
        
        RequestDispatcher view = request.getRequestDispatcher("./WEB-INF/pages/login.jsp");
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
