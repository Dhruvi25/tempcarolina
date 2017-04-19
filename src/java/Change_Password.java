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
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class Change_Password extends HttpServlet {
    
     private String host;
    private String port;
    private String user;
    private String pass;
 
    public void init() {
        // reads SMTP server setting from web.xml file
        ServletContext context = getServletContext();
        host = context.getInitParameter("host");
        System.out.println("***->>>"+host);
        port = context.getInitParameter("port");
        System.out.println("***-->"+port);
        user = context.getInitParameter("user");
        System.out.println("***-->"+user);
        pass = context.getInitParameter("pass");
         System.out.println("***-->"+pass); 
    }

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
            ResultSet rs4;
            int n1=0, n2=0;
            String tpass="";
            String mail="";
            String uid=(String)session.getAttribute("uid");
            System.out.println("uid-->"+uid);
            String opass=request.getParameter("oldpass");
            System.out.println("oldpass-->"+opass);
            String npass=request.getParameter("newpass");
            
            String npass1=request.getParameter("newpass2");
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/carolina_temp","root","");
            PreparedStatement pst=(PreparedStatement) con.prepareStatement("SELECT * FROM `login_table` WHERE stud_id=?");
            pst.setString(1, uid);
            
            rs = pst.executeQuery();
            PreparedStatement pst4=(PreparedStatement) con.prepareStatement("SELECT e_mail FROM `registration_table` WHERE stud_id=?");
            pst4.setString(1, uid);
            rs4 = pst4.executeQuery();
            while(rs4.next())
            {
            mail= rs4.getString("e_mail");
            }
            while(rs.next())
            {
            tpass= rs.getString("password");
            }
             System.out.println("oldpass-->"+tpass);
             
             if(tpass.equals(opass) && npass.equals(npass1))
             {
                
                PreparedStatement pst1=(PreparedStatement) con.prepareStatement("UPDATE `login_table` SET `password`=? WHERE stud_id=?"); 
                pst1.setString(1, npass);
                pst1.setString(2, uid);
                PreparedStatement pst2=(PreparedStatement) con.prepareStatement("UPDATE `registration_table` SET `password`=? WHERE stud_id=?"); 
                pst2.setString(1, npass);
                pst2.setString(2, uid);
                n1=pst1.executeUpdate();
                n2=pst2.executeUpdate();
             }  
             if(n1==1)
             {
                  String head ="PASSWORD";
        
        String resultMessage = "";
        String msg="your password has been change successfully!!!";
         try {
            Email_configure.sendEmail(host, port, user, pass, mail, head ,msg);
            resultMessage = "The e-mail was sent successfully";
            
             
        } catch (Exception ex) {
            ex.printStackTrace();
            resultMessage = "There were an error: " + ex.getMessage();
             
            
        } finally {
            request.setAttribute("Message", resultMessage);
            response.sendRedirect("dashboard.jsp");
            
            //getServletContext().getRequestDispatcher("/Result.jsp").forward(request, response);
        }
                 response.sendRedirect("dashboard.jsp");
                 
             }
             else
             {
                  
                 out.println("<script type=\"text/javascript\">");
                 out.println("alert('enter valid password');");
                 out.println("location='change_password.jsp';");
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
