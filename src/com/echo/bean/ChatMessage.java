package com.echo.bean;

import java.util.Date;

public class ChatMessage
{
	private String name;
	private String msg;
	private Type type;//发送消息或者接收消息
	private Date date;//时间

	public enum Type//枚举
	{
		INCOMING, OUTCOMING
	}
	
	public ChatMessage()
	{
	}
	
	

	public ChatMessage(String msg, Type type, Date date)
	{
		super();
		this.msg = msg;
		this.type = type;
		this.date = date;
	}



	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getMsg()
	{
		return msg;
	}

	public void setMsg(String msg)
	{
		this.msg = msg;
	}

	public Type getType()
	{
		return type;
	}

	public void setType(Type type)
	{
		this.type = type;
	}

	public Date getDate()
	{
		return date;
	}
	public void setDate(Date date)
	{
		this.date = date;
	}

}
