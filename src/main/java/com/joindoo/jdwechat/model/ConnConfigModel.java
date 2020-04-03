package com.joindoo.jdwechat.model;

import java.io.*;

/**
 * Created by zhuqiang1 on 2016/6/1.
 */
public class ConnConfigModel {
    public String url = null;// 数据库
    public String user =null; // 用户名
    public String password =null; // 密码

    public String url2 = null;// 数据库
    public String user2 =null; // 用户名
    public String password2 =null; // 密码

    public String url3 = null;// 数据库
    public String user3 =null; // 用户名
    public String password3 =null; // 密码

    public void readConfig(String path){
        File file = new File(path);
        try {
            BufferedReader br =new BufferedReader(new FileReader(file));
            String s=null;String spacer="==";
            while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
                s=s.trim();
                if(s.indexOf(spacer)>=0){
                    String[] arr=s.split(spacer);
                    if(arr[0].indexOf("url1")>=0){
                        url=arr[1];
                    }else if(arr[0].indexOf("username1")>=0){
                        user=arr[1];
                    }else if(arr[0].indexOf("password1")>=0){
                        password=arr[1];
                    }else if(arr[0].indexOf("url2")>=0){
                        url2=arr[1];
                    }else if(arr[0].indexOf("username2")>=0){
                        user2=arr[1];
                    }else if(arr[0].indexOf("password2")>=0){
                        password2=arr[1];
                    }else if(arr[0].indexOf("url3")>=0){
                        url3=arr[1];
                    }else if(arr[0].indexOf("username3")>=0){
                        if(arr.length==2)
                            user3=arr[1];
                    }else if(arr[0].indexOf("password3")>=0){
                        if(arr.length==2)
                            password3=arr[1];
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
