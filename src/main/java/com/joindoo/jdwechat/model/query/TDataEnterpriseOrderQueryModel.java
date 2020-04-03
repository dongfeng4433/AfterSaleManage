package com.joindoo.jdwechat.model.query;

import com.joindoo.jdwechat.model.query.BaseQueryModel;
import java.sql.Timestamp;
import java.util.Date;
import java.math.BigDecimal;

/**
* 主数据 - 企业 - 工单 
* Author: zhuqiang4433@gmail.com
* Memo: Auto Created by CodeGenerator on 2020/3/28.
*/

public class TDataEnterpriseOrderQueryModel extends BaseQueryModel{
	/*工单id*/ 
	protected String order_id;
	public String getorder_id (){ return order_id;}
	public void setorder_id(String value){
		this.order_id=value;
  	}

	/*工单编号(SH2003220001)*/ 
	protected String order_no;
	public String getorder_no (){ return order_no;}
	public void setorder_no(String value){
		this.order_no=value;
  	}

	/*企业id*/ 
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

	/*客户id*/ 
	protected String customer_id;
	public String getcustomer_id (){ return customer_id;}
	public void setcustomer_id(String value){
		this.customer_id=value;
  	}

	/*地址*/ 
	protected String address;
	public String getaddress (){ return address;}
	public void setaddress(String value){
		this.address=value;
  	}

	/*工单简介描述*/ 
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

	/*接单时间*/ 
	protected Date start_time;
	public Date getstart_time (){ return start_time;}
	public void setstart_time(Date value){
		this.start_time=value;
  	}

	/*完成时间*/ 
	protected Date complete_time;
	public Date getcomplete_time (){ return complete_time;}
	public void setcomplete_time(Date value){
		this.complete_time=value;
  	}

	/*计划完成时间*/ 
	protected Date plan_complete_time;
	public Date getplan_complete_time (){ return plan_complete_time;}
	public void setplan_complete_time(Date value){
		this.plan_complete_time=value;
  	}

	/*工单接单人员*/ 
	protected String work_user_id;
	public String getwork_user_id (){ return work_user_id;}
	public void setwork_user_id(String value){
		this.work_user_id=value;
  	}

	/*工单状态(0 待分配 1作业中 2中断 3完成 9作废)*/ 
	protected String order_status;
	public String getorder_status (){ return order_status;}
	public void setorder_status(String value){
		this.order_status=value;
  	}

	/*配件金额*/ 
	protected Double goods_amount;
	public Double getgoods_amount (){ return goods_amount;}
	public void setgoods_amount(Double value){
		this.goods_amount=value;
  	}



}

