package com.joindoo.jdwechat.beans;

import com.joindoo.jdwechat.Utility;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;
import java.math.BigDecimal;
/**
* 主数据 - 企业 - 工单 - 2 - 流程
* Author: zhuqiang4433@gmail.com
* Version: CodeGenerator 1.1
* Memo: Auto Created by CodeGenerator on 2020/4/4.
*/

public class TDataEnterpriseOrderWorkflowModel extends BaseModel implements IBaseModel{
	 public static final String TABLE_NAME="T_DATA_ENTERPRISE_ORDER_WORKFLOW";
	 public static final String SCRIPT_INSERT="INSERT INTO t_data_enterprise_order_workflow(workflow_id,order_id,work_user_id,description,is_valid,create_time,last_edit_time,creation_user_id,last_edit_user_id)VALUES(?,?,?,?,?,?,?,?,?)";
	 public static final String SCRIPT_DELETE="DELETE FROM t_data_enterprise_order_workflow WHERE  workflow_id=? ";
	 public static final String SCRIPT_UPDATE="UPDATE t_data_enterprise_order_workflow  SET {0} WHERE  workflow_id=? ";
	 public static final String SCRIPT_SELECT="SELECT workflow_id,order_id,work_user_id,description,is_valid,create_time,last_edit_time,creation_user_id,last_edit_user_id FROM t_data_enterprise_order_workflow WHERE (1=1)";
	 public String getDataTableName() {
 	 return TABLE_NAME;
	 }


	/*流程id*/ 
	protected String workflow_id;
	public String getworkflow_id (){ return workflow_id;}
	public void setworkflow_id(String value){
		this.workflow_id=value;
 		this.onChangeProperty("workflow_id",this.workflow_id,value);
 	}

	/*工单id*/ 
	protected String order_id;
	public String getorder_id (){ return order_id;}
	public void setorder_id(String value){
		this.order_id=value;
 		this.onChangeProperty("order_id",this.order_id,value);
 	}

	/*工作人员*/ 
	protected String work_user_id;
	public String getwork_user_id (){ return work_user_id;}
	public void setwork_user_id(String value){
		this.work_user_id=value;
 		this.onChangeProperty("work_user_id",this.work_user_id,value);
 	}

	/*工单简介描述*/ 
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



	public TDataEnterpriseOrderWorkflowModel(){
		super();
		this._keyProperty.add("workflow_id");
		initFieldsType();
	}

	private void initFieldsType(){
		this._fieldType.put("workflow_id","String");
		this._fieldType.put("order_id","String");
		this._fieldType.put("work_user_id","String");
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
		 if(isExistColumn(resultSet,"workflow_id")){
			 this.workflow_id=resultSet.getString("workflow_id");
		}
		 if(isExistColumn(resultSet,"order_id")){
			 this.order_id=resultSet.getString("order_id");
		}
		 if(isExistColumn(resultSet,"work_user_id")){
			 this.work_user_id=resultSet.getString("work_user_id");
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
		    sql+=" where   workflow_id="+Utility.getSqlFielStatement("string",this.workflow_id)+"  ";
	    return sql;
   }


   @Override
   public String getInsert() {
       String sql="insert into "+TABLE_NAME+"(workflow_id,order_id,work_user_id,description,is_valid,create_time,last_edit_time,creation_user_id,last_edit_user_id) values("+Utility.getSqlFielStatement("string",this.workflow_id)+","+Utility.getSqlFielStatement("string",this.order_id)+","+Utility.getSqlFielStatement("string",this.work_user_id)+","+Utility.getSqlFielStatement("string",this.description)+","+this.is_valid+","+Utility.getSqlFielStatement("date",this.create_time)+","+Utility.getSqlFielStatement("date",this.last_edit_time)+","+Utility.getSqlFielStatement("string",this.creation_user_id)+","+Utility.getSqlFielStatement("string",this.last_edit_user_id)+")";
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
   sql+= " where  workflow_id="+Utility.getSqlFielStatement("string",this.workflow_id)+" ";
   }
   return sql;
}


	@Override
	public String getSelect() {
		String sql="select workflow_id,order_id,work_user_id,description,is_valid,create_time,last_edit_time,creation_user_id,last_edit_user_id from "+TABLE_NAME;
		if(!Utility.isNullOrEmpty(workflow_id)){
			sql+=" where  workflow_id="+Utility.getSqlFielStatement("string",this.workflow_id)+" ";
		}
		return sql;
	}
	@Override
	public String getSelectByCondition(String condition) {
		String sql="select workflow_id,order_id,work_user_id,description,is_valid,create_time,last_edit_time,creation_user_id,last_edit_user_id from "+TABLE_NAME;
		if(condition!=null&&condition!=""){
			sql+=" where "+condition;
		}
		return sql;
	}


    @Override
   public String getDelete() {
	 String sql="delete from "+TABLE_NAME+" where  workflow_id="+Utility.getSqlFielStatement("string",this.workflow_id)+" ";
	 return sql;
   }


}

