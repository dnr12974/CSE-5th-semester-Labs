package MyDNR;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
@WebServlet("/visitCounter")
public class visitCounter extends HttpServlet
{
	private static final long serialVersionUID=1L;
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
	{
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession(true);
		Integer visitCount=(Integer) session.getAttribute("visitCount");
		if(visitCount==null)
		{
			visitCount=1;
			session.setAttribute("visitCount",visitCount);
			out.println("<h1>Welcome!This is your first visit!</h1>");
		}
		else
		{
			visitCount++;
			session.setAttribute("visitCount", visitCount);
			out.println("<h1>Welcome! You have visited this page "+visitCount+" times!</h1>");
		}
		out.close();
	}
}
