package com.ming.email.Smaple;

import java.util.ArrayList;
import java.util.Collection;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class MyMailGetter
{
	private Collection<InternetAddress> to;
	private Collection<InternetAddress> cc;
	private Collection<InternetAddress> bcc;
	private Collection<InternetAddress> replyTo;
	
	public MyMailGetter()
	{
		
	}
	
	public MyMailGetter(String to) throws AddressException
	{
		this.setTo(to);
	}
	
	public MyMailGetter(InternetAddress to)
	{
		this.setTo(to);
	}
	
	public MyMailGetter(Collection<InternetAddress> to)
	{
		this.setTo(to);
	}
	
	public void setTo(String to) throws AddressException
	{
		this.setTo(MyMailManager.getInstance().createInternetAddress(to));
	}
	
	public void setTo(InternetAddress to)
	{
		Collection<InternetAddress> temp = null;
		
		if(null != to)
		{
			temp = new ArrayList<InternetAddress>();
			temp.add(to);
		}
		
		this.setTo(temp);
	}
	
	public void setTo(Collection<InternetAddress> to)
	{
		this.to = to;
	}
	
	public Collection<InternetAddress> getTo()
	{
		return this.to;
	}
	
	public void setCc(String cc) throws AddressException
	{
		this.setCc(MyMailManager.getInstance().createInternetAddress(cc));
	}
	
	public void setCc(InternetAddress cc)
	{
		Collection<InternetAddress> temp = null;
		
		if(null != cc)
		{
			temp = new ArrayList<InternetAddress>();
			temp.add(cc);
		}
		
		this.setCc(temp);
	}
	
	public void setCc(Collection<InternetAddress> cc)
	{
		this.cc = cc;
	}
	
	public Collection<InternetAddress> getCc()
	{
		return this.cc;
	}
	
	public void setBcc(String bcc) throws AddressException
	{
		this.setBcc(MyMailManager.getInstance().createInternetAddress(bcc));
	}
	
	public void setBcc(InternetAddress bcc)
	{
		Collection<InternetAddress> temp = null;
		
		if(null != bcc)
		{
			temp = new ArrayList<InternetAddress>();
			temp.add(bcc);
		}
		
		this.setBcc(temp);
	}
	
	public void setBcc(Collection<InternetAddress> bcc)
	{
		this.bcc = bcc;
	}
	
	public Collection<InternetAddress> getBcc()
	{
		return this.bcc;
	}
	
	public void setReplyTo(String replyTo) throws AddressException
	{
		this.setReplyTo(MyMailManager.getInstance().createInternetAddress(replyTo));
	}
	
	public void setReplyTo(InternetAddress replyTo)
	{
		Collection<InternetAddress> temp = null;
		
		if(null != replyTo)
		{
			temp = new ArrayList<InternetAddress>();
			temp.add(replyTo);
		}
		
		this.setReplyTo(temp);
	}
	
	public void setReplyTo(Collection<InternetAddress> replyTo)
	{
		this.replyTo = replyTo;
	}
	
	public Collection<InternetAddress> getReplyTo()
	{
		return this.replyTo;
	}
	
	public Collection<InternetAddress> getAddress()
	{
		Collection<InternetAddress> rs = new ArrayList<InternetAddress>();
		
		if(null != this.to)
		{
			rs.addAll(this.to);
		}
		
		if(null != this.cc)
		{
			rs.addAll(this.cc);
		}
		
		if(null != this.bcc)
		{
			rs.addAll(this.bcc);
		}
		
		if(null != this.replyTo)
		{
			rs.addAll(this.replyTo);
		}
		
		return rs;
	}
}
