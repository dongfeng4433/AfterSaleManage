package com.joindoo.jdwechat.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.joindoo.jdwechat.SystemSetting;
import com.joindoo.jdwechat.Utility;
import com.joindoo.jdwechat.beans.BaseModel;
import com.joindoo.jdwechat.data.DBHelper;
import com.joindoo.jdwechat.data.DataContext;
import com.joindoo.jdwechat.data.DataService;
import com.joindoo.jdwechat.entity.config.DruidConfig;
import com.joindoo.jdwechat.netty.MyChannelHandlerPool;
import com.joindoo.jdwechat.netty.MyWebSocketHandler;
import com.joindoo.jdwechat.utils.HttpUtils;
import org.apache.commons.codec.language.bm.Lang;
import org.omg.IOP.Encoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhuqiang on 2018/4/3.
 */
@Component
public class CommonTask {
    @Autowired
    DruidConfig druidConfig;
    protected final Logger logger = LoggerFactory.getLogger(CommonTask.class);
    /**
     * 每30秒执行一次
     */
    @Scheduled(cron="0/30 * *  * * ? ")   //每30秒执行一次
    public void execTask(){
        MyWebSocketHandler.sendMessage("2f3200cfba1911e88d20525479985c82",Utility.formatDateTime(new Date(),false));
        System.out.println("execTask,cmd:send message 2 websocket,执行时间:"+new Date(System.currentTimeMillis()));
    }

    //每小时刷新一次
    @Scheduled(cron = "0 0 0/1 * * ?")
    public void logoutTask(){
        String url="https://localhost:9999/wechat/dmm/loadGroupCache?jd_token=64CB27123CB64388897506E11FD027BB";
//        HttpUtils.httpGet(url,new ArrayList<>());
        logger.info("loadGroupCache");
        System.out.println("execTask,cmd:loadGroupCache,执行时间:"+new Date(System.currentTimeMillis()));
    }


//    @Scheduled(cron = "0/59 * *  * * ?")
    public void execTask4Wechat(){
        if(!Utility.isNullOrEmpty(SystemSetting.SYS_WECHAT_APPID)){
            if(Utility.isNullOrEmpty(SystemSetting.SYS_WECHAT_TOKEN)||SystemSetting.SYS_WECHAT_TOKEN_EXPIRES<new Date().getTime()){
                String url="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+ SystemSetting.SYS_WECHAT_APPID+"&secret="+SystemSetting.SYS_WECHAT_APPSECRET;
                String result=HttpUtils.httpGet(url,new ArrayList<>());
                //{"access_token":"ACCESS_TOKEN","expires_in":7200}
                if(!Utility.isNullOrEmpty(result)&&result.contains("access_token")){
                    JSONObject jsonObject= JSON.parseObject(result);
                    SystemSetting.SYS_WECHAT_TOKEN=jsonObject.getString("access_token");
//                System.out.println(SystemSetting.SYS_WECHAT_TOKEN);
                    logger.info(SystemSetting.SYS_WECHAT_TOKEN);
                    SystemSetting.SYS_WECHAT_TOKEN_EXPIRES=new Date().getTime()+3600*1000;
                }else
                    logger.info(result);

                System.out.println("execTask,cmd:execTask4Wechat,执行时间:"+new Date(System.currentTimeMillis()));
            }
        }

    }
}
