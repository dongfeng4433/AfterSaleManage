package com.joindoo.jdwechat.beans;

import com.joindoo.jdwechat.Utility;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;
import java.math.BigDecimal;
/**
* 通讯 - 通知提醒记录， 除工作流外的提醒记录，便于提醒, 用于系统内提醒, 与移动终端提醒不相同，这是所有提醒通知的源
* Author: zhuqiang4433@gmail.com
* Version: CodeGenerator 1.1
* Memo: Auto Created by CodeGenerator on 2020/4/4.
*/

public class TTxTztxjlModel extends BaseModel implements IBaseModel{
	 public static final String TABLE_NAME="T_TX_TZTXJL";
	 public static final String SCRIPT_INSERT="INSERT INTO t_tx_tztxjl(tztxjl_xh,bt,tx_nr,tzry_sl,sx_sj,fqry_xh,xgjl_zj_xh,lrry_xh,xgry_xh,lr_sj,xg_sj)VALUES(?,?,?,?,?,?,?,?,?,?,?)";
	 public static final String SCRIPT_DELETE="DELETE FROM t_tx_tztxjl WHERE  tztxjl_xh=? ";
	 public static final String SCRIPT_UPDATE="UPDATE t_tx_tztxjl  SET {0} WHERE  tztxjl_xh=? ";
	 public static final String SCRIPT_SELECT="SELECT tztxjl_xh,bt,tx_nr,tzry_sl,sx_sj,fqry_xh,xgjl_zj_xh,lrry_xh,xgry_xh,lr_sj,xg_sj FROM t_tx_tztxjl WHERE (1=1)";
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

	/*标题*/ 
	protected String bt;
	public String getbt (){ return bt;}
	public void setbt(String value){
		this.bt=value;
 		this.onChangeProperty("bt",this.bt,value);
 	}

	/*内容*/ 
	protected String tx_nr;
	public String gettx_nr (){ return tx_nr;}
	public void settx_nr(String value){
		this.tx_nr=value;
 		this.onChangeProperty("tx_nr",this.tx_nr,value);
 	}

	/*通知人员数量*/ 
	protected Integer tzry_sl;
	public Integer gettzry_sl (){ return tzry_sl;}
	public void settzry_sl(Integer value){
		this.tzry_sl=value;
 		this.onChangeProperty("tzry_sl",this.tzry_sl,value);
 	}

	/*有效时间，如果时间到了则自动清除本记录*/ 
	protected Date sx_sj;
	public Date getsx_sj (){ return sx_sj;}
	public void setsx_sj(Date value){
		this.sx_sj=value;
 		this.onChangeProperty("sx_sj",this.sx_sj,value);
 	}

	/*发起人员序号*/ 
	protected String fqry_xh;
	public String getfqry_xh (){ return fqry_xh;}
	public void setfqry_xh(String value){
		this.fqry_xh=value;
 		this.onChangeProperty("fqry_xh",this.fqry_xh,value);
 	}

	/*相关记录序号*/ 
	protected String xgjl_zj_xh;
	public String getxgjl_zj_xh (){ return xgjl_zj_xh;}
	public void setxgjl_zj_xh(String value){
		this.xgjl_zj_xh=value;
 		this.onChangeProperty("xgjl_zj_xh",this.xgjl_zj_xh,value);
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



	public TTxTztxjlModel(){
		super();
		this._keyProperty.add("tztxjl_xh");
		initFieldsType();
	}

	private void initFieldsType(){
		this._fieldType.put("tztxjl_xh","String");
		this._fieldType.put("bt","String");
		this._fieldType.put("tx_nr","String");
		this._fieldType.put("tzry_sl","Integer");
		this._fieldType.put("sx_sj","Date");
		this._fieldType.put("fqry_xh","String");
		this._fieldType.put("xgjl_zj_xh","String");
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
		 if(isExistColumn(resultSet,"tztxjl_xh")){
			 this.tztxjl_xh=resultSet.getString("tztxjl_xh");
		}
		 if(isExistColumn(resultSet,"bt")){
			 this.bt=resultSet.getString("bt");
		}
		 if(isExistColumn(resultSet,"tx_nr")){
			 this.tx_nr=resultSet.getString("tx_nr");
		}
		 if(isExistColumn(resultSet,"tzry_sl")){
			 this.tzry_sl=resultSet.getInt("tzry_sl");
		}
		 if(isExistColumn(resultSet,"sx_sj")){
			 this.sx_sj=(Date)resultSet.getObject("sx_sj");
		}
		 if(isExistColumn(resultSet,"fqry_xh")){
			 this.fqry_xh=resultSet.getString("fqry_xh");
		}
		 if(isExistColumn(resultSet,"xgjl_zj_xh")){
			 this.xgjl_zj_xh=resultSet.getString("xgjl_zj_xh");
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
		    sql+=" where   tztxjl_xh="+Utility.getSqlFielStatement("string",this.tztxjl_xh)+"  ";
	    return sql;
   }


   @Override
   public String getInsert() {
       String sql="insert into "+TABLE_NAME+"(tztxjl_xh,bt,tx_nr,tzry_sl,sx_sj,fqry_xh,xgjl_zj_xh,lrry_xh,xgry_xh,lr_sj,xg_sj) values("+Utility.getSqlFielStatement("string",this.tztxjl_xh)+","+Utility.getSqlFielStatement("string",this.bt)+","+Utility.getSqlFielStatement("string",this.tx_nr)+","+this.tzry_sl+","+Utility.getSqlFielStatement("date",this.sx_sj)+","+Utility.getSqlFielStatement("string",this.fqry_xh)+","+Utility.getSqlFielStatement("string",this.xgjl_zj_xh)+","+Utility.getSqlFielStatement("string",this.lrry_xh)+","+Utility.getSqlFielStatement("string",this.xgry_xh)+","+Utility.getSqlFielStatement("date",this.lr_sj)+","+Utility.getSqlFielStatement("date",this.xg_sj)+")";
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
   sql+= " where  tztxjl_xh="+Utility.getSqlFielStatement("string",this.tztxjl_xh)+" ";
   }
   return sql;
}


	@Override
	public String getSelect() {
		String sql="select tztxjl_xh,bt,tx_nr,tzry_sl,sx_sj,fqry_xh,xgjl_zj_xh,lrry_xh,xgry_xh,lr_sj,xg_sj from "+TABLE_NAME;
		if(!Utility.isNullOrEmpty(tztxjl_xh)){
			sql+=" where  tztxjl_xh="+Utility.getSqlFielStatement("string",this.tztxjl_xh)+" ";
		}
		return sql;
	}
	@Override
	public String getSelectByCondition(String condition) {
		String sql="select tztxjl_xh,bt,tx_nr,tzry_sl,sx_sj,fqry_xh,xgjl_zj_xh,lrry_xh,xgry_xh,lr_sj,xg_sj from "+TABLE_NAME;
		if(condition!=null&&condition!=""){
			sql+=" where "+condition;
		}
		return sql;
	}


    @Override
   public String getDelete() {
	 String sql="delete from "+TABLE_NAME+" where  tztxjl_xh="+Utility.getSqlFielStatement("string",this.tztxjl_xh)+" ";
	 return sql;
   }


}

