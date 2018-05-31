package com.ryan.baidu.testandlearn.net;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by HONGYONGMING on 2016/8/18.
 */
public class net {

    public static void main(String[] args) throws UnsupportedEncodingException {
        //URLEncoder.encode();

//        String encodedUrl = "https://qy.weixin.qq.com/cgi-bin/loginpage?corp_id=wx076d9a8ae1492e31&redirect_uri=http%3A%2F%2Foums.bumpsbb.com%2FweChat%2Fauth%3Ffrm%3Dhttp%3A%2F%2Foums.bumpsbb.com&usertype=member";
//
//        String re = URLDecoder.decode(encodedUrl, "utf-8");
//        System.out.println(re);
//        Thread t = new Thread();
//        t.setDaemon();

        Socket socket = new Socket();
        try {
            socket .connect(new InetSocketAddress("www.baidu.com",80));
            System.out.println("connect success");
            System.out.println(socket.getRemoteSocketAddress().toString());

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("connect failed");
            e.printStackTrace();
        }
    }


}
