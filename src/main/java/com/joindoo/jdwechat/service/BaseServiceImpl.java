package com.joindoo.jdwechat.service;


import com.joindoo.jdwechat.Utility;
import com.joindoo.jdwechat.beans.*;
import com.joindoo.jdwechat.codeGen.TYwXxlrDtoModel;
import com.joindoo.jdwechat.common.WeChatEnums;
import com.joindoo.jdwechat.controller.api.BaseApiController;
import com.joindoo.jdwechat.daos.*;
import com.joindoo.jdwechat.data.DBHelper;
import com.joindoo.jdwechat.data.DataContext;
import com.joindoo.jdwechat.data.DataService;
import com.joindoo.jdwechat.entity.NoticeModel;
import com.joindoo.jdwechat.entity.config.DruidConfig;
import com.joindoo.jdwechat.entity.config.SysProperties;
import com.joindoo.jdwechat.model.BaseResultModel;
import com.joindoo.jdwechat.model.query.BaseResultQueryModel;
import com.joindoo.jdwechat.model.query.PagingOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class BaseServiceImpl {
    //回复
    @Transactional
    public String replyXxlr(DataService dataService,DataContext dataContext,TYwXxlrModel model,String loginId,String loginMc,String replyRyXh) {
        String result=null;
        //根据当前登录人和XXLR_XH 把处理状态设置为已回复，定时查找未回复信息的推送就不会再通知
        String sql2="update "+TYwXxlr2RyModel.TABLE_NAME+" set clzt_dm=1 where ry_xh="+Utility.getSqlFielStatement("string",loginId)+" and xxlr_xh="+Utility.getSqlFielStatement("string",model.getsj_xxlr_xh());
        DBHelper.executeNonQuery(DataContext.getCurrentConnection(),sql2);
        //先查原纪录是否存在
        BaseResultQueryModel queryModel=new BaseResultQueryModel();
        queryModel.setxxlr_xh(model.getsj_xxlr_xh());
        queryModel.setsfyx_bj(1);
        List<TYwXxlrDtoModel> childList = dataService.selectT_YW_XXLR(new PagingOptions(), queryModel);
        if(childList.size()<=0) {
            result="该记录不存在，无法回复";
        }else{
            TYwXxlrDao dao = new TYwXxlrDao(dataContext);
            String fileIdStr = model.getfj_xh();
            Date nowDate=new Date();
            try {
                //附件处理
                if (!Utility.isNullOrEmpty(fileIdStr)) {
                    model.setfj_xh(Utility.createUniqueId());
                    TXtGyFjxxDao fjxxDao = new TXtGyFjxxDao(dataContext);
                    Collection<TXtGyFjxxModel> fjDatas = this.getFjxxEntities(fjxxDao, model.getfj_xh(), fileIdStr);
                    for (TXtGyFjxxModel m : fjDatas) {
                        fjxxDao.insertOnSubmit(m);
                    }
                }
                model.setxxlr_xh(Utility.createUniqueId());
                model.setlr_sj(nowDate);
                model.setxg_sj(nowDate);
                model.setlrry_xh(loginId);
                model.setxgry_xh(loginId);
                model.setsfyx_bj(1);
                dao.insertOnSubmit(model);

                if(Utility.isNullOrEmpty(loginMc)){
                    //根据传入的登录序号查询登录人名称
                    TXtAqZhxxDao zhxxDao = new TXtAqZhxxDao(dataContext);
                    List<BaseModel> allzhxx = zhxxDao.findAll("ry_xh=? ", loginId);
                    if (!ObjectUtils.isEmpty(allzhxx) && allzhxx.size() > 0) {
                        TXtAqZhxxModel zhxxModel = (TXtAqZhxxModel) allzhxx.get(0);
                        if(zhxxModel!=null)loginMc=zhxxModel.getmc();
                    }
                }

                List<String> tzryList = new ArrayList<>();//通知人员
                tzryList.add(replyRyXh);
                //通知提醒
                NoticeModel noticeModel = new NoticeModel();
                noticeModel.copyWithReplyXxlr(model, loginId, loginMc);
                result=generateNoticeRecord(dataService,dataContext,nowDate,tzryList, noticeModel);
            } catch (Exception e) {
                e.printStackTrace();
                result=e.getMessage();
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

            } finally {
                dataContext.release();
                dataService.disposeInCurrentThread();
            }
        }
        return result;
    }



    //添加
    public String saveXxlr(DataService dataService,DataContext dataContext,String loginId,TYwXxlrModel model, String ryxhs,String hf_sj_string){
        String result=null;
        TYwXxlrDao dao = new TYwXxlrDao(dataContext);
        try {

            Date nowDate=new Date();
            model = this.initAsInsert(model, nowDate, loginId);
            if(!Utility.isNullOrEmpty(hf_sj_string)){
                model.sethf_sj(Utility.parseDate(hf_sj_string));
            }
            dao.insertOnSubmit(model);
            String[] ry_xhs = Utility.getSeparatedItems(ryxhs, ',');//艾特人员序号
            if (ry_xhs.length > 0) {
                TYwXxlr2RyDao dao2 = new TYwXxlr2RyDao(dataContext);
                List<String> tzryList = new ArrayList<>();//通知人员
                for (String ry_xh : ry_xhs) {
                    tzryList.add(ry_xh);
                    TYwXxlr2RyModel t = new TYwXxlr2RyModel();
                    t.setxxlr_2_ry_xh(Utility.createUniqueId());
                    t.setxxlr_xh(model.getxxlr_xh());
                    t.setry_xh(ry_xh);
                    t.setsfyx_bj(1);
                    t.setclzt_dm("0");
                    dao2.insertOnSubmit(t);
                }
                //通知提醒
                NoticeModel noticeModel = new NoticeModel();
                noticeModel.copyWithSaveXxlr(model, loginId);
                result=generateNoticeRecord(dataService,dataContext,nowDate,tzryList, noticeModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result=e.getMessage();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

        } finally {
            dataContext.release();
            dataService.disposeInCurrentThread();
        }
       return  result;
    }

    protected TYwXxlrModel initAsInsert(TYwXxlrModel model, Date nowDate, String loginId) {
        model.setlr_sj(nowDate);
        model.setxg_sj(nowDate);
        model.setlrry_xh(loginId);
        model.setxgry_xh(loginId);
        model.setsfyx_bj(1);
        if (Utility.isNullOrEmpty(model.getxxlr_xh())) {
            model.setxxlr_xh(Utility.createUniqueId());
        }
        return model;
    }

    //region 处理附件信息
    protected Collection<TXtGyFjxxModel> getFjxxEntities(TXtGyFjxxDao dao, String zfj_xh, String filesIdStr) throws Exception {
        List<TXtGyFjxxModel> fjList = new ArrayList<TXtGyFjxxModel>();
        String[] idArray = Utility.getSeparatedItems(filesIdStr, '|');
        for (String id : idArray) {
            TXtGyFjxxModel queryModel = new TXtGyFjxxModel();
            queryModel.setfj_xh(id);
            TXtGyFjxxModel model = dao.find(queryModel);
            if (model == null) {
                model = new TXtGyFjxxModel();
                model.setfj_xh(id);
                model.setsfyx_bj(1);
                model.setzfj_xh(zfj_xh);
                model.setlr_sj(new Date());
                fjList.add(model);
            } else {
                model.setzfj_xh(zfj_xh);
                dao.updateOnSubmit(model);
            }
        }
        return fjList;
    }
    //endregion

    //region 处理通知提醒(普通通知提醒，往通知提醒表中插入数据)
    protected String generateNoticeRecord(DataService dataService,DataContext dataContext,Date nowDate,Collection<String> userIds, NoticeModel model) throws Exception {
        if (model == null || userIds == null)
            return "通知提醒，参数不正确";
        TTxTztxjlModel tztx = new TTxTztxjlModel();
        tztx.settztxjl_xh(Utility.createUniqueId());
        tztx.settx_nr(model.getMsg());
        tztx.setfqry_xh(model.getFqry());
        tztx.settzry_sl(userIds.size());
        tztx.setbt(model.getTitle());
        tztx.setxgjl_zj_xh(model.getOriginalId());
        tztx.setlr_sj(nowDate);
        tztx.setxg_sj(nowDate);
        tztx.setlrry_xh(model.getLrry());
        tztx.setxgry_xh(model.getLrry());

        List<TTxTztxjl2RyModel> toUserlist = new ArrayList<TTxTztxjl2RyModel>();
        for (String userid : userIds) {
            TTxTztxjl2RyModel tempModel = new TTxTztxjl2RyModel();
            tempModel.settztxjl_xh(tztx.gettztxjl_xh());
            tempModel.setry_xh(userid);
            tempModel.setlr_sj(nowDate);
            tempModel.setxg_sj(nowDate);
            tempModel.setlrry_xh(model.getLrry());
            tempModel.setxgry_xh(model.getLrry());
            tempModel.setsfyx_bj(1);
            tempModel.setclzt_dm(WeChatEnums._未处理.getIndex());
            toUserlist.add(tempModel);
        }
        TTxTztxjlDao tztxjlDao = new TTxTztxjlDao(dataContext);
        TTxTztxjl2RyDao txjl2ryDao=new TTxTztxjl2RyDao(dataContext);
        tztxjlDao.insertOnSubmit(tztx);
        for (TTxTztxjl2RyModel t:toUserlist){
            txjl2ryDao.insertOnSubmit(t);
        }

        dataContext.release();
        dataService.disposeInCurrentThread();
        return null;
    }
    //endregion

}
