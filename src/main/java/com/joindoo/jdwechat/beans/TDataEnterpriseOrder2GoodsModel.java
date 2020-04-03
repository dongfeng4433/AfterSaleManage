package com.joindoo.jdwechat.beans;

import com.joindoo.jdwechat.Utility;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;
import java.math.BigDecimal;
/**
* 主数据 - 企业 - 工单 - 2 - 配件  本次工单所有的配件清单
* Author: zhuqiang4433@gmail.com
* Version: CodeGenerator 1.1
* Memo: Auto Created by CodeGenerator on 2020/4/3.
*/

public class TDataEnterpriseOrder2GoodsModel extends BaseModel implements IBaseModel{
	 public static final String TABLE_NAME="T_DATA_ENTERPRISE_ORDER_2_GOODS";
	 public static final String SCRIPT_INSERT="INSERT INTO t_data_enterprise_order_2_goods(goods_id,order_id,warehouse_id,unit_id,quantity,unit_id2,quantity2,description,is_valid,create_time,last_edit_time,creation_user_id,last_edit_user_id,price,amount)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	 public static final String SCRIPT_DELETE="DELETE FROM t_data_enterprise_order_2_goods WHERE  goods_id=? AND order_id=? ";
	 public static final String SCRIPT_UPDATE="UPDATE t_data_enterprise_order_2_goods  SET {0} WHERE  goods_id=? AND order_id=? ";
	 public static final String SCRIPT_SELECT="SELECT goods_id,order_id,warehouse_id,unit_id,quantity,unit_id2,quantity2,description,is_valid,create_time,last_edit_time,creation_user_id,last_edit_user_id,price,amount FROM t_data_enterprise_order_2_goods WHERE (1=1)";
	 public String getDataTableName() {
 	 return TABLE_NAME;
	 }


	/*货品id*/ 
	protected String goods_id;
	public String getgoods_id (){ return goods_id;}
	public void setgoods_id(String value){
		this.goods_id=value;
 		this.onChangeProperty("goods_id",this.goods_id,value);
 	}

	/*工单id*/ 
	protected String order_id;
	public String getorder_id (){ return order_id;}
	public void setorder_id(String value){
		this.order_id=value;
 		this.onChangeProperty("order_id",this.order_id,value);
 	}

	/*仓库id*/ 
	protected String warehouse_id;
	public String getwarehouse_id (){ return warehouse_id;}
	public void setwarehouse_id(String value){
		this.warehouse_id=value;
 		this.onChangeProperty("warehouse_id",this.warehouse_id,value);
 	}

	/*主计量单位*/ 
	protected String unit_id;
	public String getunit_id (){ return unit_id;}
	public void setunit_id(String value){
		this.unit_id=value;
 		this.onChangeProperty("unit_id",this.unit_id,value);
 	}

	/*主数量*/ 
	protected Double quantity;
	public Double getquantity (){ return quantity;}
	public void setquantity(Double value){
		this.quantity=value;
 		this.onChangeProperty("quantity",this.quantity,value);
 	}

	/*辅计量单位*/ 
	protected String unit_id2;
	public String getunit_id2 (){ return unit_id2;}
	public void setunit_id2(String value){
		this.unit_id2=value;
 		this.onChangeProperty("unit_id2",this.unit_id2,value);
 	}

	/*辅数量*/ 
	protected Double quantity2;
	public Double getquantity2 (){ return quantity2;}
	public void setquantity2(Double value){
		this.quantity2=value;
 		this.onChangeProperty("quantity2",this.quantity2,value);
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

	/*单价*/ 
	protected Double price;
	public Double getprice (){ return price;}
	public void setprice(Double value){
		this.price=value;
 		this.onChangeProperty("price",this.price,value);
 	}

	/*金额*/ 
	protected Double amount;
	public Double getamount (){ return amount;}
	public void setamount(Double value){
		this.amount=value;
 		this.onChangeProperty("amount",this.amount,value);
 	}



	public TDataEnterpriseOrder2GoodsModel(){
		super();
		this._keyProperty.add("goods_id");
		this._keyProperty.add("order_id");
		initFieldsType();
	}

	private void initFieldsType(){
		this._fieldType.put("goods_id","String");
		this._fieldType.put("order_id","String");
		this._fieldType.put("warehouse_id","String");
		this._fieldType.put("unit_id","String");
		this._fieldType.put("quantity","Double");
		this._fieldType.put("unit_id2","String");
		this._fieldType.put("quantity2","Double");
		this._fieldType.put("description","String");
		this._fieldType.put("is_valid","Integer");
		this._fieldType.put("create_time","Date");
		this._fieldType.put("last_edit_time","Date");
		this._fieldType.put("creation_user_id","String");
		this._fieldType.put("last_edit_user_id","String");
		this._fieldType.put("price","Double");
		this._fieldType.put("amount","Double");
	}
	@Override
	public void initAsInsert() {

	}
	@Override
	public void fillModel(ResultSet resultSet) {
		try { 
		 if(isExistColumn(resultSet,"goods_id")){
			 this.goods_id=resultSet.getString("goods_id");
		}
		 if(isExistColumn(resultSet,"order_id")){
			 this.order_id=resultSet.getString("order_id");
		}
		 if(isExistColumn(resultSet,"warehouse_id")){
			 this.warehouse_id=resultSet.getString("warehouse_id");
		}
		 if(isExistColumn(resultSet,"unit_id")){
			 this.unit_id=resultSet.getString("unit_id");
		}
		 if(isExistColumn(resultSet,"quantity")){
			 this.quantity=resultSet.getDouble("quantity");
			if (resultSet.wasNull()) {
				this.quantity=null;
			}
		}
		 if(isExistColumn(resultSet,"unit_id2")){
			 this.unit_id2=resultSet.getString("unit_id2");
		}
		 if(isExistColumn(resultSet,"quantity2")){
			 this.quantity2=resultSet.getDouble("quantity2");
			if (resultSet.wasNull()) {
				this.quantity2=null;
			}
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
		 if(isExistColumn(resultSet,"price")){
			 this.price=resultSet.getDouble("price");
			if (resultSet.wasNull()) {
				this.price=null;
			}
		}
		 if(isExistColumn(resultSet,"amount")){
			 this.amount=resultSet.getDouble("amount");
			if (resultSet.wasNull()) {
				this.amount=null;
			}
		}
      } catch (SQLException e) {
              e.printStackTrace();
          }
      }
  @Override
   public String getExist() {
	    String sql="select 1 from "+TABLE_NAME;
		    sql+=" where   goods_id="+Utility.getSqlFielStatement("string",this.goods_id)+" and order_id="+Utility.getSqlFielStatement("string",this.order_id)+"  ";
	    return sql;
   }


   @Override
   public String getInsert() {
       String sql="insert into "+TABLE_NAME+"(goods_id,order_id,warehouse_id,unit_id,quantity,unit_id2,quantity2,description,is_valid,create_time,last_edit_time,creation_user_id,last_edit_user_id,price,amount) values("+Utility.getSqlFielStatement("string",this.goods_id)+","+Utility.getSqlFielStatement("string",this.order_id)+","+Utility.getSqlFielStatement("string",this.warehouse_id)+","+Utility.getSqlFielStatement("string",this.unit_id)+","+this.quantity+","+Utility.getSqlFielStatement("string",this.unit_id2)+","+this.quantity2+","+Utility.getSqlFielStatement("string",this.description)+","+this.is_valid+","+Utility.getSqlFielStatement("date",this.create_time)+","+Utility.getSqlFielStatement("date",this.last_edit_time)+","+Utility.getSqlFielStatement("string",this.creation_user_id)+","+Utility.getSqlFielStatement("string",this.last_edit_user_id)+","+this.price+","+this.amount+")";
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
   sql+= " where  goods_id="+Utility.getSqlFielStatement("string",this.goods_id)+" and order_id="+Utility.getSqlFielStatement("string",this.order_id)+" ";
   }
   return sql;
}


	@Override
	public String getSelect() {
		String sql="select goods_id,order_id,warehouse_id,unit_id,quantity,unit_id2,quantity2,description,is_valid,create_time,last_edit_time,creation_user_id,last_edit_user_id,price,amount from "+TABLE_NAME;
		if(!Utility.isNullOrEmpty(goods_id)&&!Utility.isNullOrEmpty(order_id)){
			sql+=" where  goods_id="+Utility.getSqlFielStatement("string",this.goods_id)+" and order_id="+Utility.getSqlFielStatement("string",this.order_id)+" ";
		}
		return sql;
	}
	@Override
	public String getSelectByCondition(String condition) {
		String sql="select goods_id,order_id,warehouse_id,unit_id,quantity,unit_id2,quantity2,description,is_valid,create_time,last_edit_time,creation_user_id,last_edit_user_id,price,amount from "+TABLE_NAME;
		if(condition!=null&&condition!=""){
			sql+=" where "+condition;
		}
		return sql;
	}


    @Override
   public String getDelete() {
	 String sql="delete from "+TABLE_NAME+" where  goods_id="+Utility.getSqlFielStatement("string",this.goods_id)+" and order_id="+Utility.getSqlFielStatement("string",this.order_id)+" ";
	 return sql;
   }


}

