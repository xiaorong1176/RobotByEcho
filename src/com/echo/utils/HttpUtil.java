package com.echo.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

import org.apache.http.HttpConnection;

public class HttpUtil {
	private static final String URL = "";
	private static final String API_KEY = "";
	
	//���û�����һ����Ϣ��ƴ�ӳ�������URL,�õ�һ�����ؽ����
	public static String doGet(String msg){
		String result = null;
		String url = setParams(msg);
		return result;
	}

	private static String setParams(String msg) {
		// TODO Auto-generated method stub
		String url = "";
		url = URL + "?key" + API_KEY + "&info" + msg;
		try {
			java.net.URL urlNet = new java.net.URL(url);
			HttpURLConnection conn = (HttpURLConnection) urlNet.openConnection();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		return url;
	}

}
