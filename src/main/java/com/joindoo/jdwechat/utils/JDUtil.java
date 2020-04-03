package com.joindoo.jdwechat.utils;

import com.joindoo.jdwechat.model.sys.ScriptItemModel;
import org.apache.commons.codec.binary.Base64;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhuqiang on 2018/4/20.
 */
public class JDUtil {
    protected final Logger logger = LoggerFactory.getLogger(JDUtil.class);
    public static HashMap<String,ScriptItemModel> getScriptItemsByXml(String xmlPath) throws DocumentException {
        File file=  new File(xmlPath);
        return getScriptItemsByXml(file);
    }
    public static HashMap<String,ScriptItemModel> getScriptItemsByXml(File xmlFile) throws DocumentException {
        HashMap<String,ScriptItemModel> scriptItemModelHashMap=new HashMap<>();
        SAXReader reader = new SAXReader();
        Document doc  = reader.read(xmlFile);
        Element root = doc.getRootElement();
        List<Element> list = root.elements();
        for (Element el:list ) {
            ScriptItemModel itemModel=new ScriptItemModel();
            String id=el.attribute("Id").getValue();
            Element val_Ele = el.element("Value");
            Element order_Ele=el.element("OrderBy");
            itemModel.setScriptId(id);
            itemModel.setScriptContent(val_Ele.getText());
            itemModel.setOrderCondition(order_Ele.getText());
            scriptItemModelHashMap.put(itemModel.getScriptId(),itemModel);
        }
        return scriptItemModelHashMap;
    }
    public static HashMap<String,ScriptItemModel> getScriptItemsByXmlContent(String  xmlContent) throws DocumentException {
        HashMap<String,ScriptItemModel> scriptItemModelHashMap=new HashMap<>();
        SAXReader reader = new SAXReader();
        InputStream is = new ByteArrayInputStream(xmlContent.getBytes());
        Document doc  = reader.read(is);
        Element root = doc.getRootElement();
        List<Element> list = root.elements();
        for (Element el:list ) {
            ScriptItemModel itemModel=new ScriptItemModel();
            String id=el.attribute("Id").getValue();
            Element val_Ele = el.element("Value");
            Element order_Ele=el.element("OrderBy");
            itemModel.setScriptId(id);
            itemModel.setScriptContent(val_Ele.getText());
            if(null!=order_Ele)
                itemModel.setOrderCondition(order_Ele.getText());
            scriptItemModelHashMap.put(itemModel.getScriptId(),itemModel);
        }
        return scriptItemModelHashMap;
    }

    public static HashMap<String,String> getWsResultByXmlStr96106(String xml) throws DocumentException {
        SAXReader reader = new SAXReader();
        System.out.println(xml);
        reader.setEncoding(getEncoding(xml));
        InputStream is = new ByteArrayInputStream(xml.getBytes());
        Document doc  = reader.read(is);
        Element root = doc.getRootElement();
        List<Element> list = root.elements();
        HashMap<String,String> hashMap=new HashMap<>();
        for (Element el:list ) {
            String str=el.getText();
            String name=el.getName();
            hashMap.put(name,str);
        }
        return hashMap;
    }

    public static String getEncoding(String str) {
        String encode = "GB2312";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s = encode;
                return s;
            }
        } catch (Exception exception) {
        }
        encode = "ISO-8859-1";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s1 = encode;
                return s1;
            }
        } catch (Exception exception1) {
        }
        encode = "UTF-8";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s2 = encode;
                return s2;
            }
        } catch (Exception exception2) {
        }
        encode = "GBK";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s3 = encode;
                return s3;
            }
        } catch (Exception exception3) {
        }
        return "";
    }
    public Map getMiniqrQr(String path,String sence, String accessToken) {
        RestTemplate rest = new RestTemplate();
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            String url = "https://api.weixin.qq.com/wxaapp/createwxaqrcode?access_token="+accessToken;
            Map<String,Object> param = new HashMap<>();
            param.put("page", "pages/index/index");
            param.put("width", 430);
            logger.info("调用生成微信URL接口传参:" + param);
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            HttpEntity requestEntity = new HttpEntity(param, headers);
            ResponseEntity<byte[]> entity = rest.exchange(url, HttpMethod.POST, requestEntity, byte[].class, new Object[0]);
            logger.info("调用小程序生成微信永久二维码URL接口返回结果:" + entity.getBody());
            byte[] result = entity.getBody();
            logger.info(Base64.encodeBase64String(result));
            inputStream = new ByteArrayInputStream(result);

            File file = new File("C:/Users/wangqiulin/Desktop/1.png");
            if (!file.exists()){
                file.createNewFile();
            }
            outputStream = new FileOutputStream(file);
            int len = 0;
            byte[] buf = new byte[1024];
            while ((len = inputStream.read(buf, 0, 1024)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.flush();
        } catch (Exception e) {
            logger.error("调用小程序生成微信永久二维码URL接口异常",e);
        } finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
