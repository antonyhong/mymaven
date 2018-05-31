package com.ryan.baidu.testandlearn.java8.interface_1;

/**
 * Created by hongyongming on 2016/1/22.
 */
public interface IterfaceWithDefault {

    String doSomething();

    /**
     * 默认方法,我认识最主要的是问了兼容旧版本的代码;
     * 除此之外暂没想到其他用处:
     *
     * */
    default  String saySomeThing(){
        return "I'm interface with default method";
    }

}
