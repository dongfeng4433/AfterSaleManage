package com.joindoo.jdwechat.beans;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.joindoo.jdwechat.Utility;

import java.util.Date;

public class YyzzModel {
    private String ZCLX_DM;
    private String XH;
    private String ZCH;
    private String MC;
    private String FJ_XH;
    private boolean DZZP_BJ;

    private String FDDBR_FZR ;
    private String ZS ;
    public double ZCZB ;
    private String MCHZH ;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date HZ_RQ ;
    private String SSXQ_MC ;
    private String QYXYDJ ;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date KY_SJ ;
    private String SS_HY ;
    private String DH_HM ;
    private String QYLX_JJSX ;
    private String DJJG ;
    private String QY_ZT ;
    public int CY_RS ;
    private String YB_HM ;


    private String JYZ_XM ;
    private String JYCS ;
    private String XYDJ ;
    private String SDJGGSS ;
    private String SSHY ;
    private String JZCH ;
    private String XKJYFW ;
    private String JYFW ;
    private String JYZZS ;
    private String GTGSH_ZT ;
    private String SJDJJG ;
    private String YBJYXM ;
    private double ZJSE ;
    private int XGSY_RS ;

    public String getZCLX_DM() {
        return ZCLX_DM;
    }

    public void setZCLX_DM(String ZCLX_DM) {
        this.ZCLX_DM = ZCLX_DM;
    }

    public String getXH() {
        return XH;
    }

    public void setXH(String XH) {
        this.XH = XH;
    }

    public String getZCH() {
        return ZCH;
    }

    public void setZCH(String ZCH) {
        this.ZCH = ZCH;
    }

    public String getMC() {
        return MC;
    }

    public void setMC(String MC) {
        this.MC = MC;
    }

    public String getFJ_XH() {
        return FJ_XH;
    }

    public void setFJ_XH(String FJ_XH) {
        this.FJ_XH = FJ_XH;
    }

    public boolean isDZZP_BJ() {
        return DZZP_BJ;
    }

    public void setDZZP_BJ(boolean DZZP_BJ) {
        this.DZZP_BJ = DZZP_BJ;
    }

    public String getFDDBR_FZR() {
        return FDDBR_FZR;
    }

    public void setFDDBR_FZR(String FDDBR_FZR) {
        this.FDDBR_FZR = FDDBR_FZR;
    }

    public String getZS() {
        return ZS;
    }

    public void setZS(String ZS) {
        this.ZS = ZS;
    }

    public double getZCZB() {
        return ZCZB;
    }

    public void setZCZB(double ZCZB) {
        this.ZCZB = ZCZB;
    }

    public String getMCHZH() {
        return MCHZH;
    }

    public void setMCHZH(String MCHZH) {
        this.MCHZH = MCHZH;
    }

    public Date getHZ_RQ() {
        return HZ_RQ;
    }

    public void setHZ_RQ(Date HZ_RQ) {
        this.HZ_RQ = HZ_RQ;
    }

    public String getSSXQ_MC() {
        return SSXQ_MC;
    }

    public void setSSXQ_MC(String SSXQ_MC) {
        this.SSXQ_MC = SSXQ_MC;
    }

    public String getQYXYDJ() {
        return QYXYDJ;
    }

    public void setQYXYDJ(String QYXYDJ) {
        this.QYXYDJ = QYXYDJ;
    }

    public Date getKY_SJ() {
        return KY_SJ;
    }

    public void setKY_SJ(Date KY_SJ) {
        this.KY_SJ = KY_SJ;
    }

    public String getSS_HY() {
        return SS_HY;
    }

    public void setSS_HY(String SS_HY) {
        this.SS_HY = SS_HY;
    }

    public String getDH_HM() {
        return DH_HM;
    }

    public void setDH_HM(String DH_HM) {
        this.DH_HM = DH_HM;
    }

    public String getQYLX_JJSX() {
        return QYLX_JJSX;
    }

    public void setQYLX_JJSX(String QYLX_JJSX) {
        this.QYLX_JJSX = QYLX_JJSX;
    }

    public String getDJJG() {
        return DJJG;
    }

    public void setDJJG(String DJJG) {
        this.DJJG = DJJG;
    }

    public String getQY_ZT() {
        return QY_ZT;
    }

    public void setQY_ZT(String QY_ZT) {
        this.QY_ZT = QY_ZT;
    }

    public int getCY_RS() {
        return CY_RS;
    }

    public void setCY_RS(int CY_RS) {
        this.CY_RS = CY_RS;
    }

    public String getYB_HM() {
        return YB_HM;
    }

    public void setYB_HM(String YB_HM) {
        this.YB_HM = YB_HM;
    }

    public String getJYZ_XM() {
        return JYZ_XM;
    }

    public void setJYZ_XM(String JYZ_XM) {
        this.JYZ_XM = JYZ_XM;
    }

    public String getJYCS() {
        return JYCS;
    }

    public void setJYCS(String JYCS) {
        this.JYCS = JYCS;
    }

    public String getXYDJ() {
        return XYDJ;
    }

    public void setXYDJ(String XYDJ) {
        this.XYDJ = XYDJ;
    }

    public String getSDJGGSS() {
        return SDJGGSS;
    }

    public void setSDJGGSS(String SDJGGSS) {
        this.SDJGGSS = SDJGGSS;
    }

    public String getSSHY() {
        return SSHY;
    }

    public void setSSHY(String SSHY) {
        this.SSHY = SSHY;
    }

    public String getJZCH() {
        return JZCH;
    }

    public void setJZCH(String JZCH) {
        this.JZCH = JZCH;
    }

    public String getXKJYFW() {
        return XKJYFW;
    }

    public void setXKJYFW(String XKJYFW) {
        this.XKJYFW = XKJYFW;
    }

    public String getJYFW() {
        return JYFW;
    }

    public void setJYFW(String JYFW) {
        this.JYFW = JYFW;
    }

    public String getJYZZS() {
        return JYZZS;
    }

    public void setJYZZS(String JYZZS) {
        this.JYZZS = JYZZS;
    }

    public String getGTGSH_ZT() {
        return GTGSH_ZT;
    }

    public void setGTGSH_ZT(String GTGSH_ZT) {
        this.GTGSH_ZT = GTGSH_ZT;
    }

    public String getSJDJJG() {
        return SJDJJG;
    }

    public void setSJDJJG(String SJDJJG) {
        this.SJDJJG = SJDJJG;
    }

    public String getYBJYXM() {
        return YBJYXM;
    }

    public void setYBJYXM(String YBJYXM) {
        this.YBJYXM = YBJYXM;
    }

    public double getZJSE() {
        return ZJSE;
    }

    public void setZJSE(double ZJSE) {
        this.ZJSE = ZJSE;
    }

    public int getXGSY_RS() {
        return XGSY_RS;
    }

    public void setXGSY_RS(int XGSY_RS) {
        this.XGSY_RS = XGSY_RS;
    }

    public void fillModel(JSONObject jsonObject){
        if(jsonObject.get("XH")!=null){
            this.setXH(jsonObject.get("XH").toString());
        }
        if(jsonObject.get("ZCH")!=null){
            this.setZCH(jsonObject.get("ZCH").toString());
        }
        if(jsonObject.get("MC")!=null){
            this.setMC(jsonObject.get("MC").toString());
        }
        if(jsonObject.get("FJ_XH")!=null){
            this.setFJ_XH(jsonObject.get("FJ_XH").toString());
        }
        if(jsonObject.get("DZZP_BJ")!=null){
            this.setDZZP_BJ(jsonObject.getBoolean("DZZP_BJ"));
        }

        //region 公司
        if(jsonObject.get("FDDBR_FZR")!=null){
            this.setFDDBR_FZR(jsonObject.get("FDDBR_FZR").toString());
        }
        if(jsonObject.get("ZS")!=null){
            this.setZS(jsonObject.get("ZS").toString());
        }
        if(jsonObject.get("ZCZB")!=null){
            this.setZCZB(jsonObject.getDoubleValue("ZCZB"));
        }
        if(jsonObject.get("MCHZH")!=null){
            this.setMCHZH(jsonObject.get("MCHZH").toString());
        }
        if(jsonObject.get("HZ_RQ")!=null){
            String date=jsonObject.getString("HZ_RQ");
            this.setHZ_RQ(Utility.parseDate(date.replace("T"," ")));
        }
        if(jsonObject.get("SSXQ_MC")!=null){
            this.setSSXQ_MC(jsonObject.get("SSXQ_MC").toString());
        }
        if(jsonObject.get("QYXYDJ")!=null){
            this.setQYXYDJ(jsonObject.get("QYXYDJ").toString());
        }
        if(jsonObject.get("KY_SJ")!=null){
            String date=jsonObject.getString("KY_SJ");
            this.setKY_SJ(Utility.parseDate(date.replace("T"," ")));
        }
        if(jsonObject.get("SS_HY")!=null){
            this.setSS_HY(jsonObject.get("SS_HY").toString());
        }
        if(jsonObject.get("DH_HM")!=null){
            this.setDH_HM(jsonObject.get("DH_HM").toString());
        }
        if(jsonObject.get("QYLX_JJSX")!=null){
            this.setQYLX_JJSX(jsonObject.get("QYLX_JJSX").toString());
        }
        if(jsonObject.get("DJJG")!=null){
            this.setDJJG(jsonObject.get("DJJG").toString());
        }
        if(jsonObject.get("QY_ZT")!=null){
            this.setQY_ZT(jsonObject.get("QY_ZT").toString());
        }
        if(jsonObject.get("CY_RS")!=null){
            this.setCY_RS(jsonObject.getIntValue("CY_RS"));
        }
        if(jsonObject.get("YB_HM")!=null){
            this.setYB_HM(jsonObject.get("YB_HM").toString());
        }
        //endregion

        //region 个体户
        if(jsonObject.get("JYZ_XM")!=null){
            this.setJYZ_XM(jsonObject.getString("JYZ_XM"));
        }
        if(jsonObject.get("JYCS")!=null){
            this.setJYCS(jsonObject.getString("JYCS"));
        }
        if(jsonObject.get("XYDJ")!=null){
            this.setXYDJ(jsonObject.getString("XYDJ"));
        }
        if(jsonObject.get("SDJGGSS")!=null){
            this.setSDJGGSS(jsonObject.getString("SDJGGSS"));
        }
        if(jsonObject.get("SSHY")!=null){
            this.setSSHY(jsonObject.getString("SSHY"));
        }
        if(jsonObject.get("JZCH")!=null){
            this.setJZCH(jsonObject.getString("JZCH"));
        }
        if(jsonObject.get("XKJYFW")!=null){
            this.setXKJYFW(jsonObject.getString("XKJYFW"));
        }
        if(jsonObject.get("JYFW")!=null){
            this.setJYFW(jsonObject.getString("JYFW"));
        }
        if(jsonObject.get("JYZZS")!=null){
            this.setJYZZS(jsonObject.getString("JYZZS"));
        }
        if(jsonObject.get("GTGSH_ZT")!=null){
            this.setGTGSH_ZT(jsonObject.getString("GTGSH_ZT"));
        }
        if(jsonObject.get("SJDJJG")!=null){
            this.setSJDJJG(jsonObject.getString("SJDJJG"));
        }
        if(jsonObject.get("YBJYXM")!=null){
            this.setYBJYXM(jsonObject.getString("YBJYXM"));
        }
        if(jsonObject.get("ZJSE")!=null){
            this.setZJSE(jsonObject.getDoubleValue("ZJSE"));
        }
        if(jsonObject.get("XGSY_RS")!=null){
            this.setXGSY_RS(jsonObject.getIntValue("XGSY_RS"));
        }


        //endregion
    }
}
