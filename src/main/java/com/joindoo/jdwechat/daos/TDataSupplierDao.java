package com.joindoo.jdwechat.daos;

import com.joindoo.jdwechat.data.DBHelper;
import com.joindoo.jdwechat.data.DataContext;
import com.joindoo.jdwechat.Utility;
import com.joindoo.jdwechat.beans.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
/**
* 主数据 - 供应商 可以是个人也可以是企业
* Author: zhuqiang4433@gmail.com
* Version: CodeGenerator 1.1
* Memo: Auto Created by CodeGenerator on 2020/4/4.
*/

public class TDataSupplierDao extends BaseDao {
	public TDataSupplierDao(){super();}
	public TDataSupplierDao(DataContext dataContext){
		super(dataContext);
	}


  @Override
	public boolean exist(BaseModel model) {
	    String sql=((TDataSupplierModel)model).getExist();
      return DBHelper.isExist(DataContext.getCurrentConnection(), sql);
   }


   @Override
   public void insert(BaseModel model) {
       String sql=((TDataSupplierModel)model).getInsert();
       DBHelper.executeNonQuery(DataContext.getCurrentConnection(),sql);
   }
  public boolean insertOnSubmit(BaseModel model) throws Exception {
	TDataSupplierModel bean= (TDataSupplierModel)model;
	 PreparedStatement statement=DBHelper.getPreparedStatement(DataContext.getCurrentConnection(),bean.SCRIPT_INSERT);
	 Object t=null;
	 int index=1;
	 t=bean.getsupplier_id();
	 if (t==null) statement.setNull(index, Types.VARCHAR);
	 else statement.setObject(index, t, Types.VARCHAR);
	 index++;

	 t=bean.getenterprise_id();
	 if (t==null) statement.setNull(index, Types.VARCHAR);
	 else statement.setObject(index, t, Types.VARCHAR);
	 index++;

	 t=bean.getname();
	 if (t==null) statement.setNull(index, Types.VARCHAR);
	 else statement.setObject(index, t, Types.VARCHAR);
	 index++;

	 t=bean.getshort_name();
	 if (t==null) statement.setNull(index, Types.VARCHAR);
	 else statement.setObject(index, t, Types.VARCHAR);
	 index++;

	 t=bean.getaddress();
	 if (t==null) statement.setNull(index, Types.VARCHAR);
	 else statement.setObject(index, t, Types.VARCHAR);
	 index++;

	 t=bean.gettype_code();
	 if (t==null) statement.setNull(index, Types.VARCHAR);
	 else statement.setObject(index, t, Types.VARCHAR);
	 index++;

	 t=bean.getdescription();
	 if (t==null) statement.setNull(index, Types.VARCHAR);
	 else statement.setObject(index, t, Types.VARCHAR);
	 index++;

	 t=bean.gettelephone_number();
	 if (t==null) statement.setNull(index, Types.VARCHAR);
	 else statement.setObject(index, t, Types.VARCHAR);
	 index++;

	 t=bean.getis_valid();
	 if (t==null) statement.setNull(index, Types.INTEGER);
	 else statement.setObject(index, t, Types.INTEGER);
	 index++;

	 t=bean.getcreate_time();
	 if (t==null) statement.setNull(index, Types.TIMESTAMP);
	 else statement.setObject(index, new java.sql.Date(((java.util.Date)t).getTime()), Types.TIMESTAMP);
	 index++;

	 t=bean.getlast_edit_time();
	 if (t==null) statement.setNull(index, Types.TIMESTAMP);
	 else statement.setObject(index, new java.sql.Date(((java.util.Date)t).getTime()), Types.TIMESTAMP);
	 index++;

	 t=bean.getcreation_user_id();
	 if (t==null) statement.setNull(index, Types.VARCHAR);
	 else statement.setObject(index, t, Types.VARCHAR);
	 index++;

	 t=bean.getlast_edit_user_id();
	 if (t==null) statement.setNull(index, Types.VARCHAR);
	 else statement.setObject(index, t, Types.VARCHAR);
	 index++;

	 int r= statement.executeUpdate();
	 DBHelper.free(statement);
	 return r > 0;
    }


  @Override
  public void update(BaseModel model) {
	 String sql=((TDataSupplierModel)model).getUpdate();
	 if(sql!=null&&sql!=""){
	 DBHelper.executeNonQuery(DataContext.getCurrentConnection(),sql);
	 }
  }
   public boolean updateOnSubmit(BaseModel model) throws Exception {
	 TDataSupplierModel bean= (TDataSupplierModel)model;
	 Map<String,Object> changeFields=bean.getChanges();
	 if(changeFields.size()==0)return false;
	 Object t=null;
	 StringBuilder sb=new StringBuilder();
	 if(changeFields.containsKey("enterprise_id"))sb.append("enterprise_id=?,");
	 if(changeFields.containsKey("name"))sb.append("name=?,");
	 if(changeFields.containsKey("short_name"))sb.append("short_name=?,");
	 if(changeFields.containsKey("address"))sb.append("address=?,");
	 if(changeFields.containsKey("type_code"))sb.append("type_code=?,");
	 if(changeFields.containsKey("description"))sb.append("description=?,");
	 if(changeFields.containsKey("telephone_number"))sb.append("telephone_number=?,");
	 if(changeFields.containsKey("is_valid"))sb.append("is_valid=?,");
	 if(changeFields.containsKey("create_time"))sb.append("create_time=?,");
	 if(changeFields.containsKey("last_edit_time"))sb.append("last_edit_time=?,");
	 if(changeFields.containsKey("creation_user_id"))sb.append("creation_user_id=?,");
	 if(changeFields.containsKey("last_edit_user_id"))sb.append("last_edit_user_id=?,");
	 sb.deleteCharAt(sb.length() - 1);
	 String sql= Utility.String_Format(bean.SCRIPT_UPDATE,sb.toString());
	 PreparedStatement statement=DBHelper.getPreparedStatement(DataContext.getCurrentConnection(),sql);
	 int index=1;
	 /*非主键*/
	 if(changeFields.containsKey("enterprise_id")){
		 t=bean.getenterprise_id();
		 if (t==null) statement.setNull(index, Types.VARCHAR);
		 else statement.setObject(index, t, Types.VARCHAR);
		 index++;
	 }
	 if(changeFields.containsKey("name")){
		 t=bean.getname();
		 if (t==null) statement.setNull(index, Types.VARCHAR);
		 else statement.setObject(index, t, Types.VARCHAR);
		 index++;
	 }
	 if(changeFields.containsKey("short_name")){
		 t=bean.getshort_name();
		 if (t==null) statement.setNull(index, Types.VARCHAR);
		 else statement.setObject(index, t, Types.VARCHAR);
		 index++;
	 }
	 if(changeFields.containsKey("address")){
		 t=bean.getaddress();
		 if (t==null) statement.setNull(index, Types.VARCHAR);
		 else statement.setObject(index, t, Types.VARCHAR);
		 index++;
	 }
	 if(changeFields.containsKey("type_code")){
		 t=bean.gettype_code();
		 if (t==null) statement.setNull(index, Types.VARCHAR);
		 else statement.setObject(index, t, Types.VARCHAR);
		 index++;
	 }
	 if(changeFields.containsKey("description")){
		 t=bean.getdescription();
		 if (t==null) statement.setNull(index, Types.VARCHAR);
		 else statement.setObject(index, t, Types.VARCHAR);
		 index++;
	 }
	 if(changeFields.containsKey("telephone_number")){
		 t=bean.gettelephone_number();
		 if (t==null) statement.setNull(index, Types.VARCHAR);
		 else statement.setObject(index, t, Types.VARCHAR);
		 index++;
	 }
	 if(changeFields.containsKey("is_valid")){
		 t=bean.getis_valid();
		 if (t==null) statement.setNull(index, Types.INTEGER);
		 else statement.setObject(index, t, Types.INTEGER);
		 index++;
	 }
	 if(changeFields.containsKey("create_time")){
		 t=bean.getcreate_time();
		 if (t==null) statement.setNull(index, Types.TIMESTAMP);
		 else statement.setObject(index, new java.sql.Date(((java.util.Date)t).getTime()), Types.TIMESTAMP);
		 index++;
	 }
	 if(changeFields.containsKey("last_edit_time")){
		 t=bean.getlast_edit_time();
		 if (t==null) statement.setNull(index, Types.TIMESTAMP);
		 else statement.setObject(index, new java.sql.Date(((java.util.Date)t).getTime()), Types.TIMESTAMP);
		 index++;
	 }
	 if(changeFields.containsKey("creation_user_id")){
		 t=bean.getcreation_user_id();
		 if (t==null) statement.setNull(index, Types.VARCHAR);
		 else statement.setObject(index, t, Types.VARCHAR);
		 index++;
	 }
	 if(changeFields.containsKey("last_edit_user_id")){
		 t=bean.getlast_edit_user_id();
		 if (t==null) statement.setNull(index, Types.VARCHAR);
		 else statement.setObject(index, t, Types.VARCHAR);
		 index++;
	 }
	 /*主键条件*/
	 t=bean.getsupplier_id();
	 if (t==null) statement.setNull(index, Types.VARCHAR);
	 else statement.setObject(index, t, Types.VARCHAR);
	 index++;

	 int r= statement.executeUpdate();
	 DBHelper.free(statement);
	 return r > 0;
  }

    @Override
    public TDataSupplierModel find(BaseModel model) {
	 TDataSupplierModel tModel=(TDataSupplierModel)model;
	 String sql=tModel.getSelect();
	 ResultSet set=DBHelper.executeQuery(DataContext.getCurrentConnection(),sql);
	 try {
	     while(set.next()){
	      tModel.fillModel(set);
	    }
	 } catch (SQLException e) {
	    e.printStackTrace();
	 }
	 return tModel;
  }

   @Override
    public List<BaseModel> findAll(String condition) {
		TDataSupplierModel tModel=new TDataSupplierModel();
		String sql=tModel.getSelectByCondition(condition);
       List<BaseModel> resultList=new ArrayList<BaseModel>();
        ResultSet set=DBHelper.executeQuery(DataContext.getCurrentConnection(),sql);
        getData2ModelList(set,resultList);
	    DBHelper.free(set);
        return resultList;
    }

    public List<BaseModel> findAll(String condition,Object... objects) {
		TDataSupplierModel tModel=new TDataSupplierModel();
		String sql=tModel.getSelectByCondition(condition);
       List<BaseModel> resultList=new ArrayList<BaseModel>();
        ResultSet set=DBHelper.executeQuery(DataContext.getCurrentConnection(),sql,objects);
        getData2ModelList(set,resultList);
	    DBHelper.free(set);
        return resultList;
    }

	@Override
	public void getData2ModelList(ResultSet set, List<BaseModel> resultList){
		try {
			if(null==set)return;
			while(set.next()){
 				TDataSupplierModel model=new TDataSupplierModel();
                model.fillModel(set);
				resultList.add(model);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

    @Override
	public String getCountSql(String condition) {
		String sql="select count(*) as count from "+ TDataSupplierModel.TABLE_NAME;
		return sql;
	}



   @Override
   public void delete(BaseModel model) {
	 String sql=((TDataSupplierModel)model).getDelete();
	 DBHelper.executeNonQuery(DataContext.getCurrentConnection(),sql);
  }
   public boolean deleteOnSubmit(BaseModel model) throws Exception {
	 TDataSupplierModel bean= (TDataSupplierModel)model;
	 PreparedStatement statement=DBHelper.getPreparedStatement(DataContext.getCurrentConnection(),bean.SCRIPT_DELETE);
	 Object t=null;
	 int index=1;
	 t=bean.getsupplier_id();
	 if (t==null) statement.setNull(index, Types.VARCHAR);
	 else statement.setObject(index, t, Types.VARCHAR);
	 index++;

	 int r= statement.executeUpdate();
	 DBHelper.free(statement);
	 return r > 0;
 }

}