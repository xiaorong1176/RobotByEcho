package com.echo.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.Date;

import com.echo.bean.ChatMessage;
import com.echo.bean.Result;
import com.echo.bean.ChatMessage.Type;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class HttpUtils
{
	private static final String URL = "http://www.tuling123.com/openapi/api";
	private static final String API_KEY = "eab65836f5e872cd970d794a138da0e2";

	/**
	 * 发送一个消息，得到返回的消息
	 * @param msg
	 * @return
	 */
	//把工具类加强为一个返回一个ChatMessage消息
	//第三方jar Gson，将字符串转换成对象
	public static ChatMessage sendMessage(String msg)
	{
		ChatMessage chatMessage = new ChatMessage();
		//用户发送消息给服务器，得到一个返回结果（json字符串的结果），然后转换为ChatMessage的结果
		String jsonRes = doGet(msg);
		Gson gson = new Gson();
		//result进行接收 转换为result对象
		Result result = null;
		try
		{
			result = gson.fromJson(jsonRes, Result.class);
			chatMessage.setMsg(result.getText());
		} catch (Exception e)
		{
			chatMessage.setMsg("服务器繁忙，请稍候再试");
		}
		chatMessage.setDate(new Date());
		chatMessage.setType(Type.INCOMING);
		return chatMessage;
	}

	public static String doGet(String msg)
	{
		String result = "";
		String url = setParams(msg);
		ByteArrayOutputStream baos = null;
		InputStream is = null;
		try
		{
			java.net.URL urlNet = new java.net.URL(url);
			HttpURLConnection conn = (HttpURLConnection) urlNet
					.openConnection();
			conn.setReadTimeout(5 * 1000);
			conn.setConnectTimeout(5 * 1000);
			conn.setRequestMethod("GET");
			is = conn.getInputStream();  //通过GET拿到InputStream
			int len = -1;
			byte[] buf = new byte[128];
			baos = new ByteArrayOutputStream();

			while ((len = is.read(buf)) != -1)
			{
				baos.write(buf, 0, len);
			}
			baos.flush();
			result = new String(baos.toByteArray());
		} catch (MalformedURLException e)
		{
			e.printStackTrace();
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
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

	private static String setParams(String msg)
	{
		String url = "";
		try
		{
			url = URL + "?key=" + API_KEY + "&info="
					+ URLEncoder.encode(msg, "UTF-8");
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		return url;
	}

}

