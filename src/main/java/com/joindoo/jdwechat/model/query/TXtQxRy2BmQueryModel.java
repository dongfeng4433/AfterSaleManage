package com.joindoo.jdwechat.model.query;

import java.util.Date;
import java.math.BigDecimal;

/**
* 系统 - 权限 - 系统用户 - 2 - 部门
* Author: zhuqiang4433@gmail.com
* Memo: Auto Created by CodeGenerator on 2018/9/18.
*/

public class TXtQxRy2BmQueryModel extends BaseQueryModel{
	/*(外键:系统用户) 系统用户*/ 
	protected String ry_xh;
	public String getry_xh (){ return ry_xh;}
	public void setry_xh(String value){
		this.ry_xh=value;
  	}

	/*部门代码*/ 
	protected String bm_dm;
	public String getbm_dm (){ return bm_dm;}
	public void setbm_dm(String value){
		this.bm_dm=value;
  	}

	/*是否该部门管理人员*/ 
	protected Integer sfbm_gl;
	public Integer getsfbm_gl (){ return sfbm_gl;}
	public void setsfbm_gl(Integer value){
		this.sfbm_gl=value;
  	}

	/*描述*/ 
	protected String ms;
	public String getms (){ return ms;}
	public void setms(String value){
		this.ms=value;
  	}

	/*创建时间*/ 
	protected Date lr_sj;
	public Date getlr_sj (){ return lr_sj;}
	public void setlr_sj(Date value){
		this.lr_sj=value;
  	}

	/*最后修改时间*/ 
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

