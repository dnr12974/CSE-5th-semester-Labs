package MyDNR;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
@WebServlet("/Calculate")
public class Calculate extends HttpServlet
{
	private static final long serialVersionUID=1L;
	protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
	{
		String n1=request.getParameter("n1");
		String n2=request.getParameter("n2");
		String op=request.getParameter("op");
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		double r=0,a=Double.parseDouble(n1),b=Double.parseDouble(n2);
		if(op.equals("Addition"))
		{
			r=a+b;
		}
		else if(op.equals("Subtraction"))
		{
			r=a-b;
		}
		else if(op.equals("Multiplication"))
		{
			r=a*b;
		}
		else if(op.equals("Division"))
		{
			if(b!=0)
			{
				r=a/b;
			}
			else
			{
				r=Double.MAX_VALUE;
			}
		}
		else if(op.equals("e^x"))
		{
			r=Math.exp(a);
		}
		out.println("<html><body>");
		out.println("<h1>Result:"+String.valueOf(r)+"</h1>");
		out.println("</body></html>");
	}
}