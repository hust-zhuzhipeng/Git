package core.response;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;

import core.request.Request;

public class Response implements ServletResponse{
	private static final int BUFFER_SIZE = 1024;
	private Request request;
	private OutputStream output;
	private PrintWriter writer;
	
	public Response(OutputStream output){
		this.output = output;
	}
	public void serRequest(Request request){
		this.request=request;
	}
	
	//处理静态资源
	public void sendStaticResource() throws IOException{
		
	}
	
	@Override
	public PrintWriter getWriter() throws IOException {
		writer = new PrintWriter(output,true);
		return writer;
	}
	
	@Override
	public String getCharacterEncoding() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getContentType() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void setCharacterEncoding(String charset) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setContentLength(int len) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setContentType(String type) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setBufferSize(int size) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int getBufferSize() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void flushBuffer() throws IOException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void resetBuffer() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean isCommitted() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setLocale(Locale loc) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Locale getLocale() {
		// TODO Auto-generated method stub
		return null;
	}
}
