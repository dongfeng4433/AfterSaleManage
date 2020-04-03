package com.joindoo.jdwechat.utils;

import com.joindoo.jdwechat.Utility;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by zhuqiang on 2018/1/24.
 */
public class HttpContentTypes {
    private static Map<String, String> _MimeTypesDict = new HashMap();
    public static String Application_Excel = "application/vnd.ms-excel";
    public static String Application_Javascript = "application/x-javascript";
    public static String Application_Json = "application/com.jdo.json";
    public static String Application_MsAccess = "application/access";
    public static String Application_MSWord = "application/msword";
    public static String Application_OctetStream = "application/octet-stream";
    public static String Application_Pdf = "application/pdf";
    public static String Application_XZipCompressed = "application/x-zip-compressed";
    public static String Audio_Mid = "audio/mid";
    public static String Audio_Wav = "audio/wav";
    public static String Image_Gif = "image/gif";
    public static String Image_Jpeg = "image/jpeg";
    public static String Text_Css = "text/css";
    public static String Text_Html = "text/html";
    public static String Text_Plain = "text/plain";
    public static String Text_Xml = "text/xml";
    public static String Video_Mpeg = "video/mpeg";
    public static String Video_Mp4= "video/mp4";
    public static String Application_Rar = "application/x-rar-compressed";
    public static String Application_Zip = "application/x-zip-compressed";

    public HttpContentTypes() {
    }

    public static String GetMimeType(String ext) {
        if(Utility.isNullOrEmpty(ext)) {
            return "";
        } else {
            ext = ext.toLowerCase();
            if(ext.charAt(0) != 46) {
                ext = '.' + ext;
            }

            return _MimeTypesDict.containsKey(ext)?(String)_MimeTypesDict.get(ext):"application/octet-stream";
        }
    }

    public static void Initialize() {
        String[] extensions = new String[]{".doc", ".docx", ".pdf", ".jpeg", ".jpg", ".png", ".wav", ".mp3", ".mid", ".xml", ".mdb", ".xls", ".xlt", ".xlc", ".js", ".css", ".gif", ".html", ".aspx", ".htm", ".mshtml", ".asp", ".jsp", ".rar"};
        String[] mimeTypes = new String[]{"application/msword", "application/msword", "application/pdf", "image/jpeg", "image/jpeg", "image/jpeg", "audio/wav", "audio/wav", "audio/mid", "text/xml", "application/access", "application/vnd.ms-excel", "application/vnd.ms-excel", "application/vnd.ms-excel", "application/x-javascript", "text/css", "image/gif", "text/html", "text/html", "text/html", "text/html", "text/html", "text/html", "application/x-rar-compressed"};

        for(int i = 0; i < extensions.length; ++i) {
            _MimeTypesDict.put(extensions[i], mimeTypes[i]);
        }

    }

    public static void RegisterContentType(String ext, String contentType) {
        _MimeTypesDict.put(ext, contentType);
    }

    public static void RegisterContentTypes(Map<String, String> dict) {
        Iterator i$ = dict.entrySet().iterator();

        while(i$.hasNext()) {
            Map.Entry kv = (Map.Entry)i$.next();
            _MimeTypesDict.put(kv.getKey().toString(), kv.getValue().toString());
        }

    }
}
