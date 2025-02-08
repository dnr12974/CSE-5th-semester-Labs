package MyDNR;
import java.sql.*;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
@WebServlet("/Police")
public class Police extends HttpServlet
{
	private static final long serialVersionUID=1L;
	private static final String DB_URL="jdbc:mysql://localhost:3307/policeDBSEE";
	private static final String USER="root";
	private static final String PASS="";
	protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
	{
		String area=request.getParameter("area");
		String phoneNumber=request.getParameter("phoneNumber");
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection(DB_URL,USER,PASS);
			String sql="SELECT * FROM police_station WHERE area=? AND phoneNumber=?";
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, area);
			preparedStatement.setString(2, phoneNumber);
			ResultSet rs=preparedStatement.executeQuery();
		     if(rs.next())
			{
				out.println("<h1>Police Station details</h1>");
				out.println("<p>Area:"+area+"</p>");
				out.println("<p>Phone number:"+phoneNumber+"</p>");
				out.println("<p>Address:"+rs.getString("address")+"<p>");
			}
		     else
		     {
		    	 out.println("<p>No police station found with given area and phoneNumber</p>");
		     }
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