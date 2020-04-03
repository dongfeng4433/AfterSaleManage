package com.joindoo.jdwechat.model.query;

import java.util.Date;
import java.math.BigDecimal;

/**
* 诉求办理 - 工单基本信息
* Author: zhuqiang4433@gmail.com
* Memo: Auto Created by CodeGenerator on 2018/8/22.
*/

public class TSqblGdjbxxQueryModel extends BaseQueryModel{
	/*序号*/ 
	protected String id;
	public String getid (){ return id;}
	public void setid(String value){
		this.id=value;
  	}

	/*回访号码*/ 
	protected String hfhm;
	public String gethfhm (){ return hfhm;}
	public void sethfhm(String value){
		this.hfhm=value;
  	}

	/*工单编号*/ 
	protected String gdbh;
	public String getgdbh (){ return gdbh;}
	public void setgdbh(String value){
		this.gdbh=value;
  	}

	/*诉求类型（咨询、执法类）*/ 
	protected String sqlx_dm;
	public String getsqlx_dm (){ return sqlx_dm;}
	public void setsqlx_dm(String value){
		this.sqlx_dm=value;
  	}

	/*诉求人姓名*/ 
	protected String sqrxm;
	public String getsqrxm (){ return sqrxm;}
	public void setsqrxm(String value){
		this.sqrxm=value;
  	}

	/*诉求事件发生时间*/ 
	protected Date sqsjfssj;
	public Date getsqsjfssj (){ return sqsjfssj;}
	public void setsqsjfssj(Date value){
		this.sqsjfssj=value;
  	}

	/*诉求人号码*/ 
	protected String sqrhm;
	public String getsqrhm (){ return sqrhm;}
	public void setsqrhm(String value){
		this.sqrhm=value;
  	}

	/*诉求内容*/ 
	protected String sqnr;
	public String getsqnr (){ return sqnr;}
	public void setsqnr(String value){
		this.sqnr=value;
  	}

	/*诉求人地址*/ 
	protected String sqrdz;
	public String getsqrdz (){ return sqrdz;}
	public void setsqrdz(String value){
		this.sqrdz=value;
  	}

	/*录入时间*/ 
	protected Date lr_sj;
	public Date getlr_sj (){ return lr_sj;}
	public void setlr_sj(Date value){
		this.lr_sj=value;
  	}

	/*保密*/ 
	protected Byte sfbm_bj;
	public Byte getsfbm_bj (){ return sfbm_bj;}
	public void setsfbm_bj(Byte value){
		this.sfbm_bj=value;
  	}

	/*诉求事件发生地*/ 
	protected String sqsjfsdz;
	public String getsqsjfsdz (){ return sqsjfsdz;}
	public void setsqsjfsdz(String value){
		this.sqsjfsdz=value;
  	}

	/*是否回访*/ 
	protected Byte sfhf_bj;
	public Byte getsfhf_bj (){ return sfhf_bj;}
	public void setsfhf_bj(Byte value){
		this.sfhf_bj=value;
  	}

	/*微信号*/ 
	protected String wxh;
	public String getwxh (){ return wxh;}
	public void setwxh(String value){
		this.wxh=value;
  	}

	/*同步标记*/ 
	protected Byte tb_bj;
	public Byte gettb_bj (){ return tb_bj;}
	public void settb_bj(Byte value){
		this.tb_bj=value;
  	}

	/*同步时间*/ 
	protected Date tb_sj;
	public Date gettb_sj (){ return tb_sj;}
	public void settb_sj(Date value){
		this.tb_sj=value;
  	}

	/*街道名称*/ 
	protected String jd_mc;
	public String getjd_mc (){ return jd_mc;}
	public void setjd_mc(String value){
		this.jd_mc=value;
  	}

	/*社区名称*/ 
	protected String sq_mc;
	public String getsq_mc (){ return sq_mc;}
	public void setsq_mc(String value){
		this.sq_mc=value;
  	}

	/*微信群名称*/ 
	protected String wxq_mc;
	public String getwxq_mc (){ return wxq_mc;}
	public void setwxq_mc(String value){
		this.wxq_mc=value;
  	}



}

