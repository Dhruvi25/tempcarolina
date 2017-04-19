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
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jboss.weld.context.http.Http;

/**
 *
 * @author
 */


 class InvalidAgeException extends Exception{  
     InvalidAgeException(String s){  
      super(s);  
     }  
    }  

class TestCustomException1{  
  
  public static void validate(String age,String lang1,String lang2,String lang3)throws InvalidAgeException{  
     if(age=="" || lang1==null || lang2==null || lang3==null)  
      throw new InvalidAgeException("not valid");  
     else  
      System.out.println("post succesfully...!!");  
   }  
}

public class Post_ques extends HttpServlet {
    
    
    
    TestCustomException1 t1 = new TestCustomException1();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       try {
            response.setContentType("text/html;charset=UTF-8");
            HttpSession session = request.getSession();            
            String uid=(String)session.getAttribute("uid");
            String lang1=request.getParameter("lang1");
            System.out.println("yoyoyoyoyoyoy"+lang1);
            String lang2=request.getParameter("lang2");
            System.out.println("hoyohoyoho"+lang2);
            String lang3=request.getParameter("lang3");
            System.out.println("bigdaday.."+lang3);
            String title=request.getParameter("title"); 
            String ques=request.getParameter("ques");
            String replace="";
            try {
                System.out.println("-------->>>>>"+ques);
                System.out.println("-------->>>>>"+lang1);
                t1.validate(ques,lang1,lang2,lang3);
                String fcode = request.getParameter("finalcode");
            if(fcode!=null)
            {
             
              replace = fcode.replace("<","&lt");
              replace = replace.replace(">","&gt");
              replace="<pre>"+replace+"</pre>";
              System.out.println("******"+replace);
              
             
            }
            
            if(lang1.equals("other1"))
            {
                lang1= request.getParameter("input1");
              
            }
            if(lang2.equals("other2"))
            {
                lang2= request.getParameter("input2");
               
            }
            if(lang3.equals("other3"))
            {
                lang3= request.getParameter("input3");
                
            }
//           
            int n=0;
            
            Calendar calendar = Calendar.getInstance();
            java.util.Date now = calendar.getTime();
            java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());

            Class.forName("com.mysql.jdbc.Driver");
            Connection con= (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/carolina_temp","root","");
            String str = "INSERT INTO `post_table`(`title`, `stud_id`, `time`, `description`, `lang1`,`lang2`,`lang3`,`code`) VALUES ('"+title.trim()+"','"+uid.trim()+"','"+currentTimestamp+"','"+ques.trim()+"','"+lang1.trim()+"','"+lang2.trim()+"','"+lang3.trim()+"','"+replace+"')";
            System.out.println("--->"+str);
            PreparedStatement pst=(PreparedStatement) con.prepareStatement(str);
        
            n=pst.executeUpdate();
            if(n==1)
            {
                response.sendRedirect("post_ques.jsp");
            }
            
        } catch (ClassNotFoundException ex) {
            out.println(ex.getMessage());
            Logger.getLogger(Post_ques.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            out.println(ex.getMessage());
            Logger.getLogger(Post_ques.class.getName()).log(Level.SEVERE, null, ex);
        }
             }catch(Exception m)
                    {
                      
                      
                        System.out.println("Exception occured" +m);
                        response.sendRedirect("post_ques.jsp");
                    }
                
            
}

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
