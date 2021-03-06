package com.park.oss.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * http://blog.csdn.net/iijse/article/details/6201101
 */
public class HttpUtil {
	
	static ExecutorService  pool = Executors.newFixedThreadPool(5) ;
	
	public static String get(String urlName) { 
		System.out.println("get:"+urlName);
		 String result = null;  
	        BufferedReader in = null;  
	        try {  
//	            String urlName = url + "?" + param;  
	            URL realUrl = new URL(urlName);  
	            // 打开和URL之间的连接  
	            URLConnection conn = realUrl.openConnection();  
	            // 设置通用的请求属性  
	            conn.setRequestProperty("accept", "*/*");  
	            conn.setRequestProperty("connection", "Keep-Alive");  
	            conn.setRequestProperty("user-agent",  
	                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");  
	            // 建立实际的连接  
	            conn.connect();  
//	            // 获取所有响应头字段  
//	            Map<String, List<String>> map = conn.getHeaderFields();  
	           
	            // 定义BufferedReader输入流来读取URL的响应  
	            in = new BufferedReader(  
	                    new InputStreamReader(conn.getInputStream()));  
	            String line="";  
	            String content = "";
	            while ((line = in.readLine()) != null) {  
	            	content +=   line+"\n";  
	            }  
	            result = content;
	        } catch (Exception e) {  
	        		System.out.println("error:"+urlName);
	            System.out.println("发送GET请求出现异常！" + e);  
	            return null;
	          
	        }  
	        // 使用finally块来关闭输入流  
	        finally {  
	            try {  
	                if (in != null) {  
	                    in.close();  
	                }  
	            } catch (IOException ex) {  
	            	 return null;
	            }  
	        }  
	        return result;  
	}
	
	public static String get(final String url, final String param) {  
       return get(url+"?"+param);
    }  
	
	public static void getNotify(final String url,final String param){
		pool.execute(new Runnable(){

			public void run() {
				get(url,param);
				
			}
			
		});
	}
	
	public static void main(String[] args){
		String url = "http://127.0.0.1:9000/api/resources?resource=my:beetlsql&metrics=ncloc,coverage";
		String ret = get(url);
		System.out.println(ret);
	}
}
