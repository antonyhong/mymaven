package com.ming.testandlearn.xml;

import javax.xml.bind.annotation.*;
import java.util.Date;
import java.util.List;
/**
 * Created by hongyongming on 2016/2/17.
 */
//@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Bean")
//@XmlType(propOrder = {
//        "version",
//        "createTime",
//        "subBeanList",
//})
public class XmlToBean {
    @XmlElement(name = "beanName")
    private String beanName;
    @XmlElement(name = "version")
    private String version;
    @XmlElement(name = "createTime")
    private Date createTime;

 //   @XmlElementWrapper(name = "subBeanList")
    @XmlElement(name = "subBean")
    private List<SubBean> subBeanList;

    public XmlToBean(){

    }

    public XmlToBean(String beanName, String version, Date createTime, List<SubBean> subBeanList) {
        this.beanName = beanName;
        this.version = version;
        this.createTime = createTime;
        this.subBeanList = subBeanList;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<SubBean> getSubBeanList() {
        return subBeanList;
    }

    public void setSubBeanList(List<SubBean> subBeanList) {
        this.subBeanList = subBeanList;
    }

    public static class SubBean{
        @XmlElement(name = "name")
        private String name;
        @XmlElement(name = "des")
        private String des;

        public SubBean() {
        }

        public SubBean( String name,String des) {
            this.des = des;
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }
    }
}
