package com.ryan.baidu.email.Smaple;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMultipart;

import com.ryan.baidu.utils.StringUtil;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

/**
 * �ʼ�����
 * ���߱��ʼ����͹���
 * 
 * �ʼ���Ϣ�������䴦������ӳ��������⣬�п����Ҳ�������������Ȼ�Ѿ���ȷָ����
 * 
 * @author zhangdan
 *
 */
public class MyMailManager
{
	private static MyMailManager instance = null;
	
	private MyMailSender mailSender;
	
	public static void main(String[] args)
	{
		try
		{
			Thread.currentThread().getContextClassLoader().loadClass("com.sun.mail.handlers.multipart_mixed");
			
			if(true)
			{
				MyMailSender mailSender = new MyMailSender("exchange-testandlearn.rd.com", null, "pub-testandlearn@rd.com", "pub-testandlearn", null, null, true);
				MyMailGetter mailGetter = new MyMailGetter();
				mailGetter.setCc("zhangdan@rd.com");
				mailGetter.setTo("guoxiumei@rd.com");
				MyMailManager.getInstance().init(mailSender);
				MyMailManager.getInstance().sendEmail(mailGetter, "xxxx", "�ŵ��������ã�");
				
				return;
			}
			
			System.out.println("start");
			MyMailSender mailSender = new MyMailSender("exchange-c.rd.com", null, "pub-testandlearn@rd.com", "pub-testandlearn", null, null, true);
			MyMailGetter mailGetter = new MyMailGetter("zhangdan@rd.com");
			mailGetter.setTo("zhangdan@rd.com");
			MyMailManager.getInstance().init(mailSender);
//			MyMailManager.getInstance().sendEmail(mailGetter, "sub", "txt");
			Collection<EmailAttachment> attachmentList = new ArrayList<EmailAttachment>();
			EmailAttachment attachment = new EmailAttachment();
			attachment.setPath("C:\\Documents and Settings\\zhangdan\\����\\lyb.xls");
			EmailAttachment attachment1 = new EmailAttachment();
			attachment1.setPath("C:\\Documents and Settings\\zhangdan\\����\\nginx.conf");
			attachmentList.add(attachment);
			attachmentList.add(attachment1);
//			MyMailManager.getInstance().sendMultiPartEmail(mailGetter, "sub", "<html><body><font color=ff0000>abc</font></body></html>", attachment);
			MyMailManager.getInstance().sendHtmlEmail(mailGetter, "sub", "testandlearn", "<html><body><font color=ff0000>Just fot testandlearn ...</font></body></html>", attachmentList, new Date());
			System.out.println("end");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	static
	{	
		init();
	}
	
	public static MyMailManager getInstance()
	{
		if(null == instance)
		{
			instance = new MyMailManager();
		}
		
		return instance;
	}
	
	private static void init()
	{
		/**
		 * ȥ���õ��ʼ�����
		 */
		MailcapCommandMap mcMap = (MailcapCommandMap)CommandMap.getDefaultCommandMap();
		mcMap.addMailcap("text/plain; ; x-java-content-handler=com.sun.mail.handlers.text_plain");
		mcMap.addMailcap("text/html; ; x-java-content-handler=com.sun.mail.handlers.text_html");
		mcMap.addMailcap("text/xml; ; x-java-content-handler=com.sun.mail.handlers.text_xml");
		mcMap.addMailcap("multipart/mixed; ; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
		mcMap.addMailcap("multipart/*; ; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
		mcMap.addMailcap("message/rfc822; ; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
		mcMap.addMailcap("image/gif; ; x-java-content-handler=com.sun.mail.handlers.image_gif");
		mcMap.addMailcap("image/jpeg; ; x-java-content-handler=com.sun.mail.handlers.image_jpeg");
		mcMap.addMailcap("text/*; ; x-java-view=com.sun.activation.viewers.TextViewer");
		mcMap.addMailcap("text/*; ; x-java-edit=com.sun.activation.viewers.TextEditor");
		CommandMap.setDefaultCommandMap(mcMap);
	}
	
	private MyMailManager()
	{
		
	}
	
	public void init(MyMailSender mailSender) throws EmailException 
	{
		this.setMailSender(mailSender);
	}

	public void init(String host, String port, String address, String password, String nickName, String encoding, boolean isNeedAuthenticate) throws EmailException
	{
		this.setMailSender(new MyMailSender(host, port, address, password, nickName, encoding, isNeedAuthenticate));
	}
	
	public synchronized void setMailSender(MyMailSender mailSender) throws EmailException 
	{
		if(null == mailSender)
		{
			throw new EmailException("MyMailManager: the mailSender is null");
		}
		
		this.mailSender = mailSender;
	}
	
	public String sendEmail(MyMailGetter mailGetter, String subject, String msg) throws EmailException
	{
		return this.sendSimpleEmail(mailGetter, subject, msg);
	}
	
	public String sendEmail(MyMailGetter mailGetter, String subject, String msg, Date date) throws EmailException
	{
		return this.sendSimpleEmail(mailGetter, subject, msg, date);
	}
	
	public String sendEmail(String to, String subject, String msg) throws EmailException
	{
		return this.sendSimpleEmail(to, subject, msg);
	}
	
	public String sendEmail(InternetAddress to, String subject, String msg) throws EmailException
	{
		return this.sendSimpleEmail(to, subject, msg);
	}
	
	public String sendEmail(Collection<InternetAddress> to, Collection<InternetAddress> cc, Collection<InternetAddress> bcc, Collection<InternetAddress> replyTo, String subject, String msg, Date date) throws EmailException
	{
		return this.sendSimpleEmail(to, cc, bcc, replyTo, subject, msg, date);
	}
	
	public String sendSimpleEmail(MyMailGetter mailGetter, String subject, String msg) throws EmailException
	{
		return this.sendSimpleEmail(mailGetter, subject, msg, null);
	}
	
	public String sendSimpleEmail(MyMailGetter mailGetter, String subject, String msg, Date date) throws EmailException
	{
		String rs = null;
		
		if(null != mailGetter)
		{
			rs = this.sendSimpleEmail(mailGetter.getTo(), mailGetter.getCc(), mailGetter.getBcc(), mailGetter.getReplyTo(), subject, msg, date);
		}
		
		return rs;
	}
	
	public String sendSimpleEmail(String to, String subject, String msg) throws EmailException
	{
		InternetAddress ia = new InternetAddress();
		ia.setAddress(to);
		Collection<InternetAddress> temp = new ArrayList<InternetAddress>();
		temp.add(ia);
		
		return this.sendSimpleEmail(temp, null, null, null, subject, msg, null);
	}
	
	public String sendSimpleEmail(InternetAddress to, String subject, String msg) throws EmailException
	{
		Collection<InternetAddress> temp = new ArrayList<InternetAddress>();
		temp.add(to);
		
		return this.sendSimpleEmail(temp, null, null, null, subject, msg, null);
	}
	
	public String trySendSimpleEmail(int time, Collection<InternetAddress> to, Collection<InternetAddress> cc, Collection<InternetAddress> bcc, Collection<InternetAddress> replyTo, String subject, String msg, Date date) throws EmailException
	{
		String rs = null;
		time = 0 >= time ? 1 : time;
		
		for(int i = 0; i < time; i++)
		{
			try
			{
				rs = this.sendSimpleEmail(to, cc, bcc, replyTo, subject, msg, date);
				
				break;
			}
			catch(EmailException e)
			{
				if(time - 1 == i)
				{
					throw e;
				}
				
				this.loadMailDataHandler(time);
			}
		}
		
		return rs;
	}
	
	public synchronized String sendSimpleEmail(Collection<InternetAddress> to, Collection<InternetAddress> cc, Collection<InternetAddress> bcc, Collection<InternetAddress> replyTo, String subject, String msg, Date date) throws EmailException
	{
		SimpleEmail email = this.mailSender.createSimpleEmail();
		this.setEmailReceiver(email, to, cc, bcc, replyTo);		
		email.setSubject(subject);
		
		if(null != msg)
		{
			email.setMsg(msg);
		}
		
		if(null != date)
		{
			email.setSentDate(date);
		}
		
		return email.send();
	}
	
	public String sendMultiPartEmail(MyMailGetter mailGetter, String subject, String msg, EmailAttachment attachment) throws EmailException
	{
		return this.sendMultiPartEmail(mailGetter, subject, msg, attachment, null);
	}
	
	public String sendMultiPartEmail(MyMailGetter mailGetter, String subject, String msg, EmailAttachment attachment, Date date) throws EmailException
	{
		String rs = null;
		
		if(null != mailGetter)
		{
			rs = this.sendMultiPartEmail(mailGetter.getTo(), mailGetter.getCc(), mailGetter.getBcc(), mailGetter.getReplyTo(), subject, msg, attachment, date);
		}
		
		return rs;
	}
	
	public String sendMultiPartEmail(MyMailGetter mailGetter, String subject, String msg, Collection<EmailAttachment> attachmentList, Date date) throws EmailException
	{
		String rs = null;
		
		if(null != mailGetter)
		{
			rs = this.sendMultiPartEmail(mailGetter.getTo(), mailGetter.getCc(), mailGetter.getBcc(), mailGetter.getReplyTo(), subject, msg, attachmentList, date);
		}
		
		return rs;
	}
	
	public String sendMultiPartEmail(String to, String subject, String msg, EmailAttachment attachMent) throws EmailException
	{
		InternetAddress ia = new InternetAddress();
		ia.setAddress(to);
		Collection<InternetAddress> temp = new ArrayList<InternetAddress>();
		temp.add(ia);
		
		return this.sendMultiPartEmail(temp, null, null, null, subject, msg, attachMent, null);
	}
	
	public String sendMultiPartEmail(InternetAddress to, String subject, String msg, EmailAttachment attachMent) throws EmailException
	{
		Collection<InternetAddress> temp = new ArrayList<InternetAddress>();
		temp.add(to);
		
		return this.sendMultiPartEmail(temp, null, null, null, subject, msg, attachMent, null);
	}
	
	public String sendMultiPartEmail(Collection<InternetAddress> to, Collection<InternetAddress> cc, Collection<InternetAddress> bcc, Collection<InternetAddress> replyTo, String subject, String msg, EmailAttachment attachment, Date date) throws EmailException
	{
		Collection<EmailAttachment> attachmentList = null;
		
		if(null != attachment)
		{
			attachmentList = new ArrayList<EmailAttachment>();
			attachmentList.add(attachment);
		}
		
		return this.sendMultiPartEmail(to, cc, bcc, replyTo, subject, msg, attachmentList, date);
	}
	
	public String trySendMultiPartEmail(int time, Collection<InternetAddress> to, Collection<InternetAddress> cc, Collection<InternetAddress> bcc, Collection<InternetAddress> replyTo, String subject, String msg, Collection<EmailAttachment> attachmentList, Date date) throws EmailException
	{
		String rs = null;
		time = 0 >= time ? 1 : time;
		
		for(int i = 0; i < time; i++)
		{
			try
			{
				rs = this.sendMultiPartEmail(to, cc, bcc, replyTo, subject, msg, attachmentList, date);
				
				break;
			}
			catch(EmailException e)
			{
				if(time - 1 == i)
				{
					throw e;
				}
				
				this.loadMailDataHandler(time);
			}
		}
		
		return rs;
	}
	
	public synchronized String sendMultiPartEmail(Collection<InternetAddress> to, Collection<InternetAddress> cc, Collection<InternetAddress> bcc, Collection<InternetAddress> replyTo, String subject, String msg, Collection<EmailAttachment> attachmentList, Date date) throws EmailException
	{
		MultiPartEmail email = this.mailSender.createMultiPartEmail();
		this.setEmailReceiver(email, to, cc, bcc, replyTo);
		email.setContent(new MimeMultipart());
		email.setSubject(subject);
		
		if(null != msg)
		{
			email.setMsg(msg);
		}
		
		if(null != attachmentList)
		{
			for(EmailAttachment attachment : attachmentList)
			{
				if(null != attachment)
				{
					email.attach(attachment);
				}
			}
		}
		
		if(null != date)
		{
			email.setSentDate(date);
		}
		
		return email.send();
	}
	
	public String sendHtmlEmail(MyMailGetter mailGetter, String subject, String htmlMsg) throws EmailException
	{
		EmailAttachment attachment = null;
		
		return this.sendHtmlEmail(mailGetter, subject, null, htmlMsg, attachment, null);
	}
	
	public String sendHtmlEmail(MyMailGetter mailGetter, String subject, String htmlMsg, EmailAttachment attachment) throws EmailException
	{
		return this.sendHtmlEmail(mailGetter, subject, null, htmlMsg, attachment, null);
	}
	
	public String sendHtmlEmail(MyMailGetter mailGetter, String subject, String htmlMsg, EmailAttachment attachment, Date date) throws EmailException
	{
		return this.sendHtmlEmail(mailGetter, subject, null, htmlMsg, attachment, date);
	}
	
	public String sendHtmlEmail(MyMailGetter mailGetter, String subject, String textMsg, String htmlMsg, EmailAttachment attachment, Date date) throws EmailException
	{
		String rs = null;
		
		if(null != mailGetter)
		{
			rs = this.sendHtmlEmail(mailGetter.getTo(), mailGetter.getCc(), mailGetter.getBcc(), mailGetter.getReplyTo(), subject, textMsg, htmlMsg, attachment, date);
		}
		
		return rs;
	}
	
	public String sendHtmlEmail(MyMailGetter mailGetter, String subject, String textMsg, String htmlMsg, Collection<EmailAttachment> attachmentList, Date date) throws EmailException
	{
		String rs = null;
		
		if(null != mailGetter)
		{
			rs = this.sendHtmlEmail(mailGetter.getTo(), mailGetter.getCc(), mailGetter.getBcc(), mailGetter.getReplyTo(), subject, textMsg, htmlMsg, attachmentList, date);
		}
		
		return rs;
	}
	
	public String sendHtmlEmail(String to, String subject, String htmlMsg, EmailAttachment attachment) throws EmailException
	{
		InternetAddress ia = new InternetAddress();
		ia.setAddress(to);
		Collection<InternetAddress> temp = new ArrayList<InternetAddress>();
		temp.add(ia);
		
		return this.sendHtmlEmail(temp, null, null, null, subject, null, htmlMsg, attachment, null);
	}
	
	public String sendHtmlEmail(InternetAddress to, String subject, String htmlMsg, EmailAttachment attachment) throws EmailException
	{
		Collection<InternetAddress> temp = new ArrayList<InternetAddress>();
		temp.add(to);
		
		return this.sendHtmlEmail(temp, null, null, null, subject, null, htmlMsg, attachment, null);
	}
	
	public String sendHtmlEmail(Collection<InternetAddress> to, Collection<InternetAddress> cc, Collection<InternetAddress> bcc, Collection<InternetAddress> replyTo, String subject, String textMsg, String htmlMsg, EmailAttachment attachment, Date date) throws EmailException
	{
		Collection<EmailAttachment> attachmentList = null;
		
		if(null != attachment)
		{
			attachmentList = new ArrayList<EmailAttachment>();
			attachmentList.add(attachment);
		}
		
		return this.sendHtmlEmail(to, cc, bcc, replyTo, subject, textMsg, htmlMsg, attachmentList, date);
	}
	
	public String trySendHtmlEmail(int time, Collection<InternetAddress> to, Collection<InternetAddress> cc, Collection<InternetAddress> bcc, Collection<InternetAddress> replyTo, String subject, String textMsg, String htmlMsg, Collection<EmailAttachment> attachmentList, Date date) throws EmailException
	{
		String rs = null;
		time = 0 >= time ? 1 : time;
		
		for(int i = 0; i < time; i++)
		{
			try
			{
				rs = this.sendHtmlEmail(to, cc, bcc, replyTo, subject, textMsg, htmlMsg, attachmentList, date);
				
				break;
			}
			catch(EmailException e)
			{
				if(time - 1 == i)
				{
					throw e;
				}
				
				this.loadMailDataHandler(time);
			}
		}
		
		return rs;
	}
	
	public synchronized String sendHtmlEmail(Collection<InternetAddress> to, Collection<InternetAddress> cc, Collection<InternetAddress> bcc, Collection<InternetAddress> replyTo, String subject, String textMsg, String htmlMsg, Collection<EmailAttachment> attachmentList, Date date) throws EmailException
	{
		HtmlEmail email = this.mailSender.createHtmlEmail();
		this.setEmailReceiver(email, to, cc, bcc, replyTo);		
		email.setSubject(subject);
		
		if(null != textMsg)
		{
			email.setTextMsg(textMsg);
		}
		
		if(null != htmlMsg)
		{
			email.setHtmlMsg(htmlMsg);
		}
		
		if(null != attachmentList)
		{
			for(EmailAttachment attachment : attachmentList)
			{
				if(null != attachment)
				{
					email.attach(attachment);
				}
			}
		}
		
		if(null != date)
		{
			email.setSentDate(date);
		}
		
		return email.send();
	}
	
	public Collection<InternetAddress> createInternetAddress(String[] addressArray) throws AddressException
	{
		Collection<InternetAddress> rs = null;
		
		if(null != addressArray)
		{
			rs = new ArrayList<InternetAddress>();
			
			for(String address : addressArray)
			{
				InternetAddress internetAddress = this.createInternetAddress(address);
				
				if(null != internetAddress)
				{
					rs.add(internetAddress);
				}
			}
		}
		
		return rs;
	}
	
	public Collection<InternetAddress> createInternetAddress(Collection<String> addressCollection) throws AddressException
	{
		Collection<InternetAddress> rs = null;
		
		if(null != addressCollection)
		{
			rs = new ArrayList<InternetAddress>();
			
			for(String address : addressCollection)
			{
				InternetAddress internetAddress = this.createInternetAddress(address);
				
				if(null != internetAddress)
				{
					rs.add(internetAddress);
				}
			}
		}
		
		return rs;
	}
	
	public InternetAddress createInternetAddress(String address) throws AddressException
	{
		InternetAddress rs = null;
		if(!StringUtil.isBlankStr(address))
		{
			rs = new InternetAddress(address.trim());
		}
		
		return rs;
	}
	
	public EmailAttachment createEmailAttachment(String path)
	{
		EmailAttachment rs = null;
		
		if(null != path)
		{
			rs = new EmailAttachment();
			rs.setPath(path);
		}
		
		return rs;
	}
	
	public EmailAttachment createEmailAttachment(File file)
	{
		EmailAttachment rs = null;
		
		if(null != file)
		{
			rs = new EmailAttachment();
			rs.setPath(file.getAbsolutePath());
		}
		
		return rs;
	}
	
	private void setEmailReceiver(Email email, Collection<InternetAddress> to, Collection<InternetAddress> cc, Collection<InternetAddress> bcc, Collection<InternetAddress> replyTo) throws EmailException
	{
		if(null != to && !to.isEmpty())
		{
			email.setTo(to);
		}
		
		if(null != cc && !cc.isEmpty())
		{
			email.setCc(cc);
		}
		
		if(null != bcc && !bcc.isEmpty())
		{
			email.setBcc(bcc);
		}
		
		if(null != replyTo && !replyTo.isEmpty())
		{
			email.setReplyTo(replyTo);
		}
	}
	
	private void loadMailDataHandler(int time) throws EmailException
	{
		try
		{
			if(0 == time % 2)
			{
				Thread.currentThread().setContextClassLoader(MyMailManager.class.getClassLoader());
			}
			else
			{
				ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
				
				if(null == contextClassLoader)
				{
					Thread.currentThread().setContextClassLoader(MyMailManager.class.getClassLoader());
				}
				
				if(null != contextClassLoader)
				{
					contextClassLoader.loadClass("com.sun.mail.handlers.text_plain");
					contextClassLoader.loadClass("com.sun.mail.handlers.text_html");
					contextClassLoader.loadClass("com.sun.mail.handlers.text_xml");
					contextClassLoader.loadClass("com.sun.mail.handlers.multipart_mixed");
					contextClassLoader.loadClass("com.sun.mail.handlers.message_rfc822");
					contextClassLoader.loadClass("com.sun.mail.handlers.image_gif");
					contextClassLoader.loadClass("com.sun.mail.handlers.image_jpeg");
					contextClassLoader.loadClass("com.sun.activation.viewers.TextViewer");
					contextClassLoader.loadClass("com.sun.activation.viewers.TextEditor");
				}
			}
		}
		catch(ClassNotFoundException e)
		{
			throw new EmailException(e);
		}
	}
}
