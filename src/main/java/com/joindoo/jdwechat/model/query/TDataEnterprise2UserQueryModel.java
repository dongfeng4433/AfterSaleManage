package com.joindoo.jdwechat.model.query;

import com.joindoo.jdwechat.model.query.BaseQueryModel;
import java.sql.Timestamp;
import java.util.Date;
import java.math.BigDecimal;

/**
* 主数据 - 企业 - 2 - 人员 企业人员关联表，一个人员可以是多个企业，一个企业也有多个人员
* Author: zhuqiang4433@gmail.com
* Memo: Auto Created by CodeGenerator on 2020/4/1.
*/

public class TDataEnterprise2UserQueryModel extends BaseQueryModel{
	/*企业id*/ 
	protected String enterprise_id;
	public String getenterprise_id (){ return enterprise_id;}
	public void setenterprise_id(String value){
		this.enterprise_id=value;
  	}

	/*人员id*/ 
	protected String user_id;
	public String getuser_id (){ return user_id;}
	public void setuser_id(String value){
		this.user_id=value;
  	}

	/*是否有效*/ 
	protected Integer is_valid;
	public Integer getis_valid (){ return is_valid;}
	public void setis_valid(Integer value){
		this.is_valid=value;
  	}

	/*用户人员类型（0 企业管理员 1 企业员工）*/ 
	protected String user_type_code;
	public String getuser_type_code (){ return user_type_code;}
	public void setuser_type_code(String value){
		this.user_type_code=value;
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

	/*当前进行订单id*/ 
	protected String current_order_id;
	public String getcurrent_order_id (){ return current_order_id;}
	public void setcurrent_order_id(String value){
		this.current_order_id=value;
  	}

	/*当前人员状态 1工作中 2 空闲 */ 
	protected String current_status;
	public String getcurrent_status (){ return current_status;}
	public void setcurrent_status(String value){
		this.current_status=value;
  	}

	private String user_ids;

	public String getUser_ids() {
		return user_ids;
	}

	public void setUser_ids(String user_ids) {
		this.user_ids = user_ids;
	}
}

