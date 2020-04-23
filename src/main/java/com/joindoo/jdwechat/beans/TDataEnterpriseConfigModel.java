package com.joindoo.jdwechat.beans;

import com.joindoo.jdwechat.Utility;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;
import java.math.BigDecimal;
/**
* 主数据 - 企业 - 配置  企业的相关配置，比如钉钉相关的id和key
* Author: zhuqiang4433@gmail.com
* Version: CodeGenerator 1.1
* Memo: Auto Created by CodeGenerator on 2020/4/20.
*/

public class TDataEnterpriseConfigModel extends BaseModel implements IBaseModel{
	 public static final String TABLE_NAME="T_DATA_ENTERPRISE_CONFIG";
	 public static final String SCRIPT_INSERT="INSERT INTO t_data_enterprise_config(enterprise_id,config_code,config_name,config_value,description,is_valid,create_time,last_edit_time,creation_user_id,last_edit_user_id)VALUES(?,?,?,?,?,?,?,?,?,?)";
	 public static final String SCRIPT_DELETE="DELETE FROM t_data_enterprise_config WHERE  enterprise_id=? AND config_code=? ";
	 public static final String SCRIPT_UPDATE="UPDATE t_data_enterprise_config  SET {0} WHERE  enterprise_id=? AND config_code=? ";
	 public static final String SCRIPT_SELECT="SELECT enterprise_id,config_code,config_name,config_value,description,is_valid,create_time,last_edit_time,creation_user_id,last_edit_user_id FROM t_data_enterprise_config WHERE (1=1)";
	 public String getDataTableName() {
 	 return TABLE_NAME;
	 }


	/*企业id*/ 
	protected String enterprise_id;
	public String getenterprise_id (){ return enterprise_id;}
	public void setenterprise_id(String value){
		this.enterprise_id=value;
 		this.onChangeProperty("enterprise_id",this.enterprise_id,value);
 	}

	/*代码*/ 
	protected String config_code;
	public String getconfig_code (){ return config_code;}
	public void setconfig_code(String value){
		this.config_code=value;
 		this.onChangeProperty("config_code",this.config_code,value);
 	}

	/*名称*/ 
	protected String config_name;
	public String getconfig_name (){ return config_name;}
	public void setconfig_name(String value){
		this.config_name=value;
 		this.onChangeProperty("config_name",this.config_name,value);
 	}

	/*配置项值*/ 
	protected String config_value;
	public String getconfig_value (){ return config_value;}
	public void setconfig_value(String value){
		this.config_value=value;
 		this.onChangeProperty("config_value",this.config_value,value);
 	}

	/*企业介绍*/ 
	protected String description;
	public String getdescription (){ return description;}
	public void setdescription(String value){
		this.description=value;
 		this.onChangeProperty("description",this.description,value);
 	}

	/*是否有效*/ 
	protected Integer is_valid;
	public Integer getis_valid (){ return is_valid;}
	public void setis_valid(Integer value){
		this.is_valid=value;
 		this.onChangeProperty("is_valid",this.is_valid,value);
 	}

	/*创建时间*/ 
	protected Date create_time;
	public Date getcreate_time (){ return create_time;}
	public void setcreate_time(Date value){
		this.create_time=value;
 		this.onChangeProperty("create_time",this.create_time,value);
 	}

	/*最后修改时间*/ 
	protected Date last_edit_time;
	public Date getlast_edit_time (){ return last_edit_time;}
	public void setlast_edit_time(Date value){
		this.last_edit_time=value;
 		this.onChangeProperty("last_edit_time",this.last_edit_time,value);
 	}

	/*创建人*/ 
	protected String creation_user_id;
	public String getcreation_user_id (){ return creation_user_id;}
	public void setcreation_user_id(String value){
		this.creation_user_id=value;
 		this.onChangeProperty("creation_user_id",this.creation_user_id,value);
 	}

	/*最后修改人*/ 
	protected String last_edit_user_id;
	public String getlast_edit_user_id (){ return last_edit_user_id;}
	public void setlast_edit_user_id(String value){
		this.last_edit_user_id=value;
 		this.onChangeProperty("last_edit_user_id",this.last_edit_user_id,value);
 	}



	public TDataEnterpriseConfigModel(){
		super();
		this._keyProperty.add("enterprise_id");
		this._keyProperty.add("config_code");
		initFieldsType();
	}

	private void initFieldsType(){
		this._fieldType.put("enterprise_id","String");
		this._fieldType.put("config_code","String");
		this._fieldType.put("config_name","String");
		this._fieldType.put("config_value","String");
		this._fieldType.put("description","String");
		this._fieldType.put("is_valid","Integer");
		this._fieldType.put("create_time","Date");
		this._fieldType.put("last_edit_time","Date");
		this._fieldType.put("creation_user_id","String");
		this._fieldType.put("last_edit_user_id","String");
	}
	@Override
	public void initAsInsert() {

	}
	@Override
	public void fillModel(ResultSet resultSet) {
		try { 
		 if(isExistColumn(resultSet,"enterprise_id")){
			 this.enterprise_id=resultSet.getString("enterprise_id");
		}
		 if(isExistColumn(resultSet,"config_code")){
			 this.config_code=resultSet.getString("config_code");
		}
		 if(isExistColumn(resultSet,"config_name")){
			 this.config_name=resultSet.getString("config_name");
		}
		 if(isExistColumn(resultSet,"config_value")){
			 this.config_value=resultSet.getString("config_value");
		}
		 if(isExistColumn(resultSet,"description")){
			 this.description=resultSet.getString("description");
		}
		 if(isExistColumn(resultSet,"is_valid")){
			 this.is_valid=resultSet.getInt("is_valid");
		}
		 if(isExistColumn(resultSet,"create_time")){
			 this.create_time=(Date)resultSet.getObject("create_time");
		}
		 if(isExistColumn(resultSet,"last_edit_time")){
			 this.last_edit_time=(Date)resultSet.getObject("last_edit_time");
		}
		 if(isExistColumn(resultSet,"creation_user_id")){
			 this.creation_user_id=resultSet.getString("creation_user_id");
		}
		 if(isExistColumn(resultSet,"last_edit_user_id")){
			 this.last_edit_user_id=resultSet.getString("last_edit_user_id");
		}
      } catch (SQLException e) {
              e.printStackTrace();
          }
      }
  @Override
   public String getExist() {
	    String sql="select 1 from "+TABLE_NAME;
		    sql+=" where   enterprise_id="+Utility.getSqlFielStatement("string",this.enterprise_id)+" and config_code="+Utility.getSqlFielStatement("string",this.config_code)+"  ";
	    return sql;
   }


   @Override
   public String getInsert() {
       String sql="insert into "+TABLE_NAME+"(enterprise_id,config_code,config_name,config_value,description,is_valid,create_time,last_edit_time,creation_user_id,last_edit_user_id) values("+Utility.getSqlFielStatement("string",this.enterprise_id)+","+Utility.getSqlFielStatement("string",this.config_code)+","+Utility.getSqlFielStatement("string",this.config_name)+","+Utility.getSqlFielStatement("string",this.config_value)+","+Utility.getSqlFielStatement("string",this.description)+","+this.is_valid+","+Utility.getSqlFielStatement("date",this.create_time)+","+Utility.getSqlFielStatement("date",this.last_edit_time)+","+Utility.getSqlFielStatement("string",this.creation_user_id)+","+Utility.getSqlFielStatement("string",this.last_edit_user_id)+")";
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
   sql+= " where  enterprise_id="+Utility.getSqlFielStatement("string",this.enterprise_id)+" and config_code="+Utility.getSqlFielStatement("string",this.config_code)+" ";
   }
   return sql;
}


	@Override
	public String getSelect() {
		String sql="select enterprise_id,config_code,config_name,config_value,description,is_valid,create_time,last_edit_time,creation_user_id,last_edit_user_id from "+TABLE_NAME;
		if(!Utility.isNullOrEmpty(enterprise_id)&&!Utility.isNullOrEmpty(config_code)){
			sql+=" where  enterprise_id="+Utility.getSqlFielStatement("string",this.enterprise_id)+" and config_code="+Utility.getSqlFielStatement("string",this.config_code)+" ";
		}
		return sql;
	}
	@Override
	public String getSelectByCondition(String condition) {
		String sql="select enterprise_id,config_code,config_name,config_value,description,is_valid,create_time,last_edit_time,creation_user_id,last_edit_user_id from "+TABLE_NAME;
		if(condition!=null&&condition!=""){
			sql+=" where "+condition;
		}
		return sql;
	}


    @Override
   public String getDelete() {
	 String sql="delete from "+TABLE_NAME+" where  enterprise_id="+Utility.getSqlFielStatement("string",this.enterprise_id)+" and config_code="+Utility.getSqlFielStatement("string",this.config_code)+" ";
	 return sql;
   }


}

