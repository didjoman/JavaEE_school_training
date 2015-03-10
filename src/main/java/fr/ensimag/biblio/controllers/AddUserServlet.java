/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package fr.ensimag.biblio.controllers;

import com.mongodb.MongoClient;
import fr.ensimag.biblio.dao.DAOFactory;
import fr.ensimag.biblio.dao.impl.MongoDBUserDAO;
import fr.ensimag.biblio.models.User;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
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
@WebServlet(name = "AddUserServlet", urlPatterns = {"/register"})
public class AddUserServlet extends HttpServlet {
    
    
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
        RequestDispatcher view = request.getRequestDispatcher("./WEB-INF/pages/user_add.jsp");
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
        // We get the parameters into variables :
        String lastName = request.getParameter("lastName");
        String firstName = request.getParameter("firstName");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String age = request.getParameter("age");
        String town = request.getParameter("birthTown");
        
        // We check every parameter and
        //  add an error to the list of erros when a parameter is not valid.
        List <String> errors = new ArrayList<String>();
        
        if (firstName == null || firstName.equals(""))
            errors.add("Erreur : Prénom incorrecte");
        if(login == null || login.equals(""))
            errors.add("Erreur : Login incorrecte");
        if(password == null || password.equals(""))
            errors.add("Erreur : Mot de Passe incorrecte");
        if(gender == null ||
                (!gender.equals("male") && !gender.equals("female")))
            errors.add("Erreur : Sexe incorrecte");
        if(age == null ||
                (!age.equals("jeune") &&
                !age.equals("presque jeune") &&
                !age.equals("vieux")))
            errors.add("Erreur : Age incorrecte");
        
        String[] towns = {"Grenoble", "Lyon", "Paris", "Autre"};
        if(town == null || !Arrays.asList(towns).contains(town))
            errors.add("Erreur : Ville de naissance incorrecte"+town);
        
        // If one parameter is incorrect, then we return the register view :
        if(!errors.isEmpty()){
            request.setAttribute("errors", errors);
            RequestDispatcher view = request.getRequestDispatcher("./WEB-INF/pages/user_add.jsp");
            view.forward(request, response);
            return;
        }
        
        // If everything is ok :
        // |- 1) Creation of the object User
        User user = new User();
        user.setLastName(lastName);
        user.setFirstName(firstName);
        user.setLogin(login);
        user.setPassword(password);
        user.setIsMale((gender.equals("male")) ? true : false);
        user.setAge(age);
        user.setTown(town);
        
        // |- 2) We get the database
        MongoClient mongo = (MongoClient) request.getServletContext()
                .getAttribute("MONGO_CLIENT");
        
        // |- 3) Creation of the UserDAO + Add it in the DB
        MongoDBUserDAO userDAO = new MongoDBUserDAO(mongo);
        userDAO.createUser(user);
        System.out.println("User "+user.getLogin()+" Added Successfully");
        
        // ************************* /!\ USELESS THING *************************
        // We store it in the 2nd database (Oracle) :
        // TODO : BAD PRACTICE : Define a UserDAO
        Connection connec = ((DAOFactory)request.getServletContext()
                .getAttribute("SQLPLUS_CLIENT")).getConnection();
        PreparedStatement insertUser = null;
        String insertUserQuery = "INSERT INTO Users (login, password) VALUES (?,?)";
        try {
            insertUser = connec.prepareStatement(insertUserQuery);
            insertUser.setString(1, login);
            insertUser.setString(2, password);
            insertUser.executeUpdate();
            insertUser.close();
            connec.close();
        } catch (SQLException ex) {
            Logger.getLogger(AddUserServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(insertUser != null)
                    insertUser.close();
                if(connec != null)
                    connec.close();
            } catch (SQLException ex) {
                Logger.getLogger(CheckUserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        // Finally, we print the "registered" page
        request.setAttribute("lastName", lastName);
        request.setAttribute("firstName", firstName);
        request.setAttribute("login", login);
        request.setAttribute("password", password);
        if(gender.equals("male"))
            request.setAttribute("gender", "masculin");
        else if(gender.equals("female"))
            request.setAttribute("gender", "féminin");
        request.setAttribute("age", request.getParameter("age"));
        request.setAttribute("birthTown", request.getParameter("birthTown"));
        
        RequestDispatcher view = request.getRequestDispatcher("./WEB-INF/pages/user_added.jsp");
        view.forward(request, response);
    }
    
    
}
