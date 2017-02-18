/*
 * 将http请求进行解析 
 * 解析为协议-状态码-描述  响应头 相应实体
 */
package core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class SocketInputStream {
	private InputStream is;
	private final int BUFFER_SIZE;
	private String total;
	private String header;
	private Map<String,String> headmap;
	private String context;
	public SocketInputStream(InputStream is,int BUFFER_SIZE){
		this.is=is;
		this.BUFFER_SIZE=BUFFER_SIZE;
		init();
	}
	public String getTotal(){
		return total;
	}
	public String getHeader(){
		return header;
	}
	public String getContext(){
		return context;
	}
	public Map<String , String> getHeadMap(){
		return headmap;
	}
	//取出http放入tatal
	private void init(){
		int i;
    	byte[] buffer = new byte[BUFFER_SIZE];
    	StringBuffer sb = new StringBuffer(BUFFER_SIZE);
    	
    	try{
    		i = is.read(buffer);
    	}catch(IOException e){
    		e.printStackTrace();
    		i=-1;
    	}
    	for(int j=0;j<i;j++){
    		sb.append((char)buffer[j]);
    	}
    	total = sb.toString();
    	headmap = new HashMap<>();
    	generateHeader();
    	generateContext();
    	generateHeadmap();
	}
	private void generateHeader(){
		int index = total.indexOf("\r\n");
		header = total.substring(0, index);
	}
	private void generateContext(){
		int index = total.indexOf("\r\n\r\n");
		context = total.substring(index+4);
	}
	private void generateHeadmap(){
		String[] str = total.split("\r\n");
		for(int i=1;i<str.length-2;i++){
			insertInToMap(str[i]);
		}
		
	}
	private void insertInToMap(String str){
		int index = str.indexOf(':');
		String key = str.substring(0, index);
		String value = str.substring(index+2);
		headmap.put(key, value);
	}
	public static void main(String[]args){
		File f = new File("C:\\JavaEE\\zzp.txt");
		try {
			InputStream is = new FileInputStream(f);
			SocketInputStream sis = new SocketInputStream(is,1024);
			System.out.println("head:"+sis.getHeader());
			System.out.println("context:"+sis.getContext());
			Map<String,String> map = sis.getHeadMap();
			for(String key:map.keySet()){
				System.out.println(key+"="+map.get(key));
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
