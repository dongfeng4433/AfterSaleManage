package com.joindoo.jdwechat.codeGen.beans;

import java.util.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
* 系统 - 权限 - 系统用户 - 2 - 系统模块
* Author: zhuqiang4433@gmail.com
* Memo: Auto Created by CodeGenerator on 2018/9/5.
*/

@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class TXtQxRy2XtmkDtoModel extends BaseDtoModel{
	/*(外键:系统用户) 系统用户*/ 
	protected String ry_xh;
	public String getry_xh (){ return ry_xh;}
	public void setry_xh(String value){
		this.ry_xh=value;
  	}

	/*(外键:系统角色) 系统角色*/ 
	protected String xtmk_dm;
	public String getxtmk_dm (){ return xtmk_dm;}
	public void setxtmk_dm(String value){
		this.xtmk_dm=value;
  	}

	/*是否为固定的，即手动添加的，而不是通过角色计算的, 如果是通过角色计算的那么在角色发生变更时将会重新计算和设置*/ 
	protected Integer sfsdtj_bj;
	public Integer getsfsdtj_bj (){ return sfsdtj_bj;}
	public void setsfsdtj_bj(Integer value){
		this.sfsdtj_bj=value;
  	}

	/*是否禁用标记，代表对于该用户，该功能点是禁用的，注意禁用优先*/ 
	protected Integer sfjy_bj;
	public Integer getsfjy_bj (){ return sfjy_bj;}
	public void setsfjy_bj(Integer value){
		this.sfjy_bj=value;
  	}

	/*描述*/ 
	protected String ms;
	public String getms (){ return ms;}
	public void setms(String value){
		this.ms=value;
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

	/*有效标记(0无效，1有效)*/ 
	protected Integer sfyx_bj;
	public Integer getsfyx_bj (){ return sfyx_bj;}
	public void setsfyx_bj(Integer value){
		this.sfyx_bj=value;
  	}



}

