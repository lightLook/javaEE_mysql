package MesServlets;
import java.io.*;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

public class AffichePersonne extends jakarta.servlet.http.HttpServlet implements jakarta.servlet.Servlet {

 
	private static final long serialVersionUID = 1L;
	
Connection BD;
public void init() throws ServletException {
try
{
Class.forName("com.mysql.jdbc.Driver");
}
catch(ClassNotFoundException er)
{
 System.err.println("Erreur de chargement du driver" + er);
}


// Connexion à la base de données
try
{
 BD = DriverManager.getConnection("jdbc:mysql://localhost:3306/loginsystem", "root","root");
 System.out.println("con ok");
}
catch (SQLException ex)
{
System.err.println("Erreur lors de la connexion " + ex);
}
}


public void destroy() {
try
{
BD.close();
}
catch (SQLException sqle)
{
System.err.println("Erreur SQL "+sqle);
}
} 
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws
ServletException, IOException {
Statement tmpStatement = null;
ResultSet resultSet = null;
PrintWriter out=null;
resp.setContentType("text/html");
out = resp.getWriter();
out.println("<html>");
out.println("<head><title> Test servlet et BD </title></head>");
out.println("<body>");
out.println("Contenu de la table personne <BR>");
out.println("<table>");
try
{
tmpStatement = BD.createStatement();
resultSet = tmpStatement.executeQuery("select * from info");//---------------------------------
out.println("<TR>");
out.println("<TD>Nom</TD>");
out.println("<TD>Prénom</TD>");
out.println("</TR>");
while (resultSet.next())
{
out.println("<TR>");
out.println("<TD>");
out.println(resultSet.getString("Nom"));
out.println("</TD>");
out.println("<TD>");
out.println(resultSet.getString("Prénom"));
out.println("</TD>");
out.println("</TR>");
}
out.println("</table>");
out.println("</body>");
out.println("</html>");
 resultSet.close();
tmpStatement.close();
tmpStatement = null;
resultSet = null;
}
catch (java.sql.SQLException ex)
{
// Pb dans la requête
}
}
}