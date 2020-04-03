package com.joindoo.jdwechat.codeGen.beans;

import java.util.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.format.annotation.DateTimeFormat;

/**
* 系统 - 权限 - 系统模块（菜单）
* Author: zhuqiang4433@gmail.com
* Memo: Auto Created by CodeGenerator on 2020/3/24.
*/

@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class TXtQxXtmkDtoModel extends BaseDtoModel{
	/*系统模块，即菜单*/ 
	protected String xtmk_dm;
	public String getxtmk_dm (){ return xtmk_dm;}
	public void setxtmk_dm(String value){
		this.xtmk_dm=value;
  	}

	/*系统模块名称(可能是中文)*/ 
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

	/*功能点对应的URL*/ 
	protected String url;
	public String geturl (){ return url;}
	public void seturl(String value){
		this.url=value;
  	}

	/*位置序号*/ 
	protected Integer wz_px;
	public Integer getwz_px (){ return wz_px;}
	public void setwz_px(Integer value){
		this.wz_px=value;
  	}

	/*创建时间*/ 
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date lr_sj;
	public Date getlr_sj (){ return lr_sj;}
	public void setlr_sj(Date value){
		this.lr_sj=value;
  	}

	/*最后修改日期*/ 
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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

	/*是否有效*/ 
	protected Integer sfyx_bj;
	public Integer getsfyx_bj (){ return sfyx_bj;}
	public void setsfyx_bj(Integer value){
		this.sfyx_bj=value;
  	}

	/*上级系统模块代码*/ 
	protected String sj_xtmk_dm;
	public String getsj_xtmk_dm (){ return sj_xtmk_dm;}
	public void setsj_xtmk_dm(String value){
		this.sj_xtmk_dm=value;
  	}



}

