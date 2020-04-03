package com.joindoo.jdwechat.beans;

import com.joindoo.jdwechat.Utility;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;
import java.math.BigDecimal;
/**
* 业务_纳税人   企业或个体户主表 营业执照
* Author: zhuqiang4433@gmail.com
* Version: CodeGenerator 1.1
* Memo: Auto Created by CodeGenerator on 2020/4/3.
*/

public class TYwNsrModel extends BaseModel implements IBaseModel{
	 public static final String TABLE_NAME="T_YW_NSR";
	 public static final String SCRIPT_INSERT="INSERT INTO t_yw_nsr(nsr_xh,tyshxydm,nsrlbdm,nsr_mc,fddbr_fzr,zs,zczb,mchzh,hz_rq,ssxq_mc,qyxydj,xgsy_rs,yyzz_zt,jyfwjfs,ky_sj,ss_hy,dh_hm,qylx_jjsx,djjg,qy_zt,cy_rs,yb_hm,xcz,yyzzfj_xh,sfyx_bj,ms,bz,lrry_xh,xgry_xh,lr_sj,xg_sj)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	 public static final String SCRIPT_DELETE="DELETE FROM t_yw_nsr WHERE  nsr_xh=? ";
	 public static final String SCRIPT_UPDATE="UPDATE t_yw_nsr  SET {0} WHERE  nsr_xh=? ";
	 public static final String SCRIPT_SELECT="SELECT nsr_xh,tyshxydm,nsrlbdm,nsr_mc,fddbr_fzr,zs,zczb,mchzh,hz_rq,ssxq_mc,qyxydj,xgsy_rs,yyzz_zt,jyfwjfs,ky_sj,ss_hy,dh_hm,qylx_jjsx,djjg,qy_zt,cy_rs,yb_hm,xcz,yyzzfj_xh,sfyx_bj,ms,bz,lrry_xh,xgry_xh,lr_sj,xg_sj FROM t_yw_nsr WHERE (1=1)";
	 public String getDataTableName() {
 	 return TABLE_NAME;
	 }


	/*序号*/ 
	protected String nsr_xh;
	public String getnsr_xh (){ return nsr_xh;}
	public void setnsr_xh(String value){
		this.nsr_xh=value;
 		this.onChangeProperty("nsr_xh",this.nsr_xh,value);
 	}

	/*统一社会信用代码*/ 
	protected String tyshxydm;
	public String gettyshxydm (){ return tyshxydm;}
	public void settyshxydm(String value){
		this.tyshxydm=value;
 		this.onChangeProperty("tyshxydm",this.tyshxydm,value);
 	}

	/*纳税人类别代码(企业还是个体户)*/ 
	protected String nsrlbdm;
	public String getnsrlbdm (){ return nsrlbdm;}
	public void setnsrlbdm(String value){
		this.nsrlbdm=value;
 		this.onChangeProperty("nsrlbdm",this.nsrlbdm,value);
 	}

	/*纳税人名称*/ 
	protected String nsr_mc;
	public String getnsr_mc (){ return nsr_mc;}
	public void setnsr_mc(String value){
		this.nsr_mc=value;
 		this.onChangeProperty("nsr_mc",this.nsr_mc,value);
 	}

	/*法定代表人/负责人*/ 
	protected String fddbr_fzr;
	public String getfddbr_fzr (){ return fddbr_fzr;}
	public void setfddbr_fzr(String value){
		this.fddbr_fzr=value;
 		this.onChangeProperty("fddbr_fzr",this.fddbr_fzr,value);
 	}

	/*住所*/ 
	protected String zs;
	public String getzs (){ return zs;}
	public void setzs(String value){
		this.zs=value;
 		this.onChangeProperty("zs",this.zs,value);
 	}

	/*注册资本（万）*/ 
	protected Double zczb;
	public Double getzczb (){ return zczb;}
	public void setzczb(Double value){
		this.zczb=value;
 		this.onChangeProperty("zczb",this.zczb,value);
 	}

	/*名称核准号*/ 
	protected String mchzh;
	public String getmchzh (){ return mchzh;}
	public void setmchzh(String value){
		this.mchzh=value;
 		this.onChangeProperty("mchzh",this.mchzh,value);
 	}

	/*核准日期*/ 
	protected Date hz_rq;
	public Date gethz_rq (){ return hz_rq;}
	public void sethz_rq(Date value){
		this.hz_rq=value;
 		this.onChangeProperty("hz_rq",this.hz_rq,value);
 	}

	/*所属辖区名称*/ 
	protected String ssxq_mc;
	public String getssxq_mc (){ return ssxq_mc;}
	public void setssxq_mc(String value){
		this.ssxq_mc=value;
 		this.onChangeProperty("ssxq_mc",this.ssxq_mc,value);
 	}

	/*企业信用等级*/ 
	protected String qyxydj;
	public String getqyxydj (){ return qyxydj;}
	public void setqyxydj(String value){
		this.qyxydj=value;
 		this.onChangeProperty("qyxydj",this.qyxydj,value);
 	}

	/*下岗失业人数*/ 
	protected Integer xgsy_rs;
	public Integer getxgsy_rs (){ return xgsy_rs;}
	public void setxgsy_rs(Integer value){
		this.xgsy_rs=value;
 		this.onChangeProperty("xgsy_rs",this.xgsy_rs,value);
 	}

	/*营业执照状态*/ 
	protected String yyzz_zt;
	public String getyyzz_zt (){ return yyzz_zt;}
	public void setyyzz_zt(String value){
		this.yyzz_zt=value;
 		this.onChangeProperty("yyzz_zt",this.yyzz_zt,value);
 	}

	/*经营范围及方式*/ 
	protected String jyfwjfs;
	public String getjyfwjfs (){ return jyfwjfs;}
	public void setjyfwjfs(String value){
		this.jyfwjfs=value;
 		this.onChangeProperty("jyfwjfs",this.jyfwjfs,value);
 	}

	/*开业时间*/ 
	protected Date ky_sj;
	public Date getky_sj (){ return ky_sj;}
	public void setky_sj(Date value){
		this.ky_sj=value;
 		this.onChangeProperty("ky_sj",this.ky_sj,value);
 	}

	/*所属行业*/ 
	protected String ss_hy;
	public String getss_hy (){ return ss_hy;}
	public void setss_hy(String value){
		this.ss_hy=value;
 		this.onChangeProperty("ss_hy",this.ss_hy,value);
 	}

	/*电话号码*/ 
	protected String dh_hm;
	public String getdh_hm (){ return dh_hm;}
	public void setdh_hm(String value){
		this.dh_hm=value;
 		this.onChangeProperty("dh_hm",this.dh_hm,value);
 	}

	/*企业类型/经济性质*/ 
	protected String qylx_jjsx;
	public String getqylx_jjsx (){ return qylx_jjsx;}
	public void setqylx_jjsx(String value){
		this.qylx_jjsx=value;
 		this.onChangeProperty("qylx_jjsx",this.qylx_jjsx,value);
 	}

	/*登记机关*/ 
	protected String djjg;
	public String getdjjg (){ return djjg;}
	public void setdjjg(String value){
		this.djjg=value;
 		this.onChangeProperty("djjg",this.djjg,value);
 	}

	/*企业状态*/ 
	protected String qy_zt;
	public String getqy_zt (){ return qy_zt;}
	public void setqy_zt(String value){
		this.qy_zt=value;
 		this.onChangeProperty("qy_zt",this.qy_zt,value);
 	}

	/*从业人数*/ 
	protected Integer cy_rs;
	public Integer getcy_rs (){ return cy_rs;}
	public void setcy_rs(Integer value){
		this.cy_rs=value;
 		this.onChangeProperty("cy_rs",this.cy_rs,value);
 	}

	/*邮编号码*/ 
	protected String yb_hm;
	public String getyb_hm (){ return yb_hm;}
	public void setyb_hm(String value){
		this.yb_hm=value;
 		this.onChangeProperty("yb_hm",this.yb_hm,value);
 	}

	/*巡查组*/ 
	protected String xcz;
	public String getxcz (){ return xcz;}
	public void setxcz(String value){
		this.xcz=value;
 		this.onChangeProperty("xcz",this.xcz,value);
 	}

	/*营业执照附件*/ 
	protected String yyzzfj_xh;
	public String getyyzzfj_xh (){ return yyzzfj_xh;}
	public void setyyzzfj_xh(String value){
		this.yyzzfj_xh=value;
 		this.onChangeProperty("yyzzfj_xh",this.yyzzfj_xh,value);
 	}

	/*是否有效标记*/ 
	protected Integer sfyx_bj;
	public Integer getsfyx_bj (){ return sfyx_bj;}
	public void setsfyx_bj(Integer value){
		this.sfyx_bj=value;
 		this.onChangeProperty("sfyx_bj",this.sfyx_bj,value);
 	}

	/*描述*/ 
	protected String ms;
	public String getms (){ return ms;}
	public void setms(String value){
		this.ms=value;
 		this.onChangeProperty("ms",this.ms,value);
 	}

	/*备注*/ 
	protected String bz;
	public String getbz (){ return bz;}
	public void setbz(String value){
		this.bz=value;
 		this.onChangeProperty("bz",this.bz,value);
 	}

	/*录入人员序号*/ 
	protected String lrry_xh;
	public String getlrry_xh (){ return lrry_xh;}
	public void setlrry_xh(String value){
		this.lrry_xh=value;
 		this.onChangeProperty("lrry_xh",this.lrry_xh,value);
 	}

	/*修改人员序号*/ 
	protected String xgry_xh;
	public String getxgry_xh (){ return xgry_xh;}
	public void setxgry_xh(String value){
		this.xgry_xh=value;
 		this.onChangeProperty("xgry_xh",this.xgry_xh,value);
 	}

	/**/ 
	protected Date lr_sj;
	public Date getlr_sj (){ return lr_sj;}
	public void setlr_sj(Date value){
		this.lr_sj=value;
 		this.onChangeProperty("lr_sj",this.lr_sj,value);
 	}

	/**/ 
	protected Date xg_sj;
	public Date getxg_sj (){ return xg_sj;}
	public void setxg_sj(Date value){
		this.xg_sj=value;
 		this.onChangeProperty("xg_sj",this.xg_sj,value);
 	}



	public TYwNsrModel(){
		super();
		this._keyProperty.add("nsr_xh");
		initFieldsType();
	}

	private void initFieldsType(){
		this._fieldType.put("nsr_xh","String");
		this._fieldType.put("tyshxydm","String");
		this._fieldType.put("nsrlbdm","String");
		this._fieldType.put("nsr_mc","String");
		this._fieldType.put("fddbr_fzr","String");
		this._fieldType.put("zs","String");
		this._fieldType.put("zczb","Double");
		this._fieldType.put("mchzh","String");
		this._fieldType.put("hz_rq","Date");
		this._fieldType.put("ssxq_mc","String");
		this._fieldType.put("qyxydj","String");
		this._fieldType.put("xgsy_rs","Integer");
		this._fieldType.put("yyzz_zt","String");
		this._fieldType.put("jyfwjfs","String");
		this._fieldType.put("ky_sj","Date");
		this._fieldType.put("ss_hy","String");
		this._fieldType.put("dh_hm","String");
		this._fieldType.put("qylx_jjsx","String");
		this._fieldType.put("djjg","String");
		this._fieldType.put("qy_zt","String");
		this._fieldType.put("cy_rs","Integer");
		this._fieldType.put("yb_hm","String");
		this._fieldType.put("xcz","String");
		this._fieldType.put("yyzzfj_xh","String");
		this._fieldType.put("sfyx_bj","Integer");
		this._fieldType.put("ms","String");
		this._fieldType.put("bz","String");
		this._fieldType.put("lrry_xh","String");
		this._fieldType.put("xgry_xh","String");
		this._fieldType.put("lr_sj","Date");
		this._fieldType.put("xg_sj","Date");
	}
	@Override
	public void initAsInsert() {

	}
	@Override
	public void fillModel(ResultSet resultSet) {
		try { 
		 if(isExistColumn(resultSet,"nsr_xh")){
			 this.nsr_xh=resultSet.getString("nsr_xh");
		}
		 if(isExistColumn(resultSet,"tyshxydm")){
			 this.tyshxydm=resultSet.getString("tyshxydm");
		}
		 if(isExistColumn(resultSet,"nsrlbdm")){
			 this.nsrlbdm=resultSet.getString("nsrlbdm");
		}
		 if(isExistColumn(resultSet,"nsr_mc")){
			 this.nsr_mc=resultSet.getString("nsr_mc");
		}
		 if(isExistColumn(resultSet,"fddbr_fzr")){
			 this.fddbr_fzr=resultSet.getString("fddbr_fzr");
		}
		 if(isExistColumn(resultSet,"zs")){
			 this.zs=resultSet.getString("zs");
		}
		 if(isExistColumn(resultSet,"zczb")){
			 this.zczb=resultSet.getDouble("zczb");
			if (resultSet.wasNull()) {
				this.zczb=null;
			}
		}
		 if(isExistColumn(resultSet,"mchzh")){
			 this.mchzh=resultSet.getString("mchzh");
		}
		 if(isExistColumn(resultSet,"hz_rq")){
			 this.hz_rq=(Date)resultSet.getObject("hz_rq");
		}
		 if(isExistColumn(resultSet,"ssxq_mc")){
			 this.ssxq_mc=resultSet.getString("ssxq_mc");
		}
		 if(isExistColumn(resultSet,"qyxydj")){
			 this.qyxydj=resultSet.getString("qyxydj");
		}
		 if(isExistColumn(resultSet,"xgsy_rs")){
			 this.xgsy_rs=resultSet.getInt("xgsy_rs");
			if (resultSet.wasNull()) {
				this.xgsy_rs=null;
			}
		}
		 if(isExistColumn(resultSet,"yyzz_zt")){
			 this.yyzz_zt=resultSet.getString("yyzz_zt");
		}
		 if(isExistColumn(resultSet,"jyfwjfs")){
			 this.jyfwjfs=resultSet.getString("jyfwjfs");
		}
		 if(isExistColumn(resultSet,"ky_sj")){
			 this.ky_sj=(Date)resultSet.getObject("ky_sj");
		}
		 if(isExistColumn(resultSet,"ss_hy")){
			 this.ss_hy=resultSet.getString("ss_hy");
		}
		 if(isExistColumn(resultSet,"dh_hm")){
			 this.dh_hm=resultSet.getString("dh_hm");
		}
		 if(isExistColumn(resultSet,"qylx_jjsx")){
			 this.qylx_jjsx=resultSet.getString("qylx_jjsx");
		}
		 if(isExistColumn(resultSet,"djjg")){
			 this.djjg=resultSet.getString("djjg");
		}
		 if(isExistColumn(resultSet,"qy_zt")){
			 this.qy_zt=resultSet.getString("qy_zt");
		}
		 if(isExistColumn(resultSet,"cy_rs")){
			 this.cy_rs=resultSet.getInt("cy_rs");
			if (resultSet.wasNull()) {
				this.cy_rs=null;
			}
		}
		 if(isExistColumn(resultSet,"yb_hm")){
			 this.yb_hm=resultSet.getString("yb_hm");
		}
		 if(isExistColumn(resultSet,"xcz")){
			 this.xcz=resultSet.getString("xcz");
		}
		 if(isExistColumn(resultSet,"yyzzfj_xh")){
			 this.yyzzfj_xh=resultSet.getString("yyzzfj_xh");
		}
		 if(isExistColumn(resultSet,"sfyx_bj")){
			 this.sfyx_bj=resultSet.getInt("sfyx_bj");
		}
		 if(isExistColumn(resultSet,"ms")){
			 this.ms=resultSet.getString("ms");
		}
		 if(isExistColumn(resultSet,"bz")){
			 this.bz=resultSet.getString("bz");
		}
		 if(isExistColumn(resultSet,"lrry_xh")){
			 this.lrry_xh=resultSet.getString("lrry_xh");
		}
		 if(isExistColumn(resultSet,"xgry_xh")){
			 this.xgry_xh=resultSet.getString("xgry_xh");
		}
		 if(isExistColumn(resultSet,"lr_sj")){
			 this.lr_sj=(Date)resultSet.getObject("lr_sj");
		}
		 if(isExistColumn(resultSet,"xg_sj")){
			 this.xg_sj=(Date)resultSet.getObject("xg_sj");
		}
      } catch (SQLException e) {
              e.printStackTrace();
          }
      }
  @Override
   public String getExist() {
	    String sql="select 1 from "+TABLE_NAME;
		    sql+=" where   nsr_xh="+Utility.getSqlFielStatement("string",this.nsr_xh)+"  ";
	    return sql;
   }


   @Override
   public String getInsert() {
       String sql="insert into "+TABLE_NAME+"(nsr_xh,tyshxydm,nsrlbdm,nsr_mc,fddbr_fzr,zs,zczb,mchzh,hz_rq,ssxq_mc,qyxydj,xgsy_rs,yyzz_zt,jyfwjfs,ky_sj,ss_hy,dh_hm,qylx_jjsx,djjg,qy_zt,cy_rs,yb_hm,xcz,yyzzfj_xh,sfyx_bj,ms,bz,lrry_xh,xgry_xh,lr_sj,xg_sj) values("+Utility.getSqlFielStatement("string",this.nsr_xh)+","+Utility.getSqlFielStatement("string",this.tyshxydm)+","+Utility.getSqlFielStatement("string",this.nsrlbdm)+","+Utility.getSqlFielStatement("string",this.nsr_mc)+","+Utility.getSqlFielStatement("string",this.fddbr_fzr)+","+Utility.getSqlFielStatement("string",this.zs)+","+this.zczb+","+Utility.getSqlFielStatement("string",this.mchzh)+","+Utility.getSqlFielStatement("date",this.hz_rq)+","+Utility.getSqlFielStatement("string",this.ssxq_mc)+","+Utility.getSqlFielStatement("string",this.qyxydj)+","+this.xgsy_rs+","+Utility.getSqlFielStatement("string",this.yyzz_zt)+","+Utility.getSqlFielStatement("string",this.jyfwjfs)+","+Utility.getSqlFielStatement("date",this.ky_sj)+","+Utility.getSqlFielStatement("string",this.ss_hy)+","+Utility.getSqlFielStatement("string",this.dh_hm)+","+Utility.getSqlFielStatement("string",this.qylx_jjsx)+","+Utility.getSqlFielStatement("string",this.djjg)+","+Utility.getSqlFielStatement("string",this.qy_zt)+","+this.cy_rs+","+Utility.getSqlFielStatement("string",this.yb_hm)+","+Utility.getSqlFielStatement("string",this.xcz)+","+Utility.getSqlFielStatement("string",this.yyzzfj_xh)+","+this.sfyx_bj+","+Utility.getSqlFielStatement("string",this.ms)+","+Utility.getSqlFielStatement("string",this.bz)+","+Utility.getSqlFielStatement("string",this.lrry_xh)+","+Utility.getSqlFielStatement("string",this.xgry_xh)+","+Utility.getSqlFielStatement("date",this.lr_sj)+","+Utility.getSqlFielStatement("date",this.xg_sj)+")";
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
   sql+= " where  nsr_xh="+Utility.getSqlFielStatement("string",this.nsr_xh)+" ";
   }
   return sql;
}


	@Override
	public String getSelect() {
		String sql="select nsr_xh,tyshxydm,nsrlbdm,nsr_mc,fddbr_fzr,zs,zczb,mchzh,hz_rq,ssxq_mc,qyxydj,xgsy_rs,yyzz_zt,jyfwjfs,ky_sj,ss_hy,dh_hm,qylx_jjsx,djjg,qy_zt,cy_rs,yb_hm,xcz,yyzzfj_xh,sfyx_bj,ms,bz,lrry_xh,xgry_xh,lr_sj,xg_sj from "+TABLE_NAME;
		if(!Utility.isNullOrEmpty(nsr_xh)){
			sql+=" where  nsr_xh="+Utility.getSqlFielStatement("string",this.nsr_xh)+" ";
		}
		return sql;
	}
	@Override
	public String getSelectByCondition(String condition) {
		String sql="select nsr_xh,tyshxydm,nsrlbdm,nsr_mc,fddbr_fzr,zs,zczb,mchzh,hz_rq,ssxq_mc,qyxydj,xgsy_rs,yyzz_zt,jyfwjfs,ky_sj,ss_hy,dh_hm,qylx_jjsx,djjg,qy_zt,cy_rs,yb_hm,xcz,yyzzfj_xh,sfyx_bj,ms,bz,lrry_xh,xgry_xh,lr_sj,xg_sj from "+TABLE_NAME;
		if(condition!=null&&condition!=""){
			sql+=" where "+condition;
		}
		return sql;
	}


    @Override
   public String getDelete() {
	 String sql="delete from "+TABLE_NAME+" where  nsr_xh="+Utility.getSqlFielStatement("string",this.nsr_xh)+" ";
	 return sql;
   }


}

