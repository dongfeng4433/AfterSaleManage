package com.joindoo.jdwechat.beans;

import com.joindoo.jdwechat.Utility;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;
import java.math.BigDecimal;
/**
* 系统 - 权限 - 部门
* Author: zhuqiang4433@gmail.com
* Version: CodeGenerator 1.1
* Memo: Auto Created by CodeGenerator on 2020/4/20.
*/

public class TXtQxBmModel extends BaseModel implements IBaseModel{
	 public static final String TABLE_NAME="T_XT_QX_BM";
	 public static final String SCRIPT_INSERT="INSERT INTO t_xt_qx_bm(bm_dm,mc,ms,wz_px,lr_sj,xg_sj,lrry_xh,xgry_xh,sfyx_bj,yyz_xh)VALUES(?,?,?,?,?,?,?,?,?,?)";
	 public static final String SCRIPT_DELETE="DELETE FROM t_xt_qx_bm WHERE  bm_dm=? ";
	 public static final String SCRIPT_UPDATE="UPDATE t_xt_qx_bm  SET {0} WHERE  bm_dm=? ";
	 public static final String SCRIPT_SELECT="SELECT bm_dm,mc,ms,wz_px,lr_sj,xg_sj,lrry_xh,xgry_xh,sfyx_bj,yyz_xh FROM t_xt_qx_bm WHERE (1=1)";
	 public String getDataTableName() {
 	 return TABLE_NAME;
	 }


	/*部门*/ 
	protected String bm_dm;
	public String getbm_dm (){ return bm_dm;}
	public void setbm_dm(String value){
		this.bm_dm=value;
 		this.onChangeProperty("bm_dm",this.bm_dm,value);
 	}

	/*部门名称*/ 
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

	/*拥有者序号（企业id）*/ 
	protected String yyz_xh;
	public String getyyz_xh (){ return yyz_xh;}
	public void setyyz_xh(String value){
		this.yyz_xh=value;
 		this.onChangeProperty("yyz_xh",this.yyz_xh,value);
 	}



	public TXtQxBmModel(){
		super();
		this._keyProperty.add("bm_dm");
		initFieldsType();
	}

	private void initFieldsType(){
		this._fieldType.put("bm_dm","String");
		this._fieldType.put("mc","String");
		this._fieldType.put("ms","String");
		this._fieldType.put("wz_px","Integer");
		this._fieldType.put("lr_sj","Date");
		this._fieldType.put("xg_sj","Date");
		this._fieldType.put("lrry_xh","String");
		this._fieldType.put("xgry_xh","String");
		this._fieldType.put("sfyx_bj","Integer");
		this._fieldType.put("yyz_xh","String");
	}
	@Override
	public void initAsInsert() {

	}
	@Override
	public void fillModel(ResultSet resultSet) {
		try { 
		 if(isExistColumn(resultSet,"bm_dm")){
			 this.bm_dm=resultSet.getString("bm_dm");
		}
		 if(isExistColumn(resultSet,"mc")){
			 this.mc=resultSet.getString("mc");
		}
		 if(isExistColumn(resultSet,"ms")){
			 this.ms=resultSet.getString("ms");
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
		 if(isExistColumn(resultSet,"yyz_xh")){
			 this.yyz_xh=resultSet.getString("yyz_xh");
		}
      } catch (SQLException e) {
              e.printStackTrace();
          }
      }
  @Override
   public String getExist() {
	    String sql="select 1 from "+TABLE_NAME;
		    sql+=" where   bm_dm="+Utility.getSqlFielStatement("string",this.bm_dm)+"  ";
	    return sql;
   }


   @Override
   public String getInsert() {
       String sql="insert into "+TABLE_NAME+"(bm_dm,mc,ms,wz_px,lr_sj,xg_sj,lrry_xh,xgry_xh,sfyx_bj,yyz_xh) values("+Utility.getSqlFielStatement("string",this.bm_dm)+","+Utility.getSqlFielStatement("string",this.mc)+","+Utility.getSqlFielStatement("string",this.ms)+","+this.wz_px+","+Utility.getSqlFielStatement("date",this.lr_sj)+","+Utility.getSqlFielStatement("date",this.xg_sj)+","+Utility.getSqlFielStatement("string",this.lrry_xh)+","+Utility.getSqlFielStatement("string",this.xgry_xh)+","+this.sfyx_bj+","+Utility.getSqlFielStatement("string",this.yyz_xh)+")";
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
   sql+= " where  bm_dm="+Utility.getSqlFielStatement("string",this.bm_dm)+" ";
   }
   return sql;
}


	@Override
	public String getSelect() {
		String sql="select bm_dm,mc,ms,wz_px,lr_sj,xg_sj,lrry_xh,xgry_xh,sfyx_bj,yyz_xh from "+TABLE_NAME;
		if(!Utility.isNullOrEmpty(bm_dm)){
			sql+=" where  bm_dm="+Utility.getSqlFielStatement("string",this.bm_dm)+" ";
		}
		return sql;
	}
	@Override
	public String getSelectByCondition(String condition) {
		String sql="select bm_dm,mc,ms,wz_px,lr_sj,xg_sj,lrry_xh,xgry_xh,sfyx_bj,yyz_xh from "+TABLE_NAME;
		if(condition!=null&&condition!=""){
			sql+=" where "+condition;
		}
		return sql;
	}


    @Override
   public String getDelete() {
	 String sql="delete from "+TABLE_NAME+" where  bm_dm="+Utility.getSqlFielStatement("string",this.bm_dm)+" ";
	 return sql;
   }


}

