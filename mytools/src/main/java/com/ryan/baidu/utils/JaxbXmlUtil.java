package com.ryan.baidu.utils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.nio.charset.Charset;

/**
 * Created by hongyongming on 2016/2/17.
 */
public class JaxbXmlUtil {

    private static final String DEFAULT_CHARSET = "UTF-8";

    public JaxbXmlUtil() {
    }

    public static String objectToXml(Object object) throws Exception {
        return objectToXml(object,DEFAULT_CHARSET);
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
            if (out != null) {
                out.close();
            }
        }
        return e;
    }

    public static <T> T xmlToObject(Class<T> objectClass, String xmlContent) throws Exception {
        InputStream inputStream = new ByteArrayInputStream(xmlContent.getBytes(Charset.forName(DEFAULT_CHARSET)));
        return xmlToObject(objectClass, inputStream);
    }

    public static <T> T xmlToObject(Class<T> objectClass, String xmlContent, Charset charset) throws Exception {
        InputStream inputStream = new ByteArrayInputStream(xmlContent.getBytes(charset));
        return xmlToObject(objectClass, inputStream);
    }

    private static <T> T xmlToObject(Class<T> objectClass, InputStream inputStream) throws Exception {
        JAXBContext context = JAXBContext.newInstance(new Class[]{objectClass});
        Unmarshaller unmarshaller = context.createUnmarshaller();
        T resultObj;
        try {
            resultObj = (T) unmarshaller.unmarshal(inputStream);
        } catch (JAXBException var8) {
            throw var8;
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return resultObj;
    }
}

