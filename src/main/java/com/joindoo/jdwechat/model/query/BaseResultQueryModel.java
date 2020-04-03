package com.joindoo.jdwechat.model.query;

public class BaseResultQueryModel extends TYwXxlrQueryModel{
	protected String lr_type;//01 待办 02交办
	protected String ry_xh;//用于通知提醒，检索回复详情的部门字段
	protected String clzt;//0未处理1已处理
	protected String xtmk_dm;//系统模块

	protected String queryType;//营业执照检索（企业/个体）
	protected String queryText;//营业执照检索

	public String getLr_type() {
		return lr_type;
	}

	public void setLr_type(String lr_type) {
		this.lr_type = lr_type;
	}

	public String getRy_xh() {
		return ry_xh;
	}

	public void setRy_xh(String ry_xh) {
		this.ry_xh = ry_xh;
	}

	public String getClzt() {
		return clzt;
	}

	public void setClzt(String clzt) {
		this.clzt = clzt;
	}

	public String getXtmk_dm() {
		return xtmk_dm;
	}

	public void setXtmk_dm(String xtmk_dm) {
		this.xtmk_dm = xtmk_dm;
	}

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public String getQueryText() {
		return queryText;
	}

	public void setQueryText(String queryText) {
		this.queryText = queryText;
	}
}

