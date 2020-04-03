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
* 业务_纳税人   企业或个体户主表 营业执照
* Author: zhuqiang4433@gmail.com
* Memo: Auto Created by CodeGenerator on 2019/8/27.
*/

public class TYwNsrModel implements IBaseModel {
	/*序号*/ 
	protected String NSR_XH;
	public String getNSR_XH (){ return NSR_XH;}
	public void setNSR_XH(String value){
		this.NSR_XH=value;
  	}

	/*统一社会信用代码*/ 
	protected String TYSHXYDM;
	public String getTYSHXYDM (){ return TYSHXYDM;}
	public void setTYSHXYDM(String value){
		this.TYSHXYDM=value;
  	}

	/*纳税人类别代码(企业还是个体户)*/ 
	protected String NSRLBDM;
	public String getNSRLBDM (){ return NSRLBDM;}
	public void setNSRLBDM(String value){
		this.NSRLBDM=value;
  	}

	/*纳税人名称*/ 
	protected String NSR_MC;
	public String getNSR_MC (){ return NSR_MC;}
	public void setNSR_MC(String value){
		this.NSR_MC=value;
  	}

	/*法定代表人/负责人*/ 
	protected String FDDBR_FZR;
	public String getFDDBR_FZR (){ return FDDBR_FZR;}
	public void setFDDBR_FZR(String value){
		this.FDDBR_FZR=value;
  	}

	/*住所*/ 
	protected String ZS;
	public String getZS (){ return ZS;}
	public void setZS(String value){
		this.ZS=value;
  	}

	/*注册资本（万）*/ 
	protected Double ZCZB;
	public Double getZCZB (){ return ZCZB;}
	public void setZCZB(Double value){
		this.ZCZB=value;
  	}

	/*名称核准号*/ 
	protected String MCHZH;
	public String getMCHZH (){ return MCHZH;}
	public void setMCHZH(String value){
		this.MCHZH=value;
  	}

	/*核准日期*/ 
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date HZ_RQ;
	public Date getHZ_RQ (){ return HZ_RQ;}
	public void setHZ_RQ(Date value){
		this.HZ_RQ=value;
  	}

	/*所属辖区名称*/ 
	protected String SSXQ_MC;
	public String getSSXQ_MC (){ return SSXQ_MC;}
	public void setSSXQ_MC(String value){
		this.SSXQ_MC=value;
  	}

	/*企业信用等级*/ 
	protected String QYXYDJ;
	public String getQYXYDJ (){ return QYXYDJ;}
	public void setQYXYDJ(String value){
		this.QYXYDJ=value;
  	}

	/*下岗失业人数*/ 
	protected Integer XGSY_RS;
	public Integer getXGSY_RS (){ return XGSY_RS;}
	public void setXGSY_RS(Integer value){
		this.XGSY_RS=value;
  	}

	/*营业执照状态*/ 
	protected String YYZZ_ZT;
	public String getYYZZ_ZT (){ return YYZZ_ZT;}
	public void setYYZZ_ZT(String value){
		this.YYZZ_ZT=value;
  	}

	/*经营范围及方式*/ 
	protected String JYFWJFS;
	public String getJYFWJFS (){ return JYFWJFS;}
	public void setJYFWJFS(String value){
		this.JYFWJFS=value;
  	}

	/*开业时间*/ 
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date KY_SJ;
	public Date getKY_SJ (){ return KY_SJ;}
	public void setKY_SJ(Date value){
		this.KY_SJ=value;
  	}

	/*所属行业*/ 
	protected String SS_HY;
	public String getSS_HY (){ return SS_HY;}
	public void setSS_HY(String value){
		this.SS_HY=value;
  	}

	/*电话号码*/ 
	protected String DH_HM;
	public String getDH_HM (){ return DH_HM;}
	public void setDH_HM(String value){
		this.DH_HM=value;
  	}

	/*企业类型/经济性质*/ 
	protected String QYLX_JJSX;
	public String getQYLX_JJSX (){ return QYLX_JJSX;}
	public void setQYLX_JJSX(String value){
		this.QYLX_JJSX=value;
  	}

	/*登记机关*/ 
	protected String DJJG;
	public String getDJJG (){ return DJJG;}
	public void setDJJG(String value){
		this.DJJG=value;
  	}

	/*企业状态*/ 
	protected String QY_ZT;
	public String getQY_ZT (){ return QY_ZT;}
	public void setQY_ZT(String value){
		this.QY_ZT=value;
  	}

	/*从业人数*/ 
	protected Integer CY_RS;
	public Integer getCY_RS (){ return CY_RS;}
	public void setCY_RS(Integer value){
		this.CY_RS=value;
  	}

	/*邮编号码*/ 
	protected String YB_HM;
	public String getYB_HM (){ return YB_HM;}
	public void setYB_HM(String value){
		this.YB_HM=value;
  	}

	/*巡查组*/ 
	protected String XCZ;
	public String getXCZ (){ return XCZ;}
	public void setXCZ(String value){
		this.XCZ=value;
  	}

	/*营业执照附件*/ 
	protected String YYZZFJ_XH;
	public String getYYZZFJ_XH (){ return YYZZFJ_XH;}
	public void setYYZZFJ_XH(String value){
		this.YYZZFJ_XH=value;
  	}

	/*是否有效标记*/ 
	protected Integer SFYX_BJ;
	public Integer getSFYX_BJ (){ return SFYX_BJ;}
	public void setSFYX_BJ(Integer value){
		this.SFYX_BJ=value;
  	}

	/*描述*/ 
	protected String MS;
	public String getMS (){ return MS;}
	public void setMS(String value){
		this.MS=value;
  	}

	/*备注*/ 
	protected String BZ;
	public String getBZ (){ return BZ;}
	public void setBZ(String value){
		this.BZ=value;
  	}

	/*录入人员序号*/ 
	protected String LRRY_XH;
	public String getLRRY_XH (){ return LRRY_XH;}
	public void setLRRY_XH(String value){
		this.LRRY_XH=value;
  	}

	/*修改人员序号*/ 
	protected String XGRY_XH;
	public String getXGRY_XH (){ return XGRY_XH;}
	public void setXGRY_XH(String value){
		this.XGRY_XH=value;
  	}

	/**/ 
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date LR_SJ;
	public Date getLR_SJ (){ return LR_SJ;}
	public void setLR_SJ(Date value){
		this.LR_SJ=value;
  	}

	/**/ 
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date XG_SJ;
	public Date getXG_SJ (){ return XG_SJ;}
	public void setXG_SJ(Date value){
		this.XG_SJ=value;
  	}



	@Override
	public void fillModelField(ResultSet resultSet, String fileName) throws SQLException {
		fileName=fileName.toUpperCase();
		switch (fileName){ 
			case "NSR_XH":
				 this.NSR_XH=resultSet.getString(fileName);
				break;
			case "TYSHXYDM":
				 this.TYSHXYDM=resultSet.getString(fileName);
				break;
			case "NSRLBDM":
				 this.NSRLBDM=resultSet.getString(fileName);
				break;
			case "NSR_MC":
				 this.NSR_MC=resultSet.getString(fileName);
				break;
			case "FDDBR_FZR":
				 this.FDDBR_FZR=resultSet.getString(fileName);
				break;
			case "ZS":
				 this.ZS=resultSet.getString(fileName);
				break;
			case "ZCZB":
				 this.ZCZB=resultSet.getDouble(fileName);
				break;
			case "MCHZH":
				 this.MCHZH=resultSet.getString(fileName);
				break;
			case "HZ_RQ":
				 this.HZ_RQ=resultSet.getDate(fileName);
				break;
			case "SSXQ_MC":
				 this.SSXQ_MC=resultSet.getString(fileName);
				break;
			case "QYXYDJ":
				 this.QYXYDJ=resultSet.getString(fileName);
				break;
			case "XGSY_RS":
				 this.XGSY_RS=resultSet.getInt(fileName);
				break;
			case "YYZZ_ZT":
				 this.YYZZ_ZT=resultSet.getString(fileName);
				break;
			case "JYFWJFS":
				 this.JYFWJFS=resultSet.getString(fileName);
				break;
			case "KY_SJ":
				 this.KY_SJ=resultSet.getDate(fileName);
				break;
			case "SS_HY":
				 this.SS_HY=resultSet.getString(fileName);
				break;
			case "DH_HM":
				 this.DH_HM=resultSet.getString(fileName);
				break;
			case "QYLX_JJSX":
				 this.QYLX_JJSX=resultSet.getString(fileName);
				break;
			case "DJJG":
				 this.DJJG=resultSet.getString(fileName);
				break;
			case "QY_ZT":
				 this.QY_ZT=resultSet.getString(fileName);
				break;
			case "CY_RS":
				 this.CY_RS=resultSet.getInt(fileName);
				break;
			case "YB_HM":
				 this.YB_HM=resultSet.getString(fileName);
				break;
			case "XCZ":
				 this.XCZ=resultSet.getString(fileName);
				break;
			case "YYZZFJ_XH":
				 this.YYZZFJ_XH=resultSet.getString(fileName);
				break;
			case "SFYX_BJ":
				 this.SFYX_BJ=resultSet.getInt(fileName);
				break;
			case "MS":
				 this.MS=resultSet.getString(fileName);
				break;
			case "BZ":
				 this.BZ=resultSet.getString(fileName);
				break;
			case "LRRY_XH":
				 this.LRRY_XH=resultSet.getString(fileName);
				break;
			case "XGRY_XH":
				 this.XGRY_XH=resultSet.getString(fileName);
				break;
			case "LR_SJ":
				 this.LR_SJ=resultSet.getDate(fileName);
				break;
			case "XG_SJ":
				 this.XG_SJ=resultSet.getDate(fileName);
				break;
			default:
				break;
		}
	}


}

