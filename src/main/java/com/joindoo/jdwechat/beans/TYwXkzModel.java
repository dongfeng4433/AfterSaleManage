package com.joindoo.jdwechat.beans;

import com.joindoo.jdwechat.Utility;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;
import java.math.BigDecimal;
/**
* 业务_许可证   1 人力资源服务许可证  2 劳务派遣经营许可证
* Author: zhuqiang4433@gmail.com
* Version: CodeGenerator 1.1
* Memo: Auto Created by CodeGenerator on 2020/4/20.
*/

public class TYwXkzModel extends BaseModel implements IBaseModel{
	 public static final String TABLE_NAME="T_YW_XKZ";
	 public static final String SCRIPT_INSERT="INSERT INTO t_yw_xkz(xkz_xh,lx_dm,xkz_bh,jgdw_mc,lbzh,tyshxydm,dzzs,fddbr_fzr,lxdh,zczb_je,jgxz,xkwh,fwfw,fzrq,yxrq_jz,yxrq_qs,fzjg,fj_xh,sfyx_bj,ms,bz,lrry_xh,xgry_xh,lr_sj,xg_sj)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	 public static final String SCRIPT_DELETE="DELETE FROM t_yw_xkz WHERE  xkz_xh=? ";
	 public static final String SCRIPT_UPDATE="UPDATE t_yw_xkz  SET {0} WHERE  xkz_xh=? ";
	 public static final String SCRIPT_SELECT="SELECT xkz_xh,lx_dm,xkz_bh,jgdw_mc,lbzh,tyshxydm,dzzs,fddbr_fzr,lxdh,zczb_je,jgxz,xkwh,fwfw,fzrq,yxrq_jz,yxrq_qs,fzjg,fj_xh,sfyx_bj,ms,bz,lrry_xh,xgry_xh,lr_sj,xg_sj FROM t_yw_xkz WHERE (1=1)";
	 public String getDataTableName() {
 	 return TABLE_NAME;
	 }


	/*序号*/ 
	protected String xkz_xh;
	public String getxkz_xh (){ return xkz_xh;}
	public void setxkz_xh(String value){
		this.xkz_xh=value;
 		this.onChangeProperty("xkz_xh",this.xkz_xh,value);
 	}

	/*类型代码 1 人力资源服务许可证  2 劳务派遣经营许可证*/ 
	protected String lx_dm;
	public String getlx_dm (){ return lx_dm;}
	public void setlx_dm(String value){
		this.lx_dm=value;
 		this.onChangeProperty("lx_dm",this.lx_dm,value);
 	}

	/*许可证编号*/ 
	protected String xkz_bh;
	public String getxkz_bh (){ return xkz_bh;}
	public void setxkz_bh(String value){
		this.xkz_bh=value;
 		this.onChangeProperty("xkz_bh",this.xkz_bh,value);
 	}

	/*机构单位名称*/ 
	protected String jgdw_mc;
	public String getjgdw_mc (){ return jgdw_mc;}
	public void setjgdw_mc(String value){
		this.jgdw_mc=value;
 		this.onChangeProperty("jgdw_mc",this.jgdw_mc,value);
 	}

	/*劳保证号*/ 
	protected String lbzh;
	public String getlbzh (){ return lbzh;}
	public void setlbzh(String value){
		this.lbzh=value;
 		this.onChangeProperty("lbzh",this.lbzh,value);
 	}

	/*统一社会信用代码*/ 
	protected String tyshxydm;
	public String gettyshxydm (){ return tyshxydm;}
	public void settyshxydm(String value){
		this.tyshxydm=value;
 		this.onChangeProperty("tyshxydm",this.tyshxydm,value);
 	}

	/*地址住所*/ 
	protected String dzzs;
	public String getdzzs (){ return dzzs;}
	public void setdzzs(String value){
		this.dzzs=value;
 		this.onChangeProperty("dzzs",this.dzzs,value);
 	}

	/*法定代表人负责人*/ 
	protected String fddbr_fzr;
	public String getfddbr_fzr (){ return fddbr_fzr;}
	public void setfddbr_fzr(String value){
		this.fddbr_fzr=value;
 		this.onChangeProperty("fddbr_fzr",this.fddbr_fzr,value);
 	}

	/*联系电话*/ 
	protected String lxdh;
	public String getlxdh (){ return lxdh;}
	public void setlxdh(String value){
		this.lxdh=value;
 		this.onChangeProperty("lxdh",this.lxdh,value);
 	}

	/*注册资本（万元）*/ 
	protected Double zczb_je;
	public Double getzczb_je (){ return zczb_je;}
	public void setzczb_je(Double value){
		this.zczb_je=value;
 		this.onChangeProperty("zczb_je",this.zczb_je,value);
 	}

	/*机构性质(民营企业)*/ 
	protected String jgxz;
	public String getjgxz (){ return jgxz;}
	public void setjgxz(String value){
		this.jgxz=value;
 		this.onChangeProperty("jgxz",this.jgxz,value);
 	}

	/*许可文号*/ 
	protected String xkwh;
	public String getxkwh (){ return xkwh;}
	public void setxkwh(String value){
		this.xkwh=value;
 		this.onChangeProperty("xkwh",this.xkwh,value);
 	}

	/*服务范围*/ 
	protected String fwfw;
	public String getfwfw (){ return fwfw;}
	public void setfwfw(String value){
		this.fwfw=value;
 		this.onChangeProperty("fwfw",this.fwfw,value);
 	}

	/*发证日期*/ 
	protected Date fzrq;
	public Date getfzrq (){ return fzrq;}
	public void setfzrq(Date value){
		this.fzrq=value;
 		this.onChangeProperty("fzrq",this.fzrq,value);
 	}

	/*有效日期——截止*/ 
	protected Date yxrq_jz;
	public Date getyxrq_jz (){ return yxrq_jz;}
	public void setyxrq_jz(Date value){
		this.yxrq_jz=value;
 		this.onChangeProperty("yxrq_jz",this.yxrq_jz,value);
 	}

	/*有效日期-起始*/ 
	protected Date yxrq_qs;
	public Date getyxrq_qs (){ return yxrq_qs;}
	public void setyxrq_qs(Date value){
		this.yxrq_qs=value;
 		this.onChangeProperty("yxrq_qs",this.yxrq_qs,value);
 	}

	/*发证机构*/ 
	protected String fzjg;
	public String getfzjg (){ return fzjg;}
	public void setfzjg(String value){
		this.fzjg=value;
 		this.onChangeProperty("fzjg",this.fzjg,value);
 	}

	/*附件*/ 
	protected String fj_xh;
	public String getfj_xh (){ return fj_xh;}
	public void setfj_xh(String value){
		this.fj_xh=value;
 		this.onChangeProperty("fj_xh",this.fj_xh,value);
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



	public TYwXkzModel(){
		super();
		this._keyProperty.add("xkz_xh");
		initFieldsType();
	}

	private void initFieldsType(){
		this._fieldType.put("xkz_xh","String");
		this._fieldType.put("lx_dm","String");
		this._fieldType.put("xkz_bh","String");
		this._fieldType.put("jgdw_mc","String");
		this._fieldType.put("lbzh","String");
		this._fieldType.put("tyshxydm","String");
		this._fieldType.put("dzzs","String");
		this._fieldType.put("fddbr_fzr","String");
		this._fieldType.put("lxdh","String");
		this._fieldType.put("zczb_je","Double");
		this._fieldType.put("jgxz","String");
		this._fieldType.put("xkwh","String");
		this._fieldType.put("fwfw","String");
		this._fieldType.put("fzrq","Date");
		this._fieldType.put("yxrq_jz","Date");
		this._fieldType.put("yxrq_qs","Date");
		this._fieldType.put("fzjg","String");
		this._fieldType.put("fj_xh","String");
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
		 if(isExistColumn(resultSet,"xkz_xh")){
			 this.xkz_xh=resultSet.getString("xkz_xh");
		}
		 if(isExistColumn(resultSet,"lx_dm")){
			 this.lx_dm=resultSet.getString("lx_dm");
		}
		 if(isExistColumn(resultSet,"xkz_bh")){
			 this.xkz_bh=resultSet.getString("xkz_bh");
		}
		 if(isExistColumn(resultSet,"jgdw_mc")){
			 this.jgdw_mc=resultSet.getString("jgdw_mc");
		}
		 if(isExistColumn(resultSet,"lbzh")){
			 this.lbzh=resultSet.getString("lbzh");
		}
		 if(isExistColumn(resultSet,"tyshxydm")){
			 this.tyshxydm=resultSet.getString("tyshxydm");
		}
		 if(isExistColumn(resultSet,"dzzs")){
			 this.dzzs=resultSet.getString("dzzs");
		}
		 if(isExistColumn(resultSet,"fddbr_fzr")){
			 this.fddbr_fzr=resultSet.getString("fddbr_fzr");
		}
		 if(isExistColumn(resultSet,"lxdh")){
			 this.lxdh=resultSet.getString("lxdh");
		}
		 if(isExistColumn(resultSet,"zczb_je")){
			 this.zczb_je=resultSet.getDouble("zczb_je");
			if (resultSet.wasNull()) {
				this.zczb_je=null;
			}
		}
		 if(isExistColumn(resultSet,"jgxz")){
			 this.jgxz=resultSet.getString("jgxz");
		}
		 if(isExistColumn(resultSet,"xkwh")){
			 this.xkwh=resultSet.getString("xkwh");
		}
		 if(isExistColumn(resultSet,"fwfw")){
			 this.fwfw=resultSet.getString("fwfw");
		}
		 if(isExistColumn(resultSet,"fzrq")){
			 this.fzrq=(Date)resultSet.getObject("fzrq");
		}
		 if(isExistColumn(resultSet,"yxrq_jz")){
			 this.yxrq_jz=(Date)resultSet.getObject("yxrq_jz");
		}
		 if(isExistColumn(resultSet,"yxrq_qs")){
			 this.yxrq_qs=(Date)resultSet.getObject("yxrq_qs");
		}
		 if(isExistColumn(resultSet,"fzjg")){
			 this.fzjg=resultSet.getString("fzjg");
		}
		 if(isExistColumn(resultSet,"fj_xh")){
			 this.fj_xh=resultSet.getString("fj_xh");
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
		    sql+=" where   xkz_xh="+Utility.getSqlFielStatement("string",this.xkz_xh)+"  ";
	    return sql;
   }


   @Override
   public String getInsert() {
       String sql="insert into "+TABLE_NAME+"(xkz_xh,lx_dm,xkz_bh,jgdw_mc,lbzh,tyshxydm,dzzs,fddbr_fzr,lxdh,zczb_je,jgxz,xkwh,fwfw,fzrq,yxrq_jz,yxrq_qs,fzjg,fj_xh,sfyx_bj,ms,bz,lrry_xh,xgry_xh,lr_sj,xg_sj) values("+Utility.getSqlFielStatement("string",this.xkz_xh)+","+Utility.getSqlFielStatement("string",this.lx_dm)+","+Utility.getSqlFielStatement("string",this.xkz_bh)+","+Utility.getSqlFielStatement("string",this.jgdw_mc)+","+Utility.getSqlFielStatement("string",this.lbzh)+","+Utility.getSqlFielStatement("string",this.tyshxydm)+","+Utility.getSqlFielStatement("string",this.dzzs)+","+Utility.getSqlFielStatement("string",this.fddbr_fzr)+","+Utility.getSqlFielStatement("string",this.lxdh)+","+this.zczb_je+","+Utility.getSqlFielStatement("string",this.jgxz)+","+Utility.getSqlFielStatement("string",this.xkwh)+","+Utility.getSqlFielStatement("string",this.fwfw)+","+Utility.getSqlFielStatement("date",this.fzrq)+","+Utility.getSqlFielStatement("date",this.yxrq_jz)+","+Utility.getSqlFielStatement("date",this.yxrq_qs)+","+Utility.getSqlFielStatement("string",this.fzjg)+","+Utility.getSqlFielStatement("string",this.fj_xh)+","+this.sfyx_bj+","+Utility.getSqlFielStatement("string",this.ms)+","+Utility.getSqlFielStatement("string",this.bz)+","+Utility.getSqlFielStatement("string",this.lrry_xh)+","+Utility.getSqlFielStatement("string",this.xgry_xh)+","+Utility.getSqlFielStatement("date",this.lr_sj)+","+Utility.getSqlFielStatement("date",this.xg_sj)+")";
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
   sql+= " where  xkz_xh="+Utility.getSqlFielStatement("string",this.xkz_xh)+" ";
   }
   return sql;
}


	@Override
	public String getSelect() {
		String sql="select xkz_xh,lx_dm,xkz_bh,jgdw_mc,lbzh,tyshxydm,dzzs,fddbr_fzr,lxdh,zczb_je,jgxz,xkwh,fwfw,fzrq,yxrq_jz,yxrq_qs,fzjg,fj_xh,sfyx_bj,ms,bz,lrry_xh,xgry_xh,lr_sj,xg_sj from "+TABLE_NAME;
		if(!Utility.isNullOrEmpty(xkz_xh)){
			sql+=" where  xkz_xh="+Utility.getSqlFielStatement("string",this.xkz_xh)+" ";
		}
		return sql;
	}
	@Override
	public String getSelectByCondition(String condition) {
		String sql="select xkz_xh,lx_dm,xkz_bh,jgdw_mc,lbzh,tyshxydm,dzzs,fddbr_fzr,lxdh,zczb_je,jgxz,xkwh,fwfw,fzrq,yxrq_jz,yxrq_qs,fzjg,fj_xh,sfyx_bj,ms,bz,lrry_xh,xgry_xh,lr_sj,xg_sj from "+TABLE_NAME;
		if(condition!=null&&condition!=""){
			sql+=" where "+condition;
		}
		return sql;
	}


    @Override
   public String getDelete() {
	 String sql="delete from "+TABLE_NAME+" where  xkz_xh="+Utility.getSqlFielStatement("string",this.xkz_xh)+" ";
	 return sql;
   }


}

