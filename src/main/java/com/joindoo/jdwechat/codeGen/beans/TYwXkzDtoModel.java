package com.joindoo.jdwechat.codeGen.beans;

import java.util.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.format.annotation.DateTimeFormat;

/**
* 业务_许可证   1 人力资源服务许可证  2 劳务派遣经营许可证
* Author: zhuqiang4433@gmail.com
* Memo: Auto Created by CodeGenerator on 2019/8/2.
*/

@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class TYwXkzDtoModel extends BaseDtoModel{
	/*序号*/ 
	protected String xkz_xh;
	public String getxkz_xh (){ return xkz_xh;}
	public void setxkz_xh(String value){
		this.xkz_xh=value;
  	}

	/*类型代码 1 人力资源服务许可证  2 劳务派遣经营许可证*/ 
	protected String lx_dm;
	public String getlx_dm (){ return lx_dm;}
	public void setlx_dm(String value){
		this.lx_dm=value;
  	}

	/*许可证编号*/ 
	protected String xkz_bh;
	public String getxkz_bh (){ return xkz_bh;}
	public void setxkz_bh(String value){
		this.xkz_bh=value;
  	}

	/*机构单位名称*/ 
	protected String jgdw_mc;
	public String getjgdw_mc (){ return jgdw_mc;}
	public void setjgdw_mc(String value){
		this.jgdw_mc=value;
  	}

	/*统一社会信用代码*/ 
	protected String tyshxydm;
	public String gettyshxydm (){ return tyshxydm;}
	public void settyshxydm(String value){
		this.tyshxydm=value;
  	}

	/*地址住所*/ 
	protected String dzzs;
	public String getdzzs (){ return dzzs;}
	public void setdzzs(String value){
		this.dzzs=value;
  	}

	/*法定代表人负责人*/ 
	protected String fddbr_fzr;
	public String getfddbr_fzr (){ return fddbr_fzr;}
	public void setfddbr_fzr(String value){
		this.fddbr_fzr=value;
  	}

	/*注册资本（万元）*/ 
	protected Double zczb_je;
	public Double getzczb_je (){ return zczb_je;}
	public void setzczb_je(Double value){
		this.zczb_je=value;
  	}

	/*机构性质(民营企业)*/ 
	protected String jgxz;
	public String getjgxz (){ return jgxz;}
	public void setjgxz(String value){
		this.jgxz=value;
  	}

	/*许可文号*/ 
	protected String xkwh;
	public String getxkwh (){ return xkwh;}
	public void setxkwh(String value){
		this.xkwh=value;
  	}

	/*服务范围*/ 
	protected String fwfw;
	public String getfwfw (){ return fwfw;}
	public void setfwfw(String value){
		this.fwfw=value;
  	}

	/*发证日期*/ 
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date fzrq;
	public Date getfzrq (){ return fzrq;}
	public void setfzrq(Date value){
		this.fzrq=value;
  	}

	/*有效日期——截止*/ 
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date yxrq_jz;
	public Date getyxrq_jz (){ return yxrq_jz;}
	public void setyxrq_jz(Date value){
		this.yxrq_jz=value;
  	}

	/*有效日期-起始*/ 
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date yxrq_qs;
	public Date getyxrq_qs (){ return yxrq_qs;}
	public void setyxrq_qs(Date value){
		this.yxrq_qs=value;
  	}

	/*发证机构*/ 
	protected String fzjg;
	public String getfzjg (){ return fzjg;}
	public void setfzjg(String value){
		this.fzjg=value;
  	}

	/*附件*/ 
	protected String fj_xh;
	public String getfj_xh (){ return fj_xh;}
	public void setfj_xh(String value){
		this.fj_xh=value;
  	}

	/*是否有效标记*/ 
	protected Integer sfyx_bj;
	public Integer getsfyx_bj (){ return sfyx_bj;}
	public void setsfyx_bj(Integer value){
		this.sfyx_bj=value;
  	}

	/*描述*/ 
	protected String ms;
	public String getms (){ return ms;}
	public void setms(String value){
		this.ms=value;
  	}

	/*备注*/ 
	protected String bz;
	public String getbz (){ return bz;}
	public void setbz(String value){
		this.bz=value;
  	}

	/*录入人员序号*/ 
	protected String lrry_xh;
	public String getlrry_xh (){ return lrry_xh;}
	public void setlrry_xh(String value){
		this.lrry_xh=value;
  	}

	/*修改人员序号*/ 
	protected String xgry_xh;
	public String getxgry_xh (){ return xgry_xh;}
	public void setxgry_xh(String value){
		this.xgry_xh=value;
  	}

	/**/ 
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date lr_sj;
	public Date getlr_sj (){ return lr_sj;}
	public void setlr_sj(Date value){
		this.lr_sj=value;
  	}

	/**/ 
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date xg_sj;
	public Date getxg_sj (){ return xg_sj;}
	public void setxg_sj(Date value){
		this.xg_sj=value;
  	}

	/*劳保证号*/ 
	protected String lbzh;
	public String getlbzh (){ return lbzh;}
	public void setlbzh(String value){
		this.lbzh=value;
  	}

	/*联系电话*/ 
	protected String lxdh;
	public String getlxdh (){ return lxdh;}
	public void setlxdh(String value){
		this.lxdh=value;
  	}



}

