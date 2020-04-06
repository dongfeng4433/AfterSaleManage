package com.joindoo.jdwechat.beans;

import com.joindoo.jdwechat.Utility;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;
import java.math.BigDecimal;
/**
* 诉求类型：
01：投诉
02：举报
03：报修
04：求助

* Author: zhuqiang4433@gmail.com
* Version: CodeGenerator 1.1
* Memo: Auto Created by CodeGenerator on 2020/4/5.
*/

public class TDmSqlxModel extends BaseModel implements IBaseModel{
	 public static final String TABLE_NAME="T_DM_SQLX";
	 public static final String SCRIPT_INSERT="INSERT INTO t_dm_sqlx(sqlx_dm,mc)VALUES(?,?)";
	 public static final String SCRIPT_DELETE="DELETE FROM t_dm_sqlx WHERE  sqlx_dm=? ";
	 public static final String SCRIPT_UPDATE="UPDATE t_dm_sqlx  SET {0} WHERE  sqlx_dm=? ";
	 public static final String SCRIPT_SELECT="SELECT sqlx_dm,mc FROM t_dm_sqlx WHERE (1=1)";
	 public String getDataTableName() {
 	 return TABLE_NAME;
	 }


	/*诉求类型*/ 
	protected String sqlx_dm;
	public String getsqlx_dm (){ return sqlx_dm;}
	public void setsqlx_dm(String value){
		this.sqlx_dm=value;
 		this.onChangeProperty("sqlx_dm",this.sqlx_dm,value);
 	}

	/*诉求类型*/ 
	protected String mc;
	public String getmc (){ return mc;}
	public void setmc(String value){
		this.mc=value;
 		this.onChangeProperty("mc",this.mc,value);
 	}



	public TDmSqlxModel(){
		super();
		this._keyProperty.add("sqlx_dm");
		initFieldsType();
	}

	private void initFieldsType(){
		this._fieldType.put("sqlx_dm","String");
		this._fieldType.put("mc","String");
	}
	@Override
	public void initAsInsert() {

	}
	@Override
	public void fillModel(ResultSet resultSet) {
		try { 
		 if(isExistColumn(resultSet,"sqlx_dm")){
			 this.sqlx_dm=resultSet.getString("sqlx_dm");
		}
		 if(isExistColumn(resultSet,"mc")){
			 this.mc=resultSet.getString("mc");
		}
      } catch (SQLException e) {
              e.printStackTrace();
          }
      }
  @Override
   public String getExist() {
	    String sql="select 1 from "+TABLE_NAME;
		    sql+=" where   sqlx_dm="+Utility.getSqlFielStatement("string",this.sqlx_dm)+"  ";
	    return sql;
   }


   @Override
   public String getInsert() {
       String sql="insert into "+TABLE_NAME+"(sqlx_dm,mc) values("+Utility.getSqlFielStatement("string",this.sqlx_dm)+","+Utility.getSqlFielStatement("string",this.mc)+")";
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
   sql+= " where  sqlx_dm="+Utility.getSqlFielStatement("string",this.sqlx_dm)+" ";
   }
   return sql;
}


	@Override
	public String getSelect() {
		String sql="select sqlx_dm,mc from "+TABLE_NAME;
		if(!Utility.isNullOrEmpty(sqlx_dm)){
			sql+=" where  sqlx_dm="+Utility.getSqlFielStatement("string",this.sqlx_dm)+" ";
		}
		return sql;
	}
	@Override
	public String getSelectByCondition(String condition) {
		String sql="select sqlx_dm,mc from "+TABLE_NAME;
		if(condition!=null&&condition!=""){
			sql+=" where "+condition;
		}
		return sql;
	}


    @Override
   public String getDelete() {
	 String sql="delete from "+TABLE_NAME+" where  sqlx_dm="+Utility.getSqlFielStatement("string",this.sqlx_dm)+" ";
	 return sql;
   }


}

