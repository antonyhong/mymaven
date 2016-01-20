package com.ming.email.Smaple;


import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

public class MyMultiPartEmail extends MultiPartEmail
{
	@Override
	public Email setMsg(String msg) throws EmailException
	{
        return super.setMsg(msg);
	}
}
