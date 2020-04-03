package com.joindoo.jdwechat.daos;


import com.joindoo.jdwechat.beans.BaseModel;
import com.joindoo.jdwechat.data.DBHelper;
import com.joindoo.jdwechat.data.DataContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuqiang on 2018/4/4.
 */
public class BaseDao implements IBaseDao {
    protected DataContext _dataContext=null;
    public BaseDao(){}
    public BaseDao(DataContext dataContext){
        _dataContext=dataContext;
    }

    @Override
    public boolean exist(BaseModel model) {
        return false;
    }

    @Override
    public void insert(BaseModel model) {

    }

    @Override
    public void delete(BaseModel model) {

    }

    @Override
    public void update(BaseModel model) {

    }

    @Override
    public BaseModel find(BaseModel model) {
        return null;
    }

    @Override
    public List<BaseModel> findAll(String condition) {
        return null;
    }

    @Override
    public List<BaseModel> queryAll(String sql,Object... objects) {
        List<BaseModel> resultList=new ArrayList<>();
        ResultSet set=DBHelper.executeQuery(DataContext.getCurrentConnection(),sql,objects);
        getData2ModelList(set,resultList);
        DBHelper.free(set);
        return resultList;
    }

    public String getCountSql(String condition){
        String sql="";
        return sql;
    }
    public int findAllCount(String condition,Object... objects){
        String sql=getCountSql(condition);//"select count(*) as count from "+ TIdcardInfoModel.TABLE_NAME;
        if(condition!=null&&condition!=""){
            sql+=" where "+condition;
        }
        int count=0;
        ResultSet resultSet= DBHelper.executeQuery(DataContext.getCurrentConnection(), sql,objects);
        try {
            while(resultSet.next()){
                count=resultSet.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBHelper.free(resultSet);
        }
        return count;
    }

    public void getData2ModelList(ResultSet set, List<BaseModel> resultList){

    }
}
