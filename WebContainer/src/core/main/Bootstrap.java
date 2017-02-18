/*
 * 启动应用程序 替代HttpServer
 */
package core.main;

public final class Bootstrap {
	public static void main(String[]args){
		HttpConnector connector = new HttpConnector();
		new Thread(connector).start();
	}
}
