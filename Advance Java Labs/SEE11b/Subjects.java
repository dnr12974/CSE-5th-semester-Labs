package MyDNR;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.sql.*;
@WebServlet("/Subjects")
public class Subjects extends HttpServlet
{
	private static final long serialVersionUID=1L;
	private static final String DB_URL="jdbc:mysql://localhost:3307/SubjectsDBSEE";
	private static final String USER="root";
	private static final String PASS="";
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{
		String subName=request.getParameter("subName");
		String facultyId=request.getParameter("facultyId");
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection(DB_URL,USER,PASS);
			String updateSQL="UPDATE Subjects SET Sub_Name=? WHERE Faculty_ID=?";
			preparedStatement=connection.prepareStatement(updateSQL);
			preparedStatement.setString(1, subName);
			preparedStatement.setInt(2, Integer.parseInt(facultyId));
			int rowsUpdated=preparedStatement.executeUpdate();
			out.println("<h2>No of Rows updated:"+rowsUpdated);
			String sql="SELECT * FROM Subjects WHERE Faculty_ID=?";
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, Integer.parseInt(facultyId));
			ResultSet rs=preparedStatement.executeQuery();
			out.println("<table border='1'>");
			out.println("<tr><th>Subject ID</th><th>Subject Name</th><th>Faculty ID</th></tr>");
			while(rs.next())
			{
				out.println("<tr>");
				out.println("<td>"+rs.getInt("Subject_ID")+"</td>");
				out.println("<td>"+subName+"</td>");
				out.println("<td>"+facultyId+"</td>");
				out.println("</tr>");
				
			}
			out.println("</table>");
			
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(preparedStatement!=null) preparedStatement.close();
				if(connection!=null) connection.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
}