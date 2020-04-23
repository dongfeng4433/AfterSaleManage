package com.joindoo.jdwechat.beans;

import com.joindoo.jdwechat.Utility;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;
import java.math.BigDecimal;
/**
* 通讯 - 短信 - 短信发送记录
* Author: zhuqiang4433@gmail.com
* Version: CodeGenerator 1.1
* Memo: Auto Created by CodeGenerator on 2020/4/20.
*/

public class TTxDxDxfsjlModel extends BaseModel implements IBaseModel{
	 public static final String TABLE_NAME="T_TX_DX_DXFSJL";
	 public static final String SCRIPT_INSERT="INSERT INTO t_tx_dx_dxfsjl(dxfsjl_xh,sj_hm,nr,fs_type,lr_sj)VALUES(?,?,?,?,?)";
	 public static final String SCRIPT_DELETE="DELETE FROM t_tx_dx_dxfsjl WHERE  dxfsjl_xh=? ";
	 public static final String SCRIPT_UPDATE="UPDATE t_tx_dx_dxfsjl  SET {0} WHERE  dxfsjl_xh=? ";
	 public static final String SCRIPT_SELECT="SELECT dxfsjl_xh,sj_hm,nr,fs_type,lr_sj FROM t_tx_dx_dxfsjl WHERE (1=1)";
	 public String getDataTableName() {
 	 return TABLE_NAME;
	 }


	/*短信验证码*/ 
	protected String dxfsjl_xh;
	public String getdxfsjl_xh (){ return dxfsjl_xh;}
	public void setdxfsjl_xh(String value){
		this.dxfsjl_xh=value;
 		this.onChangeProperty("dxfsjl_xh",this.dxfsjl_xh,value);
 	}

	/*手机号码*/ 
	protected String sj_hm;
	public String getsj_hm (){ return sj_hm;}
	public void setsj_hm(String value){
		this.sj_hm=value;
 		this.onChangeProperty("sj_hm",this.sj_hm,value);
 	}

	/*内容*/ 
	protected String nr;
	public String getnr (){ return nr;}
	public void setnr(String value){
		this.nr=value;
 		this.onChangeProperty("nr",this.nr,value);
 	}

	/*1艾特的通知2自动发送的通知*/ 
	protected String fs_type;
	public String getfs_type (){ return fs_type;}
	public void setfs_type(String value){
		this.fs_type=value;
 		this.onChangeProperty("fs_type",this.fs_type,value);
 	}

	/*录入时间*/ 
	protected Date lr_sj;
	public Date getlr_sj (){ return lr_sj;}
	public void setlr_sj(Date value){
		this.lr_sj=value;
 		this.onChangeProperty("lr_sj",this.lr_sj,value);
 	}



	public TTxDxDxfsjlModel(){
		super();
		this._keyProperty.add("dxfsjl_xh");
		initFieldsType();
	}

	private void initFieldsType(){
		this._fieldType.put("dxfsjl_xh","String");
		this._fieldType.put("sj_hm","String");
		this._fieldType.put("nr","String");
		this._fieldType.put("fs_type","String");
		this._fieldType.put("lr_sj","Date");
	}
	@Override
	public void initAsInsert() {

	}
	@Override
	public void fillModel(ResultSet resultSet) {
		try { 
		 if(isExistColumn(resultSet,"dxfsjl_xh")){
			 this.dxfsjl_xh=resultSet.getString("dxfsjl_xh");
		}
		 if(isExistColumn(resultSet,"sj_hm")){
			 this.sj_hm=resultSet.getString("sj_hm");
		}
		 if(isExistColumn(resultSet,"nr")){
			 this.nr=resultSet.getString("nr");
		}
		 if(isExistColumn(resultSet,"fs_type")){
			 this.fs_type=resultSet.getString("fs_type");
		}
		 if(isExistColumn(resultSet,"lr_sj")){
			 this.lr_sj=(Date)resultSet.getObject("lr_sj");
		}
      } catch (SQLException e) {
              e.printStackTrace();
          }
      }
  @Override
   public String getExist() {
	    String sql="select 1 from "+TABLE_NAME;
		    sql+=" where   dxfsjl_xh="+Utility.getSqlFielStatement("string",this.dxfsjl_xh)+"  ";
	    return sql;
   }


   @Override
   public String getInsert() {
       String sql="insert into "+TABLE_NAME+"(dxfsjl_xh,sj_hm,nr,fs_type,lr_sj) values("+Utility.getSqlFielStatement("string",this.dxfsjl_xh)+","+Utility.getSqlFielStatement("string",this.sj_hm)+","+Utility.getSqlFielStatement("string",this.nr)+","+Utility.getSqlFielStatement("string",this.fs_type)+","+Utility.getSqlFielStatement("date",this.lr_sj)+")";
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
   sql+= " where  dxfsjl_xh="+Utility.getSqlFielStatement("string",this.dxfsjl_xh)+" ";
   }
   return sql;
}


	@Override
	public String getSelect() {
		String sql="select dxfsjl_xh,sj_hm,nr,fs_type,lr_sj from "+TABLE_NAME;
		if(!Utility.isNullOrEmpty(dxfsjl_xh)){
			sql+=" where  dxfsjl_xh="+Utility.getSqlFielStatement("string",this.dxfsjl_xh)+" ";
		}
		return sql;
	}
	@Override
	public String getSelectByCondition(String condition) {
		String sql="select dxfsjl_xh,sj_hm,nr,fs_type,lr_sj from "+TABLE_NAME;
		if(condition!=null&&condition!=""){
			sql+=" where "+condition;
		}
		return sql;
	}


    @Override
   public String getDelete() {
	 String sql="delete from "+TABLE_NAME+" where  dxfsjl_xh="+Utility.getSqlFielStatement("string",this.dxfsjl_xh)+" ";
	 return sql;
   }


}

