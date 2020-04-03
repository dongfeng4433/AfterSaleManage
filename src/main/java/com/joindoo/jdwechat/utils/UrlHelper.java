package com.joindoo.jdwechat.utils;

import com.joindoo.jdwechat.SystemSetting;
import com.joindoo.jdwechat.Utility;

import java.util.Map;

/**
 * Created by zhuqiang on 2018/3/8.
 */
public class UrlHelper {
    public static String HtmlEncode(Object o) {
        if (o == null) return null;
        //return URLEncoder.encode(o.toString());
        String html = o.toString();
        int size = html.length();
        StringBuilder sb = new StringBuilder(size * 2);
        char ch;
        for (int i = 0; i < size; i++) {
            ch = html.charAt(i);
            if (ch == '&') sb.append("&amp;");
            else if (ch == '\"') sb.append("&quot;");
            else if (ch == '\t') sb.append("&nbsp;&nbsp;&nbsp;&nbsp;");   // 替换跳格
            else if (ch == ' ') sb.append("&nbsp;");// 替换空格
            else if (ch == '<') sb.append("&lt;");
            else if (ch == '>') sb.append("&gt;");
            else sb.append(ch);
        }
        return sb.toString();
    }

    public static String GenerateUrl(String routeName, String actionName, String controllerName,
                                     Map<String, Object> map, boolean includeImplicitMvcValues) {
        StringBuilder sb=new StringBuilder(1024);
        sb.append(SystemSetting.Path_System_StartUrl);
        if (sb.charAt(sb.length()-1)!='/') sb.append('/');
        if (!Utility.isNullOrEmpty(routeName)){ sb.append(routeName);sb.append("/");}
        sb.append(controllerName);
        sb.append("/");
        sb.append(actionName);
        Object o;
        if (map!=null&&map.size()>0){
            sb.append('?');
            for(Map.Entry<String,Object> item : map.entrySet()){
                o=item.getValue();
                if (o!=null){
                    sb.append(HtmlEncode(item.getKey()));
                    sb.append('=');
                    sb.append(HtmlEncode(item.getValue()));
                    sb.append('&');
                }
            }
            if (sb.charAt(sb.length()-1)=='&') return sb.substring(0, sb.length()-1);
        }
        return sb.toString();
    }
}
