package com.joindoo.jdwechat.model.query;

import com.joindoo.jdwechat.model.query.BaseQueryModel;
import java.sql.Timestamp;
import java.util.Date;
import java.math.BigDecimal;

/**
* 系统 - 安全 - 账号信息表
* Author: zhuqiang4433@gmail.com
* Memo: Auto Created by CodeGenerator on 2020/4/1.
*/

public class TXtAqZhxxQueryModel extends BaseQueryModel{
	/*人员*/ 
	protected String ry_xh;
	public String getry_xh (){ return ry_xh;}
	public void setry_xh(String value){
		this.ry_xh=value;
  	}

	/*显示名称*/ 
	protected String mc;
	public String getmc (){ return mc;}
	public void setmc(String value){
		this.mc=value;
  	}

	/*邮箱*/ 
	protected String email;
	public String getemail (){ return email;}
	public void setemail(String value){
		this.email=value;
  	}

	/*移动电话*/ 
	protected String sj_hm;
	public String getsj_hm (){ return sj_hm;}
	public void setsj_hm(String value){
		this.sj_hm=value;
  	}

	/*显示名称，呢称*/ 
	protected String mc_nc;
	public String getmc_nc (){ return mc_nc;}
	public void setmc_nc(String value){
		this.mc_nc=value;
  	}

	/*拼音名称*/ 
	protected String mc_py;
	public String getmc_py (){ return mc_py;}
	public void setmc_py(String value){
		this.mc_py=value;
  	}

	/*备注*/ 
	protected String bz;
	public String getbz (){ return bz;}
	public void setbz(String value){
		this.bz=value;
  	}

	/*头像图片附件序号*/ 
	protected String tx_fj_xh;
	public String gettx_fj_xh (){ return tx_fj_xh;}
	public void settx_fj_xh(String value){
		this.tx_fj_xh=value;
  	}

	/*ASPNET账号*/ 
	protected String aspnet_xh;
	public String getaspnet_xh (){ return aspnet_xh;}
	public void setaspnet_xh(String value){
		this.aspnet_xh=value;
  	}

	/*用户名, 小写, 只能是英文或者ascaii编码*/ 
	protected String yhm;
	public String getyhm (){ return yhm;}
	public void setyhm(String value){
		this.yhm=value;
  	}

	/*工号*/ 
	protected String work_no;
	public String getwork_no (){ return work_no;}
	public void setwork_no(String value){
		this.work_no=value;
  	}

	/*最后修改日期*/ 
	protected Date xg_sj;
	public Date getxg_sj (){ return xg_sj;}
	public void setxg_sj(Date value){
		this.xg_sj=value;
  	}

	/*录入时间*/ 
	protected Date lr_sj;
	public Date getlr_sj (){ return lr_sj;}
	public void setlr_sj(Date value){
		this.lr_sj=value;
  	}

	/*拥有着标识*/ 
	protected String yyz_xh;
	public String getyyz_xh (){ return yyz_xh;}
	public void setyyz_xh(String value){
		this.yyz_xh=value;
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

	/*密码*/ 
	protected String mm;
	public String getmm (){ return mm;}
	public void setmm(String value){
		this.mm=value;
  	}

	/*密码的种子*/ 
	protected String mm_zz;
	public String getmm_zz (){ return mm_zz;}
	public void setmm_zz(String value){
		this.mm_zz=value;
  	}

	/*用户账号状态代码*/ 
	protected String zhzt_dm;
	public String getzhzt_dm (){ return zhzt_dm;}
	public void setzhzt_dm(String value){
		this.zhzt_dm=value;
  	}

	/*安全码, 该账号的安全码*/ 
	protected String aqm_bm;
	public String getaqm_bm (){ return aqm_bm;}
	public void setaqm_bm(String value){
		this.aqm_bm=value;
  	}

	/*账号类型，比如是企业账号，人员账号，工作人员账号等，对应到角色类别表, 1. */ 
	protected String yhlx_dm;
	public String getyhlx_dm (){ return yhlx_dm;}
	public void setyhlx_dm(String value){
		this.yhlx_dm=value;
  	}

	/*注册审核时间（如果需要)*/ 
	protected Date sh_rq;
	public Date getsh_rq (){ return sh_rq;}
	public void setsh_rq(Date value){
		this.sh_rq=value;
  	}

	/*秘密尝试连续失败开始时间*/ 
	protected Date mmcssb_ks_sj;
	public Date getmmcssb_ks_sj (){ return mmcssb_ks_sj;}
	public void setmmcssb_ks_sj(Date value){
		this.mmcssb_ks_sj=value;
  	}

	/*密码尝试连续失败次数*/ 
	protected Integer mmcssb_cs;
	public Integer getmmcssb_cs (){ return mmcssb_cs;}
	public void setmmcssb_cs(Integer value){
		this.mmcssb_cs=value;
  	}

	/*失效时间*/ 
	protected Date sx_rq;
	public Date getsx_rq (){ return sx_rq;}
	public void setsx_rq(Date value){
		this.sx_rq=value;
  	}

	/*是否在线*/ 
	protected Integer sfzx_bj;
	public Integer getsfzx_bj (){ return sfzx_bj;}
	public void setsfzx_bj(Integer value){
		this.sfzx_bj=value;
  	}

	/*最后活动时间*/ 
	protected Date zh_hd_sj;
	public Date getzh_hd_sj (){ return zh_hd_sj;}
	public void setzh_hd_sj(Date value){
		this.zh_hd_sj=value;
  	}

	/*最后访问时间*/ 
	protected Date zh_fw_sj;
	public Date getzh_fw_sj (){ return zh_fw_sj;}
	public void setzh_fw_sj(Date value){
		this.zh_fw_sj=value;
  	}

	/*最后密码更改时间*/ 
	protected Date zh_mmgg_sj;
	public Date getzh_mmgg_sj (){ return zh_mmgg_sj;}
	public void setzh_mmgg_sj(Date value){
		this.zh_mmgg_sj=value;
  	}

	/*最后锁定时间*/ 
	protected Date zh_sd_sj;
	public Date getzh_sd_sj (){ return zh_sd_sj;}
	public void setzh_sd_sj(Date value){
		this.zh_sd_sj=value;
  	}

	/*最后登录标识*/ 
	protected String zh_dl_xh;
	public String getzh_dl_xh (){ return zh_dl_xh;}
	public void setzh_dl_xh(String value){
		this.zh_dl_xh=value;
  	}

	/*最后设备标识*/ 
	protected String zh_sb_xh;
	public String getzh_sb_xh (){ return zh_sb_xh;}
	public void setzh_sb_xh(String value){
		this.zh_sb_xh=value;
  	}

	/*最后登录时间*/ 
	protected Date zh_dl_sj;
	public Date getzh_dl_sj (){ return zh_dl_sj;}
	public void setzh_dl_sj(Date value){
		this.zh_dl_sj=value;
  	}

	/*角色集代码*/ 
	protected String js_jh_dm;
	public String getjs_jh_dm (){ return js_jh_dm;}
	public void setjs_jh_dm(String value){
		this.js_jh_dm=value;
  	}

	/*是否核验标记,手机号码是否已经核验*/ 
	protected Integer sfsjhy_bj;
	public Integer getsfsjhy_bj (){ return sfsjhy_bj;}
	public void setsfsjhy_bj(Integer value){
		this.sfsjhy_bj=value;
  	}

	/*是否有效*/ 
	protected Integer sfyx_bj;
	public Integer getsfyx_bj (){ return sfyx_bj;}
	public void setsfyx_bj(Integer value){
		this.sfyx_bj=value;
  	}

	private boolean is_search_4_select;

	public boolean isIs_search_4_select() {
		return is_search_4_select;
	}

	public void setIs_search_4_select(boolean is_search_4_select) {
		this.is_search_4_select = is_search_4_select;
	}


}

