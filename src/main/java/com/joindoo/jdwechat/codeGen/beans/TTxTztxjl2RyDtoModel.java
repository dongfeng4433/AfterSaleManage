package com.joindoo.jdwechat.codeGen.beans;

import java.util.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
* 通讯 - 通知提醒记录 - 2 - 人员， 记录通知的人员信息
* Author: zhuqiang4433@gmail.com
* Memo: Auto Created by CodeGenerator on 2018/8/30.
*/

@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class TTxTztxjl2RyDtoModel extends BaseDtoModel{
	/*提醒记录序号*/ 
	protected String tztxjl_xh;
	public String gettztxjl_xh (){ return tztxjl_xh;}
	public void settztxjl_xh(String value){
		this.tztxjl_xh=value;
  	}

	/*人员*/ 
	protected String ry_xh;
	public String getry_xh (){ return ry_xh;}
	public void setry_xh(String value){
		this.ry_xh=value;
  	}

	/*处理时间*/ 
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date cl_sj;
	public Date getcl_sj (){ return cl_sj;}
	public void setcl_sj(Date value){
		this.cl_sj=value;
  	}

	/*处理状态代码1已处理*/ 
	protected String clzt_dm;
	public String getclzt_dm (){ return clzt_dm;}
	public void setclzt_dm(String value){
		this.clzt_dm=value;
  	}

	/*处理内容*/ 
	protected String cl_nr;
	public String getcl_nr (){ return cl_nr;}
	public void setcl_nr(String value){
		this.cl_nr=value;
  	}

	/*创建时间*/ 
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date lr_sj;
	public Date getlr_sj (){ return lr_sj;}
	public void setlr_sj(Date value){
		this.lr_sj=value;
  	}

	/*最后修改时间*/ 
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date xg_sj;
	public Date getxg_sj (){ return xg_sj;}
	public void setxg_sj(Date value){
		this.xg_sj=value;
  	}

	/*录入人员*/ 
	protected String lrry_xh;
	public String getlrry_xh (){ return lrry_xh;}
	public void setlrry_xh(String value){
		this.lrry_xh=value;
  	}

	/*(外键:系统用户) 最后修改人*/ 
	protected String xgry_xh;
	public String getxgry_xh (){ return xgry_xh;}
	public void setxgry_xh(String value){
		this.xgry_xh=value;
  	}

	/**/ 
	protected Integer sfyx_bj;
	public Integer getsfyx_bj (){ return sfyx_bj;}
	public void setsfyx_bj(Integer value){
		this.sfyx_bj=value;
  	}



}

