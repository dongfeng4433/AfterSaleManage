package com.joindoo.jdwechat.beans;


import com.joindoo.jdwechat.Utility;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by zhuqiang1 on 2016/5/27.
 */
public class BaseModel {
    protected boolean _isNeedUpdate;
    protected Map<String,Object> _changeProperty;
    protected Map<String,Object> _fieldType;
    protected HashSet<String> _keyProperty;
    BaseModel(){
        _isNeedUpdate=false;
        _changeProperty=new HashMap<String, Object>();
        _keyProperty=new HashSet<String>();
        _fieldType=new HashMap<>();
    }

    protected void onChangeProperty(String key, Object oldValue, Object newValue){
        if(_changeProperty!=null&&!_changeProperty.containsKey(key)){
            _changeProperty.put(key, newValue);
        }
    }
    protected String getSqlFieldStatement(String field, String fieldType){
        String result="";
        if(_changeProperty.containsKey(field)){
            Object o=_changeProperty.get(field);
            if(fieldType==null){
                if(_changeProperty.get(field)!=null){
                    fieldType=_changeProperty.get(field).getClass().getName();
                }
            }
            if(fieldType==String.class.getName()){
                String strTemp=o.toString();
                if(strTemp.indexOf("'")>=0){
                    strTemp=strTemp.replaceAll("'","\\'");
                }
                result="='"+strTemp+"'";
            }else if(fieldType==Integer.class.getName()){
                result="="+o.toString();
            }else if(fieldType== Date.class.getName()){
                result="='"+ Utility.formatDateTime((Date)o,false) +"'";
            }else if(o==null){
                result="";
            }else {
                result="='"+o.toString()+"'";
            }
        }
        return result;
    }
    public void doUpdate(){
        _isNeedUpdate=true;
        _changeProperty.clear();
    }
    public Map<String,Object> getChanges(){
          return _changeProperty;
    }

    /**
     * 判断查询结果集中是否存在某列
     * @param rs 查询结果集
     * @param columnName 列名
     * @return true 存在; false 不存咋
     */
    public boolean isExistColumn(ResultSet rs, String columnName) {
        try {
            if (rs.findColumn(columnName) > 0 ) {
                return true;
            }
        }
        catch (SQLException e) {
            return false;
        }

        return false;
    }
}
