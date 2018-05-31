package com.ryan.baidu.email.Smaple;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

/**
 * �ʼ�������
 * 
 * @author zhangdan
 *
 */
public class MyMailSender
{
	public static final String UNDEFINED_CONTENT = " ";
    public static final int DEFAULT_PORT_SMTP = 25;
    public static final int DEFAULT_PORT_SSL_SMTP = 465;
    public static final String DEFAULT_ENCODING = "UTF-8";
    
	private SimpleEmail simpleEmail;
	private MultiPartEmail multiPartEmail;
	private HtmlEmail htmlEmail;
	
	private String host;
	private String port;
	private String address;
	private String password;
	private String nickName;
	private String encoding;
	private boolean isNeedAuthenticate;
	
	public MyMailSender(String host, String port, String address, String password, String nickName, String encoding, boolean isNeedAuthenticate) throws EmailException
	{
		this.setHost(host);
		this.setPort(port);
		this.setAddress(address);
		this.setPassword(password);
		this.setNickName(nickName);
		this.setEncoding(encoding);
		this.setNeedAuthenticate(isNeedAuthenticate);
	}
		
	public String getHost()
	{
		return this.host;
	}
	
	public String getPort()
	{
		return this.port;
	}
	
	public String getAddress()
	{
		return this.address;
	}
	
	public String getPassword()
	{
		return this.password;
	}
	
	public String getNickName()
	{
		return this.nickName;
	}
	
	public String getEncoding()
	{
		return this.encoding;
	}
	
	public boolean isNeedAuthenticate()
	{
		return this.isNeedAuthenticate;
	}
	
	public SimpleEmail createSimpleEmail() throws EmailException
	{
		this.reset();
		
		if(null == this.simpleEmail)
		{
			this.simpleEmail = new SimpleEmail();
			this.simpleEmail.setHostName(this.host);
			this.simpleEmail.setFrom(this.address, this.nickName, this.encoding);
			this.simpleEmail.setCharset(this.encoding);
			
			if(this.isNeedAuthenticate)
			{
				this.simpleEmail.setAuthentication(this.address, this.password);
			}
		}
		
		return this.simpleEmail;
	}
	
	public MultiPartEmail createMultiPartEmail() throws EmailException
	{
		this.reset();
		
		if(null == this.multiPartEmail)
		{
			this.multiPartEmail = new MultiPartEmail();
			this.multiPartEmail.setHostName(this.host);
			this.multiPartEmail.setFrom(this.address, this.nickName, this.encoding);
			this.multiPartEmail.setCharset(this.encoding);
			
			if(this.isNeedAuthenticate)
			{
				this.multiPartEmail.setAuthentication(this.address, this.password);
			}
		}
		
		return this.multiPartEmail;
	}
	
	public HtmlEmail createHtmlEmail() throws EmailException
	{
		this.reset();
		
		if(null == this.htmlEmail)
		{
			this.htmlEmail = new HtmlEmail();
			this.htmlEmail.setHostName(this.host);
			this.htmlEmail.setFrom(this.address, this.nickName, this.encoding);
			this.htmlEmail.setCharset(this.encoding);
			
			if(this.isNeedAuthenticate)
			{
				this.htmlEmail.setAuthentication(this.address, this.password);
			}
		}
		
		return this.htmlEmail;
	}
	
	private void setHost(String host) throws EmailException
	{
		if(null == host || host.isEmpty())
		{
			throw new EmailException("MyMailSender: the host is null");
		}
		
		this.host = host.trim();
	}
	
	private void setPort(String port)
	{
		if(null == port || port.isEmpty())
		{
			port = String.valueOf(DEFAULT_PORT_SMTP);
		}
		
		this.port = port.trim();
	}
	
	private void setAddress(String address) throws EmailException
	{
		if(null == address || address.isEmpty() || -1 == address.indexOf("@"))
		{
			throw new EmailException("MyMailSender: the address is not available");
		}
		
		this.address = address.trim();
	}
	
	private void setPassword(String password)
	{
		this.password = password;
	}
	
	private void setNickName(String nickName)
	{
		if(null == nickName || nickName.isEmpty())
		{
			int index = this.address.indexOf("@");
			nickName = this.address.substring(0, index);
		}
		
		this.nickName = nickName.trim();
	}
	
	private void setEncoding(String encoding)
	{
		if(null == encoding || encoding.isEmpty())
		{
			encoding = DEFAULT_ENCODING;
		}
		
		this.encoding = encoding.trim();
	}
	
	private void setNeedAuthenticate(boolean isNeedAuthenticate) throws EmailException
	{
		this.isNeedAuthenticate = isNeedAuthenticate;
		
		if(this.isNeedAuthenticate && null == this.password)
		{
			throw new EmailException("MyMailSender: the password is null");
		}
	}
	
	private void reset() throws EmailException
	{
		if(null != this.simpleEmail)
		{
			this.simpleEmail = null;
		}
		
		if(null != this.multiPartEmail)
		{
			this.multiPartEmail = null;
		}
		
		if(null != this.htmlEmail)
		{
			this.htmlEmail = null;
		}
	}
}
