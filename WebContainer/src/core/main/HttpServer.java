package core.main;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import core.processor.Processor;
import core.processor.ServletProcessor1;
import core.processor.StaticResourceProcessor;
import core.request.Request;
import core.response.Response;

/*
 * 服务器入口 单循环
 */
public class HttpServer implements IHttpServer{
	// 终止指令
	private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";
	private boolean shutdown = false;
	
	public static void main(String[]args){
		HttpServer hs = new HttpServer();
		hs.await();
	}

	@Override
	public void await() {
		ServerSocket serversocket = null;
		int port = 8082;
		try{
			serversocket = new ServerSocket(port,1,InetAddress.getByName("127.0.0.1"));
		}catch(IOException e){
			System.out.println("ServerSocket build error"+"\n");
			e.printStackTrace();
		}
		
		//保持连接
		while(!shutdown){
			Socket socket = null;
			InputStream is = null;
			OutputStream os = null;
			try {
				socket = serversocket.accept();
				System.out.println("welcome");
				is = socket.getInputStream();
				os = socket.getOutputStream();
				
				//创建request
				Request request = new Request(is);
				request.parse();
				//创建response
				Response response = new Response(os);
				response.serRequest(request);
				
				String uri=request.getUri();
				System.out.println("uri:"+uri);
				if(uri.startsWith("/servlet")){
					System.out.println("进入Servlet");
					Processor processor = new ServletProcessor1();
					processor.process(request, response);
				}else{
					System.out.println("进入静态");
					Processor processor = new StaticResourceProcessor();
					processor.process(request, response);
				}
				socket.close();
				shutdown = request.getUri().equals(SHUTDOWN_COMMAND);
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("close the server！");
				System.exit(1);
			}
		}
	}
	
}











