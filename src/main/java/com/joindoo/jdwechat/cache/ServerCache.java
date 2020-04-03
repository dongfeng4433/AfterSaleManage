package com.joindoo.jdwechat.cache;


import com.joindoo.jdwechat.SystemSetting;
import com.joindoo.jdwechat.Utility;
import com.joindoo.jdwechat.beans.*;
import com.joindoo.jdwechat.codeGen.TXtGyJdDtoModel;
import com.joindoo.jdwechat.codeGen.TXtGyQzDtoModel;
import com.joindoo.jdwechat.codeGen.TXtGySqDtoModel;
import com.joindoo.jdwechat.common.ClassPathResourceReader;
import com.joindoo.jdwechat.model.sys.ScriptItemModel;
import com.joindoo.jdwechat.utils.JDUtil;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhuqi on 2017/12/11.
 */
public class ServerCache {
    private final Logger log = LoggerFactory.getLogger(getClass());
    /**
     * 初始化容量
     */
    private int initialCapacity;
    /**
     * 负载因子 系统默认为 0.75  如果程序比较关心空间开销、内存比较紧张，可以适当地增加负载因子；
     * 如果程序比较关心时间开销，内存比较宽裕则可以适当的减少负载因子。通常情况下，无需改变负载因子的值。
     */
    private float loadFactor;

    private ConcurrentHashMap<String, UserCacheModel> userCacheModelConcurrentHashMap;
    private ConcurrentHashMap<String,PersonCacheModel> personCacheModelConcurrentHashMap;
    private HashMap<String,ScriptItemModel> scriptItemModelHashMap;
    private ConcurrentHashMap<String,TXtWxyhModel> wxyhModelConcurrentHashMap;
    private ConcurrentHashMap<String, UserCacheModel> zhxxCacheModelHashMap;

    private ConcurrentHashMap<String,TXtGyQzDtoModel> tXtGyQzDtoModelConcurrentHashMap;
    private ConcurrentHashMap<String,TXtGySqDtoModel> tXtGySqDtoModelConcurrentHashMap;
    private ConcurrentHashMap<String,TXtGyJdDtoModel> tXtGyJdDtoModelConcurrentHashMap;

    public ServerCache() {
        this.initialCapacity = 10 * 1024;
        this.loadFactor = (float) 0.75;

        this.userCacheModelConcurrentHashMap = new ConcurrentHashMap<>(this.initialCapacity, this.loadFactor);
        this.personCacheModelConcurrentHashMap=new ConcurrentHashMap<>(this.initialCapacity, this.loadFactor);
        this.wxyhModelConcurrentHashMap=new ConcurrentHashMap<>(this.initialCapacity, this.loadFactor);
        this.scriptItemModelHashMap=new HashMap<>();
        this.zhxxCacheModelHashMap= new ConcurrentHashMap<>(this.initialCapacity, this.loadFactor);

        this.tXtGyQzDtoModelConcurrentHashMap=new ConcurrentHashMap<>(this.initialCapacity,this.loadFactor);
        this.tXtGyJdDtoModelConcurrentHashMap=new ConcurrentHashMap<>();
        this.tXtGySqDtoModelConcurrentHashMap=new ConcurrentHashMap<>();
    }

    public void initializeBaseData(List<PersonCacheModel> personCacheModels,List<BaseModel> zhxxList, List<BaseModel> wxyhList) {
        log.info("更新系统缓存中...");
        if(personCacheModels.size()>0){
            for(PersonCacheModel cacheModel:personCacheModels){
                this.personCacheModelConcurrentHashMap.put(cacheModel.getIdcard_no(),cacheModel);
            }
        }
        if(zhxxList.size()>0){
            for(BaseModel baseModel:zhxxList){
                TXtAqZhxxModel model= (TXtAqZhxxModel) baseModel;
                UserCacheModel cacheModel=new UserCacheModel();
                cacheModel.setUserId(model.getry_xh());
                cacheModel.setUserName(model.getyhm());
                cacheModel.setPassword(model.getmm());
                cacheModel.setName(model.getmc());
                cacheModel.setAccountType(model.getyhlx_dm());
                updateUserCacheModel(cacheModel);
            }
        }
        if(wxyhList.size()>0){
            for (BaseModel baseModel:wxyhList){
                TXtWxyhModel model=(TXtWxyhModel)baseModel;
                this.wxyhModelConcurrentHashMap.put(model.getopenid(),model);
            }
        }
        log.info("更新系统缓存结束");
        initSciptsCache();
    }

    private void initSciptsCache(){
        if(!Utility.isNullOrEmpty(SystemSetting.SCRIPT_FILE_PATH)){
            try {
                String[] pathArr=SystemSetting.SCRIPT_FILE_PATH.split(";");
                for (String path:pathArr) {
                    String content = new ClassPathResourceReader("config/"+path).getContent();
                    HashMap<String,ScriptItemModel> hashMap= JDUtil.getScriptItemsByXmlContent(content);
                    for(String key :hashMap.keySet()){
                        this.scriptItemModelHashMap.put(key,hashMap.get(key));
                    }
                }
            } catch (DocumentException e) {
                e.printStackTrace();
                log.error(e.getMessage());
            }
            log.info("load sql script complete...,item length:"+this.scriptItemModelHashMap.keySet().size());
        }
    }

    public ScriptItemModel getSciptItemModel(String scriptId){
        return this.scriptItemModelHashMap.get(scriptId);
    }
    public boolean isExistPersonCache(String idcard_no){
        if(this.personCacheModelConcurrentHashMap.containsKey(idcard_no))return true;
        else return false;
    }

    public void updatePersonCache(PersonCacheModel cacheModel){
        this.personCacheModelConcurrentHashMap.put(cacheModel.getIdcard_no(),cacheModel);
    }
    public PersonCacheModel getCurrentPersonCahceModel(String idcard_no){
        return this.personCacheModelConcurrentHashMap.get(idcard_no);
    }
    public UserCacheModel getUserCacheModel(String userName){
        if(this.userCacheModelConcurrentHashMap.containsKey(userName)){
            return this.userCacheModelConcurrentHashMap.get(userName);
        }
        return null;
    }
    public UserCacheModel getUserCacheModelById(String userId){
        if(this.userCacheModelConcurrentHashMap.containsKey(userId)){
            return this.userCacheModelConcurrentHashMap.get(userId);
        }
        return null;
    }
    public void updateUserCacheModel(TSysUserinfoModel userinfoModel){
        UserCacheModel cacheModel=new UserCacheModel();
        cacheModel.setUserId(userinfoModel.getid());
        cacheModel.setUserName(userinfoModel.getusername());
        cacheModel.setPassword(userinfoModel.getpassword());
        cacheModel.setAttempts_times(userinfoModel.getattempts_times());
        cacheModel.setSalt(userinfoModel.getsalt());
        this.userCacheModelConcurrentHashMap.put(userinfoModel.getusername(),cacheModel);
        this.userCacheModelConcurrentHashMap.put(userinfoModel.getid(),cacheModel);
    }
    public void updateUserCacheModel( UserCacheModel cacheModel){
        this.userCacheModelConcurrentHashMap.put(cacheModel.getUserName(),cacheModel);
        this.userCacheModelConcurrentHashMap.put(cacheModel.getUserId(),cacheModel);
    }
    public boolean isExistWxyh(TXtWxyhModel model){
        return this.wxyhModelConcurrentHashMap.containsKey(model.getopenid());
    }
    public TXtWxyhModel getWxyhCacheModel(String openid){
        return this.wxyhModelConcurrentHashMap.get(openid);
    }
    public void updateWxyhCache(TXtWxyhModel model){
        this.wxyhModelConcurrentHashMap.put(model.getopenid(),model);
    }



    public void updateZhxxCacheModel(TXtAqZhxxModel zhxxModel){
        UserCacheModel cacheModel=new UserCacheModel();
        cacheModel.setUserId(zhxxModel.getry_xh());
        cacheModel.setUserName(zhxxModel.getyhm());
        cacheModel.setPassword(zhxxModel.getmm());
        cacheModel.setName(zhxxModel.getmc());
        cacheModel.setAccountType(zhxxModel.getyhlx_dm());
        this.zhxxCacheModelHashMap.put(zhxxModel.getyhm(),cacheModel);
        this.zhxxCacheModelHashMap.put(zhxxModel.getry_xh(),cacheModel);
    }
    public void updateZhxxCacheModel(UserCacheModel zhxxModel){
        this.zhxxCacheModelHashMap.put(zhxxModel.getUserName(),zhxxModel);
        this.zhxxCacheModelHashMap.put(zhxxModel.getUserId(),zhxxModel);
    }
    public UserCacheModel getZhxxCacheModelById(String userId){
        if(this.zhxxCacheModelHashMap.containsKey(userId)){
            return this.zhxxCacheModelHashMap.get(userId);
        }
        return null;
    }

    public void updateQzCacheModel(TXtGyQzModel model){
        TXtGyQzDtoModel dtoModel=new TXtGyQzDtoModel();
        BeanUtils.copyProperties(model,dtoModel);
        this.tXtGyQzDtoModelConcurrentHashMap.put(dtoModel.getgroup_id(),dtoModel);
    }

    public List<TXtGyQzDtoModel> getQzCacheByCommunityId(String community_id){
        List<TXtGyQzDtoModel> qzDtoModels=new ArrayList<>();
        if(!Utility.isNullOrEmpty(community_id)){
            for(TXtGyQzDtoModel dtoModel:this.tXtGyQzDtoModelConcurrentHashMap.values()){
                if(community_id.equals(dtoModel.getcommunity_id())){
                    qzDtoModels.add(dtoModel);
                }
            }
        }
        return qzDtoModels;
    }
    public TXtGyQzDtoModel getQzCacheByGroupId(String group_id){
        TXtGyQzDtoModel model=null;
        if(!Utility.isNullOrEmpty(group_id)){
            for(TXtGyQzDtoModel dtoModel:this.tXtGyQzDtoModelConcurrentHashMap.values()){
                if(group_id.equals(dtoModel.getgroup_id())){
                    model=dtoModel;
                }
            }
        }
        return model;
    }

    public void updateSqCacheModel(TXtGySqModel model){
        TXtGySqDtoModel sqDtoModel=new TXtGySqDtoModel();
        BeanUtils.copyProperties(model,sqDtoModel);
        this.tXtGySqDtoModelConcurrentHashMap.put(sqDtoModel.getcommunity_id(),sqDtoModel);
    }
    public List<TXtGySqDtoModel> getSqCacheByStreetId(String street_id){
        List<TXtGySqDtoModel> sqDtoModels= new ArrayList<>();
        if(!Utility.isNullOrEmpty(street_id)){
            for (TXtGySqDtoModel dtoModel:this.tXtGySqDtoModelConcurrentHashMap.values()){
                if(street_id.equals(dtoModel.getstreet_id())){
                    sqDtoModels.add(dtoModel);
                }
            }
        }
        return sqDtoModels;
    }
    public void updateJdCacheModel(TXtGyJdModel model){
        TXtGyJdDtoModel dtoModel=new TXtGyJdDtoModel();
        BeanUtils.copyProperties(model,dtoModel);
        this.tXtGyJdDtoModelConcurrentHashMap.put(dtoModel.getstreet_id(),dtoModel);
    }

    public List<TXtGyJdDtoModel> getJdCache(){
        List<TXtGyJdDtoModel> dtoModels=new ArrayList<>();
        for(TXtGyJdDtoModel dtoModel:this.tXtGyJdDtoModelConcurrentHashMap.values()){
            dtoModels.add(dtoModel);
        }
        return dtoModels;
    }
}
