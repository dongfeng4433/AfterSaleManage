package com.joindoo.jdwechat.controller;

import com.joindoo.jdwechat.Utility;
import com.joindoo.jdwechat.WeChatFields;
import com.joindoo.jdwechat.beans.BaseModel;
import com.joindoo.jdwechat.beans.TXtAqZhxxModel;
import com.joindoo.jdwechat.beans.TXtGyFjxxModel;
import com.joindoo.jdwechat.beans.TYwXxlrModel;
import com.joindoo.jdwechat.cache.SessionModel;
import com.joindoo.jdwechat.codeGen.TYwXxlrDtoModel;
import com.joindoo.jdwechat.daos.TXtAqZhxxDao;
import com.joindoo.jdwechat.daos.TXtGyFjxxDao;
import com.joindoo.jdwechat.daos.TYwXxlrDao;
import com.joindoo.jdwechat.data.DataContext;
import com.joindoo.jdwechat.data.DataService;
import com.joindoo.jdwechat.entity.NoticeModel;
import com.joindoo.jdwechat.entity.config.DruidConfig;
import com.joindoo.jdwechat.entity.config.SysProperties;
import com.joindoo.jdwechat.model.BaseResultModel;
import com.joindoo.jdwechat.model.query.BaseResultQueryModel;
import com.joindoo.jdwechat.model.query.PagingOptions;
import com.joindoo.jdwechat.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/Mobile")
public class MobileController extends BaseController  {
    @Autowired
    SysProperties sysProperties;
    @Autowired
    DruidConfig druidConfig;


    @RequestMapping("/ToMobile")
    public ModelAndView ToMobile(HttpServletRequest servletRequest) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("mobile/index");
        return modelAndView;
    }


    @RequestMapping("/replyXxlr")
    public ResponseEntity<BaseResultModel> replyXxlr(TYwXxlrModel model,String loginId,String replyRyXh) throws Exception {
        BaseResultModel baseResultModel = new BaseResultModel();
        if (Utility.isNullOrEmpty(loginId) ||Utility.isNullOrEmpty(model.getsj_xxlr_xh()) || Utility.isNullOrEmpty(replyRyXh)) {
            baseResultModel.setMsg("参数有误");
            baseResultModel.setSuccess(false);
            return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
        }
        DataService dataService=this.getDataService();
        DataContext dataContext = dataService.getDataContext(druidConfig);
        BaseServiceImpl baseServiceImpl=new BaseServiceImpl();
        String result= baseServiceImpl.replyXxlr(dataService,dataContext,model,loginId,null,replyRyXh);
        if(!Utility.isNullOrEmpty(result)){
            baseResultModel.setMsg(result);
            baseResultModel.setSuccess(false);
            return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
        }
        baseResultModel.setSuccess(true);
        return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
    }
}
