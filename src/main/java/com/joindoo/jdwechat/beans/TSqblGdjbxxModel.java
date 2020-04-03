package com.joindoo.jdwechat.beans;

import com.joindoo.jdwechat.Utility;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;
import java.math.BigDecimal;
/**
* 诉求办理 - 工单基本信息
* Author: zhuqiang4433@gmail.com
* Version: CodeGenerator 1.1
* Memo: Auto Created by CodeGenerator on 2020/4/3.
*/

public class TSqblGdjbxxModel extends BaseModel implements IBaseModel{
	 public static final String TABLE_NAME="T_SQBL_GDJBXX";
	 public static final String SCRIPT_INSERT="INSERT INTO t_sqbl_gdjbxx(id,hfhm,gdbh,sqlx_dm,sqrxm,sqsjfssj,sqrhm,sqnr,sqrdz,lr_sj,sfbm_bj,sqsjfsdz,sfhf_bj,wxh,tb_bj,tb_sj,jd_id,jd_mc,sq_id,sq_mc,wxq_id,wxq_mc,fj_xhs)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	 public static final String SCRIPT_DELETE="DELETE FROM t_sqbl_gdjbxx WHERE  id=? ";
	 public static final String SCRIPT_UPDATE="UPDATE t_sqbl_gdjbxx  SET {0} WHERE  id=? ";
	 public static final String SCRIPT_SELECT="SELECT id,hfhm,gdbh,sqlx_dm,sqrxm,sqsjfssj,sqrhm,sqnr,sqrdz,lr_sj,sfbm_bj,sqsjfsdz,sfhf_bj,wxh,tb_bj,tb_sj,jd_id,jd_mc,sq_id,sq_mc,wxq_id,wxq_mc,fj_xhs FROM t_sqbl_gdjbxx WHERE (1=1)";
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

	/*回访号码*/ 
	protected String hfhm;
	public String gethfhm (){ return hfhm;}
	public void sethfhm(String value){
		this.hfhm=value;
 		this.onChangeProperty("hfhm",this.hfhm,value);
 	}

	/*工单编号*/ 
	protected String gdbh;
	public String getgdbh (){ return gdbh;}
	public void setgdbh(String value){
		this.gdbh=value;
 		this.onChangeProperty("gdbh",this.gdbh,value);
 	}

	/*诉求类型（咨询、执法类）*/ 
	protected String sqlx_dm;
	public String getsqlx_dm (){ return sqlx_dm;}
	public void setsqlx_dm(String value){
		this.sqlx_dm=value;
 		this.onChangeProperty("sqlx_dm",this.sqlx_dm,value);
 	}

	/*诉求人姓名*/ 
	protected String sqrxm;
	public String getsqrxm (){ return sqrxm;}
	public void setsqrxm(String value){
		this.sqrxm=value;
 		this.onChangeProperty("sqrxm",this.sqrxm,value);
 	}

	/*诉求事件发生时间*/ 
	protected Date sqsjfssj;
	public Date getsqsjfssj (){ return sqsjfssj;}
	public void setsqsjfssj(Date value){
		this.sqsjfssj=value;
 		this.onChangeProperty("sqsjfssj",this.sqsjfssj,value);
 	}

	/*诉求人号码*/ 
	protected String sqrhm;
	public String getsqrhm (){ return sqrhm;}
	public void setsqrhm(String value){
		this.sqrhm=value;
 		this.onChangeProperty("sqrhm",this.sqrhm,value);
 	}

	/*诉求内容*/ 
	protected String sqnr;
	public String getsqnr (){ return sqnr;}
	public void setsqnr(String value){
		this.sqnr=value;
 		this.onChangeProperty("sqnr",this.sqnr,value);
 	}

	/*诉求人地址*/ 
	protected String sqrdz;
	public String getsqrdz (){ return sqrdz;}
	public void setsqrdz(String value){
		this.sqrdz=value;
 		this.onChangeProperty("sqrdz",this.sqrdz,value);
 	}

	/*录入时间*/ 
	protected Date lr_sj;
	public Date getlr_sj (){ return lr_sj;}
	public void setlr_sj(Date value){
		this.lr_sj=value;
 		this.onChangeProperty("lr_sj",this.lr_sj,value);
 	}

	/*保密*/ 
	protected Boolean sfbm_bj;
	public Boolean getsfbm_bj (){ return sfbm_bj;}
	public void setsfbm_bj(Boolean value){
		this.sfbm_bj=value;
 		this.onChangeProperty("sfbm_bj",this.sfbm_bj,value);
 	}

	/*诉求事件发生地*/ 
	protected String sqsjfsdz;
	public String getsqsjfsdz (){ return sqsjfsdz;}
	public void setsqsjfsdz(String value){
		this.sqsjfsdz=value;
 		this.onChangeProperty("sqsjfsdz",this.sqsjfsdz,value);
 	}

	/*是否回访*/ 
	protected Boolean sfhf_bj;
	public Boolean getsfhf_bj (){ return sfhf_bj;}
	public void setsfhf_bj(Boolean value){
		this.sfhf_bj=value;
 		this.onChangeProperty("sfhf_bj",this.sfhf_bj,value);
 	}

	/*微信号*/ 
	protected String wxh;
	public String getwxh (){ return wxh;}
	public void setwxh(String value){
		this.wxh=value;
 		this.onChangeProperty("wxh",this.wxh,value);
 	}

	/*同步标记*/ 
	protected Boolean tb_bj;
	public Boolean gettb_bj (){ return tb_bj;}
	public void settb_bj(Boolean value){
		this.tb_bj=value;
 		this.onChangeProperty("tb_bj",this.tb_bj,value);
 	}

	/*同步时间*/ 
	protected Date tb_sj;
	public Date gettb_sj (){ return tb_sj;}
	public void settb_sj(Date value){
		this.tb_sj=value;
 		this.onChangeProperty("tb_sj",this.tb_sj,value);
 	}

	/*街道id*/ 
	protected String jd_id;
	public String getjd_id (){ return jd_id;}
	public void setjd_id(String value){
		this.jd_id=value;
 		this.onChangeProperty("jd_id",this.jd_id,value);
 	}

	/*街道名称*/ 
	protected String jd_mc;
	public String getjd_mc (){ return jd_mc;}
	public void setjd_mc(String value){
		this.jd_mc=value;
 		this.onChangeProperty("jd_mc",this.jd_mc,value);
 	}

	/*社区id*/ 
	protected String sq_id;
	public String getsq_id (){ return sq_id;}
	public void setsq_id(String value){
		this.sq_id=value;
 		this.onChangeProperty("sq_id",this.sq_id,value);
 	}

	/*社区名称*/ 
	protected String sq_mc;
	public String getsq_mc (){ return sq_mc;}
	public void setsq_mc(String value){
		this.sq_mc=value;
 		this.onChangeProperty("sq_mc",this.sq_mc,value);
 	}

	/*微信群id*/ 
	protected String wxq_id;
	public String getwxq_id (){ return wxq_id;}
	public void setwxq_id(String value){
		this.wxq_id=value;
 		this.onChangeProperty("wxq_id",this.wxq_id,value);
 	}

	/*微信群名称*/ 
	protected String wxq_mc;
	public String getwxq_mc (){ return wxq_mc;}
	public void setwxq_mc(String value){
		this.wxq_mc=value;
 		this.onChangeProperty("wxq_mc",this.wxq_mc,value);
 	}

	/*附件序号列表*/ 
	protected String fj_xhs;
	public String getfj_xhs (){ return fj_xhs;}
	public void setfj_xhs(String value){
		this.fj_xhs=value;
 		this.onChangeProperty("fj_xhs",this.fj_xhs,value);
 	}



	public TSqblGdjbxxModel(){
		super();
		this._keyProperty.add("id");
		initFieldsType();
	}

	private void initFieldsType(){
		this._fieldType.put("id","String");
		this._fieldType.put("hfhm","String");
		this._fieldType.put("gdbh","String");
		this._fieldType.put("sqlx_dm","String");
		this._fieldType.put("sqrxm","String");
		this._fieldType.put("sqsjfssj","Date");
		this._fieldType.put("sqrhm","String");
		this._fieldType.put("sqnr","String");
		this._fieldType.put("sqrdz","String");
		this._fieldType.put("lr_sj","Date");
		this._fieldType.put("sfbm_bj","Boolean");
		this._fieldType.put("sqsjfsdz","String");
		this._fieldType.put("sfhf_bj","Boolean");
		this._fieldType.put("wxh","String");
		this._fieldType.put("tb_bj","Boolean");
		this._fieldType.put("tb_sj","Date");
		this._fieldType.put("jd_id","String");
		this._fieldType.put("jd_mc","String");
		this._fieldType.put("sq_id","String");
		this._fieldType.put("sq_mc","String");
		this._fieldType.put("wxq_id","String");
		this._fieldType.put("wxq_mc","String");
		this._fieldType.put("fj_xhs","String");
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
		 if(isExistColumn(resultSet,"hfhm")){
			 this.hfhm=resultSet.getString("hfhm");
		}
		 if(isExistColumn(resultSet,"gdbh")){
			 this.gdbh=resultSet.getString("gdbh");
		}
		 if(isExistColumn(resultSet,"sqlx_dm")){
			 this.sqlx_dm=resultSet.getString("sqlx_dm");
		}
		 if(isExistColumn(resultSet,"sqrxm")){
			 this.sqrxm=resultSet.getString("sqrxm");
		}
		 if(isExistColumn(resultSet,"sqsjfssj")){
			 this.sqsjfssj=(Date)resultSet.getObject("sqsjfssj");
		}
		 if(isExistColumn(resultSet,"sqrhm")){
			 this.sqrhm=resultSet.getString("sqrhm");
		}
		 if(isExistColumn(resultSet,"sqnr")){
			 this.sqnr=resultSet.getString("sqnr");
		}
		 if(isExistColumn(resultSet,"sqrdz")){
			 this.sqrdz=resultSet.getString("sqrdz");
		}
		 if(isExistColumn(resultSet,"lr_sj")){
			 this.lr_sj=(Date)resultSet.getObject("lr_sj");
		}
		 if(isExistColumn(resultSet,"sfbm_bj")){
			 this.sfbm_bj=resultSet.getBoolean("sfbm_bj");
		}
		 if(isExistColumn(resultSet,"sqsjfsdz")){
			 this.sqsjfsdz=resultSet.getString("sqsjfsdz");
		}
		 if(isExistColumn(resultSet,"sfhf_bj")){
			 this.sfhf_bj=resultSet.getBoolean("sfhf_bj");
		}
		 if(isExistColumn(resultSet,"wxh")){
			 this.wxh=resultSet.getString("wxh");
		}
		 if(isExistColumn(resultSet,"tb_bj")){
			 this.tb_bj=resultSet.getBoolean("tb_bj");
		}
		 if(isExistColumn(resultSet,"tb_sj")){
			 this.tb_sj=(Date)resultSet.getObject("tb_sj");
		}
		 if(isExistColumn(resultSet,"jd_id")){
			 this.jd_id=resultSet.getString("jd_id");
		}
		 if(isExistColumn(resultSet,"jd_mc")){
			 this.jd_mc=resultSet.getString("jd_mc");
		}
		 if(isExistColumn(resultSet,"sq_id")){
			 this.sq_id=resultSet.getString("sq_id");
		}
		 if(isExistColumn(resultSet,"sq_mc")){
			 this.sq_mc=resultSet.getString("sq_mc");
		}
		 if(isExistColumn(resultSet,"wxq_id")){
			 this.wxq_id=resultSet.getString("wxq_id");
		}
		 if(isExistColumn(resultSet,"wxq_mc")){
			 this.wxq_mc=resultSet.getString("wxq_mc");
		}
		 if(isExistColumn(resultSet,"fj_xhs")){
			 this.fj_xhs=resultSet.getString("fj_xhs");
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
       String sql="insert into "+TABLE_NAME+"(id,hfhm,gdbh,sqlx_dm,sqrxm,sqsjfssj,sqrhm,sqnr,sqrdz,lr_sj,sfbm_bj,sqsjfsdz,sfhf_bj,wxh,tb_bj,tb_sj,jd_id,jd_mc,sq_id,sq_mc,wxq_id,wxq_mc,fj_xhs) values("+Utility.getSqlFielStatement("string",this.id)+","+Utility.getSqlFielStatement("string",this.hfhm)+","+Utility.getSqlFielStatement("string",this.gdbh)+","+Utility.getSqlFielStatement("string",this.sqlx_dm)+","+Utility.getSqlFielStatement("string",this.sqrxm)+","+Utility.getSqlFielStatement("date",this.sqsjfssj)+","+Utility.getSqlFielStatement("string",this.sqrhm)+","+Utility.getSqlFielStatement("string",this.sqnr)+","+Utility.getSqlFielStatement("string",this.sqrdz)+","+Utility.getSqlFielStatement("date",this.lr_sj)+","+this.sfbm_bj+","+Utility.getSqlFielStatement("string",this.sqsjfsdz)+","+this.sfhf_bj+","+Utility.getSqlFielStatement("string",this.wxh)+","+this.tb_bj+","+Utility.getSqlFielStatement("date",this.tb_sj)+","+Utility.getSqlFielStatement("string",this.jd_id)+","+Utility.getSqlFielStatement("string",this.jd_mc)+","+Utility.getSqlFielStatement("string",this.sq_id)+","+Utility.getSqlFielStatement("string",this.sq_mc)+","+Utility.getSqlFielStatement("string",this.wxq_id)+","+Utility.getSqlFielStatement("string",this.wxq_mc)+","+Utility.getSqlFielStatement("string",this.fj_xhs)+")";
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
		String sql="select id,hfhm,gdbh,sqlx_dm,sqrxm,sqsjfssj,sqrhm,sqnr,sqrdz,lr_sj,sfbm_bj,sqsjfsdz,sfhf_bj,wxh,tb_bj,tb_sj,jd_id,jd_mc,sq_id,sq_mc,wxq_id,wxq_mc,fj_xhs from "+TABLE_NAME;
		if(!Utility.isNullOrEmpty(id)){
			sql+=" where  id="+Utility.getSqlFielStatement("string",this.id)+" ";
		}
		return sql;
	}
	@Override
	public String getSelectByCondition(String condition) {
		String sql="select id,hfhm,gdbh,sqlx_dm,sqrxm,sqsjfssj,sqrhm,sqnr,sqrdz,lr_sj,sfbm_bj,sqsjfsdz,sfhf_bj,wxh,tb_bj,tb_sj,jd_id,jd_mc,sq_id,sq_mc,wxq_id,wxq_mc,fj_xhs from "+TABLE_NAME;
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

