package com.joindoo.jdwechat.beans;

import com.joindoo.jdwechat.Utility;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;
import java.math.BigDecimal;
/**
* 系统 - 公用 - 附件信息。附件只能添加，不能修改
* Author: zhuqiang4433@gmail.com
* Version: CodeGenerator 1.1
* Memo: Auto Created by CodeGenerator on 2020/4/5.
*/

public class TXtGyFjxxModel extends BaseModel implements IBaseModel{
	 public static final String TABLE_NAME="T_XT_GY_FJXX";
	 public static final String SCRIPT_INSERT="INSERT INTO t_xt_gy_fjxx(fj_xh,fj_mc,fj_dx,fj_wz,fj_lj,sjcd_mzs,sx_sj,sfyx_bj,md5,lr_sj,lrry_xh,cgsc_sj,zfj_xh)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
	 public static final String SCRIPT_DELETE="DELETE FROM t_xt_gy_fjxx WHERE  fj_xh=? ";
	 public static final String SCRIPT_UPDATE="UPDATE t_xt_gy_fjxx  SET {0} WHERE  fj_xh=? ";
	 public static final String SCRIPT_SELECT="SELECT fj_xh,fj_mc,fj_dx,fj_wz,fj_lj,sjcd_mzs,sx_sj,sfyx_bj,md5,lr_sj,lrry_xh,cgsc_sj,zfj_xh FROM t_xt_gy_fjxx WHERE (1=1)";
	 public String getDataTableName() {
 	 return TABLE_NAME;
	 }


	/*附件序号(64个字节，能够比32字节多存储点内容，包含文件的标识和后缀名)*/ 
	protected String fj_xh;
	public String getfj_xh (){ return fj_xh;}
	public void setfj_xh(String value){
		this.fj_xh=value;
 		this.onChangeProperty("fj_xh",this.fj_xh,value);
 	}

	/*附件名称*/ 
	protected String fj_mc;
	public String getfj_mc (){ return fj_mc;}
	public void setfj_mc(String value){
		this.fj_mc=value;
 		this.onChangeProperty("fj_mc",this.fj_mc,value);
 	}

	/*消息附件文件大小数值（B）*/ 
	protected Integer fj_dx;
	public Integer getfj_dx (){ return fj_dx;}
	public void setfj_dx(Integer value){
		this.fj_dx=value;
 		this.onChangeProperty("fj_dx",this.fj_dx,value);
 	}

	/*文件后缀名*/ 
	protected String fj_wz;
	public String getfj_wz (){ return fj_wz;}
	public void setfj_wz(String value){
		this.fj_wz=value;
 		this.onChangeProperty("fj_wz",this.fj_wz,value);
 	}

	/*文件路径*/ 
	protected String fj_lj;
	public String getfj_lj (){ return fj_lj;}
	public void setfj_lj(String value){
		this.fj_lj=value;
 		this.onChangeProperty("fj_lj",this.fj_lj,value);
 	}

	/*时间长度秒钟数, 如果是音频视频，则包含时间长度数值（秒）*/ 
	protected Integer sjcd_mzs;
	public Integer getsjcd_mzs (){ return sjcd_mzs;}
	public void setsjcd_mzs(Integer value){
		this.sjcd_mzs=value;
 		this.onChangeProperty("sjcd_mzs",this.sjcd_mzs,value);
 	}

	/*失效日期*/ 
	protected Date sx_sj;
	public Date getsx_sj (){ return sx_sj;}
	public void setsx_sj(Date value){
		this.sx_sj=value;
 		this.onChangeProperty("sx_sj",this.sx_sj,value);
 	}

	/*有效标记(0无效，1有效)*/ 
	protected Integer sfyx_bj;
	public Integer getsfyx_bj (){ return sfyx_bj;}
	public void setsfyx_bj(Integer value){
		this.sfyx_bj=value;
 		this.onChangeProperty("sfyx_bj",this.sfyx_bj,value);
 	}

	/*文件MD5值*/ 
	protected String md5;
	public String getmd5 (){ return md5;}
	public void setmd5(String value){
		this.md5=value;
 		this.onChangeProperty("md5",this.md5,value);
 	}

	/*录入时间*/ 
	protected Date lr_sj;
	public Date getlr_sj (){ return lr_sj;}
	public void setlr_sj(Date value){
		this.lr_sj=value;
 		this.onChangeProperty("lr_sj",this.lr_sj,value);
 	}

	/*录入人员*/ 
	protected String lrry_xh;
	public String getlrry_xh (){ return lrry_xh;}
	public void setlrry_xh(String value){
		this.lrry_xh=value;
 		this.onChangeProperty("lrry_xh",this.lrry_xh,value);
 	}

	/*成功上传时间*/ 
	protected Date cgsc_sj;
	public Date getcgsc_sj (){ return cgsc_sj;}
	public void setcgsc_sj(Date value){
		this.cgsc_sj=value;
 		this.onChangeProperty("cgsc_sj",this.cgsc_sj,value);
 	}

	/*主附件序号*/ 
	protected String zfj_xh;
	public String getzfj_xh (){ return zfj_xh;}
	public void setzfj_xh(String value){
		this.zfj_xh=value;
 		this.onChangeProperty("zfj_xh",this.zfj_xh,value);
 	}



	public TXtGyFjxxModel(){
		super();
		this._keyProperty.add("fj_xh");
		initFieldsType();
	}

	private void initFieldsType(){
		this._fieldType.put("fj_xh","String");
		this._fieldType.put("fj_mc","String");
		this._fieldType.put("fj_dx","Integer");
		this._fieldType.put("fj_wz","String");
		this._fieldType.put("fj_lj","String");
		this._fieldType.put("sjcd_mzs","Integer");
		this._fieldType.put("sx_sj","Date");
		this._fieldType.put("sfyx_bj","Integer");
		this._fieldType.put("md5","String");
		this._fieldType.put("lr_sj","Date");
		this._fieldType.put("lrry_xh","String");
		this._fieldType.put("cgsc_sj","Date");
		this._fieldType.put("zfj_xh","String");
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
		 if(isExistColumn(resultSet,"fj_mc")){
			 this.fj_mc=resultSet.getString("fj_mc");
		}
		 if(isExistColumn(resultSet,"fj_dx")){
			 this.fj_dx=resultSet.getInt("fj_dx");
			if (resultSet.wasNull()) {
				this.fj_dx=null;
			}
		}
		 if(isExistColumn(resultSet,"fj_wz")){
			 this.fj_wz=resultSet.getString("fj_wz");
		}
		 if(isExistColumn(resultSet,"fj_lj")){
			 this.fj_lj=resultSet.getString("fj_lj");
		}
		 if(isExistColumn(resultSet,"sjcd_mzs")){
			 this.sjcd_mzs=resultSet.getInt("sjcd_mzs");
			if (resultSet.wasNull()) {
				this.sjcd_mzs=null;
			}
		}
		 if(isExistColumn(resultSet,"sx_sj")){
			 this.sx_sj=(Date)resultSet.getObject("sx_sj");
		}
		 if(isExistColumn(resultSet,"sfyx_bj")){
			 this.sfyx_bj=resultSet.getInt("sfyx_bj");
		}
		 if(isExistColumn(resultSet,"md5")){
			 this.md5=resultSet.getString("md5");
		}
		 if(isExistColumn(resultSet,"lr_sj")){
			 this.lr_sj=(Date)resultSet.getObject("lr_sj");
		}
		 if(isExistColumn(resultSet,"lrry_xh")){
			 this.lrry_xh=resultSet.getString("lrry_xh");
		}
		 if(isExistColumn(resultSet,"cgsc_sj")){
			 this.cgsc_sj=(Date)resultSet.getObject("cgsc_sj");
		}
		 if(isExistColumn(resultSet,"zfj_xh")){
			 this.zfj_xh=resultSet.getString("zfj_xh");
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
       String sql="insert into "+TABLE_NAME+"(fj_xh,fj_mc,fj_dx,fj_wz,fj_lj,sjcd_mzs,sx_sj,sfyx_bj,md5,lr_sj,lrry_xh,cgsc_sj,zfj_xh) values("+Utility.getSqlFielStatement("string",this.fj_xh)+","+Utility.getSqlFielStatement("string",this.fj_mc)+","+this.fj_dx+","+Utility.getSqlFielStatement("string",this.fj_wz)+","+Utility.getSqlFielStatement("string",this.fj_lj)+","+this.sjcd_mzs+","+Utility.getSqlFielStatement("date",this.sx_sj)+","+this.sfyx_bj+","+Utility.getSqlFielStatement("string",this.md5)+","+Utility.getSqlFielStatement("date",this.lr_sj)+","+Utility.getSqlFielStatement("string",this.lrry_xh)+","+Utility.getSqlFielStatement("date",this.cgsc_sj)+","+Utility.getSqlFielStatement("string",this.zfj_xh)+")";
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
		String sql="select fj_xh,fj_mc,fj_dx,fj_wz,fj_lj,sjcd_mzs,sx_sj,sfyx_bj,md5,lr_sj,lrry_xh,cgsc_sj,zfj_xh from "+TABLE_NAME;
		if(!Utility.isNullOrEmpty(fj_xh)){
			sql+=" where  fj_xh="+Utility.getSqlFielStatement("string",this.fj_xh)+" ";
		}
		return sql;
	}
	@Override
	public String getSelectByCondition(String condition) {
		String sql="select fj_xh,fj_mc,fj_dx,fj_wz,fj_lj,sjcd_mzs,sx_sj,sfyx_bj,md5,lr_sj,lrry_xh,cgsc_sj,zfj_xh from "+TABLE_NAME;
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

