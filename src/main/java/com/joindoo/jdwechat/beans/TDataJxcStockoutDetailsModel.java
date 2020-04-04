package com.joindoo.jdwechat.beans;

import com.joindoo.jdwechat.Utility;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;
import java.math.BigDecimal;
/**
* 主数据 - 进销存 - 出库单 - 明细 出库明细，保存本次出库的货品详情
* Author: zhuqiang4433@gmail.com
* Version: CodeGenerator 1.1
* Memo: Auto Created by CodeGenerator on 2020/4/4.
*/

public class TDataJxcStockoutDetailsModel extends BaseModel implements IBaseModel{
	 public static final String TABLE_NAME="T_DATA_JXC_STOCKOUT_DETAILS";
	 public static final String SCRIPT_INSERT="INSERT INTO t_data_jxc_stockout_details(stockout_details_id,stockout_id,enterprise_id,goods_id,description,is_valid,create_time,last_edit_time,creation_user_id,last_edit_user_id)VALUES(?,?,?,?,?,?,?,?,?,?)";
	 public static final String SCRIPT_DELETE="DELETE FROM t_data_jxc_stockout_details WHERE  stockout_details_id=? ";
	 public static final String SCRIPT_UPDATE="UPDATE t_data_jxc_stockout_details  SET {0} WHERE  stockout_details_id=? ";
	 public static final String SCRIPT_SELECT="SELECT stockout_details_id,stockout_id,enterprise_id,goods_id,description,is_valid,create_time,last_edit_time,creation_user_id,last_edit_user_id FROM t_data_jxc_stockout_details WHERE (1=1)";
	 public String getDataTableName() {
 	 return TABLE_NAME;
	 }


	/*明细id*/ 
	protected String stockout_details_id;
	public String getstockout_details_id (){ return stockout_details_id;}
	public void setstockout_details_id(String value){
		this.stockout_details_id=value;
 		this.onChangeProperty("stockout_details_id",this.stockout_details_id,value);
 	}

	/*出库单id*/ 
	protected String stockout_id;
	public String getstockout_id (){ return stockout_id;}
	public void setstockout_id(String value){
		this.stockout_id=value;
 		this.onChangeProperty("stockout_id",this.stockout_id,value);
 	}

	/*企业id*/ 
	protected String enterprise_id;
	public String getenterprise_id (){ return enterprise_id;}
	public void setenterprise_id(String value){
		this.enterprise_id=value;
 		this.onChangeProperty("enterprise_id",this.enterprise_id,value);
 	}

	/*货品id*/ 
	protected String goods_id;
	public String getgoods_id (){ return goods_id;}
	public void setgoods_id(String value){
		this.goods_id=value;
 		this.onChangeProperty("goods_id",this.goods_id,value);
 	}

	/*备注*/ 
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



	public TDataJxcStockoutDetailsModel(){
		super();
		this._keyProperty.add("stockout_details_id");
		initFieldsType();
	}

	private void initFieldsType(){
		this._fieldType.put("stockout_details_id","String");
		this._fieldType.put("stockout_id","String");
		this._fieldType.put("enterprise_id","String");
		this._fieldType.put("goods_id","String");
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
		 if(isExistColumn(resultSet,"stockout_details_id")){
			 this.stockout_details_id=resultSet.getString("stockout_details_id");
		}
		 if(isExistColumn(resultSet,"stockout_id")){
			 this.stockout_id=resultSet.getString("stockout_id");
		}
		 if(isExistColumn(resultSet,"enterprise_id")){
			 this.enterprise_id=resultSet.getString("enterprise_id");
		}
		 if(isExistColumn(resultSet,"goods_id")){
			 this.goods_id=resultSet.getString("goods_id");
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
		    sql+=" where   stockout_details_id="+Utility.getSqlFielStatement("string",this.stockout_details_id)+"  ";
	    return sql;
   }


   @Override
   public String getInsert() {
       String sql="insert into "+TABLE_NAME+"(stockout_details_id,stockout_id,enterprise_id,goods_id,description,is_valid,create_time,last_edit_time,creation_user_id,last_edit_user_id) values("+Utility.getSqlFielStatement("string",this.stockout_details_id)+","+Utility.getSqlFielStatement("string",this.stockout_id)+","+Utility.getSqlFielStatement("string",this.enterprise_id)+","+Utility.getSqlFielStatement("string",this.goods_id)+","+Utility.getSqlFielStatement("string",this.description)+","+this.is_valid+","+Utility.getSqlFielStatement("date",this.create_time)+","+Utility.getSqlFielStatement("date",this.last_edit_time)+","+Utility.getSqlFielStatement("string",this.creation_user_id)+","+Utility.getSqlFielStatement("string",this.last_edit_user_id)+")";
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
   sql+= " where  stockout_details_id="+Utility.getSqlFielStatement("string",this.stockout_details_id)+" ";
   }
   return sql;
}


	@Override
	public String getSelect() {
		String sql="select stockout_details_id,stockout_id,enterprise_id,goods_id,description,is_valid,create_time,last_edit_time,creation_user_id,last_edit_user_id from "+TABLE_NAME;
		if(!Utility.isNullOrEmpty(stockout_details_id)){
			sql+=" where  stockout_details_id="+Utility.getSqlFielStatement("string",this.stockout_details_id)+" ";
		}
		return sql;
	}
	@Override
	public String getSelectByCondition(String condition) {
		String sql="select stockout_details_id,stockout_id,enterprise_id,goods_id,description,is_valid,create_time,last_edit_time,creation_user_id,last_edit_user_id from "+TABLE_NAME;
		if(condition!=null&&condition!=""){
			sql+=" where "+condition;
		}
		return sql;
	}


    @Override
   public String getDelete() {
	 String sql="delete from "+TABLE_NAME+" where  stockout_details_id="+Utility.getSqlFielStatement("string",this.stockout_details_id)+" ";
	 return sql;
   }


}

