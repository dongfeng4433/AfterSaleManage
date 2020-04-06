package com.joindoo.jdwechat.beans;

import com.joindoo.jdwechat.Utility;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;
import java.math.BigDecimal;
/**
* 主数据 - 企业 - 货品   每个企业自身的货品配件等
* Author: zhuqiang4433@gmail.com
* Version: CodeGenerator 1.1
* Memo: Auto Created by CodeGenerator on 2020/4/5.
*/

public class TDataEnterpriseGoodsModel extends BaseModel implements IBaseModel{
	 public static final String TABLE_NAME="T_DATA_ENTERPRISE_GOODS";
	 public static final String SCRIPT_INSERT="INSERT INTO t_data_enterprise_goods(goods_id,enterprise_id,name,unit_id,unit_id2,address,description,is_valid,create_time,last_edit_time,creation_user_id,last_edit_user_id,sale_price,batch_no,brand)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	 public static final String SCRIPT_DELETE="DELETE FROM t_data_enterprise_goods WHERE  goods_id=? ";
	 public static final String SCRIPT_UPDATE="UPDATE t_data_enterprise_goods  SET {0} WHERE  goods_id=? ";
	 public static final String SCRIPT_SELECT="SELECT goods_id,enterprise_id,name,unit_id,unit_id2,address,description,is_valid,create_time,last_edit_time,creation_user_id,last_edit_user_id,sale_price,batch_no,brand FROM t_data_enterprise_goods WHERE (1=1)";
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

	/*主计量单位*/ 
	protected String unit_id;
	public String getunit_id (){ return unit_id;}
	public void setunit_id(String value){
		this.unit_id=value;
 		this.onChangeProperty("unit_id",this.unit_id,value);
 	}

	/*辅计量单位*/ 
	protected String unit_id2;
	public String getunit_id2 (){ return unit_id2;}
	public void setunit_id2(String value){
		this.unit_id2=value;
 		this.onChangeProperty("unit_id2",this.unit_id2,value);
 	}

	/*地址*/ 
	protected String address;
	public String getaddress (){ return address;}
	public void setaddress(String value){
		this.address=value;
 		this.onChangeProperty("address",this.address,value);
 	}

	/*货品描述*/ 
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

	/*销售价格*/ 
	protected Double sale_price;
	public Double getsale_price (){ return sale_price;}
	public void setsale_price(Double value){
		this.sale_price=value;
 		this.onChangeProperty("sale_price",this.sale_price,value);
 	}

	/*批号*/ 
	protected String batch_no;
	public String getbatch_no (){ return batch_no;}
	public void setbatch_no(String value){
		this.batch_no=value;
 		this.onChangeProperty("batch_no",this.batch_no,value);
 	}

	/*品牌*/ 
	protected String brand;
	public String getbrand (){ return brand;}
	public void setbrand(String value){
		this.brand=value;
 		this.onChangeProperty("brand",this.brand,value);
 	}



	public TDataEnterpriseGoodsModel(){
		super();
		this._keyProperty.add("goods_id");
		initFieldsType();
	}

	private void initFieldsType(){
		this._fieldType.put("goods_id","String");
		this._fieldType.put("enterprise_id","String");
		this._fieldType.put("name","String");
		this._fieldType.put("unit_id","String");
		this._fieldType.put("unit_id2","String");
		this._fieldType.put("address","String");
		this._fieldType.put("description","String");
		this._fieldType.put("is_valid","Integer");
		this._fieldType.put("create_time","Date");
		this._fieldType.put("last_edit_time","Date");
		this._fieldType.put("creation_user_id","String");
		this._fieldType.put("last_edit_user_id","String");
		this._fieldType.put("sale_price","Double");
		this._fieldType.put("batch_no","String");
		this._fieldType.put("brand","String");
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
		 if(isExistColumn(resultSet,"enterprise_id")){
			 this.enterprise_id=resultSet.getString("enterprise_id");
		}
		 if(isExistColumn(resultSet,"name")){
			 this.name=resultSet.getString("name");
		}
		 if(isExistColumn(resultSet,"unit_id")){
			 this.unit_id=resultSet.getString("unit_id");
		}
		 if(isExistColumn(resultSet,"unit_id2")){
			 this.unit_id2=resultSet.getString("unit_id2");
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
		 if(isExistColumn(resultSet,"sale_price")){
			 this.sale_price=resultSet.getDouble("sale_price");
			if (resultSet.wasNull()) {
				this.sale_price=null;
			}
		}
		 if(isExistColumn(resultSet,"batch_no")){
			 this.batch_no=resultSet.getString("batch_no");
		}
		 if(isExistColumn(resultSet,"brand")){
			 this.brand=resultSet.getString("brand");
		}
      } catch (SQLException e) {
              e.printStackTrace();
          }
      }
  @Override
   public String getExist() {
	    String sql="select 1 from "+TABLE_NAME;
		    sql+=" where   goods_id="+Utility.getSqlFielStatement("string",this.goods_id)+"  ";
	    return sql;
   }


   @Override
   public String getInsert() {
       String sql="insert into "+TABLE_NAME+"(goods_id,enterprise_id,name,unit_id,unit_id2,address,description,is_valid,create_time,last_edit_time,creation_user_id,last_edit_user_id,sale_price,batch_no,brand) values("+Utility.getSqlFielStatement("string",this.goods_id)+","+Utility.getSqlFielStatement("string",this.enterprise_id)+","+Utility.getSqlFielStatement("string",this.name)+","+Utility.getSqlFielStatement("string",this.unit_id)+","+Utility.getSqlFielStatement("string",this.unit_id2)+","+Utility.getSqlFielStatement("string",this.address)+","+Utility.getSqlFielStatement("string",this.description)+","+this.is_valid+","+Utility.getSqlFielStatement("date",this.create_time)+","+Utility.getSqlFielStatement("date",this.last_edit_time)+","+Utility.getSqlFielStatement("string",this.creation_user_id)+","+Utility.getSqlFielStatement("string",this.last_edit_user_id)+","+this.sale_price+","+Utility.getSqlFielStatement("string",this.batch_no)+","+Utility.getSqlFielStatement("string",this.brand)+")";
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
   sql+= " where  goods_id="+Utility.getSqlFielStatement("string",this.goods_id)+" ";
   }
   return sql;
}


	@Override
	public String getSelect() {
		String sql="select goods_id,enterprise_id,name,unit_id,unit_id2,address,description,is_valid,create_time,last_edit_time,creation_user_id,last_edit_user_id,sale_price,batch_no,brand from "+TABLE_NAME;
		if(!Utility.isNullOrEmpty(goods_id)){
			sql+=" where  goods_id="+Utility.getSqlFielStatement("string",this.goods_id)+" ";
		}
		return sql;
	}
	@Override
	public String getSelectByCondition(String condition) {
		String sql="select goods_id,enterprise_id,name,unit_id,unit_id2,address,description,is_valid,create_time,last_edit_time,creation_user_id,last_edit_user_id,sale_price,batch_no,brand from "+TABLE_NAME;
		if(condition!=null&&condition!=""){
			sql+=" where "+condition;
		}
		return sql;
	}


    @Override
   public String getDelete() {
	 String sql="delete from "+TABLE_NAME+" where  goods_id="+Utility.getSqlFielStatement("string",this.goods_id)+" ";
	 return sql;
   }


}

