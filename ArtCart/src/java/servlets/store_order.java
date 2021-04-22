/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ankur
 */
public class store_order extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try{
            /* TODO output your page here. You may use following sample code. */
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/artcart","root","");
            Connection con2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/artcart","root","");
                    Statement st = con.createStatement();
                    //out.println("<h>connection done</h>");
                    ResultSet rs;
                    String email;    
                    //name =request.getParameter("name");
                    Cookie ck[]=request.getCookies();  
                    HttpSession session = request.getSession(false);
                    if(session!=null){  
                         email=(String)session.getAttribute("email");
                         
                         
                        String name=(String)session.getAttribute("name");
                        
                        if(!email.equals("")||email!=null){ 
                            int total=0;
                            
                            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM cart WHERE email=?");
                            pstmt.setString(1, email);
                            ResultSet rs1 = pstmt.executeQuery();
                            
                            PreparedStatement ps=con2.prepareStatement("INSERT INTO storeorder values(?,?,?,?)");
                            while(rs1.next()) {
                                String it = rs1.getString("item");
                    
                          
                                
                                String em= rs1.getString("email");
                                
                                float price=rs1.getFloat("price");
                                
                                ps.setString(1, em);
                                ps.setString(2, it);
                                ps.setFloat(3, price);
                                Date date = new Date();
                                ps.setString(4, date.toString());
                                int i = ps.executeUpdate();
                                
                               
                            }
                            
                            pstmt = con.prepareStatement("DELETE FROM cart WHERE email=?");
                           pstmt.setString(1, email);
                           int i = pstmt.executeUpdate();
                           request.getRequestDispatcher("home.html").include(request, response);
                        }
            
                    }
        }
        catch(Exception e){
            out.println(e);
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
