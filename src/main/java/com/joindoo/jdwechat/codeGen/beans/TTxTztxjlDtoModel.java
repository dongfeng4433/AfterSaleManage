package com.joindoo.jdwechat.codeGen.beans;

import java.util.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
* 通讯 - 通知提醒记录， 除工作流外的提醒记录，便于提醒, 用于系统内提醒, 与移动终端提醒不相同，这是所有提醒通知的源
* Author: zhuqiang4433@gmail.com
* Memo: Auto Created by CodeGenerator on 2018/8/30.
*/

@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class TTxTztxjlDtoModel extends BaseDtoModel{
	/*提醒记录序号*/ 
	protected String tztxjl_xh;
	public String gettztxjl_xh (){ return tztxjl_xh;}
	public void settztxjl_xh(String value){
		this.tztxjl_xh=value;
  	}

	/*标题*/ 
	protected String bt;
	public String getbt (){ return bt;}
	public void setbt(String value){
		this.bt=value;
  	}

	/*内容*/ 
	protected String tx_nr;
	public String gettx_nr (){ return tx_nr;}
	public void settx_nr(String value){
		this.tx_nr=value;
  	}

	/*通知人员数量*/ 
	protected Integer tzry_sl;
	public Integer gettzry_sl (){ return tzry_sl;}
	public void settzry_sl(Integer value){
		this.tzry_sl=value;
  	}

	/*有效时间，如果时间到了则自动清除本记录*/ 
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date sx_sj;
	public Date getsx_sj (){ return sx_sj;}
	public void setsx_sj(Date value){
		this.sx_sj=value;
  	}

	/*发起人员序号*/ 
	protected String fqry_xh;
	public String getfqry_xh (){ return fqry_xh;}
	public void setfqry_xh(String value){
		this.fqry_xh=value;
  	}

	/*相关记录序号*/ 
	protected String xgjl_zj_xh;
	public String getxgjl_zj_xh (){ return xgjl_zj_xh;}
	public void setxgjl_zj_xh(String value){
		this.xgjl_zj_xh=value;
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

	/*备注*/ 
	protected String bz;
	public String getbz (){ return bz;}
	public void setbz(String value){
		this.bz=value;
  	}

	/*处理状态代码1已处理*/ 
	protected String clzt_dm;
	public String getclzt_dm (){ return clzt_dm;}
	public void setclzt_dm(String value){
		this.clzt_dm=value;
  	}



}

