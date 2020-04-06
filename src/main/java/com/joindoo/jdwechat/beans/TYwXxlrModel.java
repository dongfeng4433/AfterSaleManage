package com.joindoo.jdwechat.beans;

import com.joindoo.jdwechat.Utility;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;
import java.math.BigDecimal;
/**
* 业务_信息录入
* Author: zhuqiang4433@gmail.com
* Version: CodeGenerator 1.1
* Memo: Auto Created by CodeGenerator on 2020/4/5.
*/

public class TYwXxlrModel extends BaseModel implements IBaseModel{
	 public static final String TABLE_NAME="T_YW_XXLR";
	 public static final String SCRIPT_INSERT="INSERT INTO t_yw_xxlr(xxlr_xh,ry_name,ry_zj_hm,sqnr,sfyx_bj,fj_xh,hf_sj,sj_xxlr_xh,ms,bz,lrry_xh,xgry_xh,lr_sj,xg_sj,zt_dm)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	 public static final String SCRIPT_DELETE="DELETE FROM t_yw_xxlr WHERE  xxlr_xh=? ";
	 public static final String SCRIPT_UPDATE="UPDATE t_yw_xxlr  SET {0} WHERE  xxlr_xh=? ";
	 public static final String SCRIPT_SELECT="SELECT xxlr_xh,ry_name,ry_zj_hm,sqnr,sfyx_bj,fj_xh,hf_sj,sj_xxlr_xh,ms,bz,lrry_xh,xgry_xh,lr_sj,xg_sj,zt_dm FROM t_yw_xxlr WHERE (1=1)";
	 public String getDataTableName() {
 	 return TABLE_NAME;
	 }


	/*序号*/ 
	protected String xxlr_xh;
	public String getxxlr_xh (){ return xxlr_xh;}
	public void setxxlr_xh(String value){
		this.xxlr_xh=value;
 		this.onChangeProperty("xxlr_xh",this.xxlr_xh,value);
 	}

	/*姓名*/ 
	protected String ry_name;
	public String getry_name (){ return ry_name;}
	public void setry_name(String value){
		this.ry_name=value;
 		this.onChangeProperty("ry_name",this.ry_name,value);
 	}

	/*证件号码*/ 
	protected String ry_zj_hm;
	public String getry_zj_hm (){ return ry_zj_hm;}
	public void setry_zj_hm(String value){
		this.ry_zj_hm=value;
 		this.onChangeProperty("ry_zj_hm",this.ry_zj_hm,value);
 	}

	/*内容*/ 
	protected String sqnr;
	public String getsqnr (){ return sqnr;}
	public void setsqnr(String value){
		this.sqnr=value;
 		this.onChangeProperty("sqnr",this.sqnr,value);
 	}

	/*是否有效标记*/ 
	protected Integer sfyx_bj;
	public Integer getsfyx_bj (){ return sfyx_bj;}
	public void setsfyx_bj(Integer value){
		this.sfyx_bj=value;
 		this.onChangeProperty("sfyx_bj",this.sfyx_bj,value);
 	}

	/*附件*/ 
	protected String fj_xh;
	public String getfj_xh (){ return fj_xh;}
	public void setfj_xh(String value){
		this.fj_xh=value;
 		this.onChangeProperty("fj_xh",this.fj_xh,value);
 	}

	/*设定需要回复的时间*/ 
	protected Date hf_sj;
	public Date gethf_sj (){ return hf_sj;}
	public void sethf_sj(Date value){
		this.hf_sj=value;
 		this.onChangeProperty("hf_sj",this.hf_sj,value);
 	}

	/*上级信息录入序号*/ 
	protected String sj_xxlr_xh;
	public String getsj_xxlr_xh (){ return sj_xxlr_xh;}
	public void setsj_xxlr_xh(String value){
		this.sj_xxlr_xh=value;
 		this.onChangeProperty("sj_xxlr_xh",this.sj_xxlr_xh,value);
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

	/*用于查询展示*/ 
	protected String zt_dm;
	public String getzt_dm (){ return zt_dm;}
	public void setzt_dm(String value){
		this.zt_dm=value;
 		this.onChangeProperty("zt_dm",this.zt_dm,value);
 	}



	public TYwXxlrModel(){
		super();
		this._keyProperty.add("xxlr_xh");
		initFieldsType();
	}

	private void initFieldsType(){
		this._fieldType.put("xxlr_xh","String");
		this._fieldType.put("ry_name","String");
		this._fieldType.put("ry_zj_hm","String");
		this._fieldType.put("sqnr","String");
		this._fieldType.put("sfyx_bj","Integer");
		this._fieldType.put("fj_xh","String");
		this._fieldType.put("hf_sj","Date");
		this._fieldType.put("sj_xxlr_xh","String");
		this._fieldType.put("ms","String");
		this._fieldType.put("bz","String");
		this._fieldType.put("lrry_xh","String");
		this._fieldType.put("xgry_xh","String");
		this._fieldType.put("lr_sj","Date");
		this._fieldType.put("xg_sj","Date");
		this._fieldType.put("zt_dm","String");
	}
	@Override
	public void initAsInsert() {

	}
	@Override
	public void fillModel(ResultSet resultSet) {
		try { 
		 if(isExistColumn(resultSet,"xxlr_xh")){
			 this.xxlr_xh=resultSet.getString("xxlr_xh");
		}
		 if(isExistColumn(resultSet,"ry_name")){
			 this.ry_name=resultSet.getString("ry_name");
		}
		 if(isExistColumn(resultSet,"ry_zj_hm")){
			 this.ry_zj_hm=resultSet.getString("ry_zj_hm");
		}
		 if(isExistColumn(resultSet,"sqnr")){
			 this.sqnr=resultSet.getString("sqnr");
		}
		 if(isExistColumn(resultSet,"sfyx_bj")){
			 this.sfyx_bj=resultSet.getInt("sfyx_bj");
		}
		 if(isExistColumn(resultSet,"fj_xh")){
			 this.fj_xh=resultSet.getString("fj_xh");
		}
		 if(isExistColumn(resultSet,"hf_sj")){
			 this.hf_sj=(Date)resultSet.getObject("hf_sj");
		}
		 if(isExistColumn(resultSet,"sj_xxlr_xh")){
			 this.sj_xxlr_xh=resultSet.getString("sj_xxlr_xh");
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
		 if(isExistColumn(resultSet,"zt_dm")){
			 this.zt_dm=resultSet.getString("zt_dm");
		}
      } catch (SQLException e) {
              e.printStackTrace();
          }
      }
  @Override
   public String getExist() {
	    String sql="select 1 from "+TABLE_NAME;
		    sql+=" where   xxlr_xh="+Utility.getSqlFielStatement("string",this.xxlr_xh)+"  ";
	    return sql;
   }


   @Override
   public String getInsert() {
       String sql="insert into "+TABLE_NAME+"(xxlr_xh,ry_name,ry_zj_hm,sqnr,sfyx_bj,fj_xh,hf_sj,sj_xxlr_xh,ms,bz,lrry_xh,xgry_xh,lr_sj,xg_sj,zt_dm) values("+Utility.getSqlFielStatement("string",this.xxlr_xh)+","+Utility.getSqlFielStatement("string",this.ry_name)+","+Utility.getSqlFielStatement("string",this.ry_zj_hm)+","+Utility.getSqlFielStatement("string",this.sqnr)+","+this.sfyx_bj+","+Utility.getSqlFielStatement("string",this.fj_xh)+","+Utility.getSqlFielStatement("date",this.hf_sj)+","+Utility.getSqlFielStatement("string",this.sj_xxlr_xh)+","+Utility.getSqlFielStatement("string",this.ms)+","+Utility.getSqlFielStatement("string",this.bz)+","+Utility.getSqlFielStatement("string",this.lrry_xh)+","+Utility.getSqlFielStatement("string",this.xgry_xh)+","+Utility.getSqlFielStatement("date",this.lr_sj)+","+Utility.getSqlFielStatement("date",this.xg_sj)+","+Utility.getSqlFielStatement("string",this.zt_dm)+")";
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
   sql+= " where  xxlr_xh="+Utility.getSqlFielStatement("string",this.xxlr_xh)+" ";
   }
   return sql;
}


	@Override
	public String getSelect() {
		String sql="select xxlr_xh,ry_name,ry_zj_hm,sqnr,sfyx_bj,fj_xh,hf_sj,sj_xxlr_xh,ms,bz,lrry_xh,xgry_xh,lr_sj,xg_sj,zt_dm from "+TABLE_NAME;
		if(!Utility.isNullOrEmpty(xxlr_xh)){
			sql+=" where  xxlr_xh="+Utility.getSqlFielStatement("string",this.xxlr_xh)+" ";
		}
		return sql;
	}
	@Override
	public String getSelectByCondition(String condition) {
		String sql="select xxlr_xh,ry_name,ry_zj_hm,sqnr,sfyx_bj,fj_xh,hf_sj,sj_xxlr_xh,ms,bz,lrry_xh,xgry_xh,lr_sj,xg_sj,zt_dm from "+TABLE_NAME;
		if(condition!=null&&condition!=""){
			sql+=" where "+condition;
		}
		return sql;
	}


    @Override
   public String getDelete() {
	 String sql="delete from "+TABLE_NAME+" where  xxlr_xh="+Utility.getSqlFielStatement("string",this.xxlr_xh)+" ";
	 return sql;
   }


}

