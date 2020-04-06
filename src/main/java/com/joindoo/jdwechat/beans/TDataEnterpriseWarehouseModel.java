package com.joindoo.jdwechat.beans;

import com.joindoo.jdwechat.Utility;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;
import java.math.BigDecimal;
/**
* 主数据 - 企业 - 仓库  主要有企业管理员维护 
* Author: zhuqiang4433@gmail.com
* Version: CodeGenerator 1.1
* Memo: Auto Created by CodeGenerator on 2020/4/5.
*/

public class TDataEnterpriseWarehouseModel extends BaseModel implements IBaseModel{
	 public static final String TABLE_NAME="T_DATA_ENTERPRISE_WAREHOUSE";
	 public static final String SCRIPT_INSERT="INSERT INTO t_data_enterprise_warehouse(warehouse_id,enterprise_id,name,short_name,address,description,telephone_number,is_valid,create_time,last_edit_time,creation_user_id,last_edit_user_id)VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
	 public static final String SCRIPT_DELETE="DELETE FROM t_data_enterprise_warehouse WHERE  warehouse_id=? ";
	 public static final String SCRIPT_UPDATE="UPDATE t_data_enterprise_warehouse  SET {0} WHERE  warehouse_id=? ";
	 public static final String SCRIPT_SELECT="SELECT warehouse_id,enterprise_id,name,short_name,address,description,telephone_number,is_valid,create_time,last_edit_time,creation_user_id,last_edit_user_id FROM t_data_enterprise_warehouse WHERE (1=1)";
	 public String getDataTableName() {
 	 return TABLE_NAME;
	 }


	/*仓库id*/ 
	protected String warehouse_id;
	public String getwarehouse_id (){ return warehouse_id;}
	public void setwarehouse_id(String value){
		this.warehouse_id=value;
 		this.onChangeProperty("warehouse_id",this.warehouse_id,value);
 	}

	/*企业id*/ 
	protected String enterprise_id;
	public String getenterprise_id (){ return enterprise_id;}
	public void setenterprise_id(String value){
		this.enterprise_id=value;
 		this.onChangeProperty("enterprise_id",this.enterprise_id,value);
 	}

	/*名称*/ 
	protected String name;
	public String getname (){ return name;}
	public void setname(String value){
		this.name=value;
 		this.onChangeProperty("name",this.name,value);
 	}

	/*简称*/ 
	protected String short_name;
	public String getshort_name (){ return short_name;}
	public void setshort_name(String value){
		this.short_name=value;
 		this.onChangeProperty("short_name",this.short_name,value);
 	}

	/*地址*/ 
	protected String address;
	public String getaddress (){ return address;}
	public void setaddress(String value){
		this.address=value;
 		this.onChangeProperty("address",this.address,value);
 	}

	/*仓库描述*/ 
	protected String description;
	public String getdescription (){ return description;}
	public void setdescription(String value){
		this.description=value;
 		this.onChangeProperty("description",this.description,value);
 	}

	/*联系电话*/ 
	protected String telephone_number;
	public String gettelephone_number (){ return telephone_number;}
	public void settelephone_number(String value){
		this.telephone_number=value;
 		this.onChangeProperty("telephone_number",this.telephone_number,value);
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



	public TDataEnterpriseWarehouseModel(){
		super();
		this._keyProperty.add("warehouse_id");
		initFieldsType();
	}

	private void initFieldsType(){
		this._fieldType.put("warehouse_id","String");
		this._fieldType.put("enterprise_id","String");
		this._fieldType.put("name","String");
		this._fieldType.put("short_name","String");
		this._fieldType.put("address","String");
		this._fieldType.put("description","String");
		this._fieldType.put("telephone_number","String");
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
		 if(isExistColumn(resultSet,"warehouse_id")){
			 this.warehouse_id=resultSet.getString("warehouse_id");
		}
		 if(isExistColumn(resultSet,"enterprise_id")){
			 this.enterprise_id=resultSet.getString("enterprise_id");
		}
		 if(isExistColumn(resultSet,"name")){
			 this.name=resultSet.getString("name");
		}
		 if(isExistColumn(resultSet,"short_name")){
			 this.short_name=resultSet.getString("short_name");
		}
		 if(isExistColumn(resultSet,"address")){
			 this.address=resultSet.getString("address");
		}
		 if(isExistColumn(resultSet,"description")){
			 this.description=resultSet.getString("description");
		}
		 if(isExistColumn(resultSet,"telephone_number")){
			 this.telephone_number=resultSet.getString("telephone_number");
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
		    sql+=" where   warehouse_id="+Utility.getSqlFielStatement("string",this.warehouse_id)+"  ";
	    return sql;
   }


   @Override
   public String getInsert() {
       String sql="insert into "+TABLE_NAME+"(warehouse_id,enterprise_id,name,short_name,address,description,telephone_number,is_valid,create_time,last_edit_time,creation_user_id,last_edit_user_id) values("+Utility.getSqlFielStatement("string",this.warehouse_id)+","+Utility.getSqlFielStatement("string",this.enterprise_id)+","+Utility.getSqlFielStatement("string",this.name)+","+Utility.getSqlFielStatement("string",this.short_name)+","+Utility.getSqlFielStatement("string",this.address)+","+Utility.getSqlFielStatement("string",this.description)+","+Utility.getSqlFielStatement("string",this.telephone_number)+","+this.is_valid+","+Utility.getSqlFielStatement("date",this.create_time)+","+Utility.getSqlFielStatement("date",this.last_edit_time)+","+Utility.getSqlFielStatement("string",this.creation_user_id)+","+Utility.getSqlFielStatement("string",this.last_edit_user_id)+")";
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
   sql+= " where  warehouse_id="+Utility.getSqlFielStatement("string",this.warehouse_id)+" ";
   }
   return sql;
}


	@Override
	public String getSelect() {
		String sql="select warehouse_id,enterprise_id,name,short_name,address,description,telephone_number,is_valid,create_time,last_edit_time,creation_user_id,last_edit_user_id from "+TABLE_NAME;
		if(!Utility.isNullOrEmpty(warehouse_id)){
			sql+=" where  warehouse_id="+Utility.getSqlFielStatement("string",this.warehouse_id)+" ";
		}
		return sql;
	}
	@Override
	public String getSelectByCondition(String condition) {
		String sql="select warehouse_id,enterprise_id,name,short_name,address,description,telephone_number,is_valid,create_time,last_edit_time,creation_user_id,last_edit_user_id from "+TABLE_NAME;
		if(condition!=null&&condition!=""){
			sql+=" where "+condition;
		}
		return sql;
	}


    @Override
   public String getDelete() {
	 String sql="delete from "+TABLE_NAME+" where  warehouse_id="+Utility.getSqlFielStatement("string",this.warehouse_id)+" ";
	 return sql;
   }


}

