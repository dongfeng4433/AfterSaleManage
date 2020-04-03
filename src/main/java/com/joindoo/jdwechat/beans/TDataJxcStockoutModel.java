package com.joindoo.jdwechat.beans;

import com.joindoo.jdwechat.Utility;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;
import java.math.BigDecimal;
/**
* 主数据 - 进销存 - 出库单  在售后工单派单成功后，会自动生成出库单
* Author: zhuqiang4433@gmail.com
* Version: CodeGenerator 1.1
* Memo: Auto Created by CodeGenerator on 2020/4/3.
*/

public class TDataJxcStockoutModel extends BaseModel implements IBaseModel{
	 public static final String TABLE_NAME="T_DATA_JXC_STOCKOUT";
	 public static final String SCRIPT_INSERT="INSERT INTO t_data_jxc_stockout(stockout_id,stockout_no,warehouse_id,source_type,source_no,enterprise_id,customer_id,description,is_valid,create_time,last_edit_time,creation_user_id,last_edit_user_id,audit_type,audit_user_id,audit_time,audit_memo)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	 public static final String SCRIPT_DELETE="DELETE FROM t_data_jxc_stockout WHERE  stockout_id=? ";
	 public static final String SCRIPT_UPDATE="UPDATE t_data_jxc_stockout  SET {0} WHERE  stockout_id=? ";
	 public static final String SCRIPT_SELECT="SELECT stockout_id,stockout_no,warehouse_id,source_type,source_no,enterprise_id,customer_id,description,is_valid,create_time,last_edit_time,creation_user_id,last_edit_user_id,audit_type,audit_user_id,audit_time,audit_memo FROM t_data_jxc_stockout WHERE (1=1)";
	 public String getDataTableName() {
 	 return TABLE_NAME;
	 }


	/*出库单id*/ 
	protected String stockout_id;
	public String getstockout_id (){ return stockout_id;}
	public void setstockout_id(String value){
		this.stockout_id=value;
 		this.onChangeProperty("stockout_id",this.stockout_id,value);
 	}

	/*出库单号（SO2003220001）*/ 
	protected String stockout_no;
	public String getstockout_no (){ return stockout_no;}
	public void setstockout_no(String value){
		this.stockout_no=value;
 		this.onChangeProperty("stockout_no",this.stockout_no,value);
 	}

	/*仓库id*/ 
	protected String warehouse_id;
	public String getwarehouse_id (){ return warehouse_id;}
	public void setwarehouse_id(String value){
		this.warehouse_id=value;
 		this.onChangeProperty("warehouse_id",this.warehouse_id,value);
 	}

	/*来源类型(1售后工单 2 )*/ 
	protected String source_type;
	public String getsource_type (){ return source_type;}
	public void setsource_type(String value){
		this.source_type=value;
 		this.onChangeProperty("source_type",this.source_type,value);
 	}

	/*来源单据号*/ 
	protected String source_no;
	public String getsource_no (){ return source_no;}
	public void setsource_no(String value){
		this.source_no=value;
 		this.onChangeProperty("source_no",this.source_no,value);
 	}

	/*企业id*/ 
	protected String enterprise_id;
	public String getenterprise_id (){ return enterprise_id;}
	public void setenterprise_id(String value){
		this.enterprise_id=value;
 		this.onChangeProperty("enterprise_id",this.enterprise_id,value);
 	}

	/*客户id*/ 
	protected String customer_id;
	public String getcustomer_id (){ return customer_id;}
	public void setcustomer_id(String value){
		this.customer_id=value;
 		this.onChangeProperty("customer_id",this.customer_id,value);
 	}

	/*简介描述*/ 
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

	/*审核状态（0 未申 1审核通过 2 不通过）*/ 
	protected String audit_type;
	public String getaudit_type (){ return audit_type;}
	public void setaudit_type(String value){
		this.audit_type=value;
 		this.onChangeProperty("audit_type",this.audit_type,value);
 	}

	/*审核人*/ 
	protected String audit_user_id;
	public String getaudit_user_id (){ return audit_user_id;}
	public void setaudit_user_id(String value){
		this.audit_user_id=value;
 		this.onChangeProperty("audit_user_id",this.audit_user_id,value);
 	}

	/*审核时间*/ 
	protected Date audit_time;
	public Date getaudit_time (){ return audit_time;}
	public void setaudit_time(Date value){
		this.audit_time=value;
 		this.onChangeProperty("audit_time",this.audit_time,value);
 	}

	/*审核备注*/ 
	protected String audit_memo;
	public String getaudit_memo (){ return audit_memo;}
	public void setaudit_memo(String value){
		this.audit_memo=value;
 		this.onChangeProperty("audit_memo",this.audit_memo,value);
 	}



	public TDataJxcStockoutModel(){
		super();
		this._keyProperty.add("stockout_id");
		initFieldsType();
	}

	private void initFieldsType(){
		this._fieldType.put("stockout_id","String");
		this._fieldType.put("stockout_no","String");
		this._fieldType.put("warehouse_id","String");
		this._fieldType.put("source_type","String");
		this._fieldType.put("source_no","String");
		this._fieldType.put("enterprise_id","String");
		this._fieldType.put("customer_id","String");
		this._fieldType.put("description","String");
		this._fieldType.put("is_valid","Integer");
		this._fieldType.put("create_time","Date");
		this._fieldType.put("last_edit_time","Date");
		this._fieldType.put("creation_user_id","String");
		this._fieldType.put("last_edit_user_id","String");
		this._fieldType.put("audit_type","String");
		this._fieldType.put("audit_user_id","String");
		this._fieldType.put("audit_time","Date");
		this._fieldType.put("audit_memo","String");
	}
	@Override
	public void initAsInsert() {

	}
	@Override
	public void fillModel(ResultSet resultSet) {
		try { 
		 if(isExistColumn(resultSet,"stockout_id")){
			 this.stockout_id=resultSet.getString("stockout_id");
		}
		 if(isExistColumn(resultSet,"stockout_no")){
			 this.stockout_no=resultSet.getString("stockout_no");
		}
		 if(isExistColumn(resultSet,"warehouse_id")){
			 this.warehouse_id=resultSet.getString("warehouse_id");
		}
		 if(isExistColumn(resultSet,"source_type")){
			 this.source_type=resultSet.getString("source_type");
		}
		 if(isExistColumn(resultSet,"source_no")){
			 this.source_no=resultSet.getString("source_no");
		}
		 if(isExistColumn(resultSet,"enterprise_id")){
			 this.enterprise_id=resultSet.getString("enterprise_id");
		}
		 if(isExistColumn(resultSet,"customer_id")){
			 this.customer_id=resultSet.getString("customer_id");
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
		 if(isExistColumn(resultSet,"audit_type")){
			 this.audit_type=resultSet.getString("audit_type");
		}
		 if(isExistColumn(resultSet,"audit_user_id")){
			 this.audit_user_id=resultSet.getString("audit_user_id");
		}
		 if(isExistColumn(resultSet,"audit_time")){
			 this.audit_time=(Date)resultSet.getObject("audit_time");
		}
		 if(isExistColumn(resultSet,"audit_memo")){
			 this.audit_memo=resultSet.getString("audit_memo");
		}
      } catch (SQLException e) {
              e.printStackTrace();
          }
      }
  @Override
   public String getExist() {
	    String sql="select 1 from "+TABLE_NAME;
		    sql+=" where   stockout_id="+Utility.getSqlFielStatement("string",this.stockout_id)+"  ";
	    return sql;
   }


   @Override
   public String getInsert() {
       String sql="insert into "+TABLE_NAME+"(stockout_id,stockout_no,warehouse_id,source_type,source_no,enterprise_id,customer_id,description,is_valid,create_time,last_edit_time,creation_user_id,last_edit_user_id,audit_type,audit_user_id,audit_time,audit_memo) values("+Utility.getSqlFielStatement("string",this.stockout_id)+","+Utility.getSqlFielStatement("string",this.stockout_no)+","+Utility.getSqlFielStatement("string",this.warehouse_id)+","+Utility.getSqlFielStatement("string",this.source_type)+","+Utility.getSqlFielStatement("string",this.source_no)+","+Utility.getSqlFielStatement("string",this.enterprise_id)+","+Utility.getSqlFielStatement("string",this.customer_id)+","+Utility.getSqlFielStatement("string",this.description)+","+this.is_valid+","+Utility.getSqlFielStatement("date",this.create_time)+","+Utility.getSqlFielStatement("date",this.last_edit_time)+","+Utility.getSqlFielStatement("string",this.creation_user_id)+","+Utility.getSqlFielStatement("string",this.last_edit_user_id)+","+Utility.getSqlFielStatement("string",this.audit_type)+","+Utility.getSqlFielStatement("string",this.audit_user_id)+","+Utility.getSqlFielStatement("date",this.audit_time)+","+Utility.getSqlFielStatement("string",this.audit_memo)+")";
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
   sql+= " where  stockout_id="+Utility.getSqlFielStatement("string",this.stockout_id)+" ";
   }
   return sql;
}


	@Override
	public String getSelect() {
		String sql="select stockout_id,stockout_no,warehouse_id,source_type,source_no,enterprise_id,customer_id,description,is_valid,create_time,last_edit_time,creation_user_id,last_edit_user_id,audit_type,audit_user_id,audit_time,audit_memo from "+TABLE_NAME;
		if(!Utility.isNullOrEmpty(stockout_id)){
			sql+=" where  stockout_id="+Utility.getSqlFielStatement("string",this.stockout_id)+" ";
		}
		return sql;
	}
	@Override
	public String getSelectByCondition(String condition) {
		String sql="select stockout_id,stockout_no,warehouse_id,source_type,source_no,enterprise_id,customer_id,description,is_valid,create_time,last_edit_time,creation_user_id,last_edit_user_id,audit_type,audit_user_id,audit_time,audit_memo from "+TABLE_NAME;
		if(condition!=null&&condition!=""){
			sql+=" where "+condition;
		}
		return sql;
	}


    @Override
   public String getDelete() {
	 String sql="delete from "+TABLE_NAME+" where  stockout_id="+Utility.getSqlFielStatement("string",this.stockout_id)+" ";
	 return sql;
   }


}

