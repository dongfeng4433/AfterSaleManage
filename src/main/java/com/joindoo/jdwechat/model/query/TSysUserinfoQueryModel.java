package com.joindoo.jdwechat.model.query;

import java.util.Date;
import java.math.BigDecimal;

/**
* 
* Author: zhuqiang4433@gmail.com
* Memo: Auto Created by CodeGenerator on 2018/8/8.
*/

public class TSysUserinfoQueryModel extends BaseQueryModel{
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
	protected Date create_time;
	public Date getcreate_time (){ return create_time;}
	public void setcreate_time(Date value){
		this.create_time=value;
  	}

	/*修改时间*/ 
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

