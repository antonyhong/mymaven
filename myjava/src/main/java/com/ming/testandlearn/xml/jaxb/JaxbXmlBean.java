package com.ming.testandlearn.xml.jaxb;

import com.ming.utils.DateUtils;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by hongyongming on 2016/2/17.
 *
 * jaxb
 */

/**
 *
 * 异常:类的两个属性具有相同名称的报错
 * 以下情况会抛出异常:
 *   1.使用@XmlAccessorType(XmlAccessType.FIELD) +@XmlElement(name = "version")
 *   2.在属性字段上添加@XmlElement(name = "version") +　getset方法都存在
 *
 *故:
 *   @XmlElement 只添加在get或者set 方法接口即可；（或者在属性字段上添加 ，但是不要同事存在getset方法）
 *
 *
 * */
//@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Bean")
@XmlType(propOrder = {
        "beanName",
        "version",
        "createTime",
        "subBeans"
})
public class JaxbXmlBean {

    private String beanName;
    private String version;

    private Date createTime;
    private List<SubBean> subBeans;

    public JaxbXmlBean() {
    }

    public JaxbXmlBean(String beanName, String version, Date createTime) {
        this.beanName = beanName;
        this.version = version;
        this.createTime = createTime;
    }
    @XmlElement(name = "beanName")
    public String getBeanName() {
        return beanName;
    }

    @XmlElement(name = "createTime")
    @XmlJavaTypeAdapter(JaxbDateAdapter.class)
    public Date getCreateTime() {
        return createTime;
    }
    @XmlElement(name = "version")
    public String getVersion() {
        return version;
    }

    @XmlElementWrapper(name = "subBeanList")
    @XmlElement(name = "subBean")
    public List<SubBean> getSubBeans() {
        return subBeans;
    }

    public void setSubBeans(List<SubBean> subBeans) {
        this.subBeans = subBeans;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public static class SubBean{

        private String name;

        private String des;

        public SubBean() {
        }

        public SubBean( String name,String des) {
            this.des = des;
            this.name = name;
        }



        @XmlValue()
        public String getName() {
            return name;
        }
        //@XmlElement(name = "des")
        @XmlAttribute(name="des")
        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }


        public void setName(String name) {
            this.name = name;
        }
    }

    public static class Name{
    }


    public static void main(String[] args) {
        JaxbXmlBean jaxbXmlBean = new JaxbXmlBean("BeanName", "4.2.2.2", DateUtils.parseAllFormat("2015-02-02"));
        jaxbXmlBean.setSubBeans(new ArrayList<SubBean>(){{
                add(new SubBean("name1","des1"));
            add(new SubBean("name2","des2"));
            add(new SubBean("name3","des3"));
        }});

        String result = JaxbXmlUtil.convertToXml(jaxbXmlBean);
        System.out.println(result);

        JaxbXmlBean jaxbXmlBeanFromStr =  JaxbXmlUtil.convertXmlStrToObject(JaxbXmlBean.class, result);


        System.out.println(jaxbXmlBeanFromStr);
    }


}
