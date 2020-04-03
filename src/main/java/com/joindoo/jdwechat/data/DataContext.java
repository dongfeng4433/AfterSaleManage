package com.joindoo.jdwechat.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by zhuqiang1 on 2017/3/22.
 */
public class DataContext {
    protected final Logger logger = LoggerFactory.getLogger(DataContext.class);
    public static DataSource _dataSource=null;
    public static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    private static DataContext _instance=null;
    public static DataContext getInstance(){return _instance;}

    public DataContext(){}
    public DataContext(DataSource dataSource){
        _dataSource=dataSource;
    }
    public static void setInstance(DataContext dataContext){
        if(dataContext!=null&&_instance==null)_instance=dataContext;
        if(_instance==null)_instance=new DataContext();
        _instance.initialize();
    }

    public static Connection getCurrentConnection(){
        Connection connection= threadLocal.get();
        if(null==connection&&null!=_instance){
            _instance.initialize();
            connection= DataContext.threadLocal.get();
        }
        return connection;
    }
    /**
     * 初始化系统
     */
    public void initialize(){
        Connection _Connection= threadLocal.get();
        if(null!=_dataSource&&null==_Connection){
            try {
                _Connection=_dataSource.getConnection();
                threadLocal.set(_Connection);
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error(e.getMessage());
            }
        }
        if(_Connection==null){
            _Connection= DBHelper.getConnection();
            threadLocal.set(_Connection);
        }
    }

    /**
     * 释放资源
     */
    public void release(){
        try {
            Connection _Connection = this.threadLocal.get();
            if(_Connection != null) {
                DBHelper.free(_Connection);
            }
            this.threadLocal.remove();
        } catch (Exception var2) {
            logger.error(var2.getMessage());
        }
    }

}
