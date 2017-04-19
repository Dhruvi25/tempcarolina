import java.io.IOException;
import java.io.PrintWriter;
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
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String uid1 = request.getParameter("uid");
            String pass1 = request.getParameter("pass");
            String name1 = request.getParameter("user");
            out.println("*****"+name1);
            
            HttpSession session = request.getSession();
            session.setAttribute("uid",uid1);
            
             Class.forName("com.mysql.jdbc.Driver");
             Connection con= (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/carolina_temp","root","");
            if(name1.equals("student"))
            {
            PreparedStatement pst=(PreparedStatement) con.prepareStatement("select * from login_table where stud_id=? and password=?");
            pst.setString(1,uid1);
            pst.setString(2,pass1);
            ResultSet rs = pst.executeQuery();
            
                 if(rs.first())
                 {
                     response.sendRedirect("dashboard.jsp");
                 }
                 else
                 {
                     
                        out.println("<script type=\"text/javascript\">");
                        out.println("alert('UserId or password is incorrect');");
                        out.println("location='login.jsp';");
                        out.println("</script>");
                     
                 }
            }
            else if(name1.equals("expert"))
            {
                PreparedStatement pst = (PreparedStatement) con.prepareStatement("select * from expert_module_table where expert_id=? and password=?");
                pst.setString(1, uid1);
                pst.setString(2, pass1);
                ResultSet rs = pst.executeQuery();
            
                 if(rs.first())
                 {
                     response.sendRedirect("expert_dashboard.jsp");
                 }
                 else
                 {
                     
                        out.println("<script type=\"text/javascript\">");
                        out.println("alert('UserId or password is incorrect');");
                        out.println("location='login.jsp';");
                        out.println("</script>");
                     
                 }
            }
            out.println("</body>");
            out.println("</html>");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
