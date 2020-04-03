package com.joindoo.jdwechat.model.query;

import java.util.Date;
import java.math.BigDecimal;

/**
* 系统 - 微信用户 微信用户openid,昵称等
* Author: zhuqiang4433@gmail.com
* Memo: Auto Created by CodeGenerator on 2018/9/5.
*/

public class TXtWxyhQueryModel extends BaseQueryModel{
	/*序号*/ 
	protected String id;
	public String getid (){ return id;}
	public void setid(String value){
		this.id=value;
  	}

	/**/ 
	protected String wxaccount;
	public String getwxaccount (){ return wxaccount;}
	public void setwxaccount(String value){
		this.wxaccount=value;
  	}

	/*登录时session*/ 
	protected String session_key;
	public String getsession_key (){ return session_key;}
	public void setsession_key(String value){
		this.session_key=value;
  	}

	/*微信的openid*/ 
	protected String openid;
	public String getopenid (){ return openid;}
	public void setopenid(String value){
		this.openid=value;
  	}

	/*昵称*/ 
	protected String nickname;
	public String getnickname (){ return nickname;}
	public void setnickname(String value){
		this.nickname=value;
  	}

	/*头像链接*/ 
	protected String avatarurl;
	public String getavatarurl (){ return avatarurl;}
	public void setavatarurl(String value){
		this.avatarurl=value;
  	}

	/*性别 0：未知、1：男、2：女*/ 
	protected String gender;
	public String getgender (){ return gender;}
	public void setgender(String value){
		this.gender=value;
  	}

	/*所在省*/ 
	protected String province;
	public String getprovince (){ return province;}
	public void setprovince(String value){
		this.province=value;
  	}

	/*城市*/ 
	protected String city;
	public String getcity (){ return city;}
	public void setcity(String value){
		this.city=value;
  	}

	/*国家*/ 
	protected String country;
	public String getcountry (){ return country;}
	public void setcountry(String value){
		this.country=value;
  	}

	/*手机型号*/ 
	protected String model;
	public String getmodel (){ return model;}
	public void setmodel(String value){
		this.model=value;
  	}

	/**/ 
	protected String pixelratio;
	public String getpixelratio (){ return pixelratio;}
	public void setpixelratio(String value){
		this.pixelratio=value;
  	}

	/**/ 
	protected String windowwidth;
	public String getwindowwidth (){ return windowwidth;}
	public void setwindowwidth(String value){
		this.windowwidth=value;
  	}

	/**/ 
	protected String windowheight;
	public String getwindowheight (){ return windowheight;}
	public void setwindowheight(String value){
		this.windowheight=value;
  	}

	/*语言*/ 
	protected String language;
	public String getlanguage (){ return language;}
	public void setlanguage(String value){
		this.language=value;
  	}

	/*版本*/ 
	protected String version;
	public String getversion (){ return version;}
	public void setversion(String value){
		this.version=value;
  	}

	/*平台*/ 
	protected String platform;
	public String getplatform (){ return platform;}
	public void setplatform(String value){
		this.platform=value;
  	}

	/*系统*/ 
	protected String system;
	public String getsystem (){ return system;}
	public void setsystem(String value){
		this.system=value;
  	}

	/*创建时间*/ 
	protected Date create_time;
	public Date getcreate_time (){ return create_time;}
	public void setcreate_time(Date value){
		this.create_time=value;
  	}

	/*是否有效*/ 
	protected Integer is_valid;
	public Integer getis_valid (){ return is_valid;}
	public void setis_valid(Integer value){
		this.is_valid=value;
  	}



}

