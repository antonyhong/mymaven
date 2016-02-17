package com.ming.testandlearn.xml;

import com.ming.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by hongyongming on 2016/2/17.
 */
public class XmlToBeanTest {

    public static void main(String[] args) {
        XmlToBean();
    }

    public static void XmlToBean(){
        List<XmlToBean.SubBean> subBeans = new ArrayList<XmlToBean.SubBean>(){{
           // add(new XmlToBean.SubBean("name1","des1"));
         //   add(new XmlToBean.SubBean("name2","des2"));
          //  add(new XmlToBean.SubBean("name3","des3"));
        }};
        XmlToBean xmlToBean = new XmlToBean("beanNameCn","1.1.1.1", DateUtils.parseAllFormat("2015-01-02"),subBeans);
        System.out.println(XmlUtil.convertToXml(xmlToBean));
    };
    public static void BeanToXml(){

    }
}
