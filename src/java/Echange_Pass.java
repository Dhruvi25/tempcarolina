/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class Echange_Pass extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DataUpdate</title>"); 
            out.println("</head>");
            out.println("<body>");
            HttpSession session = request.getSession();
            ResultSet rs;
            int n1=0, n2=0;
            String tpass="";
            String uid=(String)session.getAttribute("uid");
            System.out.println("uid-->"+uid);
            String opass=request.getParameter("oldpass");
            System.out.println("oldpass-->"+opass);
            String npass=request.getParameter("newpass");
            
            String npass1=request.getParameter("newpass2");
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/carolina_temp","root","");
            PreparedStatement pst=(PreparedStatement) con.prepareStatement("SELECT * FROM `expert_module_table` WHERE expert_id=?");
            pst.setString(1, uid);
            rs = pst.executeQuery();
            while(rs.next())
            {
            tpass= rs.getString("password");
            }
             System.out.println("oldpass-->"+tpass);
             
             if(tpass.equals(opass) && npass.equals(npass1))
             {
                
                PreparedStatement pst1=(PreparedStatement) con.prepareStatement("UPDATE `expert_module_table` SET `password`=? WHERE expert_id=?"); 
                pst1.setString(1, npass);
                pst1.setString(2, uid);
               
                n1=pst1.executeUpdate();
                
             }  
             if(n1==1)
             {
                 response.sendRedirect("expert_dashboard.jsp");
                 
             }
             else
             {
                  
                 out.println("<script type=\"text/javascript\">");
                 out.println("alert('enter valid password');");
                 out.println("location='echange_pass.jsp';");
                 out.println("</script>");
                
             }
                
            out.println("</body>");
            out.println("</html>");
        } catch (Exception e) {
                        out.println(e.getMessage());
                    }
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
