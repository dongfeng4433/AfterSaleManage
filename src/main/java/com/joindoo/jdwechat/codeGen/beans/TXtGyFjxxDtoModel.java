package com.joindoo.jdwechat.codeGen.beans;

import java.util.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
* 系统 - 公用 - 附件信息。附件只能添加，不能修改
* Author: zhuqiang4433@gmail.com
* Memo: Auto Created by CodeGenerator on 2018/8/27.
*/

@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class TXtGyFjxxDtoModel extends BaseDtoModel{
	/*附件序号(64个字节，能够比32字节多存储点内容，包含文件的标识和后缀名)*/ 
	protected String fj_xh;
	public String getfj_xh (){ return fj_xh;}
	public void setfj_xh(String value){
		this.fj_xh=value;
  	}

	/*附件名称*/ 
	protected String fj_mc;
	public String getfj_mc (){ return fj_mc;}
	public void setfj_mc(String value){
		this.fj_mc=value;
  	}

	/*消息附件文件大小数值（B）*/ 
	protected Integer fj_dx;
	public Integer getfj_dx (){ return fj_dx;}
	public void setfj_dx(Integer value){
		this.fj_dx=value;
  	}

	/*文件后缀名*/ 
	protected String fj_wz;
	public String getfj_wz (){ return fj_wz;}
	public void setfj_wz(String value){
		this.fj_wz=value;
  	}

	/*文件路径*/ 
	protected String fj_lj;
	public String getfj_lj (){ return fj_lj;}
	public void setfj_lj(String value){
		this.fj_lj=value;
  	}

	/*时间长度秒钟数, 如果是音频视频，则包含时间长度数值（秒）*/ 
	protected Integer sjcd_mzs;
	public Integer getsjcd_mzs (){ return sjcd_mzs;}
	public void setsjcd_mzs(Integer value){
		this.sjcd_mzs=value;
  	}

	/*失效日期*/ 
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date sx_sj;
	public Date getsx_sj (){ return sx_sj;}
	public void setsx_sj(Date value){
		this.sx_sj=value;
  	}

	/*有效标记(0无效，1有效)*/ 
	protected Integer sfyx_bj;
	public Integer getsfyx_bj (){ return sfyx_bj;}
	public void setsfyx_bj(Integer value){
		this.sfyx_bj=value;
  	}

	/*文件MD5值*/ 
	protected String md5;
	public String getmd5 (){ return md5;}
	public void setmd5(String value){
		this.md5=value;
  	}

	/*录入时间*/ 
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date lr_sj;
	public Date getlr_sj (){ return lr_sj;}
	public void setlr_sj(Date value){
		this.lr_sj=value;
  	}

	/*录入人员*/ 
	protected String lrry_xh;
	public String getlrry_xh (){ return lrry_xh;}
	public void setlrry_xh(String value){
		this.lrry_xh=value;
  	}

	/*成功上传时间*/ 
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date cgsc_sj;
	public Date getcgsc_sj (){ return cgsc_sj;}
	public void setcgsc_sj(Date value){
		this.cgsc_sj=value;
  	}

	/*主附件序号*/ 
	protected String zfj_xh;
	public String getzfj_xh (){ return zfj_xh;}
	public void setzfj_xh(String value){
		this.zfj_xh=value;
  	}



}

