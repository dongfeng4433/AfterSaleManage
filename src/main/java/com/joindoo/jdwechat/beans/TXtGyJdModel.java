package com.joindoo.jdwechat.beans;

import com.joindoo.jdwechat.Utility;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;
import java.math.BigDecimal;
/**
* 系统 - 公用-街道
* Author: zhuqiang4433@gmail.com
* Version: CodeGenerator 1.1
* Memo: Auto Created by CodeGenerator on 2020/4/4.
*/

public class TXtGyJdModel extends BaseModel implements IBaseModel{
	 public static final String TABLE_NAME="T_XT_GY_JD";
	 public static final String SCRIPT_INSERT="INSERT INTO t_xt_gy_jd(street_id,street_name,create_time,is_valid)VALUES(?,?,?,?)";
	 public static final String SCRIPT_DELETE="DELETE FROM t_xt_gy_jd WHERE  street_id=? ";
	 public static final String SCRIPT_UPDATE="UPDATE t_xt_gy_jd  SET {0} WHERE  street_id=? ";
	 public static final String SCRIPT_SELECT="SELECT street_id,street_name,create_time,is_valid FROM t_xt_gy_jd WHERE (1=1)";
	 public String getDataTableName() {
 	 return TABLE_NAME;
	 }


	/*序号*/ 
	protected String street_id;
	public String getstreet_id (){ return street_id;}
	public void setstreet_id(String value){
		this.street_id=value;
 		this.onChangeProperty("street_id",this.street_id,value);
 	}

	/*街道名称*/ 
	protected String street_name;
	public String getstreet_name (){ return street_name;}
	public void setstreet_name(String value){
		this.street_name=value;
 		this.onChangeProperty("street_name",this.street_name,value);
 	}

	/*创建时间*/ 
	protected Date create_time;
	public Date getcreate_time (){ return create_time;}
	public void setcreate_time(Date value){
		this.create_time=value;
 		this.onChangeProperty("create_time",this.create_time,value);
 	}

	/*是否有效*/ 
	protected Integer is_valid;
	public Integer getis_valid (){ return is_valid;}
	public void setis_valid(Integer value){
		this.is_valid=value;
 		this.onChangeProperty("is_valid",this.is_valid,value);
 	}



	public TXtGyJdModel(){
		super();
		this._keyProperty.add("street_id");
		initFieldsType();
	}

	private void initFieldsType(){
		this._fieldType.put("street_id","String");
		this._fieldType.put("street_name","String");
		this._fieldType.put("create_time","Date");
		this._fieldType.put("is_valid","Integer");
	}
	@Override
	public void initAsInsert() {

	}
	@Override
	public void fillModel(ResultSet resultSet) {
		try { 
		 if(isExistColumn(resultSet,"street_id")){
			 this.street_id=resultSet.getString("street_id");
		}
		 if(isExistColumn(resultSet,"street_name")){
			 this.street_name=resultSet.getString("street_name");
		}
		 if(isExistColumn(resultSet,"create_time")){
			 this.create_time=(Date)resultSet.getObject("create_time");
		}
		 if(isExistColumn(resultSet,"is_valid")){
			 this.is_valid=resultSet.getInt("is_valid");
		}
      } catch (SQLException e) {
              e.printStackTrace();
          }
      }
  @Override
   public String getExist() {
	    String sql="select 1 from "+TABLE_NAME;
		    sql+=" where   street_id="+Utility.getSqlFielStatement("string",this.street_id)+"  ";
	    return sql;
   }


   @Override
   public String getInsert() {
       String sql="insert into "+TABLE_NAME+"(street_id,street_name,create_time,is_valid) values("+Utility.getSqlFielStatement("string",this.street_id)+","+Utility.getSqlFielStatement("string",this.street_name)+","+Utility.getSqlFielStatement("date",this.create_time)+","+this.is_valid+")";
       return sql;
   }


   @Override
   public String getUpdate() {
   String sql="";
   if(this._isNeedUpdate&&this._changeProperty.size()>0){
   sql="update "+TABLE_NAME+" set ";
   for (String s:this._changeProperty.keySet()){
       if(this._keyProperty.contains(s))continue;
        String str=this.getSqlFieldStatement(s,null);
         if(str!=""){
               sql+=s+str+",";
         }
   }
   if(sql.endsWith(","))
   sql=sql.substring(0,sql.length()-1);
   else
       return "";
   sql+= " where  street_id="+Utility.getSqlFielStatement("string",this.street_id)+" ";
   }
   return sql;
}


	@Override
	public String getSelect() {
		String sql="select street_id,street_name,create_time,is_valid from "+TABLE_NAME;
		if(!Utility.isNullOrEmpty(street_id)){
			sql+=" where  street_id="+Utility.getSqlFielStatement("string",this.street_id)+" ";
		}
		return sql;
	}
	@Override
	public String getSelectByCondition(String condition) {
		String sql="select street_id,street_name,create_time,is_valid from "+TABLE_NAME;
		if(condition!=null&&condition!=""){
			sql+=" where "+condition;
		}
		return sql;
	}


    @Override
   public String getDelete() {
	 String sql="delete from "+TABLE_NAME+" where  street_id="+Utility.getSqlFielStatement("string",this.street_id)+" ";
	 return sql;
   }


}

