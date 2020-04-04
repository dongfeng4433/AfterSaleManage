package com.joindoo.jdwechat.beans;

import com.joindoo.jdwechat.Utility;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;
import java.math.BigDecimal;
/**
* 系统 - 公用-社区
* Author: zhuqiang4433@gmail.com
* Version: CodeGenerator 1.1
* Memo: Auto Created by CodeGenerator on 2020/4/4.
*/

public class TXtGySqModel extends BaseModel implements IBaseModel{
	 public static final String TABLE_NAME="T_XT_GY_SQ";
	 public static final String SCRIPT_INSERT="INSERT INTO t_xt_gy_sq(community_id,street_id,street_name,community_name,create_time,is_valid)VALUES(?,?,?,?,?,?)";
	 public static final String SCRIPT_DELETE="DELETE FROM t_xt_gy_sq WHERE  community_id=? ";
	 public static final String SCRIPT_UPDATE="UPDATE t_xt_gy_sq  SET {0} WHERE  community_id=? ";
	 public static final String SCRIPT_SELECT="SELECT community_id,street_id,street_name,community_name,create_time,is_valid FROM t_xt_gy_sq WHERE (1=1)";
	 public String getDataTableName() {
 	 return TABLE_NAME;
	 }


	/*序号*/ 
	protected String community_id;
	public String getcommunity_id (){ return community_id;}
	public void setcommunity_id(String value){
		this.community_id=value;
 		this.onChangeProperty("community_id",this.community_id,value);
 	}

	/*街道id*/ 
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

	/*社区名称*/ 
	protected String community_name;
	public String getcommunity_name (){ return community_name;}
	public void setcommunity_name(String value){
		this.community_name=value;
 		this.onChangeProperty("community_name",this.community_name,value);
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



	public TXtGySqModel(){
		super();
		this._keyProperty.add("community_id");
		initFieldsType();
	}

	private void initFieldsType(){
		this._fieldType.put("community_id","String");
		this._fieldType.put("street_id","String");
		this._fieldType.put("street_name","String");
		this._fieldType.put("community_name","String");
		this._fieldType.put("create_time","Date");
		this._fieldType.put("is_valid","Integer");
	}
	@Override
	public void initAsInsert() {

	}
	@Override
	public void fillModel(ResultSet resultSet) {
		try { 
		 if(isExistColumn(resultSet,"community_id")){
			 this.community_id=resultSet.getString("community_id");
		}
		 if(isExistColumn(resultSet,"street_id")){
			 this.street_id=resultSet.getString("street_id");
		}
		 if(isExistColumn(resultSet,"street_name")){
			 this.street_name=resultSet.getString("street_name");
		}
		 if(isExistColumn(resultSet,"community_name")){
			 this.community_name=resultSet.getString("community_name");
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
		    sql+=" where   community_id="+Utility.getSqlFielStatement("string",this.community_id)+"  ";
	    return sql;
   }


   @Override
   public String getInsert() {
       String sql="insert into "+TABLE_NAME+"(community_id,street_id,street_name,community_name,create_time,is_valid) values("+Utility.getSqlFielStatement("string",this.community_id)+","+Utility.getSqlFielStatement("string",this.street_id)+","+Utility.getSqlFielStatement("string",this.street_name)+","+Utility.getSqlFielStatement("string",this.community_name)+","+Utility.getSqlFielStatement("date",this.create_time)+","+this.is_valid+")";
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
   sql+= " where  community_id="+Utility.getSqlFielStatement("string",this.community_id)+" ";
   }
   return sql;
}


	@Override
	public String getSelect() {
		String sql="select community_id,street_id,street_name,community_name,create_time,is_valid from "+TABLE_NAME;
		if(!Utility.isNullOrEmpty(community_id)){
			sql+=" where  community_id="+Utility.getSqlFielStatement("string",this.community_id)+" ";
		}
		return sql;
	}
	@Override
	public String getSelectByCondition(String condition) {
		String sql="select community_id,street_id,street_name,community_name,create_time,is_valid from "+TABLE_NAME;
		if(condition!=null&&condition!=""){
			sql+=" where "+condition;
		}
		return sql;
	}


    @Override
   public String getDelete() {
	 String sql="delete from "+TABLE_NAME+" where  community_id="+Utility.getSqlFielStatement("string",this.community_id)+" ";
	 return sql;
   }


}

