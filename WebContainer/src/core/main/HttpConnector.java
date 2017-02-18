/*
 * 连接器 等待http请求 替代 HttpServer的该功能
 */
package core.main;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import core.processor.HttpProcessor;
import core.processor.Processor;

public class HttpConnector implements Runnable{
	private boolean stopped;
	private String scheme = "http";
	public String getScheme(){
		return scheme;
	}
	public void run(){
		ServerSocket serverSocket = null;
		int port = 8082;
		//创建Socket连接
		try{
			serverSocket = new ServerSocket(port,1,InetAddress.getByName("127.0.0.1"));
			System.out.println("serverSocket启动成功");
		}catch(IOException e){
			System.out.println("服务器启动失败");
			e.printStackTrace();
			System.exit(1);
		}
		//大循环接收http请求
		while(!stopped){
			Socket socket = null;
			
			try {
				socket = serverSocket.accept();
				System.out.println("新连接建立 from"+socket.getPort());
			} catch (IOException e) {
				System.out.println("连接失败");
				e.printStackTrace();
				continue;
			}
			//处理器来创建 response和 request 
			HttpProcessor processor = new HttpProcessor();
			processor.process(socket);
		}
		
	}
}
