package com.joindoo.jdwechat.beans;

import com.joindoo.jdwechat.Utility;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;
import java.math.BigDecimal;
/**
* 系统 - 安全 - 账号信息表
* Author: zhuqiang4433@gmail.com
* Version: CodeGenerator 1.1
* Memo: Auto Created by CodeGenerator on 2020/4/3.
*/

public class TXtAqZhxxModel extends BaseModel implements IBaseModel{
	 public static final String TABLE_NAME="T_XT_AQ_ZHXX";
	 public static final String SCRIPT_INSERT="INSERT INTO t_xt_aq_zhxx(ry_xh,mc,email,sj_hm,mc_nc,mc_py,bz,tx_fj_xh,aspnet_xh,yhm,work_no,xg_sj,lr_sj,yyz_xh,lrry_xh,xgry_xh,mm,mm_zz,zhzt_dm,aqm_bm,yhlx_dm,sh_rq,mmcssb_ks_sj,mmcssb_cs,sx_rq,sfzx_bj,zh_hd_sj,zh_fw_sj,zh_mmgg_sj,zh_sd_sj,zh_dl_xh,zh_sb_xh,zh_dl_sj,js_jh_dm,sfsjhy_bj,sfyx_bj)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	 public static final String SCRIPT_DELETE="DELETE FROM t_xt_aq_zhxx WHERE  ry_xh=? ";
	 public static final String SCRIPT_UPDATE="UPDATE t_xt_aq_zhxx  SET {0} WHERE  ry_xh=? ";
	 public static final String SCRIPT_SELECT="SELECT ry_xh,mc,email,sj_hm,mc_nc,mc_py,bz,tx_fj_xh,aspnet_xh,yhm,work_no,xg_sj,lr_sj,yyz_xh,lrry_xh,xgry_xh,mm,mm_zz,zhzt_dm,aqm_bm,yhlx_dm,sh_rq,mmcssb_ks_sj,mmcssb_cs,sx_rq,sfzx_bj,zh_hd_sj,zh_fw_sj,zh_mmgg_sj,zh_sd_sj,zh_dl_xh,zh_sb_xh,zh_dl_sj,js_jh_dm,sfsjhy_bj,sfyx_bj FROM t_xt_aq_zhxx WHERE (1=1)";
	 public String getDataTableName() {
 	 return TABLE_NAME;
	 }


	/*人员*/ 
	protected String ry_xh;
	public String getry_xh (){ return ry_xh;}
	public void setry_xh(String value){
		this.ry_xh=value;
 		this.onChangeProperty("ry_xh",this.ry_xh,value);
 	}

	/*显示名称*/ 
	protected String mc;
	public String getmc (){ return mc;}
	public void setmc(String value){
		this.mc=value;
 		this.onChangeProperty("mc",this.mc,value);
 	}

	/*邮箱*/ 
	protected String email;
	public String getemail (){ return email;}
	public void setemail(String value){
		this.email=value;
 		this.onChangeProperty("email",this.email,value);
 	}

	/*移动电话*/ 
	protected String sj_hm;
	public String getsj_hm (){ return sj_hm;}
	public void setsj_hm(String value){
		this.sj_hm=value;
 		this.onChangeProperty("sj_hm",this.sj_hm,value);
 	}

	/*显示名称，呢称*/ 
	protected String mc_nc;
	public String getmc_nc (){ return mc_nc;}
	public void setmc_nc(String value){
		this.mc_nc=value;
 		this.onChangeProperty("mc_nc",this.mc_nc,value);
 	}

	/*拼音名称*/ 
	protected String mc_py;
	public String getmc_py (){ return mc_py;}
	public void setmc_py(String value){
		this.mc_py=value;
 		this.onChangeProperty("mc_py",this.mc_py,value);
 	}

	/*备注*/ 
	protected String bz;
	public String getbz (){ return bz;}
	public void setbz(String value){
		this.bz=value;
 		this.onChangeProperty("bz",this.bz,value);
 	}

	/*头像图片附件序号*/ 
	protected String tx_fj_xh;
	public String gettx_fj_xh (){ return tx_fj_xh;}
	public void settx_fj_xh(String value){
		this.tx_fj_xh=value;
 		this.onChangeProperty("tx_fj_xh",this.tx_fj_xh,value);
 	}

	/*ASPNET账号*/ 
	protected String aspnet_xh;
	public String getaspnet_xh (){ return aspnet_xh;}
	public void setaspnet_xh(String value){
		this.aspnet_xh=value;
 		this.onChangeProperty("aspnet_xh",this.aspnet_xh,value);
 	}

	/*用户名, 小写, 只能是英文或者ascaii编码*/ 
	protected String yhm;
	public String getyhm (){ return yhm;}
	public void setyhm(String value){
		this.yhm=value;
 		this.onChangeProperty("yhm",this.yhm,value);
 	}

	/*工号*/ 
	protected String work_no;
	public String getwork_no (){ return work_no;}
	public void setwork_no(String value){
		this.work_no=value;
 		this.onChangeProperty("work_no",this.work_no,value);
 	}

	/*最后修改日期*/ 
	protected Date xg_sj;
	public Date getxg_sj (){ return xg_sj;}
	public void setxg_sj(Date value){
		this.xg_sj=value;
 		this.onChangeProperty("xg_sj",this.xg_sj,value);
 	}

	/*录入时间*/ 
	protected Date lr_sj;
	public Date getlr_sj (){ return lr_sj;}
	public void setlr_sj(Date value){
		this.lr_sj=value;
 		this.onChangeProperty("lr_sj",this.lr_sj,value);
 	}

	/*拥有着标识*/ 
	protected String yyz_xh;
	public String getyyz_xh (){ return yyz_xh;}
	public void setyyz_xh(String value){
		this.yyz_xh=value;
 		this.onChangeProperty("yyz_xh",this.yyz_xh,value);
 	}

	/*录入人员*/ 
	protected String lrry_xh;
	public String getlrry_xh (){ return lrry_xh;}
	public void setlrry_xh(String value){
		this.lrry_xh=value;
 		this.onChangeProperty("lrry_xh",this.lrry_xh,value);
 	}

	/*(外键:系统用户) 最后修改人*/ 
	protected String xgry_xh;
	public String getxgry_xh (){ return xgry_xh;}
	public void setxgry_xh(String value){
		this.xgry_xh=value;
 		this.onChangeProperty("xgry_xh",this.xgry_xh,value);
 	}

	/*密码*/ 
	protected String mm;
	public String getmm (){ return mm;}
	public void setmm(String value){
		this.mm=value;
 		this.onChangeProperty("mm",this.mm,value);
 	}

	/*密码的种子*/ 
	protected String mm_zz;
	public String getmm_zz (){ return mm_zz;}
	public void setmm_zz(String value){
		this.mm_zz=value;
 		this.onChangeProperty("mm_zz",this.mm_zz,value);
 	}

	/*用户账号状态代码*/ 
	protected String zhzt_dm;
	public String getzhzt_dm (){ return zhzt_dm;}
	public void setzhzt_dm(String value){
		this.zhzt_dm=value;
 		this.onChangeProperty("zhzt_dm",this.zhzt_dm,value);
 	}

	/*安全码, 该账号的安全码*/ 
	protected String aqm_bm;
	public String getaqm_bm (){ return aqm_bm;}
	public void setaqm_bm(String value){
		this.aqm_bm=value;
 		this.onChangeProperty("aqm_bm",this.aqm_bm,value);
 	}

	/*账号类型，比如是企业账号，人员账号，工作人员账号等，对应到角色类别表, 1. */ 
	protected String yhlx_dm;
	public String getyhlx_dm (){ return yhlx_dm;}
	public void setyhlx_dm(String value){
		this.yhlx_dm=value;
 		this.onChangeProperty("yhlx_dm",this.yhlx_dm,value);
 	}

	/*注册审核时间（如果需要)*/ 
	protected Date sh_rq;
	public Date getsh_rq (){ return sh_rq;}
	public void setsh_rq(Date value){
		this.sh_rq=value;
 		this.onChangeProperty("sh_rq",this.sh_rq,value);
 	}

	/*秘密尝试连续失败开始时间*/ 
	protected Date mmcssb_ks_sj;
	public Date getmmcssb_ks_sj (){ return mmcssb_ks_sj;}
	public void setmmcssb_ks_sj(Date value){
		this.mmcssb_ks_sj=value;
 		this.onChangeProperty("mmcssb_ks_sj",this.mmcssb_ks_sj,value);
 	}

	/*密码尝试连续失败次数*/ 
	protected Integer mmcssb_cs;
	public Integer getmmcssb_cs (){ return mmcssb_cs;}
	public void setmmcssb_cs(Integer value){
		this.mmcssb_cs=value;
 		this.onChangeProperty("mmcssb_cs",this.mmcssb_cs,value);
 	}

	/*失效时间*/ 
	protected Date sx_rq;
	public Date getsx_rq (){ return sx_rq;}
	public void setsx_rq(Date value){
		this.sx_rq=value;
 		this.onChangeProperty("sx_rq",this.sx_rq,value);
 	}

	/*是否在线*/ 
	protected Integer sfzx_bj;
	public Integer getsfzx_bj (){ return sfzx_bj;}
	public void setsfzx_bj(Integer value){
		this.sfzx_bj=value;
 		this.onChangeProperty("sfzx_bj",this.sfzx_bj,value);
 	}

	/*最后活动时间*/ 
	protected Date zh_hd_sj;
	public Date getzh_hd_sj (){ return zh_hd_sj;}
	public void setzh_hd_sj(Date value){
		this.zh_hd_sj=value;
 		this.onChangeProperty("zh_hd_sj",this.zh_hd_sj,value);
 	}

	/*最后访问时间*/ 
	protected Date zh_fw_sj;
	public Date getzh_fw_sj (){ return zh_fw_sj;}
	public void setzh_fw_sj(Date value){
		this.zh_fw_sj=value;
 		this.onChangeProperty("zh_fw_sj",this.zh_fw_sj,value);
 	}

	/*最后密码更改时间*/ 
	protected Date zh_mmgg_sj;
	public Date getzh_mmgg_sj (){ return zh_mmgg_sj;}
	public void setzh_mmgg_sj(Date value){
		this.zh_mmgg_sj=value;
 		this.onChangeProperty("zh_mmgg_sj",this.zh_mmgg_sj,value);
 	}

	/*最后锁定时间*/ 
	protected Date zh_sd_sj;
	public Date getzh_sd_sj (){ return zh_sd_sj;}
	public void setzh_sd_sj(Date value){
		this.zh_sd_sj=value;
 		this.onChangeProperty("zh_sd_sj",this.zh_sd_sj,value);
 	}

	/*最后登录标识*/ 
	protected String zh_dl_xh;
	public String getzh_dl_xh (){ return zh_dl_xh;}
	public void setzh_dl_xh(String value){
		this.zh_dl_xh=value;
 		this.onChangeProperty("zh_dl_xh",this.zh_dl_xh,value);
 	}

	/*最后设备标识*/ 
	protected String zh_sb_xh;
	public String getzh_sb_xh (){ return zh_sb_xh;}
	public void setzh_sb_xh(String value){
		this.zh_sb_xh=value;
 		this.onChangeProperty("zh_sb_xh",this.zh_sb_xh,value);
 	}

	/*最后登录时间*/ 
	protected Date zh_dl_sj;
	public Date getzh_dl_sj (){ return zh_dl_sj;}
	public void setzh_dl_sj(Date value){
		this.zh_dl_sj=value;
 		this.onChangeProperty("zh_dl_sj",this.zh_dl_sj,value);
 	}

	/*角色集代码*/ 
	protected String js_jh_dm;
	public String getjs_jh_dm (){ return js_jh_dm;}
	public void setjs_jh_dm(String value){
		this.js_jh_dm=value;
 		this.onChangeProperty("js_jh_dm",this.js_jh_dm,value);
 	}

	/*是否核验标记,手机号码是否已经核验*/ 
	protected Integer sfsjhy_bj;
	public Integer getsfsjhy_bj (){ return sfsjhy_bj;}
	public void setsfsjhy_bj(Integer value){
		this.sfsjhy_bj=value;
 		this.onChangeProperty("sfsjhy_bj",this.sfsjhy_bj,value);
 	}

	/*是否有效*/ 
	protected Integer sfyx_bj;
	public Integer getsfyx_bj (){ return sfyx_bj;}
	public void setsfyx_bj(Integer value){
		this.sfyx_bj=value;
 		this.onChangeProperty("sfyx_bj",this.sfyx_bj,value);
 	}



	public TXtAqZhxxModel(){
		super();
		this._keyProperty.add("ry_xh");
		initFieldsType();
	}

	private void initFieldsType(){
		this._fieldType.put("ry_xh","String");
		this._fieldType.put("mc","String");
		this._fieldType.put("email","String");
		this._fieldType.put("sj_hm","String");
		this._fieldType.put("mc_nc","String");
		this._fieldType.put("mc_py","String");
		this._fieldType.put("bz","String");
		this._fieldType.put("tx_fj_xh","String");
		this._fieldType.put("aspnet_xh","String");
		this._fieldType.put("yhm","String");
		this._fieldType.put("work_no","String");
		this._fieldType.put("xg_sj","Date");
		this._fieldType.put("lr_sj","Date");
		this._fieldType.put("yyz_xh","String");
		this._fieldType.put("lrry_xh","String");
		this._fieldType.put("xgry_xh","String");
		this._fieldType.put("mm","String");
		this._fieldType.put("mm_zz","String");
		this._fieldType.put("zhzt_dm","String");
		this._fieldType.put("aqm_bm","String");
		this._fieldType.put("yhlx_dm","String");
		this._fieldType.put("sh_rq","Date");
		this._fieldType.put("mmcssb_ks_sj","Date");
		this._fieldType.put("mmcssb_cs","Integer");
		this._fieldType.put("sx_rq","Date");
		this._fieldType.put("sfzx_bj","Integer");
		this._fieldType.put("zh_hd_sj","Date");
		this._fieldType.put("zh_fw_sj","Date");
		this._fieldType.put("zh_mmgg_sj","Date");
		this._fieldType.put("zh_sd_sj","Date");
		this._fieldType.put("zh_dl_xh","String");
		this._fieldType.put("zh_sb_xh","String");
		this._fieldType.put("zh_dl_sj","Date");
		this._fieldType.put("js_jh_dm","String");
		this._fieldType.put("sfsjhy_bj","Integer");
		this._fieldType.put("sfyx_bj","Integer");
	}
	@Override
	public void initAsInsert() {

	}
	@Override
	public void fillModel(ResultSet resultSet) {
		try { 
		 if(isExistColumn(resultSet,"ry_xh")){
			 this.ry_xh=resultSet.getString("ry_xh");
		}
		 if(isExistColumn(resultSet,"mc")){
			 this.mc=resultSet.getString("mc");
		}
		 if(isExistColumn(resultSet,"email")){
			 this.email=resultSet.getString("email");
		}
		 if(isExistColumn(resultSet,"sj_hm")){
			 this.sj_hm=resultSet.getString("sj_hm");
		}
		 if(isExistColumn(resultSet,"mc_nc")){
			 this.mc_nc=resultSet.getString("mc_nc");
		}
		 if(isExistColumn(resultSet,"mc_py")){
			 this.mc_py=resultSet.getString("mc_py");
		}
		 if(isExistColumn(resultSet,"bz")){
			 this.bz=resultSet.getString("bz");
		}
		 if(isExistColumn(resultSet,"tx_fj_xh")){
			 this.tx_fj_xh=resultSet.getString("tx_fj_xh");
		}
		 if(isExistColumn(resultSet,"aspnet_xh")){
			 this.aspnet_xh=resultSet.getString("aspnet_xh");
		}
		 if(isExistColumn(resultSet,"yhm")){
			 this.yhm=resultSet.getString("yhm");
		}
		 if(isExistColumn(resultSet,"work_no")){
			 this.work_no=resultSet.getString("work_no");
		}
		 if(isExistColumn(resultSet,"xg_sj")){
			 this.xg_sj=(Date)resultSet.getObject("xg_sj");
		}
		 if(isExistColumn(resultSet,"lr_sj")){
			 this.lr_sj=(Date)resultSet.getObject("lr_sj");
		}
		 if(isExistColumn(resultSet,"yyz_xh")){
			 this.yyz_xh=resultSet.getString("yyz_xh");
		}
		 if(isExistColumn(resultSet,"lrry_xh")){
			 this.lrry_xh=resultSet.getString("lrry_xh");
		}
		 if(isExistColumn(resultSet,"xgry_xh")){
			 this.xgry_xh=resultSet.getString("xgry_xh");
		}
		 if(isExistColumn(resultSet,"mm")){
			 this.mm=resultSet.getString("mm");
		}
		 if(isExistColumn(resultSet,"mm_zz")){
			 this.mm_zz=resultSet.getString("mm_zz");
		}
		 if(isExistColumn(resultSet,"zhzt_dm")){
			 this.zhzt_dm=resultSet.getString("zhzt_dm");
		}
		 if(isExistColumn(resultSet,"aqm_bm")){
			 this.aqm_bm=resultSet.getString("aqm_bm");
		}
		 if(isExistColumn(resultSet,"yhlx_dm")){
			 this.yhlx_dm=resultSet.getString("yhlx_dm");
		}
		 if(isExistColumn(resultSet,"sh_rq")){
			 this.sh_rq=(Date)resultSet.getObject("sh_rq");
		}
		 if(isExistColumn(resultSet,"mmcssb_ks_sj")){
			 this.mmcssb_ks_sj=(Date)resultSet.getObject("mmcssb_ks_sj");
		}
		 if(isExistColumn(resultSet,"mmcssb_cs")){
			 this.mmcssb_cs=resultSet.getInt("mmcssb_cs");
		}
		 if(isExistColumn(resultSet,"sx_rq")){
			 this.sx_rq=(Date)resultSet.getObject("sx_rq");
		}
		 if(isExistColumn(resultSet,"sfzx_bj")){
			 this.sfzx_bj=resultSet.getInt("sfzx_bj");
		}
		 if(isExistColumn(resultSet,"zh_hd_sj")){
			 this.zh_hd_sj=(Date)resultSet.getObject("zh_hd_sj");
		}
		 if(isExistColumn(resultSet,"zh_fw_sj")){
			 this.zh_fw_sj=(Date)resultSet.getObject("zh_fw_sj");
		}
		 if(isExistColumn(resultSet,"zh_mmgg_sj")){
			 this.zh_mmgg_sj=(Date)resultSet.getObject("zh_mmgg_sj");
		}
		 if(isExistColumn(resultSet,"zh_sd_sj")){
			 this.zh_sd_sj=(Date)resultSet.getObject("zh_sd_sj");
		}
		 if(isExistColumn(resultSet,"zh_dl_xh")){
			 this.zh_dl_xh=resultSet.getString("zh_dl_xh");
		}
		 if(isExistColumn(resultSet,"zh_sb_xh")){
			 this.zh_sb_xh=resultSet.getString("zh_sb_xh");
		}
		 if(isExistColumn(resultSet,"zh_dl_sj")){
			 this.zh_dl_sj=(Date)resultSet.getObject("zh_dl_sj");
		}
		 if(isExistColumn(resultSet,"js_jh_dm")){
			 this.js_jh_dm=resultSet.getString("js_jh_dm");
		}
		 if(isExistColumn(resultSet,"sfsjhy_bj")){
			 this.sfsjhy_bj=resultSet.getInt("sfsjhy_bj");
		}
		 if(isExistColumn(resultSet,"sfyx_bj")){
			 this.sfyx_bj=resultSet.getInt("sfyx_bj");
		}
      } catch (SQLException e) {
              e.printStackTrace();
          }
      }
  @Override
   public String getExist() {
	    String sql="select 1 from "+TABLE_NAME;
		    sql+=" where   ry_xh="+Utility.getSqlFielStatement("string",this.ry_xh)+"  ";
	    return sql;
   }


   @Override
   public String getInsert() {
       String sql="insert into "+TABLE_NAME+"(ry_xh,mc,email,sj_hm,mc_nc,mc_py,bz,tx_fj_xh,aspnet_xh,yhm,work_no,xg_sj,lr_sj,yyz_xh,lrry_xh,xgry_xh,mm,mm_zz,zhzt_dm,aqm_bm,yhlx_dm,sh_rq,mmcssb_ks_sj,mmcssb_cs,sx_rq,sfzx_bj,zh_hd_sj,zh_fw_sj,zh_mmgg_sj,zh_sd_sj,zh_dl_xh,zh_sb_xh,zh_dl_sj,js_jh_dm,sfsjhy_bj,sfyx_bj) values("+Utility.getSqlFielStatement("string",this.ry_xh)+","+Utility.getSqlFielStatement("string",this.mc)+","+Utility.getSqlFielStatement("string",this.email)+","+Utility.getSqlFielStatement("string",this.sj_hm)+","+Utility.getSqlFielStatement("string",this.mc_nc)+","+Utility.getSqlFielStatement("string",this.mc_py)+","+Utility.getSqlFielStatement("string",this.bz)+","+Utility.getSqlFielStatement("string",this.tx_fj_xh)+","+Utility.getSqlFielStatement("string",this.aspnet_xh)+","+Utility.getSqlFielStatement("string",this.yhm)+","+Utility.getSqlFielStatement("string",this.work_no)+","+Utility.getSqlFielStatement("date",this.xg_sj)+","+Utility.getSqlFielStatement("date",this.lr_sj)+","+Utility.getSqlFielStatement("string",this.yyz_xh)+","+Utility.getSqlFielStatement("string",this.lrry_xh)+","+Utility.getSqlFielStatement("string",this.xgry_xh)+","+Utility.getSqlFielStatement("string",this.mm)+","+Utility.getSqlFielStatement("string",this.mm_zz)+","+Utility.getSqlFielStatement("string",this.zhzt_dm)+","+Utility.getSqlFielStatement("string",this.aqm_bm)+","+Utility.getSqlFielStatement("string",this.yhlx_dm)+","+Utility.getSqlFielStatement("date",this.sh_rq)+","+Utility.getSqlFielStatement("date",this.mmcssb_ks_sj)+","+this.mmcssb_cs+","+Utility.getSqlFielStatement("date",this.sx_rq)+","+this.sfzx_bj+","+Utility.getSqlFielStatement("date",this.zh_hd_sj)+","+Utility.getSqlFielStatement("date",this.zh_fw_sj)+","+Utility.getSqlFielStatement("date",this.zh_mmgg_sj)+","+Utility.getSqlFielStatement("date",this.zh_sd_sj)+","+Utility.getSqlFielStatement("string",this.zh_dl_xh)+","+Utility.getSqlFielStatement("string",this.zh_sb_xh)+","+Utility.getSqlFielStatement("date",this.zh_dl_sj)+","+Utility.getSqlFielStatement("string",this.js_jh_dm)+","+this.sfsjhy_bj+","+this.sfyx_bj+")";
       return sql;
   }


   @Override
   public String getUpdate() {
   String sql="";
   if(this._isNeedUpdate&&this._changeProperty.size()>0){
   sql="update "+TABLE_NAME+" set ";
   for (String s:this._changeProperty.keySet()){
       if(this._keyProperty.contains(s))continue;
        String str=this.getSqlFieldStatement(s,null);
         if(str!=""){
               sql+=s+str+",";
         }
   }
   if(sql.endsWith(","))
   sql=sql.substring(0,sql.length()-1);
   else
       return "";
   sql+= " where  ry_xh="+Utility.getSqlFielStatement("string",this.ry_xh)+" ";
   }
   return sql;
}


	@Override
	public String getSelect() {
		String sql="select ry_xh,mc,email,sj_hm,mc_nc,mc_py,bz,tx_fj_xh,aspnet_xh,yhm,work_no,xg_sj,lr_sj,yyz_xh,lrry_xh,xgry_xh,mm,mm_zz,zhzt_dm,aqm_bm,yhlx_dm,sh_rq,mmcssb_ks_sj,mmcssb_cs,sx_rq,sfzx_bj,zh_hd_sj,zh_fw_sj,zh_mmgg_sj,zh_sd_sj,zh_dl_xh,zh_sb_xh,zh_dl_sj,js_jh_dm,sfsjhy_bj,sfyx_bj from "+TABLE_NAME;
		if(!Utility.isNullOrEmpty(ry_xh)){
			sql+=" where  ry_xh="+Utility.getSqlFielStatement("string",this.ry_xh)+" ";
		}
		return sql;
	}
	@Override
	public String getSelectByCondition(String condition) {
		String sql="select ry_xh,mc,email,sj_hm,mc_nc,mc_py,bz,tx_fj_xh,aspnet_xh,yhm,work_no,xg_sj,lr_sj,yyz_xh,lrry_xh,xgry_xh,mm,mm_zz,zhzt_dm,aqm_bm,yhlx_dm,sh_rq,mmcssb_ks_sj,mmcssb_cs,sx_rq,sfzx_bj,zh_hd_sj,zh_fw_sj,zh_mmgg_sj,zh_sd_sj,zh_dl_xh,zh_sb_xh,zh_dl_sj,js_jh_dm,sfsjhy_bj,sfyx_bj from "+TABLE_NAME;
		if(condition!=null&&condition!=""){
			sql+=" where "+condition;
		}
		return sql;
	}


    @Override
   public String getDelete() {
	 String sql="delete from "+TABLE_NAME+" where  ry_xh="+Utility.getSqlFielStatement("string",this.ry_xh)+" ";
	 return sql;
   }


}

