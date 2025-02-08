package MyDNR;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import jakarta.servlet.annotation.*;
@WebServlet("/payroll")
public class payroll extends HttpServlet
{
	protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
	{
		String employeeName=request.getParameter("employeeName");
		int hoursWorked=Integer.parseInt(request.getParameter("hoursWorked"));
		double hourlyPayRate=Double.parseDouble(request.getParameter("hourlyPayRate"));
		double taxRate=0.20;
		double grossPay=hoursWorked*hourlyPayRate;
		double tax=grossPay*taxRate;
		double netPay=grossPay-tax;
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.println("<html><body>");
		out.println("<h1>Payroll Statement</h1>");
		out.println("<p>Employee name:"+employeeName+"</p>");
		out.println("<p>Hours Worked:"+hoursWorked+"</p>");
		out.println("<p>Hourly Pay Rate:"+hourlyPayRate+"</p>");
		out.println("<p>Tax Rate:"+taxRate+"</p>");
		out.println("<p>Gross Pay:"+grossPay+"</p>");
		out.println("<p>Tax:"+tax+"</p>");
		out.println("<p>Net Pay:"+netPay+"</p>");
		out.println("</body></html>");
	}
}
