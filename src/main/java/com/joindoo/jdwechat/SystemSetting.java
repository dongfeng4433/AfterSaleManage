package com.joindoo.jdwechat;


import com.joindoo.jdwechat.cache.ServerCache;

/**
 * Created by zhuqiang1 on 2017/8/18.
 */
public class SystemSetting {
    /**
     * 系统的起始url
     */
    public static String Path_System_StartUrl = "/";
    public static int System_BufferSize = 1024 * 1024 * 1;
    public static String SYS_API_KEY="";
    public static String SYS_PATH_ROOT="";
    public static String SYS_PATH_ROOT_DOCS="";
    public static String SYS_PATH_WEB_CLASS_CONFIG="";
    public static String SYS_PATH_CONFIG_LOG4J="";
    public static String SYS_PATH_CONFIG_DB="";
    public static String SYS_PATH_RESOURSE="";
    public static String SYS_PATH_MAINSTORAGE="";
    public static String SCRIPT_FILE_PATH="";
    public static String SYS_ADMIN="";
    public static String SYS_WECHAT_APPID="";
    public static String SYS_WECHAT_APPSECRET="";
    public static String SYS_GOV_HOST="";

    public final static String ORACLE_USER="QXZFJX";

    public static String SYS_WECHAT_TOKEN="";
    public static Long SYS_WECHAT_TOKEN_EXPIRES=0L;

    public final static String WEB_HOST="http://localhost:9312";
    /**
     * 钉钉网关gettoken地址
     */
    public static final String DINGTALK_URL_GET_TOKKEN = "https://oapi.dingtalk.com/gettoken";

    /**
     *获取用户在企业内userId的接口URL
     */
    public static final String DINGTALK_URL_GET_USER_INFO = "https://oapi.dingtalk.com/user/getuserinfo";

    /**
     *获取用户姓名的接口url
     */
    public static final String DINGTALK_URL_USER_GET = "https://oapi.dingtalk.com/user/get";

    /**
     * 开发者后台->企业自建应用->选择您创建的E应用->查看->AppKey
     */
    public static String SYS_DINGTALK_APP_KEY = "";
    /**
     * 开发者后台->企业自建应用->选择您创建的E应用->查看->AppSecret
     */
    public static String SYS_DINGTALK_APP_SECRET="";

    public static void InitSetting(String path){
        if(!Utility.isNullOrEmpty(path)){
            SYS_PATH_ROOT=path;
            SYS_PATH_WEB_CLASS_CONFIG=Utility.Path_Combine(SYS_PATH_ROOT,"WEB-INF/classes");
            SYS_PATH_CONFIG_LOG4J=Utility.Path_Combine(SYS_PATH_ROOT,"WEB-INF/classes/log4j.properties");
            SYS_PATH_CONFIG_DB=Utility.Path_Combine(SYS_PATH_ROOT,"WEB-INF/classes/config.properties");
            SYS_PATH_RESOURSE=Utility.Path_Combine(SYS_PATH_ROOT,"Resources");
        }
    }

    public static ServerCache JD_ServerCache=new ServerCache();
}
