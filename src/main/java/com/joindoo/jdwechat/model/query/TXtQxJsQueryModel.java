package com.joindoo.jdwechat.model.query;

import com.joindoo.jdwechat.model.query.BaseQueryModel;
import java.sql.Timestamp;
import java.util.Date;
import java.math.BigDecimal;

/**
* 系统 - 权限 - 系统角色
* Author: zhuqiang4433@gmail.com
* Memo: Auto Created by CodeGenerator on 2020/4/3.
*/

public class TXtQxJsQueryModel extends BaseQueryModel{
	/*标识(一般为32位GUID)*/ 
	protected String js_dm;
	public String getjs_dm (){ return js_dm;}
	public void setjs_dm(String value){
		this.js_dm=value;
  	}

	/*角色名称*/ 
	protected String mc;
	public String getmc (){ return mc;}
	public void setmc(String value){
		this.mc=value;
  	}

	/*描述*/ 
	protected String ms;
	public String getms (){ return ms;}
	public void setms(String value){
		this.ms=value;
  	}

	/*位置排序*/ 
	protected Integer wz_px;
	public Integer getwz_px (){ return wz_px;}
	public void setwz_px(Integer value){
		this.wz_px=value;
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

	/*拥有者序号（企业id）*/ 
	protected String yyz_xh;
	public String getyyz_xh (){ return yyz_xh;}
	public void setyyz_xh(String value){
		this.yyz_xh=value;
  	}



}

