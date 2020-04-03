package com.joindoo.jdwechat.data;

import com.joindoo.jdwechat.SystemSetting;
import com.joindoo.jdwechat.Utility;
import com.joindoo.jdwechat.WeChatFields;
import com.joindoo.jdwechat.beans.*;
import com.joindoo.jdwechat.codeGen.*;
import com.joindoo.jdwechat.codeGen.beans.TXtAqZhxxDtoModel;
import com.joindoo.jdwechat.codeGen.beans.TXtGyFjxxDtoModel;
import com.joindoo.jdwechat.codeGen.beans.TXtQxXtmkDtoModel;
import com.joindoo.jdwechat.codeGen.beans.TXtQxRy2JsDtoModel;
import com.joindoo.jdwechat.common.WeChatEnums;
import com.joindoo.jdwechat.entity.config.DruidConfig;
import com.joindoo.jdwechat.model.query.*;
import com.joindoo.jdwechat.model.sys.ScriptItemModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * Created by zhuqiang1 on 2017/8/20.
 */
public class DataService extends BaseDataService {
    public List<JtSjSqblGdjbxxDtoModel> selectJT_SJ_SQBL_GDJBXX(PagingOptions pagingOptions, JtSjSqblGdjbxxQueryModel queryModel){
        List<JtSjSqblGdjbxxDtoModel> resultList=new ArrayList<>();
        ScriptItemModel scriptItemModel= SystemSetting.JD_ServerCache.getSciptItemModel(WeChatFields.Script_SelectJT_SJ_SQBL_GDJBXX);
        if(null==scriptItemModel){
            logger.info("Script_SelectT_SEARCH_GUIDETAX 查询脚本没有找到");
            return null;
        }
        queryModel.dataParamsHandler=new DataParamsHandler(){
            @Override
            public void resolveParams(ScriptItemModel scriptItemModel) {
                ArrayList<Object> params=new ArrayList<>();
                if(null!=queryModel){
                    String where="";
                    if(!Utility.isNullOrEmpty(queryModel.getcbdw())){
                        where+=" and A.CBDW Like ?";
                        params.add("%"+queryModel.getcbdw()+"%");
                    }
                    scriptItemModel.setSqlWhere(where);
                }
                scriptItemModel.setParams(params.toArray());
            }
        };
        Collection<IBaseModel> collection= SelectBaseData(DataContext.getCurrentConnection(),JtSjSqblGdjbxxModel.class,scriptItemModel,pagingOptions,queryModel);
        for (IBaseModel b:collection ) {
            JtSjSqblGdjbxxDtoModel dto=new JtSjSqblGdjbxxDtoModel();
            BeanUtils.copyProperties((JtSjSqblGdjbxxModel)b,dto);
            resultList.add(dto);
        }
        return resultList;
    }

    public List<TSqblGdjbxxDtoModel> selectT_SQBL_GDJBXX(PagingOptions pagingOptions, TSqblGdjbxxQueryModel queryModel){
        List<TSqblGdjbxxDtoModel> resultList=new ArrayList<>();
        ScriptItemModel scriptItemModel= SystemSetting.JD_ServerCache.getSciptItemModel(WeChatFields.Script_SelectT_SQBL_GDJBXX_2);
        if(null==scriptItemModel){
            logger.info("Script_SelectT_SQBL_GDJBXX 查询脚本没有找到");
            return null;
        }
        queryModel.dataParamsHandler=new DataParamsHandler(){
            @Override
            public void resolveParams(ScriptItemModel scriptItemModel) {
                ArrayList<Object> params=new ArrayList<>();
                if(null!=queryModel){
                    String where="";
                    if(!Utility.isNullOrEmpty(queryModel.getgdbh())){
                        where+=" and A.GDBH = ?";
                        params.add(queryModel.getgdbh());
                    }
                    if(!Utility.isNullOrEmpty(queryModel.getsqnr())){
                        where+=" and A.SQNR Like ?";
                        params.add("%"+queryModel.getsqnr()+"%");
                    }
                    if (!Utility.isNullOrEmpty(queryModel.getEndTime())&&!Utility.isNullOrEmpty(queryModel.getStartTime())){
                        where+=" and A.LR_SJ>=? and A.LR_SJ<=?";
                        String dateStr =new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(queryModel.getStartTime());
                        String dateStr1 =new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(queryModel.getEndTime());
                        params.add(dateStr);
                        params.add(dateStr1);
                    }
                    scriptItemModel.setSqlWhere(where);
                }
                scriptItemModel.setParams(params.toArray());
            }
        };
        Collection<IBaseModel> collection= SelectBaseData(DataContext.getCurrentConnection(),TSqblGdjbxxModel.class,scriptItemModel,pagingOptions,queryModel);
        for (IBaseModel b:collection ) {
            TSqblGdjbxxDtoModel dto=new TSqblGdjbxxDtoModel();
            BeanUtils.copyProperties((TSqblGdjbxxModel)b,dto);
            resultList.add(dto);
        }
        return resultList;
    }



    //region PC端后台
    public List<TXtQxRy2JsDtoModel> selectT_XT_QX_RY_2_JS(PagingOptions pagingOptions, TXtQxRy2JsQueryModel queryModel, String sql){
        List<TXtQxRy2JsDtoModel> resultList=new ArrayList<>();
        if(Utility.isNullOrEmpty(sql)){
            sql=WeChatFields.Script_SelectT_XT_QX_RY_2_JS;
        }
        ScriptItemModel scriptItemModel= SystemSetting.JD_ServerCache.getSciptItemModel(sql);
        if(null==scriptItemModel){
            logger.info(sql+" 查询脚本没有找到");
            return null;
        }
        queryModel.dataParamsHandler=new DataParamsHandler(){
            @Override
            public void resolveParams(ScriptItemModel scriptItemModel) {
                ArrayList<Object> params=new ArrayList<>();
                if(null!=queryModel){
                    String where="";
                    if(!Utility.isNullOrEmpty(queryModel.getjs_dm())){
                        where+=" AND A.JS_DM = ?";
                        params.add(queryModel.getjs_dm());
                    }
                    if(!Utility.isNullOrEmpty(queryModel.getry_xh())){
                        where+=" AND A.RY_XH = ?";
                        params.add(queryModel.getry_xh());
                    }
                    scriptItemModel.setSqlWhere(where);
                }
                scriptItemModel.setParams(params.toArray());
            }
        };
        Collection<IBaseModel> collection= SelectBaseData(DataContext.getCurrentConnection(),TXtQxRy2JsModel.class,scriptItemModel,pagingOptions,queryModel);
        for (IBaseModel b:collection ) {
            TXtQxRy2JsDtoModel dto=new TXtQxRy2JsDtoModel();
            BeanUtils.copyProperties((TXtQxRy2JsModel)b,dto);
            resultList.add(dto);
        }
        return resultList;
    }

    public List<TXtQxRy2BmDtoModel> selectT_XT_QX_RY_2_BM(PagingOptions pagingOptions, TXtQxRy2BmQueryModel queryModel, String sql){
        List<TXtQxRy2BmDtoModel> resultList=new ArrayList<>();
        if(Utility.isNullOrEmpty(sql)){
            sql=WeChatFields.Script_SelectT_XT_QX_RY_2_BM;
        }
        ScriptItemModel scriptItemModel= SystemSetting.JD_ServerCache.getSciptItemModel(sql);
        if(null==scriptItemModel){
            logger.info(sql+" 查询脚本没有找到");
            return null;
        }
        queryModel.dataParamsHandler=new DataParamsHandler(){
            @Override
            public void resolveParams(ScriptItemModel scriptItemModel) {
                ArrayList<Object> params=new ArrayList<>();
                if(null!=queryModel){
                    String where="";
                    if(!Utility.isNullOrEmpty(queryModel.getbm_dm())){
                        where+=" AND A.BM_DM = ?";
                        params.add(queryModel.getbm_dm());
                    }
                    if(!Utility.isNullOrEmpty(queryModel.getry_xh())){
                        where+=" AND A.RY_XH = ?";
                        params.add(queryModel.getry_xh());
                    }
                    scriptItemModel.setSqlWhere(where);
                }
                scriptItemModel.setParams(params.toArray());
            }
        };
        Collection<IBaseModel> collection= SelectBaseData(DataContext.getCurrentConnection(),TXtQxRy2BmModel.class,scriptItemModel,pagingOptions,queryModel);
        for (IBaseModel b:collection ) {
            TXtQxRy2BmDtoModel dto=new TXtQxRy2BmDtoModel();
            BeanUtils.copyProperties((TXtQxRy2BmModel)b,dto);
            resultList.add(dto);
        }
        return resultList;
    }


    public List<TYwXxlr2RyDtoModel> selectT_YW_XXLR_2_RY(PagingOptions pagingOptions, BaseResultQueryModel queryModel,String sql){
        List<TYwXxlr2RyDtoModel> resultList=new ArrayList<>();
        if(Utility.isNullOrEmpty(sql)){
            sql=WeChatFields.Script_SelectT_YW_XXLR_2_RY;
        }
        ScriptItemModel scriptItemModel= SystemSetting.JD_ServerCache.getSciptItemModel(sql);
        if(null==scriptItemModel){
            logger.info(sql+" 查询脚本没有找到");
            return null;
        }
        queryModel.dataParamsHandler=new DataParamsHandler(){
            @Override
            public void resolveParams(ScriptItemModel scriptItemModel) {
                ArrayList<Object> params=new ArrayList<>();
                if(null!=queryModel){
                    String where="";
                    if(!Utility.isNullOrEmpty(queryModel.getxxlr_xh())){
                        where+=" AND A.XXLR_XH = ?";
                        params.add(queryModel.getxxlr_xh());
                    }
                    if(!Utility.isNullOrEmpty(queryModel.getRy_xh())){
                        where+=" AND A.RY_XH = ?";
                        params.add(queryModel.getsj_xxlr_xh());
                    }

                    if(Utility.isNullOrEmpty(queryModel.getClzt())){
                        where+=" AND A.CLZT_DM IS NULL ";
                    }else {
                        where+=" AND A.CLZT_DM = ? ";
                        params.add(queryModel.getClzt());
                    }

                    scriptItemModel.setSqlWhere(where);
                }
                scriptItemModel.setParams(params.toArray());
            }
        };
        Collection<IBaseModel> collection= SelectBaseData(DataContext.getCurrentConnection(),TYwXxlr2RyModel.class,scriptItemModel,pagingOptions,queryModel);
        for (IBaseModel b:collection ) {
            TYwXxlr2RyDtoModel dto=new TYwXxlr2RyDtoModel();
            BeanUtils.copyProperties((TYwXxlr2RyModel)b,dto);
            resultList.add(dto);
        }
        return resultList;
    }

    public List<TYwXxlrDtoModel> selectT_YW_XXLR(PagingOptions pagingOptions, BaseResultQueryModel queryModel){
        List<TYwXxlrDtoModel> resultList=new ArrayList<>();
        ScriptItemModel scriptItemModel= SystemSetting.JD_ServerCache.getSciptItemModel(WeChatFields.Script_SelectT_YW_XXLR);
        if(null==scriptItemModel){
            logger.info("Script_SelectT_YW_XXLR 查询脚本没有找到");
            return null;
        }
        queryModel.dataParamsHandler=new DataParamsHandler(){
            @Override
            public void resolveParams(ScriptItemModel scriptItemModel) {
                ArrayList<Object> params=new ArrayList<>();
                if(null!=queryModel){
                    String where="";
                    if(!Utility.isNullOrEmpty(queryModel.getxxlr_xh())){
                        where+=" AND A.XXLR_XH = ?";
                        params.add(queryModel.getxxlr_xh());
                    }
                    if(!Utility.isNullOrEmpty(queryModel.getsj_xxlr_xh())){
                        where+=" AND A.SJ_XXLR_XH = ?";
                        params.add(queryModel.getsj_xxlr_xh());
                    }
                    if(!Utility.isNullOrEmpty(queryModel.getsqnr())){
                        where+=" AND A.SQNR Like ?";
                        params.add("%"+queryModel.getsqnr()+"%");
                    }
                    if(!Utility.isNullOrEmpty(queryModel.getRy_xh())){
                        where+=" AND A.LRRY_XH = ?";
                        params.add(queryModel.getRy_xh());
                    }
                    if(queryModel.getsfyx_bj()==null||queryModel.getsfyx_bj().intValue()==0){
                        where+=" AND A.SFYX_BJ IS NULL ";
                    }else if(queryModel.getsfyx_bj()!=null) {
                        where+=" AND A.SFYX_BJ = ? ";
                        params.add(queryModel.getsfyx_bj());
                    }
                    if(WeChatEnums._待办.getIndex().equals(queryModel.getLr_type())){
                        where+=" AND A.XXLR_XH IN (SELECT XXLR_XH FROM T_YW_XXLR_2_RY WHERE SFYX_BJ=1 AND RY_XH=?)";
                        params.add(queryModel.getlrry_xh());
                    }else if(WeChatEnums._发起.getIndex().equals(queryModel.getLr_type())){
                        where+=" AND A.LRRY_XH= ? AND A.SJ_XXLR_XH IS NULL";
                        params.add(queryModel.getlrry_xh());
                    }else if(WeChatEnums._历史记录.getIndex().equals(queryModel.getLr_type())){
                        where+=" AND A.SJ_XXLR_XH IS NULL";
                    }
                    scriptItemModel.setSqlWhere(where);
                }
                scriptItemModel.setParams(params.toArray());
            }
        };
        Collection<IBaseModel> collection= SelectBaseData(DataContext.getCurrentConnection(),TYwXxlrModel.class,scriptItemModel,pagingOptions,queryModel);
        for (IBaseModel b:collection ) {
            TYwXxlrDtoModel dto=new TYwXxlrDtoModel();
            BeanUtils.copyProperties((TYwXxlrModel)b,dto);
            resultList.add(dto);
        }
        return resultList;
    }

    public List<TXtAqZhxxDtoModel> selectT_XT_AQ_ZHXX(PagingOptions pagingOptions, TXtAqZhxxQueryModel queryModel,String sql){
        List<TXtAqZhxxDtoModel> resultList=new ArrayList<>();
        if(Utility.isNullOrEmpty(sql))
            sql=WeChatFields.Script_SelectT_XT_AQ_ZHXX;
        ScriptItemModel scriptItemModel= SystemSetting.JD_ServerCache.getSciptItemModel(sql);
        if(null==scriptItemModel){
            logger.info(sql+" 查询脚本没有找到");
            return null;
        }
        queryModel.dataParamsHandler=new DataParamsHandler(){
            @Override
            public void resolveParams(ScriptItemModel scriptItemModel) {
                ArrayList<Object> params=new ArrayList<>();
                if(null!=queryModel){
                    String where="";
                    if(!Utility.isNullOrEmpty(queryModel.getry_xh())){
                        where+=" AND A.RY_XH = ?";
                        params.add(queryModel.getry_xh());
                    }
                    if(!Utility.isNullOrEmpty(queryModel.getEnterprise_id())){
                        where+=" AND (B.enterprise_id=? and B.is_valid=1)";
                        params.add(queryModel.getEnterprise_id());
                    }
                    if(queryModel.isIs_search_4_select()){
                        //此处默认mc和sj_hm都存在
                        if(!Utility.isNullOrEmpty(queryModel.getmc())){
                            where+=" and (A.MC Like ? ";
                            params.add("%"+queryModel.getmc()+"%");
                        }
                        if(!Utility.isNullOrEmpty(queryModel.getsj_hm())){
                            where+=" OR A.SJ_HM like ? )";
                            params.add(queryModel.getsj_hm()+"%");
                        }
                    }else {
                        if(!Utility.isNullOrEmpty(queryModel.getsj_hm())){
                            where+=" AND A.SJ_HM like ?";
                            params.add(queryModel.getsj_hm()+"%");
                        }
                        if(!Utility.isNullOrEmpty(queryModel.getmc())){
                            where+=" and A.MC Like ?";
                            params.add("%"+queryModel.getmc()+"%");
                        }
                        //部门
                        if(!Utility.isNullOrEmpty(queryModel.getyyz_xh())){
                            where+=" AND A.RY_XH IN (SELECT RY_XH FROM T_XT_QX_RY_2_BM WHERE BM_DM=?)";
                            params.add(queryModel.getyyz_xh());
                        }
                    }

                    if(queryModel.getsfyx_bj()==null|| queryModel.getsfyx_bj() ==0){
                        where+=" AND A.SFYX_BJ is null ";
                    }else if(queryModel.getsfyx_bj()!=null) {
                        where+=" AND A.SFYX_BJ = ? ";
                        params.add(queryModel.getsfyx_bj());
                    }

                    scriptItemModel.setSqlWhere(where);
                }
                scriptItemModel.setParams(params.toArray());
            }
        };
        Collection<IBaseModel> collection= SelectBaseData(DataContext.getCurrentConnection(),TXtAqZhxxModel.class,scriptItemModel,pagingOptions,queryModel);
        for (IBaseModel b:collection ) {
            TXtAqZhxxDtoModel dto=new TXtAqZhxxDtoModel();
            BeanUtils.copyProperties((TXtAqZhxxModel)b,dto);
            resultList.add(dto);
        }
        return resultList;
    }

    public List<TXtGyFjxxDtoModel> selectT_XT_GY_FJXX(PagingOptions pagingOptions, TXtGyFjxxQueryModel queryModel){
        List<TXtGyFjxxDtoModel> resultList=new ArrayList<>();
        ScriptItemModel scriptItemModel= SystemSetting.JD_ServerCache.getSciptItemModel(WeChatFields.Script_SelectT_XT_GY_FJXX);
        if(null==scriptItemModel){
            logger.info("Script_SelectT_XT_GY_FJXX 查询脚本没有找到");
            return null;
        }
        queryModel.dataParamsHandler=new DataParamsHandler(){
            @Override
            public void resolveParams(ScriptItemModel scriptItemModel) {
                ArrayList<Object> params=new ArrayList<>();
                if(null!=queryModel){
                    String where="";
                    if(!Utility.isNullOrEmpty(queryModel.getfj_xh())){
                        where+=" AND A.FJ_XH = ?";
                        params.add(queryModel.getfj_xh());
                    }
                    if(!Utility.isNullOrEmpty(queryModel.getzfj_xh())){
                        where+=" AND A.ZFJ_XH = ?";
                        params.add(queryModel.getzfj_xh());
                    }
                    if(queryModel.getsfyx_bj()==null||queryModel.getsfyx_bj().intValue()==0){
                        where+=" AND A.SFYX_BJ is null ";
                    }else if(queryModel.getsfyx_bj()!=null) {
                        where+=" AND A.SFYX_BJ = ? ";
                        params.add(queryModel.getsfyx_bj());
                    }

                    scriptItemModel.setSqlWhere(where);
                }
                scriptItemModel.setParams(params.toArray());
            }
        };
        Collection<IBaseModel> collection= SelectBaseData(DataContext.getCurrentConnection(),TXtGyFjxxModel.class,scriptItemModel,pagingOptions,queryModel);
        for (IBaseModel b:collection ) {
            TXtGyFjxxDtoModel dto=new TXtGyFjxxDtoModel();
            BeanUtils.copyProperties((TXtGyFjxxModel)b,dto);
            resultList.add(dto);
        }
        return resultList;
    }

    public List<TTxTztxjlDtoModel> selectT_TX_TZTXJL(PagingOptions pagingOptions, BaseResultQueryModel queryModel){
        List<TTxTztxjlDtoModel> resultList=new ArrayList<>();
        ScriptItemModel scriptItemModel= SystemSetting.JD_ServerCache.getSciptItemModel(WeChatFields.Script_SelectT_TX_TZTXJL);
        if(null==scriptItemModel){
            logger.info("Script_SelectT_TX_TZTXJL 查询脚本没有找到");
            return null;
        }
        queryModel.dataParamsHandler=new DataParamsHandler(){
            @Override
            public void resolveParams(ScriptItemModel scriptItemModel) {
                ArrayList<Object> params=new ArrayList<>();
                if(null!=queryModel){
                    String where="";
                    if(!Utility.isNullOrEmpty(queryModel.getRy_xh())){
                        where+=" AND B.RY_XH = ?";
                        params.add(queryModel.getRy_xh());
                    }
                    if(!Utility.isNullOrEmpty(queryModel.getClzt())){
                        where+=" AND B.CLZT_DM = ?";
                        params.add(queryModel.getClzt());
                    }
                    scriptItemModel.setSqlWhere(where);
                }
                scriptItemModel.setParams(params.toArray());
            }
        };
        Collection<IBaseModel> collection= SelectBaseData(DataContext.getCurrentConnection(),TTxTztxjlModel.class,scriptItemModel,pagingOptions,queryModel);
        for (IBaseModel b:collection ) {
            TTxTztxjlDtoModel dto=new TTxTztxjlDtoModel();
            BeanUtils.copyProperties((TTxTztxjlModel)b,dto);
            resultList.add(dto);
        }
        return resultList;
    }

    public List<TXtQxXtmkDtoModel> selectT_XT_QX_XTMK(PagingOptions pagingOptions, BaseResultQueryModel queryModel,String sql){
        List<TXtQxXtmkDtoModel> resultList=new ArrayList<>();
        if(Utility.isNullOrEmpty(sql)){
            sql=WeChatFields.Script_SelectT_XT_QX_XTMK;
        }
        ScriptItemModel scriptItemModel= SystemSetting.JD_ServerCache.getSciptItemModel(sql);
        if(null==scriptItemModel){
            logger.info(sql+" 查询脚本没有找到");
            return null;
        }
        queryModel.dataParamsHandler=new DataParamsHandler(){
            @Override
            public void resolveParams(ScriptItemModel scriptItemModel) {
                ArrayList<Object> params=new ArrayList<>();
                if(null!=queryModel){
                    String where="";
                    if(!Utility.isNullOrEmpty(queryModel.getXtmk_dm())){
                        where+=" AND A.XTMK_DM = ?";
                        params.add(queryModel.getXtmk_dm());
                    }
                    if(!Utility.isNullOrEmpty(queryModel.getRy_xh())){
                        where+=" AND B.RY_XH = ?";
                        params.add(queryModel.getRy_xh());
                    }
                    scriptItemModel.setSqlWhere(where);
                }
                scriptItemModel.setParams(params.toArray());
            }
        };
        Collection<IBaseModel> collection= SelectBaseData(DataContext.getCurrentConnection(),TXtQxXtmkModel.class,scriptItemModel,pagingOptions,queryModel);
        for (IBaseModel b:collection ) {
            TXtQxXtmkDtoModel dto=new TXtQxXtmkDtoModel();
            BeanUtils.copyProperties((TXtQxXtmkModel)b,dto);
            resultList.add(dto);
        }
        return resultList;
    }

    public List<TXtQxBmDtoModel> selectT_XT_QX_BM(PagingOptions pagingOptions, TXtQxBmQueryModel queryModel,String sql){
        List<TXtQxBmDtoModel > resultList=new ArrayList<>();
        if(Utility.isNullOrEmpty(sql)){
            sql=WeChatFields.Script_SelectT_XT_QX_BM;
        }
        ScriptItemModel scriptItemModel= SystemSetting.JD_ServerCache.getSciptItemModel(sql);
        if(null==scriptItemModel){
            logger.info(sql+" 查询脚本没有找到");
            return null;
        }
        queryModel.dataParamsHandler=new DataParamsHandler(){
            @Override
            public void resolveParams(ScriptItemModel scriptItemModel) {
                ArrayList<Object> params=new ArrayList<>();
                if(null!=queryModel){
                    String where="";
                    if(!Utility.isNullOrEmpty(queryModel.getbm_dm())){
                        where+=" AND A.BM_DM = ?";
                        params.add(queryModel.getbm_dm());
                    }
                    scriptItemModel.setSqlWhere(where);
                }
                scriptItemModel.setParams(params.toArray());
            }
        };
        Collection<IBaseModel> collection= SelectBaseData(DataContext.getCurrentConnection(),TXtQxBmModel.class,scriptItemModel,pagingOptions,queryModel);
        for (IBaseModel b:collection ) {
            TXtQxBmDtoModel dto=new TXtQxBmDtoModel();
            BeanUtils.copyProperties((TXtQxBmModel)b,dto);
            resultList.add(dto);
        }
        return resultList;
    }

    public List<TXtQxJsDtoModel> selectT_XT_QX_JS(PagingOptions pagingOptions, TXtQxJsQueryModel queryModel, String sql){
        List<TXtQxJsDtoModel > resultList=new ArrayList<>();
        if(Utility.isNullOrEmpty(sql)){
            sql=WeChatFields.Script_SelectT_XT_QX_JS;
        }
        ScriptItemModel scriptItemModel= SystemSetting.JD_ServerCache.getSciptItemModel(sql);
        if(null==scriptItemModel){
            logger.info(sql+" 查询脚本没有找到");
            return null;
        }
        queryModel.dataParamsHandler=new DataParamsHandler(){
            @Override
            public void resolveParams(ScriptItemModel scriptItemModel) {
                ArrayList<Object> params=new ArrayList<>();
                if(null!=queryModel){
                    String where="";
                    if(!Utility.isNullOrEmpty(queryModel.getjs_dm())){
                        where+=" AND A.JS_DM = ?";
                        params.add(queryModel.getjs_dm());
                    }
                    scriptItemModel.setSqlWhere(where);
                }
                scriptItemModel.setParams(params.toArray());
            }
        };
        Collection<IBaseModel> collection= SelectBaseData(DataContext.getCurrentConnection(),TXtQxJsModel.class,scriptItemModel,pagingOptions,queryModel);
        for (IBaseModel b:collection ) {
            TXtQxJsDtoModel dto=new TXtQxJsDtoModel();
            BeanUtils.copyProperties((TXtQxJsModel)b,dto);
            resultList.add(dto);
        }
        return resultList;
    }

    //endregion

    //region 许可证
    public List<TYwXkzDtoModel> selectT_YW_XKZ(PagingOptions pagingOptions, TYwXkzQueryModel queryModel, String sql){
        List<TYwXkzDtoModel > resultList=new ArrayList<>();
        if(Utility.isNullOrEmpty(sql)){
            sql=WeChatFields.Script_SelectT_YW_XKZ;
        }
        ScriptItemModel scriptItemModel= SystemSetting.JD_ServerCache.getSciptItemModel(sql);
        if(null==scriptItemModel){
            logger.info(sql+" 查询脚本没有找到");
            return null;
        }
        queryModel.dataParamsHandler=new DataParamsHandler(){
            @Override
            public void resolveParams(ScriptItemModel scriptItemModel) {
                ArrayList<Object> params=new ArrayList<>();
                if(null!=queryModel){
                    String where="";
                    if(!Utility.isNullOrEmpty(queryModel.getlx_dm())){
                        where+=" AND A.LX_DM = ?";
                        params.add(queryModel.getlx_dm());
                    }
                    if(!Utility.isNullOrEmpty(queryModel.getjgdw_mc())){
                        where+=" AND A.JGDW_MC like ?";
                        params.add("%"+queryModel.getjgdw_mc()+"%");
                    }
                    if(!Utility.isNullOrEmpty(queryModel.getxkz_bh())){
                        where+=" AND A.XKZ_BH LIKE ?";
                        params.add("%"+queryModel.getxkz_bh()+"%");
                    }
                    scriptItemModel.setSqlWhere(where);
                }
                scriptItemModel.setParams(params.toArray());
            }
        };
        Collection<IBaseModel> collection= SelectBaseData(DataContext.getCurrentConnection(),TYwXkzModel.class,scriptItemModel,pagingOptions,queryModel);
        for (IBaseModel b:collection ) {
            TYwXkzDtoModel dto=new TYwXkzDtoModel();
            BeanUtils.copyProperties((TYwXkzModel)b,dto);
            resultList.add(dto);
        }
        return resultList;
    }
    //endregion

    //region  纳税人
    public List<TYwNsrDtoModel> selectT_YW_NSR(PagingOptions pagingOptions, TYwNsrQueryModel queryModel, String sql){
        List<TYwNsrDtoModel> resultList=new ArrayList<>();
        if(Utility.isNullOrEmpty(sql)){
            sql=WeChatFields.Script_SelectT_YW_NSR;
        }
        ScriptItemModel scriptItemModel= SystemSetting.JD_ServerCache.getSciptItemModel(sql);
        if(null==scriptItemModel){
            logger.info(sql+" 查询脚本没有找到");
            return null;
        }
        queryModel.dataParamsHandler=new DataParamsHandler(){
            @Override
            public void resolveParams(ScriptItemModel scriptItemModel) {
                ArrayList<Object> params=new ArrayList<>();
                if(null!=queryModel){
                    String where="";
                    if(!Utility.isNullOrEmpty(queryModel.gettyshxydm())){
                        where+=" AND A.TYSHXYDM = ?";
                        params.add(queryModel.gettyshxydm());
                    }
                    if(!Utility.isNullOrEmpty(queryModel.getnsr_xh())){
                        where+=" AND A.NSR_XH = ?";
                        params.add(queryModel.getnsr_xh());
                    }
                    if(!Utility.isNullOrEmpty(queryModel.getnsr_mc())){
                        where+=" AND A.NSR_MC like ?";
                        params.add("%"+queryModel.getnsr_mc()+"%");
                    }
                    scriptItemModel.setSqlWhere(where);
                }
                scriptItemModel.setParams(params.toArray());
            }
        };
        Collection<IBaseModel> collection= SelectBaseData(DataContext.getCurrentConnection(),TYwNsrModel.class,scriptItemModel,pagingOptions,queryModel);
        for (IBaseModel b:collection ) {
            TYwNsrDtoModel dto=new TYwNsrDtoModel();
            BeanUtils.copyProperties((TYwNsrModel)b,dto);
            resultList.add(dto);
        }
        return resultList;
    }
    //endregion

    //region  企业列表
    public List<TDataEnterpriseDtoModel> selectT_DATA_ENTERPRISE(PagingOptions pagingOptions, TDataEnterpriseQueryModel queryModel, String sql){
        List<TDataEnterpriseDtoModel> resultList=new ArrayList<>();
        if(Utility.isNullOrEmpty(sql)){
            sql=WeChatFields.Script_SelectT_DATA_ENTERPRISE;
        }
        ScriptItemModel scriptItemModel= SystemSetting.JD_ServerCache.getSciptItemModel(sql);
        if(null==scriptItemModel){
            logger.info(sql+" 查询脚本没有找到");
            return null;
        }
        queryModel.dataParamsHandler=new DataParamsHandler(){
            @Override
            public void resolveParams(ScriptItemModel scriptItemModel) {
                ArrayList<Object> params=new ArrayList<>();
                if(null!=queryModel){
                    String where="";
                    if(!Utility.isNullOrEmpty(queryModel.getdescription())){
                        where+=" AND A.DESCRIPTION = ?";
                        params.add(queryModel.getdescription());
                    }
                    if(!Utility.isNullOrEmpty(queryModel.getenterprise_id())){
                        where+=" AND A.ENTERPRISE_ID = ?";
                        params.add(queryModel.getenterprise_id());
                    }
                    if(!Utility.isNullOrEmpty(queryModel.getname())){
                        where+=" AND A.NAME like ?";
                        params.add("%"+queryModel.getname()+"%");
                    }
                    scriptItemModel.setSqlWhere(where);
                }
                scriptItemModel.setParams(params.toArray());
            }
        };
        Collection<IBaseModel> collection= SelectBaseData(DataContext.getCurrentConnection(),TDataEnterpriseModel.class,scriptItemModel,pagingOptions,queryModel);
        for (IBaseModel b:collection ) {
            TDataEnterpriseDtoModel dto=new TDataEnterpriseDtoModel();
            BeanUtils.copyProperties((TDataEnterpriseModel)b,dto);
            resultList.add(dto);
        }
        return resultList;
    }
    //endregion

}
