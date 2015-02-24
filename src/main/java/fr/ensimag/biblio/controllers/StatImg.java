/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package fr.ensimag.biblio.controllers;

import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Alexandre Rupp
 */
@WebServlet(name = "StatImg", urlPatterns = {"/StatImg"})
public class StatImg extends HttpServlet {
    
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
        
        OutputStream out = response.getOutputStream();
        response.setContentType("image/png");
        //int val1 = 40;
        //int val2 = 60;
        try {
            DefaultPieDataset dataset = new DefaultPieDataset();
            String title = "Statistiques";
            boolean legend = true ;
            
            Map <String, String[]> params = request.getParameterMap();
            for(Map.Entry<String, String[]> param : params.entrySet()){
                String key = param.getKey();
                String[] value = param.getValue();
                System.out.println("key : "+key + "value : "+value[0]);
                if(key.equals("legend"))
                    legend = Boolean.parseBoolean(value[0]);
                else
                    dataset.setValue(key, Double.parseDouble(value[0]));
            }
            
            //dataset.setValue("Valeur 1", val1);
            //dataset.setValue("Valeur 2", val2);
            JFreeChart chart = ChartFactory.createPieChart(
                    title,
                    dataset,
                    legend,
                    false,
                    false);
            chart.setBackgroundPaint(Color.WHITE);
            ChartUtilities.writeChartAsPNG(out, chart, 500, 300);
        } finally {
            out.close();
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
