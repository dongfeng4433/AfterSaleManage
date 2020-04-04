package com.joindoo.jdwechat.codeGen.beans;

import java.util.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.format.annotation.DateTimeFormat;

/**
* 主数据 - 供应商 可以是个人也可以是企业
* Author: zhuqiang4433@gmail.com
* Version: CodeGenerator 1.1
* Memo: Auto Created by CodeGenerator on 2020/4/4.
*/

@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class TDataSupplierDtoModel extends BaseDtoModel{
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
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date create_time;
	public Date getcreate_time (){ return create_time;}
	public void setcreate_time(Date value){
		this.create_time=value;
  	}

	/*最后修改时间*/ 
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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

