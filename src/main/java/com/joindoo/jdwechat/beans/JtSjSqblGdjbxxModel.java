package com.joindoo.jdwechat.beans;

import com.joindoo.jdwechat.Utility;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;
import java.math.BigDecimal;
/**
* 数据——诉求办理——工单基本信息
* Author: zhuqiang4433@gmail.com
* Memo: Auto Created by CodeGenerator on 2018/6/8.
*/

public class JtSjSqblGdjbxxModel extends BaseModel implements IBaseModel{
	 public static final String TABLE_NAME="JT_SJ_SQBL_GDJBXX";
	 public static final String SCRIPT_INSERT="INSERT INTO jt_sj_sqbl_gdjbxx(ID,GDBH,SQMD,SQNR,SQLX,HFLX,GJZ,SJGK,SQHM,SQR,LDSJ,SQSJFSSJ,SQDZ,SFJJ,SFBM,TBBJ,GUID,CBDW,CBDW2,LXDH1,LXDH2,SQR_XB,SQLYQD,WJGK,HFJG)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	 public static final String SCRIPT_DELETE="DELETE FROM jt_sj_sqbl_gdjbxx WHERE  ID=? ";
	 public static final String SCRIPT_UPDATE="UPDATE jt_sj_sqbl_gdjbxx  SET {0} WHERE  ID=? ";
	 public static final String SCRIPT_SELECT="SELECT ID,GDBH,SQMD,SQNR,SQLX,HFLX,GJZ,SJGK,SQHM,SQR,LDSJ,SQSJFSSJ,SQDZ,SFJJ,SFBM,TBBJ,GUID,CBDW,CBDW2,LXDH1,LXDH2,SQR_XB,SQLYQD,WJGK,HFJG FROM jt_sj_sqbl_gdjbxx WHERE (1=1)";
	 public String getDataTableName() {
 	 return TABLE_NAME;
	 }


	/**/ 
	protected String id;
	public String getid (){ return id;}
	public void setid(String value){
		this.id=value;
 		this.onChangeProperty("id",this.id,value);
 	}

	/*工单编号*/ 
	protected String gdbh;
	public String getgdbh (){ return gdbh;}
	public void setgdbh(String value){
		this.gdbh=value;
 		this.onChangeProperty("gdbh",this.gdbh,value);
 	}

	/*诉求目的（即工单标题）*/ 
	protected String sqmd;
	public String getsqmd (){ return sqmd;}
	public void setsqmd(String value){
		this.sqmd=value;
 		this.onChangeProperty("sqmd",this.sqmd,value);
 	}

	/*诉求内容*/ 
	protected String sqnr;
	public String getsqnr (){ return sqnr;}
	public void setsqnr(String value){
		this.sqnr=value;
 		this.onChangeProperty("sqnr",this.sqnr,value);
 	}

	/*诉求类型（咨询、执法类）*/ 
	protected String sqlx;
	public String getsqlx (){ return sqlx;}
	public void setsqlx(String value){
		this.sqlx=value;
 		this.onChangeProperty("sqlx",this.sqlx,value);
 	}

	/*回访类型（正常回访、无需回访）*/ 
	protected String hflx;
	public String gethflx (){ return hflx;}
	public void sethflx(String value){
		this.hflx=value;
 		this.onChangeProperty("hflx",this.hflx,value);
 	}

	/*关键字*/ 
	protected String gjz;
	public String getgjz (){ return gjz;}
	public void setgjz(String value){
		this.gjz=value;
 		this.onChangeProperty("gjz",this.gjz,value);
 	}

	/*三级归口*/ 
	protected String sjgk;
	public String getsjgk (){ return sjgk;}
	public void setsjgk(String value){
		this.sjgk=value;
 		this.onChangeProperty("sjgk",this.sjgk,value);
 	}

	/*诉求号码*/ 
	protected String sqhm;
	public String getsqhm (){ return sqhm;}
	public void setsqhm(String value){
		this.sqhm=value;
 		this.onChangeProperty("sqhm",this.sqhm,value);
 	}

	/*诉求人*/ 
	protected String sqr;
	public String getsqr (){ return sqr;}
	public void setsqr(String value){
		this.sqr=value;
 		this.onChangeProperty("sqr",this.sqr,value);
 	}

	/*诉求来电时间*/ 
	protected Date ldsj;
	public Date getldsj (){ return ldsj;}
	public void setldsj(Date value){
		this.ldsj=value;
 		this.onChangeProperty("ldsj",this.ldsj,value);
 	}

	/* 诉求事件发生日期*/ 
	protected Date sqsjfssj;
	public Date getsqsjfssj (){ return sqsjfssj;}
	public void setsqsjfssj(Date value){
		this.sqsjfssj=value;
 		this.onChangeProperty("sqsjfssj",this.sqsjfssj,value);
 	}

	/*诉求地址*/ 
	protected String sqdz;
	public String getsqdz (){ return sqdz;}
	public void setsqdz(String value){
		this.sqdz=value;
 		this.onChangeProperty("sqdz",this.sqdz,value);
 	}

	/*是否紧急*/ 
	protected String sfjj;
	public String getsfjj (){ return sfjj;}
	public void setsfjj(String value){
		this.sfjj=value;
 		this.onChangeProperty("sfjj",this.sfjj,value);
 	}

	/*是否保密*/ 
	protected String sfbm;
	public String getsfbm (){ return sfbm;}
	public void setsfbm(String value){
		this.sfbm=value;
 		this.onChangeProperty("sfbm",this.sfbm,value);
 	}

	/*同步标记，1：已同步，0或空：未同步*/ 
	protected Integer tbbj;
	public Integer gettbbj (){ return tbbj;}
	public void settbbj(Integer value){
		this.tbbj=value;
 		this.onChangeProperty("tbbj",this.tbbj,value);
 	}

	/*抓取中间量*/ 
	protected String guid;
	public String getguid (){ return guid;}
	public void setguid(String value){
		this.guid=value;
 		this.onChangeProperty("guid",this.guid,value);
 	}

	/*（园区单位等id）*/ 
	protected String cbdw;
	public String getcbdw (){ return cbdw;}
	public void setcbdw(String value){
		this.cbdw=value;
 		this.onChangeProperty("cbdw",this.cbdw,value);
 	}

	/*（区id）*/ 
	protected String cbdw2;
	public String getcbdw2 (){ return cbdw2;}
	public void setcbdw2(String value){
		this.cbdw2=value;
 		this.onChangeProperty("cbdw2",this.cbdw2,value);
 	}

	/*联系电话1*/ 
	protected String lxdh1;
	public String getlxdh1 (){ return lxdh1;}
	public void setlxdh1(String value){
		this.lxdh1=value;
 		this.onChangeProperty("lxdh1",this.lxdh1,value);
 	}

	/*联系电话2*/ 
	protected String lxdh2;
	public String getlxdh2 (){ return lxdh2;}
	public void setlxdh2(String value){
		this.lxdh2=value;
 		this.onChangeProperty("lxdh2",this.lxdh2,value);
 	}

	/*诉求人性别*/ 
	protected String sqr_xb;
	public String getsqr_xb (){ return sqr_xb;}
	public void setsqr_xb(String value){
		this.sqr_xb=value;
 		this.onChangeProperty("sqr_xb",this.sqr_xb,value);
 	}

	/*诉求来源渠道*/ 
	protected String sqlyqd;
	public String getsqlyqd (){ return sqlyqd;}
	public void setsqlyqd(String value){
		this.sqlyqd=value;
 		this.onChangeProperty("sqlyqd",this.sqlyqd,value);
 	}

	/*五级归口*/ 
	protected String wjgk;
	public String getwjgk (){ return wjgk;}
	public void setwjgk(String value){
		this.wjgk=value;
 		this.onChangeProperty("wjgk",this.wjgk,value);
 	}

	/*回访结果*/ 
	protected String hfjg;
	public String gethfjg (){ return hfjg;}
	public void sethfjg(String value){
		this.hfjg=value;
 		this.onChangeProperty("hfjg",this.hfjg,value);
 	}



	public JtSjSqblGdjbxxModel(){
		super();
		this._keyProperty.add("id");
		initFieldsType();
	}

	private void initFieldsType(){
		this._fieldType.put("id","String");
		this._fieldType.put("gdbh","String");
		this._fieldType.put("sqmd","String");
		this._fieldType.put("sqnr","String");
		this._fieldType.put("sqlx","String");
		this._fieldType.put("hflx","String");
		this._fieldType.put("gjz","String");
		this._fieldType.put("sjgk","String");
		this._fieldType.put("sqhm","String");
		this._fieldType.put("sqr","String");
		this._fieldType.put("ldsj","Date");
		this._fieldType.put("sqsjfssj","Date");
		this._fieldType.put("sqdz","String");
		this._fieldType.put("sfjj","String");
		this._fieldType.put("sfbm","String");
		this._fieldType.put("tbbj","Integer");
		this._fieldType.put("guid","String");
		this._fieldType.put("cbdw","String");
		this._fieldType.put("cbdw2","String");
		this._fieldType.put("lxdh1","String");
		this._fieldType.put("lxdh2","String");
		this._fieldType.put("sqr_xb","String");
		this._fieldType.put("sqlyqd","String");
		this._fieldType.put("wjgk","String");
		this._fieldType.put("hfjg","String");
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
		 if(isExistColumn(resultSet,"gdbh")){
			 this.gdbh=resultSet.getString("gdbh");
		}
		 if(isExistColumn(resultSet,"sqmd")){
			 this.sqmd=resultSet.getString("sqmd");
		}
		 if(isExistColumn(resultSet,"sqnr")){
			 this.sqnr=resultSet.getString("sqnr");
		}
		 if(isExistColumn(resultSet,"sqlx")){
			 this.sqlx=resultSet.getString("sqlx");
		}
		 if(isExistColumn(resultSet,"hflx")){
			 this.hflx=resultSet.getString("hflx");
		}
		 if(isExistColumn(resultSet,"gjz")){
			 this.gjz=resultSet.getString("gjz");
		}
		 if(isExistColumn(resultSet,"sjgk")){
			 this.sjgk=resultSet.getString("sjgk");
		}
		 if(isExistColumn(resultSet,"sqhm")){
			 this.sqhm=resultSet.getString("sqhm");
		}
		 if(isExistColumn(resultSet,"sqr")){
			 this.sqr=resultSet.getString("sqr");
		}
		 if(isExistColumn(resultSet,"ldsj")){
			 this.ldsj=(Date)resultSet.getObject("ldsj");
		}
		 if(isExistColumn(resultSet,"sqsjfssj")){
			 this.sqsjfssj=(Date)resultSet.getObject("sqsjfssj");
		}
		 if(isExistColumn(resultSet,"sqdz")){
			 this.sqdz=resultSet.getString("sqdz");
		}
		 if(isExistColumn(resultSet,"sfjj")){
			 this.sfjj=resultSet.getString("sfjj");
		}
		 if(isExistColumn(resultSet,"sfbm")){
			 this.sfbm=resultSet.getString("sfbm");
		}
		 if(isExistColumn(resultSet,"tbbj")){
			 this.tbbj=resultSet.getInt("tbbj");
		}
		 if(isExistColumn(resultSet,"guid")){
			 this.guid=resultSet.getString("guid");
		}
		 if(isExistColumn(resultSet,"cbdw")){
			 this.cbdw=resultSet.getString("cbdw");
		}
		 if(isExistColumn(resultSet,"cbdw2")){
			 this.cbdw2=resultSet.getString("cbdw2");
		}
		 if(isExistColumn(resultSet,"lxdh1")){
			 this.lxdh1=resultSet.getString("lxdh1");
		}
		 if(isExistColumn(resultSet,"lxdh2")){
			 this.lxdh2=resultSet.getString("lxdh2");
		}
		 if(isExistColumn(resultSet,"sqr_xb")){
			 this.sqr_xb=resultSet.getString("sqr_xb");
		}
		 if(isExistColumn(resultSet,"sqlyqd")){
			 this.sqlyqd=resultSet.getString("sqlyqd");
		}
		 if(isExistColumn(resultSet,"wjgk")){
			 this.wjgk=resultSet.getString("wjgk");
		}
		 if(isExistColumn(resultSet,"hfjg")){
			 this.hfjg=resultSet.getString("hfjg");
		}
      } catch (SQLException e) {
              e.printStackTrace();
          }
      }
  @Override
   public String getExist() {
	    String sql="select 1 from "+TABLE_NAME;
		    sql+=" where   ID="+Utility.getSqlFielStatement("string",this.id)+"  ";
	    return sql;
   }


   @Override
   public String getInsert() {
       String sql="insert into "+TABLE_NAME+"(ID,GDBH,SQMD,SQNR,SQLX,HFLX,GJZ,SJGK,SQHM,SQR,LDSJ,SQSJFSSJ,SQDZ,SFJJ,SFBM,TBBJ,GUID,CBDW,CBDW2,LXDH1,LXDH2,SQR_XB,SQLYQD,WJGK,HFJG) values("+Utility.getSqlFielStatement("string",this.id)+","+Utility.getSqlFielStatement("string",this.gdbh)+","+Utility.getSqlFielStatement("string",this.sqmd)+","+Utility.getSqlFielStatement("string",this.sqnr)+","+Utility.getSqlFielStatement("string",this.sqlx)+","+Utility.getSqlFielStatement("string",this.hflx)+","+Utility.getSqlFielStatement("string",this.gjz)+","+Utility.getSqlFielStatement("string",this.sjgk)+","+Utility.getSqlFielStatement("string",this.sqhm)+","+Utility.getSqlFielStatement("string",this.sqr)+","+Utility.getSqlFielStatement("date",this.ldsj)+","+Utility.getSqlFielStatement("date",this.sqsjfssj)+","+Utility.getSqlFielStatement("string",this.sqdz)+","+Utility.getSqlFielStatement("string",this.sfjj)+","+Utility.getSqlFielStatement("string",this.sfbm)+","+this.tbbj+","+Utility.getSqlFielStatement("string",this.guid)+","+Utility.getSqlFielStatement("string",this.cbdw)+","+Utility.getSqlFielStatement("string",this.cbdw2)+","+Utility.getSqlFielStatement("string",this.lxdh1)+","+Utility.getSqlFielStatement("string",this.lxdh2)+","+Utility.getSqlFielStatement("string",this.sqr_xb)+","+Utility.getSqlFielStatement("string",this.sqlyqd)+","+Utility.getSqlFielStatement("string",this.wjgk)+","+Utility.getSqlFielStatement("string",this.hfjg)+")";
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
   sql+= " where  ID="+Utility.getSqlFielStatement("string",this.id)+" ";
   }
   return sql;
}


	@Override
	public String getSelect() {
		String sql="select ID,GDBH,SQMD,SQNR,SQLX,HFLX,GJZ,SJGK,SQHM,SQR,LDSJ,SQSJFSSJ,SQDZ,SFJJ,SFBM,TBBJ,GUID,CBDW,CBDW2,LXDH1,LXDH2,SQR_XB,SQLYQD,WJGK,HFJG from "+TABLE_NAME;
		if(!Utility.isNullOrEmpty(id)){
			sql+=" where  ID="+Utility.getSqlFielStatement("string",this.id)+" ";
		}
		return sql;
	}
	@Override
	public String getSelectByCondition(String condition) {
		String sql="select ID,GDBH,SQMD,SQNR,SQLX,HFLX,GJZ,SJGK,SQHM,SQR,LDSJ,SQSJFSSJ,SQDZ,SFJJ,SFBM,TBBJ,GUID,CBDW,CBDW2,LXDH1,LXDH2,SQR_XB,SQLYQD,WJGK,HFJG from "+TABLE_NAME;
		if(condition!=null&&condition!=""){
			sql+=" where "+condition;
		}
		return sql;
	}


    @Override
   public String getDelete() {
	 String sql="delete from "+TABLE_NAME+" where  ID="+Utility.getSqlFielStatement("string",this.id)+" ";
	 return sql;
   }


}

