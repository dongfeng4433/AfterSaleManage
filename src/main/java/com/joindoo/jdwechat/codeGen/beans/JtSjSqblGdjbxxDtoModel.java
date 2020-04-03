package com.joindoo.jdwechat.codeGen.beans;

import java.util.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
* 数据——诉求办理——工单基本信息
* Author: zhuqiang4433@gmail.com
* Memo: Auto Created by CodeGenerator on 2018/6/8.
*/

@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class JtSjSqblGdjbxxDtoModel extends BaseDtoModel{
	/**/ 
	protected String id;
	public String getid (){ return id;}
	public void setid(String value){
		this.id=value;
  	}

	/*工单编号*/ 
	protected String gdbh;
	public String getgdbh (){ return gdbh;}
	public void setgdbh(String value){
		this.gdbh=value;
  	}

	/*诉求目的（即工单标题）*/ 
	protected String sqmd;
	public String getsqmd (){ return sqmd;}
	public void setsqmd(String value){
		this.sqmd=value;
  	}

	/*诉求内容*/ 
	protected String sqnr;
	public String getsqnr (){ return sqnr;}
	public void setsqnr(String value){
		this.sqnr=value;
  	}

	/*诉求类型（咨询、执法类）*/ 
	protected String sqlx;
	public String getsqlx (){ return sqlx;}
	public void setsqlx(String value){
		this.sqlx=value;
  	}

	/*回访类型（正常回访、无需回访）*/ 
	protected String hflx;
	public String gethflx (){ return hflx;}
	public void sethflx(String value){
		this.hflx=value;
  	}

	/*关键字*/ 
	protected String gjz;
	public String getgjz (){ return gjz;}
	public void setgjz(String value){
		this.gjz=value;
  	}

	/*三级归口*/ 
	protected String sjgk;
	public String getsjgk (){ return sjgk;}
	public void setsjgk(String value){
		this.sjgk=value;
  	}

	/*诉求号码*/ 
	protected String sqhm;
	public String getsqhm (){ return sqhm;}
	public void setsqhm(String value){
		this.sqhm=value;
  	}

	/*诉求人*/ 
	protected String sqr;
	public String getsqr (){ return sqr;}
	public void setsqr(String value){
		this.sqr=value;
  	}

	/*诉求来电时间*/ 
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date ldsj;
	public Date getldsj (){ return ldsj;}
	public void setldsj(Date value){
		this.ldsj=value;
  	}

	/* 诉求事件发生日期*/ 
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date sqsjfssj;
	public Date getsqsjfssj (){ return sqsjfssj;}
	public void setsqsjfssj(Date value){
		this.sqsjfssj=value;
  	}

	/*诉求地址*/ 
	protected String sqdz;
	public String getsqdz (){ return sqdz;}
	public void setsqdz(String value){
		this.sqdz=value;
  	}

	/*是否紧急*/ 
	protected String sfjj;
	public String getsfjj (){ return sfjj;}
	public void setsfjj(String value){
		this.sfjj=value;
  	}

	/*是否保密*/ 
	protected String sfbm;
	public String getsfbm (){ return sfbm;}
	public void setsfbm(String value){
		this.sfbm=value;
  	}

	/*同步标记，1：已同步，0或空：未同步*/ 
	protected Integer tbbj;
	public Integer gettbbj (){ return tbbj;}
	public void settbbj(Integer value){
		this.tbbj=value;
  	}

	/*抓取中间量*/ 
	protected String guid;
	public String getguid (){ return guid;}
	public void setguid(String value){
		this.guid=value;
  	}

	/*（园区单位等id）*/ 
	protected String cbdw;
	public String getcbdw (){ return cbdw;}
	public void setcbdw(String value){
		this.cbdw=value;
  	}

	/*（区id）*/ 
	protected String cbdw2;
	public String getcbdw2 (){ return cbdw2;}
	public void setcbdw2(String value){
		this.cbdw2=value;
  	}

	/*联系电话1*/ 
	protected String lxdh1;
	public String getlxdh1 (){ return lxdh1;}
	public void setlxdh1(String value){
		this.lxdh1=value;
  	}

	/*联系电话2*/ 
	protected String lxdh2;
	public String getlxdh2 (){ return lxdh2;}
	public void setlxdh2(String value){
		this.lxdh2=value;
  	}

	/*诉求人性别*/ 
	protected String sqr_xb;
	public String getsqr_xb (){ return sqr_xb;}
	public void setsqr_xb(String value){
		this.sqr_xb=value;
  	}

	/*诉求来源渠道*/ 
	protected String sqlyqd;
	public String getsqlyqd (){ return sqlyqd;}
	public void setsqlyqd(String value){
		this.sqlyqd=value;
  	}

	/*五级归口*/ 
	protected String wjgk;
	public String getwjgk (){ return wjgk;}
	public void setwjgk(String value){
		this.wjgk=value;
  	}

	/*回访结果*/ 
	protected String hfjg;
	public String gethfjg (){ return hfjg;}
	public void sethfjg(String value){
		this.hfjg=value;
  	}



}

