package com.joindoo.jdwechat.model.query;

import com.joindoo.jdwechat.model.query.BaseQueryModel;
import java.sql.Timestamp;
import java.util.Date;
import java.math.BigDecimal;

/**
* 主数据 - 供应商 可以是个人也可以是企业
* Author: zhuqiang4433@gmail.com
* Memo: Auto Created by CodeGenerator on 2020/4/4.
*/

public class TDataSupplierQueryModel extends BaseQueryModel{
	/*供应商id*/ 
	protected String supplier_id;
	public String getsupplier_id (){ return supplier_id;}
	public void setsupplier_id(String value){
		this.supplier_id=value;
  	}

	/*企业id（客户由本企业自己维护）*/ 
	protected String enterprise_id;
	public String getenterprise_id (){ return enterprise_id;}
	public void setenterprise_id(String value){
		this.enterprise_id=value;
  	}

	/*名称*/ 
	protected String name;
	public String getname (){ return name;}
	public void setname(String value){
		this.name=value;
  	}

	/*简称*/ 
	protected String short_name;
	public String getshort_name (){ return short_name;}
	public void setshort_name(String value){
		this.short_name=value;
  	}

	/*地址*/ 
	protected String address;
	public String getaddress (){ return address;}
	public void setaddress(String value){
		this.address=value;
  	}

	/*类型编码 1 企业 2 个人 3 事业单位 9其他*/ 
	protected String type_code;
	public String gettype_code (){ return type_code;}
	public void settype_code(String value){
		this.type_code=value;
  	}

	/*客户介绍*/ 
	protected String description;
	public String getdescription (){ return description;}
	public void setdescription(String value){
		this.description=value;
  	}

	/*联系电话*/ 
	protected String telephone_number;
	public String gettelephone_number (){ return telephone_number;}
	public void settelephone_number(String value){
		this.telephone_number=value;
  	}

	/*是否有效*/ 
	protected Integer is_valid;
	public Integer getis_valid (){ return is_valid;}
	public void setis_valid(Integer value){
		this.is_valid=value;
  	}

	/*创建时间*/ 
	protected Date create_time;
	public Date getcreate_time (){ return create_time;}
	public void setcreate_time(Date value){
		this.create_time=value;
  	}

	/*最后修改时间*/ 
	protected Date last_edit_time;
	public Date getlast_edit_time (){ return last_edit_time;}
	public void setlast_edit_time(Date value){
		this.last_edit_time=value;
  	}

	/*创建人*/ 
	protected String creation_user_id;
	public String getcreation_user_id (){ return creation_user_id;}
	public void setcreation_user_id(String value){
		this.creation_user_id=value;
  	}

	/*最后修改人*/ 
	protected String last_edit_user_id;
	public String getlast_edit_user_id (){ return last_edit_user_id;}
	public void setlast_edit_user_id(String value){
		this.last_edit_user_id=value;
  	}



}

