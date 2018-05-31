package com.ryan.baidu.testandlearn.reflect.proxy.cglibtest;

import net.sf.cglib.proxy.Enhancer;

/**
 * Created by HONGYONGMING on 2016/8/24.
 */

    public class ChineseProxyFactory
    {
        public static Chinese getAuthInstance()
        {
            //使用 Enhancer 来创建代理
            Enhancer en = new Enhancer();
            // 设置要代理的目标类
            en.setSuperclass(Chinese.class);
            // 设置要代理的拦截器
            en.setCallback(new AroundAdvice());
            // 生成代理类的实例
            return (Chinese)en.create();
        }
    }

