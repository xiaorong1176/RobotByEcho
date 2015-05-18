package com.echo.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.security.PublicKey;

import org.apache.http.HttpConnection;

import android.database.CursorJoiner.Result;

public class HttpUtil {
	private static final String URL = "";
	private static final String API_KEY = "";
	
	//由用户输入一条消息，拼接成完整的URL,得到一个返回结果。
	public static String doGet(String msg){
		String result = "";
		String url = setParams(msg);
		return result;
	}

	private static String setParams(String msg) {
		// TODO Auto-generated method stub
		String url = "";
		url = URL + "?key" + API_KEY + "&info" + msg;
		InputStream is = null;
		ByteArrayOutputStream baos = null;
		try {
			java.net.URL urlNet = new java.net.URL(url);
			HttpURLConnection conn = (HttpURLConnection) urlNet.openConnection();
			conn.setReadTimeout(5 * 1000);
			conn.setConnectTimeout(5 * 1000);
			conn.setRequestMethod("GET");
			is = conn.getInputStream();  //通过GET拿到InputStream
			//通过服务器拿到的InputStream转换为String进行返回
			int len = -1;
			//声明缓冲区
			byte[] buf = new byte[128];
			//需要一个把流转换为String的类
			 baos = new ByteArrayOutputStream();
			 //将内容读到缓冲区，如果没到结尾，及lenth！=-1
			 while((len = is.read(buf)) != -1){
				 baos.write(buf, 0, len);
			 }
			 baos.flush();
			 String result = new String(baos.toByteArray());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally{//关闭资源
				try
				{
					if (baos != null)
						baos.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}

				try
				{
					if (is != null)
					{
						is.close();
					}
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		return url;
	}

}
