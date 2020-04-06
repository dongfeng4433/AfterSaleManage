package com.joindoo.jdwechat.beans;

import com.joindoo.jdwechat.Utility;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;
import java.math.BigDecimal;
/**
* 主数据 - 企业 - 工单 
* Author: zhuqiang4433@gmail.com
* Version: CodeGenerator 1.1
* Memo: Auto Created by CodeGenerator on 2020/4/5.
*/

public class TDataEnterpriseOrderModel extends BaseModel implements IBaseModel{
	 public static final String TABLE_NAME="T_DATA_ENTERPRISE_ORDER";
	 public static final String SCRIPT_INSERT="INSERT INTO t_data_enterprise_order(order_id,order_no,enterprise_id,name,customer_id,address,description,is_valid,create_time,last_edit_time,creation_user_id,last_edit_user_id,start_time,complete_time,plan_complete_time,work_user_id,order_status,goods_amount)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	 public static final String SCRIPT_DELETE="DELETE FROM t_data_enterprise_order WHERE  order_id=? ";
	 public static final String SCRIPT_UPDATE="UPDATE t_data_enterprise_order  SET {0} WHERE  order_id=? ";
	 public static final String SCRIPT_SELECT="SELECT order_id,order_no,enterprise_id,name,customer_id,address,description,is_valid,create_time,last_edit_time,creation_user_id,last_edit_user_id,start_time,complete_time,plan_complete_time,work_user_id,order_status,goods_amount FROM t_data_enterprise_order WHERE (1=1)";
	 public String getDataTableName() {
 	 return TABLE_NAME;
	 }


	/*工单id*/ 
	protected String order_id;
	public String getorder_id (){ return order_id;}
	public void setorder_id(String value){
		this.order_id=value;
 		this.onChangeProperty("order_id",this.order_id,value);
 	}

	/*工单编号(SH2003220001)*/ 
	protected String order_no;
	public String getorder_no (){ return order_no;}
	public void setorder_no(String value){
		this.order_no=value;
 		this.onChangeProperty("order_no",this.order_no,value);
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

	/*客户id*/ 
	protected String customer_id;
	public String getcustomer_id (){ return customer_id;}
	public void setcustomer_id(String value){
		this.customer_id=value;
 		this.onChangeProperty("customer_id",this.customer_id,value);
 	}

	/*地址*/ 
	protected String address;
	public String getaddress (){ return address;}
	public void setaddress(String value){
		this.address=value;
 		this.onChangeProperty("address",this.address,value);
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

	/*接单时间*/ 
	protected Date start_time;
	public Date getstart_time (){ return start_time;}
	public void setstart_time(Date value){
		this.start_time=value;
 		this.onChangeProperty("start_time",this.start_time,value);
 	}

	/*完成时间*/ 
	protected Date complete_time;
	public Date getcomplete_time (){ return complete_time;}
	public void setcomplete_time(Date value){
		this.complete_time=value;
 		this.onChangeProperty("complete_time",this.complete_time,value);
 	}

	/*计划完成时间*/ 
	protected Date plan_complete_time;
	public Date getplan_complete_time (){ return plan_complete_time;}
	public void setplan_complete_time(Date value){
		this.plan_complete_time=value;
 		this.onChangeProperty("plan_complete_time",this.plan_complete_time,value);
 	}

	/*工单接单人员*/ 
	protected String work_user_id;
	public String getwork_user_id (){ return work_user_id;}
	public void setwork_user_id(String value){
		this.work_user_id=value;
 		this.onChangeProperty("work_user_id",this.work_user_id,value);
 	}

	/*工单状态(0 待分配 1作业中 2中断 3完成 9作废)*/ 
	protected String order_status;
	public String getorder_status (){ return order_status;}
	public void setorder_status(String value){
		this.order_status=value;
 		this.onChangeProperty("order_status",this.order_status,value);
 	}

	/*配件金额*/ 
	protected Double goods_amount;
	public Double getgoods_amount (){ return goods_amount;}
	public void setgoods_amount(Double value){
		this.goods_amount=value;
 		this.onChangeProperty("goods_amount",this.goods_amount,value);
 	}



	public TDataEnterpriseOrderModel(){
		super();
		this._keyProperty.add("order_id");
		initFieldsType();
	}

	private void initFieldsType(){
		this._fieldType.put("order_id","String");
		this._fieldType.put("order_no","String");
		this._fieldType.put("enterprise_id","String");
		this._fieldType.put("name","String");
		this._fieldType.put("customer_id","String");
		this._fieldType.put("address","String");
		this._fieldType.put("description","String");
		this._fieldType.put("is_valid","Integer");
		this._fieldType.put("create_time","Date");
		this._fieldType.put("last_edit_time","Date");
		this._fieldType.put("creation_user_id","String");
		this._fieldType.put("last_edit_user_id","String");
		this._fieldType.put("start_time","Date");
		this._fieldType.put("complete_time","Date");
		this._fieldType.put("plan_complete_time","Date");
		this._fieldType.put("work_user_id","String");
		this._fieldType.put("order_status","String");
		this._fieldType.put("goods_amount","Double");
	}
	@Override
	public void initAsInsert() {

	}
	@Override
	public void fillModel(ResultSet resultSet) {
		try { 
		 if(isExistColumn(resultSet,"order_id")){
			 this.order_id=resultSet.getString("order_id");
		}
		 if(isExistColumn(resultSet,"order_no")){
			 this.order_no=resultSet.getString("order_no");
		}
		 if(isExistColumn(resultSet,"enterprise_id")){
			 this.enterprise_id=resultSet.getString("enterprise_id");
		}
		 if(isExistColumn(resultSet,"name")){
			 this.name=resultSet.getString("name");
		}
		 if(isExistColumn(resultSet,"customer_id")){
			 this.customer_id=resultSet.getString("customer_id");
		}
		 if(isExistColumn(resultSet,"address")){
			 this.address=resultSet.getString("address");
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
		 if(isExistColumn(resultSet,"start_time")){
			 this.start_time=(Date)resultSet.getObject("start_time");
		}
		 if(isExistColumn(resultSet,"complete_time")){
			 this.complete_time=(Date)resultSet.getObject("complete_time");
		}
		 if(isExistColumn(resultSet,"plan_complete_time")){
			 this.plan_complete_time=(Date)resultSet.getObject("plan_complete_time");
		}
		 if(isExistColumn(resultSet,"work_user_id")){
			 this.work_user_id=resultSet.getString("work_user_id");
		}
		 if(isExistColumn(resultSet,"order_status")){
			 this.order_status=resultSet.getString("order_status");
		}
		 if(isExistColumn(resultSet,"goods_amount")){
			 this.goods_amount=resultSet.getDouble("goods_amount");
			if (resultSet.wasNull()) {
				this.goods_amount=null;
			}
		}
      } catch (SQLException e) {
              e.printStackTrace();
          }
      }
  @Override
   public String getExist() {
	    String sql="select 1 from "+TABLE_NAME;
		    sql+=" where   order_id="+Utility.getSqlFielStatement("string",this.order_id)+"  ";
	    return sql;
   }


   @Override
   public String getInsert() {
       String sql="insert into "+TABLE_NAME+"(order_id,order_no,enterprise_id,name,customer_id,address,description,is_valid,create_time,last_edit_time,creation_user_id,last_edit_user_id,start_time,complete_time,plan_complete_time,work_user_id,order_status,goods_amount) values("+Utility.getSqlFielStatement("string",this.order_id)+","+Utility.getSqlFielStatement("string",this.order_no)+","+Utility.getSqlFielStatement("string",this.enterprise_id)+","+Utility.getSqlFielStatement("string",this.name)+","+Utility.getSqlFielStatement("string",this.customer_id)+","+Utility.getSqlFielStatement("string",this.address)+","+Utility.getSqlFielStatement("string",this.description)+","+this.is_valid+","+Utility.getSqlFielStatement("date",this.create_time)+","+Utility.getSqlFielStatement("date",this.last_edit_time)+","+Utility.getSqlFielStatement("string",this.creation_user_id)+","+Utility.getSqlFielStatement("string",this.last_edit_user_id)+","+Utility.getSqlFielStatement("date",this.start_time)+","+Utility.getSqlFielStatement("date",this.complete_time)+","+Utility.getSqlFielStatement("date",this.plan_complete_time)+","+Utility.getSqlFielStatement("string",this.work_user_id)+","+Utility.getSqlFielStatement("string",this.order_status)+","+this.goods_amount+")";
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
   sql+= " where  order_id="+Utility.getSqlFielStatement("string",this.order_id)+" ";
   }
   return sql;
}


	@Override
	public String getSelect() {
		String sql="select order_id,order_no,enterprise_id,name,customer_id,address,description,is_valid,create_time,last_edit_time,creation_user_id,last_edit_user_id,start_time,complete_time,plan_complete_time,work_user_id,order_status,goods_amount from "+TABLE_NAME;
		if(!Utility.isNullOrEmpty(order_id)){
			sql+=" where  order_id="+Utility.getSqlFielStatement("string",this.order_id)+" ";
		}
		return sql;
	}
	@Override
	public String getSelectByCondition(String condition) {
		String sql="select order_id,order_no,enterprise_id,name,customer_id,address,description,is_valid,create_time,last_edit_time,creation_user_id,last_edit_user_id,start_time,complete_time,plan_complete_time,work_user_id,order_status,goods_amount from "+TABLE_NAME;
		if(condition!=null&&condition!=""){
			sql+=" where "+condition;
		}
		return sql;
	}


    @Override
   public String getDelete() {
	 String sql="delete from "+TABLE_NAME+" where  order_id="+Utility.getSqlFielStatement("string",this.order_id)+" ";
	 return sql;
   }


}

