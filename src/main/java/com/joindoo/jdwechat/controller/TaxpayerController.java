package com.joindoo.jdwechat.controller;

import com.joindoo.jdwechat.SystemSetting;
import com.joindoo.jdwechat.Utility;
import com.joindoo.jdwechat.beans.BaseModel;
import com.joindoo.jdwechat.beans.TXtGyFjxxModel;
import com.joindoo.jdwechat.beans.TYwXkzModel;
import com.joindoo.jdwechat.cache.SessionModel;
import com.joindoo.jdwechat.cache.UserCacheModel;
import com.joindoo.jdwechat.codeGen.TYwNsrDtoModel;
import com.joindoo.jdwechat.codeGen.TYwXkzDtoModel;
import com.joindoo.jdwechat.daos.TXtGyFjxxDao;
import com.joindoo.jdwechat.daos.TYwXkzDao;
import com.joindoo.jdwechat.data.DataContext;
import com.joindoo.jdwechat.data.DataService;
import com.joindoo.jdwechat.entity.config.DruidConfig;
import com.joindoo.jdwechat.entity.config.SysProperties;
import com.joindoo.jdwechat.model.BaseResultModel;
import com.joindoo.jdwechat.model.ResultListModel;
import com.joindoo.jdwechat.model.query.PagingOptions;
import com.joindoo.jdwechat.model.query.TXtGyFjxxQueryModel;
import com.joindoo.jdwechat.model.query.TYwNsrQueryModel;
import com.joindoo.jdwechat.model.query.TYwXkzQueryModel;
import com.joindoo.jdwechat.model.sys.FileModel;
import com.joindoo.jdwechat.service.ExcelService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/Taxpayer")
public class TaxpayerController extends BaseController {
    @Autowired
    SysProperties sysProperties;
    @Autowired
    DruidConfig druidConfig;


    //查询许可证
    @RequestMapping("/searchTaxpayer")
    public ResponseEntity<ResultListModel> searchTaxpayer(HttpServletRequest servletRequest, HttpServletResponse httpServletResponse, PagingOptions pagingOptions, TYwNsrQueryModel queryModel) throws Exception {
        ResultListModel resultListModel = new ResultListModel();
        SessionModel sessionModel = ChkLogin(servletRequest);
        if (!sessionModel.isLogin()){
            resultListModel.setLoginStatus(HttpStatus.UNAUTHORIZED.value());
            resultListModel.setSuccess(false);
        }else{
            DataService dataService = this.getDataService();
            DataContext dataContext = dataService.getDataContext(druidConfig);
            if (pagingOptions.getPageSize() == 0) pagingOptions.setPageSize(20);
            pagingOptions.setNeedTotal(true);
            queryModel.setsfyx_bj(1);
            List<TYwNsrDtoModel>  dtoModelList= dataService.selectT_YW_NSR(pagingOptions, queryModel,null);
            for(TYwNsrDtoModel dtoModel:dtoModelList){
                if(!Utility.isNullOrEmpty(dtoModel.getlrry_xh())){
                    UserCacheModel cacheModel=SystemSetting.JD_ServerCache.getUserCacheModelById(dtoModel.getlrry_xh());
                    if(cacheModel!=null){
                        dtoModel.setLrry_mc(cacheModel.getUserName());
                    }
                }
            }
            int start = pagingOptions.getStart();
            resultListModel.setRows(dtoModelList);
            resultListModel.setStart(start);
            resultListModel.setSuccess(true);
            resultListModel.setResultCount(dtoModelList.size());
            resultListModel.setTotal(pagingOptions.getTotal());
            dataContext.release();
            dataService.disposeInCurrentThread();
        }
        return new ResponseEntity<>(resultListModel, HttpStatus.OK);
    }


    //添加信息
    @Transactional
    @RequestMapping("/saveTaxpayer")
    public ResponseEntity<BaseResultModel> saveTaxpayer(HttpServletRequest servletRequest, HttpServletResponse httpServletResponse,TYwXkzDtoModel model) throws Exception {
        BaseResultModel baseResultModel = new BaseResultModel();
        String loginId = "";
        SessionModel sessionModel = ChkLogin(servletRequest);
        if (sessionModel.isLogin() == false){
            baseResultModel.setLoginStatus(HttpStatus.UNAUTHORIZED.value());
            baseResultModel.setSuccess(false);
        }else{
            loginId = sessionModel.getUserId();
            if(Utility.isNullOrEmpty(model.getjgdw_mc())){
                baseResultModel.setSuccess(false);
                baseResultModel.setMsg("请输入机构单位名称");
                return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
            }
            DataService dataService = this.getDataService();
            DataContext dataContext = dataService.getDataContext(druidConfig);
            TYwXkzDao dao = new TYwXkzDao(dataContext);
            Date now=new Date();
            try {
                boolean isNew=true;
                TYwXkzModel xkzModel=new TYwXkzModel();
                if(!Utility.isNullOrEmpty(model.getxkz_xh())){
                    xkzModel.setxkz_xh(model.getxkz_xh());
                    TYwXkzModel old_model= dao.find(xkzModel);
                    if(old_model!=null){
                        isNew=false;
                        xkzModel=old_model;
                    }
                }
                if(isNew){//新增
                    BeanUtils.copyProperties(model,xkzModel);
                    xkzModel.setxkz_xh(Utility.createUniqueId());
                    xkzModel.setlr_sj(now);
                    xkzModel.setlrry_xh(loginId);
                    xkzModel.setxg_sj(now);
                    xkzModel.setxgry_xh(loginId);
                    xkzModel.setsfyx_bj(1);
                    dao.insertOnSubmit(xkzModel);
                }else{//修改
                    xkzModel.doUpdate();
                    xkzModel.setlx_dm(model.getlx_dm());
                    xkzModel.setxg_sj(now);
                    xkzModel.setxkz_bh(model.getxkz_bh());
                    xkzModel.setjgdw_mc(model.getjgdw_mc());
                    xkzModel.setdzzs(model.getdzzs());
                    xkzModel.settyshxydm(model.gettyshxydm());
                    xkzModel.setfddbr_fzr(model.getfddbr_fzr());
                    xkzModel.setzczb_je(model.getzczb_je());
                    xkzModel.setfwfw(model.getfwfw());
                    xkzModel.setxkwh(model.getxkwh());
                    xkzModel.setfzrq(model.getfzrq());
                    xkzModel.setyxrq_qs(model.getyxrq_qs());
                    xkzModel.setyxrq_jz(model.getyxrq_jz());
                    xkzModel.setfzjg(model.getfzjg());
                    xkzModel.setfj_xh(model.getfj_xh());
                    xkzModel.setjgxz(model.getjgxz());
                    xkzModel.setlxdh(model.getlxdh());
                    xkzModel.setlbzh(model.getlbzh());
                    dao.updateOnSubmit(xkzModel);
                }

                baseResultModel.setSuccess(true);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error(e.getMessage());
                baseResultModel.setSuccess(false);
            } finally {
                dataContext.release();
                dataService.disposeInCurrentThread();
            }
        }
        return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
    }

    @Transactional
    @RequestMapping("/importTaxpayer")
    public ResponseEntity<BaseResultModel> importTaxpayer(HttpServletRequest servletRequest, HttpServletResponse httpServletResponse,TYwXkzDtoModel model) throws Exception {
        BaseResultModel baseResultModel = new BaseResultModel();
        String loginId = "";
        SessionModel sessionModel = ChkLogin(servletRequest);
        if (sessionModel.isLogin() == false){
            baseResultModel.setLoginStatus(HttpStatus.UNAUTHORIZED.value());
            baseResultModel.setSuccess(false);
        }else{
            loginId = sessionModel.getUserId();
            logger.info(model.getUpload_fj_xh());
            DataService dataService = this.getDataService();
            DataContext dataContext = dataService.getDataContext(druidConfig);
            Date now=new Date();
            try {
                TXtGyFjxxDao dao=new TXtGyFjxxDao(dataContext);
                TXtGyFjxxModel model1=new TXtGyFjxxModel();
                model1.setfj_xh(model.getUpload_fj_xh());
                TXtGyFjxxModel fjxxModel=dao.find(model1);
                if(fjxxModel!=null&&Utility.isFileExists(fjxxModel.getfj_lj())){
                    ExcelService service=new ExcelService();
                    HashMap<Integer,HashMap<Integer,Object>> hashMapHashMap  = service.getExcelData(fjxxModel.getfj_lj());
                    if(hashMapHashMap!=null){
                        TYwXkzDao xkzDao=new TYwXkzDao(dataContext);
                        Collection<BaseModel> collection=xkzDao.findAll(" sfyx_bj=1");
                        HashMap<String,TYwXkzModel> hashMap=new HashMap<>();
                        for(BaseModel baseModel:collection){
                            TYwXkzModel xkzModel= (TYwXkzModel) baseModel;
                            if(!hashMap.containsKey(xkzModel.getlbzh())){
                                hashMap.put(xkzModel.getlbzh(),xkzModel);
                            }
                        }
                        for(int i=0;i<hashMapHashMap.keySet().size();i++){
                            HashMap<Integer,Object> temp=hashMapHashMap.get(i);
                            if(!Utility.isNullOrEmpty(temp.get(0))&& !temp.get(0).toString().contains("序号")){
                                if(!hashMap.containsKey(temp.get(1).toString().trim())){
                                    TYwXkzModel o=new TYwXkzModel();
                                    o.setxkz_xh(Utility.createUniqueId());
                                    o.setlr_sj(now);
                                    o.setxg_sj(now);
                                    o.setlrry_xh(loginId);
                                    o.setxgry_xh(loginId);
                                    o.setsfyx_bj(1);
                                    o.setlbzh(temp.get(1).toString());
                                    o.setjgdw_mc(temp.get(2).toString());
                                    o.setdzzs(temp.get(3).toString());
                                    String lx=temp.get(4).toString();
                                    if(lx.contains("人力资源服务许可证"))
                                        o.setlx_dm("1");
                                    else
                                        o.setlx_dm("2");
                                    o.setfddbr_fzr(temp.get(5).toString());
                                    o.setlxdh(temp.get(6).toString());
                                    o.setxkz_bh(temp.get(7).toString());
                                    o.settyshxydm(temp.get(8).toString());
                                    o.setjgxz(temp.get(9).toString());
                                    String fzrq=temp.get(10).toString().trim();
                                    String yxq_qs=temp.get(11).toString().trim();
                                    String yxq_jz=temp.get(12).toString().trim();
                                    if(!Utility.isNullOrEmpty(fzrq))
                                        o.setfzrq(Utility.parseDate(fzrq) );
                                    if(!Utility.isNullOrEmpty(yxq_qs))
                                        o.setyxrq_qs(Utility.parseDate(yxq_qs));
                                    if(!Utility.isNullOrEmpty(yxq_jz))
                                        o.setyxrq_jz(Utility.parseDate(yxq_jz));
                                    o.setfwfw(temp.get(13).toString().trim());
                                    xkzDao.insertOnSubmit(o);
                                }
                            }
                        }
                    }
                }

                baseResultModel.setSuccess(true);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error(e.getMessage());
                baseResultModel.setSuccess(false);
            } finally {
                dataContext.release();
                dataService.disposeInCurrentThread();
            }
        }
        return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
    }

    @RequestMapping("/queryFileInfo")
    public ModelAndView queryFileInfo(HttpServletRequest request, HttpServletResponse response, TXtGyFjxxQueryModel queryModel) throws UnsupportedEncodingException {
        BaseResultModel baseResultModel = new BaseResultModel();
        SessionModel sessionModel = ChkLogin(request);
        FileModel fileModel=new FileModel();
        fileModel.setFilePath("");
        fileModel.setType("2");
        if(Utility.isNullOrEmpty(queryModel.getfj_xh())){
            baseResultModel.setSuccess(false);
            baseResultModel.setMsg("参数有误");
            return onProcessFile(response,request,fileModel);
        }
        if (sessionModel.isLogin() == false){
            baseResultModel.setLoginStatus(HttpStatus.UNAUTHORIZED.value());
            baseResultModel.setSuccess(false);
        }else{
            DataService dataService=this.getDataService();
            DataContext dataContext = dataService.getDataContext(druidConfig);
            TXtGyFjxxDao fjxxDao = new TXtGyFjxxDao(dataContext);
            List<BaseModel> list=fjxxDao.findAll("fj_xh=?",queryModel.getfj_xh()) ;
            if(list.size()>0){
                TXtGyFjxxModel fjxxModel= (TXtGyFjxxModel) list.get(0);
                fileModel.setFilePath(fjxxModel.getfj_lj());
                return onProcessFile(response,request,fileModel);
            }
            baseResultModel.setSuccess(true);
        }
        return onProcessFile(response,request,fileModel);
    }

    @Transactional
    @RequestMapping("/deleteTaxpayer")
    public ResponseEntity<BaseResultModel> deleteLicence(HttpServletRequest servletRequest,TYwXkzDtoModel model) throws Exception {
        BaseResultModel baseResultModel = new BaseResultModel();
        String loginId = "";
        SessionModel sessionModel = ChkLogin(servletRequest);
        if (sessionModel.isLogin() == false){
            baseResultModel.setLoginStatus(HttpStatus.UNAUTHORIZED.value());
            baseResultModel.setSuccess(false);
        }else{
            loginId = sessionModel.getUserId();
            if(Utility.isNullOrEmpty(model.getxkz_xh())){
                baseResultModel.setSuccess(false);
                baseResultModel.setMsg("请选择记录");
                return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
            }
            DataService dataService = this.getDataService();
            DataContext dataContext = dataService.getDataContext(druidConfig);
            TYwXkzDao dao = new TYwXkzDao(dataContext);
            Date now=new Date();
            try {
                TYwXkzModel xkzModel=new TYwXkzModel();
                if(!Utility.isNullOrEmpty(model.getxkz_xh())){
                    xkzModel.setxkz_xh(model.getxkz_xh());
                    TYwXkzModel old_model= dao.find(xkzModel);
                    if(old_model!=null){
                        xkzModel=old_model;
                        xkzModel.doUpdate();
                        xkzModel.setsfyx_bj(0);
                        xkzModel.setxg_sj(now);
                        xkzModel.setxgry_xh(loginId);
                        dao.updateOnSubmit(xkzModel);
                        baseResultModel.setSuccess(true);
                        baseResultModel.setMsg("记录删除成功");
                    }else {
                        baseResultModel.setSuccess(false);
                        baseResultModel.setMsg("未找到记录");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.error(e.getMessage());
                baseResultModel.setSuccess(false);
            } finally {
                dataContext.release();
                dataService.disposeInCurrentThread();
            }
        }
        return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
    }

}
