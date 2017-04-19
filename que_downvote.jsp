<%-- 
    Document   : view_ans
    Created on : Oct 22, 2016, 12:00:01 PM
    Author     : dhruvi
--%>

<%@page import="java.sql.Timestamp"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Carolina-University dashboard</title>
    </head>
    <body>
       <%
            String uid = (String) session.getAttribute("uid");
            String queid= request.getParameter("p");
            ResultSet rs=null;
            Boolean var = true;
            Boolean b1= false;
           
               
            int n = 0, n1=0;
            int a= 1;
            try {               
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/carolina_temp", "root", "");
                    String sql1 ="SELECT * FROM `quest_vote_table`";
                    PreparedStatement pst1 = con.prepareStatement(sql1);
                    rs=pst1.executeQuery();
                    while(rs.next())
                    {
                    if(rs.getString("quest_id").equals(queid) && rs.getString("stud_id").equals(uid) || rs.getString("up_votes").equals(a))
                    {
                        var= false;
                    }
                   }
                    
                    if(var)
                    {
                        String sql = "INSERT INTO `quest_vote_table`(`quest_id`, `stud_id`, `up_votes`,`down_votes`) VALUES (?,?,?,?)";
                        PreparedStatement pst = con.prepareStatement(sql);
                         pst.setString(1, queid);
                         pst.setString(2, uid);
                         pst.setString(3, "0");
                         pst.setString(4, "1");
                         n=pst.executeUpdate();
                         b1= true;
                         
                    }
                    if(var== false)
                    {
                        String sql2 ="UPDATE `quest_vote_table` SET `quest_id`=?,`stud_id`=?,`up_votes`=?,`down_votes`=? WHERE quest_id=? AND stud_id=? ";
                        PreparedStatement pst2 = con.prepareStatement(sql2);
                         pst2.setString(1, queid);
                         pst2.setString(2, uid);
                         pst2.setString(3, "0");
                         pst2.setString(4, "1");
                         pst2.setString(5, queid);
                         pst2.setString(6, uid);
                         n1=pst2.executeUpdate();
                         b1= true;
                     }
                     if(b1)
                    {
                        out.println("voted");
                    }
            }
                   
                            catch (Exception e){
                           out.println(e.getMessage());
                           
                          
       }
                
                 
       %>
    </body>
</html>
