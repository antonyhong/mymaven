package com.ming.testandlearn.net;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Proxy;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by HONGYONGMING on 2016/8/18.
 */
public class net {

    public static void main(String[] args) throws UnsupportedEncodingException {
        //URLEncoder.encode();

        String encodedUrl = "https://qy.weixin.qq.com/cgi-bin/loginpage?corp_id=wx076d9a8ae1492e31&redirect_uri=http%3A%2F%2Foums.bumpsbb.com%2FweChat%2Fauth%3Ffrm%3Dhttp%3A%2F%2Foums.bumpsbb.com&usertype=member";

        String re = URLDecoder.decode(encodedUrl, "utf-8");
        System.out.println(re);


    }
}
