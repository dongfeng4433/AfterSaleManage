package com.joindoo.jdwechat.beans;

import com.joindoo.jdwechat.Utility;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;
import java.math.BigDecimal;
/**
* 音频，照片
* Author: zhuqiang4433@gmail.com
* Version: CodeGenerator 1.1
* Memo: Auto Created by CodeGenerator on 2020/4/5.
*/

public class TSqblFjModel extends BaseModel implements IBaseModel{
	 public static final String TABLE_NAME="T_SQBL_FJ";
	 public static final String SCRIPT_INSERT="INSERT INTO t_sqbl_fj(fj_xh,gdbh,fj_lj)VALUES(?,?,?)";
	 public static final String SCRIPT_DELETE="DELETE FROM t_sqbl_fj WHERE  fj_xh=? ";
	 public static final String SCRIPT_UPDATE="UPDATE t_sqbl_fj  SET {0} WHERE  fj_xh=? ";
	 public static final String SCRIPT_SELECT="SELECT fj_xh,gdbh,fj_lj FROM t_sqbl_fj WHERE (1=1)";
	 public String getDataTableName() {
 	 return TABLE_NAME;
	 }


	/*附件序号*/ 
	protected String fj_xh;
	public String getfj_xh (){ return fj_xh;}
	public void setfj_xh(String value){
		this.fj_xh=value;
 		this.onChangeProperty("fj_xh",this.fj_xh,value);
 	}

	/*工单编号*/ 
	protected String gdbh;
	public String getgdbh (){ return gdbh;}
	public void setgdbh(String value){
		this.gdbh=value;
 		this.onChangeProperty("gdbh",this.gdbh,value);
 	}

	/*附件路径*/ 
	protected String fj_lj;
	public String getfj_lj (){ return fj_lj;}
	public void setfj_lj(String value){
		this.fj_lj=value;
 		this.onChangeProperty("fj_lj",this.fj_lj,value);
 	}



	public TSqblFjModel(){
		super();
		this._keyProperty.add("fj_xh");
		initFieldsType();
	}

	private void initFieldsType(){
		this._fieldType.put("fj_xh","String");
		this._fieldType.put("gdbh","String");
		this._fieldType.put("fj_lj","String");
	}
	@Override
	public void initAsInsert() {

	}
	@Override
	public void fillModel(ResultSet resultSet) {
		try { 
		 if(isExistColumn(resultSet,"fj_xh")){
			 this.fj_xh=resultSet.getString("fj_xh");
		}
		 if(isExistColumn(resultSet,"gdbh")){
			 this.gdbh=resultSet.getString("gdbh");
		}
		 if(isExistColumn(resultSet,"fj_lj")){
			 this.fj_lj=resultSet.getString("fj_lj");
		}
      } catch (SQLException e) {
              e.printStackTrace();
          }
      }
  @Override
   public String getExist() {
	    String sql="select 1 from "+TABLE_NAME;
		    sql+=" where   fj_xh="+Utility.getSqlFielStatement("string",this.fj_xh)+"  ";
	    return sql;
   }


   @Override
   public String getInsert() {
       String sql="insert into "+TABLE_NAME+"(fj_xh,gdbh,fj_lj) values("+Utility.getSqlFielStatement("string",this.fj_xh)+","+Utility.getSqlFielStatement("string",this.gdbh)+","+Utility.getSqlFielStatement("string",this.fj_lj)+")";
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
   sql+= " where  fj_xh="+Utility.getSqlFielStatement("string",this.fj_xh)+" ";
   }
   return sql;
}


	@Override
	public String getSelect() {
		String sql="select fj_xh,gdbh,fj_lj from "+TABLE_NAME;
		if(!Utility.isNullOrEmpty(fj_xh)){
			sql+=" where  fj_xh="+Utility.getSqlFielStatement("string",this.fj_xh)+" ";
		}
		return sql;
	}
	@Override
	public String getSelectByCondition(String condition) {
		String sql="select fj_xh,gdbh,fj_lj from "+TABLE_NAME;
		if(condition!=null&&condition!=""){
			sql+=" where "+condition;
		}
		return sql;
	}


    @Override
   public String getDelete() {
	 String sql="delete from "+TABLE_NAME+" where  fj_xh="+Utility.getSqlFielStatement("string",this.fj_xh)+" ";
	 return sql;
   }


}

