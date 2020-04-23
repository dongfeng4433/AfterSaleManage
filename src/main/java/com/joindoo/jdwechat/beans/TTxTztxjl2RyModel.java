package com.joindoo.jdwechat.beans;

import com.joindoo.jdwechat.Utility;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;
import java.math.BigDecimal;
/**
* 通讯 - 通知提醒记录 - 2 - 人员， 记录通知的人员信息
* Author: zhuqiang4433@gmail.com
* Version: CodeGenerator 1.1
* Memo: Auto Created by CodeGenerator on 2020/4/20.
*/

public class TTxTztxjl2RyModel extends BaseModel implements IBaseModel{
	 public static final String TABLE_NAME="T_TX_TZTXJL_2_RY";
	 public static final String SCRIPT_INSERT="INSERT INTO t_tx_tztxjl_2_ry(tztxjl_xh,ry_xh,cl_sj,clzt_dm,cl_nr,lr_sj,xg_sj,lrry_xh,xgry_xh,sfyx_bj)VALUES(?,?,?,?,?,?,?,?,?,?)";
	 public static final String SCRIPT_DELETE="DELETE FROM t_tx_tztxjl_2_ry WHERE  tztxjl_xh=? AND ry_xh=? ";
	 public static final String SCRIPT_UPDATE="UPDATE t_tx_tztxjl_2_ry  SET {0} WHERE  tztxjl_xh=? AND ry_xh=? ";
	 public static final String SCRIPT_SELECT="SELECT tztxjl_xh,ry_xh,cl_sj,clzt_dm,cl_nr,lr_sj,xg_sj,lrry_xh,xgry_xh,sfyx_bj FROM t_tx_tztxjl_2_ry WHERE (1=1)";
	 public String getDataTableName() {
 	 return TABLE_NAME;
	 }


	/*提醒记录序号*/ 
	protected String tztxjl_xh;
	public String gettztxjl_xh (){ return tztxjl_xh;}
	public void settztxjl_xh(String value){
		this.tztxjl_xh=value;
 		this.onChangeProperty("tztxjl_xh",this.tztxjl_xh,value);
 	}

	/*人员*/ 
	protected String ry_xh;
	public String getry_xh (){ return ry_xh;}
	public void setry_xh(String value){
		this.ry_xh=value;
 		this.onChangeProperty("ry_xh",this.ry_xh,value);
 	}

	/*处理时间*/ 
	protected Date cl_sj;
	public Date getcl_sj (){ return cl_sj;}
	public void setcl_sj(Date value){
		this.cl_sj=value;
 		this.onChangeProperty("cl_sj",this.cl_sj,value);
 	}

	/*处理状态代码1已处理*/ 
	protected String clzt_dm;
	public String getclzt_dm (){ return clzt_dm;}
	public void setclzt_dm(String value){
		this.clzt_dm=value;
 		this.onChangeProperty("clzt_dm",this.clzt_dm,value);
 	}

	/*处理内容*/ 
	protected String cl_nr;
	public String getcl_nr (){ return cl_nr;}
	public void setcl_nr(String value){
		this.cl_nr=value;
 		this.onChangeProperty("cl_nr",this.cl_nr,value);
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

	/**/ 
	protected Integer sfyx_bj;
	public Integer getsfyx_bj (){ return sfyx_bj;}
	public void setsfyx_bj(Integer value){
		this.sfyx_bj=value;
 		this.onChangeProperty("sfyx_bj",this.sfyx_bj,value);
 	}



	public TTxTztxjl2RyModel(){
		super();
		this._keyProperty.add("tztxjl_xh");
		this._keyProperty.add("ry_xh");
		initFieldsType();
	}

	private void initFieldsType(){
		this._fieldType.put("tztxjl_xh","String");
		this._fieldType.put("ry_xh","String");
		this._fieldType.put("cl_sj","Date");
		this._fieldType.put("clzt_dm","String");
		this._fieldType.put("cl_nr","String");
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
		 if(isExistColumn(resultSet,"tztxjl_xh")){
			 this.tztxjl_xh=resultSet.getString("tztxjl_xh");
		}
		 if(isExistColumn(resultSet,"ry_xh")){
			 this.ry_xh=resultSet.getString("ry_xh");
		}
		 if(isExistColumn(resultSet,"cl_sj")){
			 this.cl_sj=(Date)resultSet.getObject("cl_sj");
		}
		 if(isExistColumn(resultSet,"clzt_dm")){
			 this.clzt_dm=resultSet.getString("clzt_dm");
		}
		 if(isExistColumn(resultSet,"cl_nr")){
			 this.cl_nr=resultSet.getString("cl_nr");
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
		    sql+=" where   tztxjl_xh="+Utility.getSqlFielStatement("string",this.tztxjl_xh)+" and ry_xh="+Utility.getSqlFielStatement("string",this.ry_xh)+"  ";
	    return sql;
   }


   @Override
   public String getInsert() {
       String sql="insert into "+TABLE_NAME+"(tztxjl_xh,ry_xh,cl_sj,clzt_dm,cl_nr,lr_sj,xg_sj,lrry_xh,xgry_xh,sfyx_bj) values("+Utility.getSqlFielStatement("string",this.tztxjl_xh)+","+Utility.getSqlFielStatement("string",this.ry_xh)+","+Utility.getSqlFielStatement("date",this.cl_sj)+","+Utility.getSqlFielStatement("string",this.clzt_dm)+","+Utility.getSqlFielStatement("string",this.cl_nr)+","+Utility.getSqlFielStatement("date",this.lr_sj)+","+Utility.getSqlFielStatement("date",this.xg_sj)+","+Utility.getSqlFielStatement("string",this.lrry_xh)+","+Utility.getSqlFielStatement("string",this.xgry_xh)+","+this.sfyx_bj+")";
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
   sql+= " where  tztxjl_xh="+Utility.getSqlFielStatement("string",this.tztxjl_xh)+" and ry_xh="+Utility.getSqlFielStatement("string",this.ry_xh)+" ";
   }
   return sql;
}


	@Override
	public String getSelect() {
		String sql="select tztxjl_xh,ry_xh,cl_sj,clzt_dm,cl_nr,lr_sj,xg_sj,lrry_xh,xgry_xh,sfyx_bj from "+TABLE_NAME;
		if(!Utility.isNullOrEmpty(tztxjl_xh)&&!Utility.isNullOrEmpty(ry_xh)){
			sql+=" where  tztxjl_xh="+Utility.getSqlFielStatement("string",this.tztxjl_xh)+" and ry_xh="+Utility.getSqlFielStatement("string",this.ry_xh)+" ";
		}
		return sql;
	}
	@Override
	public String getSelectByCondition(String condition) {
		String sql="select tztxjl_xh,ry_xh,cl_sj,clzt_dm,cl_nr,lr_sj,xg_sj,lrry_xh,xgry_xh,sfyx_bj from "+TABLE_NAME;
		if(condition!=null&&condition!=""){
			sql+=" where "+condition;
		}
		return sql;
	}


    @Override
   public String getDelete() {
	 String sql="delete from "+TABLE_NAME+" where  tztxjl_xh="+Utility.getSqlFielStatement("string",this.tztxjl_xh)+" and ry_xh="+Utility.getSqlFielStatement("string",this.ry_xh)+" ";
	 return sql;
   }


}

