package com.ming.email;

import org.apache.commons.mail.*;
import sun.misc.BASE64Encoder;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by hongyongming on 2015/9/22.
 */
public class CommonEmailTest {
    private static final String Host_sina = "smtp.sina.com";
    private static final String UserName = "fellowming@sina.com";
    private static final String PassWord = "sn666666qq";

    public static void main(String args[]) throws EmailException, MalformedURLException {
        // test_outlook();
        //test_sina();
        //test_sina();
        //test_MutilFileMail();
        test_Html();
    }

    private static void test_163() {
        SimpleEmail email = new SimpleEmail();
        // email.setTLS(true);
        email.setHostName("smtp.163.com");
        email.setAuthentication("fellowming@163.com", "666666qq");//你的邮箱帐号和密码
        try {
            email.addTo("6107799239@qq.com");  //接收方
            email.setFrom("fellowming@163.com"); //发送方
            //email.setCharset("GBK");            //字符编码方式
            email.setSubject("Java mail testandlearn!");   //邮件标题
            email.setMsg("This is a testandlearn");   //内容
            email.send();

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("test2_ok");
    }


    private static void test_outlook() {
        SimpleEmail email = new SimpleEmail();
        //email.setTLS(true);
        //databaseccr-pa.rd.com
        email.setHostName("exchange-testandlearn.rd.com");
        // email.setHostName("databaseccr-pa.rd.com");
        email.setAuthentication("hongyongming@rd.com", "222222");//你的邮箱帐号和密码
        try {
            email.addTo("hongyongming@rd.com");  //接收方
            email.setFrom("hongyongming@rd.com"); //发送方
            email.setCharset("GBK");            //字符编码方式
            email.setSubject("Java mail testandlearn!");   //邮件标题
            email.setMsg("This is a testandlearn");   //内容
            email.send();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("test_outlook_err");
            return;
        }
        System.out.println("test_outlook_ok");
    }
    private static void test_sina() {
        SimpleEmail email = new SimpleEmail();
        email.setTLS(true);
        email.setHostName(Host_sina);
        email.setAuthentication(UserName,PassWord);//你的邮箱帐号和密码
        try {
            email.addTo("610799239@qq.com");  //接收方
            email.setFrom("fellowming@sina.com"); //发送方
            email.setCharset("GBK");            //字符编码方式
            email.setSubject("Java mail testandlearn!");   //邮件标题
            email.setMsg("This is a testandlearn");   //内容
            email.send();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("test1_err");
            return;
        }
        System.out.println("test1_ok");
    }

    private static void test_MutilFileMail() throws EmailException {
        String result = null;
        String enclosureFile = "D:\\修改天猫非旗舰店的数据.sql";
        EmailAttachment emailattachment = new EmailAttachment();
        //设置附件路径
        emailattachment.setPath(enclosureFile);
        //System.out.println(path);
        emailattachment.setDisposition(EmailAttachment.ATTACHMENT);
        //附件描述
        emailattachment.setDescription("This is Smile picture");
           /*
            * 设置附件的中文乱码问题，解决附件的中文名称 乱码问题
            */
        BASE64Encoder enc = new BASE64Encoder();
        //this.getName().getBytes()使用的是系统缺省的编码处理,这里是GBK
        emailattachment.setName("=?GBK?B?" + enc.encode(enclosureFile.getBytes()) + "?=");
        //attachment.setName(this.getName()); //不处理字符集的话,发送的附件中文名称是乱码
        //创建一个email
        MultiPartEmail multipartemail = new MultiPartEmail();
        //设置主机名称
        multipartemail.setHostName(Host_sina);
        //设置字符编码
        multipartemail.setCharset("GBK");
        //multipartemail.setCharset("GBK");
        //设置发送邮件目的地址
        multipartemail.addTo("610799239@qq.com");
        //设置发送又见源地址
        multipartemail.setFrom(UserName);
        //设置用户名和密码
        multipartemail.setAuthentication(UserName, PassWord);
        //设置主题
        multipartemail.setSubject("主题_11");
        //设置邮件内容
        multipartemail.setMsg("正文:本邮件为系统自动发送");

        //添加附件
        multipartemail.attach(emailattachment);
        //发送邮件
        multipartemail.send();

        result = "The attachmentEmail send sucessful!!!";

        return;

    }

    private static void test_Html() throws EmailException, MalformedURLException {
        HtmlEmail htmlEmail = new HtmlEmail();
        htmlEmail.setHostName(Host_sina);
        htmlEmail.setAuthentication(UserName, PassWord);
        htmlEmail.addTo("610799239@qq.com");
        htmlEmail.setFrom(UserName);
        htmlEmail.setSubject("这是一个 html 邮件_1");
        // embed the image and get the content id
//        URL url = new URL("http://www.apache.org/images/asf_logo_wide.gif");
//        string_1 cid = email.embed(url, "Apache logo");
        // set the html message
        htmlEmail.setHtmlMsg("<html>The apache logo <body>" +
                "<font color=ff0000>Just fot testandlearn ...</font>" +
                //"<img src=\"http://www.apache.org/images/asf_logo_wide.gif\">" +
                "<br>" +
                "</body></html>");
        // set the alternative message
        htmlEmail.setTextMsg("Your email client does not support HTML messages");
        // send the email
        htmlEmail.send();

    }


    public String send() {
        String result = null;

        SimpleEmail email = new SimpleEmail();
        //设置主机名称
        email.setHostName(Host_sina);
        //设置用户名，密码
        email.setAuthentication(UserName, PassWord);
        //设置字符编码方式
        email.setCharset("GB2312");
        try {
            //设置发送源地址
            email.setFrom(UserName);
            //设置目标地址
            email.addTo("610799239@qq.com");
            //设置主题
            email.setSubject("我是一个测试邮件");
            //设置邮件内容
            email.setMsg("测试邮件的内容");
            //发送邮件
            email.send();
            result = "successful";
        } catch (Exception e) {
            e.printStackTrace();
            result = "not successful";
        }

        return result;
    }

    public String sendEnclosure() throws EmailException {
        String result = null;
        String enclosureFile = "D:\\修改天猫非旗舰店的数据.sql";
        EmailAttachment emailattachment = new EmailAttachment();
        //设置附件路径
        emailattachment.setPath(enclosureFile);
        //System.out.println(path);
        emailattachment.setDisposition(EmailAttachment.ATTACHMENT);
        //附件描述
        emailattachment.setDescription("This is Smile picture");
	       /*
            * 设置附件的中文乱码问题，解决附件的中文名称 乱码问题
            */
        BASE64Encoder enc = new BASE64Encoder();
        //this.getName().getBytes()使用的是系统缺省的编码处理,这里是GBK
        emailattachment.setName("=?GBK?B?" + enc.encode(enclosureFile.getBytes()) + "?=");

        //attachment.setName(this.getName()); //不处理字符集的话,发送的附件中文名称是乱码


        //创建一个email
        MultiPartEmail multipartemail = new MultiPartEmail();
        //设置主机名称
        multipartemail.setHostName(Host_sina);
        //设置字符编码
        multipartemail.setCharset("GB2312");
        //设置发送邮件目的地址
        multipartemail.addTo("610799239@qq.com");
        //设置发送又见源地址
        multipartemail.setFrom(UserName);
        //设置用户名和密码
        multipartemail.setAuthentication(UserName, PassWord);
        //设置主题
        multipartemail.setSubject("主题");
        //设置邮件内容
        multipartemail.setMsg("正文");
        //添加附件
        multipartemail.attach(emailattachment);
        //发送邮件
        multipartemail.send();

        result = "The attachmentEmail send sucessful!!!";

        return result;
    }

    public String sendHtml() throws EmailException, MalformedURLException {
        // 创建邮件信息
        String result = null;
        HtmlEmail email = new HtmlEmail();
        email.setHostName(Host_sina);
        email.addTo("");
        email.setFrom("");
        email.setSubject("");
        // embed the image and get the content id
        URL url = new URL("http://www.apache.org/images/asf_logo_wide.gif");
        String cid = email.embed(url, "Apache logo");
        // set the html message
        email.setHtmlMsg("<html>The apache logo - <img src=\"cid:" + cid + "\"></html>");
        // set the alternative message
        email.setTextMsg("Your email client does not support HTML messages");
        // send the email
        email.send();

        return result;
    }
}
