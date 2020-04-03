package com.joindoo.jdwechat.codeGen.beans;

import java.util.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.format.annotation.DateTimeFormat;

/**
* 通讯 - 短信 - 短信发送记录
* Author: zhuqiang4433@gmail.com
* Memo: Auto Created by CodeGenerator on 2018/9/12.
*/

@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class TTxDxDxfsjlDtoModel extends BaseDtoModel{
	/*短信验证码*/ 
	protected String dxfsjl_xh;
	public String getdxfsjl_xh (){ return dxfsjl_xh;}
	public void setdxfsjl_xh(String value){
		this.dxfsjl_xh=value;
  	}

	/*手机号码*/ 
	protected String sj_hm;
	public String getsj_hm (){ return sj_hm;}
	public void setsj_hm(String value){
		this.sj_hm=value;
  	}

	/*内容*/ 
	protected String nr;
	public String getnr (){ return nr;}
	public void setnr(String value){
		this.nr=value;
  	}

	/*1艾特的通知2自动发送的通知*/ 
	protected String fs_type;
	public String getfs_type (){ return fs_type;}
	public void setfs_type(String value){
		this.fs_type=value;
  	}

	/*录入时间*/ 
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date lr_sj;
	public Date getlr_sj (){ return lr_sj;}
	public void setlr_sj(Date value){
		this.lr_sj=value;
  	}



}

