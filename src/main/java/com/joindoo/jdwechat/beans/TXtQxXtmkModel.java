package com.joindoo.jdwechat.beans;

import com.joindoo.jdwechat.Utility;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;
import java.math.BigDecimal;
/**
* 系统 - 权限 - 系统模块（菜单）
* Author: zhuqiang4433@gmail.com
* Version: CodeGenerator 1.1
* Memo: Auto Created by CodeGenerator on 2020/4/3.
*/

public class TXtQxXtmkModel extends BaseModel implements IBaseModel{
	 public static final String TABLE_NAME="T_XT_QX_XTMK";
	 public static final String SCRIPT_INSERT="INSERT INTO t_xt_qx_xtmk(xtmk_dm,mc,ms,url,wz_px,lr_sj,xg_sj,lrry_xh,xgry_xh,sfyx_bj,sj_xtmk_dm)VALUES(?,?,?,?,?,?,?,?,?,?,?)";
	 public static final String SCRIPT_DELETE="DELETE FROM t_xt_qx_xtmk WHERE  xtmk_dm=? ";
	 public static final String SCRIPT_UPDATE="UPDATE t_xt_qx_xtmk  SET {0} WHERE  xtmk_dm=? ";
	 public static final String SCRIPT_SELECT="SELECT xtmk_dm,mc,ms,url,wz_px,lr_sj,xg_sj,lrry_xh,xgry_xh,sfyx_bj,sj_xtmk_dm FROM t_xt_qx_xtmk WHERE (1=1)";
	 public String getDataTableName() {
 	 return TABLE_NAME;
	 }


	/*系统模块，即菜单*/ 
	protected String xtmk_dm;
	public String getxtmk_dm (){ return xtmk_dm;}
	public void setxtmk_dm(String value){
		this.xtmk_dm=value;
 		this.onChangeProperty("xtmk_dm",this.xtmk_dm,value);
 	}

	/*系统模块名称(可能是中文)*/ 
	protected String mc;
	public String getmc (){ return mc;}
	public void setmc(String value){
		this.mc=value;
 		this.onChangeProperty("mc",this.mc,value);
 	}

	/*描述*/ 
	protected String ms;
	public String getms (){ return ms;}
	public void setms(String value){
		this.ms=value;
 		this.onChangeProperty("ms",this.ms,value);
 	}

	/*功能点对应的URL*/ 
	protected String url;
	public String geturl (){ return url;}
	public void seturl(String value){
		this.url=value;
 		this.onChangeProperty("url",this.url,value);
 	}

	/*位置序号*/ 
	protected Integer wz_px;
	public Integer getwz_px (){ return wz_px;}
	public void setwz_px(Integer value){
		this.wz_px=value;
 		this.onChangeProperty("wz_px",this.wz_px,value);
 	}

	/*创建时间*/ 
	protected Date lr_sj;
	public Date getlr_sj (){ return lr_sj;}
	public void setlr_sj(Date value){
		this.lr_sj=value;
 		this.onChangeProperty("lr_sj",this.lr_sj,value);
 	}

	/*最后修改日期*/ 
	protected Date xg_sj;
	public Date getxg_sj (){ return xg_sj;}
	public void setxg_sj(Date value){
		this.xg_sj=value;
 		this.onChangeProperty("xg_sj",this.xg_sj,value);
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

	/*是否有效*/ 
	protected Integer sfyx_bj;
	public Integer getsfyx_bj (){ return sfyx_bj;}
	public void setsfyx_bj(Integer value){
		this.sfyx_bj=value;
 		this.onChangeProperty("sfyx_bj",this.sfyx_bj,value);
 	}

	/*上级系统模块代码*/ 
	protected String sj_xtmk_dm;
	public String getsj_xtmk_dm (){ return sj_xtmk_dm;}
	public void setsj_xtmk_dm(String value){
		this.sj_xtmk_dm=value;
 		this.onChangeProperty("sj_xtmk_dm",this.sj_xtmk_dm,value);
 	}



	public TXtQxXtmkModel(){
		super();
		this._keyProperty.add("xtmk_dm");
		initFieldsType();
	}

	private void initFieldsType(){
		this._fieldType.put("xtmk_dm","String");
		this._fieldType.put("mc","String");
		this._fieldType.put("ms","String");
		this._fieldType.put("url","String");
		this._fieldType.put("wz_px","Integer");
		this._fieldType.put("lr_sj","Date");
		this._fieldType.put("xg_sj","Date");
		this._fieldType.put("lrry_xh","String");
		this._fieldType.put("xgry_xh","String");
		this._fieldType.put("sfyx_bj","Integer");
		this._fieldType.put("sj_xtmk_dm","String");
	}
	@Override
	public void initAsInsert() {

	}
	@Override
	public void fillModel(ResultSet resultSet) {
		try { 
		 if(isExistColumn(resultSet,"xtmk_dm")){
			 this.xtmk_dm=resultSet.getString("xtmk_dm");
		}
		 if(isExistColumn(resultSet,"mc")){
			 this.mc=resultSet.getString("mc");
		}
		 if(isExistColumn(resultSet,"ms")){
			 this.ms=resultSet.getString("ms");
		}
		 if(isExistColumn(resultSet,"url")){
			 this.url=resultSet.getString("url");
		}
		 if(isExistColumn(resultSet,"wz_px")){
			 this.wz_px=resultSet.getInt("wz_px");
			if (resultSet.wasNull()) {
				this.wz_px=null;
			}
		}
		 if(isExistColumn(resultSet,"lr_sj")){
			 this.lr_sj=(Date)resultSet.getObject("lr_sj");
		}
		 if(isExistColumn(resultSet,"xg_sj")){
			 this.xg_sj=(Date)resultSet.getObject("xg_sj");
		}
		 if(isExistColumn(resultSet,"lrry_xh")){
			 this.lrry_xh=resultSet.getString("lrry_xh");
		}
		 if(isExistColumn(resultSet,"xgry_xh")){
			 this.xgry_xh=resultSet.getString("xgry_xh");
		}
		 if(isExistColumn(resultSet,"sfyx_bj")){
			 this.sfyx_bj=resultSet.getInt("sfyx_bj");
		}
		 if(isExistColumn(resultSet,"sj_xtmk_dm")){
			 this.sj_xtmk_dm=resultSet.getString("sj_xtmk_dm");
		}
      } catch (SQLException e) {
              e.printStackTrace();
          }
      }
  @Override
   public String getExist() {
	    String sql="select 1 from "+TABLE_NAME;
		    sql+=" where   xtmk_dm="+Utility.getSqlFielStatement("string",this.xtmk_dm)+"  ";
	    return sql;
   }


   @Override
   public String getInsert() {
       String sql="insert into "+TABLE_NAME+"(xtmk_dm,mc,ms,url,wz_px,lr_sj,xg_sj,lrry_xh,xgry_xh,sfyx_bj,sj_xtmk_dm) values("+Utility.getSqlFielStatement("string",this.xtmk_dm)+","+Utility.getSqlFielStatement("string",this.mc)+","+Utility.getSqlFielStatement("string",this.ms)+","+Utility.getSqlFielStatement("string",this.url)+","+this.wz_px+","+Utility.getSqlFielStatement("date",this.lr_sj)+","+Utility.getSqlFielStatement("date",this.xg_sj)+","+Utility.getSqlFielStatement("string",this.lrry_xh)+","+Utility.getSqlFielStatement("string",this.xgry_xh)+","+this.sfyx_bj+","+Utility.getSqlFielStatement("string",this.sj_xtmk_dm)+")";
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
   sql+= " where  xtmk_dm="+Utility.getSqlFielStatement("string",this.xtmk_dm)+" ";
   }
   return sql;
}


	@Override
	public String getSelect() {
		String sql="select xtmk_dm,mc,ms,url,wz_px,lr_sj,xg_sj,lrry_xh,xgry_xh,sfyx_bj,sj_xtmk_dm from "+TABLE_NAME;
		if(!Utility.isNullOrEmpty(xtmk_dm)){
			sql+=" where  xtmk_dm="+Utility.getSqlFielStatement("string",this.xtmk_dm)+" ";
		}
		return sql;
	}
	@Override
	public String getSelectByCondition(String condition) {
		String sql="select xtmk_dm,mc,ms,url,wz_px,lr_sj,xg_sj,lrry_xh,xgry_xh,sfyx_bj,sj_xtmk_dm from "+TABLE_NAME;
		if(condition!=null&&condition!=""){
			sql+=" where "+condition;
		}
		return sql;
	}


    @Override
   public String getDelete() {
	 String sql="delete from "+TABLE_NAME+" where  xtmk_dm="+Utility.getSqlFielStatement("string",this.xtmk_dm)+" ";
	 return sql;
   }


}

