package com.joindoo.jdwechat.utils;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * Created by zhuqiang on 2018/4/3.
 */
public class HttpUtils {
    /**
     * 发送post请求--用于接口接收的参数为JSON字符串
     * @param url 请求地址
     * @param params json格式的字符串
     * @return
     */
    public static String httpPost(String url, String params){
        String result = null;
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            /*
             * 发送json字符串，这两句需要设置
             */
            httpPost.addHeader("Content-type","application/json; charset=utf-8");
            httpPost.setHeader("Accept", "application/json");

            httpPost.setEntity(new StringEntity(params, "UTF-8"));

            HttpResponse response = httpClient.execute(httpPost);

            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode == HttpStatus.SC_OK) {
                // Read the response body
                result = EntityUtils.toString(response.getEntity(),"UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    private static RequestConfig createConfig(int timeout, boolean redirectsEnabled)
    {
        return RequestConfig.custom()
            .setSocketTimeout(timeout)
            .setConnectTimeout(timeout)
            .setConnectionRequestTimeout(timeout)
            .setRedirectsEnabled(redirectsEnabled)
            .build();
    }
    /**
     * 发送post请求--用于接口接收的参数为键值对
     * @param url 请求地址
     * @param nameValuePairs 键值对
     * @return
     */
    public static String httpPost(String url, Map<String,String> headers, List<NameValuePair> nameValuePairs) {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(createConfig(5000,false));
        if(null!=headers&&headers.size()>0){
            for(String key :headers.keySet()){
                httpPost.setHeader(key,headers.get(key));
            }
        }
        String strResult = "";
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
            CloseableHttpResponse response  = client.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == 200) {
                /* 读返回数据 */
                strResult = EntityUtils.toString(response.getEntity());
                // System.out.println("conResult:"+conResult);
            } else {
                strResult += "发送失败:" + response.getStatusLine().getStatusCode();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return strResult;
    }

    public static String httpGet(String url, List<NameValuePair> nameValuePairs){
        HttpClient httpClient = new DefaultHttpClient();
        String sb = "";
        String result = "";
        try {
            if(null!=nameValuePairs){
                for(NameValuePair nvp:nameValuePairs){
                    sb += nvp.getName()+"="+nvp.getValue()+"&";
                }
            }
            if(StringUtils.isNotEmpty(sb)){
                sb = sb.substring(0,sb.length()-1);
                sb = URLDecoder.decode(sb, "UTF-8");

                if(url.contains("?"))
                    url=url+"&"+sb;
                else
                    url=url+"?"+sb;
            }

            HttpGet httpGet = new HttpGet(url);

            HttpResponse response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                /* 读返回数据 */
                result = EntityUtils.toString(response.getEntity());
            } else {
                result += "发送失败:" + response.getStatusLine().getStatusCode();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
