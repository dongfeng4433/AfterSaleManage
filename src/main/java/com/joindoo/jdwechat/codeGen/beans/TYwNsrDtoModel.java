package com.joindoo.jdwechat.codeGen.beans;

import java.util.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.format.annotation.DateTimeFormat;

/**
* 业务_纳税人   企业或个体户主表 营业执照
* Author: zhuqiang4433@gmail.com
* Memo: Auto Created by CodeGenerator on 2019/8/27.
*/

@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class TYwNsrDtoModel extends BaseDtoModel{
	/*序号*/ 
	protected String nsr_xh;
	public String getnsr_xh (){ return nsr_xh;}
	public void setnsr_xh(String value){
		this.nsr_xh=value;
  	}

	/*统一社会信用代码*/ 
	protected String tyshxydm;
	public String gettyshxydm (){ return tyshxydm;}
	public void settyshxydm(String value){
		this.tyshxydm=value;
  	}

	/*纳税人类别代码(企业还是个体户)*/ 
	protected String nsrlbdm;
	public String getnsrlbdm (){ return nsrlbdm;}
	public void setnsrlbdm(String value){
		this.nsrlbdm=value;
  	}

	/*纳税人名称*/ 
	protected String nsr_mc;
	public String getnsr_mc (){ return nsr_mc;}
	public void setnsr_mc(String value){
		this.nsr_mc=value;
  	}

	/*法定代表人/负责人*/ 
	protected String fddbr_fzr;
	public String getfddbr_fzr (){ return fddbr_fzr;}
	public void setfddbr_fzr(String value){
		this.fddbr_fzr=value;
  	}

	/*住所*/ 
	protected String zs;
	public String getzs (){ return zs;}
	public void setzs(String value){
		this.zs=value;
  	}

	/*注册资本（万）*/ 
	protected Double zczb;
	public Double getzczb (){ return zczb;}
	public void setzczb(Double value){
		this.zczb=value;
  	}

	/*名称核准号*/ 
	protected String mchzh;
	public String getmchzh (){ return mchzh;}
	public void setmchzh(String value){
		this.mchzh=value;
  	}

	/*核准日期*/ 
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date hz_rq;
	public Date gethz_rq (){ return hz_rq;}
	public void sethz_rq(Date value){
		this.hz_rq=value;
  	}

	/*所属辖区名称*/ 
	protected String ssxq_mc;
	public String getssxq_mc (){ return ssxq_mc;}
	public void setssxq_mc(String value){
		this.ssxq_mc=value;
  	}

	/*企业信用等级*/ 
	protected String qyxydj;
	public String getqyxydj (){ return qyxydj;}
	public void setqyxydj(String value){
		this.qyxydj=value;
  	}

	/*下岗失业人数*/ 
	protected Integer xgsy_rs;
	public Integer getxgsy_rs (){ return xgsy_rs;}
	public void setxgsy_rs(Integer value){
		this.xgsy_rs=value;
  	}

	/*营业执照状态*/ 
	protected String yyzz_zt;
	public String getyyzz_zt (){ return yyzz_zt;}
	public void setyyzz_zt(String value){
		this.yyzz_zt=value;
  	}

	/*经营范围及方式*/ 
	protected String jyfwjfs;
	public String getjyfwjfs (){ return jyfwjfs;}
	public void setjyfwjfs(String value){
		this.jyfwjfs=value;
  	}

	/*开业时间*/ 
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date ky_sj;
	public Date getky_sj (){ return ky_sj;}
	public void setky_sj(Date value){
		this.ky_sj=value;
  	}

	/*所属行业*/ 
	protected String ss_hy;
	public String getss_hy (){ return ss_hy;}
	public void setss_hy(String value){
		this.ss_hy=value;
  	}

	/*电话号码*/ 
	protected String dh_hm;
	public String getdh_hm (){ return dh_hm;}
	public void setdh_hm(String value){
		this.dh_hm=value;
  	}

	/*企业类型/经济性质*/ 
	protected String qylx_jjsx;
	public String getqylx_jjsx (){ return qylx_jjsx;}
	public void setqylx_jjsx(String value){
		this.qylx_jjsx=value;
  	}

	/*登记机关*/ 
	protected String djjg;
	public String getdjjg (){ return djjg;}
	public void setdjjg(String value){
		this.djjg=value;
  	}

	/*企业状态*/ 
	protected String qy_zt;
	public String getqy_zt (){ return qy_zt;}
	public void setqy_zt(String value){
		this.qy_zt=value;
  	}

	/*从业人数*/ 
	protected Integer cy_rs;
	public Integer getcy_rs (){ return cy_rs;}
	public void setcy_rs(Integer value){
		this.cy_rs=value;
  	}

	/*邮编号码*/ 
	protected String yb_hm;
	public String getyb_hm (){ return yb_hm;}
	public void setyb_hm(String value){
		this.yb_hm=value;
  	}

	/*巡查组*/ 
	protected String xcz;
	public String getxcz (){ return xcz;}
	public void setxcz(String value){
		this.xcz=value;
  	}

	/*营业执照附件*/ 
	protected String yyzzfj_xh;
	public String getyyzzfj_xh (){ return yyzzfj_xh;}
	public void setyyzzfj_xh(String value){
		this.yyzzfj_xh=value;
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



}

