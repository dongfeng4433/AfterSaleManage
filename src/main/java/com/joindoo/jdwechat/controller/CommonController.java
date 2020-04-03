package com.joindoo.jdwechat.controller;

import com.joindoo.jdwechat.SystemSetting;
import com.joindoo.jdwechat.Utility;
import com.joindoo.jdwechat.beans.BaseModel;
import com.joindoo.jdwechat.beans.TXtGyJdModel;
import com.joindoo.jdwechat.beans.TXtGyQzModel;
import com.joindoo.jdwechat.beans.TXtGySqModel;
import com.joindoo.jdwechat.codeGen.TXtGyJdDtoModel;
import com.joindoo.jdwechat.codeGen.TXtGyQzDtoModel;
import com.joindoo.jdwechat.codeGen.beans.TXtQxXtmkDtoModel;
import com.joindoo.jdwechat.daos.TXtGyJdDao;
import com.joindoo.jdwechat.daos.TXtGyQzDao;
import com.joindoo.jdwechat.daos.TXtGySqDao;
import com.joindoo.jdwechat.data.DataContext;
import com.joindoo.jdwechat.data.DataService;
import com.joindoo.jdwechat.entity.config.DruidConfig;
import com.joindoo.jdwechat.model.BaseResultModel;
import com.joindoo.jdwechat.model.ResultListModel;
import com.joindoo.jdwechat.model.query.BaseQueryModel;
import com.joindoo.jdwechat.model.query.BaseResultQueryModel;
import com.joindoo.jdwechat.model.query.GroupQueryModel;
import com.joindoo.jdwechat.model.query.PagingOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by zhuqiang on 2018/11/18.
 */
@Controller
@RequestMapping("Common")
public class CommonController extends BaseController {
    @Autowired
    DruidConfig druidConfig;

    @RequestMapping(value = "/queryOrg",method = RequestMethod.GET)
    public ResponseEntity<BaseResultModel> queryOrg(HttpServletRequest request, GroupQueryModel queryModel){
        BaseResultModel baseResultModel = new BaseResultModel();
        if(Utility.isNullOrEmpty( queryModel.getJd_token())|| !SystemSetting.SYS_API_KEY.equals(queryModel.getJd_token())){
            logger.info("api jd_token is error");
            baseResultModel.setSuccess(false);
        }
        baseResultModel.setSuccess(true);
        if(!Utility.isNullOrEmpty(queryModel.getType_id())){
            if(!Utility.isNullOrEmpty(queryModel.getGroup_id())){
                TXtGyQzDtoModel qzDtoModel=SystemSetting.JD_ServerCache.getQzCacheByGroupId(queryModel.getGroup_id());
                //todo get data by group id
            }
            if("1".equals(queryModel.getType_id())){//街道
                List<TXtGyJdDtoModel> list=SystemSetting.JD_ServerCache.getJdCache();
                baseResultModel.setTag(list);
            }else if("2".equals(queryModel.getType_id())){//社区
                List list=SystemSetting.JD_ServerCache.getSqCacheByStreetId(queryModel.getStreet_id());
                baseResultModel.setTag(list);
            }else if("3".equals(queryModel.getType_id())){//群组
                List list=SystemSetting.JD_ServerCache.getQzCacheByCommunityId(queryModel.getCommunity_id());
                baseResultModel.setTag(list);
            }
        }

        return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
    }

    @RequestMapping(value = "/queryMenuTreeData",method = RequestMethod.GET)
    public ResponseEntity<BaseResultModel> queryMenuTreeData(HttpServletRequest request, BaseResultQueryModel queryModel){
        ResultListModel resultListModel = new ResultListModel();
        resultListModel.setSuccess(true);
        DataService dataService = this.getDataService();
        DataContext dataContext = dataService.getDataContext(druidConfig);
        PagingOptions pagingOptions=new PagingOptions();
        List<TXtQxXtmkDtoModel> dtoModelList= dataService.selectT_XT_QX_XTMK(pagingOptions,queryModel,null);
        resultListModel.setRows(dtoModelList);
        dataContext.release();
        dataService.disposeInCurrentThread();
        return new ResponseEntity<>(resultListModel, HttpStatus.OK);
    }
}
