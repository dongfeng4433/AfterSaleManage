package com.joindoo.jdwechat.beans;

import com.joindoo.jdwechat.Utility;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;
import java.math.BigDecimal;
/**
* 主数据 - 通知  通知消息  此处可能用钉钉群内机器人进行通知
* Author: zhuqiang4433@gmail.com
* Version: CodeGenerator 1.1
* Memo: Auto Created by CodeGenerator on 2020/4/5.
*/

public class TDataNotificationModel extends BaseModel implements IBaseModel{
	 public static final String TABLE_NAME="T_DATA_NOTIFICATION";
	 public static final String SCRIPT_INSERT="INSERT INTO t_data_notification(notification_id,enterprise_id,accept_user_id,third_user_id,title,description,is_valid,create_time,last_edit_time,creation_user_id,last_edit_user_id,send_time,msg_status,attempt_times)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	 public static final String SCRIPT_DELETE="DELETE FROM t_data_notification WHERE  notification_id=? ";
	 public static final String SCRIPT_UPDATE="UPDATE t_data_notification  SET {0} WHERE  notification_id=? ";
	 public static final String SCRIPT_SELECT="SELECT notification_id,enterprise_id,accept_user_id,third_user_id,title,description,is_valid,create_time,last_edit_time,creation_user_id,last_edit_user_id,send_time,msg_status,attempt_times FROM t_data_notification WHERE (1=1)";
	 public String getDataTableName() {
 	 return TABLE_NAME;
	 }


	/*通知消息id*/ 
	protected String notification_id;
	public String getnotification_id (){ return notification_id;}
	public void setnotification_id(String value){
		this.notification_id=value;
 		this.onChangeProperty("notification_id",this.notification_id,value);
 	}

	/*企业id*/ 
	protected String enterprise_id;
	public String getenterprise_id (){ return enterprise_id;}
	public void setenterprise_id(String value){
		this.enterprise_id=value;
 		this.onChangeProperty("enterprise_id",this.enterprise_id,value);
 	}

	/*消息接收人员*/ 
	protected String accept_user_id;
	public String getaccept_user_id (){ return accept_user_id;}
	public void setaccept_user_id(String value){
		this.accept_user_id=value;
 		this.onChangeProperty("accept_user_id",this.accept_user_id,value);
 	}

	/*第三方用户id*/ 
	protected String third_user_id;
	public String getthird_user_id (){ return third_user_id;}
	public void setthird_user_id(String value){
		this.third_user_id=value;
 		this.onChangeProperty("third_user_id",this.third_user_id,value);
 	}

	/*消息主题*/ 
	protected String title;
	public String gettitle (){ return title;}
	public void settitle(String value){
		this.title=value;
 		this.onChangeProperty("title",this.title,value);
 	}

	/*描述*/ 
	protected String description;
	public String getdescription (){ return description;}
	public void setdescription(String value){
		this.description=value;
 		this.onChangeProperty("description",this.description,value);
 	}

	/*是否有效*/ 
	protected Integer is_valid;
	public Integer getis_valid (){ return is_valid;}
	public void setis_valid(Integer value){
		this.is_valid=value;
 		this.onChangeProperty("is_valid",this.is_valid,value);
 	}

	/*创建时间*/ 
	protected Date create_time;
	public Date getcreate_time (){ return create_time;}
	public void setcreate_time(Date value){
		this.create_time=value;
 		this.onChangeProperty("create_time",this.create_time,value);
 	}

	/*最后修改时间*/ 
	protected Date last_edit_time;
	public Date getlast_edit_time (){ return last_edit_time;}
	public void setlast_edit_time(Date value){
		this.last_edit_time=value;
 		this.onChangeProperty("last_edit_time",this.last_edit_time,value);
 	}

	/*创建人（发送人）*/ 
	protected String creation_user_id;
	public String getcreation_user_id (){ return creation_user_id;}
	public void setcreation_user_id(String value){
		this.creation_user_id=value;
 		this.onChangeProperty("creation_user_id",this.creation_user_id,value);
 	}

	/*最后修改人*/ 
	protected String last_edit_user_id;
	public String getlast_edit_user_id (){ return last_edit_user_id;}
	public void setlast_edit_user_id(String value){
		this.last_edit_user_id=value;
 		this.onChangeProperty("last_edit_user_id",this.last_edit_user_id,value);
 	}

	/*发送时间*/ 
	protected Date send_time;
	public Date getsend_time (){ return send_time;}
	public void setsend_time(Date value){
		this.send_time=value;
 		this.onChangeProperty("send_time",this.send_time,value);
 	}

	/*消息状态（0未发送 1 发送成功 2发送失败）*/ 
	protected String msg_status;
	public String getmsg_status (){ return msg_status;}
	public void setmsg_status(String value){
		this.msg_status=value;
 		this.onChangeProperty("msg_status",this.msg_status,value);
 	}

	/*尝试次数*/ 
	protected Integer attempt_times;
	public Integer getattempt_times (){ return attempt_times;}
	public void setattempt_times(Integer value){
		this.attempt_times=value;
 		this.onChangeProperty("attempt_times",this.attempt_times,value);
 	}



	public TDataNotificationModel(){
		super();
		this._keyProperty.add("notification_id");
		initFieldsType();
	}

	private void initFieldsType(){
		this._fieldType.put("notification_id","String");
		this._fieldType.put("enterprise_id","String");
		this._fieldType.put("accept_user_id","String");
		this._fieldType.put("third_user_id","String");
		this._fieldType.put("title","String");
		this._fieldType.put("description","String");
		this._fieldType.put("is_valid","Integer");
		this._fieldType.put("create_time","Date");
		this._fieldType.put("last_edit_time","Date");
		this._fieldType.put("creation_user_id","String");
		this._fieldType.put("last_edit_user_id","String");
		this._fieldType.put("send_time","Date");
		this._fieldType.put("msg_status","String");
		this._fieldType.put("attempt_times","Integer");
	}
	@Override
	public void initAsInsert() {

	}
	@Override
	public void fillModel(ResultSet resultSet) {
		try { 
		 if(isExistColumn(resultSet,"notification_id")){
			 this.notification_id=resultSet.getString("notification_id");
		}
		 if(isExistColumn(resultSet,"enterprise_id")){
			 this.enterprise_id=resultSet.getString("enterprise_id");
		}
		 if(isExistColumn(resultSet,"accept_user_id")){
			 this.accept_user_id=resultSet.getString("accept_user_id");
		}
		 if(isExistColumn(resultSet,"third_user_id")){
			 this.third_user_id=resultSet.getString("third_user_id");
		}
		 if(isExistColumn(resultSet,"title")){
			 this.title=resultSet.getString("title");
		}
		 if(isExistColumn(resultSet,"description")){
			 this.description=resultSet.getString("description");
		}
		 if(isExistColumn(resultSet,"is_valid")){
			 this.is_valid=resultSet.getInt("is_valid");
		}
		 if(isExistColumn(resultSet,"create_time")){
			 this.create_time=(Date)resultSet.getObject("create_time");
		}
		 if(isExistColumn(resultSet,"last_edit_time")){
			 this.last_edit_time=(Date)resultSet.getObject("last_edit_time");
		}
		 if(isExistColumn(resultSet,"creation_user_id")){
			 this.creation_user_id=resultSet.getString("creation_user_id");
		}
		 if(isExistColumn(resultSet,"last_edit_user_id")){
			 this.last_edit_user_id=resultSet.getString("last_edit_user_id");
		}
		 if(isExistColumn(resultSet,"send_time")){
			 this.send_time=(Date)resultSet.getObject("send_time");
		}
		 if(isExistColumn(resultSet,"msg_status")){
			 this.msg_status=resultSet.getString("msg_status");
		}
		 if(isExistColumn(resultSet,"attempt_times")){
			 this.attempt_times=resultSet.getInt("attempt_times");
			if (resultSet.wasNull()) {
				this.attempt_times=null;
			}
		}
      } catch (SQLException e) {
              e.printStackTrace();
          }
      }
  @Override
   public String getExist() {
	    String sql="select 1 from "+TABLE_NAME;
		    sql+=" where   notification_id="+Utility.getSqlFielStatement("string",this.notification_id)+"  ";
	    return sql;
   }


   @Override
   public String getInsert() {
       String sql="insert into "+TABLE_NAME+"(notification_id,enterprise_id,accept_user_id,third_user_id,title,description,is_valid,create_time,last_edit_time,creation_user_id,last_edit_user_id,send_time,msg_status,attempt_times) values("+Utility.getSqlFielStatement("string",this.notification_id)+","+Utility.getSqlFielStatement("string",this.enterprise_id)+","+Utility.getSqlFielStatement("string",this.accept_user_id)+","+Utility.getSqlFielStatement("string",this.third_user_id)+","+Utility.getSqlFielStatement("string",this.title)+","+Utility.getSqlFielStatement("string",this.description)+","+this.is_valid+","+Utility.getSqlFielStatement("date",this.create_time)+","+Utility.getSqlFielStatement("date",this.last_edit_time)+","+Utility.getSqlFielStatement("string",this.creation_user_id)+","+Utility.getSqlFielStatement("string",this.last_edit_user_id)+","+Utility.getSqlFielStatement("date",this.send_time)+","+Utility.getSqlFielStatement("string",this.msg_status)+","+this.attempt_times+")";
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
   sql+= " where  notification_id="+Utility.getSqlFielStatement("string",this.notification_id)+" ";
   }
   return sql;
}


	@Override
	public String getSelect() {
		String sql="select notification_id,enterprise_id,accept_user_id,third_user_id,title,description,is_valid,create_time,last_edit_time,creation_user_id,last_edit_user_id,send_time,msg_status,attempt_times from "+TABLE_NAME;
		if(!Utility.isNullOrEmpty(notification_id)){
			sql+=" where  notification_id="+Utility.getSqlFielStatement("string",this.notification_id)+" ";
		}
		return sql;
	}
	@Override
	public String getSelectByCondition(String condition) {
		String sql="select notification_id,enterprise_id,accept_user_id,third_user_id,title,description,is_valid,create_time,last_edit_time,creation_user_id,last_edit_user_id,send_time,msg_status,attempt_times from "+TABLE_NAME;
		if(condition!=null&&condition!=""){
			sql+=" where "+condition;
		}
		return sql;
	}


    @Override
   public String getDelete() {
	 String sql="delete from "+TABLE_NAME+" where  notification_id="+Utility.getSqlFielStatement("string",this.notification_id)+" ";
	 return sql;
   }


}

