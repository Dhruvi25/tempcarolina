/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;

import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 * @author Admin
 */
public class Reply_answer extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String uid=(String)session.getAttribute("uid");
        String qid= request.getParameter("uid1");
        String page= request.getParameter("uid2");
        System.out.print("****"+page);
         String replace="";
         
        String ans = request.getParameter("ans");
         System.out.print("********"+ans);
        
        String fcode = request.getParameter("finalcode");
        if(fcode!=null)
        {
             replace = fcode.replace("<","&lt");
              replace = replace.replace(">","&gt");
             
            fcode="<pre>"+replace+"</pre>";
        }
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
        SimpleDateFormat dateFormat = new SimpleDateFormat(" HH:mm:ss");
        Date date1 = new Date();
        String date2 =dateFormat.format(date1);
        int n = 0;
        try {
            
            
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/carolina_temp", "root", "");
            String sql = "INSERT INTO `answer_table`(`stud_id`, `quest_id`, `ans_description`,`anscode`, `time`) VALUES (?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, uid);
            pst.setString(2, qid);
            pst.setString(3, ans);
            pst.setString(4, fcode);
            pst.setTimestamp(5, currentTimestamp);
             
            
            String sql2 = "UPDATE `post_table` SET `checked`= 1 WHERE quest_id=?";
            PreparedStatement pst2 = con.prepareStatement(sql2);
              pst2.setString(1, qid);
             int n2=pst2.executeUpdate();
              
            n=pst.executeUpdate();
            if(n==1)
            { 
                if(page.equals("search_ques"))
                {
                        
                        response.sendRedirect("search_ques.jsp?id1="+qid);
                }
                if(page.equals("doc_que"))
                {
                        String lang= request.getParameter("uid3");
                        out.println("++++____"+lang);
                        response.sendRedirect("doc_que.jsp?id2="+lang);
                }
                if(page.equals("doc_que1"))
                {
                        String lang= request.getParameter("uid3");
                        String q = request.getParameter("uid4");
                        out.println("++++____"+lang);
                        response.sendRedirect("doc_que1.jsp?id2="+lang+"&id3="+q);
                }
                if(page.equals("asked_ques"))
                {
                         response.sendRedirect("asked_ques.jsp");
                }
                if(page.equals("asked_ques1"))
                {
                         String q = request.getParameter("uid3");
                        response.sendRedirect("asked_ques1.jsp?id1="+q);
                }
               
            }
            
        }
                
        catch (Exception e){
            out.println(e.getMessage());
        }
        
            
}

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
