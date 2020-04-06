package com.joindoo.jdwechat.beans;

import com.joindoo.jdwechat.Utility;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;
import java.math.BigDecimal;
/**
* 系统 - 微信用户 微信用户openid,昵称等
* Author: zhuqiang4433@gmail.com
* Version: CodeGenerator 1.1
* Memo: Auto Created by CodeGenerator on 2020/4/5.
*/

public class TXtWxyhModel extends BaseModel implements IBaseModel{
	 public static final String TABLE_NAME="T_XT_WXYH";
	 public static final String SCRIPT_INSERT="INSERT INTO t_xt_wxyh(id,wxaccount,session_key,openid,nickname,avatarurl,gender,province,city,country,model,pixelratio,windowwidth,windowheight,language,version,platform,system,create_time,is_valid,jd_mc,sq_mc,wxq_mc)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	 public static final String SCRIPT_DELETE="DELETE FROM t_xt_wxyh WHERE  id=? ";
	 public static final String SCRIPT_UPDATE="UPDATE t_xt_wxyh  SET {0} WHERE  id=? ";
	 public static final String SCRIPT_SELECT="SELECT id,wxaccount,session_key,openid,nickname,avatarurl,gender,province,city,country,model,pixelratio,windowwidth,windowheight,language,version,platform,system,create_time,is_valid,jd_mc,sq_mc,wxq_mc FROM t_xt_wxyh WHERE (1=1)";
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

	/**/ 
	protected String wxaccount;
	public String getwxaccount (){ return wxaccount;}
	public void setwxaccount(String value){
		this.wxaccount=value;
 		this.onChangeProperty("wxaccount",this.wxaccount,value);
 	}

	/*登录时session*/ 
	protected String session_key;
	public String getsession_key (){ return session_key;}
	public void setsession_key(String value){
		this.session_key=value;
 		this.onChangeProperty("session_key",this.session_key,value);
 	}

	/*微信的openid*/ 
	protected String openid;
	public String getopenid (){ return openid;}
	public void setopenid(String value){
		this.openid=value;
 		this.onChangeProperty("openid",this.openid,value);
 	}

	/*昵称*/ 
	protected String nickname;
	public String getnickname (){ return nickname;}
	public void setnickname(String value){
		this.nickname=value;
 		this.onChangeProperty("nickname",this.nickname,value);
 	}

	/*头像链接*/ 
	protected String avatarurl;
	public String getavatarurl (){ return avatarurl;}
	public void setavatarurl(String value){
		this.avatarurl=value;
 		this.onChangeProperty("avatarurl",this.avatarurl,value);
 	}

	/*性别 0：未知、1：男、2：女*/ 
	protected String gender;
	public String getgender (){ return gender;}
	public void setgender(String value){
		this.gender=value;
 		this.onChangeProperty("gender",this.gender,value);
 	}

	/*所在省*/ 
	protected String province;
	public String getprovince (){ return province;}
	public void setprovince(String value){
		this.province=value;
 		this.onChangeProperty("province",this.province,value);
 	}

	/*城市*/ 
	protected String city;
	public String getcity (){ return city;}
	public void setcity(String value){
		this.city=value;
 		this.onChangeProperty("city",this.city,value);
 	}

	/*国家*/ 
	protected String country;
	public String getcountry (){ return country;}
	public void setcountry(String value){
		this.country=value;
 		this.onChangeProperty("country",this.country,value);
 	}

	/*手机型号*/ 
	protected String model;
	public String getmodel (){ return model;}
	public void setmodel(String value){
		this.model=value;
 		this.onChangeProperty("model",this.model,value);
 	}

	/**/ 
	protected String pixelratio;
	public String getpixelratio (){ return pixelratio;}
	public void setpixelratio(String value){
		this.pixelratio=value;
 		this.onChangeProperty("pixelratio",this.pixelratio,value);
 	}

	/**/ 
	protected String windowwidth;
	public String getwindowwidth (){ return windowwidth;}
	public void setwindowwidth(String value){
		this.windowwidth=value;
 		this.onChangeProperty("windowwidth",this.windowwidth,value);
 	}

	/**/ 
	protected String windowheight;
	public String getwindowheight (){ return windowheight;}
	public void setwindowheight(String value){
		this.windowheight=value;
 		this.onChangeProperty("windowheight",this.windowheight,value);
 	}

	/*语言*/ 
	protected String language;
	public String getlanguage (){ return language;}
	public void setlanguage(String value){
		this.language=value;
 		this.onChangeProperty("language",this.language,value);
 	}

	/*版本*/ 
	protected String version;
	public String getversion (){ return version;}
	public void setversion(String value){
		this.version=value;
 		this.onChangeProperty("version",this.version,value);
 	}

	/*平台*/ 
	protected String platform;
	public String getplatform (){ return platform;}
	public void setplatform(String value){
		this.platform=value;
 		this.onChangeProperty("platform",this.platform,value);
 	}

	/*系统*/ 
	protected String system;
	public String getsystem (){ return system;}
	public void setsystem(String value){
		this.system=value;
 		this.onChangeProperty("system",this.system,value);
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

	/*街道名称*/ 
	protected String jd_mc;
	public String getjd_mc (){ return jd_mc;}
	public void setjd_mc(String value){
		this.jd_mc=value;
 		this.onChangeProperty("jd_mc",this.jd_mc,value);
 	}

	/*社区名称*/ 
	protected String sq_mc;
	public String getsq_mc (){ return sq_mc;}
	public void setsq_mc(String value){
		this.sq_mc=value;
 		this.onChangeProperty("sq_mc",this.sq_mc,value);
 	}

	/*微信群名称*/ 
	protected String wxq_mc;
	public String getwxq_mc (){ return wxq_mc;}
	public void setwxq_mc(String value){
		this.wxq_mc=value;
 		this.onChangeProperty("wxq_mc",this.wxq_mc,value);
 	}



	public TXtWxyhModel(){
		super();
		this._keyProperty.add("id");
		initFieldsType();
	}

	private void initFieldsType(){
		this._fieldType.put("id","String");
		this._fieldType.put("wxaccount","String");
		this._fieldType.put("session_key","String");
		this._fieldType.put("openid","String");
		this._fieldType.put("nickname","String");
		this._fieldType.put("avatarurl","String");
		this._fieldType.put("gender","String");
		this._fieldType.put("province","String");
		this._fieldType.put("city","String");
		this._fieldType.put("country","String");
		this._fieldType.put("model","String");
		this._fieldType.put("pixelratio","String");
		this._fieldType.put("windowwidth","String");
		this._fieldType.put("windowheight","String");
		this._fieldType.put("language","String");
		this._fieldType.put("version","String");
		this._fieldType.put("platform","String");
		this._fieldType.put("system","String");
		this._fieldType.put("create_time","Date");
		this._fieldType.put("is_valid","Integer");
		this._fieldType.put("jd_mc","String");
		this._fieldType.put("sq_mc","String");
		this._fieldType.put("wxq_mc","String");
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
		 if(isExistColumn(resultSet,"wxaccount")){
			 this.wxaccount=resultSet.getString("wxaccount");
		}
		 if(isExistColumn(resultSet,"session_key")){
			 this.session_key=resultSet.getString("session_key");
		}
		 if(isExistColumn(resultSet,"openid")){
			 this.openid=resultSet.getString("openid");
		}
		 if(isExistColumn(resultSet,"nickname")){
			 this.nickname=resultSet.getString("nickname");
		}
		 if(isExistColumn(resultSet,"avatarurl")){
			 this.avatarurl=resultSet.getString("avatarurl");
		}
		 if(isExistColumn(resultSet,"gender")){
			 this.gender=resultSet.getString("gender");
		}
		 if(isExistColumn(resultSet,"province")){
			 this.province=resultSet.getString("province");
		}
		 if(isExistColumn(resultSet,"city")){
			 this.city=resultSet.getString("city");
		}
		 if(isExistColumn(resultSet,"country")){
			 this.country=resultSet.getString("country");
		}
		 if(isExistColumn(resultSet,"model")){
			 this.model=resultSet.getString("model");
		}
		 if(isExistColumn(resultSet,"pixelratio")){
			 this.pixelratio=resultSet.getString("pixelratio");
		}
		 if(isExistColumn(resultSet,"windowwidth")){
			 this.windowwidth=resultSet.getString("windowwidth");
		}
		 if(isExistColumn(resultSet,"windowheight")){
			 this.windowheight=resultSet.getString("windowheight");
		}
		 if(isExistColumn(resultSet,"language")){
			 this.language=resultSet.getString("language");
		}
		 if(isExistColumn(resultSet,"version")){
			 this.version=resultSet.getString("version");
		}
		 if(isExistColumn(resultSet,"platform")){
			 this.platform=resultSet.getString("platform");
		}
		 if(isExistColumn(resultSet,"system")){
			 this.system=resultSet.getString("system");
		}
		 if(isExistColumn(resultSet,"create_time")){
			 this.create_time=(Date)resultSet.getObject("create_time");
		}
		 if(isExistColumn(resultSet,"is_valid")){
			 this.is_valid=resultSet.getInt("is_valid");
		}
		 if(isExistColumn(resultSet,"jd_mc")){
			 this.jd_mc=resultSet.getString("jd_mc");
		}
		 if(isExistColumn(resultSet,"sq_mc")){
			 this.sq_mc=resultSet.getString("sq_mc");
		}
		 if(isExistColumn(resultSet,"wxq_mc")){
			 this.wxq_mc=resultSet.getString("wxq_mc");
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
       String sql="insert into "+TABLE_NAME+"(id,wxaccount,session_key,openid,nickname,avatarurl,gender,province,city,country,model,pixelratio,windowwidth,windowheight,language,version,platform,system,create_time,is_valid,jd_mc,sq_mc,wxq_mc) values("+Utility.getSqlFielStatement("string",this.id)+","+Utility.getSqlFielStatement("string",this.wxaccount)+","+Utility.getSqlFielStatement("string",this.session_key)+","+Utility.getSqlFielStatement("string",this.openid)+","+Utility.getSqlFielStatement("string",this.nickname)+","+Utility.getSqlFielStatement("string",this.avatarurl)+","+Utility.getSqlFielStatement("string",this.gender)+","+Utility.getSqlFielStatement("string",this.province)+","+Utility.getSqlFielStatement("string",this.city)+","+Utility.getSqlFielStatement("string",this.country)+","+Utility.getSqlFielStatement("string",this.model)+","+Utility.getSqlFielStatement("string",this.pixelratio)+","+Utility.getSqlFielStatement("string",this.windowwidth)+","+Utility.getSqlFielStatement("string",this.windowheight)+","+Utility.getSqlFielStatement("string",this.language)+","+Utility.getSqlFielStatement("string",this.version)+","+Utility.getSqlFielStatement("string",this.platform)+","+Utility.getSqlFielStatement("string",this.system)+","+Utility.getSqlFielStatement("date",this.create_time)+","+this.is_valid+","+Utility.getSqlFielStatement("string",this.jd_mc)+","+Utility.getSqlFielStatement("string",this.sq_mc)+","+Utility.getSqlFielStatement("string",this.wxq_mc)+")";
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
		String sql="select id,wxaccount,session_key,openid,nickname,avatarurl,gender,province,city,country,model,pixelratio,windowwidth,windowheight,language,version,platform,system,create_time,is_valid,jd_mc,sq_mc,wxq_mc from "+TABLE_NAME;
		if(!Utility.isNullOrEmpty(id)){
			sql+=" where  id="+Utility.getSqlFielStatement("string",this.id)+" ";
		}
		return sql;
	}
	@Override
	public String getSelectByCondition(String condition) {
		String sql="select id,wxaccount,session_key,openid,nickname,avatarurl,gender,province,city,country,model,pixelratio,windowwidth,windowheight,language,version,platform,system,create_time,is_valid,jd_mc,sq_mc,wxq_mc from "+TABLE_NAME;
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

