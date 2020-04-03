package com.joindoo.jdwechat.data;

import com.joindoo.jdwechat.Utility;
import com.joindoo.jdwechat.beans.IBaseModel;
import com.joindoo.jdwechat.entity.config.DruidConfig;
import com.joindoo.jdwechat.model.query.BaseQueryModel;
import com.joindoo.jdwechat.model.query.PagingOptions;
import com.joindoo.jdwechat.model.sys.ScriptItemModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Collection;

/**
 * Created by zhuqiang on 2018/5/17.
 */
public class EdenDataService {
    protected final Logger logger = LoggerFactory.getLogger(EdenDataService.class);
    private ThreadLocal<DataContext> ThreadLocal_DataContext = new ThreadLocal();

    public EdenDataService(){
    }
    public DataContext getDataContext(DruidConfig druidConfig) {
        DataContext dataContext =this.ThreadLocal_DataContext.get();
        if(dataContext == null) {
            dataContext = new DataContext(druidConfig.druidDataSource());
            dataContext.initialize();
            this.ThreadLocal_DataContext.set(dataContext);
        }
        return dataContext;
    }
    public void disposeInCurrentThread() {
        try {
            DataContext dataContext = this.ThreadLocal_DataContext.get();
            if(dataContext != null) {
                dataContext.release();
            }

            this.ThreadLocal_DataContext.remove();
        } catch (Exception var2) {
            logger.error(var2.getMessage());
        }
    }

    /**
     * 生成的bean model
     * @param connection
     * @param modelType
     * @param scriptItemModel
     * @param pagingOptions
     * @param queryModel
     * @return
     */
    public static Collection<IBaseModel> SelectBaseData(Connection connection, Class modelType, ScriptItemModel scriptItemModel, PagingOptions pagingOptions, BaseQueryModel queryModel){
        queryModel.fillParams(scriptItemModel);
        String sql=scriptItemModel.getScriptContent();
        if(!Utility.isNullOrEmpty(scriptItemModel.getSqlWhere())){
            sql=sql.replace("(1=1)","(1=1) "+scriptItemModel.getSqlWhere());
        }
        if(pagingOptions.isNeedTotal()){
            int total= DBHelper.getCount(connection,sql,scriptItemModel.getParams());
            pagingOptions.setTotal(total);
        }
        if(!Utility.isNullOrEmpty(scriptItemModel.getOrderCondition().trim())){
            sql+=    " order by "+scriptItemModel.getOrderCondition();
        }
        if(pagingOptions.getStart()>=0&&pagingOptions.getPageSize()>0){
            sql+=" limit "+pagingOptions.getStart()+","+pagingOptions.getPageSize();
        }

        return DBHelper.executeQuery(connection,modelType,sql,scriptItemModel.getParams());
    }

    /**
     * 自定义的接受model
     * @param connection
     * @param modelType
     * @param scriptItemModel
     * @param pagingOptions
     * @param queryModel
     * @return
     */
    public static Collection<com.joindoo.jdwechat.model.IBaseModel> SelectBaseData2(Connection connection, Class modelType, ScriptItemModel scriptItemModel, PagingOptions pagingOptions, BaseQueryModel queryModel){
        queryModel.fillParams(scriptItemModel);
        String sql=scriptItemModel.getScriptContent();
        if(!Utility.isNullOrEmpty(scriptItemModel.getSqlWhere())){
            sql=sql.replace("(1=1)","(1=1) "+scriptItemModel.getSqlWhere());
        }
        if(!Utility.isNullOrEmpty(scriptItemModel.getOrderCondition().trim())){
            sql+=    " order by "+scriptItemModel.getOrderCondition();
        }
        if(pagingOptions.getStart()>=0&&pagingOptions.getPageSize()>0){
            sql+=" limit "+pagingOptions.getStart()+","+pagingOptions.getPageSize();
        }
        return DBHelper.executeQuery2(connection,modelType,sql,scriptItemModel.getParams());
    }
}
