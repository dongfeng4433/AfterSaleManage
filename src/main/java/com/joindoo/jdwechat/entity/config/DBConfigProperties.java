package com.joindoo.jdwechat.entity.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by zhuqiang on 2018/3/29.
 */
@Component
@ConfigurationProperties("spring.datasource")
public class DBConfigProperties {
    private String driver_class_name;
    private String url;
    private String username;
    private String password;

    public String getDriver_class_name() {
        return driver_class_name;
    }

    public void setDriver_class_name(String driver_class_name) {
        this.driver_class_name = driver_class_name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
