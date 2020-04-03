package com.joindoo.jdwechat.codeGen.beans;

import java.util.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
* 
* Author: zhuqiang4433@gmail.com
* Memo: Auto Created by CodeGenerator on 2018/8/8.
*/

@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class TSysUserinfoDtoModel extends BaseDtoModel{
	/*序号*/ 
	protected String id;
	public String getid (){ return id;}
	public void setid(String value){
		this.id=value;
  	}

	/*昵称*/ 
	protected String username;
	public String getusername (){ return username;}
	public void setusername(String value){
		this.username=value;
  	}

	/*密码*/ 
	protected String password;
	public String getpassword (){ return password;}
	public void setpassword(String value){
		this.password=value;
  	}

	/*加密盐值*/ 
	protected String salt;
	public String getsalt (){ return salt;}
	public void setsalt(String value){
		this.salt=value;
  	}

	/*创建时间*/ 
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date create_time;
	public Date getcreate_time (){ return create_time;}
	public void setcreate_time(Date value){
		this.create_time=value;
  	}

	/*修改时间*/ 
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date edit_time;
	public Date getedit_time (){ return edit_time;}
	public void setedit_time(Date value){
		this.edit_time=value;
  	}

	/*是否有效*/ 
	protected Integer is_valid;
	public Integer getis_valid (){ return is_valid;}
	public void setis_valid(Integer value){
		this.is_valid=value;
  	}



}

