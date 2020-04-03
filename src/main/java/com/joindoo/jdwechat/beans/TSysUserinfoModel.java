package com.joindoo.jdwechat.beans;

import com.joindoo.jdwechat.Utility;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;
import java.math.BigDecimal;
/**
* 
* Author: zhuqiang4433@gmail.com
* Memo: Auto Created by CodeGenerator on 2019/8/27.
*/

public class TSysUserinfoModel extends BaseModel implements IBaseModel{
	 public static final String TABLE_NAME="T_SYS_USERINFO";
	 public static final String SCRIPT_INSERT="INSERT INTO t_sys_userinfo(id,username,password,salt,create_time,edit_time,is_valid,attempts_times)VALUES(?,?,?,?,?,?,?,?)";
	 public static final String SCRIPT_DELETE="DELETE FROM t_sys_userinfo WHERE  id=? ";
	 public static final String SCRIPT_UPDATE="UPDATE t_sys_userinfo  SET {0} WHERE  id=? ";
	 public static final String SCRIPT_SELECT="SELECT id,username,password,salt,create_time,edit_time,is_valid,attempts_times FROM t_sys_userinfo WHERE (1=1)";
	 public String getDataTableName() {
 	 return TABLE_NAME;
	 }


	/*序号*/ 
	protected String id;
	public String getid (){ return id;}
	public void setid(String value){
		this.id=value;
 		this.onChangeProperty("id",this.id,value);
 	}

	/*昵称*/ 
	protected String username;
	public String getusername (){ return username;}
	public void setusername(String value){
		this.username=value;
 		this.onChangeProperty("username",this.username,value);
 	}

	/*密码*/ 
	protected String password;
	public String getpassword (){ return password;}
	public void setpassword(String value){
		this.password=value;
 		this.onChangeProperty("password",this.password,value);
 	}

	/*加密盐值*/ 
	protected String salt;
	public String getsalt (){ return salt;}
	public void setsalt(String value){
		this.salt=value;
 		this.onChangeProperty("salt",this.salt,value);
 	}

	/*创建时间*/ 
	protected Date create_time;
	public Date getcreate_time (){ return create_time;}
	public void setcreate_time(Date value){
		this.create_time=value;
 		this.onChangeProperty("create_time",this.create_time,value);
 	}

	/*修改时间*/ 
	protected Date edit_time;
	public Date getedit_time (){ return edit_time;}
	public void setedit_time(Date value){
		this.edit_time=value;
 		this.onChangeProperty("edit_time",this.edit_time,value);
 	}

	/*是否有效*/ 
	protected Integer is_valid;
	public Integer getis_valid (){ return is_valid;}
	public void setis_valid(Integer value){
		this.is_valid=value;
 		this.onChangeProperty("is_valid",this.is_valid,value);
 	}

	/**/ 
	protected Integer attempts_times;
	public Integer getattempts_times (){ return attempts_times;}
	public void setattempts_times(Integer value){
		this.attempts_times=value;
 		this.onChangeProperty("attempts_times",this.attempts_times,value);
 	}



	public TSysUserinfoModel(){
		super();
		this._keyProperty.add("id");
		initFieldsType();
	}

	private void initFieldsType(){
		this._fieldType.put("id","String");
		this._fieldType.put("username","String");
		this._fieldType.put("password","String");
		this._fieldType.put("salt","String");
		this._fieldType.put("create_time","Date");
		this._fieldType.put("edit_time","Date");
		this._fieldType.put("is_valid","Integer");
		this._fieldType.put("attempts_times","Integer");
	}
	@Override
	public void initAsInsert() {

	}
	@Override
	public void fillModel(ResultSet resultSet) {
		try { 
		 if(isExistColumn(resultSet,"id")){
			 this.id=resultSet.getString("id");
		}
		 if(isExistColumn(resultSet,"username")){
			 this.username=resultSet.getString("username");
		}
		 if(isExistColumn(resultSet,"password")){
			 this.password=resultSet.getString("password");
		}
		 if(isExistColumn(resultSet,"salt")){
			 this.salt=resultSet.getString("salt");
		}
		 if(isExistColumn(resultSet,"create_time")){
			 this.create_time=(Date)resultSet.getObject("create_time");
		}
		 if(isExistColumn(resultSet,"edit_time")){
			 this.edit_time=(Date)resultSet.getObject("edit_time");
		}
		 if(isExistColumn(resultSet,"is_valid")){
			 this.is_valid=resultSet.getInt("is_valid");
		}
		 if(isExistColumn(resultSet,"attempts_times")){
			 this.attempts_times=resultSet.getInt("attempts_times");
		}
      } catch (SQLException e) {
              e.printStackTrace();
          }
      }
  @Override
   public String getExist() {
	    String sql="select 1 from "+TABLE_NAME;
		    sql+=" where   id="+Utility.getSqlFielStatement("string",this.id)+"  ";
	    return sql;
   }


   @Override
   public String getInsert() {
       String sql="insert into "+TABLE_NAME+"(id,username,password,salt,create_time,edit_time,is_valid,attempts_times) values("+Utility.getSqlFielStatement("string",this.id)+","+Utility.getSqlFielStatement("string",this.username)+","+Utility.getSqlFielStatement("string",this.password)+","+Utility.getSqlFielStatement("string",this.salt)+","+Utility.getSqlFielStatement("date",this.create_time)+","+Utility.getSqlFielStatement("date",this.edit_time)+","+this.is_valid+","+this.attempts_times+")";
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
   sql+= " where  id="+Utility.getSqlFielStatement("string",this.id)+" ";
   }
   return sql;
}


	@Override
	public String getSelect() {
		String sql="select id,username,password,salt,create_time,edit_time,is_valid,attempts_times from "+TABLE_NAME;
		if(!Utility.isNullOrEmpty(id)){
			sql+=" where  id="+Utility.getSqlFielStatement("string",this.id)+" ";
		}
		return sql;
	}
	@Override
	public String getSelectByCondition(String condition) {
		String sql="select id,username,password,salt,create_time,edit_time,is_valid,attempts_times from "+TABLE_NAME;
		if(condition!=null&&condition!=""){
			sql+=" where "+condition;
		}
		return sql;
	}


    @Override
   public String getDelete() {
	 String sql="delete from "+TABLE_NAME+" where  id="+Utility.getSqlFielStatement("string",this.id)+" ";
	 return sql;
   }


}

