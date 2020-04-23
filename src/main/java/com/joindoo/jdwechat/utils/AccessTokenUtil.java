package com.joindoo.jdwechat.utils;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.joindoo.jdwechat.SystemSetting;
import com.joindoo.jdwechat.beans.TDataEnterpriseConfigModel;
import com.taobao.api.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.List;


/**
 * 获取access_token工具类
 */
public class AccessTokenUtil {
    private static final Logger bizLogger = LoggerFactory.getLogger(AccessTokenUtil.class);

    public static String getToken() throws RuntimeException {
        try {
            DefaultDingTalkClient client = new DefaultDingTalkClient(SystemSetting.DINGTALK_URL_GET_TOKKEN);
            OapiGettokenRequest request = new OapiGettokenRequest();

            request.setAppkey(SystemSetting.SYS_DINGTALK_APP_KEY);
            request.setAppsecret(SystemSetting.SYS_DINGTALK_APP_SECRET);
            request.setHttpMethod("GET");
            OapiGettokenResponse response = client.execute(request);
            String accessToken = response.getAccessToken();
            return accessToken;
        } catch (ApiException e) {
            bizLogger.error("getAccessToken failed", e);
            throw new RuntimeException();
        }

    }

    public static String getToken(String enterprise_id) throws RuntimeException {
        try {
            if(!StringUtils.isEmpty(enterprise_id)){
                DefaultDingTalkClient client = new DefaultDingTalkClient(SystemSetting.DINGTALK_URL_GET_TOKKEN);
                OapiGettokenRequest request = new OapiGettokenRequest();

                List<TDataEnterpriseConfigModel> configModelList=SystemSetting.JD_ServerCache.getCorpConfig(enterprise_id);
                if(configModelList!=null&&configModelList.size()>0){
                    String app_key="";String app_secret="";
                    for(TDataEnterpriseConfigModel model:configModelList){
                        if("SYS_DINGTALK_APP_KEY".equals(model.getconfig_code())){
                            app_key=model.getconfig_value();
                        }
                        if("SYS_DINGTALK_APP_SECRET".equals(model.getconfig_code())){
                            app_secret=model.getconfig_value();
                        }
                    }
                    if(!StringUtils.isEmpty(app_key)&&!StringUtils.isEmpty(app_secret)){
                        request.setAppkey(app_key);
                        request.setAppsecret(app_secret);
                        request.setHttpMethod("GET");
                        OapiGettokenResponse response = client.execute(request);
                        String accessToken = response.getAccessToken();
                        return accessToken;
                    }

                }
                return null;
            }
            return null;
        } catch (ApiException e) {
            bizLogger.error("getAccessToken failed", e);
            throw new RuntimeException();
        }

    }
    public static void main(String[] args)throws ApiException {
        String accessToken = AccessTokenUtil.getToken();
        System.out.println(accessToken);
    }
}
