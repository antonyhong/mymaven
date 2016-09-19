package com.ming.testandlearn.base64;

import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.util.Base64;

/**
 * Created by hongyongming on 2015/12/23.
 */
public class Base64Test {

    public static void main(String[] args) throws IOException {

        String content = getStr();
        //string_1 encodeContent  = new BASE64Encoder().encode(xmlStr.getBytes(Constants.DEFAULT_CHARSET));
        String encode = new BASE64Encoder().encode(content.getBytes("UTF-8"));
        String encodeBuffer = new BASE64Encoder().encodeBuffer(content.getBytes("UTF-8"));
        String encodeGet =  new String(Base64.getEncoder().encode(content.getBytes("UTF-8")), "UTF-8");

        System.out.println("-------------------------------------------------");
        System.out.println("encode:\r\n"+encode);
        System.out.println("-------------------------------------------------");
        System.out.println("encodeBuffer:\r\n"+encodeBuffer);
        System.out.println("-------------------------------------------------");
        System.out.println("encodeGet:\r\n"+encodeGet);
        System.out.println("-------------------------------------------------");

        //decodeContent = new string_1(new BASE64Decoder().decodeBuffer(content), Constants.DEFAULT_CHARSET);
//        string_1 decode2 = new string_1(Base64.getDecoder().decode(encodeGet),"UTF-8");
//        System.out.println("encodeGet=>decode2:\r\n"+decode2);
//        System.out.println("-------------------------------------------------");
//
//        string_1 decode = new string_1(new BASE64Decoder().decodeBuffer(encodeGet),"UTF-8");
//        System.out.println("encodeGet=>decode:\r\n"+decode);
//        System.out.println("-------------------------------------------------");

        /**err**/

        //string_1 decode3 = new string_1(Base64.getDecoder().decode(encodeBuffer),"UTF-8");
        String decode3 = new String(Base64.getDecoder().decode(encodeBuffer.getBytes("UTF-8")),"UTF-8");
        System.out.println("encodeBuffer=>decode3:\r\n"+decode3);
        System.out.println("-------------------------------------------------");

//        string_1 decode4 = new string_1(new BASE64Decoder().decodeBuffer(encodeBuffer),"UTF-8");
//        System.out.println("encodeBuffer=>decode4:\r\n"+decode4);
//        System.out.println("-------------------------------------------------");

        /***/
//        string_1 decode5 = new string_1(Base64.getDecoder().decode(encode),"UTF-8");
//        System.out.println("encode=>decode5:\r\n"+decode5);
//        System.out.println("-------------------------------------------------");

//        string_1 decode6 = new string_1(new BASE64Decoder().decodeBuffer(encode),"UTF-8");
//        System.out.println("encode=>decode6:\r\n"+decode6);
//        System.out.println("-------------------------------------------------");

    }

    private static String getStr(){
        return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<request>\n" +
                "  <items>\n" +
                "    <item>\n" +
                "      <itemNo>BTB商品编码</itemNo>\n" +
                "      <nameEn>BTB商品英文描述</nameEn>\n" +
                "      <nameCn>BTB商品中文描述</nameCn>\n" +
                "      <barCode>商品条形码</barCode>\n" +
                "      <joinTime>商品加入时间</joinTime>\n" +
                "      <itemClass>ITEM的分类</itemClass>\n" +
                "      <category>商品主分类</category>\n" +
                "      <subCategory>商品子分类</subCategory>\n" +
                "      <webCate>网站主分类</webCate>\n" +
                "      <webSubCate>网站子分类</webSubCate>\n" +
                "      <brandName>商品品牌名称</brandName>\n" +
                "      <vendorName>供应商名称</vendorName>\n" +
                "      <vendorCode>供应商对应商品代码</vendorCode>\n" +
                "      <consignment>是否寄售(0:否；1:是)</consignment>\n" +
                "      <discontinued>是否断货(0:否；1:是)</discontinued>\n" +
                "      <color>颜色</color>\n" +
                "      <specification>商品条码规格</specification>\n" +
                "      <unitPrice>商品单价</unitPrice>\n" +
                "      <standardCost>标准成本</standardCost>\n" +
                "      <averageCost>平均成本</averageCost>\n" +
                "      <discount>折扣</discount>\n" +
                "      <lpPrice>最后采购价</lpPrice>\n" +
                "      <parentCode>父级ITEM编码</parentCode>\n" +
                "      <unit>计量单位</unit>\n" +
                "      <description>ITEM描述</description>\n" +
                "      <currency>外汇 如：GBP</currency>\n" +
                "      <foreignCost>外汇金额</foreignCost>\n" +
                "      <active>商品状态（0:无效；1:有效）</active>\n" +
                "    </item>\n" +
                "  </items>\n" +
                "</request>\n";
    }
}
