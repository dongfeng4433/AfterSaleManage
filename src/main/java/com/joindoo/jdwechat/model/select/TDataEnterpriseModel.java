package com.joindoo.jdwechat.model.select;

import com.joindoo.jdwechat.model.IBaseModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.math.BigDecimal;

/**
* 主数据 - 企业  平台主要表，企业管理员必须关联本表
* Author: zhuqiang4433@gmail.com
* Memo: Auto Created by CodeGenerator on 2020/3/24.
*/

public class TDataEnterpriseModel implements IBaseModel {
	/*企业id*/ 
	protected String ENTERPRISE_ID;
	public String getENTERPRISE_ID (){ return ENTERPRISE_ID;}
	public void setENTERPRISE_ID(String value){
		this.ENTERPRISE_ID=value;
  	}

	/*名称*/ 
	protected String NAME;
	public String getNAME (){ return NAME;}
	public void setNAME(String value){
		this.NAME=value;
  	}

	/*简称*/ 
	protected String SHORT_NAME;
	public String getSHORT_NAME (){ return SHORT_NAME;}
	public void setSHORT_NAME(String value){
		this.SHORT_NAME=value;
  	}

	/*地址*/ 
	protected String ADDRESS;
	public String getADDRESS (){ return ADDRESS;}
	public void setADDRESS(String value){
		this.ADDRESS=value;
  	}

	/*企业介绍*/ 
	protected String DESCRIPTION;
	public String getDESCRIPTION (){ return DESCRIPTION;}
	public void setDESCRIPTION(String value){
		this.DESCRIPTION=value;
  	}

	/*联系电话*/ 
	protected String TELEPHONE_NUMBER;
	public String getTELEPHONE_NUMBER (){ return TELEPHONE_NUMBER;}
	public void setTELEPHONE_NUMBER(String value){
		this.TELEPHONE_NUMBER=value;
  	}

	/*是否有效*/ 
	protected Integer IS_VALID;
	public Integer getIS_VALID (){ return IS_VALID;}
	public void setIS_VALID(Integer value){
		this.IS_VALID=value;
  	}

	/*创建时间*/ 
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date CREATE_TIME;
	public Date getCREATE_TIME (){ return CREATE_TIME;}
	public void setCREATE_TIME(Date value){
		this.CREATE_TIME=value;
  	}

	/*最后修改时间*/ 
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date LAST_EDIT_TIME;
	public Date getLAST_EDIT_TIME (){ return LAST_EDIT_TIME;}
	public void setLAST_EDIT_TIME(Date value){
		this.LAST_EDIT_TIME=value;
  	}

	/*创建人*/ 
	protected String CREATION_USER_ID;
	public String getCREATION_USER_ID (){ return CREATION_USER_ID;}
	public void setCREATION_USER_ID(String value){
		this.CREATION_USER_ID=value;
  	}

	/*最后修改人*/ 
	protected String LAST_EDIT_USER_ID;
	public String getLAST_EDIT_USER_ID (){ return LAST_EDIT_USER_ID;}
	public void setLAST_EDIT_USER_ID(String value){
		this.LAST_EDIT_USER_ID=value;
  	}



	@Override
	public void fillModelField(ResultSet resultSet, String fileName) throws SQLException {
		fileName=fileName.toUpperCase();
		switch (fileName){ 
			case "ENTERPRISE_ID":
				 this.ENTERPRISE_ID=resultSet.getString(fileName);
				break;
			case "NAME":
				 this.NAME=resultSet.getString(fileName);
				break;
			case "SHORT_NAME":
				 this.SHORT_NAME=resultSet.getString(fileName);
				break;
			case "ADDRESS":
				 this.ADDRESS=resultSet.getString(fileName);
				break;
			case "DESCRIPTION":
				 this.DESCRIPTION=resultSet.getString(fileName);
				break;
			case "TELEPHONE_NUMBER":
				 this.TELEPHONE_NUMBER=resultSet.getString(fileName);
				break;
			case "IS_VALID":
				 this.IS_VALID=resultSet.getInt(fileName);
				break;
			case "CREATE_TIME":
				 this.CREATE_TIME=resultSet.getDate(fileName);
				break;
			case "LAST_EDIT_TIME":
				 this.LAST_EDIT_TIME=resultSet.getDate(fileName);
				break;
			case "CREATION_USER_ID":
				 this.CREATION_USER_ID=resultSet.getString(fileName);
				break;
			case "LAST_EDIT_USER_ID":
				 this.LAST_EDIT_USER_ID=resultSet.getString(fileName);
				break;
			default:
				break;
		}
	}


}

