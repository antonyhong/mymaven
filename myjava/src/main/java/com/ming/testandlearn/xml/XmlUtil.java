package com.ming.testandlearn.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

/**
 * Created by hongyongming on 2016/2/17.
 */
public class XmlUtil {

    /**
     * 将对象直接转换成String类型的 XML输出
     *
     * @param obj
     * @return
     */
    public static String convertToXml(Object obj) {
        // 创建输出流
        StringWriter sw = new StringWriter();
        try {
            // 利用jdk中自带的转换类实现
            JAXBContext context = JAXBContext.newInstance(obj.getClass());

            Marshaller marshaller = context.createMarshaller();
            // 格式化xml输出的格式
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
                    Boolean.TRUE);
            // 将对象转换成输出流形式的xml
            marshaller.marshal(obj, sw);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return sw.toString();
    }

    /**
     * 将对象根据路径转换成xml文件
     *
     * @param obj
     * @param path
     * @return
     */
    public static void convertToXml(Object obj, String path) {
        try {
            // 利用jdk中自带的转换类实现
            JAXBContext context = JAXBContext.newInstance(obj.getClass());

            Marshaller marshaller = context.createMarshaller();
            // 格式化xml输出的格式
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
                    Boolean.TRUE);
            // 将对象转换成输出流形式的xml
            // 创建输出流
            FileWriter fw = null;
            try {
                fw = new FileWriter(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
            marshaller.marshal(obj, fw);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    /**
     * 将String类型的xml转换成对象
     */
    public static Object convertXmlStrToObject(Class clazz, String xmlStr) {
        Object xmlObject = null;
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            // 进行将Xml转成对象的核心接口
            Unmarshaller unmarshaller = context.createUnmarshaller();
            StringReader sr = new StringReader(xmlStr);
            xmlObject = unmarshaller.unmarshal(sr);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return xmlObject;
    }

    @SuppressWarnings("unchecked")
    /**
     * 将file类型的xml转换成对象
     */
    public static Object convertXmlFileToObject(Class clazz, String xmlPath) {
        Object xmlObject = null;
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            FileReader fr = null;
            try {
                fr = new FileReader(xmlPath);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            xmlObject = unmarshaller.unmarshal(fr);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return xmlObject;
    }

    /**
     *
     * //
     // Source code recreated from a .class file by IntelliJ IDEA
     // (powered by Fernflower decompiler)
     //

     package com.baitian.fourb.erp.utils;

     import java.io.ByteArrayInputStream;
     import java.io.ByteArrayOutputStream;
     import java.io.InputStream;
     import javax.xml.bind.JAXBContext;
     import javax.xml.bind.JAXBException;
     import javax.xml.bind.Marshaller;
     import javax.xml.bind.Unmarshaller;

     public class XmlUtil {
     private static final String DEFAULT_CHARSET = "UTF-8";

     public XmlUtil() {
     }

     public static String objectToXml(Object object) throws Exception {
     return objectToXml(object, "UTF-8");
     }

     public static String objectToXml(Object object, String charset) throws Exception {
     JAXBContext context = JAXBContext.newInstance(new Class[]{object.getClass()});
     Marshaller mar = context.createMarshaller();
     mar.setProperty("jaxb.fragment", Boolean.valueOf(false));
     mar.setProperty("jaxb.encoding", "UTF-8");
     ByteArrayOutputStream out = new ByteArrayOutputStream();

     String e;
     try {
     mar.marshal(object, out);
     e = out.toString(charset);
     } catch (Exception var9) {
     throw var9;
     } finally {
     if(out != null) {
     out.close();
     }

     }

     return e;
     }

     public static <T> T xmlToObject(Class<T> objectClass, InputStream inputStream) throws Exception {
     JAXBContext context = JAXBContext.newInstance(new Class[]{objectClass});
     Unmarshaller unmarshaller = context.createUnmarshaller();

     Object e;
     try {
     e = unmarshaller.unmarshal(inputStream);
     } catch (JAXBException var8) {
     throw var8;
     } finally {
     if(inputStream != null) {
     inputStream.close();
     }

     }

     return e;
     }

     public static <T> T xmlToObject(Class<T> objectClass, byte[] bytes) throws Exception {
     JAXBContext context = JAXBContext.newInstance(new Class[]{objectClass});
     Unmarshaller unmarshaller = context.createUnmarshaller();
     ByteArrayInputStream inputStream = null;

     Object e;
     try {
     inputStream = new ByteArrayInputStream(bytes);
     e = unmarshaller.unmarshal(inputStream);
     } catch (JAXBException var9) {
     throw var9;
     } finally {
     if(inputStream != null) {
     inputStream.close();
     }

     }

     return e;
     }
     }

     *
     * */
}

