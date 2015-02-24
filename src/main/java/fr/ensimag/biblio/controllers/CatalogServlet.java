/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package fr.ensimag.biblio.controllers;

import com.mongodb.MongoClient;
import fr.ensimag.biblio.dao.OracleDB;
import fr.ensimag.biblio.dao.impl.MongoDBUserDAO;
import fr.ensimag.biblio.models.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alexandre Rupp
 */
public class CatalogServlet extends HttpServlet {
    
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
        
        // Select in database (Oracle) :
        Connection connec = new OracleDB().connect();
        PreparedStatement selectBiblio = null;
        String selectBiblioQuery = "SELECT auteur, titre FROM bibliographie";
        ResultSet res = null;
        try {
            selectBiblio = connec.prepareStatement(selectBiblioQuery);
            res = selectBiblio.executeQuery();
            
            // We set the attributes
            List<Map<String, String>> result =
                    new ArrayList<Map<String, String>>();
            try {
                while(res.next()){
                    Map<String, String> line = new HashMap<String, String>();
                    line.put("author", res.getString("auteur"));
                    line.put("title", res.getString("titre"));
                    result.add(line);
                }
            } catch (SQLException ex) {
                Logger.getLogger(CatalogServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("bib", result);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AddUserServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(selectBiblio != null)
                    selectBiblio.close();
                connec.close();
            } catch (SQLException ex) {
                Logger.getLogger(CatalogServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        
        // Finally we print the page :
        
        RequestDispatcher view = request.getRequestDispatcher("./WEB-INF/pages/catalog.jsp");
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
    
}
