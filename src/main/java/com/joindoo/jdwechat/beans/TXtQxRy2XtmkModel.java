package com.joindoo.jdwechat.beans;

import com.joindoo.jdwechat.Utility;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;
import java.math.BigDecimal;
/**
* 系统 - 权限 - 系统用户 - 2 - 系统模块
* Author: zhuqiang4433@gmail.com
* Version: CodeGenerator 1.1
* Memo: Auto Created by CodeGenerator on 2020/4/3.
*/

public class TXtQxRy2XtmkModel extends BaseModel implements IBaseModel{
	 public static final String TABLE_NAME="T_XT_QX_RY_2_XTMK";
	 public static final String SCRIPT_INSERT="INSERT INTO t_xt_qx_ry_2_xtmk(ry_xh,xtmk_dm,sfsdtj_bj,sfjy_bj,ms,lr_sj,xg_sj,lrry_xh,xgry_xh,sfyx_bj)VALUES(?,?,?,?,?,?,?,?,?,?)";
	 public static final String SCRIPT_DELETE="DELETE FROM t_xt_qx_ry_2_xtmk WHERE  ry_xh=? AND xtmk_dm=? ";
	 public static final String SCRIPT_UPDATE="UPDATE t_xt_qx_ry_2_xtmk  SET {0} WHERE  ry_xh=? AND xtmk_dm=? ";
	 public static final String SCRIPT_SELECT="SELECT ry_xh,xtmk_dm,sfsdtj_bj,sfjy_bj,ms,lr_sj,xg_sj,lrry_xh,xgry_xh,sfyx_bj FROM t_xt_qx_ry_2_xtmk WHERE (1=1)";
	 public String getDataTableName() {
 	 return TABLE_NAME;
	 }


	/*(外键:系统用户) 系统用户*/ 
	protected String ry_xh;
	public String getry_xh (){ return ry_xh;}
	public void setry_xh(String value){
		this.ry_xh=value;
 		this.onChangeProperty("ry_xh",this.ry_xh,value);
 	}

	/*(外键:系统角色) 系统角色*/ 
	protected String xtmk_dm;
	public String getxtmk_dm (){ return xtmk_dm;}
	public void setxtmk_dm(String value){
		this.xtmk_dm=value;
 		this.onChangeProperty("xtmk_dm",this.xtmk_dm,value);
 	}

	/*是否为固定的，即手动添加的，而不是通过角色计算的, 如果是通过角色计算的那么在角色发生变更时将会重新计算和设置*/ 
	protected Integer sfsdtj_bj;
	public Integer getsfsdtj_bj (){ return sfsdtj_bj;}
	public void setsfsdtj_bj(Integer value){
		this.sfsdtj_bj=value;
 		this.onChangeProperty("sfsdtj_bj",this.sfsdtj_bj,value);
 	}

	/*是否禁用标记，代表对于该用户，该功能点是禁用的，注意禁用优先*/ 
	protected Integer sfjy_bj;
	public Integer getsfjy_bj (){ return sfjy_bj;}
	public void setsfjy_bj(Integer value){
		this.sfjy_bj=value;
 		this.onChangeProperty("sfjy_bj",this.sfjy_bj,value);
 	}

	/*描述*/ 
	protected String ms;
	public String getms (){ return ms;}
	public void setms(String value){
		this.ms=value;
 		this.onChangeProperty("ms",this.ms,value);
 	}

	/*创建时间*/ 
	protected Date lr_sj;
	public Date getlr_sj (){ return lr_sj;}
	public void setlr_sj(Date value){
		this.lr_sj=value;
 		this.onChangeProperty("lr_sj",this.lr_sj,value);
 	}

	/*最后修改时间*/ 
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

	/*有效标记(0无效，1有效)*/ 
	protected Integer sfyx_bj;
	public Integer getsfyx_bj (){ return sfyx_bj;}
	public void setsfyx_bj(Integer value){
		this.sfyx_bj=value;
 		this.onChangeProperty("sfyx_bj",this.sfyx_bj,value);
 	}



	public TXtQxRy2XtmkModel(){
		super();
		this._keyProperty.add("ry_xh");
		this._keyProperty.add("xtmk_dm");
		initFieldsType();
	}

	private void initFieldsType(){
		this._fieldType.put("ry_xh","String");
		this._fieldType.put("xtmk_dm","String");
		this._fieldType.put("sfsdtj_bj","Integer");
		this._fieldType.put("sfjy_bj","Integer");
		this._fieldType.put("ms","String");
		this._fieldType.put("lr_sj","Date");
		this._fieldType.put("xg_sj","Date");
		this._fieldType.put("lrry_xh","String");
		this._fieldType.put("xgry_xh","String");
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
		 if(isExistColumn(resultSet,"xtmk_dm")){
			 this.xtmk_dm=resultSet.getString("xtmk_dm");
		}
		 if(isExistColumn(resultSet,"sfsdtj_bj")){
			 this.sfsdtj_bj=resultSet.getInt("sfsdtj_bj");
		}
		 if(isExistColumn(resultSet,"sfjy_bj")){
			 this.sfjy_bj=resultSet.getInt("sfjy_bj");
		}
		 if(isExistColumn(resultSet,"ms")){
			 this.ms=resultSet.getString("ms");
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
      } catch (SQLException e) {
              e.printStackTrace();
          }
      }
  @Override
   public String getExist() {
	    String sql="select 1 from "+TABLE_NAME;
		    sql+=" where   ry_xh="+Utility.getSqlFielStatement("string",this.ry_xh)+" and xtmk_dm="+Utility.getSqlFielStatement("string",this.xtmk_dm)+"  ";
	    return sql;
   }


   @Override
   public String getInsert() {
       String sql="insert into "+TABLE_NAME+"(ry_xh,xtmk_dm,sfsdtj_bj,sfjy_bj,ms,lr_sj,xg_sj,lrry_xh,xgry_xh,sfyx_bj) values("+Utility.getSqlFielStatement("string",this.ry_xh)+","+Utility.getSqlFielStatement("string",this.xtmk_dm)+","+this.sfsdtj_bj+","+this.sfjy_bj+","+Utility.getSqlFielStatement("string",this.ms)+","+Utility.getSqlFielStatement("date",this.lr_sj)+","+Utility.getSqlFielStatement("date",this.xg_sj)+","+Utility.getSqlFielStatement("string",this.lrry_xh)+","+Utility.getSqlFielStatement("string",this.xgry_xh)+","+this.sfyx_bj+")";
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
   sql+= " where  ry_xh="+Utility.getSqlFielStatement("string",this.ry_xh)+" and xtmk_dm="+Utility.getSqlFielStatement("string",this.xtmk_dm)+" ";
   }
   return sql;
}


	@Override
	public String getSelect() {
		String sql="select ry_xh,xtmk_dm,sfsdtj_bj,sfjy_bj,ms,lr_sj,xg_sj,lrry_xh,xgry_xh,sfyx_bj from "+TABLE_NAME;
		if(!Utility.isNullOrEmpty(ry_xh)&&!Utility.isNullOrEmpty(xtmk_dm)){
			sql+=" where  ry_xh="+Utility.getSqlFielStatement("string",this.ry_xh)+" and xtmk_dm="+Utility.getSqlFielStatement("string",this.xtmk_dm)+" ";
		}
		return sql;
	}
	@Override
	public String getSelectByCondition(String condition) {
		String sql="select ry_xh,xtmk_dm,sfsdtj_bj,sfjy_bj,ms,lr_sj,xg_sj,lrry_xh,xgry_xh,sfyx_bj from "+TABLE_NAME;
		if(condition!=null&&condition!=""){
			sql+=" where "+condition;
		}
		return sql;
	}


    @Override
   public String getDelete() {
	 String sql="delete from "+TABLE_NAME+" where  ry_xh="+Utility.getSqlFielStatement("string",this.ry_xh)+" and xtmk_dm="+Utility.getSqlFielStatement("string",this.xtmk_dm)+" ";
	 return sql;
   }


}

