package com.joindoo.jdwechat.codeGen.beans;

import java.util.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.format.annotation.DateTimeFormat;

/**
* 主数据 - 企业 - 库存每个企业自身的货品配件库存数据
* Author: zhuqiang4433@gmail.com
* Version: CodeGenerator 1.1
* Memo: Auto Created by CodeGenerator on 2020/4/5.
*/

@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class TDataEnterpriseStockDtoModel extends BaseDtoModel{
	/*库存序号*/ 
	protected String stock_id;
	public String getstock_id (){ return stock_id;}
	public void setstock_id(String value){
		this.stock_id=value;
  	}

	/*货品id*/ 
	protected String goods_id;
	public String getgoods_id (){ return goods_id;}
	public void setgoods_id(String value){
		this.goods_id=value;
  	}

	/*企业id*/ 
	protected String enterprise_id;
	public String getenterprise_id (){ return enterprise_id;}
	public void setenterprise_id(String value){
		this.enterprise_id=value;
  	}

	/*仓库id*/ 
	protected String warehouse_id;
	public String getwarehouse_id (){ return warehouse_id;}
	public void setwarehouse_id(String value){
		this.warehouse_id=value;
  	}

	/*名称*/ 
	protected String name;
	public String getname (){ return name;}
	public void setname(String value){
		this.name=value;
  	}

	/*主计量单位*/ 
	protected String unit_id;
	public String getunit_id (){ return unit_id;}
	public void setunit_id(String value){
		this.unit_id=value;
  	}

	/*主数量*/ 
	protected Double quantity;
	public Double getquantity (){ return quantity;}
	public void setquantity(Double value){
		this.quantity=value;
  	}

	/*辅计量单位*/ 
	protected String unit_id2;
	public String getunit_id2 (){ return unit_id2;}
	public void setunit_id2(String value){
		this.unit_id2=value;
  	}

	/*辅数量*/ 
	protected Double quantity2;
	public Double getquantity2 (){ return quantity2;}
	public void setquantity2(Double value){
		this.quantity2=value;
  	}

	/*地址*/ 
	protected String address;
	public String getaddress (){ return address;}
	public void setaddress(String value){
		this.address=value;
  	}

	/*描述*/ 
	protected String description;
	public String getdescription (){ return description;}
	public void setdescription(String value){
		this.description=value;
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

	/*单价*/ 
	protected Double price;
	public Double getprice (){ return price;}
	public void setprice(Double value){
		this.price=value;
  	}

	/*金额*/ 
	protected Double amount;
	public Double getamount (){ return amount;}
	public void setamount(Double value){
		this.amount=value;
  	}

	/*主计量单位*/ 
	protected String unit_name;
	public String getunit_name (){ return unit_name;}
	public void setunit_name(String value){
		this.unit_name=value;
  	}

	/*辅计量单位*/ 
	protected String unit2_name;
	public String getunit2_name (){ return unit2_name;}
	public void setunit2_name(String value){
		this.unit2_name=value;
  	}



}

