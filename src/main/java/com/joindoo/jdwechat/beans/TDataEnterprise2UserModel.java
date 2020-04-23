package com.joindoo.jdwechat.beans;

import com.joindoo.jdwechat.Utility;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;
import java.math.BigDecimal;
/**
* 主数据 - 企业 - 2 - 人员 企业人员关联表，一个人员可以是多个企业，一个企业也有多个人员
* Author: zhuqiang4433@gmail.com
* Version: CodeGenerator 1.1
* Memo: Auto Created by CodeGenerator on 2020/4/20.
*/

public class TDataEnterprise2UserModel extends BaseModel implements IBaseModel{
	 public static final String TABLE_NAME="T_DATA_ENTERPRISE_2_USER";
	 public static final String SCRIPT_INSERT="INSERT INTO t_data_enterprise_2_user(enterprise_id,user_id,is_valid,user_type_code,create_time,last_edit_time,creation_user_id,last_edit_user_id,current_order_id,current_status)VALUES(?,?,?,?,?,?,?,?,?,?)";
	 public static final String SCRIPT_DELETE="DELETE FROM t_data_enterprise_2_user WHERE  enterprise_id=? AND user_id=? ";
	 public static final String SCRIPT_UPDATE="UPDATE t_data_enterprise_2_user  SET {0} WHERE  enterprise_id=? AND user_id=? ";
	 public static final String SCRIPT_SELECT="SELECT enterprise_id,user_id,is_valid,user_type_code,create_time,last_edit_time,creation_user_id,last_edit_user_id,current_order_id,current_status FROM t_data_enterprise_2_user WHERE (1=1)";
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

	/*人员id*/ 
	protected String user_id;
	public String getuser_id (){ return user_id;}
	public void setuser_id(String value){
		this.user_id=value;
 		this.onChangeProperty("user_id",this.user_id,value);
 	}

	/*是否有效*/ 
	protected Integer is_valid;
	public Integer getis_valid (){ return is_valid;}
	public void setis_valid(Integer value){
		this.is_valid=value;
 		this.onChangeProperty("is_valid",this.is_valid,value);
 	}

	/*用户人员类型（0 企业管理员 1 企业员工）*/ 
	protected String user_type_code;
	public String getuser_type_code (){ return user_type_code;}
	public void setuser_type_code(String value){
		this.user_type_code=value;
 		this.onChangeProperty("user_type_code",this.user_type_code,value);
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

	/*当前进行订单id*/ 
	protected String current_order_id;
	public String getcurrent_order_id (){ return current_order_id;}
	public void setcurrent_order_id(String value){
		this.current_order_id=value;
 		this.onChangeProperty("current_order_id",this.current_order_id,value);
 	}

	/*当前人员状态 1工作中 2 空闲 */ 
	protected String current_status;
	public String getcurrent_status (){ return current_status;}
	public void setcurrent_status(String value){
		this.current_status=value;
 		this.onChangeProperty("current_status",this.current_status,value);
 	}



	public TDataEnterprise2UserModel(){
		super();
		this._keyProperty.add("enterprise_id");
		this._keyProperty.add("user_id");
		initFieldsType();
	}

	private void initFieldsType(){
		this._fieldType.put("enterprise_id","String");
		this._fieldType.put("user_id","String");
		this._fieldType.put("is_valid","Integer");
		this._fieldType.put("user_type_code","String");
		this._fieldType.put("create_time","Date");
		this._fieldType.put("last_edit_time","Date");
		this._fieldType.put("creation_user_id","String");
		this._fieldType.put("last_edit_user_id","String");
		this._fieldType.put("current_order_id","String");
		this._fieldType.put("current_status","String");
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
		 if(isExistColumn(resultSet,"user_id")){
			 this.user_id=resultSet.getString("user_id");
		}
		 if(isExistColumn(resultSet,"is_valid")){
			 this.is_valid=resultSet.getInt("is_valid");
		}
		 if(isExistColumn(resultSet,"user_type_code")){
			 this.user_type_code=resultSet.getString("user_type_code");
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
		 if(isExistColumn(resultSet,"current_order_id")){
			 this.current_order_id=resultSet.getString("current_order_id");
		}
		 if(isExistColumn(resultSet,"current_status")){
			 this.current_status=resultSet.getString("current_status");
		}
      } catch (SQLException e) {
              e.printStackTrace();
          }
      }
  @Override
   public String getExist() {
	    String sql="select 1 from "+TABLE_NAME;
		    sql+=" where   enterprise_id="+Utility.getSqlFielStatement("string",this.enterprise_id)+" and user_id="+Utility.getSqlFielStatement("string",this.user_id)+"  ";
	    return sql;
   }


   @Override
   public String getInsert() {
       String sql="insert into "+TABLE_NAME+"(enterprise_id,user_id,is_valid,user_type_code,create_time,last_edit_time,creation_user_id,last_edit_user_id,current_order_id,current_status) values("+Utility.getSqlFielStatement("string",this.enterprise_id)+","+Utility.getSqlFielStatement("string",this.user_id)+","+this.is_valid+","+Utility.getSqlFielStatement("string",this.user_type_code)+","+Utility.getSqlFielStatement("date",this.create_time)+","+Utility.getSqlFielStatement("date",this.last_edit_time)+","+Utility.getSqlFielStatement("string",this.creation_user_id)+","+Utility.getSqlFielStatement("string",this.last_edit_user_id)+","+Utility.getSqlFielStatement("string",this.current_order_id)+","+Utility.getSqlFielStatement("string",this.current_status)+")";
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
   sql+= " where  enterprise_id="+Utility.getSqlFielStatement("string",this.enterprise_id)+" and user_id="+Utility.getSqlFielStatement("string",this.user_id)+" ";
   }
   return sql;
}


	@Override
	public String getSelect() {
		String sql="select enterprise_id,user_id,is_valid,user_type_code,create_time,last_edit_time,creation_user_id,last_edit_user_id,current_order_id,current_status from "+TABLE_NAME;
		if(!Utility.isNullOrEmpty(enterprise_id)&&!Utility.isNullOrEmpty(user_id)){
			sql+=" where  enterprise_id="+Utility.getSqlFielStatement("string",this.enterprise_id)+" and user_id="+Utility.getSqlFielStatement("string",this.user_id)+" ";
		}
		return sql;
	}
	@Override
	public String getSelectByCondition(String condition) {
		String sql="select enterprise_id,user_id,is_valid,user_type_code,create_time,last_edit_time,creation_user_id,last_edit_user_id,current_order_id,current_status from "+TABLE_NAME;
		if(condition!=null&&condition!=""){
			sql+=" where "+condition;
		}
		return sql;
	}


    @Override
   public String getDelete() {
	 String sql="delete from "+TABLE_NAME+" where  enterprise_id="+Utility.getSqlFielStatement("string",this.enterprise_id)+" and user_id="+Utility.getSqlFielStatement("string",this.user_id)+" ";
	 return sql;
   }


}

