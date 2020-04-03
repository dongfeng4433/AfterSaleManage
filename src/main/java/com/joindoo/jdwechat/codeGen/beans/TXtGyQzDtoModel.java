package com.joindoo.jdwechat.codeGen.beans;

import java.util.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.format.annotation.DateTimeFormat;

/**
* 系统 - 公用-群组
* Author: zhuqiang4433@gmail.com
* Memo: Auto Created by CodeGenerator on 2018/11/18.
*/

@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class TXtGyQzDtoModel extends BaseDtoModel{
	/*群组序号*/ 
	protected String group_id;
	public String getgroup_id (){ return group_id;}
	public void setgroup_id(String value){
		this.group_id=value;
  	}

	/*群组名称*/ 
	protected String group_name;
	public String getgroup_name (){ return group_name;}
	public void setgroup_name(String value){
		this.group_name=value;
  	}

	/*社区序号*/ 
	protected String community_id;
	public String getcommunity_id (){ return community_id;}
	public void setcommunity_id(String value){
		this.community_id=value;
  	}

	/*街道id*/ 
	protected String street_id;
	public String getstreet_id (){ return street_id;}
	public void setstreet_id(String value){
		this.street_id=value;
  	}

	/*街道名称*/ 
	protected String street_name;
	public String getstreet_name (){ return street_name;}
	public void setstreet_name(String value){
		this.street_name=value;
  	}

	/*社区名称*/ 
	protected String community_name;
	public String getcommunity_name (){ return community_name;}
	public void setcommunity_name(String value){
		this.community_name=value;
  	}

	/*创建时间*/ 
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date create_time;
	public Date getcreate_time (){ return create_time;}
	public void setcreate_time(Date value){
		this.create_time=value;
  	}

	/*是否有效*/ 
	protected Integer is_valid;
	public Integer getis_valid (){ return is_valid;}
	public void setis_valid(Integer value){
		this.is_valid=value;
  	}



}

