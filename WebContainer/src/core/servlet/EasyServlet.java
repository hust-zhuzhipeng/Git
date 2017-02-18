package core.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EasyServlet implements Servlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("EasyServlet init");
	}
	@Override
	public void destroy() {
		System.out.println("EasyServlet destroy");
	}
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		System.out.println("EasyServlet service");
		PrintWriter out=res.getWriter();
		out.println("Hello! form EasyServlet~");
		out.flush();
	}
	
	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	


}
