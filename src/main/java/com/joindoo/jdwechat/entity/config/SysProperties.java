package com.joindoo.jdwechat.entity.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by zhuqiang on 2018/3/29.
 */
@Component
@PropertySource("classpath:config/system.properties")
@ConfigurationProperties(prefix="com.jd.wechat")
public class SysProperties {
    private String docs;
    private String facestorage;
    private String ak;
    private String scripts;
    private String server_port;
    private String admin;
    private String appid;
    private String secret;
    private String download_file;
    private String gov_host;

    public String getDocs() {
        return docs;
    }

    public void setDocs(String docs) {
        this.docs = docs;
    }

    public String getFacestorage() {
        return facestorage;
    }

    public void setFacestorage(String facestorage) {
        this.facestorage = facestorage;
    }

    public String getAk() {
        return ak;
    }

    public void setAk(String ak) {
        this.ak = ak;
    }

    public String getScripts() {
        return scripts;
    }

    public void setScripts(String scripts) {
        this.scripts = scripts;
    }

    public String getServer_port() {
        return server_port;
    }

    public void setServer_port(String server_port) {
        this.server_port = server_port;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getDownload_file() {
        return download_file;
    }

    public void setDownload_file(String download_file) {
        this.download_file = download_file;
    }

    public String getGov_host() {
        return gov_host;
    }

    public void setGov_host(String gov_host) {
        this.gov_host = gov_host;
    }
}
