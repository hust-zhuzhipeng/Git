package core.processor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import core.request.Request;
import core.response.Response;

public class ServletProcessor1 implements Processor{

	@Override
	public void process(Request request, Response response) {
		String uri = request.getUri();
		System.out.println(uri);
		System.out.println(uri.lastIndexOf('/')+1);
		String ser = uri.substring(uri.lastIndexOf('/')+1);
		System.out.println(ser);
		try {
			Servlet servlet = (Servlet)Class.forName("core.servlet."+ser).newInstance();
			servlet.service(request, response);
		} catch(Exception e){
			System.out.println("Servlet NOT FOUND");
			String errorMessage="Http:/1.1 404 File Not Found\r\n"+
					"Content-Type: text/html\r\n"+
					"Content-Length: 23\r\n"+
					"\r\n"+
					"<h1>File Not Found</h1>";
			
			try {
				response.getOutputStream().write(errorMessage.getBytes());
				response.getOutputStream().flush();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}
	
}
