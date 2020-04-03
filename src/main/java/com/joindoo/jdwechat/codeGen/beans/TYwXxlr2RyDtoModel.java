package com.joindoo.jdwechat.codeGen.beans;

import java.util.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.format.annotation.DateTimeFormat;

/**
* 业务_信息录入2人员
* Author: zhuqiang4433@gmail.com
* Memo: Auto Created by CodeGenerator on 2018/9/10.
*/

@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class TYwXxlr2RyDtoModel extends BaseDtoModel{
	/*序号*/ 
	protected String xxlr_2_ry_xh;
	public String getxxlr_2_ry_xh (){ return xxlr_2_ry_xh;}
	public void setxxlr_2_ry_xh(String value){
		this.xxlr_2_ry_xh=value;
  	}

	/*信息录入序号*/ 
	protected String xxlr_xh;
	public String getxxlr_xh (){ return xxlr_xh;}
	public void setxxlr_xh(String value){
		this.xxlr_xh=value;
  	}

	/*人员*/ 
	protected String ry_xh;
	public String getry_xh (){ return ry_xh;}
	public void setry_xh(String value){
		this.ry_xh=value;
  	}

	/*是否有效标记*/ 
	protected Integer sfyx_bj;
	public Integer getsfyx_bj (){ return sfyx_bj;}
	public void setsfyx_bj(Integer value){
		this.sfyx_bj=value;
  	}

	/*处理状态*/ 
	protected String clzt_dm;
	public String getclzt_dm (){ return clzt_dm;}
	public void setclzt_dm(String value){
		this.clzt_dm=value;
  	}



}

