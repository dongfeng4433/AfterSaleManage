package com.joindoo.jdwechat.model.query;

import java.util.Date;
import java.math.BigDecimal;

/**
* 业务_信息录入
* Author: zhuqiang4433@gmail.com
* Memo: Auto Created by CodeGenerator on 2018/9/18.
*/

public class TYwXxlrQueryModel extends BaseQueryModel{
	/*序号*/ 
	protected String xxlr_xh;
	public String getxxlr_xh (){ return xxlr_xh;}
	public void setxxlr_xh(String value){
		this.xxlr_xh=value;
  	}

	/*姓名*/ 
	protected String ry_name;
	public String getry_name (){ return ry_name;}
	public void setry_name(String value){
		this.ry_name=value;
  	}

	/*证件号码*/ 
	protected String ry_zj_hm;
	public String getry_zj_hm (){ return ry_zj_hm;}
	public void setry_zj_hm(String value){
		this.ry_zj_hm=value;
  	}

	/*内容*/ 
	protected String sqnr;
	public String getsqnr (){ return sqnr;}
	public void setsqnr(String value){
		this.sqnr=value;
  	}

	/*是否有效标记*/ 
	protected Integer sfyx_bj;
	public Integer getsfyx_bj (){ return sfyx_bj;}
	public void setsfyx_bj(Integer value){
		this.sfyx_bj=value;
  	}

	/*附件*/ 
	protected String fj_xh;
	public String getfj_xh (){ return fj_xh;}
	public void setfj_xh(String value){
		this.fj_xh=value;
  	}

	/*设定需要回复的时间*/ 
	protected Date hf_sj;
	public Date gethf_sj (){ return hf_sj;}
	public void sethf_sj(Date value){
		this.hf_sj=value;
  	}

	/*上级信息录入序号*/ 
	protected String sj_xxlr_xh;
	public String getsj_xxlr_xh (){ return sj_xxlr_xh;}
	public void setsj_xxlr_xh(String value){
		this.sj_xxlr_xh=value;
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
	protected Date lr_sj;
	public Date getlr_sj (){ return lr_sj;}
	public void setlr_sj(Date value){
		this.lr_sj=value;
  	}

	/**/ 
	protected Date xg_sj;
	public Date getxg_sj (){ return xg_sj;}
	public void setxg_sj(Date value){
		this.xg_sj=value;
  	}

	/*用于查询展示*/ 
	protected String zt_dm;
	public String getzt_dm (){ return zt_dm;}
	public void setzt_dm(String value){
		this.zt_dm=value;
  	}



}

