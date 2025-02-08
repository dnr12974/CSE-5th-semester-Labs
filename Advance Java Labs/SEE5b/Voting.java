package MyDNR;
import java.io.*;
import java.time.*;
import java.time.format.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
@WebServlet("/Voting")
public class Voting extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{
		String fname=request.getParameter("fname");
		String lname=request.getParameter("lname");
		String dob=request.getParameter("age");
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.println("<html><body>");
		try
		{
			LocalDate birthDate=LocalDate.parse(dob,DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			int age=Period.between(birthDate,LocalDate.now()).getYears();
			if(age>=18)
			{
				out.println("<h1>You are eligible to vote!,"+fname+" "+lname+"</h1>");
			}
			else
			{
				out.println("<h1>You are not eligible to vote!,"+fname+" "+lname+"</h1>");
			}
			
		}
		catch(DateTimeParseException e)
		{
			out.println("<h1>Error occured!");
		}
		out.println("</body></html>");
		
	}
}
	
  