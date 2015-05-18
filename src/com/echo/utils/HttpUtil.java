package com.echo.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

public class HttpUtil {
	private static final String URL = "";
	private static final String API_KEY = "";
	
	//���û�����һ����Ϣ��ƴ�ӳ�������URL,�õ�һ�����ؽ����
	public static String doGet(String msg){
		String result = "";
		String url = setParams(msg);
		InputStream is = null;
		ByteArrayOutputStream baos = null;
		try {
			java.net.URL urlNet = new java.net.URL(url);
			HttpURLConnection conn = (HttpURLConnection) urlNet.openConnection();
			conn.setReadTimeout(5 * 1000);
			conn.setConnectTimeout(5 * 1000);
			conn.setRequestMethod("GET");
			is = conn.getInputStream();  //ͨ��GET�õ�InputStream
			//ͨ���������õ���InputStreamת��ΪString���з���
			int len = -1;
			//����������
			byte[] buf = new byte[128];
			//��Ҫһ������ת��ΪString����
			 baos = new ByteArrayOutputStream();
			 //�����ݶ��������������û����β����lenth��=-1
			 while((len = is.read(buf)) != -1){
				 baos.write(buf, 0, len);
			 }
			 baos.flush();
			 result = new String(baos.toByteArray());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally{//�ر���Դ
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
		return result;
	}

	private static String setParams(String msg) {
		// TODO Auto-generated method stub
		String url = "";
		url = URL + "?key" + API_KEY + "&info" + msg;
		
		return url;
	}

}
