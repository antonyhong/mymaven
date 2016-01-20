package com.ming.email.Smaple;

public class MyMailData
{
	private String subject;
	private String msg;
	
	public MyMailData(String subject, String msg)
	{
		this.setSubject(subject);
		this.setMsg(msg);
	}
	
	public void setSubject(String subject)
	{
		this.subject = subject;
	}
	
	public String getSubject()
	{
		return this.subject;
	}
	
	public void setMsg(String msg)
	{
		this.msg = msg;
	}
	
	public String getMsg()
	{
		return this.msg;
	}
}
