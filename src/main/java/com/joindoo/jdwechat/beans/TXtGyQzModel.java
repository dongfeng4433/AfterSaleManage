package com.joindoo.jdwechat.beans;

import com.joindoo.jdwechat.Utility;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;
import java.math.BigDecimal;
/**
* 系统 - 公用-群组
* Author: zhuqiang4433@gmail.com
* Version: CodeGenerator 1.1
* Memo: Auto Created by CodeGenerator on 2020/4/20.
*/

public class TXtGyQzModel extends BaseModel implements IBaseModel{
	 public static final String TABLE_NAME="T_XT_GY_QZ";
	 public static final String SCRIPT_INSERT="INSERT INTO t_xt_gy_qz(group_id,group_name,community_id,street_id,street_name,community_name,create_time,is_valid,qrcode_path,qrcode_create_time)VALUES(?,?,?,?,?,?,?,?,?,?)";
	 public static final String SCRIPT_DELETE="DELETE FROM t_xt_gy_qz WHERE  group_id=? ";
	 public static final String SCRIPT_UPDATE="UPDATE t_xt_gy_qz  SET {0} WHERE  group_id=? ";
	 public static final String SCRIPT_SELECT="SELECT group_id,group_name,community_id,street_id,street_name,community_name,create_time,is_valid,qrcode_path,qrcode_create_time FROM t_xt_gy_qz WHERE (1=1)";
	 public String getDataTableName() {
 	 return TABLE_NAME;
	 }


	/*群组序号*/ 
	protected String group_id;
	public String getgroup_id (){ return group_id;}
	public void setgroup_id(String value){
		this.group_id=value;
 		this.onChangeProperty("group_id",this.group_id,value);
 	}

	/*群组名称*/ 
	protected String group_name;
	public String getgroup_name (){ return group_name;}
	public void setgroup_name(String value){
		this.group_name=value;
 		this.onChangeProperty("group_name",this.group_name,value);
 	}

	/*社区序号*/ 
	protected String community_id;
	public String getcommunity_id (){ return community_id;}
	public void setcommunity_id(String value){
		this.community_id=value;
 		this.onChangeProperty("community_id",this.community_id,value);
 	}

	/*街道id*/ 
	protected String street_id;
	public String getstreet_id (){ return street_id;}
	public void setstreet_id(String value){
		this.street_id=value;
 		this.onChangeProperty("street_id",this.street_id,value);
 	}

	/*街道名称*/ 
	protected String street_name;
	public String getstreet_name (){ return street_name;}
	public void setstreet_name(String value){
		this.street_name=value;
 		this.onChangeProperty("street_name",this.street_name,value);
 	}

	/*社区名称*/ 
	protected String community_name;
	public String getcommunity_name (){ return community_name;}
	public void setcommunity_name(String value){
		this.community_name=value;
 		this.onChangeProperty("community_name",this.community_name,value);
 	}

	/*创建时间*/ 
	protected Date create_time;
	public Date getcreate_time (){ return create_time;}
	public void setcreate_time(Date value){
		this.create_time=value;
 		this.onChangeProperty("create_time",this.create_time,value);
 	}

	/*是否有效*/ 
	protected Integer is_valid;
	public Integer getis_valid (){ return is_valid;}
	public void setis_valid(Integer value){
		this.is_valid=value;
 		this.onChangeProperty("is_valid",this.is_valid,value);
 	}

	/*二维码图片路径*/ 
	protected String qrcode_path;
	public String getqrcode_path (){ return qrcode_path;}
	public void setqrcode_path(String value){
		this.qrcode_path=value;
 		this.onChangeProperty("qrcode_path",this.qrcode_path,value);
 	}

	/*二维码生成时间*/ 
	protected Date qrcode_create_time;
	public Date getqrcode_create_time (){ return qrcode_create_time;}
	public void setqrcode_create_time(Date value){
		this.qrcode_create_time=value;
 		this.onChangeProperty("qrcode_create_time",this.qrcode_create_time,value);
 	}



	public TXtGyQzModel(){
		super();
		this._keyProperty.add("group_id");
		initFieldsType();
	}

	private void initFieldsType(){
		this._fieldType.put("group_id","String");
		this._fieldType.put("group_name","String");
		this._fieldType.put("community_id","String");
		this._fieldType.put("street_id","String");
		this._fieldType.put("street_name","String");
		this._fieldType.put("community_name","String");
		this._fieldType.put("create_time","Date");
		this._fieldType.put("is_valid","Integer");
		this._fieldType.put("qrcode_path","String");
		this._fieldType.put("qrcode_create_time","Date");
	}
	@Override
	public void initAsInsert() {

	}
	@Override
	public void fillModel(ResultSet resultSet) {
		try { 
		 if(isExistColumn(resultSet,"group_id")){
			 this.group_id=resultSet.getString("group_id");
		}
		 if(isExistColumn(resultSet,"group_name")){
			 this.group_name=resultSet.getString("group_name");
		}
		 if(isExistColumn(resultSet,"community_id")){
			 this.community_id=resultSet.getString("community_id");
		}
		 if(isExistColumn(resultSet,"street_id")){
			 this.street_id=resultSet.getString("street_id");
		}
		 if(isExistColumn(resultSet,"street_name")){
			 this.street_name=resultSet.getString("street_name");
		}
		 if(isExistColumn(resultSet,"community_name")){
			 this.community_name=resultSet.getString("community_name");
		}
		 if(isExistColumn(resultSet,"create_time")){
			 this.create_time=(Date)resultSet.getObject("create_time");
		}
		 if(isExistColumn(resultSet,"is_valid")){
			 this.is_valid=resultSet.getInt("is_valid");
		}
		 if(isExistColumn(resultSet,"qrcode_path")){
			 this.qrcode_path=resultSet.getString("qrcode_path");
		}
		 if(isExistColumn(resultSet,"qrcode_create_time")){
			 this.qrcode_create_time=(Date)resultSet.getObject("qrcode_create_time");
		}
      } catch (SQLException e) {
              e.printStackTrace();
          }
      }
  @Override
   public String getExist() {
	    String sql="select 1 from "+TABLE_NAME;
		    sql+=" where   group_id="+Utility.getSqlFielStatement("string",this.group_id)+"  ";
	    return sql;
   }


   @Override
   public String getInsert() {
       String sql="insert into "+TABLE_NAME+"(group_id,group_name,community_id,street_id,street_name,community_name,create_time,is_valid,qrcode_path,qrcode_create_time) values("+Utility.getSqlFielStatement("string",this.group_id)+","+Utility.getSqlFielStatement("string",this.group_name)+","+Utility.getSqlFielStatement("string",this.community_id)+","+Utility.getSqlFielStatement("string",this.street_id)+","+Utility.getSqlFielStatement("string",this.street_name)+","+Utility.getSqlFielStatement("string",this.community_name)+","+Utility.getSqlFielStatement("date",this.create_time)+","+this.is_valid+","+Utility.getSqlFielStatement("string",this.qrcode_path)+","+Utility.getSqlFielStatement("date",this.qrcode_create_time)+")";
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
   sql+= " where  group_id="+Utility.getSqlFielStatement("string",this.group_id)+" ";
   }
   return sql;
}


	@Override
	public String getSelect() {
		String sql="select group_id,group_name,community_id,street_id,street_name,community_name,create_time,is_valid,qrcode_path,qrcode_create_time from "+TABLE_NAME;
		if(!Utility.isNullOrEmpty(group_id)){
			sql+=" where  group_id="+Utility.getSqlFielStatement("string",this.group_id)+" ";
		}
		return sql;
	}
	@Override
	public String getSelectByCondition(String condition) {
		String sql="select group_id,group_name,community_id,street_id,street_name,community_name,create_time,is_valid,qrcode_path,qrcode_create_time from "+TABLE_NAME;
		if(condition!=null&&condition!=""){
			sql+=" where "+condition;
		}
		return sql;
	}


    @Override
   public String getDelete() {
	 String sql="delete from "+TABLE_NAME+" where  group_id="+Utility.getSqlFielStatement("string",this.group_id)+" ";
	 return sql;
   }


}

