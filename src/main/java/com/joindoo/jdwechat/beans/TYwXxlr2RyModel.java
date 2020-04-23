package com.joindoo.jdwechat.beans;

import com.joindoo.jdwechat.Utility;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;
import java.math.BigDecimal;
/**
* 业务_信息录入2人员
* Author: zhuqiang4433@gmail.com
* Version: CodeGenerator 1.1
* Memo: Auto Created by CodeGenerator on 2020/4/20.
*/

public class TYwXxlr2RyModel extends BaseModel implements IBaseModel{
	 public static final String TABLE_NAME="T_YW_XXLR_2_RY";
	 public static final String SCRIPT_INSERT="INSERT INTO t_yw_xxlr_2_ry(xxlr_2_ry_xh,xxlr_xh,ry_xh,sfyx_bj,clzt_dm)VALUES(?,?,?,?,?)";
	 public static final String SCRIPT_DELETE="DELETE FROM t_yw_xxlr_2_ry WHERE  xxlr_2_ry_xh=? ";
	 public static final String SCRIPT_UPDATE="UPDATE t_yw_xxlr_2_ry  SET {0} WHERE  xxlr_2_ry_xh=? ";
	 public static final String SCRIPT_SELECT="SELECT xxlr_2_ry_xh,xxlr_xh,ry_xh,sfyx_bj,clzt_dm FROM t_yw_xxlr_2_ry WHERE (1=1)";
	 public String getDataTableName() {
 	 return TABLE_NAME;
	 }


	/*序号*/ 
	protected String xxlr_2_ry_xh;
	public String getxxlr_2_ry_xh (){ return xxlr_2_ry_xh;}
	public void setxxlr_2_ry_xh(String value){
		this.xxlr_2_ry_xh=value;
 		this.onChangeProperty("xxlr_2_ry_xh",this.xxlr_2_ry_xh,value);
 	}

	/*信息录入序号*/ 
	protected String xxlr_xh;
	public String getxxlr_xh (){ return xxlr_xh;}
	public void setxxlr_xh(String value){
		this.xxlr_xh=value;
 		this.onChangeProperty("xxlr_xh",this.xxlr_xh,value);
 	}

	/*人员*/ 
	protected String ry_xh;
	public String getry_xh (){ return ry_xh;}
	public void setry_xh(String value){
		this.ry_xh=value;
 		this.onChangeProperty("ry_xh",this.ry_xh,value);
 	}

	/*是否有效标记*/ 
	protected Integer sfyx_bj;
	public Integer getsfyx_bj (){ return sfyx_bj;}
	public void setsfyx_bj(Integer value){
		this.sfyx_bj=value;
 		this.onChangeProperty("sfyx_bj",this.sfyx_bj,value);
 	}

	/*处理状态代码1已处理*/ 
	protected String clzt_dm;
	public String getclzt_dm (){ return clzt_dm;}
	public void setclzt_dm(String value){
		this.clzt_dm=value;
 		this.onChangeProperty("clzt_dm",this.clzt_dm,value);
 	}



	public TYwXxlr2RyModel(){
		super();
		this._keyProperty.add("xxlr_2_ry_xh");
		initFieldsType();
	}

	private void initFieldsType(){
		this._fieldType.put("xxlr_2_ry_xh","String");
		this._fieldType.put("xxlr_xh","String");
		this._fieldType.put("ry_xh","String");
		this._fieldType.put("sfyx_bj","Integer");
		this._fieldType.put("clzt_dm","String");
	}
	@Override
	public void initAsInsert() {

	}
	@Override
	public void fillModel(ResultSet resultSet) {
		try { 
		 if(isExistColumn(resultSet,"xxlr_2_ry_xh")){
			 this.xxlr_2_ry_xh=resultSet.getString("xxlr_2_ry_xh");
		}
		 if(isExistColumn(resultSet,"xxlr_xh")){
			 this.xxlr_xh=resultSet.getString("xxlr_xh");
		}
		 if(isExistColumn(resultSet,"ry_xh")){
			 this.ry_xh=resultSet.getString("ry_xh");
		}
		 if(isExistColumn(resultSet,"sfyx_bj")){
			 this.sfyx_bj=resultSet.getInt("sfyx_bj");
		}
		 if(isExistColumn(resultSet,"clzt_dm")){
			 this.clzt_dm=resultSet.getString("clzt_dm");
		}
      } catch (SQLException e) {
              e.printStackTrace();
          }
      }
  @Override
   public String getExist() {
	    String sql="select 1 from "+TABLE_NAME;
		    sql+=" where   xxlr_2_ry_xh="+Utility.getSqlFielStatement("string",this.xxlr_2_ry_xh)+"  ";
	    return sql;
   }


   @Override
   public String getInsert() {
       String sql="insert into "+TABLE_NAME+"(xxlr_2_ry_xh,xxlr_xh,ry_xh,sfyx_bj,clzt_dm) values("+Utility.getSqlFielStatement("string",this.xxlr_2_ry_xh)+","+Utility.getSqlFielStatement("string",this.xxlr_xh)+","+Utility.getSqlFielStatement("string",this.ry_xh)+","+this.sfyx_bj+","+Utility.getSqlFielStatement("string",this.clzt_dm)+")";
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
   sql+= " where  xxlr_2_ry_xh="+Utility.getSqlFielStatement("string",this.xxlr_2_ry_xh)+" ";
   }
   return sql;
}


	@Override
	public String getSelect() {
		String sql="select xxlr_2_ry_xh,xxlr_xh,ry_xh,sfyx_bj,clzt_dm from "+TABLE_NAME;
		if(!Utility.isNullOrEmpty(xxlr_2_ry_xh)){
			sql+=" where  xxlr_2_ry_xh="+Utility.getSqlFielStatement("string",this.xxlr_2_ry_xh)+" ";
		}
		return sql;
	}
	@Override
	public String getSelectByCondition(String condition) {
		String sql="select xxlr_2_ry_xh,xxlr_xh,ry_xh,sfyx_bj,clzt_dm from "+TABLE_NAME;
		if(condition!=null&&condition!=""){
			sql+=" where "+condition;
		}
		return sql;
	}


    @Override
   public String getDelete() {
	 String sql="delete from "+TABLE_NAME+" where  xxlr_2_ry_xh="+Utility.getSqlFielStatement("string",this.xxlr_2_ry_xh)+" ";
	 return sql;
   }


}

