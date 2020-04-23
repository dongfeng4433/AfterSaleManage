package com.joindoo.jdwechat.controller;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiUserGetRequest;
import com.dingtalk.api.request.OapiUserGetuserinfoRequest;
import com.dingtalk.api.response.OapiUserGetResponse;
import com.dingtalk.api.response.OapiUserGetuserinfoResponse;
import com.joindoo.jdwechat.SystemSetting;
import com.joindoo.jdwechat.Utility;
import com.joindoo.jdwechat.beans.BaseModel;
import com.joindoo.jdwechat.beans.TDataEnterpriseModel;
import com.joindoo.jdwechat.cache.SessionModel;
import com.joindoo.jdwechat.codeGen.TDataEnterpriseOrderDtoModel;
import com.joindoo.jdwechat.daos.TDataEnterpriseDao;
import com.joindoo.jdwechat.data.DataContext;
import com.joindoo.jdwechat.data.DataService;
import com.joindoo.jdwechat.dingtalk.ServiceResult;
import com.joindoo.jdwechat.model.BaseResultModel;
import com.joindoo.jdwechat.model.ResultListModel;
import com.joindoo.jdwechat.model.query.DingTalkQueryModel;
import com.joindoo.jdwechat.model.query.PagingOptions;
import com.joindoo.jdwechat.model.query.TDataEnterpriseOrderQueryModel;
import com.joindoo.jdwechat.utils.AccessTokenUtil;
import com.taobao.api.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/DingTalk")
public class DingTalkController extends BaseController{
    private static final Logger bizLogger = LoggerFactory.getLogger(DingTalkController.class);

    /**
     * 欢迎页面,通过url访问，判断后端服务是否启动
     */
    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome() {
        return "welcome";
    }

    /**
     * 钉钉用户登录，显示当前登录用户的userId和名称
     *
     * @param queryModel
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<BaseResultModel> login(DingTalkQueryModel queryModel) {
        BaseResultModel baseResultModel = new BaseResultModel();
        if(Utility.isNullOrEmpty(queryModel.getCropId())||Utility.isNullOrEmpty(queryModel.getAuthCode())){
            baseResultModel.setSuccess(false);
            baseResultModel.setMsg("参数有误");
            return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
        }
        DataService dataService=this.getDataService();
        DataContext dataContext = dataService.getDataContext(druidConfig);
        TDataEnterpriseDao dao=new TDataEnterpriseDao(dataContext);
        try {
            List<BaseModel> list= dao.findAll("dd_corp_id=?",queryModel.getCropId());
            if(list==null||list.size()==0){
                baseResultModel.setSuccess(false);
                baseResultModel.setMsg("参数有误");
                return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
            }
            TDataEnterpriseModel enterpriseModel=(TDataEnterpriseModel)list.get(0);
            //获取accessToken,注意正是代码要有异常流处理
            String accessToken = AccessTokenUtil.getToken(enterpriseModel.getenterprise_id());

            //获取用户信息
            DingTalkClient client = new DefaultDingTalkClient(SystemSetting.DINGTALK_URL_GET_USER_INFO);
            OapiUserGetuserinfoRequest request = new OapiUserGetuserinfoRequest();
            request.setCode(queryModel.getAuthCode());
            request.setHttpMethod("GET");

            OapiUserGetuserinfoResponse response;
            try {
                response = client.execute(request, accessToken);
            } catch (ApiException e) {
                e.printStackTrace();
                return null;
            }
            //3.查询得到当前用户的userId
            // 获得到userId之后应用应该处理应用自身的登录会话管理（session）,避免后续的业务交互（前端到应用服务端）每次都要重新获取用户身份，提升用户体验
            String userId = response.getUserid();

            OapiUserGetResponse userInfo = getUserInfo(accessToken, userId);
            if(userInfo!=null){
                System.out.println(userInfo.getName());
                //返回结果
                Map<String, Object> resultMap = new HashMap<>();
                resultMap.put("userId", userId);
                resultMap.put("userName", userInfo.getName());
                resultMap.put("mobile",userInfo.getMobile());
                baseResultModel.setTag(resultMap);
                baseResultModel.setSuccess(true);
            }

        }catch (Exception ex){
            logger.error(ex.getMessage());
        }finally {
            dataContext.release();
            dataService.disposeInCurrentThread();
        }

        return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
    }

    /**
     * 获取用户姓名
     *
     * @param accessToken
     * @param userId
     * @return
     */
    private String getUserName(String accessToken, String userId) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(SystemSetting.DINGTALK_URL_USER_GET);
            OapiUserGetRequest request = new OapiUserGetRequest();
            request.setUserid(userId);
            request.setHttpMethod("GET");
            OapiUserGetResponse response = client.execute(request, accessToken);
            String mobile=response.getMobile();
            System.out.println(mobile);
            return response.getName();
        } catch (ApiException e) {
            e.printStackTrace();
            return null;
        }
    }
    private OapiUserGetResponse getUserInfo(String accessToken, String userId) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(SystemSetting.DINGTALK_URL_USER_GET);
            OapiUserGetRequest request = new OapiUserGetRequest();
            request.setUserid(userId);
            request.setHttpMethod("GET");
            OapiUserGetResponse response = client.execute(request, accessToken);
            String mobile=response.getMobile();
            System.out.println(mobile);
            return response;
        } catch (ApiException e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/queryOrderList",method = RequestMethod.POST)
    public ResponseEntity<ResultListModel> queryOrderList(HttpServletRequest request, PagingOptions pagingOptions, TDataEnterpriseOrderQueryModel queryModel) {
        ResultListModel resultListModel = new ResultListModel();
        SessionModel sessionModel=this.getSessionModel(request);
        DataService dataService = this.getDataService();
        DataContext dataContext = dataService.getDataContext(druidConfig);

        //归属企业查询
        fillQueryModel4Crop(dataContext,queryModel,sessionModel);
        queryModel.setenterprise_id(queryModel.getEnterprise_id());
        if(pagingOptions.getPageSize()==0)pagingOptions.setPageSize(20);
        pagingOptions.setNeedTotal(true);
        Collection<TDataEnterpriseOrderDtoModel> dtoModelList=dataService.SelectT_DATA_ENTERPRISE_ORDER(pagingOptions,queryModel,null);
        int start = pagingOptions.getStart();
        resultListModel.setRows(dtoModelList);
        resultListModel.setStart(start);
        resultListModel.setResultCount(dtoModelList.size());
        resultListModel.setTotal(pagingOptions.getTotal());
        dataContext.release();
        dataService.disposeInCurrentThread();
        return new ResponseEntity<>(resultListModel, HttpStatus.OK);
    }
}
