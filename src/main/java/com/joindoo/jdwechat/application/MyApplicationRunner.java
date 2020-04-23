package com.joindoo.jdwechat.application;

import com.joindoo.jdwechat.SystemSetting;
import com.joindoo.jdwechat.beans.BaseModel;
import com.joindoo.jdwechat.cache.PersonCacheModel;
import com.joindoo.jdwechat.daos.TDataEnterpriseConfigDao;
import com.joindoo.jdwechat.daos.TXtAqZhxxDao;
import com.joindoo.jdwechat.daos.TXtWxyhDao;
import com.joindoo.jdwechat.data.DBHelper;
import com.joindoo.jdwechat.data.DataContext;
import com.joindoo.jdwechat.entity.config.DBConfigProperties;
import com.joindoo.jdwechat.entity.config.DingTalkConfig;
import com.joindoo.jdwechat.entity.config.DruidConfig;
import com.joindoo.jdwechat.entity.config.SysProperties;
import com.joindoo.jdwechat.model.ConnConfigModel;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuqiang on 2018/3/29.
 */
@Component
public class MyApplicationRunner implements ApplicationRunner {
    @Autowired
    DruidConfig druidConfig;
    @Autowired
    SysProperties sysProperties;
    @Autowired
    DBConfigProperties dbConfigProperties;

    @Autowired
    DingTalkConfig dingTalkConfig;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        SystemSetting.SYS_API_KEY=sysProperties.getAk();
        SystemSetting.SYS_PATH_ROOT=new File(ResourceUtils.getURL("classpath:").getPath()).getAbsolutePath();
        SystemSetting.SCRIPT_FILE_PATH= sysProperties.getScripts();
        SystemSetting.SYS_PATH_ROOT_DOCS=sysProperties.getDocs();
        SystemSetting.SYS_ADMIN=sysProperties.getAdmin();
        SystemSetting.SYS_WECHAT_APPID=sysProperties.getAppid();
        SystemSetting.SYS_WECHAT_APPSECRET=sysProperties.getSecret();

        SystemSetting.SYS_DINGTALK_APP_KEY=dingTalkConfig.getApp_key();
        SystemSetting.SYS_DINGTALK_APP_SECRET=dingTalkConfig.getApp_secret();

        SystemSetting.SYS_GOV_HOST=sysProperties.getGov_host();

        ConnConfigModel configModel=new ConnConfigModel();
        configModel.url=dbConfigProperties.getUrl();
        configModel.user=dbConfigProperties.getUsername();
        configModel.password=dbConfigProperties.getPassword();
        DBHelper.setConfigModel(configModel);
        DataContext dataContext=new DataContext(druidConfig.druidDataSource());
        DataContext.setInstance(dataContext);

        List<PersonCacheModel> personCacheModels=new ArrayList<>();//从数据库获取
        TXtAqZhxxDao zhxxDao=new TXtAqZhxxDao(dataContext);
        List<BaseModel> zhxxList= zhxxDao.findAll("sfyx_bj =1");
        TXtWxyhDao wxyhDao=new TXtWxyhDao(dataContext);
        List<BaseModel> list= wxyhDao.findAll("is_valid =1");
        TDataEnterpriseConfigDao configDao=new TDataEnterpriseConfigDao(dataContext);
        List<BaseModel> configList=configDao.findAll("is_valid = 1");
        SystemSetting.JD_ServerCache.initializeBaseData(personCacheModels,zhxxList,list,configList);
        dataContext.release();
    }
}
