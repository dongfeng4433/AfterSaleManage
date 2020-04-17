package com.joindoo.jdwechat.data;


import com.joindoo.jdwechat.SystemSetting;
import com.joindoo.jdwechat.Utility;
import com.joindoo.jdwechat.beans.BaseModel;
import com.joindoo.jdwechat.model.ConnConfigModel;
import com.joindoo.jdwechat.beans.IBaseModel;
import oracle.sql.BLOB;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.*;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by zhuqiang1 on 2016/5/27.
 */
public final class DBHelper {
    public static final Logger logger =  LoggerFactory.getLogger(DBHelper.class);
    static ConnConfigModel configModel=null;

    public static void setConfigModel(ConnConfigModel configModel) {
        DBHelper.configModel = configModel;
    }

    // 此方法为获取数据库连接
    public static Connection getConnection(){
        if(configModel==null){
            configModel =new ConnConfigModel();
            if(StringUtils.isEmpty(SystemSetting.SYS_PATH_CONFIG_DB)){

            }else
                configModel.readConfig(SystemSetting.SYS_PATH_CONFIG_DB);
        }
        return getConnection(configModel);
    }
    public static Connection getConnectionByDruid(DataSource dataSource) {
        Connection conn = null;
        try {
            conn=dataSource.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
    public static Connection getConnection(ConnConfigModel configModel) {
        Connection conn = null;
        try {
            String driver = "com.mysql.jdbc.Driver"; // 数据库驱动
            Class.forName(driver); // 加载数据库驱动
            if (null == conn) {
                conn = DriverManager.getConnection(configModel.url, configModel.user, configModel.password);
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Sorry,can't find the Driver!");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
    public static Connection getConnection2(ConnConfigModel configModel) {
        Connection conn = null;
        try {
            String driver = "com.mysql.jdbc.Driver"; // 数据库驱动
            Class.forName(driver); // 加载数据库驱动
            if (null == conn) {
                conn = DriverManager.getConnection(configModel.url2, configModel.user2, configModel.password2);
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Sorry,can't find the Driver!");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
    public static Connection getConnection3(ConnConfigModel configModel) {
        Connection conn = null;
        try {
            String driver = "com.mysql.jdbc.Driver"; // 数据库驱动
            Class.forName(driver); // 加载数据库驱动
            if (null == conn) {
                conn = DriverManager.getConnection(configModel.url3, configModel.user3, configModel.password3);
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Sorry,can't find the Driver!");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
    public static PreparedStatement getPreparedStatement(Connection conn, String sql){
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
        } catch (SQLException err) {
            err.printStackTrace();
            logger.error(err.getMessage());
        }
        return stmt;
    }


    /**
     * 增删改【Add、Del、Update】
     *
     * @param sql
     * @return int
     */
    public static int executeNonQuery(String sql) {
        int result = 0;
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            result = stmt.executeUpdate(sql);
        } catch (SQLException err) {
            err.printStackTrace();
            logger.error(err.getMessage());
            free(null, stmt, conn);
        } finally {
            free(null, stmt, conn);
        }
        return result;
    }
    public static int executeNonQuery(Connection connection,String sql) {
        int result = 0;
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            result = stmt.executeUpdate(sql);
        } catch (SQLException err) {
            err.printStackTrace();
            logger.error(err.getMessage());
            free(null, stmt, null);
        } finally {
            free(null, stmt, null);
        }
        return result;
    }
    public static int executeNonQuery(List<String> sqlList){
        int result = 0;
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            for(String sql : sqlList){
                stmt.executeUpdate(sql);
                result++;
            }
            conn.commit();

        } catch (SQLException err) {
            err.printStackTrace();
            logger.error(err.getMessage());
            try{
                conn.rollback();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                free(null, stmt, conn);
            }
        } finally {
            free(null, stmt, conn);
        }
        return result;
    }
    public static int executeNonQuery(Connection conn, List<String> sqlList){
        int result = 0;
        Statement stmt = null;
        try {
            // conn = getConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            for(String sql : sqlList){
                stmt.executeUpdate(sql);
                result++;
            }
            conn.commit();

        } catch (SQLException err) {
            err.printStackTrace();
            logger.error(err.getMessage());
            try{
                conn.rollback();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                free(null, stmt, null);
            }
        } finally {
            free(null, stmt, null);
        }
        return result;
    }

    /**
     * 增删改【Add、Delete、Update】
     *
     * @param sql
     * @param obj
     * @return int
     */

    public static int executeNonQuery(String sql, Object... obj) {
        int result = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < obj.length; i++) {
                pstmt.setObject(i + 1, obj[i]);
            }
            result = pstmt.executeUpdate();
        } catch (SQLException err) {
            err.printStackTrace();
            free(null, pstmt, conn);
        } finally {
            free(null, pstmt, conn);
        }
        return result;
    }

    public static int executeNonQuery(Connection conn ,String sql, Object... obj) {
        int result = 0;
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < obj.length; i++) {
                pstmt.setObject(i + 1, obj[i]);
            }
            result = pstmt.executeUpdate();
        } catch (SQLException err) {
            err.printStackTrace();
            free(null, pstmt, null);
        } finally {
            free(null, pstmt, null);
        }
        return result;
    }

    /**

     * 查【Query】

     *

     * @param sql

     * @return ResultSet

     */

    public static ResultSet executeQuery(String sql) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException err) {
            err.printStackTrace();
            free(rs, stmt, conn);
        }
        return rs;
    }
    public static ResultSet executeQuery(Connection connection , String sql) {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException err) {
            err.printStackTrace();
            free(rs, stmt, connection);
        }
        return rs;
    }
    public static ResultSet executeQuery(Connection connection , String sql, Object... obj) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement(sql);
            for (int i = 0; i < obj.length; i++) {
                Object parmas=obj[i];
                if(parmas instanceof Integer){
                    pstmt.setInt(i+1,(Integer)parmas);
                }else if(parmas instanceof String){
                    if(!Utility.isNullOrEmpty(parmas.toString()))
                        pstmt.setString(i+1,parmas.toString());
                }else if(parmas instanceof BigDecimal){
                    pstmt.setBigDecimal(i+1,(BigDecimal)parmas);
                }
                else
                    pstmt.setObject(i + 1, parmas);
            }
            rs = pstmt.executeQuery();
        } catch (SQLException err) {
            err.printStackTrace();
            free(rs, pstmt, null);
        }
        return rs;
    }
    public static List<BaseModel> executeQuery(Class modelType, String sql){
        ResultSet resultSet= DBHelper.executeQuery(sql);
        List<BaseModel> result=new ArrayList<BaseModel>();
        return result;
    }
    /**
     * 查【Query】
     *
     * @param sql
     * @param obj
     * @return ResultSet
     */
    public static ResultSet executeQuery(String sql, Object... obj) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < obj.length; i++) {
                pstmt.setObject(i + 1, obj[i]);
            }
            rs = pstmt.executeQuery();
        } catch (SQLException err) {
            err.printStackTrace();
            free(rs, pstmt, conn);
        }
        return rs;
    }



    /**
     * 判断记录是否存在
     *
     * @param sql
     * @return Boolean
     */

    public static Boolean isExist(String sql) {
        ResultSet rs = null;
        try {
            rs = executeQuery(sql);
            rs.last();
            int count = rs.getRow();
            if (count > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException err) {
            err.printStackTrace();
            free(rs);
            return false;
        } finally {
            free(rs);
        }
    }
    public static Boolean isExist(Connection connection, String sql) {
        ResultSet rs = null;
        try {
            rs = executeQuery(connection, sql);
            rs.last();
            int count = rs.getRow();
            if (count > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException err) {
            err.printStackTrace();
            free(rs);
            return false;
        } finally {
            free(rs);
        }
    }


    /**
     * 判断记录是否存在
     *
     * @param sql
     * @return Boolean
     */

    public static Boolean isExist(String sql, Object... obj) {
        ResultSet rs = null;
        try {
            rs = executeQuery(sql, obj);
            rs.last();
            int count = rs.getRow();
            if (count > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException err) {
            err.printStackTrace();
            free(rs);
            return false;
        } finally {
            free(rs);
        }
    }
    /**
     * 获取查询记录的总行数
     *
     * @param sql
     * @return int
     */

    public static int getCount(String sql) {
        int result = 0;
        ResultSet rs = null;
        try {
            rs = executeQuery(sql);
            rs.last();
            result = rs.getRow();
        } catch (SQLException err) {
            free(rs);
            err.printStackTrace();
        } finally {
            free(rs);
        }
        return result;
    }


    /**
     * 获取查询记录的总行数
     *
     * @param sql
     * @param obj
     * @return int
     */
    public static int getCount(String sql, Object... obj) {
        int result = 0;
        ResultSet rs = null;
        try {
            rs = executeQuery(sql, obj);
            rs.last();
            result = rs.getRow();
        } catch (SQLException err) {
            err.printStackTrace();
        } finally {
            free(rs);
        }
        return result;
    }

    public static int getCount(Connection connection, String sql, Object... obj) {
        int result = 0;
        ResultSet rs = null;
        try {
            rs = executeQuery(connection,sql, obj);
            rs.last();
            result = rs.getRow();
        } catch (SQLException err) {
            err.printStackTrace();
        } finally {
            free(rs);
        }
        return result;
    }

    /**
     * 释放【ResultSet】资源
     *
     * @param rs
     */

    public static void free(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException err) {
            err.printStackTrace();
        }
    }



    /**
     * 释放【Statement】资源
     *
     * @param st
     */
    public static void free(Statement st) {
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException err) {
            err.printStackTrace();
        }
    }



    /**
     * 释放【Connection】资源
     *
     * @param conn
     */
    public static void free(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException err) {
            err.printStackTrace();
        }

    }



    /**
     * 释放所有数据资源
     *
     * @param rs
     * @param st
     * @param conn
     */

    public static void free(ResultSet rs, Statement st, Connection conn) {
        free(rs);
        free(st);
        free(conn);
    }

    /**
     * 通用查询接口
     * @param modelType
     * @param sql
     * @param objects
     * @return
     */
    public static Collection<IBaseModel> executeQuery(Connection connection,Class modelType, String sql, final Object... objects){
        List<IBaseModel> models=new ArrayList<>();
        try {
            ResultSet resultSet= DBHelper.executeQuery(connection,sql,objects);
            while(resultSet.next()){
                IBaseModel model=(IBaseModel) modelType.newInstance();
                model.fillModel(resultSet);
                models.add(model);
            }
        } catch (SQLException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return models;
    }
    public static Collection<com.joindoo.jdwechat.model.IBaseModel> executeQuery2(Connection connection,Class modelType, String sql, final Object... objects){
        List<com.joindoo.jdwechat.model.IBaseModel> models=new ArrayList<>();
        try {
            ResultSet resultSet= DBHelper.executeQuery(connection,sql,objects);
            java.lang.reflect.Field[] fields= modelType.getDeclaredFields();
            List<String> columns =getColumnsByResultSet(resultSet);
            while(resultSet.next()){
                com.joindoo.jdwechat.model.IBaseModel model=(com.joindoo.jdwechat.model.IBaseModel) modelType.newInstance();
                for(Field field:fields){
                    String name=field.getName();
                    if(columns.contains(name))
                        fillModelField(resultSet,model,name);
                }
                models.add(model);
            }
        } catch (SQLException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return models;
    }
    /**
     * 获取列名集合
     * @param resultSet
     * @return
     * @throws SQLException
     */
    private static List<String> getColumnsByResultSet(ResultSet resultSet) throws SQLException {
        java.sql.ResultSetMetaData rsmd = resultSet.getMetaData();
        int count=rsmd.getColumnCount();
        List<String> cloumns=new ArrayList<>();
        for(int i=1;i<=count;i++){
            cloumns.add(rsmd.getColumnLabel(i).toLowerCase());
        }
        return cloumns;
    }
    private static void fillModelField(ResultSet resultSet,com.joindoo.jdwechat.model.IBaseModel model, String name){
        try {
            if(resultSet.findColumn(name) > 0 ){
                model.fillModelField(resultSet,name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void updateBlob(Connection connection,String path,String sqlInsert, String sqlQuery, Object... args) {
        PreparedStatement prepareStatement = null;
        ResultSet rs = null;

        OutputStream os = null;
        FileInputStream fis = null;
        try
        {
            // 现在表中插入空的Blob
            // 事物处理前，取消Connection的默认提交行为
            connection.setAutoCommit(false);
            prepareStatement = connection.prepareStatement(sqlInsert);

            for (int i = 0; i < args.length; i++)
            {
                prepareStatement.setObject(i + 1, args[i]);
            }
            prepareStatement.executeUpdate();
            BLOB blob = null;
            prepareStatement = connection.prepareStatement(sqlQuery);
            for (int i = 0; i < args.length; i++) {
                prepareStatement.setObject(i + 1, args[i]);
            }

            // prepareStatement.setString(1, "QQA");
            rs = prepareStatement.executeQuery();
            if (rs.next()) {
                blob = (BLOB)rs.getBlob(1);
            }
            // 得到数据库的输出流
            os = blob != null ? blob.getBinaryOutputStream() : null;
            // 得到要插入文件的输入流
            fis = new FileInputStream(path);
            byte[] b = new byte[1024];
            int len;
            while (-1 != (len = fis.read(b))) {
                if (os != null) {
                    os.write(b, 0, len);
                }
            }
            // 清空流的缓存
            if (os != null) {
                os.flush();
            }
            // 事物处理：如果事物处理成功则提交事物
            connection.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
            try  {
                // 事物处理：如果出现异常 则在catch块中回滚事物
                connection.rollback();
            }
            catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        finally {
            if (null != fis) {
                try  {
                    fis.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != os) {
                try  {
                    os.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            free(rs,prepareStatement,null);
        }
    }

    /**
     * 查询有Blob的表 方法 <功能详细描述>
     *
     * @param sql
     * @param args
     * @see [类、类#方法、类#成员]
     */
    public static byte[] queryBlob(Connection connection, String sql, Object... args) {
        PreparedStatement prepareStatement = null;
        ResultSet rs = null;
        FileOutputStream fos = null;
        InputStream is = null;
        File temp=new File("test.jpg");
        try {
            // 事物处理前，取消Connection的默认提交行为
            connection.setAutoCommit(false);
            prepareStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                prepareStatement.setObject(i + 1, args[i]);
            }
            rs = prepareStatement.executeQuery();
            if (rs.next()) {
                BLOB blob = (BLOB)rs.getBlob(1);
                is = blob.getBinaryStream();
                fos = new FileOutputStream(temp);
                byte[] b = new byte[1024];
                int len;
                while (-1 != (len = is.read(b))) {
                    fos.write(b, 0, len);
                }
                fos.flush();
            }
            // 事物处理：如果事物处理成功则提交事物
            connection.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
            try {
                // 事物处理：如果出现异常 则在catch块中回滚事物
                connection.rollback();
            }
            catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        finally {
            if (null != is) {
                try  {
                    is.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != fos) {
                try {
                    fos.close();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
            free(rs,prepareStatement, null);
        }
        return Utility.getBytes(temp);
    }

}
