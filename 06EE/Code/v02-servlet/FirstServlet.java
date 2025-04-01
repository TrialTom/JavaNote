import javax.servlet.*;
import java.util.Date;

public class FirstServlet extends GenericServlet{
	public void service(ServletRequest req,ServletResponse res)throws ServletException,java.io.IOException{
		System.out.println("Hello World");
		res.getWriter().println(new Date());
	}
}