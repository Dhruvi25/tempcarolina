
 
import java.io.IOException; 
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
/**
 * A servlet that takes message details from user and send it as a new e-mail
 * through an SMTP server.
 *
 * @author www.codejava.net
 *
 */
@WebServlet("/EmailSendingServlet")
public class Forgot_Password extends HttpServlet {
    private String host;
    private String port;
    private String user;
    private String pass;
 
    public void init() {
        // reads SMTP server setting from web.xml file
        ServletContext context = getServletContext();
        host = context.getInitParameter("host");
        System.out.println("host : "+host);
        port = context.getInitParameter("port");
        System.out.println("port : "+port);
        user = context.getInitParameter("user");
        System.out.println("user : "+user);
        pass = context.getInitParameter("pass");
         System.out.println("pass : "+pass); 
    }
 
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // reads form fields
        String userid = request.getParameter("uid");
        System.out.println("***"+userid);
        String username = request.getParameter("uname");
        System.out.println("***"+username);
        String name1 = request.getParameter("user");
        String mail="";
        String pass1="";
        try{
           
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/carolina_temp", "root", "");
        if(name1.equals("student"))
        {
                    String sql = "SELECT * FROM `registration_table` WHERE stud_id=? and stud_name=?";
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setString(1, userid);
                    pst.setString(2, username);
                    ResultSet rs = pst.executeQuery();
                    while(rs.next())
                    {
                        if(rs.getString("stud_id").equals(userid) && rs.getString("stud_name").equals(username))
                        {
                               mail= rs.getString("e_mail");
                               
                               pass1= rs.getString("password");
                        }
                    }
                 }
        else if(name1.equals("expert"))
        {
            String sql2 = "SELECT * FROM `expert_module_table` WHERE expert_id=? and expert_name=?";
                    PreparedStatement pst2 = con.prepareStatement(sql2);
                    pst2.setString(1, userid);
                    pst2.setString(2, username);
                    ResultSet rs2 = pst2.executeQuery();
                    while(rs2.next())
                    {
                        if(rs2.getString("expert_id").equals(userid) && rs2.getString("expert_name").equals(username))
                        {
                               mail= rs2.getString("e_mail");
                               
                               pass1= rs2.getString("password");
                        }
                    }
        }
        }  
       
       
                 
        catch (Exception e){
                           out.println(e.getMessage());
                   }
         System.out.println("****-->"+mail);
        System.out.println("****-->"+pass1);
        String head ="YOur PASSWORD OF CAROLINA";
        
        String resultMessage = "";
         try {
            Email_configure.sendEmail(host, port, user, pass, mail, head ,pass1);
            resultMessage = "The e-mail was sent successfully";
            
             out.println("<script type=\"text/javascript\">");
             out.println("alert('your Password is sent to your mail_id');");
              out.println("</script>");
        } catch (Exception ex) {
            ex.printStackTrace();
            resultMessage = "There were an error: " + ex.getMessage();
             out.println("<script type=\"text/javascript\">");
             out.println("alert('Invalid userid or username');");
            out.println("</script>");
            
        } finally {
            request.setAttribute("Message", resultMessage);
            response.sendRedirect("login.jsp");
             out.println("<script type=\"text/javascript\">");
            out.println("location='login.jsp';");
             out.println("</script>");
            //getServletContext().getRequestDispatcher("/Result.jsp").forward(request, response);
        }
    }
}