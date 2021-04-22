/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ankur
 */
public class login extends HttpServlet {

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
        try {
            
            Class.forName("com.mysql.jdbc.Driver");  
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/artcart","root","");
            PreparedStatement ps;
            ResultSet rs;
          
            
            String email = request.getParameter("email");
            String pass  = request.getParameter("password");
            
            
            ps = con.prepareStatement(" select * from user where email = ? ");
            ps.setString(1,email);
            rs = ps.executeQuery();
            
            
            
            if(rs.next()){
                
                String name = rs.getString("name");
                String db_pass = rs.getString("password");
                
                
                if(pass.equals(db_pass)){
                    
                    
                    out.println("<script type=\"text/javascript\">");
                    String s = "alert('Welcome " + name+"');";
                    out.println(s);
                    out.println("location='artcartshop.html';");
                    out.println("</script>");
                    
                    Cookie c=new Cookie("name",name);
                    response.addCookie(c);
                    
                    
                    HttpSession session=request.getSession();
                    session.setAttribute("name",name );
                    session.setAttribute("email",email );
                    session.setAttribute("pass", pass);
                    request.getRequestDispatcher("artcartshop.html").include(request, response);
                    
                    
                    
                }
                else{
                    
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Either Email or Password is Wrong');");
                    out.println("location='index.html';");
                    out.println("</script>");
                    
                    request.getRequestDispatcher("index.html").include(request, response);
                    
                    
                   
                }
            } 
            else {
                    request.getRequestDispatcher("index.html").include(request, response);
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Please Sign Up First');");
                    out.println("location='index.html';");
                    out.println("</script>");
                    
                    
                    
            }
            
            
        }
        catch(Exception e){out.println("Exception raised"+ e);}
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
