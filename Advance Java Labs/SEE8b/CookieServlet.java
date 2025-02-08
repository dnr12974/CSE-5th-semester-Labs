package MyDNR;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.*;
@WebServlet("/CookieServlet")
public class CookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		Cookie cookie1=new Cookie("cookie1","FirstCookie");
		Cookie cookie2=new Cookie("cookie2","SecondCookie");
		Cookie cookie3=new Cookie("cookie3","ThirdCookie");
		Cookie cookie4=new Cookie("cookie4","FourthCookie");
		
		cookie1.setMaxAge(5);
		cookie2.setMaxAge(5);
		response.addCookie(cookie1);
		response.addCookie(cookie2);
		response.addCookie(cookie3);
		response.addCookie(cookie4);
		
		Cookie[] cookies=request.getCookies();
		out.println("<html><body>");
		out.println("<h1>Cookies Display</h1>");
		if(cookies!=null)
		{
			out.println("<table border='1'>");
			out.println("<tr><th>Cookie name</th><th>Cookie value</th></tr>");
			for(Cookie c:cookies)
			{
				out.println("<tr>");
				out.println("<td>"+c.getName()+"</td>");
				out.println("<td>"+c.getValue()+"</td>");
				out.println("</tr>");
			}
			out.println("</table>");
		}
		else
		{
			out.println("<p>No cookies found!</p>");
		}
		out.println("</body></html>");	
	}
}
      
